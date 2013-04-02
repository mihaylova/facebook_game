
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
object add extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[Form[Question],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(question_form: Form[Question]):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._


Seq[Any](format.raw/*1.33*/("""

"""),format.raw/*5.1*/("""
"""),_display_(Seq[Any](/*6.2*/main_admin("Add Question")/*6.28*/ {_display_(Seq[Any](format.raw/*6.30*/("""
<div id="exit"> <p> <a class="btn btn-primary" href="/logout"> Изход</a> </p> </div>
<ul class="nav nav-tabs">
      <li>
       <a href="/admin">Home</a> 
      </li>
     <li class="active"><a href="/admin/question/add">Добави въпрос</a></li>
	<li> <a href="/admin/users_questions"> Преглед на потребоителски въпроси</a></li>
	<li> <a href="/admin/questions"> Преглед на въпроси</a></li>
	
    </ul>  
    
    """),_display_(Seq[Any](/*18.6*/if(flash.contains("success"))/*18.35*/ {_display_(Seq[Any](format.raw/*18.37*/("""
               
                 <div class="alert alert-success">
        <button type="button" class="close" data-dismiss="alert">×</button>
         """),_display_(Seq[Any](/*22.11*/flash/*22.16*/.get("success"))),format.raw/*22.31*/("""</div>
            """)))})),format.raw/*23.14*/("""
      <div class="row"></div>
    <div class="row"></div>
    <div class="row"></div>
    <div class="row-fluid">
      <div class="span10 offset1">
        """),_display_(Seq[Any](/*29.10*/helper/*29.16*/.form(action = routes.Questions.save)/*29.53*/ {_display_(Seq[Any](format.raw/*29.55*/("""
          <h2>Добавете въпрос:</h2>
          <div class="row-fluid">
            <div class="span4">
              <span class="help-block">Въпрос</span>
              <textarea class="input-block-level" rows="6" name="question"
              placeholder="Въведете въпрос...."></textarea>
            </div>
            <div class="span4">
              <span class="help-block">Категория</span>
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
              <span class="help-block">Верен отговор</span>
              <input type="text" class="input-block-level" placeholder="Верен отговор..."
              name="right_answer"> 
            </div>
            <div class="span4">
              <span class="help-block">Други отговори</span>
              <input type="text" class="input-block-level input-medium"
              name="answer1">
              <input type="text" class="input-block-level input-medium" name="answer2">
              <input type="text" class="input-block-level input-medium" name="answer3">
              <div class="form-actions">
                <button type="submit" class="btn btn-primary">Submit</button>
                <input type="reset" class="btn" value="Reset"> 
              </div>
            </div>
          </div>
       """)))})),format.raw/*67.9*/("""
      </div>
    </div>
    
    
    
""")))})))}
    }
    
    def render(question_form:Form[Question]): play.api.templates.Html = apply(question_form)
    
    def f:((Form[Question]) => play.api.templates.Html) = (question_form) => apply(question_form)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Tue Apr 02 20:33:21 EEST 2013
                    SOURCE: /home/r/work/FbPokerQuiz/app/views/questions/add.scala.html
                    HASH: 192b56cbd222de3362e9062d984c5827c1ead273
                    MATRIX: 739->1|897->32|925->85|961->87|995->113|1034->115|1484->530|1522->559|1562->561|1752->715|1766->720|1803->735|1855->755|2050->914|2065->920|2111->957|2151->959|3969->2746
                    LINES: 26->1|32->1|34->5|35->6|35->6|35->6|47->18|47->18|47->18|51->22|51->22|51->22|52->23|58->29|58->29|58->29|58->29|96->67
                    -- GENERATED --
                */
            