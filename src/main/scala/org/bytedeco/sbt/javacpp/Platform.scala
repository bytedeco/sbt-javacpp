package org.bytedeco.sbt.javacpp

import org.bytedeco.javacpp.Loader

/**
 * Created by Lloyd on 2/22/16.
 */

object Platform {

  private val platformOverridePropertyKey: String = "sbt.javacpp.platform"

  /**
   * To override, set the "sbt.javacpp.platform" System Property. Multiple platforms can be passed as a space-separated string
   *
   * @example
   * {{{
   * sbt compile -Dsbt.javacpp.platform="android-arm android-x86"
   * }}}
   */
  val current: Seq[String] = sys.props.get(platformOverridePropertyKey) match {
    case Some(platform) if platform.trim().nonEmpty => platform.split(' ')
    case _ => Seq(Loader.Detector.getPlatform)
  }

}
