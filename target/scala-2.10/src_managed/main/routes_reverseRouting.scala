// @SOURCE:/home/r/work/FbPokerQuiz/conf/routes
// @HASH:911a2957d17f5764443340a71bb8c641f56529f8
// @DATE:Tue Apr 02 16:41:06 EEST 2013

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString


// @LINE:40
// @LINE:36
// @LINE:35
// @LINE:33
// @LINE:32
// @LINE:31
// @LINE:30
// @LINE:29
// @LINE:28
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:14
// @LINE:13
// @LINE:11
// @LINE:10
// @LINE:8
// @LINE:7
// @LINE:6
package controllers {

// @LINE:31
// @LINE:30
// @LINE:29
// @LINE:11
// @LINE:10
class ReverseUser_questions {
    

// @LINE:29
def view(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "admin/users_questions")
}
                                                

// @LINE:31
def delete(id:Long): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "admin/users_questions/delete/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                

// @LINE:11
def save(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "question")
}
                                                

// @LINE:10
def add(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "question/add")
}
                                                

// @LINE:30
def insert(id:Long): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "admin/users_questions/insert/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                
    
}
                          

// @LINE:40
class ReverseAssets {
    

// @LINE:40
def at(file:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                
    
}
                          

// @LINE:33
// @LINE:32
// @LINE:28
// @LINE:26
// @LINE:25
// @LINE:24
class ReverseQuestions {
    

// @LINE:28
def view(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "admin/questions")
}
                                                

// @LINE:33
def delete(id:Long): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "admin/questions/delete/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                

// @LINE:26
def editQuestion(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "admin/editQuestion")
}
                                                

// @LINE:25
def save(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "admin/question")
}
                                                

// @LINE:32
def edit(id:Long): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "admin/questions/edit/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                

// @LINE:24
def add(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "admin/question/add")
}
                                                
    
}
                          

// @LINE:36
// @LINE:35
class ReverseGames {
    

// @LINE:36
def join(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "game/join")
}
                                                

// @LINE:35
def index(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "game")
}
                                                
    
}
                          

// @LINE:14
// @LINE:13
class ReverseFb_users {
    

// @LINE:14
def convert(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "for_me/convert")
}
                                                

// @LINE:13
def for_me(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "for_me")
}
                                                
    
}
                          

// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:19
class ReverseAdmins {
    

// @LINE:20
def authenticate(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "login")
}
                                                

// @LINE:21
def logout(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "logout")
}
                                                

// @LINE:19
def login(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "login")
}
                                                

// @LINE:22
def index(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "admin")
}
                                                
    
}
                          

// @LINE:8
// @LINE:7
// @LINE:6
class ReverseCanvas {
    

// @LINE:7
def redirect(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "canvas/")
}
                                                

// @LINE:8
// @LINE:6
def index(): Call = {
   () match {
// @LINE:6
case () if true => Call("POST", _prefix + { _defaultPrefix } + "canvas/")
                                                        
// @LINE:8
case () if true => Call("GET", _prefix + { _defaultPrefix } + "home/")
                                                        
   }
}
                                                
    
}
                          
}
                  


// @LINE:40
// @LINE:36
// @LINE:35
// @LINE:33
// @LINE:32
// @LINE:31
// @LINE:30
// @LINE:29
// @LINE:28
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:14
// @LINE:13
// @LINE:11
// @LINE:10
// @LINE:8
// @LINE:7
// @LINE:6
package controllers.javascript {

// @LINE:31
// @LINE:30
// @LINE:29
// @LINE:11
// @LINE:10
class ReverseUser_questions {
    

// @LINE:29
def view : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.User_questions.view",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/users_questions"})
      }
   """
)
                        

// @LINE:31
def delete : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.User_questions.delete",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/users_questions/delete/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:11
def save : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.User_questions.save",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "question"})
      }
   """
)
                        

// @LINE:10
def add : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.User_questions.add",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "question/add"})
      }
   """
)
                        

// @LINE:30
def insert : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.User_questions.insert",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/users_questions/insert/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        
    
}
              

// @LINE:40
class ReverseAssets {
    

// @LINE:40
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        
    
}
              

// @LINE:33
// @LINE:32
// @LINE:28
// @LINE:26
// @LINE:25
// @LINE:24
class ReverseQuestions {
    

// @LINE:28
def view : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Questions.view",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/questions"})
      }
   """
)
                        

// @LINE:33
def delete : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Questions.delete",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/questions/delete/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:26
def editQuestion : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Questions.editQuestion",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/editQuestion"})
      }
   """
)
                        

// @LINE:25
def save : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Questions.save",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/question"})
      }
   """
)
                        

// @LINE:32
def edit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Questions.edit",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/questions/edit/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:24
def add : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Questions.add",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/question/add"})
      }
   """
)
                        
    
}
              

// @LINE:36
// @LINE:35
class ReverseGames {
    

// @LINE:36
def join : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Games.join",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "game/join"})
      }
   """
)
                        

// @LINE:35
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Games.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "game"})
      }
   """
)
                        
    
}
              

// @LINE:14
// @LINE:13
class ReverseFb_users {
    

// @LINE:14
def convert : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Fb_users.convert",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "for_me/convert"})
      }
   """
)
                        

// @LINE:13
def for_me : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Fb_users.for_me",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "for_me"})
      }
   """
)
                        
    
}
              

// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:19
class ReverseAdmins {
    

// @LINE:20
def authenticate : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Admins.authenticate",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "login"})
      }
   """
)
                        

// @LINE:21
def logout : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Admins.logout",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "logout"})
      }
   """
)
                        

// @LINE:19
def login : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Admins.login",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "login"})
      }
   """
)
                        

