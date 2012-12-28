
public class User {
	private String name;
	private int id;
	private String photo;
	private int points;
	
	public User(String name, int id){
		this.setName(name);
		this.setId(id);
		this.points=this.getPoints();
	}


public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}


public int getId() {
	return id;
}


public void setId(int id) {
	this.id = id;
}


public String getPhoto() {
	return photo;
}


public void setPhoto(String photo) {
	this.photo = photo;
}


public int getPoints() {
	return points;
}


public void setPoints(int points) {
	this.points = points;
}

public void refreshPoints(){
	//do something
}

}
