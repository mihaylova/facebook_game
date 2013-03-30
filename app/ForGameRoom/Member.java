package ForGameRoom;

import java.util.ArrayList;



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
	
	
	 public Member(Long uid, int slot, WebSocket.Out<JsonNode> channel, int points, String name, int coins) {
         this.uid = uid;
         this.slot = slot;
         this.channel=channel;
         this.points = points;
         this.name = name;
         this.coins = coins;
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
