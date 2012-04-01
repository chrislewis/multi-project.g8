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
                Shared.specsDep(v):_*
            ))))
            
  def project(id: String, base: File, settings: Seq[Project.Setting[_]] = Nil) =
    Project(id = id,
            base = base,
            settings = Project.defaultSettings ++ Shared.settings ++ settings)
}

object Shared {
  
  /** Resolve specs version for the current scala version (thanks @n8han). */
  def specsDep(sv: String, cfg: String = "test") =
    (sv.split("[.-]").toList match {
      case "2" :: "8" :: "1" :: Nil =>
        "org.specs2" %% "specs2" % "1.5" ::
        "org.specs2" %% "specs2-scalaz-core" % "5.1-SNAPSHOT" ::
        Nil
      case "2" :: "9" :: "0" :: _ => "org.specs2" % "specs2_2.9.1" % "1.7.1" :: Nil
      case "2" :: "9" :: _ :: _ => "org.specs2" % "specs2_2.9.1" % "1.8.2" :: Nil
      case _ => sys.error("Specs not supported for scala version %s" format sv)
    }) map (_ % cfg)
  
  val settings = Seq(
    organization := "$organization$",
    version := "$version$",
    scalaVersion := "2.9.1",
    crossScalaVersions := Seq("2.8.1, 2.9.0, 2.9.0-1"),
    resolvers += "Scala-Tools Maven2 Snapshots Repository" at "http://scala-tools.org/repo-snapshots",
    initialCommands := "import $organization$.$name;format="normalize,word"$._"
  )
  
}
