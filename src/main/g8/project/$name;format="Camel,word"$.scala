import sbt._
import Keys._

object $name;format="Camel,word"$ extends sbt.Build {

  lazy val root =
    project(id = "$name;format="normalize"$-all",
            base = file(".")) aggregate(core)

  lazy val core =
    project(id = "$name;format="normalize"$-core",
            base = file("$name$-core"),
            settings = Seq(
              libraryDependencies <++= scalaVersion (v => Seq(
                Shared.specsDep(v) % "test"
            ))))
            
  def project(id: String, base: File, settings: Seq[Project.Setting[_]] = Nil) =
    Project(id = id,
            base = base,
            settings = Project.defaultSettings ++ Shared.settings ++ settings)
}

object Shared {
  
  /** Resolve specs version for the current scala version (thanks @n8han). */
  def specsDep(sv: String) =
    sv.split("[.-]").toList match {
      case "2" :: "8" :: _ => "org.scala-tools.testing" % "specs_2.8.1" % "1.6.8"
      case "2" :: "9" :: "1" :: _ => "org.scala-tools.testing" % "specs_2.9.1" % "1.6.9"
      case "2" :: "9" :: _ => "org.scala-tools.testing" %% "specs" % "1.6.8"
      case _ => sys.error("Specs not supported for scala version %s" format sv)
    }
  
  val settings = Seq(
    organization := "$organization$",
    version := "$version$",
    scalaVersion := "2.9.1",
    crossScalaVersions := Seq("2.8.1, 2.9.0, 2.9.0-1"),
    initialCommands := "import $organization$.$name;format="normalize,word"$._"
  )
  
}
