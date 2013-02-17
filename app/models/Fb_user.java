package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity 
public class Fb_user extends Model {

		
		@Id
		  @Constraints.Min(10)
		  public Long id;
		
		  @Constraints.Required
		  public Long uid;
		  		  
		  public String name;
		  public String AcessToken;
		  public int points;
	
		  
		 
		  
		  public static Finder<Long,Fb_user> find = new Finder<Long,Fb_user>(
		    Long.class, Fb_user.class
		  ); 

		  public static Fb_user find_by_uid(Long uid) {
	        return find.where()
	            .eq("uid", uid)
	            .findUnique();
		   }
		  
		  public void PlusPoints(int count){
			  this.points=this.points+count;
			  save();
		  }
		  
		  public void MinusPoints(int count){
			  this.points=this.points-count;
			  save();
		  }
}
