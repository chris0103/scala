package homework.chap02

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.util.Timeout
import akka.pattern.ask
import org.scalatest.{FunSpecLike, Matchers}

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

class DeliveryActorSpec extends FunSpecLike with Matchers {

  val system = ActorSystem()

  implicit val timeout: Timeout = Timeout(5 seconds)

  val deliveryActor : ActorRef = system.actorOf(Props(classOf[DeliveryActor]))

  describe("deliver functionalities") {
    describe("given a \"Ping\"") {
      it("should respond with \"Pong\"") {
        val future : Future[Any] = deliveryActor ? "Ping"
        val result: String = Await.result(future.mapTo[String], 2 seconds)
        result should equal("Pong")
      }
    }

    describe("given any other String") {
      it("should return reversed String") {
        val future : Future[Any] = deliveryActor ? "Hello"
        val result: String = Await.result(future.mapTo[String], 2 seconds)
        result should equal("olleH")
      }
    }
  }
}
