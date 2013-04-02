import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "GameQuiz"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean,
    "mysql" % "mysql-connector-java" % "5.1.22",
    "org.apache.directory.studio" % "org.apache.commons.codec" % "1.6",
    "com.googlecode.json-simple" % "json-simple" % "1.1.1",
    "com.restfb" % "restfb" % "1.6.11"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  )

}
