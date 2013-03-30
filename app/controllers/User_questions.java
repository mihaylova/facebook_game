package controllers;

import models.User_question;
import play.data.Form;
import play.db.DB;
import play.mvc.Result;
import views.html.user_questions.*;

import play.data.*;
import static play.data.Form.*;

public class User_questions extends Application {

final static Form<User_question> questionForm = form(User_question.class);
	
	
	
public static Result add () {
		
		return ok(add.render(questionForm));
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
//public static Result show(Long id){
//
//	User_question question = User_question.find.byId(id);
//
//	return ok(show.render(question));
//}

}
