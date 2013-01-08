import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Question {

private String question;
private ArrayList<String> answers = new ArrayList<String>();
private String right_answer;
private String category;



public Question(String category){
	DB db = new DB();
	this.category=category;
	ResultSet result = db.GetRow("Select * from Questions where category='"+category+"' order by RAND() LIMIT 1;");
	try {
		this.question=result.getString("question");
		this.right_answer=result.getString("right_answer");
		answers.add(result.getString("answer1"));
		answers.add(result.getString("answer2"));
		answers.add(result.getString("answer3"));
		answers.add(result.getString("right_answer"));
		MixAnswers();

		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	
}
public static boolean add(String question, String right_answer, String answer1, String answer2, String answer3, String category){
	DB db = new DB();
	String query = "Insert into questions set question='"+question+"', category='"+category+"', right_answer='"+right_answer+"', answer1='"+answer1+"', answer2='"+answer2+"', answer3='"+answer3+"';";
	boolean result = db.Do(query);
	return result;

}

public static String RandomCategory(){
	String[] categories = {"Biologia", "Matematika"};
	int item = (int) (Math.random() * (categories.length));
	return categories[item];
}

private void MixAnswers(){
	int size = this.answers.size();
	for(int i=0; i<size; i++){
		int random = (int) (Math.random() * (size-i));
		String a= this.answers.get(random);
		this.answers.set(random, this.answers.get(i));
		this.answers.set(i, a);
	}
}

public String getQuestion() {
	return question;
}


public ArrayList<String> getAnswers() {
	return answers;
}


public String getRigth_answer() {
	return right_answer;
}


public String getCategory() {
	return category;
}







}
