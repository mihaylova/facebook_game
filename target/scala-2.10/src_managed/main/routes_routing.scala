// @SOURCE:/home/r/work/FbPokerQuiz/conf/routes
// @HASH:911a2957d17f5764443340a71bb8c641f56529f8
// @DATE:Tue Apr 02 16:41:06 EEST 2013


import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString

object Routes extends Router.Routes {

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix  
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" } 


// @LINE:6
private[this] lazy val controllers_Canvas_index0 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("canvas/"))))
        

// @LINE:7
private[this] lazy val controllers_Canvas_redirect1 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("canvas/"))))
        

// @LINE:8
private[this] lazy val controllers_Canvas_index2 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("home/"))))
        

// @LINE:10
private[this] lazy val controllers_User_questions_add3 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("question/add"))))
        

// @LINE:11
private[this] lazy val controllers_User_questions_save4 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("question"))))
        

// @LINE:13
private[this] lazy val controllers_Fb_users_for_me5 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("for_me"))))
        

// @LINE:14
private[this] lazy val controllers_Fb_users_convert6 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("for_me/convert"))))
        

// @LINE:19
private[this] lazy val controllers_Admins_login7 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("login"))))
        

// @LINE:20
private[this] lazy val controllers_Admins_authenticate8 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("login"))))
        

// @LINE:21
private[this] lazy val controllers_Admins_logout9 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("logout"))))
        

// @LINE:22
private[this] lazy val controllers_Admins_index10 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin"))))
        

// @LINE:24
private[this] lazy val controllers_Questions_add11 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/question/add"))))
        

// @LINE:25
private[this] lazy val controllers_Questions_save12 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/question"))))
        

// @LINE:26
private[this] lazy val controllers_Questions_editQuestion13 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/editQuestion"))))
        

// @LINE:28
private[this] lazy val controllers_Questions_view14 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/questions"))))
        

// @LINE:29
private[this] lazy val controllers_User_questions_view15 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/users_questions"))))
        

// @LINE:30
private[this] lazy val controllers_User_questions_insert16 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/users_questions/insert/"),DynamicPart("id", """[^/]+"""))))
        

// @LINE:31
private[this] lazy val controllers_User_questions_delete17 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/users_questions/delete/"),DynamicPart("id", """[^/]+"""))))
        

// @LINE:32
private[this] lazy val controllers_Questions_edit18 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/questions/edit/"),DynamicPart("id", """[^/]+"""))))
        

// @LINE:33
private[this] lazy val controllers_Questions_delete19 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/questions/delete/"),DynamicPart("id", """[^/]+"""))))
        

// @LINE:35
private[this] lazy val controllers_Games_index20 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("game"))))
        

// @LINE:36
private[this] lazy val controllers_Games_join21 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("game/join"))))
        

// @LINE:40
private[this] lazy val controllers_Assets_at22 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+"""))))
        
