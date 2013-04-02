
package views.html

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
object main_play extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[String,Html,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(title: String)(content: Html):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.32*/("""

<!DOCTYPE html>

<html>
    <head>
        <title>"""),_display_(Seq[Any](/*7.17*/title)),format.raw/*7.22*/("""</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*9.54*/routes/*9.60*/.Assets.at("stylesheets/main.css"))),format.raw/*9.94*/("""">
        <link rel="shortcut icon" type="image/png" href=""""),_display_(Seq[Any](/*10.59*/routes/*10.65*/.Assets.at("images/favicon.png"))),format.raw/*10.97*/("""">
        <link rel="stylesheet" href="https://app.divshot.com/css/bootstrap.css">
    	<link rel="stylesheet" href="https://app.divshot.com/css/bootstrap-responsive.css"> 
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
        <script src=""""),_display_(Seq[Any](/*14.23*/routes/*14.29*/.Assets.at("javascripts/jquery.pietimer.js"))),format.raw/*14.73*/("""" type="text/javascript"></script>
        <script src="https://app.divshot.com/js/bootstrap.min.js"></script>
       	<script src=""""),_display_(Seq[Any](/*16.23*/routes/*16.29*/.Assets.at("javascripts/underscore.js"))),format.raw/*16.68*/("""" type="text/javascript"></script>
        <script src=""""),_display_(Seq[Any](/*17.23*/routes/*17.29*/.Assets.at("javascripts/main.js"))),format.raw/*17.62*/("""" type="text/javascript"></script>        
        <script src=""""),_display_(Seq[Any](/*18.23*/routes/*18.29*/.Assets.at("javascripts/game.js"))),format.raw/*18.62*/("""" type="text/javascript"></script>
    </head>
    <body id="play">
       """),_display_(Seq[Any](/*21.9*/content)),format.raw/*21.16*/("""
    </body>
</html>
"""))}
    }
    
    def render(title:String,content:Html): play.api.templates.Html = apply(title)(content)
    
    def f:((String) => (Html) => play.api.templates.Html) = (title) => (content) => apply(title)(content)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Tue Apr 02 15:33:09 EEST 2013
                    SOURCE: /home/r/work/FbPokerQuiz/app/views/main_play.scala.html
                    HASH: ae5459e58fb1a15ab67743b148c4129d2f754488
                    MATRIX: 732->1|839->31|927->84|953->89|1126->227|1140->233|1195->267|1292->328|1307->334|1361->366|1690->659|1705->665|1771->709|1940->842|1955->848|2016->887|2109->944|2124->950|2179->983|2280->1048|2295->1054|2350->1087|2461->1163|2490->1170
                    LINES: 26->1|29->1|35->7|35->7|37->9|37->9|37->9|38->10|38->10|38->10|42->14|42->14|42->14|44->16|44->16|44->16|45->17|45->17|45->17|46->18|46->18|46->18|49->21|49->21
                    -- GENERATED --
                */
            