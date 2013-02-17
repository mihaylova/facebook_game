package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity 
public class Admin extends Model {

		
		@Id
		  @Constraints.Min(10)
		  public Long id;
		
		  @Constraints.Required
		  public String username;
		  		  
		  public String password;
		 
	
		  
		 
		  
		  public static Finder<Long, Admin> find = new Finder<Long,Admin>(
		    Long.class, Admin.class
		  ); 
		  
		  public static Admin authenticate(String username, String password) {
		        return find.where()
		            .eq("username", username)
		            .eq("password", password)
		            .findUnique();
		    }
}
