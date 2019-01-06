package com.akkademy

import com.akkademy.messages.KeyNotFoundException
import org.scalatest.{FunSpecLike, Matchers}

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.language.postfixOps

class SClientIntegrationSpec extends FunSpecLike with Matchers {

  val client = new SClient("127.0.0.1:2552")

  describe("akkademyDbClient") {
      it("should set a value") {
        client.set("123", new Integer(123))
        val futureResult = client.get("123")
        val result = Await.result(futureResult, 2 seconds)
        result should equal(123)
      }

      it("should fail on missing key") {
        import scala.concurrent.ExecutionContext.Implicits.global

        client.set("123", new Integer(123))
        val future = client.get("321").recover {
          case t : KeyNotFoundException => constructErrorMessage(t)
        }
        val result = Await.result(future, 2 seconds)
        result should equal(constructErrorMessage(KeyNotFoundException("321")))
      }
  }

  def constructErrorMessage(t: KeyNotFoundException) : String = s"${t.getClass}[${t.key}]"
}
