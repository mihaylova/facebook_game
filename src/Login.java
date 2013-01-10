

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		response.setContentType("text/html; charset=utf-8");
		DB db = new DB();
		HttpSession sess = request.getSession(true);
		
		String uid = (String) sess.getAttribute("uid");
		if (uid == null || uid.isEmpty()) {
			
			request.setAttribute("logged", false);
		} else {
		
			request.setAttribute("logged", true); 
			F_user user=new F_user(uid);
			request.setAttribute("name", user.getName());
			request.setAttribute("uid", sess.getAttribute("uid"));
			
			
		}
		
		request.getRequestDispatcher("/facebook.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DB db = new DB();
		HttpSession sess = request.getSession(true);

        String token = request.getParameter("token");
        if(token == null){
        	
        	response.setHeader("Location", "/inna/Login/");
        	response.setStatus( HttpServletResponse.SC_MOVED_TEMPORARILY);
        } else {
        	FacebookClient facebookClient = new DefaultFacebookClient(token);
        	
        	User fuser = facebookClient.fetchObject("me", User.class);
        	
        	sess.setAttribute("uid", fuser.getId());
        	F_user f_user = new F_user(fuser.getId());
        	if (f_user.isNew()) {
	
        		f_user.setName(fuser.getName());
        		f_user.setPoints("200");
        	}
        	
        	f_user.setAcessToken(token);

    		PrintWriter out = response.getWriter();
    		out.print("OK");
        }
        
		
      
	}
	}


