package controllers;

import java.util.List;

import models.Question;
import models.User_question;
import play.data.Form;
import play.db.DB;
import play.mvc.Result;
import views.html.canvas.redirect;
import views.html.game.index;
import views.html.questions.view_from_user;
import views.html.user_questions.*;

import play.data.*;
import static play.data.Form.*;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
//import com.restfb.AccessToken;
import com.restfb.types.User;
import com.restfb.types.Photo;
import com.restfb.types.FacebookType;
import com.restfb.Parameter;
import com.typesafe.config.ConfigFactory;


public class User_questions extends Application {

	final static Form<User_question> questionForm = form(User_question.class);
		
		
		
	public static Result add () {
		if(current_user() != null){
			return ok(add.render(questionForm));
			
		}
		else {
			String permission_request_url = "https://graph.facebook.com/oauth/authorize?"
	                + "client_id="+ConfigFactory.load().getString("fbapp.id")+"&"
	                + "redirect_uri=http://apps.facebook.com/"+ConfigFactory.load().getString("fbapp.id")+"/&scope=user_about_me";
				
				return ok(redirect.render(permission_request_url));
		}
		
	}
	
	public static Result save () {
		Form<User_question> filledForm = questionForm.bindFromRequest();
		
	    if(filledForm.hasErrors()) {
	        return badRequest(add.render(filledForm));
	    } else {
	        User_question question = filledForm.get();
	        
	       question.setUsername(current_user().name);
	       question.setUser_uid(current_user().uid);
	       question.save();
	       flash("success", "Въпроса беше добавен!!!");
	    
	      return redirect("/question/add");
	        
	        
	    }
			
	}
	public static Result view() {
		if(session("admin")!=null){	
			List<User_question> user_questions =  (List<User_question> ) User_question.find.all();
			
			return ok(view_from_user.render(user_questions, Question.categories));
			
		}
		else return redirect(routes.Admins.login());
	}
	public static Result insert (Long id){
		if(session("admin")!=null){
			User_question user_question = User_question.find.byId(id);
			Question.set_from_user(user_question);
			
			flash("success", "Въпроса беше добавен!!!");
			return redirect("/admin/users_questions");
			}
		else return redirect(routes.Admins.login());
				}
	
	public static Result delete(Long id){
		if(session("admin")!=null){
				User_question question = User_question.find.byId(id);
				question.delete();
				flash("success", "Въпроса беше изтрит!!!");
				return redirect("/admin/users_questions");
			}
		else return redirect(routes.Admins.login());
				}
}
