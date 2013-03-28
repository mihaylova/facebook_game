package Algorithms;

import java.util.ArrayList;

import models.Fb_user;
import ForGameRoom.GameState;
import ForGameRoom.Member;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

import play.libs.Json;
import play.mvc.WebSocket;

public class Notify {
	
		public static void OnJoinOrQuit(String kind, ArrayList<Member> members) {
	        ObjectNode event = Json.newObject();
	        event.put("kind", kind);
	        ArrayNode m = event.putArray("members");
	        for(Member member: members){
	        	Fb_user user = Fb_user.find_by_uid(member.uid);
	        	ObjectNode user_data = Json.newObject();
	        	user_data.put("uid", Long.toString(user.uid));
	        	user_data.put("name", user.name);
	        	user_data.put("picture", user.picture);
	        	user_data.put("slot", Integer.toString(member.slot));
	        	user_data.put("points", Integer.toString(member.points));
	        	m.add(user_data);
	        }
	        for(Member member: members){
		        WebSocket.Out<JsonNode> channel = member.channel;
				channel.write(event);
	        }
		}
	    
	    public static void OnFold(String text, ArrayList<Member> members) {
	         ObjectNode event = Json.newObject();
	         event.put("kind", "fold");
	         event.put("message", text);
	         for(Member member: members){
	         	WebSocket.Out<JsonNode> channel = member.channel;
	 			channel.write(event);
	         }
	     }
	         
		public static void OnCallOrRaise(String text, GameState gamestate,  Member member_on_turn) {
	         ObjectNode event = Json.newObject();
	         event.put("kind", "call_or_raise");
	         event.put("message", text);
	         event.put("user_uid", Long.toString(member_on_turn.uid));
	         event.put("game_bet", Integer.toString(gamestate.Bet));
	         event.put("user_points", Integer.toString(member_on_turn.points));
	         for(Member member: gamestate.members){
	         	WebSocket.Out<JsonNode> channel = member.channel;
	 			channel.write(event);
	         }
	     }
	        
	    public static  void OnNextTurn(Member member_on_turn, GameState gamestate) {
            ObjectNode event = Json.newObject();
            event.put("kind", "user_on_turn");
            event.put("user_on_turn", Long.toString(member_on_turn.uid));
            event.put("slot", member_on_turn.slot);
            event.put("max_bet", Integer.toString(gamestate.MaxBet));
            event.put("user_uncall_bet", Integer.toString(member_on_turn.UnCallBet));
            for(Member member: gamestate.members){
            	WebSocket.Out<JsonNode> channel = member.channel;
    			channel.write(event);
            }
	      }
	             
	    public static void All(String kind, String text, ArrayList<Member> members) {
	        ObjectNode event = Json.newObject();
	        event.put("kind", kind);
	        event.put("message", text);
	        for(Member member: members){
	        	WebSocket.Out<JsonNode> channel =member.channel;
				channel.write(event);
	        }
	    }
	    
	    public static void OnFinishAnswering(ArrayList<Member> members){
	    	ObjectNode event = Json.newObject();
	        event.put("kind", "finish_answering");
	        ArrayNode m = event.putArray("members");
	        for(Member member :members){
	        	ObjectNode user_data = Json.newObject();
	        	user_data.put("uid", Long.toString(member.uid));
	        	user_data.put("answer", member.button);
	        	user_data.put("time", member.time_on_answering);
	        	m.add(user_data);
	        }
	        for(Member member: members){
	        	WebSocket.Out<JsonNode> channel =member.channel;
				channel.write(event);
	        }
	    }
	    
	    public static void OnWin(Long user_uid, String user_name, int user_points, ArrayList<Member> members){
	    	ObjectNode event = Json.newObject();
	        event.put("kind", "win");
	        event.put("message", "Победител е " + user_name);
	        event.put("user_uid", Long.toString(user_uid));
	        event.put("user_points", Integer.toString(user_points));
	        for(Member member: members){
	        	WebSocket.Out<JsonNode> channel =member.channel;
				channel.write(event);
	        }
	    }
	    
	    public static void OnQuestion(GameState gamestate){
	    	ObjectNode event = Json.newObject();
	        event.put("kind", "question");
	        event.put("question", gamestate.question.getQuestion());
	        event.put("answer1", (String) gamestate.question.answers.get(0));
	        event.put("answer2", (String)gamestate.question.answers.get(1));
	        event.put("answer3", (String)gamestate.question.answers.get(2));
	        event.put("answer4", (String)gamestate.question.answers.get(3));
	        event.put("bet", Integer.toString(gamestate.Bet));
	        
		    ArrayNode m = event.putArray("members");
	        for(Member member: gamestate.members){
	        	if(!member.fold && !member.wait && !member.out_of_points ){
	        		m.add(member.uid);
	        	}
	        }
	        for(Member member: gamestate.members){
	        	WebSocket.Out<JsonNode> channel = member.channel;
				channel.write(event);
	        }
	    }
	    
	    public static void One(String kind, String message, Member member){
	    	 ObjectNode event = Json.newObject();
		     event.put("kind", kind);
		     event.put("message", message);
		     member.channel.write(event);
	    }
	    
	 
	    
	    public static void OnMoreWinners(String text, GameState gamestate, ArrayList<Member> winners){
	    	ObjectNode event = Json.newObject();
	        event.put("kind", "more_winners");
	        event.put("message", text);
	        ArrayNode m = event.putArray("members");
	        for(Member winner: winners){
	        	ObjectNode user_data = Json.newObject();
	        	user_data.put("uid", Long.toString(winner.uid));
	        	user_data.put("points", Integer.toString(winner.points));
	        	m.add(user_data);
	         }
	      
	        for(Member member: gamestate.members){
	        	WebSocket.Out<JsonNode> channel = member.channel;
				channel.write(event);
	        }
	    }
	    
	    public static void OnCategory(int category, GameState gamestate){
	    	ObjectNode event = Json.newObject();
	    	event.put("kind", "category");
	        event.put("category", Integer.toString(category));
	        event.put("bet", Integer.toString(gamestate.Bet));
	        ArrayNode m = event.putArray("members");
	        for(Member member :gamestate.members){
	        	ObjectNode user_data = Json.newObject();
	        	user_data.put("uid", Long.toString(member.uid));
	        	user_data.put("points", Integer.toString(member.points));
	        	m.add(user_data);
	        }
	        for(Member member: gamestate.members){
	        	WebSocket.Out<JsonNode> channel = member.channel;
				channel.write(event);
	        }
	    }
	    
	    public static void OnJoker_50(Member member, ArrayList<String> answers){
	    	ObjectNode event = Json.newObject();
	    	event.put("kind", "joker_50");
	    	event.put("answer1", answers.get(0));
	        event.put("answer2", answers.get(1));
	        event.put("answer3", answers.get(2));
	        event.put("answer4", answers.get(3));
	        member.channel.write(event);
	    	
	    }
}
