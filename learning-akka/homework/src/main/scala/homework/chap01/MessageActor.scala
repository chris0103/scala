package homework.chap01

import akka.actor.{Actor, Status}

class MessageActor extends Actor {

  var message : String = _

  override def receive: Receive = {
    case str : String => message = str
    case _ => sender() ! Status.Failure(new Exception("unknown message"))
  }
}
