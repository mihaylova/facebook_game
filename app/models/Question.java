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
		private int category;
		private String answer1;
		private String answer2;
		private String answer3;
		
		public ArrayList<String> answers;
		public int choice_answer1;
		public int choice_answer2;
		public int choice_answer3;
		public int choice_answer4;
		
		public static String[] categories={"", "История", "Природни науки", "Изкуство", "Литература", "Спорт", "География", "Астронимия", "Технологии", "Кино и телевизия"};
		
	
		public static Question Get (int category){
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
		
		
		public static int RandomCategory(){
			int random = (int)(Math.random()*(10));
			if(random==0){
				return RandomCategory();
			}
			else{
				return random;
			}
			
		}

		private void MixAnswers(){
			this.answers=new ArrayList<String>();
			answers.add(this.answer1);
			answers.add(this.answer2);
			answers.add(this.answer3);
			answers.add(this.right_answer);
			int size = answers.size();
			for(int i=0; i<size; i++){
				int random = (int) (Math.random() * (size));
				String a= (String) answers.get(random);
				answers.set(random, answers.get(i));
				answers.set(i, a);
				
			}
		}

		public static void set_from_user(User_question user_question){
			
			Question question = new Question();
			question.setQuestion(user_question.getQuestion());
			question.setCategory(user_question.getCategory());
			question.setRight_answer(user_question.getRight_answer());
			question.setAnswer1(user_question.getAnswer1());
			question.setAnswer2(user_question.getAnswer2());
			question.setAnswer3(user_question.getAnswer3());
			question.save();
			user_question.delete();
			
			
		
			
		}



		

}
