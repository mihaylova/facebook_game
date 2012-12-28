

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
		Session sess = new Session(request, response, db);
		HttpSession session = request.getSession(true);
		String uid = sess.getUID();
		if (uid == null || uid.isEmpty()) {
			
			session.setAttribute("logged", false);
		} else {
			session.setAttribute("logged", true);
			session.setAttribute("name", sess.getName());
		}
		
ResultSet sessions = db.Get("SELECT * FROM sessions");
		
		ArrayList<String[]> users = new ArrayList<String[]>();

		try {
			while(sessions.next()) {
				String user_uid = sessions.getString("uid");
				String user_name = sessions.getString("name");
				
				if (user_uid != null && user_name != null) {

					String[] user = {user_uid, user_name};
	
					users.add(user);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (users.size() > 0) {
			session.setAttribute("has_users", true);
		} else {
			session.setAttribute("has_users", false);
		}
		session.setAttribute("users", users);
		response.sendRedirect("facebook.jsp");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DB db = new DB();
		Session sess = new Session(request, response, db);

        String token = request.getParameter("token");
        if(token == null){
        	response.setHeader("Location", "/inna/facebook/");
        	response.setStatus( HttpServletResponse.SC_MOVED_TEMPORARILY);
        } else {
        	FacebookClient facebookClient = new DefaultFacebookClient(token);
        	
        	User user = facebookClient.fetchObject("me", User.class);
        	       	
        	sess.setUID(user.getId());
        	sess.setName(user.getName());
        	sess.setAcessToken(token);
        	sess.flush();

    		PrintWriter out = response.getWriter();
    		out.print("OK");
        }
        
		
      
	}
	}


