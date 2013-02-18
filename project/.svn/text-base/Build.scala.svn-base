import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "play2-morphia"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean,
    "org.mongodb" % "mongo-java-driver" % "2.8.0",
    "org.jongo" % "jongo" % "0.3"
    //"uk.co.panaxiom" %% "play-jongo" % "0.3"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
   resolvers += Resolver.url("My GitHub Play Repository", url("http://alexanderjarvis.github.com/releases/"))(Resolver.ivyStylePatterns)
  )
}
