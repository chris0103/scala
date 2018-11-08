package homework.chap01

import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import org.scalatest.{FunSpecLike, Matchers}

class MessageActorSpec extends FunSpecLike with Matchers {

  implicit val system: ActorSystem = ActorSystem()

  describe("message actor") {
    describe("given one message") {
      it("should store the message") {
        val messageActor = TestActorRef(new MessageActor)
        messageActor ! "Hello!"
        val message = messageActor.underlyingActor.message
        message should equal("Hello!")
      }
    }

    describe("given two messages") {
      it("should store the second message") {
        val messageActor = TestActorRef(new MessageActor)
        messageActor ! "hello"
        messageActor ! "world"
        val message = messageActor.underlyingActor.message
        message should equal("world")
      }
    }
  }
}
