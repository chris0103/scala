package homework.chap01

import akka.actor.Actor

class MessageActor extends Actor {

  var message : String = _

  override def receive: Receive = {
    case str : String => message = str
  }
}
