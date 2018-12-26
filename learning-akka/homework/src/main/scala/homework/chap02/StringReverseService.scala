package homework.chap02

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.util.Timeout
import akka.pattern.ask

import scala.concurrent.Future
import scala.concurrent.duration._

class StringReverseService {

  implicit val timeout: Timeout = Timeout(2 seconds)

  val system : ActorSystem = ActorSystem()

  val actor: ActorRef = system.actorOf(Props[StringReverseActor])

  def reverseString(msg : Any) : Future[Any] = {
    actor ? msg
  }
}
