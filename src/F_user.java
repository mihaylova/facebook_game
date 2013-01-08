
public class F_user {
	private String id;
	private String points;
	private DB db = new DB();
	
	public F_user(String id, String points){
		this.id=id;
		this.points=points;
		}


public String getId() {
	return id;
}
public String getPoints() {
	return points;
}

//public void PlusPoints(){
//	this.points=this.points+20;
//	db.Do("Update sessions set points="+this.points+"where id="+this.id+";");
//}
//public void MinusPoints(){
//	this.points=this.points-20;
//	db.Do("Update sessions set points="+this.points+"where id="+this.id+";");
//}

}
