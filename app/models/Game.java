package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import controllers.Application;
import play.data.validation.Constraints;
import play.db.ebean.Model;
@Entity 
public class Game extends Model {


	@Id
	@Constraints.Min(10)
	public Long id;
	
	
	
	public Long player1UID;
	public Long player2UID;
	public Boolean isStart=false;
	public Boolean isFinish=false;
	public Date start;
	public Date finish;
	
	public static Finder<Long, Game> find = new Finder<Long,Game>(
			Long.class, Game.class
	); 
	

	public static Game Join_or_Create(){
		Game game = find.where().eq("is_start", false).eq("is_finish", false).findUnique();
		 if(game != null){
			 game.set_player(Application.current_user().uid); 
			 if (!game.has_more_free_slots()) {
				 game.start_current_game();
			 }
			
			 
			 

		 }
		 else{
			 game = new Game();
			 game.set_player(Application.current_user().uid);
			

		 }
		 return game;
	}
	
	private Boolean has_more_free_slots(){
		if(player1UID==null || player2UID==null){
			return true;
		}
		else return false;
	}
	
	private void start_current_game(){
		isStart = true;
		start = new Date();
		save();
	}
	
	private void finish_current_game(){
		isFinish = true;
		finish = new Date();
		save();
	}
	
	private void set_player(long player_uid){
		if(this.player1UID==null){
			this.player1UID=player_uid;
		}
		else if(this.player2UID==null){
			this.player2UID=player_uid;
		}
		save();
	}
	
//	private String category;
//	private Question question;
//	
//	public Game(){
//		this.player1=Application.current_user();
//	}
//	public Fb_user getPlayer1() {
//		return player1;
//	}
//	
//	
//	public Question getQuestion() {
//		return question;
//	}
//	public void setQuestion(String category) {
//		this.question = Question.Get(category);
//	
//		
//	}
//	public String getCategory() {
//		return category;
//	}
//	public void setRandomCategory() {
//		String category;
//
//		do{
//			category=Question.RandomCategory();
//		}
//		while(category.equals(this.category));
//
//		this.category = category;
//	}

	
}
