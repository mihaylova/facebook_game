
package views.html.canvas

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
object index extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[String,String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(name: String, message: String):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.33*/("""

"""),_display_(Seq[Any](/*3.2*/main("facebook canvas")/*3.25*/ {_display_(Seq[Any](format.raw/*3.27*/("""
	
	<p id="username">"""),_display_(Seq[Any](/*5.20*/name)),format.raw/*5.24*/("""</p>
	<a  class="btn btn-inverse btn-large" href="/game" id="start_new_game" >Play</a> 
	<div style="position: absolute; left: 31px;top: 223px; font-size: 15px;font-weight: bold; padding-left: 0px;
padding-right: 0px;

border-left-width: 0px;
border-right-width: 0px;" class="alert alert-error">"""),_display_(Seq[Any](/*11.54*/message)),format.raw/*11.61*/("""</div>



		
		
""")))})))}
    }
    
    def render(name:String,message:String): play.api.templates.Html = apply(name,message)
    
    def f:((String,String) => play.api.templates.Html) = (name,message) => apply(name,message)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Tue Apr 02 20:28:02 EEST 2013
                    SOURCE: /home/r/work/FbPokerQuiz/app/views/canvas/index.scala.html
                    HASH: 928f0a308d355568e7b4b93406adf61272a9a568
                    MATRIX: 737->1|845->32|882->35|913->58|952->60|1009->82|1034->86|1366->382|1395->389
                    LINES: 26->1|29->1|31->3|31->3|31->3|33->5|33->5|39->11|39->11
                    -- GENERATED --
                */
            