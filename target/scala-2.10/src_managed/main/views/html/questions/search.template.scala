
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
object search extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template0[play.api.templates.Html] {

    /**/
    def apply/*1.2*/():play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.4*/("""

"""),_display_(Seq[Any](/*3.2*/main_admin("Questions")/*3.25*/ {_display_(Seq[Any](format.raw/*3.27*/("""
	
	
   
	<div id="exit"> <p> <a class="btn btn-primary" href="/logout"> Изход</a> </p> </div>
<ul class="nav nav-tabs">
      <li>
       <a href="/admin">Home</a> 
      </li>
     <li><a href="/admin/question/add">Добави въпрос</a></li>
	<li > <a href="/admin/users_questions"> Преглед на потребоителски въпроси</a></li>
	<li > <a href="/admin/questions"> Преглед на въпроси</a></li>
	<li class="active"> <a href="/admin/questions/search"> Търсене</a></li>
		       
    </ul>
"""),_display_(Seq[Any](/*18.2*/helper/*18.8*/.form(action = routes.Questions.view_by_category)/*18.57*/ {_display_(Seq[Any](format.raw/*18.59*/("""
          
              <span class="help">Категория</span>
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
              <div class="form-actions">
              <button type="submit" class="btn btn-primary">Търси</button>
           </div>
       """)))})),format.raw/*36.9*/("""


""")))})))}
    }
    
    def render(): play.api.templates.Html = apply()
    
    def f:(() => play.api.templates.Html) = () => apply()
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Sun Apr 07 21:29:21 EEST 2013
                    SOURCE: /home/r/work/FbPokerQuiz/app/views/questions/search.scala.html
                    HASH: 0a8dd05f9e97900e6928f4fd3bd160a247836829
                    MATRIX: 727->1|805->3|842->6|873->29|912->31|1428->512|1442->518|1500->567|1540->569|2341->1339
                    LINES: 26->1|29->1|31->3|31->3|31->3|46->18|46->18|46->18|46->18|64->36
                    -- GENERATED --
                */
            