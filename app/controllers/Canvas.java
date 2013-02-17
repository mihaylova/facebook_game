package controllers;

import org.codehaus.jackson.JsonNode;

import play.*;
import play.data.DynamicForm;
import play.mvc.*;

import views.html.canvas.*;




public class Canvas extends Application {

//	@With(ProbaAction.class)
	public static Result index () {
		if (parse_facebook_signed_request()) {
			return ok(current_user().name);
		}
		
		return badRequest("Reload page");
	}
	
}
