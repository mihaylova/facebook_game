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
import play.libs.Akka;
import scala.concurrent.Await;
import scala.concurrent.duration.Duration;
import play.api.libs.iteratee.*;
import play.api.libs.concurrent.*;


import akka.actor.*;

import scala.concurrent.duration.*;
import static play.libs.Akka.future;
import scala.concurrent.duration.FiniteDuration;




import org.codehaus.jackson.*;
import org.codehaus.jackson.node.*;

import static java.util.concurrent.TimeUnit.*;

import static akka.pattern.Patterns.ask;


import org.joda.time.Seconds;

import com.google.common.primitives.Longs;

import java.util.*;





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
                   GameState gamestate = game.gamestate;
                   Member member = Member.Find_by_uid(user.uid, gamestate.members);
                   if(gamestate.isStart && !gamestate.isFinish && !member.out_of_points && !member.fold && !member.wait){

            		   if(kind.equals("joker50")){
            			   if(member.answer==null && gamestate.answering && !member.usejoker && member.coins>=5){
            				   game_room.tell(new Joker50(member, gamestate.question), game_room);
            			   }
            		   }
            		   else if(kind.equals("joker_voice")){
            			   if(member.answer==null && gamestate.answering && !member.usejoker && gamestate.hasVoiceJoker && member.coins>=5){
            				   game_room.tell(new JokerVoice(member, gamestate.question), game_room);
            			   }
            		   }
            	   
            		   else if(kind.equals("raise")){
	                	   
	                	   if(gamestate.user_on_turn.uid==user.uid && gamestate.poker_algorithm  && gamestate.MaxBet>= event.get("bet").asInt()){
	                		   
	                		   game_room.tell(new Raise(event.get("bet").asInt(), member, game.id, gamestate), game_room);
	                	   }
	                	   
	                	   
	                   }
	                   else if(kind.equals("fold")){
	                	  
	                	   if(gamestate.user_on_turn.uid==user.uid && gamestate.poker_algorithm){
	                		   game_room.tell(new Fold( member, game.id, gamestate), game_room);
	                	   }
	                	   
	                	  
	                   }
	                   else if(kind.equals("call")){
	                	   
	                	   if(gamestate.user_on_turn.uid==user.uid && gamestate.poker_algorithm){
	                		   game_room.tell(new Call(member, game.id, gamestate), game_room);
	                	   }
	                	   
	                	   
	                   }
	                   else if(kind.equals("answer")){
	                	   
	                	   if(member.answer==null && gamestate.answering){
	                		   game_room.tell(new Answer(gamestate.question, member, game.id, event.get("answer").asText(), event.get("button").asText()), game_room);
	                	   }
	                	  
	                   }
                	   
                   }
            	   
            	   
               }
            });
            
            // When the socket is closed.
            in.onClose(new Callback0() {
               public void invoke() {
                   
                   // Send a Quit message to the room.
                   game_room.tell(new Quit(user, game), game_room);
                   
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
            	getSender().tell("You are already plaing", getSender());
            }
            
            else {
            	gamestate.addMember(join.user, join.channel);

                Notify.OnJoinOrQuit("join",  gamestate.members);
                
                if (gamestate.members.size() > 1) {
                	
                  // startGame(join.game.id);
                	join.game.start_current_game();
                	//join.game.start_current_game();
                }
               
                getSender().tell("OK", getSender());
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
            
            
            
            Notify.OnJoinOrQuit("quit", gamestate.members);
            if(gamestate.members.size() == 1){
            	
            	Game.Find(quit.game.id).finish_current_game();
            }
            else if(count_of_playing_members==1){
            	//*****************************************************************
            	Member member = Member.Find_unfold(gamestate.members);
            	quit.game.checkPointsOnExit(member);
            	gameRooms.get(quit.game.id).tell(new Start(quit.game.id, quit.game.gamestate), gameRooms.get(quit.game.id));
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
        	//*****************************************************************
        	show_winner.game.ShowWinner();
//        	
        }
        else if(message instanceof FinishAnswering) {
        	FinishAnswering finish_answering = (FinishAnswering) message;
        	
        	
        	
        	finish_answering.game.gamestate.answering= false;
        	Notify.OnFinishAnswering(finish_answering.game.gamestate.members);
        	Cancellable timer = Akka.system().scheduler().scheduleOnce(
    				Duration.create(3, SECONDS),
    				gameRooms.get(finish_answering.game.id),
    				//*****************************************************
    				new ShowWinner(finish_answering.game),
    				Akka.system().dispatcher()
    		);
			timers.put(finish_answering.game.id, timer);
        	
        	
        }
        else if(message instanceof Answer) {
        	Answer answer = (Answer) message;
        	Member member = answer.member;
        	member.answer = answer.answer;
        	member.time_on_answering = answer.time_on_answering;
        	member.button = answer.button;
        	Notify.One("answer", member.button, member);
        	answer.question.ChoiceAnswer(answer.answer);
        	
        }
        else if(message instanceof AskQuestion){
        	AskQuestion ask_question = (AskQuestion) message;
        	//*****************************************************************
        	ask_question.game.AskQuestion();
        }
        
        else if (message instanceof Joker50){
        	Joker50 joker = (Joker50) message;
        	//*****************************************************************
        	
        	joker.question.Joker_50(joker.member);
        }
        
        else if (message instanceof JokerVoice){
        	JokerVoice joker = (JokerVoice) message;
        	//*****************************************************************
        	
        	joker.question.Joker_Voice(joker.member);
        }
        
        	else {
            unhandled(message);
       }
        
    }
    

    

    
    public static class FinishAnswering{
    	final Game game;
    	
    	
    	public FinishAnswering(Game game){
    		this.game = game;
    	}
    }
    
    public static class ShowWinner{
    	final Game game;
    	
    	public ShowWinner(Game game){
    		this.game = game;
    		
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
        
        public Fold(Member member, Long game_id, GameState gamestate) {
        	this.member = member;
        	this.game_id = game_id;
        	this.gamestate = gamestate;
        	
        }
        
    }
    
    public static class Raise {
        final Member member;
        final Long game_id;
        final GameState gamestate;
    	
        
        final int bet;
        
        public Raise(int bet, Member member, Long game_id, GameState gamestate) {
        	this.member = member;
        	this.game_id = game_id;
            this.bet = bet;
            this.gamestate = gamestate;
           
    		
        }
        
    }
    
    public static class Call{
        final Member member;
        final Long game_id;
        final GameState gamestate;
        
        public Call(Member member, Long game_id, GameState gamestate) {
        	this.member = member;
        	this.game_id = game_id;
        	this.gamestate = gamestate;
        	
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
    	
    	
    	public Start(Long game_id, GameState gamestate) {
    		
    		this.game_id = game_id;
    		this.gamestate = gamestate;
    	}
    }
    
    public static class AskQuestion{
    	final Game game;
    	
    	public AskQuestion(Game game){
    		this.game = game;
    		
    	}
    }
    
  
    
    public static class Answer{
    	final Member member;
    	final Long game_id;
    	final String answer;
    	final long time_on_answering;
    	final String button;
    	final Question question;
    	
    	public Answer(Question question, Member member, Long game_id, String answer, String button){
    		this.member = member;
    		this.game_id = game_id;
    		this.answer = answer;
    		this.button = button;
    		GameState gamestate = GameState.Get(game_id);
    		this.time_on_answering = (new Date().getTime() - gamestate.time)/1000;
    		this.question = question;
    	}
    }
    
    public static class NextOnTurn{
    	final long game_id;
    	final GameState gamestate;
    	
    	public NextOnTurn(long game_id, GameState gamestate){
    		this.game_id = game_id;
    		this.gamestate = gamestate;
    		
    	}
    }
    
    public static class Joker50{
    	final Question question;
    	final Member member;
    	
    	 public Joker50(Member member, Question question){
    		 this.question = question;
    		 this.member = member;
    		
    		 
    	 }
    }
    
    public static class JokerVoice{
    	final Question question;
    	final Member member;
    	
    	
    	public JokerVoice(Member member, Question question){
    		this.member = member;
    		this.question = question;
    		
    		
    	}
    
    }

}
