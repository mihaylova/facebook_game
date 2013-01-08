

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Game extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Boolean play=false;
	Boolean first;
	Session sess;
	F_user user;
	DB db=new DB();
	HttpSession session;
    
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
				}
		
		if(play) {
			Play(request, response);
			}
		
		request.getRequestDispatcher("game.jsp").forward(request, response);

	}
	
	protected void CheckStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		if(request.getParameter("play")==null && (request.getParameter("noplay")==null)){
			first=true;
		}
		
		if(request.getParameter("play")!=null){
			play=true;
			first=false;
		}
		if(request.getParameter("noplay")!=null){
			play=false;
			first=false;
		}
		
	}
	
	protected void SetUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		sess=new Session(request, response, db);
		user=new F_user(sess.getUID(), sess.getPoints());
		session = request.getSession();
		session.setAttribute("user", user);
		
	}

	protected void Play(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setAttribute("play", true);
		Question question = new Question((String)session.getAttribute("category"));
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
		request.setAttribute("play", false);
	}
	
	}
