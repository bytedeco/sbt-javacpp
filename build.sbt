name := "sbt-javacpp"

version := "1.17-SNAPSHOT"

organization := "org.bytedeco"

sbtPlugin := true

publishMavenStyle := true

scalaVersion in Global := "2.12.11"

sbtVersion in Global := "1.3.10"

crossSbtVersions := Vector("0.13.18", "1.3.10")

scalaCompilerBridgeSource := {
  val sv = appConfiguration.value.provider.id.version
  ("org.scala-sbt" % "compiler-interface" % sv % "component").sources
}

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

libraryDependencies += "org.bytedeco" % "javacpp" % "1.5.4"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-Xlint", "-Xlog-free-terms")

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (version.value.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

pomExtra :=
  <url>https://github.com/bytedeco/sbt-javacpp</url>
    <licenses>
      <license>
        <name>MIT</name>
        <url>http://opensource.org/licenses/MIT</url>
        <distribution>repo</distribution>
      </license>
    </licenses>
    <scm>
      <url>git@github.com:bytedeco/sbt-javacpp.git</url>
      <connection>scm:git:git@github.com:bytedeco/sbt-javacpp.git</connection>
    </scm>
    <developers>
      <developer>
        <id>lloydmeta</id>
        <name>Lloyd Chan</name>
        <url>https://beachape.com</url>
      </developer>
    </developers>
