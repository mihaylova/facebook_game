package models;

import java.util.ArrayList;

import play.db.ebean.Model;
import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity 
public class Question extends Model {
	@Id
	  @Constraints.Min(10)
	  public Long id;
	
	
		private String question;
		private String right_answer;
		private String category;
		private String answer1;
		private String answer2;
		private String answer3;
		private String answer4;
		
	
		public static Question Get (String category){
			Question question=(Question) Question.find.where().eq("category", category).orderBy("RAND()").setMaxRows(1).findUnique();
	
			question.MixAnswers();
			return question;
		}
		public static Finder<Long,Question> find = new Finder<Long, Question>(
			    Long.class, Question.class
			  );


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


		public String getCategory() {
			return category;
		}


		public void setCategory(String category) {
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
		
		public String getAnswer4() {
			return answer4;
		}


		public void setAnswer4(String answer4) {
			this.answer4 = answer4;
		} 
		

		
		public static String RandomCategory(){
			String[] categories = {"Математика", "История", "География", "Астрономия", "Компютърни науки"};
			int item = (int) (Math.random() * (categories.length));
			return categories[item];
		}

		private void MixAnswers(){
			ArrayList answers=new ArrayList();
			answers.add(this.answer1);
			answers.add(this.answer2);
			answers.add(this.answer3);
			answers.add(this.answer4);
			int size = answers.size();
			for(int i=0; i<size; i++){
				int random = (int) (Math.random() * (size));
				String a= (String) answers.get(random);
				answers.set(random, answers.get(i));
				answers.set(i, a);
				this.answer1=(String) answers.get(0);
				this.answer2=(String) answers.get(1);
				this.answer3=(String) answers.get(2);
				this.answer4=(String) answers.get(3);
			}
		}

		public static Question set_from_user(User_question user_question){
			
			Question question = new Question();
			question.setQuestion(user_question.getQuestion());
			question.setCategory(user_question.getCategory());
			question.setRight_answer(user_question.getRight_answer());
			question.setAnswer1(user_question.getAnswer1());
			question.setAnswer2(user_question.getAnswer2());
			question.setAnswer3(user_question.getAnswer3());
			question.setAnswer4(user_question.getRight_answer());
			
			
			
			return question;
			
		}



		

}
