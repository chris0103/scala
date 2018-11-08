package homework.chap02

import akka.actor.{Actor, Status}

class StringReverseActor extends Actor {

  override def receive: Receive = {
    case str : String =>
      val sb: StringBuffer = new StringBuffer(str)
      sb.reverse().toString
    case _ => sender() ! Status.Failure(new Exception("unknown message"))
  }
}
