import utest._
package object minesweeper {
  
  def assertPosition(b: Board, col: Int, row: Int) =
    b.getCell(Position(col, row)) ==>
      b.getCellByCordinates(col, row)

  def createTestBoard(s: String): Board =
    
    val lines = s.linesIterator.filterNot(_.isEmpty).toSeq
    val cols = lines(0).size
    val rows = lines.size
    val mines = lines
      .zipWithIndex
      .flatMap { (l, c) =>
        l.zipWithIndex.flatMap {
          case ('x', r) => Some(Cell(Position(c, r), true, false))
          case ('r', r) => Some(Cell(Position(c, r), false, true))
          case (_, r) => None
        }
      }
      .map(c => c.p -> c)
      .toMap
    Board(rows, cols, 1).withMines(mines)

  def assertBoard(expected: String, b: Board) =
      createTestBoard(expected) ==> b

      
}
