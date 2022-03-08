import scala.scalajs.js
import js.annotation.JSExportTopLevel
import minesweeper._

@JSExportTopLevel("Minesweeper")
class Minesweeper() extends js.Object {
  val sketch: js.Function1[Sketch, Unit] = { s =>
    import s._
    val rows = 10
    val cols = 10
    val cellSize = 40
    val mines = 10
    var board: Board = null

    setup = () =>
      board = new Board(rows, cols, cellSize)
        .withMines(mines)

      val cnvs = createCanvas(board.cols * cellSize, board.rows * cellSize)
      cnvs.mousePressed { () =>
        board = board.click(mouseX, mouseY)
        if board.ended then noLoop()
      }

    draw = () =>
      background(232)
      board.forCells { c =>
        val x = c.x(cellSize)
        val y = c.y(cellSize)
        rect(x, y, cellSize, cellSize)
        if c.revealed then
          if c.mine then
            circle(x + cellSize / 2, y + cellSize / 2, cellSize * 0.8)
          else
            text(
              "" + board.neighbouringMines(c),
              x + cellSize / 2,
              y + cellSize / 2
            )
      }
  }
}
