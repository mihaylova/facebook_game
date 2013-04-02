
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
object redirect extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(url: String):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.15*/("""

"""),_display_(Seq[Any](/*3.2*/main("facebook redirect")/*3.27*/ {_display_(Seq[Any](format.raw/*3.29*/("""
	<script>
		window.top.location = """"),_display_(Seq[Any](/*5.27*/Html(url))),format.raw/*5.36*/("""";
	</script>	
""")))})))}
    }
    
    def render(url:String): play.api.templates.Html = apply(url)
    
    def f:((String) => play.api.templates.Html) = (url) => apply(url)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Tue Apr 02 19:52:42 EEST 2013
                    SOURCE: /home/r/work/FbPokerQuiz/app/views/canvas/redirect.scala.html
                    HASH: 122cda19a447ec56cf30c43b0b452c42bced0630
                    MATRIX: 733->1|823->14|860->17|893->42|932->44|1004->81|1034->90
                    LINES: 26->1|29->1|31->3|31->3|31->3|33->5|33->5
                    -- GENERATED --
                */
            