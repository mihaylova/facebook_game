

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Foo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Render render;
    
   public void init() {
    	render = new Render(getServletContext()); 
    }
	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DB db = new DB();
        
        ResultSet result = db.GetRow("SELECT username FROM users ORDER BY RAND() LIMIT 1");
        
        String mio = null;
		try {
			mio = result.getString(1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        render.set("username", mio);
        
        render.render(response, "test.ftl");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
