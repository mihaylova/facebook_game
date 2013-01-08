import java.io.Serializable;


public class F_user implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String uid;
	private String points;
	private String name;
	private DB db = new DB();
	
	public F_user(String uid, String points, String name){
		this.uid=uid;
		this.points=points;
		this.name=name;
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

public void PlusPoints(){
	int points_int=Integer.parseInt(this.points);
	points_int = points_int + 20;
	this.points=Integer.toString(points_int);
	db.Do("Update sessions set points='"+this.points+"' where uid='"+this.uid+"';");
}
public void MinusPoints(){
	int points_int=Integer.parseInt(this.points);
	points_int = points_int - 20;
	this.points=Integer.toString(points_int);
	db.Do("Update sessions set points='"+this.points+"' where uid='"+this.uid+"';");
}

}
