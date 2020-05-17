version := "0.1"

scalaVersion := "2.12.11"

libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"

javaCppPresetLibs ++= Seq("mkl" -> "2020.1", "mkl-redist" -> "2020.1")

fork := true
