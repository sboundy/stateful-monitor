package utilsCalculations

import play.api.Application
import play.api.GlobalSettings
import play.api.Logger
import play.api.Play
import akka.actor.Props
import play.api._
import akka.actor.Actor
import play.api.libs.ws._
import sun.security.krb5.Checksum
import play.api.db._
import java.sql._

class DifferenceEngine {
  
	def getCheckSomeDifference(urlToCheck : String, checksumToCheck : String) {

		val databaseWorker = new utilsCalculations.DatabaseWorker
			
		if (databaseWorker.historyCount(urlToCheck, checksumToCheck)==0){
  	
			databaseWorker.insertChecksumValue(urlToCheck, checksumToCheck)	
  		
		}
  		
		else {
			
		  	if(databaseWorker.getLastChecksumValue(urlToCheck)==checksumToCheck){
  	  			  
  				Logger.debug("The same!")
  	  			
  			}
  			else{
  				  
  				  //Get the date difference.  Compare to configured value...then pass back true or false
  				 Logger.debug("Different so update to new value")
  				 databaseWorker.updateChecksumValue(urlToCheck, checksumToCheck)
  				 
  			}
		  			
  		}
  	}  		
}