

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Game
 */
public class Game extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Boolean play=false;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processRequest(request, response);
	}
	
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		
		if(request.getParameter("play")!=null){
			play=true;
		}
		if(request.getParameter("noplay")!=null){
			play=false;
		}
		if(!play){
		
			//new session
			//new user whit data from session
			String category=Question.RandomCategory();//whit while loop while(category!=session.category)
			request.setAttribute("category", category);
			//save category in session
			request.setAttribute("play", false);
			
		}
		if(play) {
			request.setAttribute("message", "NEW GAME");
			request.setAttribute("play", true);
			
		}
		request.getRequestDispatcher("game.jsp").forward(request, response);

	}

}
