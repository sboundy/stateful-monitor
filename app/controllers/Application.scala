package controllers

import play.api._
import play.api.mvc._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import scala.concurrent.duration._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.concurrent.Promise
import scala.concurrent._
import play.api.libs.ws._
import scala.concurrent.Future
import play.api.libs.iteratee._
import java.io._
import play.api.db._
import play.api.Play.current


object Application extends Controller {


	def index = Action {
	  
	Ok(views.html.index("Test"))
      
	}
	
	//def index = Action.async {
    
		//WS.url("http://www.bbc.co.uk").get().map {
			//response =>
			//Ok("Feed title: " + (response.body))
			
		//}
	//}
}        