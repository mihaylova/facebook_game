
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
object main_admin extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[String,Html,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(title: String)(content: Html):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.32*/("""

<!DOCTYPE html>

<html>
    <head>
        <title>"""),_display_(Seq[Any](/*7.17*/title)),format.raw/*7.22*/("""</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <script src="https://app.divshot.com/js/jquery.min.js"></script>
    	<script src="https://app.divshot.com/js/bootstrap.min.js"></script>
        <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*11.54*/routes/*11.60*/.Assets.at("stylesheets/main.css"))),format.raw/*11.94*/("""">
        <link rel="shortcut icon" type="image/png" href=""""),_display_(Seq[Any](/*12.59*/routes/*12.65*/.Assets.at("images/favicon.png"))),format.raw/*12.97*/("""">
        <link rel="stylesheet" href="https://app.divshot.com/themes/readable/bootstrap.min.css">
    	<link rel="stylesheet" href="https://app.divshot.com/css/bootstrap-responsive.css"> 
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
    </head>
    <body>
    
		
	
	        """),_display_(Seq[Any](/*21.11*/content)),format.raw/*21.18*/("""
      
        <script src=""""),_display_(Seq[Any](/*23.23*/routes/*23.29*/.Assets.at("javascripts/main.js"))),format.raw/*23.62*/("""" type="text/javascript"></script>
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
                    DATE: Tue Apr 02 15:58:39 EEST 2013
                    SOURCE: /home/r/work/FbPokerQuiz/app/views/main_admin.scala.html
                    HASH: 79b67df7e01cff31f40ce24f6684ab305dc7e0bf
                    MATRIX: 733->1|840->31|928->84|954->89|1274->373|1289->379|1345->413|1442->474|1457->480|1511->512|1877->842|1906->849|1972->879|1987->885|2042->918
                    LINES: 26->1|29->1|35->7|35->7|39->11|39->11|39->11|40->12|40->12|40->12|49->21|49->21|51->23|51->23|51->23
                    -- GENERATED --
                */
            