package models;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeSet;

import javax.persistence.Entity;
import javax.persistence.Id;

import models.GameRoom.AskQuestion;
import models.GameRoom.FinishAnswering;
import models.GameRoom.Start;
import models.Question;
import Algorithms.Notify;
import Algorithms.PokerAlgorithm;
import ForGameRoom.GameState;
import ForGameRoom.Member;
import akka.actor.Cancellable;

import controllers.Application;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.libs.Akka;
import scala.concurrent.duration.Duration;
@Entity 
public class Game extends Model {


	@Id
	@Constraints.Min(10)
	public Long id;
	
	
	
	private Long player1UID;
	private Long player2UID;
	private Long player3UID;
	private Long player4UID;
	public Boolean isStart=false;
	public Boolean isFinish=false;
	private Date start;
	private Date finish;
	public GameState gamestate;
	
	public static Finder<Long, Game> find = new Finder<Long,Game>(
			Long.class, Game.class
	);
	
	public static Game findByUid(Long uid) {
		Game game = null;
		int i=1;
		do{
			game=find.where().eq("player"+i+"uid", uid ).eq("is_finish", false).findUnique();
			i++;
		}
		while(game == null && i < 5);
		
		return game;
	}
	
	public static Game Get(){
		Game game=Game.findByUid(Application.current_user().uid);
				
		if(game!=null){
			game.gamestate = GameState.Get(game.id);
			return game;
		}
		else{
			return Join_or_Create();
		}
	}
	
	private static Game Join_or_Create(){
		Game game=null;
		
		List<Game> games=find.where().eq("is_finish", false).findList();
		if(games.isEmpty()){
			game = new Game();
			game.set_player(Application.current_user().uid);
		}
		else{
			for(Game unfinish_game: games){
				if(unfinish_game.has_more_free_slots()){
					game=unfinish_game;
					
					 game.set_player(Application.current_user().uid);
					 break;
				}
			}
		}
		if(game==null){
			game = new Game();
			game.set_player(Application.current_user().uid);
		}
		
		game.gamestate = GameState.Get(game.id);
		return game;
	}
	
	public static Game Find(long game_id){
		Game game = Game.find.byId(game_id);
		game.gamestate = GameState.Get(game_id);
		return game;
	}
	
	private Boolean has_more_free_slots(){
		if(player1UID==null || player2UID==null || player3UID==null ||player4UID==null ){
			return true;
		}
		else return false;
	}
	
	public void start_current_game(){
		isStart = true;
		start = new Date();
		gamestate.isStart = true;
		
    	Notify.All("start", "game will begin in 5 seconds", gamestate.members);
    	
    	
 	
    	Cancellable timer = Akka.system().scheduler().scheduleOnce(
        		Duration.create(5, SECONDS),
        		GameRoom.gameRooms.get(this.id),
                new Start(this.id, gamestate),
                Akka.system().dispatcher()
        );
    	GameRoom.timers.put(this.id, timer);
    	save();
	}
	
	public void finish_current_game(){
		isFinish = true;
		finish = new Date();
		gamestate.isFinish = true;
		
    	    	
    	if(PokerAlgorithm.timers.containsKey(this.id)){
    		if(!PokerAlgorithm.timers.get(this.id).isCancelled()){
    			PokerAlgorithm.timers.get(this.id).cancel();
    		}
    	
        	PokerAlgorithm.timers.remove(this.id);
    	}
    	
    	if(GameRoom.timers.containsKey(this.id)){
    		if(!GameRoom.timers.get(this.id).isCancelled()){
    			GameRoom.timers.get(this.id).cancel();
    		}
    		GameRoom.timers.remove(this.id);
    	}
    	
    	Notify.All("finish", "Играта приключи!", gamestate.members);
    	
    	checkPointsOnExit(gamestate.members.get(0));
    	Notify.One("update_points", Integer.toString(gamestate.members.get(0).points), gamestate.members.get(0));
    	save();
	}
	
	private void set_player(long player_uid){
		if(this.player1UID==null){
			this.player1UID=player_uid;
		}
		else if(this.player2UID==null){
			this.player2UID=player_uid;
		}
		else if(this.player3UID==null){
			this.player3UID=player_uid;
		}
		else if(this.player4UID==null){
			this.player4UID=player_uid;
		}
		save();
	}
	
