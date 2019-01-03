package homework.chap02

import akka.dispatch.Futures
import akka.util.Timeout
import org.scalatest.{FunSpecLike, Matchers}

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

class StringReverseServiceSpec extends FunSpecLike with Matchers {

  val service : StringReverseService = new StringReverseService()

  implicit val timeout: Timeout = Timeout(5 seconds)

  describe("string reverse service") {
    describe("given a string") {
      it("should reverse the string") {
        val future : Future[String] = service.reverseString("hello")
        val result: String = Await.result(future, 2 seconds)
        result should equal("olleh")
      }
    }

    describe("given unknown type") {
      it("should fail on unknown types") {
        val future : Future[String] = service.reverseString(new Object())
        intercept[Exception] {
          val result = Await.result(future, 2 seconds)
          result should equal("unknown message")
        }
      }
    }

    describe("given a list of String") {
      it("should reverse all the strings") {
        import scala.concurrent.ExecutionContext.Implicits.global

        val strs : List[String] = List("Apple", "Banana", "Citrus")
        val results : Map[String, String] = Map("Apple" -> "elppA", "Banana" -> "ananaB", "Citrus" -> "surtiC")
        val listOfFuture : List[Future[String]] = strs.map(service.reverseString(_))
        val futureOfList : Future[List[String]] = Future.sequence(listOfFuture).onSuccess({
          case str : String => results(str)
        })
      }
    }
  }
}
