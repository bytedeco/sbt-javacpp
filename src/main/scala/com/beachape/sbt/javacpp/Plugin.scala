package com.beachape.sbt.javacpp

import scala.language.postfixOps
import sbt._
import sbt.Keys._

object Plugin extends AutoPlugin {

  override def projectSettings: Seq[Setting[_]] = {
    import autoImport._
    Seq(
      autoCompilerPlugins := true,
      classpathTypes += "maven-plugin", // Some dependencies like `javacpp` are packaged with maven-plugin packaging
      javaCppPlatform := Platform.current,
      javaCppVersion := Versions.javaCppVersion,
      javaCppPresetsVersion := Versions.javaCppPresentsVersion,
      libraryDependencies <+= javaCppVersion { resolvedJavaCppVersion =>
        "org.bytedeco" % "javacpp" % resolvedJavaCppVersion
      }
    )
  }

  object Versions {
    val javaCppVersion = "1.1"
    val javaCppPresentsVersion = "3.0.0"
  }

  object autoImport {
    val javaCppPlatform = SettingKey[String]("javaCppPlatform", """The platform that you want to compile for (defaults to the platform of the current computer). You can also set this via the "sbt.javacpp.platform" System Property """)
    val javaCppVersion = SettingKey[String]("javaCppVersion", s"Version of Java CPP that you want to use, defaults to ${Versions.javaCppVersion}")
    val javaCppPresetsVersion = SettingKey[String]("javaCppPresetsVersion", s"Version of Java CPP Presets that you want to use, defaults to ${Versions.javaCppVersion}")
  }

  override def requires: Plugins = plugins.JvmPlugin

  override def trigger: PluginTrigger = allRequirements

}