	public void remove_player(long player_uid){
		if(this.player1UID!= null && this.player1UID == player_uid){
			this.player1UID=null;
		}
		else if(this.player2UID!= null && this.player2UID == player_uid){
			this.player2UID=null;
		}
		else if(this.player3UID!= null && this.player3UID == player_uid){
			this.player3UID=null;
		}
		else if(this.player4UID!= null && this.player4UID == player_uid){
			this.player4UID=null;
		}
		save();
	}
	
	 public  void checkPointsOnExit(Member member){
		   if(gamestate.question != null && member.answer!=null && member.answer.equals(gamestate.question.getRight_answer() ) ){
	       	member.PlusPoints(gamestate.Bet);
	   	}
	       else if(gamestate.poker_algorithm || gamestate.answering){
	       
	       	member.PlusPoints(member.user_bet);
	       }
	   }
	    

	   
	   private  ArrayList<Member> GetWinners(ArrayList<Member> right_answered){
	    	ArrayList<Member> winners = new ArrayList<Member>();
	    	
	    	TreeSet<Long> times = new TreeSet<Long>();
	    	for(Member member: right_answered){
	    		times.add(member.time_on_answering);
	    	}
	    	
	    	Long min = times.first();
	    	
	    	for(Member member: right_answered){
	    		if(member.time_on_answering==min){
	    			winners.add(member);
	    		}
	    	}
	    	return winners;
	    }
	    
	    private  ArrayList<Member> GetRightAnswered(ArrayList<Member> answered){
	    	ArrayList<Member> right_answered= new ArrayList<Member>();
	    	
	    	for(Member member : answered){
	    		if(member.answer.equals(gamestate.question.getRight_answer())){
	    			right_answered.add(member);
	    		}
	    	}
	    	return right_answered;
	    }
	    
		private ArrayList<Member> GetAnswered (){
	    	ArrayList<Member> answered= new ArrayList<Member>();
	    	
	    	for(Member member:  gamestate.members){
	    		if(member.answer!=null){
	    			answered.add(member);
	    		}
	    	}
	    	return answered;
	    }
	    
