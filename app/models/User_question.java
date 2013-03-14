package models;



import play.db.ebean.Model;
import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity 
public class User_question extends Model {
	
	@Id
	  @Constraints.Min(10)
	  public Long id;
	
	
		private String question;
		private String right_answer;
		private int category;
		private String answer1;
		private String answer2;
		private String answer3;
		private String username;
		private Long user_uid;
		
		
		public static Finder<Long,User_question> find = new Finder<Long,User_question>(
			    Long.class, User_question.class
			  );


		public String getUsername() {
			return username;
		}


		public void setUsername(String username) {
			this.username = username;
		}


		public Long getUser_uid() {
			return user_uid;
		}


		public void setUser_uid(Long user_uid) {
			this.user_uid = user_uid;
		}


		public String getQuestion() {
			return question;
		}


		public void setQuestion(String question) {
			this.question = question;
		}


		public String getRight_answer() {
			return right_answer;
		}


		public void setRight_answer(String right_answer) {
			this.right_answer = right_answer;
		}


		public int getCategory() {
			return category;
		}


		public void setCategory(int category) {
			this.category = category;
		}


		public String getAnswer1() {
			return answer1;
		}


		public void setAnswer1(String answer1) {
			this.answer1 = answer1;
		}


		public String getAnswer2() {
			return answer2;
		}


		public void setAnswer2(String answer2) {
			this.answer2 = answer2;
		}


		public String getAnswer3() {
			return answer3;
		}


		public void setAnswer3(String answer3) {
			this.answer3 = answer3;
		} 



		
}
