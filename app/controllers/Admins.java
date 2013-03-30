package controllers;

import java.util.HashMap;

import models.Admin;
import play.data.Form;
import play.db.DB;
import play.mvc.Result;
import views.html.admins.*;
import views.html.questions.add;
import play.data.*;
import static play.data.Form.*;

public class Admins extends Application {

			
		public static Result login() {
			return ok(
			    login.render(form(Admin.class))
			);
		}
		
		
		public static Result authenticate() {
			Form<Admin> loginForm = form(Admin.class).bindFromRequest();
			if(loginForm.hasErrors()) {
			    return badRequest(login.render(loginForm));
			} else {
				
				if(Admin.authenticate(loginForm.get().username, loginForm.get().password) == null) {
					flash("error", "Invalid user or password");
					return redirect(
					    routes.Admins.login()
					);
			    }
			    
				else{
				    session("admin", loginForm.get().username);
				    return redirect(
				        routes.Admins.index()
				    );
				}
			}
		}
		
		
		public static Result logout() {
		session().clear();
		flash("success", "You've been logged out");
		return redirect(
		    routes.Admins.login()
		);
		}
		
		public static Result index(){
			if(session("admin")!=null){	
				return ok(index.render(session("admin")));
			}
			else return redirect(routes.Admins.login());
			
		}


}
