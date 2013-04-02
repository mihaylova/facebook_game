package controllers;

import org.codehaus.jackson.JsonNode;

import com.typesafe.config.ConfigFactory;

import play.*;
import play.data.DynamicForm;
import play.mvc.*;

import views.html.canvas.*;


public class Canvas extends Application {

//	@With(ProbaAction.class)
	public static Result index () {
		if (parse_facebook_signed_request() || current_user() != null) {
			return ok(index.render(current_user().name, ""));
		} else {
			String permission_request_url = "https://graph.facebook.com/oauth/authorize?"
                + "client_id="+ConfigFactory.load().getString("fbapp.id")+"&"
                + "redirect_uri=http://apps.facebook.com/"+ConfigFactory.load().getString("fbapp.id")+"/&scope=user_about_me";
			
			// + "client_id=348605795251405&"
             //+ "redirect_uri=http://apps.facebook.com/348605795251405/&scope=user_about_me";
			
			return ok(redirect.render(permission_request_url));
		}
	}
	public static Result redirect(){
		return redirect("http://apps.facebook.com/"+ConfigFactory.load().getString("fbapp.id")+"/");
	}
}
