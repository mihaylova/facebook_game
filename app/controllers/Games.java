package controllers;



import models.Game;
import play.data.DynamicForm;
import play.mvc.Result;
import views.html.game.*;

public class Games extends Application {
	
	
	public static Result index() {

		Game game=Game.Join_or_Create();
		session("game_id", Long.toString(game.id));
		return ok();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//private static  Game game = new Game();
	
//	
//	public static Result show_category() {
//		game.setRandomCategory();
//		return ok(show_category.render(game.getCategory()));
//		
//	}
//		
//	public static Result show_question() {
//		game.setQuestion(game.getCategory());
//		return ok(show_question.render(game.getQuestion()));
//		
//	}
//	
//	public static Result check_answer() {
//		 DynamicForm form = form().bindFromRequest();
//		
//		if(form.get("user_answer").equals(game.getQuestion().getRight_answer())){
//			game.getPlayer1().PlusPoints(10);
//			flash("message", "Отговорихте правилно! Вашите точки сега са: "+game.getPlayer1().points);
//			return redirect("/");
//		}
//		else{
//			game.getPlayer1().MinusPoints(10);
//			flash("message", "Отговорихте грешно! Вашите точки сега са: "+game.getPlayer1().points);
//			return redirect("/");
//		}
//		
//	}
		

}
