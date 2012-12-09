import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DB {
	private Connection connection = null;
    Statement statement;
    private ResultSet rs = null;

	
	public DB() {
		try {
	         Class.forName("com.mysql.jdbc.Driver");
	        	
	         connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/facebook?useUnicode=true&"
	        		 + "characterEncoding=UTF8&user=root&password=123");
		} catch (Exception e) {
			e.printStackTrace();
        }

//		try {
//			statement = connection.createStatement();
//			
//			statement.execute("SET NAMES utf8");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
	}
	
	public boolean Do(String sql) {
		boolean status = false;

		try {
			statement = connection.createStatement();
			
			status = statement.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return status;
	}
	
	public ResultSet Get(String sql)  {
		ResultSet result = null;
		
		try {
			statement = connection.createStatement();
			
			result = statement.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ResultSet GetRow(String sql) {
		ResultSet result = this.Get(sql);
		
		try {
			result.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
}
