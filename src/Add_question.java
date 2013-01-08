

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Add_question
 */
public class Add_question extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Boolean submit = false;
	boolean result;
	String question;
	String category;
	String right_answer;
	String answer1;
	String answer2;
	String answer3;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
	
		
		request.setAttribute("submit", submit);
		request.getRequestDispatcher("add_question.jsp").forward(request, response);
		
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
			
		if(request.getParameter("submit")!=null){
		submit=true;
		question = request.getParameter("question");
		category = request.getParameter("category");
		right_answer = request.getParameter("right_answer");
		answer1 = request.getParameter("answer1");
		answer2 = request.getParameter("answer2");
		answer3 = request.getParameter("answer3");
		
				
		result = Question.add(question, right_answer, answer1, answer2, answer3, category); 
		
		}
		if(result==true){
			request.setAttribute("status", "Данните бяха записани успешно!");
			
	}
		else{ 
			
			request.setAttribute("status", "Inna Netsho se obarka");
		} 
		
		request.setAttribute("question", question);
		request.setAttribute("category", category);
		request.setAttribute("right_answer", right_answer);
		request.setAttribute("answer1", answer1);
		request.setAttribute("answer2", answer2);
		request.setAttribute("answer3", answer3);
		
		request.setAttribute("submit", submit);
		request.getRequestDispatcher("add_question.jsp").forward(request, response);
		submit=false;
	}
	
}
