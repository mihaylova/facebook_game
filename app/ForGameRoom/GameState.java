package ForGameRoom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import models.Fb_user;
import models.Game;
import models.GameRoom;
import models.Question;
import ForGameRoom.Member;

import org.codehaus.jackson.JsonNode;

import play.mvc.WebSocket;

public class GameState {
	public static Map<Long, GameState> states= new HashMap<Long, GameState>();
	public Member user_on_turn;
	public Long  game_id;
	public int Bet=0;
	public int MaxBet=9999;
	public int category;
	public Question question=null;
	public int question_choiceAnswer1;
	public int question_choiceAnswer2;
	public int question_choiceAnswer3;
	public int question_choiceAnswer4;
	public long time;
	public int unanswered_question = 0;
	public boolean poker_algorithm=false;
	public boolean answering=false;
	public ArrayList<Member> members;
	public boolean hasVoiceJoker = false;
	public boolean isStart=false;
	public boolean isFinish=false;
	
	public  void addMember(Fb_user user, WebSocket.Out<JsonNode> channel) {
		Member member=new Member(findFreeSlot(), channel, user);
    	this.members.add(member);
	}
	
	public  void removeMember(Long user_uid) { 
    	for(Member member : this.members){
    		if(member.uid == user_uid){
    			Fb_user user = Fb_user.find_by_uid(member.uid);
    			user.SetPoints(member.points);
    			user.SetCoins(member.coins);
    			user.SetMedals(member);
    			this.members.remove(member);
    			Game.find.byId(this.game_id).remove_player(user_uid);
    			break;
    		}
    	}
    	if (members.isEmpty()) {
    		GameRoom.gameRooms.remove(game_id);
    		GameState.Delete(game_id);
    		Game.find.byId(this.game_id).delete();
    		
    	}
    }
	
	public void SetQuestion(Question question){
		this.question= question;
		question_choiceAnswer1 = question.choice_answer1;
		question_choiceAnswer2 = question.choice_answer2;
		question_choiceAnswer3 = question.choice_answer3;
		question_choiceAnswer4 = question.choice_answer4;
	}
	
	
	public void ChoiceAnswer(String answer){
		if(question.getAnswer1().equals(answer)){
			question_choiceAnswer1++;
    	}
    	else if(question.getAnswer2().equals(answer)){
    		question_choiceAnswer2++;
    	}
    	else if(question.getAnswer3().equals(answer)){
    		question_choiceAnswer3++;
    	}
    	else if(question.getRight_answer().equals(answer)){
    		question_choiceAnswer4++;
    	}
	
	}
	
    protected  int findFreeSlot() {
    	for(int i=1; i <= 4; i++){
    		Boolean is_free = true;
    		for(Member member: this.members){
            	
            	if(member.slot == i){
            		is_free = false;
            	}	
            }
    		
    		if (is_free) {
    			return i;
    		}
    	}
    	
		throw new ArrayIndexOutOfBoundsException();
    }
	
	public void SetMaxBet(){
		this.MaxBet = 9999;
		for(Member member: this.members){
			if(!member.fold && !member.wait && !member.out_of_points){
				this.MaxBet=Math.min(this.MaxBet, member.points);
			}
			
		}
	}
	
	public static GameState Get(Long game_id){
		if(states.containsKey(game_id)){
			return states.get(game_id);
			
		}
		else{
			GameState gamestate = new GameState();
			gamestate.members = new ArrayList<Member>();
    		gamestate.game_id=game_id;
    		states.put(game_id, gamestate);
    		return gamestate;
		}
	}

	public  void NextUser_on_turn(){
		int i = this.members.indexOf(this.user_on_turn);
		if(i<(members.size()-1)){
			this.user_on_turn=this.members.get(i+1);
		}
		else{
			this.user_on_turn=this.members.get(0);
		}
		
		if(user_on_turn.fold || user_on_turn.wait || user_on_turn.out_of_points){
			this.NextUser_on_turn();
		}

	}
	
	public static void Delete(Long game_id){
		states.remove(game_id);
	}
}
