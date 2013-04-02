package controllers;

import static play.data.Form.form;
import models.Fb_user;
import play.data.DynamicForm;
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
	
	public static Result convert(){
		DynamicForm form = form().bindFromRequest();
		Fb_user user = current_user();
		if(user.points<Integer.parseInt(form.get("points")) || Integer.parseInt(form.get("points"))%2!=0){
			return redirect(routes.Fb_users.for_me());
		}
		else{
			user.MinusPoints(Integer.parseInt(form.get("points")));
			user.SetCoins(user.coins+ (Integer.parseInt(form.get("points")))/2);
			return redirect(routes.Fb_users.for_me());
		}
		
		
	}
	
}
