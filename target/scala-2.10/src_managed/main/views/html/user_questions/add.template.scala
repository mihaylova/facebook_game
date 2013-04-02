
package views.html.user_questions

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
object add extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[Form[User_question],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(question_form: Form[User_question]):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._


Seq[Any](format.raw/*1.38*/("""

"""),format.raw/*5.1*/("""
"""),_display_(Seq[Any](/*6.2*/main("Add question")/*6.22*/ {_display_(Seq[Any](format.raw/*6.24*/("""
	
    """),_display_(Seq[Any](/*8.6*/if(flash.contains("success"))/*8.35*/ {_display_(Seq[Any](format.raw/*8.37*/("""
               
                 <div class="alert alert-success" id="add-message">
        <button type="button" class="close" data-dismiss="alert">×</button>
         """),_display_(Seq[Any](/*12.11*/flash/*12.16*/.get("success"))),format.raw/*12.31*/("""</div>
            """)))})),format.raw/*13.14*/("""

    	<div id="error"  class="alert alert-error" style="display:none">
    		
    		Не са подпълнени всички полета!</div>
    		
    		
    		<div id="error1"  class="alert alert-error" style="display:none">
    		 
    		Дължината на полeто е твърде голяма!</div>
    		
    	<form action="/question" id="form-add-question" method="POST">
    		
    	<div id="add-form">
    	
	    	<div class="control-group">
			  <label class="control-label" for="question">Въпрос</label>
			  <div class="controls">
			    <textarea rows="6" cols="12" name="question" placeholder="Въведете въпрос...."></textarea>
			  </div>
			</div>
	    	<div class="control-group">
			  <label class="control-label" for="category">Категория</label>
			  <div class="controls">
			    <select name="category">
	                <option value="1">История</option>
              	  <option value="2">Природни науки</option>
               	 <option value="3">Изкуство</option>
                <option value="4">Литература</option>
                <option value="5">Спорт</option>
                <option value="6">География</option>
                <option value="7">Астронимия</option>
                <option value="8">Технологии</option>
                <option value="9">Кино и телевизия</option>
	              </select>
			  </div>
			</div>
	       <div class="control-group">
			  <label class="control-label" for="ight_answer">Верен отговор</label>
			  <div class="controls">
			    <input type="text" class="input-large" id="answer" placeholder="Верен отговор..." name="right_answer">
			  </div>
			</div>
			
			<div class="control-group">
			  <label class="control-label" for="answer1">Друг отговор</label>
			  <div class="controls">
			    <input type="text" class="input-large" id="answer" name="answer1">
			  </div>
			</div>
			
			<div class="control-group">
			  <label class="control-label" for="answer2">Друг отговор</label>
			  <div class="controls">
			     <input type="text" class="input-large" id="answer" name="answer2">
			  </div>
			</div>
			
			<div class="control-group">
			  <label class="control-label" for="answer3">Друг отговор</label>
			  <div class="controls">
				 <input type="text" class="input-large" id="answer" name="answer3">
			  </div>
			</div>
			
	           <br>
	          
	            <button type="submit" class="btn"  id="submit-btn">Добави</button>
	            <input type="reset" class="btn" value="Reset"> 
          </div>
    </form>
   <script src=""""),_display_(Seq[Any](/*84.18*/routes/*84.24*/.Assets.at("javascripts/main.js"))),format.raw/*84.57*/("""" type="text/javascript"></script>
  
""")))})))}
    }
    
    def render(question_form:Form[User_question]): play.api.templates.Html = apply(question_form)
    
    def f:((Form[User_question]) => play.api.templates.Html) = (question_form) => apply(question_form)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Tue Apr 02 14:21:19 EEST 2013
                    SOURCE: /home/r/work/FbPokerQuiz/app/views/user_questions/add.scala.html
                    HASH: 27bea0e54cb04f6d0a9da296365b1abde3e35a7f
                    MATRIX: 749->1|912->37|940->90|976->92|1004->112|1043->114|1085->122|1122->151|1161->153|1368->324|1382->329|1419->344|1471->364|4001->2858|4016->2864|4071->2897
                    LINES: 26->1|32->1|34->5|35->6|35->6|35->6|37->8|37->8|37->8|41->12|41->12|41->12|42->13|113->84|113->84|113->84
                    -- GENERATED --
                */
            