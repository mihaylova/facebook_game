

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		response.setContentType("text/html; charset=utf-8");
		DB db = new DB();
		Session sess = new Session(request, response, db);
		
		String uid = sess.getUID();
		if (uid == null || uid.isEmpty()) {
			
			request.setAttribute("logged", false);
		} else {
		F_user user = new F_user((String)sess.getUID(), (String)sess.getPoints());
			request.setAttribute("logged", true); 
			request.setAttribute("name", sess.getName());
		}
		
ResultSet sessions = db.Get("SELECT * FROM sessions");
		
		ArrayList<String[]> users = new ArrayList<String[]>();

		try {
			while(sessions.next()) {
				String user_uid = sessions.getString("uid");
				String user_name = sessions.getString("name");
				String user_points = sessions.getString("points");
				
				if (user_uid != null && user_name != null) {

					String[] user = {user_uid, user_name, user_points};
	
					users.add(user);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (users.size() > 0) {
			request.setAttribute("has_users", true);
		} else {
			request.setAttribute("has_users", false);
		}
		request.setAttribute("users", users);
		request.getRequestDispatcher("facebook.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DB db = new DB();
		Session sess = new Session(request, response, db);

        String token = request.getParameter("token");
        if(token == null){
        	
        	response.setHeader("Location", "/inna/Login");
        	response.setStatus( HttpServletResponse.SC_MOVED_TEMPORARILY);
        } else {
        	FacebookClient facebookClient = new DefaultFacebookClient(token);
        	
        	User fuser = facebookClient.fetchObject("me", User.class);
        	       	
        	sess.setUID(fuser.getId());
        	sess.setName(fuser.getName());
        	sess.setAcessToken(token);
        	sess.setPoints("200");
        	sess.flush();

    		PrintWriter out = response.getWriter();
    		out.print("OK");
        }
        
		
      
	}
	}


