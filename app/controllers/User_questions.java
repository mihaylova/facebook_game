package controllers;

import models.User_question;
import play.data.Form;
import play.db.DB;
import play.mvc.Result;
import views.html.user_questions.*;

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
        
       // question.setUsername() ot sesiata
        //question.setUser_uid() ot sesiata
       question.save();
      return redirect("/question/" + question.id);
        
        
    }
		
}
public static Result show(Long id){

	User_question question = User_question.find.byId(id);

	return ok(show.render(question));
}

}
