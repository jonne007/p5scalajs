package matrix

import utest._
import matrix.Matrix
import matrix.MatrixExt._

object MatrixSuite extends TestSuite:

  val tests = Tests {
    test("there's a glitch") {
      val m: Matrix = Seq(
        Seq(1, 1, 1),
        Seq(1, 1, 1),
        Seq(1, 1, 1)
      )
      val projectionM: Matrix = Seq(
        Seq(1, 0, 0),
        Seq(0, 1, 0),
        Seq(0, 0, 0)
      )

      val result = m.matmult(projectionM)

      result ==> Seq(
        Seq(1, 1, 0),
        Seq(1, 1, 0),
        Seq(1, 1, 0)
      )
    }
  }
