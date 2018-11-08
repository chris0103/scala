import org.scalatest.FunSuite

class TemplateTest extends FunSuite {
  test("Template.greet") {
    assert(Template.greet("Chris") === "Hello Chris!")
  }
}
