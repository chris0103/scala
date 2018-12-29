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

    describe("given a list of String") {
      it("should reverse all the strings") {
        import scala.concurrent.ExecutionContext.Implicits.global

        val strs : List[String] = List("Apple", "Banana", "Citrus")
        val results : Map[String, String] = Map("Apple" -> "elppA", "Banana" -> "ananaB", "Citrus" -> "surtiC")
        val listOfFuture : List[Future[Any]] = strs.map(service.reverseString(_))
        val futureOfList : Future[List[Any]] = Future.sequence(listOfFuture.map(future => future.recover{
          case _ : Exception => ""
        }))
        futureOfList.onSuccess({
          case str : String => results(str)
        })
      }
    }
  }
}
