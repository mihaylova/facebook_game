import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.http.*;

public class Session {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private DB db;
	private int id = 0;
	private HashMap<String, String> params = new HashMap<String, String>();

	public Session (HttpServletRequest request, HttpServletResponse response, DB db) {
		this.request = request;
		this.response = response;
		this.db = db;
		
		this.init();
	}
	
	public void init() {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for(int i=0; i<cookies.length; i++){
				Cookie cookie = cookies[i];
				
				String cookie_name = cookie.getName();
				
				if (cookie_name.equals("_session")) {
					
					id = Integer.parseInt(cookie.getValue());
				}
			}
		}

		if (id != 0) {
			// TODO: fix id escape
			ResultSet session_data = db.GetRow("SELECT * FROM sessions WHERE id=" + id);
			
			try {
				params.put("uid", session_data.getString("uid"));
				params.put("access_token", session_data.getString("access_token"));
				params.put("name", session_data.getString("name"));
			} catch (SQLException e) {
				id = 0;
			}
		}

		if (id == 0) {

			id = (int) (Math.random() * 99999);
			
			db.Do("INSERT INTO sessions SET id=" + id);
			
			Cookie cookie = new Cookie("_session", Integer.toString(id));
			
			response.addCookie(cookie);
		}
	}
	
	public void setUID (String uid) {
		params.put("uid", uid);
	}

	public String getUID () {
		return (String) params.get("uid");
	}

	public void setName (String name) {
		params.put("name", name);
	}

	public String getName () {
		return (String) params.get("name");
	}

	public void setAcessToken (String token) {
		params.put("access_token", token);
	}

	public String getAcessToken () {
		return (String) params.get("access_token");
	}
	
	public void flush() {
		String query = "UPDATE sessions SET";

		query += " uid='" + params.get("uid") + "',";
		query += " access_token='" + params.get("access_token") + "',";
		query += " name='" + params.get("name") + "'";
		
		query += " WHERE id=" + id;
		
		db.Do(query);
	}
}
