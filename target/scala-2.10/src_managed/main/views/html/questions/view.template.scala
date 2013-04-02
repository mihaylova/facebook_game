
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
	
	
   
	<div id="exit"> <p> <a class="btn btn-inverse" href="/logout"> Изход</a> </p> </div>
<ul class="nav nav-tabs">
      <li>
       <a href="/admin">Home</a> 
      </li>
     <li><a href="/admin/question/add">Добави въпрос</a></li>
	<li > <a href="/admin/users_questions"> Преглед на потребоителски въпроси</a></li>
	<li class="active"> <a href="/admin/questions"> Преглед на въпроси</a></li>
		       
    </ul>

"""),_display_(Seq[Any](/*18.2*/if(flash.contains("success"))/*18.31*/ {_display_(Seq[Any](format.raw/*18.33*/("""
               
                 <div class="alert alert-success">
        <button type="button" class="close" data-dismiss="alert">×</button>
         """),_display_(Seq[Any](/*22.11*/flash/*22.16*/.get("success"))),format.raw/*22.31*/("""</div>
            """)))})),format.raw/*23.14*/("""

 <table class="table table-hover">
      <thead>
        <tr>
        

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
       
       
       """),_display_(Seq[Any](/*43.9*/for(question <- questions) yield /*43.35*/ {_display_(Seq[Any](format.raw/*43.37*/("""
		
		<tr>
		
	
		
		<td>"""),_display_(Seq[Any](/*49.8*/question/*49.16*/.getQuestion())),format.raw/*49.30*/("""</td>
		<td>"""),_display_(Seq[Any](/*50.8*/categories(question.getCategory()))),format.raw/*50.42*/("""</td>
		<td>"""),_display_(Seq[Any](/*51.8*/question/*51.16*/.getRight_answer())),format.raw/*51.34*/("""</td>
		<td>"""),_display_(Seq[Any](/*52.8*/question/*52.16*/.getAnswer1())),format.raw/*52.29*/("""</td>
		<td>"""),_display_(Seq[Any](/*53.8*/question/*53.16*/.getAnswer2())),format.raw/*53.29*/("""</td>
		<td>"""),_display_(Seq[Any](/*54.8*/question/*54.16*/.getAnswer3())),format.raw/*54.29*/("""</td>
		<td> <a  class="btn btn-success btn-mini" href="/admin/questions/edit/"""),_display_(Seq[Any](/*55.74*/question/*55.82*/.id)),format.raw/*55.85*/("""">Edit</a></td>
		<td> <a  class="btn btn-danger btn-mini" href="/admin/questions/delete/"""),_display_(Seq[Any](/*56.75*/question/*56.83*/.id)),format.raw/*56.86*/("""">Delete</a></td>
		
		</tr>

""")))})),format.raw/*60.2*/("""
       
       
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
                    DATE: Tue Apr 02 16:41:09 EEST 2013
                    SOURCE: /home/r/work/FbPokerQuiz/app/views/questions/view.scala.html
                    HASH: 089ef8e9758d3f05dae6b5ffb8ddbbe2819e0b73
                    MATRIX: 754->1|885->55|922->58|953->81|992->83|1450->506|1488->535|1528->537|1718->691|1732->696|1769->711|1821->731|2266->1141|2308->1167|2348->1169|2409->1195|2426->1203|2462->1217|2510->1230|2566->1264|2614->1277|2631->1285|2671->1303|2719->1316|2736->1324|2771->1337|2819->1350|2836->1358|2871->1371|2919->1384|2936->1392|2971->1405|3086->1484|3103->1492|3128->1495|3254->1585|3271->1593|3296->1596|3358->1627
                    LINES: 26->1|29->1|31->3|31->3|31->3|46->18|46->18|46->18|50->22|50->22|50->22|51->23|71->43|71->43|71->43|77->49|77->49|77->49|78->50|78->50|79->51|79->51|79->51|80->52|80->52|80->52|81->53|81->53|81->53|82->54|82->54|82->54|83->55|83->55|83->55|84->56|84->56|84->56|88->60
                    -- GENERATED --
                */
            