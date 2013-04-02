
package views.html.fb_user

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
object for_me extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[Fb_user,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(user: Fb_user):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.17*/("""


"""),_display_(Seq[Any](/*4.2*/main("For me")/*4.16*/ {_display_(Seq[Any](format.raw/*4.18*/("""
	<div id="info">
		<p > <span>Име: </span>"""),_display_(Seq[Any](/*6.27*/user/*6.31*/.name)),format.raw/*6.36*/(""" </p>
		<p> <span>Точки: </span> """),_display_(Seq[Any](/*7.29*/user/*7.33*/.points)),format.raw/*7.40*/(""" </p>
		<p> </span>Монети: </span> """),_display_(Seq[Any](/*8.31*/user/*8.35*/.coins)),format.raw/*8.41*/(""" </p>
			</div>
	<button  onclick='ShowConvert("""),_display_(Seq[Any](/*10.33*/user/*10.37*/.points)),format.raw/*10.44*/(""");'class="btn btn-inverse"  id="convert" >Купи монети</button> 
	<div id="show_convert" style="display:none;"> 
		<form oninput="coins.value = parseInt(points.value)/2" action="/for_me/convert" method="post">
		<span>Точки: </span>
		<input type="number" name="points" min="2" max=""""),_display_(Seq[Any](/*14.52*/{user.points -(user.points%2)})),format.raw/*14.82*/("""" step="2"> <br>
		<span>Монети: </span> <output for="points" name="coins"></output><br><br>
		 <input type="submit"  class="btn btn-inverse btn-small" value="Купи"  style="
    left: 106px;
    position: absolute;
    font-weight: bold;
" > 
		</form>
		</div>
		<div id="error-no-points"  style="display:none;">Нямате достатъчно точки за да купите монети. За получаване на точки можете да добавите въпрос.</div>
		
		
		<div id="medals">
			<span>Медали</span>
			<table align="center">
				<tr>
					<th></th>
					<th>Бронзов</th>
					<th>Сребърен</th>
					<th>Златен</th>
				</tr>
				<tr>
					<td>История</td>
					<td align="center" >"""),_display_(Seq[Any](/*37.27*/if(user.medals1>9)/*37.45*/{_display_(Seq[Any](format.raw/*37.46*/("""
						<img  class="medal" src=""""),_display_(Seq[Any](/*38.33*/routes/*38.39*/.Assets.at("images/bronze.gif"))),format.raw/*38.70*/("""">
					""")))})),format.raw/*39.7*/("""</td>
					<td align="center" >"""),_display_(Seq[Any](/*40.27*/if(user.medals1>19)/*40.46*/{_display_(Seq[Any](format.raw/*40.47*/("""
						<img  class="medal" src=""""),_display_(Seq[Any](/*41.33*/routes/*41.39*/.Assets.at("images/silver.gif"))),format.raw/*41.70*/("""">
					""")))})),format.raw/*42.7*/("""</td>
					<td align="center" >"""),_display_(Seq[Any](/*43.27*/if(user.medals1>49)/*43.46*/{_display_(Seq[Any](format.raw/*43.47*/("""
						<img  class="medal" src=""""),_display_(Seq[Any](/*44.33*/routes/*44.39*/.Assets.at("images/gold.gif"))),format.raw/*44.68*/("""">
					""")))})),format.raw/*45.7*/("""</td>
				</tr>	
				<tr>
					<td>Природни науки</td>
					<td align="center" >"""),_display_(Seq[Any](/*49.27*/if(user.medals2>9)/*49.45*/{_display_(Seq[Any](format.raw/*49.46*/("""
						<img  class="medal" src=""""),_display_(Seq[Any](/*50.33*/routes/*50.39*/.Assets.at("images/bronze.gif"))),format.raw/*50.70*/("""">
					""")))})),format.raw/*51.7*/("""</td>
					<td align="center" >"""),_display_(Seq[Any](/*52.27*/if(user.medals2>19)/*52.46*/{_display_(Seq[Any](format.raw/*52.47*/("""
						<img  class="medal" src=""""),_display_(Seq[Any](/*53.33*/routes/*53.39*/.Assets.at("images/silver.gif"))),format.raw/*53.70*/("""">
					""")))})),format.raw/*54.7*/("""</td>
					<td align="center" >"""),_display_(Seq[Any](/*55.27*/if(user.medals2>49)/*55.46*/{_display_(Seq[Any](format.raw/*55.47*/("""
						<img  class="medal" src=""""),_display_(Seq[Any](/*56.33*/routes/*56.39*/.Assets.at("images/gold.gif"))),format.raw/*56.68*/("""">
					""")))})),format.raw/*57.7*/("""</td>
				</tr>
				<tr>
					<td>Изкуство</td>
					<td align="center" >"""),_display_(Seq[Any](/*61.27*/if(user.medals3>9)/*61.45*/{_display_(Seq[Any](format.raw/*61.46*/("""
						<img  class="medal" src=""""),_display_(Seq[Any](/*62.33*/routes/*62.39*/.Assets.at("images/bronze.gif"))),format.raw/*62.70*/("""">
					""")))})),format.raw/*63.7*/("""</td>
					<td align="center" >"""),_display_(Seq[Any](/*64.27*/if(user.medals3>19)/*64.46*/{_display_(Seq[Any](format.raw/*64.47*/("""
						<img  class="medal" src=""""),_display_(Seq[Any](/*65.33*/routes/*65.39*/.Assets.at("images/silver.gif"))),format.raw/*65.70*/("""">
					""")))})),format.raw/*66.7*/("""</td>
					<td align="center" >"""),_display_(Seq[Any](/*67.27*/if(user.medals3>49)/*67.46*/{_display_(Seq[Any](format.raw/*67.47*/("""
						<img  class="medal" src=""""),_display_(Seq[Any](/*68.33*/routes/*68.39*/.Assets.at("images/gold.gif"))),format.raw/*68.68*/("""">
					""")))})),format.raw/*69.7*/("""</td>
				</tr>
				<tr>
					<td>Литература</td>
					<td align="center" >"""),_display_(Seq[Any](/*73.27*/if(user.medals4>9)/*73.45*/{_display_(Seq[Any](format.raw/*73.46*/("""
						<img  class="medal" src=""""),_display_(Seq[Any](/*74.33*/routes/*74.39*/.Assets.at("images/bronze.gif"))),format.raw/*74.70*/("""">
					""")))})),format.raw/*75.7*/("""</td>
					<td align="center" >"""),_display_(Seq[Any](/*76.27*/if(user.medals4>19)/*76.46*/{_display_(Seq[Any](format.raw/*76.47*/("""
						<img  class="medal" src=""""),_display_(Seq[Any](/*77.33*/routes/*77.39*/.Assets.at("images/silver.gif"))),format.raw/*77.70*/("""">
					""")))})),format.raw/*78.7*/("""</td>
					<td align="center" >"""),_display_(Seq[Any](/*79.27*/if(user.medals4>49)/*79.46*/{_display_(Seq[Any](format.raw/*79.47*/("""
						<img  class="medal" src=""""),_display_(Seq[Any](/*80.33*/routes/*80.39*/.Assets.at("images/gold.gif"))),format.raw/*80.68*/("""">
					""")))})),format.raw/*81.7*/("""</td>
				</tr>
				<tr>
					<td>Спорт</td>
					<td align="center" >"""),_display_(Seq[Any](/*85.27*/if(user.medals5>9)/*85.45*/{_display_(Seq[Any](format.raw/*85.46*/("""
						<img  class="medal" src=""""),_display_(Seq[Any](/*86.33*/routes/*86.39*/.Assets.at("images/bronze.gif"))),format.raw/*86.70*/("""">
					""")))})),format.raw/*87.7*/("""</td>
					<td align="center" >"""),_display_(Seq[Any](/*88.27*/if(user.medals5>19)/*88.46*/{_display_(Seq[Any](format.raw/*88.47*/("""
						<img  class="medal" src=""""),_display_(Seq[Any](/*89.33*/routes/*89.39*/.Assets.at("images/silver.gif"))),format.raw/*89.70*/("""">
					""")))})),format.raw/*90.7*/("""</td>
					<td align="center" >"""),_display_(Seq[Any](/*91.27*/if(user.medals5>49)/*91.46*/{_display_(Seq[Any](format.raw/*91.47*/("""
						<img  class="medal" src=""""),_display_(Seq[Any](/*92.33*/routes/*92.39*/.Assets.at("images/gold.gif"))),format.raw/*92.68*/("""">
					""")))})),format.raw/*93.7*/("""</td>
				</tr>
				<tr>
					<td>География</td>
					<td align="center" >"""),_display_(Seq[Any](/*97.27*/if(user.medals6>9)/*97.45*/{_display_(Seq[Any](format.raw/*97.46*/("""
						<img  class="medal" src=""""),_display_(Seq[Any](/*98.33*/routes/*98.39*/.Assets.at("images/bronze.gif"))),format.raw/*98.70*/("""">
					""")))})),format.raw/*99.7*/("""</td>
					<td align="center" >"""),_display_(Seq[Any](/*100.27*/if(user.medals6>19)/*100.46*/{_display_(Seq[Any](format.raw/*100.47*/("""
						<img  class="medal" src=""""),_display_(Seq[Any](/*101.33*/routes/*101.39*/.Assets.at("images/silver.gif"))),format.raw/*101.70*/("""">
					""")))})),format.raw/*102.7*/("""</td>
					<td align="center" >"""),_display_(Seq[Any](/*103.27*/if(user.medals6>49)/*103.46*/{_display_(Seq[Any](format.raw/*103.47*/("""
						<img  class="medal" src=""""),_display_(Seq[Any](/*104.33*/routes/*104.39*/.Assets.at("images/gold.gif"))),format.raw/*104.68*/("""">
					""")))})),format.raw/*105.7*/("""</td>
				</tr>
				<tr>
					<td>Астронимия</td>
					<td align="center" >"""),_display_(Seq[Any](/*109.27*/if(user.medals7>9)/*109.45*/{_display_(Seq[Any](format.raw/*109.46*/("""
						<img  class="medal" src=""""),_display_(Seq[Any](/*110.33*/routes/*110.39*/.Assets.at("images/bronze.gif"))),format.raw/*110.70*/("""">
					""")))})),format.raw/*111.7*/("""</td>
					<td align="center" >"""),_display_(Seq[Any](/*112.27*/if(user.medals7>19)/*112.46*/{_display_(Seq[Any](format.raw/*112.47*/("""
						<img  class="medal" src=""""),_display_(Seq[Any](/*113.33*/routes/*113.39*/.Assets.at("images/silver.gif"))),format.raw/*113.70*/("""">
					""")))})),format.raw/*114.7*/("""</td>
					<td align="center" >"""),_display_(Seq[Any](/*115.27*/if(user.medals7>49)/*115.46*/{_display_(Seq[Any](format.raw/*115.47*/("""
						<img  class="medal" src=""""),_display_(Seq[Any](/*116.33*/routes/*116.39*/.Assets.at("images/gold.gif"))),format.raw/*116.68*/("""">
					""")))})),format.raw/*117.7*/("""</td>
				</tr>
				<tr>
					<td>Технологии</td>
					<td align="center" >"""),_display_(Seq[Any](/*121.27*/if(user.medals8>9)/*121.45*/{_display_(Seq[Any](format.raw/*121.46*/("""
						<img  class="medal" src=""""),_display_(Seq[Any](/*122.33*/routes/*122.39*/.Assets.at("images/bronze.gif"))),format.raw/*122.70*/("""">
					""")))})),format.raw/*123.7*/("""</td>
					<td align="center" >"""),_display_(Seq[Any](/*124.27*/if(user.medals8>19)/*124.46*/{_display_(Seq[Any](format.raw/*124.47*/("""
						<img  class="medal" src=""""),_display_(Seq[Any](/*125.33*/routes/*125.39*/.Assets.at("images/silver.gif"))),format.raw/*125.70*/("""">
					""")))})),format.raw/*126.7*/("""</td>
					<td align="center" >"""),_display_(Seq[Any](/*127.27*/if(user.medals8>49)/*127.46*/{_display_(Seq[Any](format.raw/*127.47*/("""
						<img  class="medal" src=""""),_display_(Seq[Any](/*128.33*/routes/*128.39*/.Assets.at("images/gold.gif"))),format.raw/*128.68*/("""">
					""")))})),format.raw/*129.7*/("""</td>
				</tr>
				<tr>
					<td>Кино и телевизия</td>
					<td align="center" >"""),_display_(Seq[Any](/*133.27*/if(user.medals9>9)/*133.45*/{_display_(Seq[Any](format.raw/*133.46*/("""
						<img  class="medal" src=""""),_display_(Seq[Any](/*134.33*/routes/*134.39*/.Assets.at("images/bronze.gif"))),format.raw/*134.70*/("""">
					""")))})),format.raw/*135.7*/("""</td>
					<td align="center" >"""),_display_(Seq[Any](/*136.27*/if(user.medals9>19)/*136.46*/{_display_(Seq[Any](format.raw/*136.47*/("""
						<img  class="medal" src=""""),_display_(Seq[Any](/*137.33*/routes/*137.39*/.Assets.at("images/silver.gif"))),format.raw/*137.70*/("""">
					""")))})),format.raw/*138.7*/("""</td>
					<td align="center" >"""),_display_(Seq[Any](/*139.27*/if(user.medals9>49)/*139.46*/{_display_(Seq[Any](format.raw/*139.47*/("""
						<img  class="medal" src=""""),_display_(Seq[Any](/*140.33*/routes/*140.39*/.Assets.at("images/gold.gif"))),format.raw/*140.68*/("""">
					""")))})),format.raw/*141.7*/("""</td>
				</tr>
			</table>
				
			
		</div>
""")))})))}
    }
    
    def render(user:Fb_user): play.api.templates.Html = apply(user)
    
    def f:((Fb_user) => play.api.templates.Html) = (user) => apply(user)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Tue Apr 02 18:32:53 EEST 2013
                    SOURCE: /home/r/work/FbPokerQuiz/app/views/fb_user/for_me.scala.html
                    HASH: 2c9bf5ff180f0fa0f232cedd75534b2a76f6fac9
                    MATRIX: 733->1|825->16|863->20|885->34|924->36|1003->80|1015->84|1041->89|1110->123|1122->127|1150->134|1221->170|1233->174|1260->180|1344->228|1357->232|1386->239|1705->522|1757->552|2439->1198|2466->1216|2505->1217|2574->1250|2589->1256|2642->1287|2682->1296|2750->1328|2778->1347|2817->1348|2886->1381|2901->1387|2954->1418|2994->1427|3062->1459|3090->1478|3129->1479|3198->1512|3213->1518|3264->1547|3304->1556|3421->1637|3448->1655|3487->1656|3556->1689|3571->1695|3624->1726|3664->1735|3732->1767|3760->1786|3799->1787|3868->1820|3883->1826|3936->1857|3976->1866|4044->1898|4072->1917|4111->1918|4180->1951|4195->1957|4246->1986|4286->1995|4396->2069|4423->2087|4462->2088|4531->2121|4546->2127|4599->2158|4639->2167|4707->2199|4735->2218|4774->2219|4843->2252|4858->2258|4911->2289|4951->2298|5019->2330|5047->2349|5086->2350|5155->2383|5170->2389|5221->2418|5261->2427|5373->2503|5400->2521|5439->2522|5508->2555|5523->2561|5576->2592|5616->2601|5684->2633|5712->2652|5751->2653|5820->2686|5835->2692|5888->2723|5928->2732|5996->2764|6024->2783|6063->2784|6132->2817|6147->2823|6198->2852|6238->2861|6345->2932|6372->2950|6411->2951|6480->2984|6495->2990|6548->3021|6588->3030|6656->3062|6684->3081|6723->3082|6792->3115|6807->3121|6860->3152|6900->3161|6968->3193|6996->3212|7035->3213|7104->3246|7119->3252|7170->3281|7210->3290|7321->3365|7348->3383|7387->3384|7456->3417|7471->3423|7524->3454|7564->3463|7633->3495|7662->3514|7702->3515|7772->3548|7788->3554|7842->3585|7883->3594|7952->3626|7981->3645|8021->3646|8091->3679|8107->3685|8159->3714|8200->3723|8313->3799|8341->3817|8381->3818|8451->3851|8467->3857|8521->3888|8562->3897|8631->3929|8660->3948|8700->3949|8770->3982|8786->3988|8840->4019|8881->4028|8950->4060|8979->4079|9019->4080|9089->4113|9105->4119|9157->4148|9198->4157|9311->4233|9339->4251|9379->4252|9449->4285|9465->4291|9519->4322|9560->4331|9629->4363|9658->4382|9698->4383|9768->4416|9784->4422|9838->4453|9879->4462|9948->4494|9977->4513|10017->4514|10087->4547|10103->4553|10155->4582|10196->4591|10315->4673|10343->4691|10383->4692|10453->4725|10469->4731|10523->4762|10564->4771|10633->4803|10662->4822|10702->4823|10772->4856|10788->4862|10842->4893|10883->4902|10952->4934|10981->4953|11021->4954|11091->4987|11107->4993|11159->5022|11200->5031
                    LINES: 26->1|29->1|32->4|32->4|32->4|34->6|34->6|34->6|35->7|35->7|35->7|36->8|36->8|36->8|38->10|38->10|38->10|42->14|42->14|65->37|65->37|65->37|66->38|66->38|66->38|67->39|68->40|68->40|68->40|69->41|69->41|69->41|70->42|71->43|71->43|71->43|72->44|72->44|72->44|73->45|77->49|77->49|77->49|78->50|78->50|78->50|79->51|80->52|80->52|80->52|81->53|81->53|81->53|82->54|83->55|83->55|83->55|84->56|84->56|84->56|85->57|89->61|89->61|89->61|90->62|90->62|90->62|91->63|92->64|92->64|92->64|93->65|93->65|93->65|94->66|95->67|95->67|95->67|96->68|96->68|96->68|97->69|101->73|101->73|101->73|102->74|102->74|102->74|103->75|104->76|104->76|104->76|105->77|105->77|105->77|106->78|107->79|107->79|107->79|108->80|108->80|108->80|109->81|113->85|113->85|113->85|114->86|114->86|114->86|115->87|116->88|116->88|116->88|117->89|117->89|117->89|118->90|119->91|119->91|119->91|120->92|120->92|120->92|121->93|125->97|125->97|125->97|126->98|126->98|126->98|127->99|128->100|128->100|128->100|129->101|129->101|129->101|130->102|131->103|131->103|131->103|132->104|132->104|132->104|133->105|137->109|137->109|137->109|138->110|138->110|138->110|139->111|140->112|140->112|140->112|141->113|141->113|141->113|142->114|143->115|143->115|143->115|144->116|144->116|144->116|145->117|149->121|149->121|149->121|150->122|150->122|150->122|151->123|152->124|152->124|152->124|153->125|153->125|153->125|154->126|155->127|155->127|155->127|156->128|156->128|156->128|157->129|161->133|161->133|161->133|162->134|162->134|162->134|163->135|164->136|164->136|164->136|165->137|165->137|165->137|166->138|167->139|167->139|167->139|168->140|168->140|168->140|169->141
                    -- GENERATED --
                */
            