package ForGameRoom;

import java.util.ArrayList;



import models.Fb_user;

import org.codehaus.jackson.JsonNode;

import play.mvc.WebSocket;

public class Member {
	public final Long uid;
	public final int slot;
	public final WebSocket.Out<JsonNode> channel;
	public int UnCallBet=0;
	public boolean fold=false;
	public boolean wait = true;
	public int turn=0;
	public String answer = null;
	public long time_on_answering=9999;
	public String button = null;
	public int points;
	public String name;
	public boolean out_of_points = false;
	public int user_bet=0;
	public boolean usejoker=false;
	public int coins;
	public int medals1;
	public int medals2;
	public int medals3;
	public int medals4;
	public int medals5;
	public int medals6;
	public int medals7;
	public int medals8;
	public int medals9;

	
	 public Member(int slot, WebSocket.Out<JsonNode> channel, Fb_user user) {
         this.uid = user.uid;
         this.slot = slot;
         this.channel=channel;
         this.points = user.points;
         this.name = user.name;
         this.coins = user.coins;
         this.medals1 = user.medals1;
		 this.medals2 = user.medals2;
		 this.medals3 = user.medals3;
		 this.medals4 = user.medals4;
		 this.medals5 = user.medals5;
		 this.medals6 = user.medals6;
		 this.medals7 = user.medals7;
		 this.medals8 = user.medals8;
		 this.medals9 = user.medals9;
     }
	 
	 
	 public void PlusMedalItem(int i){
		 if(i==1){
			 this.medals1++;
		 }
		 else if(i==2){
			 this.medals2++;
		 }
		 else if(i==3){
			 this.medals3++;
		 }
		 else if(i==4){
			 this.medals4++;
		 }
		 else if(i==5){
			 this.medals5++;
		 }
		 else if(i==6){
			 this.medals6++;
		 }
		 else if(i==7){
			 this.medals7++;
		 }
		 else if(i==8){
			 this.medals8++;
		 }
		 else if(i==9){
			 this.medals9++;
		 }
	 }
	 
	 public String isNewMedal(int i){
		 String medal = "no";
		 if(i==1){
			 if(this.medals1==10){
				 medal="bronze";
			 }
			 else if(this.medals1==20){
				 medal="silver";
			 }
			 else if(this.medals1==50){
				 medal="gold";
			 }
		 }
		 else if(i==2){
			 if(this.medals2==10){
				 medal="bronze";
			 }
			 else if(this.medals2==20){
				 medal="silver";
			 }
			 else if(this.medals2==50){
				 medal="gold";
			 }
		 }
		 else if(i==3){
			 if(this.medals3==10){
				 medal="bronze";
			 }
			 else if(this.medals3==20){
				 medal="silver";
			 }
			 else if(this.medals3==50){
				 medal="gold";
			 }
		 }
		 else if(i==4){
			 if(this.medals4==10){
				 medal="bronze";
			 }
			 else if(this.medals4==20){
				 medal="silver";
			 }
			 else if(this.medals4==50){
				 medal="gold";
			 }
		 }
		 else if(i==5){
			 if(this.medals5==10){
				 medal="bronze";
			 }
			 else if(this.medals5==20){
				 medal="silver";
			 }
			 else if(this.medals5==50){
				 medal="gold";
			 }
		 }
		 else if(i==6){
			 if(this.medals6==10){
				 medal="bronze";
			 }
			 else if(this.medals6==20){
				 medal="silver";
			 }
			 else if(this.medals6==50){
				 medal="gold";
			 }
		 }
		 else if(i==7){
			 if(this.medals7==10){
				 medal="bronze";
			 }
			 else if(this.medals7==20){
				 medal="silver";
			 }
			 else if(this.medals7==50){
				 medal="gold";
			 }
		 }
		 else if(i==8){
			 if(this.medals8==10){
				 medal="bronze";
			 }
			 else if(this.medals8==20){
				 medal="silver";
			 }
			 else if(this.medals8==50){
				 medal="gold";
			 }
		 }
		 else if(i==9){
			 if(this.medals9==10){
				 medal="bronze";
			 }
			 else if(this.medals9==20){
				 medal="silver";
			 }
			 else if(this.medals9==50){
				 medal="gold";
			 }
		 }
		 return medal;
	 }
	 
	 public static Member Find_by_uid(Long uid, ArrayList<Member> members){
		 for(Member member: members){
			 if(member.uid.equals(uid)){
				 return member;
   			 }
		 }
		 return null;
	 }
	 
	 public static Member Find_unfold(ArrayList<Member> members){
		 for(Member member: members){
			 if(!member.fold && !member.wait && !member.out_of_points){
				 return member;
			 }
		 }
		 return null;
	 }
	 
	 public void PlusPoints(int count){
		 this.points= this.points + count;
	 }
	 
	 public void MinusPoints(int count){
		 this.points = this.points - count;
		 this.user_bet = this.user_bet + count;
	 }

}
