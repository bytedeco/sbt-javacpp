package com.beachape.sbt.javacpp

import scala.language.postfixOps
import sbt._
import sbt.Keys._

object Plugin extends AutoPlugin {

  override def projectSettings: Seq[Setting[_]] = {
    import autoImport._
    Seq(
      autoCompilerPlugins := true,
      classpathTypes += "maven-plugin",
      javaCppPlatform := Platform.current,
      javaCppVersion := Versions.javaCppVersion,
      javaCppPresetLibs := Seq.empty,
      libraryDependencies <+= javaCppVersion { resolvedJavaCppVersion =>
        "org.bytedeco" % "javacpp" % resolvedJavaCppVersion
      },
      javaCppPresetDependencies
    )
  }

  object Versions {
    val javaCppVersion = "1.1"
  }

  object autoImport {
    val javaCppPlatform = SettingKey[String]("javaCppPlatform", """The platform that you want to compile for (defaults to the platform of the current computer). You can also set this via the "sbt.javacpp.platform" System Property """)
    val javaCppVersion = SettingKey[String]("javaCppVersion", s"Version of Java CPP that you want to use, defaults to ${Versions.javaCppVersion}")
    val javaCppPresetLibs = SettingKey[Seq[(String, String)]]("javaCppPresetLibs", "List of additional JavaCPP presets that you would wish to bind lazily, defaults to an empty list")
  }

  override def requires: Plugins = plugins.JvmPlugin

  override def trigger: PluginTrigger = allRequirements

  private def javaCppPresetDependencies: Def.Setting[Seq[ModuleID]] = {
    import autoImport._
    libraryDependencies <++= (javaCppPlatform, javaCppVersion, javaCppPresetLibs) {
      (resolvedJavaCppPlatform, resolvedJavaCppVersion, resolvedJavaCppPresetLibs) =>
        resolvedJavaCppPresetLibs.flatMap {
          case (libName, libVersion) =>
            Seq(
              "org.bytedeco.javacpp-presets" % libName % s"$libVersion-$resolvedJavaCppVersion" classifier "",
              "org.bytedeco.javacpp-presets" % libName % s"$libVersion-$resolvedJavaCppVersion" classifier resolvedJavaCppPlatform
            )
        }
    }
  }

}