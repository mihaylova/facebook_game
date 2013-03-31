package controllers;



//import models.ChatRoom;
import models.Game;
import models.GameRoom;
import models.Fb_user;
import play.data.DynamicForm;
import play.libs.Json;
import play.mvc.Result;
import play.mvc.WebSocket;
import views.html.canvas.redirect;
import views.html.game.*;


import org.codehaus.jackson.*;
import org.codehaus.jackson.node.ObjectNode;

public class Games extends Application {
	
	public static Result index() {
		//null pointer exception
		if(current_user() != null){
			if(current_user().points<=0){
				return ok(views.html.canvas.index.render(current_user().name, "Нямате достатъчно точки за да играете. За получаване на точки можете да зададете въпрос."));
			}
			else{
				return ok(index.render(current_user().uid));
			}
			
		}
		else {
			String permission_request_url = "https://graph.facebook.com/oauth/authorize?"
	                + "client_id=249406605187123&"
	                + "redirect_uri=http://apps.facebook.com/249406605187123/";
				
				return ok(redirect.render(permission_request_url));
		}
		
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
    
   
	
   
}
