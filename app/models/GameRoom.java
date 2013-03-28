package models;

import models.Game;
import models.Question;
import Algorithms.PokerAlgorithm;
import Algorithms.Notify;
import ForGameRoom.Member;
import ForGameRoom.GameState;

import play.mvc.*;
import play.mvc.WebSocket.Out;
import play.libs.*;
import play.libs.F.*;
import scala.Int;

import akka.util.*;
import akka.actor.*;
import akka.dispatch.*;
import static akka.pattern.Patterns.ask;

import org.codehaus.jackson.*;
import org.codehaus.jackson.node.*;
import org.joda.time.Seconds;

import com.google.common.primitives.Longs;

import java.util.*;

import static java.util.concurrent.TimeUnit.*;



public class GameRoom extends UntypedActor {
    
    // rooms
	public static Map<Long, ActorRef> gameRooms = new HashMap<Long, ActorRef>();
	public static Map<Long, Cancellable> timers= new HashMap<Long, Cancellable>();
  
    public static void join(final Fb_user user, final Game game, WebSocket.In<JsonNode> in, WebSocket.Out<JsonNode> out) throws Exception{

    	final ActorRef game_room;
    	if (gameRooms.containsKey(game.id)) {
    		game_room = gameRooms.get(game.id);
    	} else {
    		game_room = Akka.system().actorOf(new Props(GameRoom.class));
    		
    		gameRooms.put(game.id, game_room);
    	}
        
        // Send the Join message to the room
        String result = (String)Await.result(ask(game_room,new Join(user, game, out), 1000), Duration.create(1, SECONDS));
        
        if("OK".equals(result)) {
            
            // For each event received on the socket,
            in.onMessage(new Callback<JsonNode>() {
               public void invoke(JsonNode event) {
                   String kind = event.get("kind").asText();
                   GameState gamestate = GameState.Get(game.id);
                   Member member = Member.Find_by_uid(user.uid, gamestate.members);
                   
            	   if(member.out_of_points || gamestate.finish || member.fold || member.wait){
            		   //Do nothing
            	   }
            	   else{
            		   if(kind.equals("joker50")){
            			   game_room.tell(new Joker50(member, game.id));
            			  
            		   }
            	   
            		   else if(kind.equals("raise")){
	                	   
	                	   if(gamestate.user_on_turn.uid!=user.uid || !gamestate.poker_algorithm  || gamestate.MaxBet< event.get("bet").asInt()){
	                		   //user can't raise
	                	   }
	                	   
	                	   else game_room.tell(new Raise(event.get("bet").asInt(), member, game.id));
	                   }
	                   else if(kind.equals("fold")){
	                	  
	                	   if(gamestate.user_on_turn.uid!=user.uid || !gamestate.poker_algorithm){
	                		   //user can't fold
	                	   }
	                	   
	                	   else game_room.tell(new Fold( member, game.id));
	                   }
	                   else if(kind.equals("call")){
	                	   
	                	   if(gamestate.user_on_turn.uid!=user.uid || !gamestate.poker_algorithm){
	                		   //user can't call
	                	   }
	                	   
	                	   else game_room.tell(new Call(member, game.id));
	                   }
	                   else if(kind.equals("answer")){
	                	   
	                	   if(member.answer!=null || !gamestate.answering){
	                		   //user can't answering the question
	                	   }
	                	   else game_room.tell(new Answer(member, game.id, event.get("answer").asText(), event.get("button").asText()));
	                   }
            	   }
               } 
            });
            
            // When the socket is closed.
            in.onClose(new Callback0() {
               public void invoke() {
                   
                   // Send a Quit message to the room.
                   game_room.tell(new Quit(user, game));
                   
               }
            });
            
        } else {
            
            // Cannot connect, create a Json error.
            ObjectNode error = Json.newObject();
            error.put("error", result);
            
            // Send the error to the socket.
            out.write(error);
            
        }
        
    }
    
