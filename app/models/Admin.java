package models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
		        Admin admin =  find.where()
		            .eq("username", username)
		            //.eq("password", password)
		            .findUnique();
		       
					MessageDigest md = null;
					try {
						md = MessageDigest.getInstance("SHA-1");
					} catch (NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				if(admin==null){
					return null;
				}
				else{
					String password_hash = byteArrayToHexString(md.digest(password.getBytes()));
			        if(admin.password.equals(password_hash)){
			        	return admin;
			        }
			        else return null;
				}
		        
		    }


		  public static String byteArrayToHexString(byte[] b) {
			  String result = "";
			  for (int i=0; i < b.length; i++) {
			    result +=
			          Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
			  }
			  return result;
		  }
}
