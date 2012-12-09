
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import com.restfb.*;
import com.restfb.types.User;

/**
 * Servlet implementation class facebook
 */
public class facebook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DB db = new DB();
		Session sess = new Session(request, response, db);
		Render render = new Render(getServletContext());
		
		String uid = sess.getUID();
		if (uid == null || uid.isEmpty()) {
			
			render.set("logged", false);
		} else {
			render.set("logged", true);
			render.set("name", sess.getName());
		}

		ResultSet sessions = db.Get("SELECT * FROM sessions");
		
		ArrayList users = new ArrayList();

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
			render.set("has_users", true);
		} else {
			render.set("has_users", false);
		}
		render.set("users", users);
		
		//sess.flush();

		render.render(response, "facebook.html");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DB db = new DB();
		Session sess = new Session(request, response, db);

        String token = request.getParameter("token");
        
        if (token != null) {
        	FacebookClient facebookClient = new DefaultFacebookClient(token);
        	
        	User user = facebookClient.fetchObject("me", User.class);
        	       	
        	sess.setUID(user.getId());
        	sess.setName(user.getName());
        	sess.setAcessToken(token);
        	sess.flush();
        }
        
		PrintWriter out = response.getWriter();
		out.print("OK");
	}

}
