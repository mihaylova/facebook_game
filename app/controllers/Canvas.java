package controllers;

import org.codehaus.jackson.JsonNode;

import play.*;
import play.data.DynamicForm;
import play.mvc.*;

import views.html.canvas.*;

public class Canvas extends Application {

//	@With(ProbaAction.class)
	public static Result index () {
		if (parse_facebook_signed_request() || current_user() != null) {
			return ok(index.render(current_user().name));
		} else {
			String permission_request_url = "https://graph.facebook.com/oauth/authorize?"
                + "client_id=249406605187123&"
                + "redirect_uri=http://apps.facebook.com/249406605187123/";
			
			return ok(redirect.render(permission_request_url));
		}
	}
	
}
