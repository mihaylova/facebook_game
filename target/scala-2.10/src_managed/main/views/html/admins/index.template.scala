
package views.html.admins

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
object index extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(message: String):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.19*/("""

"""),_display_(Seq[Any](/*3.2*/main_admin("Admin")/*3.21*/ {_display_(Seq[Any](format.raw/*3.23*/("""
    

	      
	

<div id="exit"> <p> <a class="btn btn-primary" href="/logout"> Изход</a> </p> </div>
<ul class="nav nav-tabs">
      <li class="active">
       <a href="/admin">Home</a> 
      </li>
     <li><a href="/admin/question/add">Добави въпрос</a></li>
	<li> <a href="/admin/users_questions"> Преглед на потребоителски въпроси</a></li>
	<li> <a href="/admin/questions"> Преглед на въпроси</a></li>
		       
    </ul>
   
	


""")))})))}
    }
    
    def render(message:String): play.api.templates.Html = apply(message)
    
    def f:((String) => play.api.templates.Html) = (message) => apply(message)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Tue Apr 02 13:25:08 EEST 2013
                    SOURCE: /home/r/work/facebook_game/app/views/admins/index.scala.html
                    HASH: 59e6414baa52d2499bc32200bd92dd68f77c29da
                    MATRIX: 730->1|824->18|861->21|888->40|927->42
                    LINES: 26->1|29->1|31->3|31->3|31->3
                    -- GENERATED --
                */
            