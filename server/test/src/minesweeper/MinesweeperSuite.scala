package minesweeper

import utest._
import minesweeper._

object MinesweeperSuite extends TestSuite {

  val tests = Tests {
    test("flood reveal 1") {
      val b = """
        |oo
        |oo
        |""".stripMargin
      val dut = createTestBoard(b)

      val result = dut.click(0, 0)

      val expected = """
        |rr
        |rr
        |""".stripMargin

      assertBoard(expected, result)
    }
    test("flood reveal 2") {
      val b = """
        |ooo
        |ooo
        |oox
        |""".stripMargin
      val dut = createTestBoard(b)

      val result = dut.click(0, 0)

      val expected = """
        |rrr
        |rrr
        |rrx
        |""".stripMargin

      assertBoard(expected, result)
    }
    test("flood reveal 3") {
      val b = """
        |oooo
        |oooo
        |ooxo
        |oooo
        |""".stripMargin
      val dut = createTestBoard(b)

      val result = dut.click(0, 0)

      val expected = """
        |rrrr
        |rrrr
        |rrxo
        |rroo
        |""".stripMargin

      assertBoard(expected, result)
    }
    test("position") {
      val dut = Position(0, 0)
      
      dut.neighbours ==> Seq(Position(0,1), Position(1,0), Position(1,1))
    }
    test("createTestBoard") {
      val b = """
        |ooo
        |oxo
        |ooo
        |""".stripMargin
      val dut = createTestBoard(b)

      dut.cols ==> 3
      dut.rows ==> 3
      dut.getCellByCordinates(1, 1) ==>
        Cell(Position(1, 1), true, false)
      dut.getCellByCordinates(0, 1) ==>
        Cell(Position(0, 1), false, false)
      dut.getCellByCordinates(2, 2) ==>
        Cell(Position(2, 2), false, false)

      assertPosition(dut, 0, 0)
      assertPosition(dut, 0, 1)
      assertPosition(dut, 0, 2)
      assertPosition(dut, 1, 0)
      assertPosition(dut, 1, 1)
      assertPosition(dut, 1, 2)
      assertPosition(dut, 2, 0)
      assertPosition(dut, 2, 1)
      assertPosition(dut, 2, 2)
    }
  }
     
}
