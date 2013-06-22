
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
	<li> <a href="/admin/questions/search"> Търсене</a></li>
	
    </ul>  
    
 
      <div class="row"></div>
    <div class="row"></div>
    <div class="row"></div>
    <div class="row-fluid">
      <div class="span10 offset1">
        """),_display_(Seq[Any](/*25.10*/helper/*25.16*/.form(action = routes.Questions.editQuestion)/*25.61*/ {_display_(Seq[Any](format.raw/*25.63*/("""
          <h2>Добавете въпрос:</h2>
          <div class="row-fluid">
            <div class="span4">
              <span class="help-block">Въпрос</span>
              <textarea class="input-block-level" rows="6" name="question"
              >"""),_display_(Seq[Any](/*31.17*/question/*31.25*/.getQuestion())),format.raw/*31.39*/("""</textarea>
            </div>
            <div class="span4">
              <span class="help-block">Категория</span>
              <select name="category">
                <option value=""""),_display_(Seq[Any](/*36.33*/question/*36.41*/.getCategory())),format.raw/*36.55*/("""">"""),_display_(Seq[Any](/*36.58*/categories(question.getCategory()))),format.raw/*36.92*/("""</option>
                
             
      
      		"""),_display_(Seq[Any](/*40.10*/for(a <- 1 until 10) yield /*40.30*/{_display_(Seq[Any](format.raw/*40.31*/("""
        		 """),_display_(Seq[Any](/*41.13*/if(a!=question.getCategory())/*41.42*/{_display_(Seq[Any](format.raw/*41.43*/("""
        		 	<option value=""""),_display_(Seq[Any](/*42.29*/a)),format.raw/*42.30*/("""">"""),_display_(Seq[Any](/*42.33*/categories(a))),format.raw/*42.46*/("""</option>
        		 """)))})),format.raw/*43.13*/("""
      		""")))})),format.raw/*44.10*/("""
                
                
               
              </select>
              <span class="help-block">Верен отговор</span>
              <input type="text" class="input-block-level" value=""""),_display_(Seq[Any](/*50.68*/question/*50.76*/.getRight_answer)),format.raw/*50.92*/(""""
              name="right_answer"> 
            </div>
            <div class="span4">
              <span class="help-block">Други отговори</span>
              <input type="text" class="input-block-level input-medium" value=""""),_display_(Seq[Any](/*55.81*/question/*55.89*/.getAnswer1)),format.raw/*55.100*/(""""
              name="answer1">
              <input type="text" class="input-block-level input-medium" value=""""),_display_(Seq[Any](/*57.81*/question/*57.89*/.getAnswer2)),format.raw/*57.100*/("""" name="answer2">
              <input type="text" class="input-block-level input-medium" value=""""),_display_(Seq[Any](/*58.81*/question/*58.89*/.getAnswer3)),format.raw/*58.100*/("""" name="answer3">
              <div class="form-actions">
                <button type="submit" class="btn btn-primary">Submit</button>
                <input type="reset" class="btn" value="Reset"> 
              </div>
            </div>
          </div>
       """)))})),format.raw/*65.9*/("""
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
                    DATE: Sat Jun 22 17:22:39 EEST 2013
                    SOURCE: /home/r/work/FbPokerQuiz/app/views/questions/edit.scala.html
                    HASH: a83f33dec4b8aae0831cbe210fea77bf3fa59642
                    MATRIX: 748->1|922->48|950->101|986->103|1021->130|1060->132|1710->746|1725->752|1779->797|1819->799|2102->1046|2119->1054|2155->1068|2381->1258|2398->1266|2434->1280|2473->1283|2529->1317|2622->1374|2658->1394|2697->1395|2746->1408|2784->1437|2823->1438|2888->1467|2911->1468|2950->1471|2985->1484|3039->1506|3081->1516|3319->1718|3336->1726|3374->1742|3640->1972|3657->1980|3691->1991|3839->2103|3856->2111|3890->2122|4024->2220|4041->2228|4075->2239|4372->2505
                    LINES: 26->1|32->1|34->5|35->6|35->6|35->6|54->25|54->25|54->25|54->25|60->31|60->31|60->31|65->36|65->36|65->36|65->36|65->36|69->40|69->40|69->40|70->41|70->41|70->41|71->42|71->42|71->42|71->42|72->43|73->44|79->50|79->50|79->50|84->55|84->55|84->55|86->57|86->57|86->57|87->58|87->58|87->58|94->65
                    -- GENERATED --
                */
            