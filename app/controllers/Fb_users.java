package controllers;

import play.mvc.Result;
import views.html.canvas.redirect;
import views.html.fb_user.*;

public class Fb_users extends Application {
	
	
	public static Result for_me() {
		if(current_user() != null){
			return ok(for_me.render(current_user()));
		}
		else{
			String permission_request_url = "https://graph.facebook.com/oauth/authorize?"
	                + "client_id=249406605187123&"
	                + "redirect_uri=http://apps.facebook.com/249406605187123/&scope=user_about_me";
				
				return ok(redirect.render(permission_request_url));
		}
		
	}
	
}
