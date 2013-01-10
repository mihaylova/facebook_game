

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;

/**
 * Servlet implementation class Start
 */
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html; charset=utf-8");
	
		HttpSession session= request.getSession();
		
		Boolean logged = Have_cookie(request, response);
		
		//metod koito vrastra true ili false dali ima takava biskvita, ako niama logged=false
			request.setAttribute("logged", logged);
		
		if(logged){
			//metod koito vrashta id-to na biskvitata i s tova id se sazdava nov user/ ostanalite parametri se vikat ot bazata Users avtomatichno v klasa user
			//F_user user= new F_ 
			request.setAttribute("name", session.getAttribute("name"));
			//others
			//tozi nov potrebitel se zapazva v sesia
		}
		else{
		String token = request.getParameter("token");
        if(token == null){
        	
        	response.setHeader("Location", "/inna/Start");
        	response.setStatus( HttpServletResponse.SC_MOVED_TEMPORARILY);
        } else {
        	FacebookClient facebookClient = new DefaultFacebookClient(token);
        	
        	User fuser = facebookClient.fetchObject("me", User.class);
        	 
        	session.setAttribute("uid", fuser.getId());
        	session.setAttribute("name", fuser.getName());
        	session.setAttribute("token", token);
        	
        	//static method F_user.add(
        	
        	//tuk se sazdava nova biskvita, metoda add vkarva v bazat danni id na biskvitata i drugite danni za potrebitellia
        
        }
        	

    		
        }

		
		
		
		
		
		request.getRequestDispatcher("facebook.jsp").forward(request, response);
		
	}
	protected void Add_cookie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int id = (int) (Math.random() * 99999);
		
		Cookie cookie = new Cookie("_session", Integer.toString(id));
		
		response.addCookie(cookie);
	}
	
	protected String Get_cookie_id(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id=null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for(int i=0; i<cookies.length; i++){
				Cookie cookie = cookies[i];
				
				String cookie_name = cookie.getName();
				
				if (cookie_name.equals("_session")) {
					
					id = cookie.getValue();
				}
			}
		}
		return id;
	}
	
	protected boolean Have_cookie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		boolean have_cookie=false;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for(int i=0; i<cookies.length; i++){
				Cookie cookie = cookies[i];
				
				String cookie_name = cookie.getName();
				
				if (cookie_name.equals("_session")) {
					
					have_cookie=true;
				}
			}
		}
		return have_cookie;
		
	}
	
}