	    public  void AskQuestion(){
	    	for(Member member : gamestate.members){
	    		member.answer = null;
	    		member.time_on_answering = 9999;
	    		member.button = null;
	    		member.usejoker = false;
	    	}
	    	
	    	gamestate.answering = true;
	    	
	    	//test*****************************************TEST
	    	if(gamestate.question==null){
	    		gamestate.question= Question.Get(gamestate.category);
	    	}
	    	else{
	    		Question question;
	    		do{
		    		question  = Question.Get(gamestate.category);
		    	}
		    	while(question.id.equals(gamestate.question.id));
		    	
		    	gamestate.question = question;
	    	}
	    	
	    	
	    	gamestate.time = new Date().getTime();
	    	if(gamestate.question.choice_answer1+gamestate.question.choice_answer2+gamestate.question.choice_answer3+gamestate.question.choice_answer4!=0){
	    		gamestate.hasVoiceJoker = true;
			}
	    	else gamestate.hasVoiceJoker = false;
	    	Notify.OnQuestion(gamestate);
	    	
	    	Cancellable timer = Akka.system().scheduler().scheduleOnce(
					Duration.create(15, SECONDS),
					GameRoom.gameRooms.get(id),
					new FinishAnswering(this),
					Akka.system().dispatcher()
			);
	    	GameRoom.timers.put(id, timer);
	    }
	
	
	   public void ShowWinner(){
	    	
        	ArrayList<Member> answered = GetAnswered();
        	Notify.All("mark_right_answer", gamestate.question.getRight_answer(), gamestate.members);
        	if(answered.isEmpty()){
        		
        		gamestate.unanswered_question++;
        		if(gamestate.unanswered_question<3){
        			Notify.All("message", "Няма отговорили", gamestate.members);
        			
            		Cancellable timer = Akka.system().scheduler().scheduleOnce(
            				Duration.create(2, SECONDS),
            				GameRoom.gameRooms.get(id),
            				//******************************************
            				new AskQuestion(this),
            				Akka.system().dispatcher()
            		);
            		GameRoom.timers.put(id, timer);
        		}
        		else{
        			
        			
        			finish_current_game();
        		}
        		
        	}
        	else{ 
        		ArrayList<Member> right_answered = GetRightAnswered(answered);
        		if(right_answered.isEmpty()){
        			Notify.All("message", "Няма познали!",  gamestate.members);
        			Cancellable timer = Akka.system().scheduler().scheduleOnce(
            				Duration.create(3, SECONDS),
            				GameRoom.gameRooms.get(id),
            				//******************************************
            				new AskQuestion(this),
            				Akka.system().dispatcher()            				
            		);
        			GameRoom.timers.put(id, timer);
        		}
        		else if(right_answered.size()==1){
        			Member member = right_answered.get(0);
        			member.PlusPoints(gamestate.Bet);
        			gamestate.Bet=0;
        			member.PlusMedalItem(gamestate.category);
        			String medal = member.isNewMedal(gamestate.category);
        			if(!medal.equals("no")){
        				Notify.OnMedal(medal, member);
        				if(medal.equals("bronze")){
        					member.PlusPoints(10);
        				}
        				else if(medal.equals("silver")){
        					member.PlusPoints(20);
        				}
        				else if(medal.equals("gold")){
        					member.PlusPoints(50);
        				}
        			}
        			Notify.OnWin(member.uid, member.name, member.points, gamestate.members);
        			Cancellable timer = Akka.system().scheduler().scheduleOnce(
            				Duration.create(3, SECONDS),
            				GameRoom.gameRooms.get(id),
            				new Start(id, gamestate),
            				Akka.system().dispatcher()
            		);
        			GameRoom.timers.put(id, timer);
        		}
        		else if(right_answered.size()>1){
        			for(Member member: right_answered){
        				member.PlusMedalItem(gamestate.category);
        				String medal = member.isNewMedal(gamestate.category);
            			if(!medal.equals("no")){
            				Notify.OnMedal(medal, member);
            				if(medal.equals("bronze")){
            					member.PlusPoints(10);
            				}
            				else if(medal.equals("silver")){
            					member.PlusPoints(20);
            				}
            				else if(medal.equals("gold")){
            					member.PlusPoints(50);
            				}
            			}
        			}
        			ArrayList<Member> winners = GetWinners(right_answered);
        			if(winners.size()==1){
        				Member member  = winners.get(0);
            			
            			member.PlusPoints(gamestate.Bet);
            			gamestate.Bet=0;
            			Notify.OnWin(member.uid, member.name, member.points, gamestate.members);
            			Cancellable timer = Akka.system().scheduler().scheduleOnce(
                				Duration.create(3, SECONDS),
                				GameRoom.gameRooms.get(id),
                				new Start(id, gamestate),
                				Akka.system().dispatcher()
                		);
            			GameRoom.timers.put(id, timer);
        			}
        			else if(winners.size()>1){
        				int bet = gamestate.Bet;
        				gamestate.Bet=0;
        				int size = winners.size();
        				int module = bet%size;
        				String string = "Победители са";
        				if(module!=0){
        					bet=bet+module;
        				}
    					for(Member member :winners){
    						
    						member.PlusPoints(bet/size);
    						string = string + " " + member.name;
    					}
    					string = string + " Те си разделят точките, поради еднакви времена за отговор!";
    					Notify.OnMoreWinners(string, gamestate, winners);
    					Cancellable timer = Akka.system().scheduler().scheduleOnce(
                				Duration.create(3, SECONDS),
                				GameRoom.gameRooms.get(id),
                				new Start(id, gamestate),
                				Akka.system().dispatcher()
                		);
            			GameRoom.timers.put(id, timer);
        			}
        		}
        		
        	}
        	int count_out_of_pints=0;
        	for (Member member :gamestate.members){
        		if(member.points<=0){
        			count_out_of_pints++;
        			member.out_of_points=true;
        			Notify.One("out_of_points", "Нямате достатъчно точки за да играете, но можете да наблюдавате хода на играта без да участвате. Можете да получите точки чрез добавяне на въпрос.", member);
        		}
        	}
        	if(count_out_of_pints>=gamestate.members.size()-1){
        		
        		finish_current_game();
        	}
	    }

	
}
