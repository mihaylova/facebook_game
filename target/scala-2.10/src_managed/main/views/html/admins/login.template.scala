
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
object login extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[Form[Admin],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(form: Form[Admin]):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.21*/("""

"""),_display_(Seq[Any](/*3.2*/main_admin("Login")/*3.21*/ {_display_(Seq[Any](format.raw/*3.23*/("""
	
            
            """),_display_(Seq[Any](/*6.14*/if(flash.contains("success"))/*6.43*/ {_display_(Seq[Any](format.raw/*6.45*/("""
               
                 <div class="alert alert-success">
        <button type="button" class="close" data-dismiss="alert">×</button>
         """),_display_(Seq[Any](/*10.11*/flash/*10.16*/.get("success"))),format.raw/*10.31*/("""</div>
            """)))})),format.raw/*11.14*/("""
            """),_display_(Seq[Any](/*12.14*/if(flash.contains("error"))/*12.41*/ {_display_(Seq[Any](format.raw/*12.43*/("""
               
                 <div class="alert alert-error">
        <button type="button" class="close" data-dismiss="alert">×</button>
         """),_display_(Seq[Any](/*16.11*/flash/*16.16*/.get("error"))),format.raw/*16.29*/("""</div>
            """)))})),format.raw/*17.14*/("""
	
	
	
        """),_display_(Seq[Any](/*21.10*/helper/*21.16*/.form(routes.Admins.authenticate)/*21.49*/ {_display_(Seq[Any](format.raw/*21.51*/("""
        <div id="sign_in">	
        	<div class="form-horizontal">
			  <div class="control-group">
			    <label class="control-label" for="username">Username</label>
			    <div class="controls">
			      <input type="text"  name="username" id="inputEmail" placeholder="username">
			    </div>
			  </div>
			  <div class="control-group">
			    <label class="control-label" for="inputPassword">Password</label>
			    <div class="controls">
			      <input type="password"  name="password" id="inputPassword" placeholder="password">
			    </div>
			  </div>
			  <div class="control-group">
			    <div class="controls">
			       <button type="submit" class="btn">Sign in</button>
			    </div>
			  </div>
			</div>
		</div>
        """)))})),format.raw/*43.10*/("""
        
  """)))})),format.raw/*45.4*/("""      
"""))}
    }
    
    def render(form:Form[Admin]): play.api.templates.Html = apply(form)
    
    def f:((Form[Admin]) => play.api.templates.Html) = (form) => apply(form)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Tue Apr 02 20:28:02 EEST 2013
                    SOURCE: /home/r/work/FbPokerQuiz/app/views/admins/login.scala.html
                    HASH: 94d9ba3b7fe03599cddd3d517b67efc1738a2251
                    MATRIX: 735->1|831->20|868->23|895->42|934->44|998->73|1035->102|1074->104|1264->258|1278->263|1315->278|1367->298|1417->312|1453->339|1493->341|1681->493|1695->498|1730->511|1782->531|1834->547|1849->553|1891->586|1931->588|2705->1330|2749->1343
                    LINES: 26->1|29->1|31->3|31->3|31->3|34->6|34->6|34->6|38->10|38->10|38->10|39->11|40->12|40->12|40->12|44->16|44->16|44->16|45->17|49->21|49->21|49->21|49->21|71->43|73->45
                    -- GENERATED --
                */
            