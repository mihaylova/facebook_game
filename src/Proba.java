

import java.io.IOException;
import java.io.PrintWriter;

import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


import java.sql.*;


public class Proba extends HttpServlet {
	

	private static final long serialVersionUID = 1L;
	 private Configuration cfg; 
	 
	 Connection connection = null;
     Statement statement;
     ResultSet rs = null;

	public ResultSet MySQLConn(String sql){
		 
	        try {
	         Class.forName("com.mysql.jdbc.Driver");
	        	
	        	
	          connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/facebook?" +"user=root&password=123");
	          statement = connection.createStatement();
	         rs =  statement.executeQuery(sql);
	          
	          
	        } catch (SQLException e) {
	          //out.println("SQLException: " + e.getMessage());
	        } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return rs;
		 
	 } 
	 
	 
	 public void init() {
	        // Initialize the FreeMarker configuration;
	        // - Create a configuration instance
	        cfg = new Configuration();
	        // - Templates are stoted in the WEB-INF/templates directory of the Web app.
	        cfg.setServletContextForTemplateLoading(
	                getServletContext(), "WEB-INF/templates");
	    }
	 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Proba() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  Map root = new HashMap();
     
	     
		   Template t = cfg.getTemplate("test.jsp");
	       
		   
	        response.setContentType("text/html; charset=" + t.getEncoding());
	        PrintWriter out = response.getWriter();
	        
			
	       
		
	    
	          String sql="Select * from users where username='ina'";
	          ResultSet result = MySQLConn(sql);
	     
				try {
					while (result.next()) {
						 
					  
					    root.put("message", result.getString("name"));
					    root.put("message1", result.getString("username"));
				        root.put("message2", result.getString("points"));
		  
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				 try {
						t.process(root, out);
					} catch (TemplateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */ 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
