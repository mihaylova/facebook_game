package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import ForGameRoom.Member;

@Entity 
public class Fb_user extends Model {

		
		@Id
		  @Constraints.Min(10)
		  public Long id;
		
		  @Constraints.Required
		  public Long uid;
		  		  
		  public String name;
		 
		  public int points;
		  public String picture;
		  public int coins;
		  public int medals1 = 0;
		  public int medals2 = 0;
		  public int medals3 = 0;
		  public int medals4 = 0;
		  public int medals5 = 0;
		  public int medals6 = 0;
		  public int medals7 = 0;
		  public int medals8 = 0;
		  public int medals9 = 0;
	
		  
		 
		  
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
		  
		  public void SetPoints(int count){
			  this.points = count;
			  save();
		  }
		  
		  public void SetCoins(int count){
			  this.coins = count;
			  save();
		  }
		   public void SetMedals(Member member){
			   this.medals1 = member.medals1;
			   this.medals2 = member.medals2;
			   this.medals3 = member.medals3;
			   this.medals4 = member.medals4;
			   this.medals5 = member.medals5;
			   this.medals6 = member.medals6;
			   this.medals7 = member.medals7;
			   this.medals8 = member.medals8;
			   this.medals9 = member.medals9;
			   save();
		   }
		  
}
