import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;


public class F_user implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String uid;
	private String points;
	private String name;
	private String AcessToken;
	private Boolean isNew = false;
	private DB db = new DB();
	
	public F_user(String uid){
		this.uid=uid;
		String sql="Select * from users where uid='"+this.uid+"';";
		ResultSet result = db.GetRow(sql);
		

		try {
			int numRows = result.getRow(); 
			if(numRows==0){
				db.Do("Insert into users set uid='"+this.uid+"';");
				result = db.GetRow(sql);
			}
			else{
				Boolean name_is_zero=result.getString("name").equals("0");
				
				Boolean points_is_null=result.getString("points")==null;
				
				
				if(name_is_zero && points_is_null){
					isNew=true;
				}
				else{
					this.name=result.getString("name");
					this.points=result.getString("points");
				}
			}
		} catch (SQLException e) {
			//obrabotka na izkliu4eniata
	
			
		}
		}


public String getUID() {
	return uid;
}
public String getPoints() {
	return points;
}

public String getName() {
	return name;
}
public String getAcessToken() {
	return AcessToken;
}

public void setName(String name) {
	this.name=name;
	db.Do("Update users set name='"+this.name+"' where uid='"+this.uid+"';");
}

public void setPoints(String points) {
	this.points=points;
	db.Do("Update users set points='"+this.points+"' where uid='"+this.uid+"';");
}

public void setAcessToken(String AcessToken) {
	this.AcessToken=AcessToken;
	db.Do("Update users set AcessToken='"+this.AcessToken+"' where uid='"+this.uid+"';");
}
public void PlusPoints(){
	int points_int=Integer.parseInt(this.points);
	points_int = points_int + 20;
	this.points=Integer.toString(points_int);
	db.Do("Update users set points='"+this.points+"' where uid='"+this.uid+"';");
}
public void MinusPoints(){
	int points_int=Integer.parseInt(this.points);
	points_int = points_int - 20;
	this.points=Integer.toString(points_int);
	db.Do("Update users set points='"+this.points+"' where uid='"+this.uid+"';");
}

public Boolean isNew(){
	return isNew;
}
}
