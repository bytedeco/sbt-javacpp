# SBT-JavaCPP [![Join the chat at https://gitter.im/bytedeco/sbt-javacpp](https://badges.gitter.im/bytedeco/sbt-javacpp.svg)](https://gitter.im/bytedeco/sbt-javacpp?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.bytedeco/sbt-javacpp/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.bytedeco/sbt-javacpp) [![Build Status](https://travis-ci.org/bytedeco/sbt-javacpp.svg?branch=master)](https://travis-ci.org/bytedeco/sbt-javacpp)

Makes it easy to start playing around with JavaCPP in an SBT project.

## Usage

In your `project/plugins.sbt`:

```scala
addSbtPlugin("org.bytedeco" % "sbt-javacpp" % version)
```

For the latest `version`, see the Maven badge at the top.

Adding the above line will set up your project's classpath to include `maven-plugins` as well as a add a dependency on
the core JavaCPP library.

Due to the sbt limitation, add ```fork := true``` to ```build.sbt```.

To add a dependency on a JavaCPP preset in your project, the following snippet will do that for you, taking care
of adding the proper native preset for your target platform as well. Remove ```-platform``` from the ```artifactId```:

```scala
// in build.sbt

javaCppPresetLibs ++= Seq("opencv" -> "4.4.0", "opencv-gpu" -> "4.4.0", "mkl-redist" -> "2020.3")
fork := true

```

## Customisation

By default, this plugin will download the appropriate binaries for the platform of the computer currently
running SBT, you can modify this by setting it to another platform (for example, if you want to compile JARs to be run
on other platforms)

```scala
javaCppPlatform := "android-arm"
```

Alternatively, you can set the target platform by passing a System Property: `sbt.javacpp.platform`, which means that
you can change the target platform for your build straight from your command line.

```scala
sbt compile -Dsbt.javacpp.platform="android-arm android-x86"
```

In case you want to select a different javacpp version:

```scala
javaCppVersion := "1.4.3"
```
