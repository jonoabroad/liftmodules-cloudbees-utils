package net.liftmodules.cloudbees.utils.library

import dispatch.Http
import dispatch.url
import net.liftweb.common.Box
import net.liftweb.common.Loggable
import net.liftweb.http.rest.RestHelper
import net.liftweb.json.JsonAST._
import net.liftweb.json.JsonAST.JField
import net.liftweb.json.JsonAST.JString
import net.liftweb.json.JsonParser
import net.liftweb.util.Helpers._
import net.liftweb.util.Props
import net.liftweb.json.DefaultFormats

object AppCellREST extends RestHelper with Loggable {
  serve { case "appcell" :: "whoami" :: _ Get req ⇒ JObject(List(JField("addr", JString(req.remoteAddr)))) }
}
object AppCell  extends Loggable {

  implicit val formats = DefaultFormats
  
  private[this] def json(host: String) = {
    val http = new Http()
    val u = url("%s/appcell/whoami.json".format(host.trim))
    logger.debug("Call %s".format(u))
    http(u >- JsonParser.parse)
  }

  lazy val ip: Option[String] = Props.get("cloudbees.util.appcell.host").flatMap { host ⇒ (json(host).\("addr")).extractOpt[String] }

  private[this] val rx = """.*/(\d+)""".r

  lazy val port:Option[Int] = Box.legacyNullTest(System.getProperty("user.home")).flatMap { u ⇒
    tryo {    
      val rx(port) = u
      logger.debug("PORT %s".format(port))      
      port.toInt
    }
  }

}