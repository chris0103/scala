package com.akkademy

import akka.actor.{Actor, ActorSystem, Props, Status}
import akka.event.Logging
import com.akkademy.messages.{GetRequest, KeyNotFoundException, SetRequest}

import scala.collection.mutable

object Main extends App {

  val system = ActorSystem("akkademy")
  val actor = system.actorOf(Props[AkkademyDb], "akkademy-db")
}

class AkkademyDb extends Actor {

  val map = new mutable.HashMap[String, String]

  val log = Logging(context.system, this)

  override def receive: PartialFunction[Any, Unit] = {
    case SetRequest(key, value) =>
      log.info("received SetRequest - key: {} value: {}", key, value)
      map.put(key, value)
    case GetRequest(key) =>
      log.info("received GetRequest - key: {}", key)
      val response: Option[String] = map.get(key)
      response match {
        case Some(x) => sender() ! x
        case None => sender() ! Status.Failure(KeyNotFoundException(key))
      }
    case _ => Status.Failure(new ClassNotFoundException)
  }
}
