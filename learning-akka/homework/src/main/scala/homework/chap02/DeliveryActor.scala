package homework.chap02

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import akka.util.Timeout
import akka.pattern.ask
import pong.ScalaPongActor

import scala.concurrent.{Await, ExecutionContextExecutor, Future}
import scala.concurrent.duration._

class DeliveryActor extends Actor with ActorLogging {

  implicit val timeout: Timeout = Timeout(5 seconds)
  implicit val ec: ExecutionContextExecutor = context.dispatcher

  val pongActor : ActorRef = context.actorOf(Props[ScalaPongActor])

  val service : StringReverseService = new StringReverseService()

  override def receive: Receive = {
    case "Ping" =>
      log.info("Received {}.", "Ping")
      val senderActor: ActorRef = sender()
      askPong("Ping").onSuccess({
        case msg => senderActor ! msg
      })
    case msg : String =>
      log.info("Received {}.", msg)
      val senderActor: ActorRef = sender()
      service.reverseString(msg).onSuccess({
        case msg => senderActor ! msg
      })
    case x =>
      println("unknown message " + x.getClass)
  }

  def askPong(message: String) : Future[String] = (pongActor ? message).mapTo[String]
}
