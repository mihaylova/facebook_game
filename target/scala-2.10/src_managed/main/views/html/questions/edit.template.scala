
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
object edit extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[Question,Array[String],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(question: Question, categories: Array[String]):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._


Seq[Any](format.raw/*1.49*/("""

"""),format.raw/*5.1*/("""
"""),_display_(Seq[Any](/*6.2*/main_admin("Edit Question")/*6.29*/ {_display_(Seq[Any](format.raw/*6.31*/("""
<div id="exit"> <p> <a class="btn btn-primary" href="/logout"> Изход</a> </p> </div>
<ul class="nav nav-tabs">
      <li>
       <a href="/admin">Home</a> 
      </li>
     <li ><a href="/admin/question/add">Добави въпрос</a></li>
	<li> <a href="/admin/users_questions"> Преглед на потребоителски въпроси</a></li>
	<li> <a href="/admin/questions"> Преглед на въпроси</a></li>
	
    </ul>  
    
 
      <div class="row"></div>
    <div class="row"></div>
    <div class="row"></div>
    <div class="row-fluid">
      <div class="span10 offset1">
        """),_display_(Seq[Any](/*24.10*/helper/*24.16*/.form(action = routes.Questions.editQuestion)/*24.61*/ {_display_(Seq[Any](format.raw/*24.63*/("""
          <h2>Добавете въпрос:</h2>
          <div class="row-fluid">
            <div class="span4">
              <span class="help-block">Въпрос</span>
              <textarea class="input-block-level" rows="6" name="question"
              >"""),_display_(Seq[Any](/*30.17*/question/*30.25*/.getQuestion())),format.raw/*30.39*/("""</textarea>
            </div>
            <div class="span4">
              <span class="help-block">Категория</span>
              <select name="category">
                <option value=""""),_display_(Seq[Any](/*35.33*/question/*35.41*/.getCategory())),format.raw/*35.55*/("""">"""),_display_(Seq[Any](/*35.58*/categories(question.getCategory()))),format.raw/*35.92*/("""</option>
                
             
      
      		"""),_display_(Seq[Any](/*39.10*/for(a <- 1 until 10) yield /*39.30*/{_display_(Seq[Any](format.raw/*39.31*/("""
        		 """),_display_(Seq[Any](/*40.13*/if(a!=question.getCategory())/*40.42*/{_display_(Seq[Any](format.raw/*40.43*/("""
        		 	<option value=""""),_display_(Seq[Any](/*41.29*/a)),format.raw/*41.30*/("""">"""),_display_(Seq[Any](/*41.33*/categories(a))),format.raw/*41.46*/("""</option>
        		 """)))})),format.raw/*42.13*/("""
      		""")))})),format.raw/*43.10*/("""
                
                
               
              </select>
              <span class="help-block">Верен отговор</span>
              <input type="text" class="input-block-level" value=""""),_display_(Seq[Any](/*49.68*/question/*49.76*/.getRight_answer)),format.raw/*49.92*/(""""
              name="right_answer"> 
            </div>
            <div class="span4">
              <span class="help-block">Други отговори</span>
              <input type="text" class="input-block-level input-medium" value=""""),_display_(Seq[Any](/*54.81*/question/*54.89*/.getAnswer1)),format.raw/*54.100*/(""""
              name="answer1">
              <input type="text" class="input-block-level input-medium" value=""""),_display_(Seq[Any](/*56.81*/question/*56.89*/.getAnswer2)),format.raw/*56.100*/("""" name="answer2">
              <input type="text" class="input-block-level input-medium" value=""""),_display_(Seq[Any](/*57.81*/question/*57.89*/.getAnswer3)),format.raw/*57.100*/("""" name="answer3">
              <div class="form-actions">
                <button type="submit" class="btn btn-primary">Submit</button>
                <input type="reset" class="btn" value="Reset"> 
              </div>
            </div>
          </div>
       """)))})),format.raw/*64.9*/("""
      </div>
    </div>
    
    
    
""")))})))}
    }
    
    def render(question:Question,categories:Array[String]): play.api.templates.Html = apply(question,categories)
    
    def f:((Question,Array[String]) => play.api.templates.Html) = (question,categories) => apply(question,categories)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Tue Apr 02 15:58:43 EEST 2013
                    SOURCE: /home/r/work/FbPokerQuiz/app/views/questions/edit.scala.html
                    HASH: c5669d1573c43f6c54396785abf05391a565f319
                    MATRIX: 748->1|922->48|950->101|986->103|1021->130|1060->132|1652->688|1667->694|1721->739|1761->741|2044->988|2061->996|2097->1010|2323->1200|2340->1208|2376->1222|2415->1225|2471->1259|2564->1316|2600->1336|2639->1337|2688->1350|2726->1379|2765->1380|2830->1409|2853->1410|2892->1413|2927->1426|2981->1448|3023->1458|3261->1660|3278->1668|3316->1684|3582->1914|3599->1922|3633->1933|3781->2045|3798->2053|3832->2064|3966->2162|3983->2170|4017->2181|4314->2447
                    LINES: 26->1|32->1|34->5|35->6|35->6|35->6|53->24|53->24|53->24|53->24|59->30|59->30|59->30|64->35|64->35|64->35|64->35|64->35|68->39|68->39|68->39|69->40|69->40|69->40|70->41|70->41|70->41|70->41|71->42|72->43|78->49|78->49|78->49|83->54|83->54|83->54|85->56|85->56|85->56|86->57|86->57|86->57|93->64
                    -- GENERATED --
                */
            