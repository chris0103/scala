package homework.chap02

import org.scalatest.{FunSpecLike, Matchers}

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

class StringReverseServiceSpec extends FunSpecLike with Matchers {

  val service : StringReverseService = new StringReverseService()

  describe("string reverse service") {
    describe("given a string") {
      it("should reverse the string") {
        val future : Future[Any] = service.reverseString("hello")
        val result: String = Await.result(future.mapTo[String], 2 seconds)
        result should equal("olleh")
      }
    }

    describe("given unknown type") {
      it("should fail on unknown types") {
        val future : Future[Any] = service.reverseString(new Object())
        intercept[Exception] {
          val result = Await.result(future.mapTo[String], 2 seconds)
          result should equal("unknown message")
        }
      }
    }
  }
}
