

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Game extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Boolean play=false;
	private Boolean first=true;
	private Boolean gotanswer=false;
	private Boolean right;
	private Session sess;
	private F_user user;
	private DB db=new DB();
	private HttpSession session;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processRequest(request, response);
	}
	
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		CheckStatus(request, response);
		
		
		if(first){
			SetUser(request, response);
					}
		
		if(!play){
			SetCategory(request, response);
			request.setAttribute("play", false);
				}
		
		if(play) {
			Play(request, response);
		
			}
		
		request.getRequestDispatcher("game.jsp").forward(request, response);

	}
	
	protected void CheckStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//		if(request.getParameter("play")==null && (request.getParameter("noplay")==null)){
//			first=true;
//		}
		
		if(request.getParameter("play")!=null){
			play=true;
			first=false;
		}
		if(request.getParameter("noplay")!=null){
			play=false;
			first=false;
		}
		if(request.getParameter("user_answer")!=null){
			gotanswer=true;
		}
		
	}
	
	protected void SetUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		sess=new Session(request, response, db);
		user=new F_user(sess.getUID(), sess.getPoints(), sess.getName());
		session = request.getSession();
		session.setAttribute("user", user);
		
	}

	protected void SetQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		Question question = new Question((String)session.getAttribute("category"));
		session.removeAttribute("category");
		session.setAttribute("right_answer", question.getRigth_answer());
		ArrayList<String> answers=question.getAnswers();
		String answer1 = answers.get(0);
		String answer2 = answers.get(1);
		String answer3 = answers.get(2);
		String answer4 =  answers.get(3);
		request.setAttribute("question",question.getQuestion());
		request.setAttribute("answer1", answer1);
		request.setAttribute("answer2", answer2);
		request.setAttribute("answer3", answer3);
		request.setAttribute("answer4", answer4);
		
	}
	
	protected void SetCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String category;
		
		do{
			category=Question.RandomCategory();
		}
		while(category==session.getAttribute("category"));
		
		session.removeAttribute("category");
		session.setAttribute("category", category);
		
		request.setAttribute("category", category);
		
	}
	
	protected void CheckAnswer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String right_answer=(String) session.getAttribute("right_answer");
		String user_answer=(String)request.getParameter("user_answer");
		session.removeAttribute("right_answer");
		
		if(right_answer.equals(user_answer)){
			request.setAttribute("message", "Верен отговор");
			right=true;
			
		}
		else{
			request.setAttribute("message", "Грешен отговор");
			right=false;
		}
	}
	
	protected void UpdatePoints(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		user=(F_user)session.getAttribute("user");
		if(right){
			user.PlusPoints();
		}
		else{
			user.MinusPoints();
		}
		request.setAttribute("points", user.getPoints());
		request.setAttribute("name", user.getName());
	}
	
	protected void Play(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setAttribute("play", true);
		if(!gotanswer){
			request.setAttribute("gotanswer", false);
			SetQuestion(request, response);
		}
		if(gotanswer){
			request.setAttribute("gotanswer", true);
			CheckAnswer(request, response);
			UpdatePoints(request, response);
			gotanswer=false;
			play=false;
		}
	}
}
