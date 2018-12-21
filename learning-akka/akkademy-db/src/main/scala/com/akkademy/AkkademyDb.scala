package com.akkademy

import akka.actor.{Actor, ActorRef, ActorSystem, Props, Status}
import akka.event.{Logging, LoggingAdapter}
import com.akkademy.messages.{GetRequest, KeyNotFoundException, SetRequest}

import scala.collection.mutable

object Main extends App {

  val system: ActorSystem = ActorSystem("akkademy")
  val actor: ActorRef = system.actorOf(Props[AkkademyDb], "akkademy-db")
}

class AkkademyDb extends Actor {

  val map: mutable.Map[String, Object] = new mutable.HashMap[String, Object]

  val log: LoggingAdapter = Logging(context.system, this)

  override def receive: PartialFunction[Any, Unit] = {
    case SetRequest(key, value) =>
      log.info("received SetRequest - key: {} value: {}", key, value)
      map.put(key, value)
    case GetRequest(key) =>
      log.info("received GetRequest - key: {}", key)
      val response: Option[Object] = map.get(key)
      response match {
        case Some(x) => sender() ! x
        case None => sender() ! Status.Failure(KeyNotFoundException(key))
      }
    case _ => Status.Failure(new ClassNotFoundException)
  }
}
