
package views.html.questions

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
object view_from_user extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[List[User_question],Array[String],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(questions: List[User_question], categories: Array[String]):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.61*/("""

"""),_display_(Seq[Any](/*3.2*/main_admin("Questions from users")/*3.36*/ {_display_(Seq[Any](format.raw/*3.38*/("""
	
	
   
	<div id="exit"> <p> <a class="btn btn-inverse" href="/logout"> Изход</a> </p> </div>
<ul class="nav nav-tabs">
      <li>
       <a href="/admin">Home</a> 
      </li>
     <li><a href="/admin/question/add">Добави въпрос</a></li>
	<li class="active"> <a href="/admin/users_questions"> Преглед на потребоителски въпроси</a></li>
	<li> <a href="/admin/questions"> Преглед на въпроси</a></li>
		       
    </ul>

"""),_display_(Seq[Any](/*18.2*/if(flash.contains("success"))/*18.31*/ {_display_(Seq[Any](format.raw/*18.33*/("""
               
                 <div class="alert alert-success">
        <button type="button" class="close" data-dismiss="alert">×</button>
         """),_display_(Seq[Any](/*22.11*/flash/*22.16*/.get("success"))),format.raw/*22.31*/("""</div>
            """)))})),format.raw/*23.14*/("""

 <table class="table table-hover">
      <thead>
        <tr>
        
<th class="user">UID_user</th>
<th class="user">Потребител</th>
<th class="question">Въпрос</th>
<th class="category">Категория</th>
<th class="answer">Верен отговор</th>
<th class="answer"> Отговор1</th>
<th class="answer">Отговор2</th>
<th class="answer">Отговор3</th>
<th class="button">Добави</th>
<th class="button">Изтрий</th>
        </tr>
      </thead>
      <tbody>
       
       
       """),_display_(Seq[Any](/*44.9*/for(question <- questions) yield /*44.35*/ {_display_(Seq[Any](format.raw/*44.37*/("""
		
		<tr>
		
	
		<td>"""),_display_(Seq[Any](/*49.8*/question/*49.16*/.getUser_uid())),format.raw/*49.30*/("""</td>
		<td>"""),_display_(Seq[Any](/*50.8*/question/*50.16*/.getUsername())),format.raw/*50.30*/("""</td>
		<td>"""),_display_(Seq[Any](/*51.8*/question/*51.16*/.getQuestion())),format.raw/*51.30*/("""</td>
		<td>"""),_display_(Seq[Any](/*52.8*/categories(question.getCategory()))),format.raw/*52.42*/("""</td>
		<td>"""),_display_(Seq[Any](/*53.8*/question/*53.16*/.getRight_answer())),format.raw/*53.34*/("""</td>
		<td>"""),_display_(Seq[Any](/*54.8*/question/*54.16*/.getAnswer1())),format.raw/*54.29*/("""</td>
		<td>"""),_display_(Seq[Any](/*55.8*/question/*55.16*/.getAnswer2())),format.raw/*55.29*/("""</td>
		<td>"""),_display_(Seq[Any](/*56.8*/question/*56.16*/.getAnswer3())),format.raw/*56.29*/("""</td>
		<td> <a  class="btn btn-success btn-mini" href="/admin/users_questions/insert/"""),_display_(Seq[Any](/*57.82*/question/*57.90*/.id)),format.raw/*57.93*/("""">Insert</a></td>
		<td> <a  class="btn btn-danger btn-mini" href="/admin/users_questions/delete/"""),_display_(Seq[Any](/*58.81*/question/*58.89*/.id)),format.raw/*58.92*/("""">Delete</a></td>
		
		</tr>

""")))})),format.raw/*62.2*/("""
       
       
      </tbody>
    </table>



""")))})))}
    }
    
    def render(questions:List[User_question],categories:Array[String]): play.api.templates.Html = apply(questions,categories)
    
    def f:((List[User_question],Array[String]) => play.api.templates.Html) = (questions,categories) => apply(questions,categories)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Tue Apr 02 19:52:42 EEST 2013
                    SOURCE: /home/r/work/FbPokerQuiz/app/views/questions/view_from_user.scala.html
                    HASH: 1532ba616d1d001dbed4b44a47a124c2599e7db5
                    MATRIX: 769->1|905->60|942->63|984->97|1023->99|1480->521|1518->550|1558->552|1748->706|1762->711|1799->726|1851->746|2359->1219|2401->1245|2441->1247|2499->1270|2516->1278|2552->1292|2600->1305|2617->1313|2653->1327|2701->1340|2718->1348|2754->1362|2802->1375|2858->1409|2906->1422|2923->1430|2963->1448|3011->1461|3028->1469|3063->1482|3111->1495|3128->1503|3163->1516|3211->1529|3228->1537|3263->1550|3386->1637|3403->1645|3428->1648|3562->1746|3579->1754|3604->1757|3666->1788
                    LINES: 26->1|29->1|31->3|31->3|31->3|46->18|46->18|46->18|50->22|50->22|50->22|51->23|72->44|72->44|72->44|77->49|77->49|77->49|78->50|78->50|78->50|79->51|79->51|79->51|80->52|80->52|81->53|81->53|81->53|82->54|82->54|82->54|83->55|83->55|83->55|84->56|84->56|84->56|85->57|85->57|85->57|86->58|86->58|86->58|90->62
                    -- GENERATED --
                */
            