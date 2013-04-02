package Algorithms;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;




import models.Game;
import models.GameRoom;
import models.Question;
import models.GameRoom.Call;
import models.GameRoom.Fold;
import models.GameRoom.Start;
import models.GameRoom.AskQuestion;

import ForGameRoom.GameState;
import ForGameRoom.Member;
import models.GameRoom.NextOnTurn;
import play.libs.Akka;

import akka.actor.*;
import akka.dispatch.*;



import Algorithms.Notify;


import play.mvc.*;
import play.mvc.WebSocket.Out;
import play.libs.*;
import play.libs.F.*;
import scala.Int;
import scala.concurrent.Await;
import scala.concurrent.duration.Duration;
import akka.util.*;


import scala.concurrent.duration.*;




import org.codehaus.jackson.*;
import org.codehaus.jackson.node.*;

import static java.util.concurrent.TimeUnit.*;

import static akka.pattern.Patterns.ask;


import org.joda.time.Seconds;

import com.google.common.primitives.Longs;

import java.util.*;

public class PokerAlgorithm {
	public static Map<Long, Cancellable> timers= new HashMap<Long, Cancellable>();
	
  	
	
	public static void onStart(ActorRef gameRoom, GameState gamestate){
		gamestate.Bet=0; 	 	
		for(Member member: gamestate.members){
			member.user_bet=0;
			member.MinusPoints(1);
			gamestate.Bet = gamestate.Bet+1;
			member.UnCallBet=0;
			member.wait=false;
			member.fold=false;
			member.turn=0;
		}
		
		gamestate.user_on_turn = gamestate.members.get(0);
		gamestate.SetMaxBet();
		gamestate.poker_algorithm=true;
   	 	gamestate.category =  Question.RandomCategory();
   	 	gamestate.question = null;
   	 	
   	 	
   	 	gamestate.unanswered_question=0;
		Notify.OnCategory(gamestate.category, gamestate);
		
		Cancellable timer = SetTimer(gameRoom, gamestate);
		timers.put(gamestate.game_id, timer);
	}
	
	public static void onRaise(ActorRef gameRoom, Member member, int bet, GameState gamestate){
		if(!timers.get(gamestate.game_id).isCancelled()){
			timers.get(gamestate.game_id).cancel();
		}
		timers.remove(gamestate.game_id);
		gamestate.Bet=gamestate.Bet+bet;
		member.turn++;
		for(Member othermember: gamestate.members){
			if(othermember.uid!=member.uid){
				othermember.UnCallBet=othermember.UnCallBet+(bet-member.UnCallBet);
			}
		}
		
		member.MinusPoints(bet);
		gamestate.SetMaxBet();
		int sum = bet-member.UnCallBet;
		member.UnCallBet=0;
    	Notify.OnCallOrRaise(member.name+" наддаде с "+ sum + " точки!", gamestate, member);
    	gamestate.NextUser_on_turn();

		Cancellable timer = SetTimer(gameRoom, gamestate);
		timers.put(gamestate.game_id, timer);
	}
	
	public  static void onFold(ActorRef gameRoom, Member member, final GameState gamestate){
		if(!timers.get(gamestate.game_id).isCancelled()){
			timers.get(gamestate.game_id).cancel();
		}
		timers.remove(gamestate.game_id);
		member.UnCallBet=0;
		member.fold=true;
		member.turn++;
		Notify.OnFold(member.name+ " се отказа от наддаването!", gamestate.members);
		if(isFinishFold(gamestate.members)){
			
			new Timer().schedule(new TimerTask() {          
			    @Override
			    public void run() {
			    	FinishFold(gamestate);       
			    }
			}, 1000);

			
		
					
		}
		else if(isFinishPass(gamestate.members)){
			FinishPass(gamestate);
		}
		else{
			gamestate.NextUser_on_turn();

			Cancellable timer = SetTimer(gameRoom, gamestate);
			timers.put(gamestate.game_id, timer);
		}
	}
	
	public static void onCall(ActorRef gameRoom, Member member, GameState gamestate){
		if(!timers.get(gamestate.game_id).isCancelled()){
			timers.get(gamestate.game_id).cancel();
		}
		timers.remove(gamestate.game_id);
		gamestate.Bet=gamestate.Bet+member.UnCallBet;
		member.MinusPoints(member.UnCallBet);
		member.UnCallBet=0;
		member.turn++;
		gamestate.SetMaxBet();
		Notify.OnCallOrRaise(member.name + " отговори на наддаването!", gamestate, member);
		 if(isFinishPass(gamestate.members)){
			FinishPass(gamestate);
		}
		else{
			gamestate.NextUser_on_turn();

			Cancellable timer = SetTimer(gameRoom, gamestate);
			timers.put(gamestate.game_id, timer);
		}
		
	}
	
	private static Cancellable SetTimer(ActorRef gameRoom, GameState gamestate){
		Cancellable timer;
		gameRoom.tell(new NextOnTurn(gamestate.game_id, gamestate), gameRoom);
		
  		if(gamestate.user_on_turn.UnCallBet==0){
			timer =Akka.system().scheduler().scheduleOnce(
					Duration.create(7, SECONDS),
    				gameRoom,
    				new Call(gamestate.user_on_turn, gamestate.game_id, gamestate),
    				Akka.system().dispatcher()
    		);
		}
		else{
			timer =Akka.system().scheduler().scheduleOnce(
					Duration.create(7, SECONDS),
    				gameRoom,
    				new Fold(gamestate.user_on_turn, gamestate.game_id, gamestate),
    				Akka.system().dispatcher()
    		);
		}
		return timer;
	}

	private  static Boolean isFinishFold(ArrayList<Member> members){
		Boolean finish= false;
		int sum=0;
		for(Member member: members){
			if(member.fold || member.wait){
				sum++;
			}
		}
		if(sum==(members.size()-1)){
			finish=true;
		}
		return finish;
	}
	
	private  static Boolean isFinishPass(ArrayList<Member> members){
		Boolean finish= false;
		int un_call_bets=0;
		int turns=0;
		for(Member member: members){
			un_call_bets=un_call_bets+member.UnCallBet;
			turns=turns+member.turn;
		}
		if(un_call_bets==0 && turns>=members.size()){
			finish=true;
		}
		
		return finish;
	}
	
	private static void FinishFold(GameState gamestate){
		gamestate.poker_algorithm = false;
		Member member = Member.Find_unfold(gamestate.members);
		int sumpoints = member.UnCallBet + gamestate.Bet;
		member.PlusPoints(sumpoints);
		Notify.OnWin(member.uid, member.name, member.points, gamestate.members);
		Cancellable timer = Akka.system().scheduler().scheduleOnce(
				Duration.create(3, SECONDS),
				GameRoom.gameRooms.get(gamestate.game_id),
				new Start(gamestate.game_id, gamestate),
				Akka.system().dispatcher()
		);
		timers.put(gamestate.game_id, timer);
	}
	
	private static void FinishPass(GameState gamestate){
		gamestate.poker_algorithm = false;
		Cancellable timer = Akka.system().scheduler().scheduleOnce(
				Duration.create(1, SECONDS),
				GameRoom.gameRooms.get(gamestate.game_id),
				//*************************************************
				new AskQuestion(Game.Find(gamestate.game_id)),
				Akka.system().dispatcher()
		);
		timers.put(gamestate.game_id, timer);
    	
    }
}
