package models;

import models.Game;
import models.Question;

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

import java.util.*;

import static java.util.concurrent.TimeUnit.*;

/**
 * A chat room is an Actor.
 */
public class GameRoom extends UntypedActor {
    
    // rooms
	static Map<Long, ActorRef> gameRooms = new HashMap<Long, ActorRef>();
  
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
                   
            	   
//                   if (kind.equals("message")) {
//            		   game_room.tell(new Talk(user.name, event.get("text").asText(), user.id, game.id));
//            	   }
                   
                   if(kind.equals("raise")){
                	   game_room.tell(new Raise(event.get("bet").asInt(), user.uid, game.id));
                   }
                   else if(kind.equals("fold")){
                	   game_room.tell(new Fold( user.uid, game.id));
                   }
                   else if(kind.equals("call")){
                	   game_room.tell(new Call(user.uid, game.id));
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
            
            ArrayList<Member> members = GameMembers.getMembers(join.game.id);
            Boolean play=false;
            for(Member member: members){
            	if(member.uid.equals(join.user.uid)){
            		play=true;
            	}
            }
                   
            
            if(play){
            	getSender().tell("You are already plaing");
            }
            
            else {
               GameMembers.addMember(join.user, join.game.id, join.channel);

                notifyOnJoinOrQuit("join",  GameMembers.getMembers(join.game.id));
                
                if (GameMembers.getMembers(join.game.id).size() > 1) {
                	
                   startGame(join.game.id);
                }
               
                getSender().tell("OK");
            }
            

           
        } else if(message instanceof Quit)  {
            
          
            Quit quit = (Quit)message;
            
            GameMembers.removeMember(quit.user.uid, quit.game.id);

            ArrayList<Member> members = GameMembers.getMembers(quit.game.id);

            notifyOnJoinOrQuit("quit", members);
            if(GameMembers.getMembers(quit.game.id).size() < 2){
            	quitGame(quit.game.id);
            }
//            else{
//            	notifyOnJoinOrQuit("quit", members);
//            }
        
     } 
        else if(message instanceof Start)  {
        	Start start = (Start)message;
        	
        	notifyAll("category", Integer.toString(Question.RandomCategory()), start.game_id);
        	PokerAlgorithm.onStart(start.game_id);
        	

        } 
        else if(message instanceof GameState){
        	GameState gamestate = (GameState)message;
        	//GameState.NextUser_on_turn(GameMembers.getMembers(gamestate.game_id));
        	notifyAll("user_on_turn", Long.toString(gamestate.user_on_turn.uid), gamestate.game_id);
        	gamestate.NextUser_on_turn(GameMembers.getMembers(gamestate.game_id));
        } 
        else if (message instanceof Raise) {
        	Raise raise = (Raise)message;
        	
        	//notifyAll("message", msg.text, msg.game_id);
        }
        else if (message instanceof Fold) {
        	Fold fold = (Fold)message;
        	
        	//notifyAll("message", msg.text, msg.game_id);
        }
        else if (message instanceof Call) {
        	Call call = (Call)message;
        	
        	//notifyAll("message", msg.text, msg.game_id);
        }else {
            unhandled(message);
       }
        
    }
    
    public void quitGame(Long game_id){
    	Game.find.byId(game_id).finish_current_game();
    	//izprashtane na izvestie za krai na igrata i (izhod na potrebitel ot members)js
    	GameMembers.gameMembers.remove(game_id);
		gameRooms.remove(game_id);
		//
    }
    
    public void startGame(Long game_id){
    	Game.find.byId(game_id).start_current_game();
    	notifyAll("start", "game will begin in 5 seconds", game_id);
    	
    	
        Akka.system().scheduler().scheduleOnce(
        		Duration.create(5, SECONDS),
        		gameRooms.get(game_id),
                new Start(game_id)
        );
    }
    
   
 
     public void notifyOnJoinOrQuit(String kind, ArrayList<Member> members) {
        ObjectNode event = Json.newObject();
        event.put("kind", kind);
       
        ArrayNode m = event.putArray("members");
        for(Member member: members){
        	
        	Fb_user user = Fb_user.find_by_uid(member.uid);
        	ObjectNode user_data = Json.newObject();
        	user_data.put("uid", user.uid.toString());
        	user_data.put("name", user.name);
        	user_data.put("picture", user.picture);
        	user_data.put("slot", Integer.toString(member.slot));
        	m.add(user_data);
        }

        for(Member member: members){
        	
			WebSocket.Out<JsonNode> channel = member.channel;
			channel.write(event);
        }
        
    }
    
    // Send a Json event to all members
    public void notifyAll(String kind, String text, Long game_id) {
        ObjectNode event = Json.newObject();
        event.put("kind", kind);
        event.put("message", text);
 
        ArrayList<Member> members = GameMembers.getMembers(game_id);
       
        for(Member member: members){
        	WebSocket.Out<JsonNode> channel =member.channel;
			channel.write(event);
        }

    }
    
    // -- Messages
    
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
        final Long user_id;
        final Long game_id;
        
        
        public Fold(Long user_id, Long game_id) {
        	this.user_id = user_id;
        	this.game_id = game_id;
        }
        
    }
    
    public static class Raise {
        final Long user_id;
        final Long game_id;
        
        final int bet;
        
        public Raise(int bet, Long user_id, Long game_id) {
        	this.user_id = user_id;
        	this.game_id = game_id;
            this.bet = bet;
        }
        
    }
    
    public static class Call{
        final Long user_id;
        final Long game_id;
        
        public Call(Long user_id, Long game_id) {
        	this.user_id = user_id;
        	this.game_id = game_id;
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
    	
    	public Start(Long game_id) {
    		
    		this.game_id = game_id;
    	}
    }
    
    public static class GameMembers{
    	public static Map<Long, ArrayList<Member>> gameMembers = new HashMap<Long, ArrayList<Member>>();
      
    	public static void addMember(Fb_user user, Long game_id, WebSocket.Out<JsonNode> channel) {
    		ArrayList<Member> members =  getMembers(game_id);
    		//I'll NOT kill myself
    		Member member=new Member(user.uid, findFreeSlot(members), channel);
    		
    		
    		members.add(member);
    	}
    	
        public static ArrayList<Member> getMembers(Long game_id) {
        	
        	if (gameMembers.containsKey(game_id)) {
        		
        		return gameMembers.get(game_id);
        	} else {
        		ArrayList<Member> members = new ArrayList<Member>();

        		gameMembers.put(game_id, members);
        		
        		return members;
        	}
        }
        
        public  static void removeMember(Long user_uid, Long game_id) {
        	
        	ArrayList<Member> members =  getMembers(game_id);
        	
        	for(int i = 0; members.size() >= i; i++) {
        		Member member =  members.get(i);
        		
        		if (member.uid == user_uid) {

        			members.remove(i);
        			Game.find.byId(game_id).remove_player(user_uid);
        			
        			break;
        		}
        	}
        	
//        	if (members.isEmpty()) {
//        		gameMembers.remove(game_id);
//        		
//        		gameRooms.remove(game_id);
//        	}
        	
        }

        protected static int findFreeSlot(ArrayList<Member> members) {
        	for(int i=1; i <= 2; i++){
        		Boolean is_free = true;
        		for(Member member: members){
                	
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
    }
    
    public static class Member{
    	public final Long uid;
    	public final int slot;
    	public final WebSocket.Out<JsonNode> channel;
    	public int UnCallBet=0;
    	
    	 public Member(Long uid, int slot, WebSocket.Out<JsonNode> channel) {
             this.uid = uid;
             this.slot = slot;
             this.channel=channel;
         }
    	 
    	 public static Member Find_by_uid(Long uid, ArrayList<Member> members){
    		 for(Member member: members){
    			 if(member.uid==uid){
    				 return member;
    			 }
    		 }
    		 return null;
    	 }
    }
    
    public static class GameState{
    	public static Map<Long, GameState> states;
    	public Member user_on_turn;
    	public Long  game_id;
    	public int Bet=0;
    	
    	public static GameState Get(Long game_id){
    		if(states.containsKey(game_id)){
    			return states.get(game_id);
    		}
    		else{
    			GameState gamestate = new GameState();
    			ArrayList<Member> members = GameMembers.getMembers(game_id);
        		gamestate.user_on_turn=members.get(0);
        		gamestate.game_id=game_id;
        		return gamestate;
    		}
    	}
    	
    	public  void NextUser_on_turn(ArrayList<Member> members){
    		int i = members.indexOf(user_on_turn);
    		if(i<(members.size()-1)){
    			user_on_turn=members.get(i+1);
    		}
    		else{
    			user_on_turn=members.get(0);
    		}
    	}
    	public static void Delete(Long game_id){//Pri zatvzariane na staiata
    		states.remove(game_id);
    	}
    }
    public static class PokerAlgorithm{
    	private static Cancellable cancellable;
    	public static void onStart(Long game_id){
    		GameState gamestate=GameState.Get(game_id);
    		cancellable = Akka.system().scheduler().schedule(
             		Duration.Zero(),
             		
             		Duration.create(10, SECONDS),
                    
                     gameRooms.get(game_id),
                    
                     
                    gamestate
             );
    	}
    	public static void onRaise(Long game_id, Long user_uid, int bet){
    		GameState gamestate=GameState.Get(game_id);
    		ArrayList<Member> members = GameMembers.getMembers(game_id);
    		cancellable.cancel();
    		Fb_user user = Fb_user.find_by_uid(user_uid);
    		Member member = Member.Find_by_uid(user_uid, members);
    		gamestate.Bet=gamestate.Bet+bet;
    		for(Member othermember: members){
    			if(othermember.uid!=user_uid){
    				othermember.UnCallBet=othermember.UnCallBet+(bet-member.UnCallBet);
    			}
    		}
    		member.UnCallBet=0;
    		user.MinusPoints(bet);
    		cancellable = Akka.system().scheduler().schedule(
             		Duration.Zero(),
             		
             		Duration.create(10, SECONDS),
                    
                     gameRooms.get(game_id),
                    
                     
                    gamestate
             );    
    		
    	}
    	
    	public  static void onFold(Long game_id, Long user_uid){
    		GameState gamestate=GameState.Get(game_id);
    		ArrayList<Member> members = GameMembers.getMembers(game_id);
    		cancellable.cancel();
    		Fb_user user = Fb_user.find_by_uid(user_uid);
    		Member member = Member.Find_by_uid(user_uid, members);
    		
    		member.UnCallBet=0;
    		//da ne e pri druga vratka
    		cancellable = Akka.system().scheduler().schedule(
             		Duration.Zero(),
             		
             		Duration.create(10, SECONDS),
                    
                     gameRooms.get(game_id),
                    
                     
                    gamestate
             );    
    		
    	}
    	
    	public static void onCall(Long game_id, Long user_uid){
    		GameState gamestate=GameState.Get(game_id);
    		ArrayList<Member> members = GameMembers.getMembers(game_id);
    		cancellable.cancel();
    		Fb_user user = Fb_user.find_by_uid(user_uid);
    		Member member = Member.Find_by_uid(user_uid, members);
    		gamestate.Bet=gamestate.Bet+member.UnCallBet;
    		user.MinusPoints(member.UnCallBet);
    		member.UnCallBet=0;
    		cancellable = Akka.system().scheduler().schedule(
             		Duration.Zero(),
             		
             		Duration.create(10, SECONDS),
                    
                     gameRooms.get(game_id),
                    
                     
                    gamestate
             );    
    	}
    	
    	
        
    }
    
}
