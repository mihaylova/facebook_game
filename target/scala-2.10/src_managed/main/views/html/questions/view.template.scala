
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
object view extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[List[Question],Array[String],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(questions: List[Question], categories: Array[String]):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.56*/("""

"""),_display_(Seq[Any](/*3.2*/main_admin("Questions")/*3.25*/ {_display_(Seq[Any](format.raw/*3.27*/("""
	
	
   
	<div id="exit"> <p> <a class="btn btn-primary" href="/logout"> Изход</a> </p> </div>
<ul class="nav nav-tabs">
      <li>
       <a href="/admin">Home</a> 
      </li>
     <li><a href="/admin/question/add">Добави въпрос</a></li>
	<li > <a href="/admin/users_questions"> Преглед на потребоителски въпроси</a></li>
	<li class="active"> <a href="/admin/questions"> Преглед на въпроси</a></li>
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
        

<th class="question">Въпрос</th>
<th class="category">Категория</th>
<th class="answer">Верен отговор</th>
<th class="answer"> Отговор1</th>
<th class="answer">Отговор2</th>
<th class="answer">Отговор3</th>
<th class="button">Промени</th>
<th class="button">Изтрий</th>
        </tr>
      </thead>
      <tbody>
       
       
       """),_display_(Seq[Any](/*44.9*/for(question <- questions) yield /*44.35*/ {_display_(Seq[Any](format.raw/*44.37*/("""
		
		<tr>
		
	
		
		<td>"""),_display_(Seq[Any](/*50.8*/question/*50.16*/.getQuestion())),format.raw/*50.30*/("""</td>
		<td>"""),_display_(Seq[Any](/*51.8*/categories(question.getCategory()))),format.raw/*51.42*/("""</td>
		<td>"""),_display_(Seq[Any](/*52.8*/question/*52.16*/.getRight_answer())),format.raw/*52.34*/("""</td>
		<td>"""),_display_(Seq[Any](/*53.8*/question/*53.16*/.getAnswer1())),format.raw/*53.29*/("""</td>
		<td>"""),_display_(Seq[Any](/*54.8*/question/*54.16*/.getAnswer2())),format.raw/*54.29*/("""</td>
		<td>"""),_display_(Seq[Any](/*55.8*/question/*55.16*/.getAnswer3())),format.raw/*55.29*/("""</td>
		<td> <a  class="btn btn-success btn-mini" href="/admin/questions/edit/"""),_display_(Seq[Any](/*56.74*/question/*56.82*/.id)),format.raw/*56.85*/("""">Edit</a></td>
		<td> <a  class="btn btn-danger btn-mini" href="/admin/questions/delete/"""),_display_(Seq[Any](/*57.75*/question/*57.83*/.id)),format.raw/*57.86*/("""">Delete</a></td>
		
		</tr>

""")))})),format.raw/*61.2*/("""
       
       
      </tbody>
    </table>



""")))})))}
    }
    
    def render(questions:List[Question],categories:Array[String]): play.api.templates.Html = apply(questions,categories)
    
    def f:((List[Question],Array[String]) => play.api.templates.Html) = (questions,categories) => apply(questions,categories)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Sun Apr 07 21:29:21 EEST 2013
                    SOURCE: /home/r/work/FbPokerQuiz/app/views/questions/view.scala.html
                    HASH: 8390b8a4a86c1cb519cec3fb137f1f1e2ca8e3f5
                    MATRIX: 754->1|885->55|922->58|953->81|992->83|1508->564|1546->593|1586->595|1776->749|1790->754|1827->769|1879->789|2325->1200|2367->1226|2407->1228|2468->1254|2485->1262|2521->1276|2569->1289|2625->1323|2673->1336|2690->1344|2730->1362|2778->1375|2795->1383|2830->1396|2878->1409|2895->1417|2930->1430|2978->1443|2995->1451|3030->1464|3145->1543|3162->1551|3187->1554|3313->1644|3330->1652|3355->1655|3417->1686
                    LINES: 26->1|29->1|31->3|31->3|31->3|47->19|47->19|47->19|51->23|51->23|51->23|52->24|72->44|72->44|72->44|78->50|78->50|78->50|79->51|79->51|80->52|80->52|80->52|81->53|81->53|81->53|82->54|82->54|82->54|83->55|83->55|83->55|84->56|84->56|84->56|85->57|85->57|85->57|89->61
                    -- GENERATED --
                */
            