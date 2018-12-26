package homework.chap02

import akka.actor.{Actor, ActorLogging, Status}

class StringReverseActor extends Actor with ActorLogging {

  override def receive: Receive = {
    case str : String =>
      log.info("{} received.", str)
      val sb: StringBuffer = new StringBuffer(str)
      sender() ! sb.reverse().toString
    case _ => sender() ! Status.Failure(new Exception("unknown message"))
  }
}
