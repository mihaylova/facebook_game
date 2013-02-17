package controllers;

import java.util.ArrayList;
import java.util.List;

import com.avaje.ebean.common.BeanList;

import models.Question;
import models.User_question;
import play.data.DynamicForm;
import play.data.Form;
import play.db.DB;
import play.mvc.Result;
import views.html.questions.*;

public class Questions extends Application {
	
	final static Form<Question> questionForm = form(Question.class);
		
					
	public static Result show(Long id){
		if(session("admin")!=null){
				Question question = Question.find.byId(id);
		
				return ok(show.render(question));
			}
		else return redirect(routes.Admins.login());
		}
	
	public static Result add () {
		if(session("admin")!=null){	
			return ok(add.render(questionForm));
		}
		else return redirect(routes.Admins.login());
	}
	
	public static Result save () {
		if(session("admin")!=null){	
			Form<Question> filledForm = questionForm.bindFromRequest();
			
		    if(filledForm.hasErrors()) {
		        return badRequest(add.render(filledForm));
		    } else {
		        Question question = filledForm.get();
		        question.setAnswer4(question.getRight_answer());
		       
		        question.save();
		        return redirect("/admin/question/" + question.id);
		              
		    }
				
		}
		else return redirect(routes.Admins.login());
	}
	
	public static Result view() {
		if(session("admin")!=null){	
			List<User_question> user_questions =  (List<User_question> ) User_question.find.all();
			
			return ok(view_from_user.render(user_questions));
			
		}
		else return redirect(routes.Admins.login());
	}

	public static Result insert (Long id){
		if(session("admin")!=null){
			User_question user_question = User_question.find.byId(id);
			Question question = Question.set_from_user(user_question);
			question.save();
			user_question.delete();
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