    public void onReceive(Object message) throws Exception {
        
        if(message instanceof Join) {
            Join join = (Join)message;
            GameState gamestate = GameState.Get(join.game.id);
            Boolean play=false;
            for(Member member: gamestate.members){
            	if(member.uid.equals(join.user.uid)){
            		play=true;
            	}
            }
                   
            
            if(play){
            	getSender().tell("You are already plaing");
            }
            
            else {
            	gamestate.addMember(join.user, join.channel);

                Notify.OnJoinOrQuit("join",  gamestate.members);
                
                if (gamestate.members.size() > 1) {
                	
                   startGame(join.game.id);
                }
               
                getSender().tell("OK");
            }
            

           
        } else if(message instanceof Quit)  {
            Quit quit = (Quit)message;
            GameState gamestate = GameState.Get(quit.game.id);
            gamestate.removeMember(quit.user.uid);
            
            int count_of_playing_members=0;
            for(Member member :gamestate.members){
            	if(!member.fold && !member.out_of_points && !member.wait){
            		count_of_playing_members++;
            	}
            }
            
            if(count_of_playing_members==1 &&  gamestate.question != null && Member.Find_unfold(gamestate.members).answer!=null && Member.Find_unfold(gamestate.members).answer.equals(gamestate.question.getRight_answer() ) ){
            	Member.Find_unfold(gamestate.members).PlusPoints(gamestate.Bet);
        	}
            else if(count_of_playing_members==1 && (gamestate.poker_algorithm || gamestate.answering)){
            	Member member = Member.Find_unfold(gamestate.members);
            	member.PlusPoints(member.user_bet);
            }
            Notify.OnJoinOrQuit("quit", gamestate.members);
            if(gamestate.members.size() == 1){
            	quitGame(quit.game.id); 
            }
        } 
        else if(message instanceof Start)  {
        	Start start = (Start)message;
        	PokerAlgorithm.onStart(gameRooms.get(start.game_id), start.gamestate);
        } 
        else if(message instanceof NextOnTurn){
        	NextOnTurn next_on_turn = (NextOnTurn)message;
        	Notify.OnNextTurn(next_on_turn.gamestate.user_on_turn, next_on_turn.gamestate);
        	
        } 
        else if (message instanceof Raise) {
        	Raise raise = (Raise)message;
        	PokerAlgorithm.onRaise(gameRooms.get(raise.game_id), raise.member, raise.bet, raise.gamestate);
         }
        else if (message instanceof Fold) {
        	Fold fold = (Fold)message;
        	PokerAlgorithm.onFold(gameRooms.get(fold.game_id), fold.member, fold.gamestate);
         }
        else if (message instanceof Call) {
        	Call call = (Call)message;
        	PokerAlgorithm.onCall(gameRooms.get(call.game_id), call.member, call.gamestate);
        }
        else if(message instanceof ShowWinner){
        	ShowWinner show_winner = (ShowWinner) message;
        	GameState gamestate = show_winner.gamestate;
        	ArrayList<Member> answered = GetAnswered(gamestate);
        	Notify.All("mark_right_answer", gamestate.question.getRight_answer(), gamestate.members);
        	if(answered.isEmpty()){
        		
        		gamestate.unanswered_question++;
        		if(gamestate.unanswered_question<3){
        			Notify.All("message", "Няма отговорили", gamestate.members);
            		
            		Cancellable timer = Akka.system().scheduler().scheduleOnce(
            				Duration.create(2, SECONDS),
            				gameRooms.get(show_winner.game_id),
            				new AskQuestion(show_winner.game_id)
            		);
            		timers.put(show_winner.game_id, timer);
        		}
        		else{
        			
        			quitGame(show_winner.game_id);
        		}
        		
        	}
        	else{ 
        		ArrayList<Member> right_answered = GetRightAnswered(answered, gamestate);
        		if(right_answered.isEmpty()){
        			Notify.All("message", "Няма познали!",  gamestate.members);
        			Cancellable timer = Akka.system().scheduler().scheduleOnce(
            				Duration.create(3, SECONDS),
            				gameRooms.get(show_winner.game_id),
            				new AskQuestion(show_winner.game_id)
            		);
        			timers.put(show_winner.game_id, timer);
        		}
        		else if(right_answered.size()==1){
        			Member member = right_answered.get(0);
        			member.PlusPoints(gamestate.Bet);
        			gamestate.Bet=0;
        			
        			
        			Notify.OnWin(member.uid, member.name, member.points, gamestate.members);
        			Cancellable timer = Akka.system().scheduler().scheduleOnce(
            				Duration.create(3, SECONDS),
            				gameRooms.get(show_winner.game_id),
            				new Start(show_winner.game_id)
            		);
        			timers.put(show_winner.game_id, timer);
        		}
        		else if(right_answered.size()>1){
        			ArrayList<Member> winners = GetWinners(right_answered);
        			if(winners.size()==1){
        				Member member  = winners.get(0);
            			
            			member.PlusPoints(gamestate.Bet);
            			gamestate.Bet=0;
            			Notify.OnWin(member.uid, member.name, member.points, gamestate.members);
            			Cancellable timer = Akka.system().scheduler().scheduleOnce(
                				Duration.create(3, SECONDS),
                				gameRooms.get(show_winner.game_id),
                				new Start(show_winner.game_id)
                		);
            			timers.put(show_winner.game_id, timer);
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
                				gameRooms.get(show_winner.game_id),
                				new Start(show_winner.game_id)
                		);
            			timers.put(show_winner.game_id, timer);
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
        		quitGame(gamestate.game_id);
        	}
        }
        else if(message instanceof FinishAnswering) {
        	FinishAnswering finish_answering = (FinishAnswering) message;
        	GameState gamestate = finish_answering.gamestate;
        	
        	
        	gamestate.answering= false;
        	Notify.OnFinishAnswering(gamestate.members);
        	Cancellable timer = Akka.system().scheduler().scheduleOnce(
    				Duration.create(3, SECONDS),
    				gameRooms.get(finish_answering.game_id),
    				new ShowWinner(finish_answering.game_id)
    		);
			timers.put(finish_answering.game_id, timer);
        	
        	
        }
        else if(message instanceof Answer) {
        	Answer answer = (Answer) message;
        	Member member = answer.member;
        	member.answer = answer.answer;
        	member.time_on_answering = answer.time_on_answering;
        	member.button = answer.button;
        	Notify.One("answer", member.button, member);
        	
        }
        else if(message instanceof AskQuestion){
        	AskQuestion askquestion = (AskQuestion) message;
        	AskQuestion(askquestion.gamestate);
        }
        
        else if (message instanceof Joker50){
        	Joker50 joker = (Joker50) message;
        	Joker_50(joker.gamestate.question.answers, joker.gamestate.question.getRight_answer(), joker.member);
        }
        
        	else {
            unhandled(message);
       }
        
    }
    
   private static void Joker_50(ArrayList<String> answers, String right_answer, Member member){
	   
	   int count=0;
	   do{
		   int random = (int)Math.random()*4;
		   if(answers.get(random)!= null && answers.get(random)!= right_answer){
			   String answer =  answers.get(random);
				  answer=null;
				  answers.remove(random);
				  answers.add(random, answer);
				  count++;
	   }
		  
		 		   
	   } while(count<2);
	   
	   Notify.OnJoker_50(member, answers);
   }
    
    private  static ArrayList<Member> GetWinners(ArrayList<Member> right_answered){
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
    
    private static  ArrayList<Member> GetRightAnswered(ArrayList<Member> answered, GameState gamestate){
    	ArrayList<Member> right_answered= new ArrayList<Member>();
    	
    	for(Member member : answered){
    		if(member.answer.equals(gamestate.question.getRight_answer())){
    			right_answered.add(member);
    		}
    	}
    	return right_answered;
    }
    
	private static ArrayList<Member> GetAnswered (GameState gamestate){
    	ArrayList<Member> answered= new ArrayList<Member>();
    	
    	for(Member member:  gamestate.members){
    		if(member.answer!=null){
    			answered.add(member);
    		}
    	}
    	return answered;
    }
    
    public  static void AskQuestion(GameState gamestate){
    	for(Member member : gamestate.members){
    		member.answer = null;
    		member.time_on_answering = 9999;
    		member.button = null;
    	}
    	
    	gamestate.answering = true;
    	gamestate.question = Question.Get(gamestate.category);
    	gamestate.time = new Date().getTime();
    	Notify.OnQuestion(gamestate);
    	
    	Cancellable timer = Akka.system().scheduler().scheduleOnce(
				Duration.create(15, SECONDS),
				gameRooms.get(gamestate.game_id),
				new FinishAnswering(gamestate.game_id)
		);
    	timers.put(gamestate.game_id, timer);
    }
    
    public void quitGame(Long game_id){
    	GameState gamestate = GameState.Get(game_id);
    	gamestate.finish=true;
    	Game.find.byId(game_id).finish_current_game();
    	
    	if(PokerAlgorithm.timers.containsKey(game_id)){
    		if(!PokerAlgorithm.timers.get(game_id).isCancelled()){
    			PokerAlgorithm.timers.get(game_id).cancel();
    		}
    	
        	PokerAlgorithm.timers.remove(game_id);
    	}
    	
    	if(timers.containsKey(game_id)){
    		if(!timers.get(game_id).isCancelled()){
    			timers.get(game_id).cancel();
    		}
    		timers.remove(game_id);
    	}
    	
    	Notify.All("finish", "Играта приключи!", gamestate.members);
    	
		
    }
    
    public void startGame(Long game_id){
    	Game.find.byId(game_id).start_current_game();
    	GameState gamestate = GameState.Get(game_id);
    	Notify.All("start", "game will begin in 5 seconds", gamestate.members);
    	
    	
    	Cancellable timer = Akka.system().scheduler().scheduleOnce(
        		Duration.create(5, SECONDS),
        		gameRooms.get(game_id),
                new Start(game_id)
        );
    	timers.put(gamestate.game_id, timer);
    }
    
    public static class FinishAnswering{
    	final Long game_id;
    	final GameState gamestate;
    	
    	public FinishAnswering(Long game_id){
    		this.game_id = game_id;
    		this.gamestate = GameState.Get(game_id);
    	}
    }
    
    public static class ShowWinner{
    	final Long game_id;
    	final GameState gamestate;
    	
    	public ShowWinner(Long game_id){
    		this.game_id = game_id;
    		this.gamestate = GameState.Get(game_id);
    	}
    }
    
    public static class Join {
        
        final Fb_user user;
        final Game game;
        final WebSocket.Out<JsonNode> channel;
        
        public Join(Fb_user user, Game game, WebSocket.Out<JsonNode> channel) {
            this.user = user;
            this.game = game;
            this.channel = channel;
        }
        
    }
    
    public static class Fold {
        final Member member;
        final Long game_id;
        final GameState gamestate;
        
        public Fold(Member member, Long game_id) {
        	this.member = member;
        	this.game_id = game_id;
        	this.gamestate = GameState.Get(game_id);
        }
        
    }
    
    public static class Raise {
        final Member member;
        final Long game_id;
        final GameState gamestate;
    	
        
        final int bet;
        
        public Raise(int bet, Member member, Long game_id) {
        	this.member = member;
        	this.game_id = game_id;
            this.bet = bet;
            this.gamestate = GameState.Get(game_id);
    		
        }
        
    }
    
    public static class Call{
        final Member member;
        final Long game_id;
        final GameState gamestate;
        
        public Call(Member member, Long game_id) {
        	this.member = member;
        	this.game_id = game_id;
        	this.gamestate = GameState.Get(game_id);
        }
        
    }
    
    public static class Quit {
        
        final Fb_user user;
        final Game game;
        
        public Quit(Fb_user user, Game game) {
            this.user = user;
            this.game = game;
        }
        
    }
    
    public static class Start {
    	
    	final Long game_id;
    	final GameState gamestate;
    	
    	
    	public Start(Long game_id) {
    		this.gamestate = GameState.Get(game_id);
    		this.game_id = game_id;
    	}
    }
    
    public static class AskQuestion{
    	final long game_id;
    	final GameState gamestate;
    	public AskQuestion(long game_id){
    		this.game_id = game_id;
    		this.gamestate = GameState.Get(game_id);
    	}
    }
    
  
    
    public static class Answer{
    	final Member member;
    	final Long game_id;
    	final String answer;
    	final long time_on_answering;
    	final String button;
    	
    	public Answer(Member member, Long game_id, String answer, String button){
    		this.member = member;
    		this.game_id = game_id;
    		this.answer = answer;
    		this.button = button;
    		GameState gamestate = GameState.Get(game_id);
    		this.time_on_answering = (new Date().getTime() - gamestate.time)/1000;
    	}
    }
    
    public static class NextOnTurn{
    	final long game_id;
    	final GameState gamestate;
    	public NextOnTurn(long game_id){
    		this.game_id = game_id;
    		this.gamestate = GameState.Get(game_id);
    	}
    }
    
    public static class Joker50{
    	final  long game_id;
    	final Member member;
    	final GameState gamestate;
    	 public Joker50(Member member, long game_id){
    		 this.game_id = game_id;
    		 this.member = member;
    		 this.gamestate = GameState.Get(game_id);
    	 }
    }
    
    
    
}
