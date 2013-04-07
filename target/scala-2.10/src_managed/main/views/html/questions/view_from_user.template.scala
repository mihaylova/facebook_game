
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
	
	
   
	<div id="exit"> <p> <a class="btn btn-primary" href="/logout"> Изход</a> </p> </div>
<ul class="nav nav-tabs">
      <li>
       <a href="/admin">Home</a> 
      </li>
     <li><a href="/admin/question/add">Добави въпрос</a></li>
	<li class="active"> <a href="/admin/users_questions"> Преглед на потребоителски въпроси</a></li>
	<li> <a href="/admin/questions"> Преглед на въпроси</a></li>
	<li> <a href="/admin/questions/search"> Търсене</a></li>
		       
    </ul>

"""),_display_(Seq[Any](/*19.2*/if(flash.contains("success"))/*19.31*/ {_display_(Seq[Any](format.raw/*19.33*/("""
               
                 <div class="alert alert-success">
        <button type="button" class="close" data-dismiss="alert">×</button>
         """),_display_(Seq[Any](/*23.11*/flash/*23.16*/.get("success"))),format.raw/*23.31*/("""</div>
            """)))})),format.raw/*24.14*/("""

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
       
       
       """),_display_(Seq[Any](/*45.9*/for(question <- questions) yield /*45.35*/ {_display_(Seq[Any](format.raw/*45.37*/("""
		
		<tr>
		
	
		<td>"""),_display_(Seq[Any](/*50.8*/question/*50.16*/.getUser_uid())),format.raw/*50.30*/("""</td>
		<td>"""),_display_(Seq[Any](/*51.8*/question/*51.16*/.getUsername())),format.raw/*51.30*/("""</td>
		<td>"""),_display_(Seq[Any](/*52.8*/question/*52.16*/.getQuestion())),format.raw/*52.30*/("""</td>
		<td>"""),_display_(Seq[Any](/*53.8*/categories(question.getCategory()))),format.raw/*53.42*/("""</td>
		<td>"""),_display_(Seq[Any](/*54.8*/question/*54.16*/.getRight_answer())),format.raw/*54.34*/("""</td>
		<td>"""),_display_(Seq[Any](/*55.8*/question/*55.16*/.getAnswer1())),format.raw/*55.29*/("""</td>
		<td>"""),_display_(Seq[Any](/*56.8*/question/*56.16*/.getAnswer2())),format.raw/*56.29*/("""</td>
		<td>"""),_display_(Seq[Any](/*57.8*/question/*57.16*/.getAnswer3())),format.raw/*57.29*/("""</td>
		<td> <a  class="btn btn-success btn-mini" href="/admin/users_questions/insert/"""),_display_(Seq[Any](/*58.82*/question/*58.90*/.id)),format.raw/*58.93*/("""">Insert</a></td>
		<td> <a  class="btn btn-danger btn-mini" href="/admin/users_questions/delete/"""),_display_(Seq[Any](/*59.81*/question/*59.89*/.id)),format.raw/*59.92*/("""">Delete</a></td>
		
		</tr>

""")))})),format.raw/*63.2*/("""
       
       
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
                    DATE: Sun Apr 07 21:29:21 EEST 2013
                    SOURCE: /home/r/work/FbPokerQuiz/app/views/questions/view_from_user.scala.html
                    HASH: c6e8149a5ae6bea12aae84c0e54a22527f46b931
                    MATRIX: 769->1|905->60|942->63|984->97|1023->99|1538->579|1576->608|1616->610|1806->764|1820->769|1857->784|1909->804|2417->1277|2459->1303|2499->1305|2557->1328|2574->1336|2610->1350|2658->1363|2675->1371|2711->1385|2759->1398|2776->1406|2812->1420|2860->1433|2916->1467|2964->1480|2981->1488|3021->1506|3069->1519|3086->1527|3121->1540|3169->1553|3186->1561|3221->1574|3269->1587|3286->1595|3321->1608|3444->1695|3461->1703|3486->1706|3620->1804|3637->1812|3662->1815|3724->1846
                    LINES: 26->1|29->1|31->3|31->3|31->3|47->19|47->19|47->19|51->23|51->23|51->23|52->24|73->45|73->45|73->45|78->50|78->50|78->50|79->51|79->51|79->51|80->52|80->52|80->52|81->53|81->53|82->54|82->54|82->54|83->55|83->55|83->55|84->56|84->56|84->56|85->57|85->57|85->57|86->58|86->58|86->58|87->59|87->59|87->59|91->63
                    -- GENERATED --
                */
            