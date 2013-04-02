
package views.html.game

import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
import play.api.templates.PlayMagic._
import models._
import controllers._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.api.i18n._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import views.html._
/**/
object index extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[Long,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(user_uid: Long):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.18*/("""
"""),_display_(Seq[Any](/*2.2*/main_play("Know your trump")/*2.30*/ {_display_(Seq[Any](format.raw/*2.32*/("""
	<div id="fb-root"></div>  
   	<a href="/home/"><img class="exit" src=""""),_display_(Seq[Any](/*4.46*/routes/*4.52*/.Assets.at("images/exit.jpg"))),format.raw/*4.81*/(""""></a>
	<div id="users-table"></div>
	<div id="finish-message" style="display:none;"> </div>
	<div id="message-out-of-points" ></div>
	<div id="category" style="display:none;"></div>
	<div id="timer"></div>
	<div id="timer1"> </div>
	<div id="timer2"> </div>
	<div id="timer3"> </div>
	<div id="timer4"> </div>
	<div id="coins"><img src="/assets/images/coins.gif"><span></span></div>
		<div class="question" style="display:none;"> 
			
			
			<div class="question-cont"> 
				<h4 class="question"></h4>
			</div> 
			<div class="answers"> 
				<span id="flag-answerA"> A </span>
				<span id="choiceA" style="display:none;"> </span>
				<button class="btn answer" id="answerA"></button>
				<span  id="flag-answerB" > B </span>
				<span id="choiceB" style="display:none;"></span>
				<button class="btn answer" id="answerB"></button>
				<span id="flag-answerC"> C </span>
				<span id="choiceC" style="display:none;"></span>
				<button class="btn answer" id="answerC"></button>
				<span id="flag-answerD"> D </span>
				<span id="choiceD" style="display:none;"></span>
				<button class="btn answer" id="answerD"></button>
			</div>
		</div>
		
	
		<div class="jokers">  
			<button class="btn joker" id="joker50">50/50</button>
			<button class="btn joker" id="joker_voice" style="display:none;">Гласа на мнозинството</button>
		</div>
		<span id="message" style="display:none;"> </span>
		<span id="bet" style="display:none;"></span>
		
		<div class="bet-btn" style="display:none;">
			<span class="range">
				
				<input type="range" name="raise" style="width:150px;" min="1" max="100" value="1">
				<output  class="range" for="raise" onforminput="value = raise.valueAsNumber;"></output> 
			</span>
			<button class="btn btn-block bet" id="raise">Заложи </button><br>
			<span class="bet"></span>
			<button class="btn btn-block bet" id="call">Отговори</button>
			<button class="btn btn-block bet" id="fold">Откажи</button>
		</div>
		
		
		<div class=:"joker"> </div>

	<div style="top:180px;left:300px;position: absolute;">
		<ul id="status">
		</ul>
	</div>
	
	
	<script type="text/template" id="users-table-template">
		<% 
			var number_of_slots = 4 ;
			for(var i = 1; i <= number_of_slots; i++) """),format.raw/*69.46*/("""{"""),format.raw/*69.47*/("""
				// try to find player for this slot
				var player = _.filter(members, function(member)"""),format.raw/*71.52*/("""{"""),format.raw/*71.53*/(""" return member.slot == i; """),format.raw/*71.79*/("""}"""),format.raw/*71.80*/(""")

				if (player.length) """),format.raw/*73.24*/("""{"""),format.raw/*73.25*/("""
					var member = player[0];
		%>
					<div id="member-<%= member.uid %>" >
						<span class="player<%= member.slot %> name"><%= member.name %></span>
						<div class="player<%= member.slot %>"> 
							<span class="points"><%= member.points %></span>
							<img class="player" src="<%= member.picture %>">
							<div class="user-answer" style="display:none;">
								<img id="ballon<%= member.slot %>" src="/assets/images/ballons/<%= member.slot %>.gif">
								<span id="answer<%= member.slot %>" class="answer"></span>
								<span id="time<%= member.slot %>" class="time" ></span>
								
							</div>
							
						</div>
					</div>
				<% """),format.raw/*90.8*/("""}"""),format.raw/*90.9*/(""" else """),format.raw/*90.15*/("""{"""),format.raw/*90.16*/(""" %>		
					<div class="no-player-slot">
						<span class="player<%= i %> name">&nbsp;</span><br>
						<div class="player<%= i %>"> 
							<img class="player" src=""""),_display_(Seq[Any](/*94.34*/routes/*94.40*/.Assets.at("images/noplayer.jpg"))),format.raw/*94.73*/("""">
							
						
						</div>
					</div>
				<% """),format.raw/*99.8*/("""}"""),format.raw/*99.9*/(""" %>	
		<% """),format.raw/*100.6*/("""}"""),format.raw/*100.7*/(""" %>
		
	</script>
	<script type="text/template" id="category-template">
		
		<div class="category" > 
			<img style="" src="/assets/images/category/<%= category %>.gif">  
		</div>
	</script>
	<script type="text/template" id="message-template">
		
		<div class="message"><%=message %> </div>
	</script>
    <script src=""""),_display_(Seq[Any](/*113.19*/routes/*113.25*/.Assets.at("javascripts/main.js"))),format.raw/*113.58*/("""" type="text/javascript"></script>
	<script src="//connect.facebook.net/en_US/all.js"></script>  

	<script>
		FB.init("""),format.raw/*117.11*/("""{"""),format.raw/*117.12*/("""appId: "249406605187123", status: true, cookie: true"""),format.raw/*117.64*/("""}"""),format.raw/*117.65*/(""");
		window.game = new Game("""),_display_(Seq[Any](/*118.27*/user_uid)),format.raw/*118.35*/(""").start();
	</script>
""")))})))}
    }
    
    def render(user_uid:Long): play.api.templates.Html = apply(user_uid)
    
    def f:((Long) => play.api.templates.Html) = (user_uid) => apply(user_uid)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Tue Apr 02 13:25:08 EEST 2013
                    SOURCE: /home/r/work/facebook_game/app/views/game/index.scala.html
                    HASH: c6ca854f5e0e85a4c5ef2691dcb000460041d247
                    MATRIX: 726->1|819->17|855->19|891->47|930->49|1039->123|1053->129|1103->158|3344->2371|3373->2372|3493->2464|3522->2465|3576->2491|3605->2492|3659->2518|3688->2519|4371->3175|4399->3176|4433->3182|4462->3183|4665->3350|4680->3356|4735->3389|4812->3439|4840->3440|4878->3450|4907->3451|5265->3772|5281->3778|5337->3811|5485->3930|5515->3931|5596->3983|5626->3984|5692->4013|5723->4021
                    LINES: 26->1|29->1|30->2|30->2|30->2|32->4|32->4|32->4|97->69|97->69|99->71|99->71|99->71|99->71|101->73|101->73|118->90|118->90|118->90|118->90|122->94|122->94|122->94|127->99|127->99|128->100|128->100|141->113|141->113|141->113|145->117|145->117|145->117|145->117|146->118|146->118
                    -- GENERATED --
                */
            