// @LINE:22
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Admins.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin"})
      }
   """
)
                        
    
}
              

// @LINE:8
// @LINE:7
// @LINE:6
class ReverseCanvas {
    

// @LINE:7
def redirect : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Canvas.redirect",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "canvas/"})
      }
   """
)
                        

// @LINE:8
// @LINE:6
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Canvas.index",
   """
      function() {
      if (true) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "canvas/"})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "home/"})
      }
      }
   """
)
                        
    
}
              
}
        


// @LINE:40
// @LINE:36
// @LINE:35
// @LINE:33
// @LINE:32
// @LINE:31
// @LINE:30
// @LINE:29
// @LINE:28
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:14
// @LINE:13
// @LINE:11
// @LINE:10
// @LINE:8
// @LINE:7
// @LINE:6
package controllers.ref {

// @LINE:31
// @LINE:30
// @LINE:29
// @LINE:11
// @LINE:10
class ReverseUser_questions {
    

// @LINE:29
def view(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.User_questions.view(), HandlerDef(this, "controllers.User_questions", "view", Seq(), "GET", """""", _prefix + """admin/users_questions""")
)
                      

// @LINE:31
def delete(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.User_questions.delete(id), HandlerDef(this, "controllers.User_questions", "delete", Seq(classOf[Long]), "GET", """""", _prefix + """admin/users_questions/delete/$id<[^/]+>""")
)
                      

// @LINE:11
def save(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.User_questions.save(), HandlerDef(this, "controllers.User_questions", "save", Seq(), "POST", """""", _prefix + """question""")
)
                      

// @LINE:10
def add(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.User_questions.add(), HandlerDef(this, "controllers.User_questions", "add", Seq(), "GET", """""", _prefix + """question/add""")
)
                      

// @LINE:30
def insert(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.User_questions.insert(id), HandlerDef(this, "controllers.User_questions", "insert", Seq(classOf[Long]), "GET", """""", _prefix + """admin/users_questions/insert/$id<[^/]+>""")
)
                      
    
}
                          

// @LINE:40
class ReverseAssets {
    

// @LINE:40
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      
    
}
                          

// @LINE:33
// @LINE:32
// @LINE:28
// @LINE:26
// @LINE:25
// @LINE:24
class ReverseQuestions {
    

// @LINE:28
def view(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Questions.view(), HandlerDef(this, "controllers.Questions", "view", Seq(), "GET", """""", _prefix + """admin/questions""")
)
                      

// @LINE:33
def delete(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Questions.delete(id), HandlerDef(this, "controllers.Questions", "delete", Seq(classOf[Long]), "GET", """""", _prefix + """admin/questions/delete/$id<[^/]+>""")
)
                      

// @LINE:26
def editQuestion(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Questions.editQuestion(), HandlerDef(this, "controllers.Questions", "editQuestion", Seq(), "POST", """""", _prefix + """admin/editQuestion""")
)
                      

// @LINE:25
def save(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Questions.save(), HandlerDef(this, "controllers.Questions", "save", Seq(), "POST", """""", _prefix + """admin/question""")
)
                      

// @LINE:32
def edit(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Questions.edit(id), HandlerDef(this, "controllers.Questions", "edit", Seq(classOf[Long]), "GET", """""", _prefix + """admin/questions/edit/$id<[^/]+>""")
)
                      

// @LINE:24
def add(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Questions.add(), HandlerDef(this, "controllers.Questions", "add", Seq(), "GET", """""", _prefix + """admin/question/add""")
)
                      
    
}
                          

// @LINE:36
// @LINE:35
class ReverseGames {
    

// @LINE:36
def join(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Games.join(), HandlerDef(this, "controllers.Games", "join", Seq(), "GET", """""", _prefix + """game/join""")
)
                      

// @LINE:35
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Games.index(), HandlerDef(this, "controllers.Games", "index", Seq(), "GET", """""", _prefix + """game""")
)
                      
    
}
                          

// @LINE:14
// @LINE:13
class ReverseFb_users {
    

// @LINE:14
def convert(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Fb_users.convert(), HandlerDef(this, "controllers.Fb_users", "convert", Seq(), "POST", """""", _prefix + """for_me/convert""")
)
                      

// @LINE:13
def for_me(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Fb_users.for_me(), HandlerDef(this, "controllers.Fb_users", "for_me", Seq(), "GET", """""", _prefix + """for_me""")
)
                      
    
}
                          

// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:19
class ReverseAdmins {
    

// @LINE:20
def authenticate(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Admins.authenticate(), HandlerDef(this, "controllers.Admins", "authenticate", Seq(), "POST", """""", _prefix + """login""")
)
                      

// @LINE:21
def logout(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Admins.logout(), HandlerDef(this, "controllers.Admins", "logout", Seq(), "GET", """""", _prefix + """logout""")
)
                      

// @LINE:19
def login(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Admins.login(), HandlerDef(this, "controllers.Admins", "login", Seq(), "GET", """""", _prefix + """login""")
)
                      

// @LINE:22
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Admins.index(), HandlerDef(this, "controllers.Admins", "index", Seq(), "GET", """""", _prefix + """admin""")
)
                      
    
}
                          

// @LINE:8
// @LINE:7
// @LINE:6
class ReverseCanvas {
    

// @LINE:7
def redirect(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Canvas.redirect(), HandlerDef(this, "controllers.Canvas", "redirect", Seq(), "GET", """""", _prefix + """canvas/""")
)
                      

// @LINE:6
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Canvas.index(), HandlerDef(this, "controllers.Canvas", "index", Seq(), "POST", """ Home page""", _prefix + """canvas/""")
)
                      
    
}
                          
}
                  
      