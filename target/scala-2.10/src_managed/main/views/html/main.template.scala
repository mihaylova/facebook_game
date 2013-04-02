
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
object main extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[String,Html,play.api.templates.Html] {

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
    	<script src="https://app.divshot.com/js/bootstrap.min.js"></script>
    	<script src=""""),_display_(Seq[Any](/*15.20*/routes/*15.26*/.Assets.at("javascripts/underscore.js"))),format.raw/*15.65*/("""" type="text/javascript"></script>
        <script src=""""),_display_(Seq[Any](/*16.23*/routes/*16.29*/.Assets.at("javascripts/main.js"))),format.raw/*16.62*/("""" type="text/javascript"></script>
        <script src=""""),_display_(Seq[Any](/*17.23*/routes/*17.29*/.Assets.at("javascripts/game.js"))),format.raw/*17.62*/("""" type="text/javascript"></script>
    </head>
    <body id="home_users">
    
		<div id="btn-home">
			 <a class="btn btn-block" href="/home/" id="menu-btn">Начало</a> 
		</div>
	
		<div id="btn-question">
			 <a class="btn btn-block" href="/question/add" id="menu-btn">Добави въпрос</a>
		</div>
	
		<div id="btn-info">
			 <a class="btn btn-block" href="/for_me" id="menu-btn">За мен</a> 
		</div>
	
        """),_display_(Seq[Any](/*33.10*/content)),format.raw/*33.17*/("""
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
                    DATE: Tue Apr 02 16:41:09 EEST 2013
                    SOURCE: /home/r/work/FbPokerQuiz/app/views/main.scala.html
                    HASH: 657ec7590813b74ef2ccfa3e6ec79d6dfb475b85
                    MATRIX: 727->1|834->31|922->84|948->89|1121->227|1135->233|1190->267|1287->328|1302->334|1356->366|1755->729|1770->735|1831->774|1924->831|1939->837|1994->870|2087->927|2102->933|2157->966|2605->1378|2634->1385
                    LINES: 26->1|29->1|35->7|35->7|37->9|37->9|37->9|38->10|38->10|38->10|43->15|43->15|43->15|44->16|44->16|44->16|45->17|45->17|45->17|61->33|61->33
                    -- GENERATED --
                */
            