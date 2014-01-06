import scala.concurrent.duration.DurationInt
import akka.actor.Props.apply
import play.api.Application
import play.api.GlobalSettings
import play.api.Logger
import play.api.Play
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.concurrent.Akka
import akka.actor.Props
import play.api._
import play.api.Play.current
import akka.actor.Actor
import play.api.libs.concurrent.Execution.Implicits._
import play.api.libs.ws._
import sun.security.krb5.Checksum
import play.api.db._
import java.sql.ResultSet


object Global extends GlobalSettings {

  	
	override def onStart(app: Application) {
  	  
  		val NAPMain = "json"
  		val requesturl1 : String = "http://www.net-a-porter.com/webapi/feed/sessioncount/NAP.json"
  		val FormatActor = Akka.system.actorOf(Props(new Actor {
  			def receive = {
  				case NAPMain => requestURL(requesturl1) 					  						
  			}
  		}))
  		
		Akka.system.scheduler.schedule(0.seconds, 2.seconds, FormatActor, "json")
  }		
  	
  def requestURL(url : String) {
  	  
	  WS.url(url).get().map {
  			response =>
  			val requestBody = response.body
  			Logger.info("Feed title: " + (requestBody))
  			val checksumMdFive = new utilsCalculations.ChecksumMdFive
  			val resultchecksum : String = checksumMdFive.md5SumString(requestBody.toString())
  				
  			Logger.debug(resultchecksum)
  			
  			val differenceEngine = new utilsCalculations.DifferenceEngine
  			differenceEngine.getCheckSomeDifference(url, resultchecksum)
	  }
  }
}