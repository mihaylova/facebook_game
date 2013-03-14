package controllers;



//import models.ChatRoom;
import models.Game;
import models.GameRoom;
import models.Fb_user;
import play.data.DynamicForm;
import play.libs.Json;
import play.mvc.Result;
import play.mvc.WebSocket;
import views.html.game.*;

import org.codehaus.jackson.*;
import org.codehaus.jackson.node.ObjectNode;

public class Games extends Application {
	
	public static Result index() {
		return ok(index.render(current_user().uid));
	}
	
    public static WebSocket<JsonNode> join() {
   	
		final Game game = Game.Get();
		String game_id = game.id.toString();
		session("game_id", game_id);
		final Fb_user user = current_user();
    	
        return new WebSocket<JsonNode>() {
            
            public void onReady(WebSocket.In<JsonNode> in, WebSocket.Out<JsonNode> out){
                
                // Join the game
                try { 
                    GameRoom.join(user, game, in, out);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
    }
    
    private static boolean user_can_play() {
    	return true;
    }
	
    public static Result testview(){
		return ok(view.render());
	}
}
