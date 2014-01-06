package utilsCalculations

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

class DatabaseWorker {
	
	def historyCount(urlToCheck : String, checksumToCheck : String): Int = {
		
		DB.withConnection("checksum") { conn =>
	   
  			val resultset2 : ResultSet  = conn.prepareStatement("select count(*) as count from chksumstore where url = '" + urlToCheck + "'").executeQuery()
  			resultset2.next
  			val resultset2Size : Int = resultset2.getInt("COUNT")
  			return resultset2Size
  		
		}
	}
	
	def insertChecksumValue(urlToCheck : String, checksumToCheck : String){
	  
	  DB.withConnection("checksum") { conn =>
	   
  			conn.prepareStatement("insert into chksumstore values ('" + urlToCheck + "', '" + checksumToCheck + "')").execute()
  		
		}
	}
	
	def getLastChecksumValue(urlToCheck : String):String = {
		
		DB.withConnection("checksum") { conn =>
	   
  			val resultset1 : ResultSet = conn.prepareStatement("select * from chksumstore where url = '" + urlToCheck + "'").executeQuery()	
  			resultset1.next
  			val checksumResult : String = resultset1.getString("CHCKSUM")
  			return checksumResult
  			
		}
	}
	
	def updateChecksumValue(urlToCheck : String, currentChecksum : String) {
	  
		DB.withConnection("checksum") { conn =>
	   
  			conn.prepareStatement("update chksumstore set chcksum = '" + currentChecksum + "' where url = '" + urlToCheck + "'").execute()	
  			
		}
	  
	}
}