package homework.chap02

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import akka.util.Timeout
import pong.ScalaPongActor

import scala.concurrent.duration._

class DeliveryActor extends Actor with ActorLogging {

  implicit val timeout: Timeout = Timeout(5 seconds)

  val pongActor : ActorRef = context.actorOf(Props[ScalaPongActor])

  override def receive: Receive = {
    case "Ping" =>
      log.info("Received {}.", "Ping")
      val sender = sender()

  }
}
