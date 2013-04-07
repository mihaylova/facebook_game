package controllers;

import java.util.ArrayList;
import java.util.List;

import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.common.BeanList;

import models.Question;
import models.User_question;
import play.data.DynamicForm;
import play.data.Form;
import play.db.DB;
import play.mvc.Result;
import views.html.questions.*;

import play.data.*;
import static play.data.Form.*;

public class Questions extends Application {
	
	final static Form<Question> questionForm = form(Question.class);
		
					

	
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
		       
		       
		        question.save();
		        flash("success", "Въпроса беше добавен!!!");
		        return redirect("/admin/question/add");
		              
		    }
				
		}
		else return redirect(routes.Admins.login());
	}
		
		public static Result editQuestion () {
			if(session("admin")!=null){	
				Form<Question> filledForm = questionForm.bindFromRequest();
				
			    if(filledForm.hasErrors()) {
			        return badRequest(add.render(filledForm));
			    } else {
			        
			        Question question = Question.find.byId(Long.parseLong(session("edit")));
			        question.setQuestion(filledForm.data().get("question"));
			        question.setRight_answer(filledForm.data().get("right_answer"));
			        question.setAnswer1(filledForm.data().get("answer1"));
			        question.setAnswer2(filledForm.data().get("answer2"));
			        question.setAnswer3(filledForm.data().get("answer3"));
			        question.setCategory(Integer.parseInt(filledForm.data().get("category")));
			        
			        question.save();
			        session().remove("edit");
			        flash("success", "Въпроса беше променен!!!");
			        return redirect("/admin/questions");
			              
			    }
					
			}
		
		
		else return redirect(routes.Admins.login());
	}
		
		public static Result search(){
			return ok(search.render());
		}
	
	public static Result view() {
		if(session("admin")!=null){	
			List<Question> questions =  (List<Question> )Question.find.all();
			//DynamicForm form = form().bindFromRequest();
			//int category = Integer.parseInt(form.data().get("category"));
			//List<Question> questions =   Question.find.where().eq("category", category).findList();
			
			return ok(view.render(questions, Question.categories));
			
		}
		else return redirect(routes.Admins.login());
	}
	
	public static Result view_by_category() {
		if(session("admin")!=null){	
			//List<Question> questions =  (List<Question> )Question.find.all();
			DynamicForm form = form().bindFromRequest();
			int category = Integer.parseInt(form.data().get("category"));
			List<Question> questions =   Question.find.where().eq("category", category).findList();
			
			return ok(view.render(questions, Question.categories));
			
		}
		else return redirect(routes.Admins.login());
	}
	
	public static Result edit (Long id){
		if(session("admin")!=null){
			Question question = Question.find.byId(id);
			
			session("edit", Long.toString(question.id));
			
			return ok(edit.render(question, Question.categories));
			}
		else return redirect(routes.Admins.login());
				}
	
	public static Result delete(Long id){
		if(session("admin")!=null){
				Question question = Question.find.byId(id);
				question.delete();
				flash("success", "Въпроса беше изтрит!!!");
				return redirect("/admin/questions");
			}
		else return redirect(routes.Admins.login());
				}

	
}