def documentation = List(("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """canvas/""","""controllers.Canvas.index()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """canvas/""","""controllers.Canvas.redirect()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """home/""","""controllers.Canvas.index()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """question/add""","""controllers.User_questions.add()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """question""","""controllers.User_questions.save()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """for_me""","""controllers.Fb_users.for_me()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """for_me/convert""","""controllers.Fb_users.convert()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """login""","""controllers.Admins.login()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """login""","""controllers.Admins.authenticate()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """logout""","""controllers.Admins.logout()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin""","""controllers.Admins.index()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/question/add""","""controllers.Questions.add()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/question""","""controllers.Questions.save()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/editQuestion""","""controllers.Questions.editQuestion()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/questions""","""controllers.Questions.view()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/users_questions""","""controllers.User_questions.view()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/users_questions/insert/$id<[^/]+>""","""controllers.User_questions.insert(id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/users_questions/delete/$id<[^/]+>""","""controllers.User_questions.delete(id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/questions/edit/$id<[^/]+>""","""controllers.Questions.edit(id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/questions/delete/$id<[^/]+>""","""controllers.Questions.delete(id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """game""","""controllers.Games.index()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """game/join""","""controllers.Games.join()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]] 
}}
       
    
def routes:PartialFunction[RequestHeader,Handler] = {        

// @LINE:6
case controllers_Canvas_index0(params) => {
   call { 
        invokeHandler(controllers.Canvas.index(), HandlerDef(this, "controllers.Canvas", "index", Nil,"POST", """ Home page""", Routes.prefix + """canvas/"""))
   }
}
        

// @LINE:7
case controllers_Canvas_redirect1(params) => {
   call { 
        invokeHandler(controllers.Canvas.redirect(), HandlerDef(this, "controllers.Canvas", "redirect", Nil,"GET", """""", Routes.prefix + """canvas/"""))
   }
}
        

// @LINE:8
case controllers_Canvas_index2(params) => {
   call { 
        invokeHandler(controllers.Canvas.index(), HandlerDef(this, "controllers.Canvas", "index", Nil,"GET", """""", Routes.prefix + """home/"""))
   }
}
        

// @LINE:10
case controllers_User_questions_add3(params) => {
   call { 
        invokeHandler(controllers.User_questions.add(), HandlerDef(this, "controllers.User_questions", "add", Nil,"GET", """""", Routes.prefix + """question/add"""))
   }
}
        

// @LINE:11
case controllers_User_questions_save4(params) => {
   call { 
        invokeHandler(controllers.User_questions.save(), HandlerDef(this, "controllers.User_questions", "save", Nil,"POST", """""", Routes.prefix + """question"""))
   }
}
        

// @LINE:13
case controllers_Fb_users_for_me5(params) => {
   call { 
        invokeHandler(controllers.Fb_users.for_me(), HandlerDef(this, "controllers.Fb_users", "for_me", Nil,"GET", """""", Routes.prefix + """for_me"""))
   }
}
        

// @LINE:14
case controllers_Fb_users_convert6(params) => {
   call { 
        invokeHandler(controllers.Fb_users.convert(), HandlerDef(this, "controllers.Fb_users", "convert", Nil,"POST", """""", Routes.prefix + """for_me/convert"""))
   }
}
        

// @LINE:19
case controllers_Admins_login7(params) => {
   call { 
        invokeHandler(controllers.Admins.login(), HandlerDef(this, "controllers.Admins", "login", Nil,"GET", """""", Routes.prefix + """login"""))
   }
}
        

// @LINE:20
case controllers_Admins_authenticate8(params) => {
   call { 
        invokeHandler(controllers.Admins.authenticate(), HandlerDef(this, "controllers.Admins", "authenticate", Nil,"POST", """""", Routes.prefix + """login"""))
   }
}
        

// @LINE:21
case controllers_Admins_logout9(params) => {
   call { 
        invokeHandler(controllers.Admins.logout(), HandlerDef(this, "controllers.Admins", "logout", Nil,"GET", """""", Routes.prefix + """logout"""))
   }
}
        

// @LINE:22
case controllers_Admins_index10(params) => {
   call { 
        invokeHandler(controllers.Admins.index(), HandlerDef(this, "controllers.Admins", "index", Nil,"GET", """""", Routes.prefix + """admin"""))
   }
}
        

// @LINE:24
case controllers_Questions_add11(params) => {
   call { 
        invokeHandler(controllers.Questions.add(), HandlerDef(this, "controllers.Questions", "add", Nil,"GET", """""", Routes.prefix + """admin/question/add"""))
   }
}
        

// @LINE:25
case controllers_Questions_save12(params) => {
   call { 
        invokeHandler(controllers.Questions.save(), HandlerDef(this, "controllers.Questions", "save", Nil,"POST", """""", Routes.prefix + """admin/question"""))
   }
}
        

// @LINE:26
case controllers_Questions_editQuestion13(params) => {
   call { 
        invokeHandler(controllers.Questions.editQuestion(), HandlerDef(this, "controllers.Questions", "editQuestion", Nil,"POST", """""", Routes.prefix + """admin/editQuestion"""))
   }
}
        

// @LINE:28
case controllers_Questions_view14(params) => {
   call { 
        invokeHandler(controllers.Questions.view(), HandlerDef(this, "controllers.Questions", "view", Nil,"GET", """""", Routes.prefix + """admin/questions"""))
   }
}
        

// @LINE:29
case controllers_User_questions_view15(params) => {
   call { 
        invokeHandler(controllers.User_questions.view(), HandlerDef(this, "controllers.User_questions", "view", Nil,"GET", """""", Routes.prefix + """admin/users_questions"""))
   }
}
        

// @LINE:30
case controllers_User_questions_insert16(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(controllers.User_questions.insert(id), HandlerDef(this, "controllers.User_questions", "insert", Seq(classOf[Long]),"GET", """""", Routes.prefix + """admin/users_questions/insert/$id<[^/]+>"""))
   }
}
        

// @LINE:31
case controllers_User_questions_delete17(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(controllers.User_questions.delete(id), HandlerDef(this, "controllers.User_questions", "delete", Seq(classOf[Long]),"GET", """""", Routes.prefix + """admin/users_questions/delete/$id<[^/]+>"""))
   }
}
        

// @LINE:32
case controllers_Questions_edit18(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(controllers.Questions.edit(id), HandlerDef(this, "controllers.Questions", "edit", Seq(classOf[Long]),"GET", """""", Routes.prefix + """admin/questions/edit/$id<[^/]+>"""))
   }
}
        

// @LINE:33
case controllers_Questions_delete19(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(controllers.Questions.delete(id), HandlerDef(this, "controllers.Questions", "delete", Seq(classOf[Long]),"GET", """""", Routes.prefix + """admin/questions/delete/$id<[^/]+>"""))
   }
}
        

// @LINE:35
case controllers_Games_index20(params) => {
   call { 
        invokeHandler(controllers.Games.index(), HandlerDef(this, "controllers.Games", "index", Nil,"GET", """""", Routes.prefix + """game"""))
   }
}
        

// @LINE:36
case controllers_Games_join21(params) => {
   call { 
        invokeHandler(controllers.Games.join(), HandlerDef(this, "controllers.Games", "join", Nil,"GET", """""", Routes.prefix + """game/join"""))
   }
}
        

// @LINE:40
case controllers_Assets_at22(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
   }
}
        
}
    
}
        