package models;

import models.Game;
import models.Question;
import Algorithms.PokerAlgorithm;
import Algorithms.Notify;

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
            		   
            	   
	                   if(kind.equals("raise")){
	                	   
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
	                	   else game_room.tell(new Answer(member, game.id, event.get("answer").asText(), event.get("button").asInt()));
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

            

            Notify.OnJoinOrQuit("quit", gamestate.members);
            if(gamestate.members.size() < 2){
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
        else if(message instanceof FinishAnswering) {
        	FinishAnswering finish_answering = (FinishAnswering) message;
        	GameState gamestate = finish_answering.gamestate;
        	ArrayList<Member> answered = GetAnswered(gamestate);
        	
        	gamestate.answering= false;
        	Notify.All("finish_answering", gamestate.question.getRight_answer(), gamestate.members);
        	if(answered.isEmpty()){
        		
        		gamestate.unanswered_question++;
        		if(gamestate.unanswered_question<3){
        			Notify.All("message", "Няма отговорили", gamestate.members);
            		
            		Cancellable timer = Akka.system().scheduler().scheduleOnce(
            				Duration.create(5, SECONDS),
            				gameRooms.get(finish_answering.game_id),
            				new AskQuestion(finish_answering.game_id)
            		);
            		timers.put(finish_answering.game_id, timer);
        		}
        		else{
        			Notify.All("message", "Вече три пъти не отговаряте! Какво ви има?! :)))))", gamestate.members);
        			quitGame(finish_answering.game_id);
        		}
        		
        	}
        	else{ 
        		ArrayList<Member> right_answered = GetRightAnswered(answered, gamestate);
        		if(right_answered.isEmpty()){
        			Notify.OnNoRightAnswer(answered, gamestate);
        			Cancellable timer = Akka.system().scheduler().scheduleOnce(
            				Duration.create(10, SECONDS),
            				gameRooms.get(finish_answering.game_id),
            				new AskQuestion(finish_answering.game_id)
            		);
        			timers.put(finish_answering.game_id, timer);
        		}
        		else if(right_answered.size()==1){
        			Member member = right_answered.get(0);
        			member.PlusPoints(gamestate.Bet);
        			
        			
        			Notify.OnWin(member.uid, member.name, member.points, gamestate.members);
        			Cancellable timer = Akka.system().scheduler().scheduleOnce(
            				Duration.create(10, SECONDS),
            				gameRooms.get(finish_answering.game_id),
            				new Start(finish_answering.game_id)
            		);
        			timers.put(finish_answering.game_id, timer);
        		}
        		else if(right_answered.size()>1){
        			ArrayList<Member> winners = GetWinners(right_answered);
        			if(winners.size()==1){
        				Member member  = winners.get(0);
            			
            			member.PlusPoints(gamestate.Bet);
            			Notify.OnWin(member.uid, member.name, member.points, gamestate.members);
            			Cancellable timer = Akka.system().scheduler().scheduleOnce(
                				Duration.create(10, SECONDS),
                				gameRooms.get(finish_answering.game_id),
                				new Start(finish_answering.game_id)
                		);
            			timers.put(finish_answering.game_id, timer);
        			}
        			else if(winners.size()>1){
        				int bet = gamestate.Bet;
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
        			}
        		}
        		
        	}
        	for (Member member :gamestate.members){
        		if(member.points<=0){
        			member.out_of_points=true;
        			//Notify.One - kazvame na usera che niama to4ki i mu predlagame da zadade vapros 
        		}
        	}
        }
        else if(message instanceof Answer) {
        	Answer answer = (Answer) message;
        	Member member = answer.member;
        	member.answer = answer.answer;
        	member.time_on_answering = answer.time_on_answering;
        	member.button = answer.button;
        	Notify.OneOnAnswer(member);
        	
        }
        else if(message instanceof AskQuestion){
        	AskQuestion askquestion = (AskQuestion) message;
        	AskQuestion(askquestion.gamestate);
        }
        	else {
            unhandled(message);
       }
        
    }
    
    public static ArrayList<Member> GetWinners(ArrayList<Member> right_answered){
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
    
    public static ArrayList<Member> GetRightAnswered(ArrayList<Member> answered, GameState gamestate){
    	ArrayList<Member> right_answered= new ArrayList<Member>();
    	
    	for(Member member : answered){
    		if(member.answer.equals(gamestate.question.getRight_answer())){
    			right_answered.add(member);
    		}
    	}
    	return right_answered;
    }
    
	public static ArrayList<Member> GetAnswered (GameState gamestate){
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
    		member.button = 0;
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
    	if(gamestate.members.size()==1 && gamestate.Bet>0){
    		gamestate.members.get(0).PlusPoints(gamestate.Bet);
    	}
    	Notify.All("finish", "Играта приключи!", gamestate.members); //in js
    	
		
    }
    
    public void startGame(Long game_id){
    	Game.find.byId(game_id).start_current_game();
    	GameState gamestate = GameState.Get(game_id);
    	Notify.All("start", "game will begin in 5 seconds", gamestate.members);
    	
    	
        Akka.system().scheduler().scheduleOnce(
        		Duration.create(5, SECONDS),
        		gameRooms.get(game_id),
                new Start(game_id)
        );
    }
    
    public static class FinishAnswering{
    	final Long game_id;
    	final GameState gamestate;
    	
    	public FinishAnswering(Long game_id){
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
    	final int button;
    	
    	public Answer(Member member, Long game_id, String answer, int button){
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
    
    public static class Member{
	
		public final Long uid;
		public final int slot;
		public final WebSocket.Out<JsonNode> channel;
		public int UnCallBet=0;
		public boolean fold=false;
		public boolean wait = true;
		public int turn=0;
		public String answer = null;
		public long time_on_answering=9999;
		public int button = 0;
		public int points;
		public String name;
		public boolean out_of_points = false;
		
		
		 public Member(Long uid, int slot, WebSocket.Out<JsonNode> channel, int points, String name) {
	         this.uid = uid;
	         this.slot = slot;
	         this.channel=channel;
	         this.points = points;
	         this.name = name;
	     }
		 
		 public static Member Find_by_uid(Long uid, ArrayList<Member> members){
			 for(Member member: members){
				 if(member.uid.equals(uid)){
					 return member;
	   			 }
			 }
			 return null;
		 }
		 
		 public static Member Find_unfold(ArrayList<Member> members){
			 for(Member member: members){
				 if(!member.fold || !member.wait || !member.out_of_points){
					 return member;
				 }
			 }
			 return null;
		 }
		 
		 public void PlusPoints(int count){
			 this.points= this.points + count;
		 }
		 
		 public void MinusPoints(int count){
			 this.points = this.points - count;
		 }
    }
    
    public static class GameState{
    	public static Map<Long, GameState> states= new HashMap<Long, GameState>();
    	public Member user_on_turn;
    	public Long  game_id;
    	public int Bet=0;
    	public int MaxBet=9999;
    	public int category;
    	public Question question;
    	public long time;
    	public int unanswered_question = 0;
    	public boolean poker_algorithm=false;
    	public boolean answering=false;
    	public ArrayList<Member> members;
    	public boolean finish = false;
    	
    	public  void addMember(Fb_user user, WebSocket.Out<JsonNode> channel) {
    		Member member=new Member(user.uid, findFreeSlot(), channel, user.points, user.name);
	    	this.members.add(member);
    	}
    	
    	public  void removeMember(Long user_uid) { 
        	for(Member member : this.members){
        		if(member.uid == user_uid){
        			Fb_user user = Fb_user.find_by_uid(member.uid);
        			user.SetPoints(member.points);
        			
        			this.members.remove(member);
        			Game.find.byId(this.game_id).remove_player(user_uid);
        			break;
        		}
        	}
        	if (members.isEmpty()) {
        		gameRooms.remove(game_id);
	    		GameState.Delete(game_id);
        	}
        }

        protected  int findFreeSlot() {
        	for(int i=1; i <= 2; i++){
        		Boolean is_free = true;
        		for(Member member: this.members){
                	
                	if(member.slot == i){
                		is_free = false;
                	}	
                }
        		
        		if (is_free) {
        			return i;
        		}
        	}
        	
			throw new ArrayIndexOutOfBoundsException();
        }
    	
    	public void SetMaxBet(){
    		for(Member member: this.members){
    			this.MaxBet=Math.min(this.MaxBet, member.points);
    		}
    	}
    	
    	public static GameState Get(Long game_id){
    		if(states.containsKey(game_id)){
    			return states.get(game_id);
    			
    		}
    		else{
    			GameState gamestate = new GameState();
    			gamestate.members = new ArrayList<Member>();
        		gamestate.game_id=game_id;
        		states.put(game_id, gamestate);
        		return gamestate;
    		}
    	}

    	public  void NextUser_on_turn(){
    		int i = this.members.indexOf(this.user_on_turn);
    		if(i<(members.size()-1)){
    			this.user_on_turn=this.members.get(i+1);
    		}
    		else{
    			this.user_on_turn=this.members.get(0);
    		}
    		
    		if(user_on_turn.fold || user_on_turn.wait || user_on_turn.out_of_points){
    			this.NextUser_on_turn();
    		}

    	}
    	
    	public static void Delete(Long game_id){
    		states.remove(game_id);
    	}
    }

    
}
