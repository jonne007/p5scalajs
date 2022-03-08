package minesweeper

case class Board(
    rows: Int,
    cols: Int,
    cellSize: Int,
    cells: Map[Position, Cell] = Map(),
    ended: Boolean = false
):
  val positions = for {
    col <- (0 until cols)
    row <- (0 until rows)
  } yield Position(col, row)

  def click(x: Double, y: Double): Board =
    val c = getCellByCordinates(x, y)
    if c.mine then
      this.copy(cells = this.cells + (c.p -> c.reveal), ended = true)
    else
      val revealed = floodReveal(c)
      this.copy(cells = this.cells ++ revealed)

  def floodReveal(
      c: Cell,
      acc: Map[Position, Cell] = Map()
  ): Map[Position, Cell] =
    if acc.contains(c.p) then acc
    else if neighbouringMines(c) == 0 then
      cellNeighbours(c)
        .foldLeft(acc + (c.p -> c.reveal)) { (acc, n) =>
          floodReveal(n, acc)
        }
    else acc + (c.p -> c.reveal)

  def cellNeighbours(c: Cell): Seq[Cell] =
    val r = c.p.neighbours
      .filter(p => p.col < cols && p.row < rows)
      .map(p => getCell(p))
    r

  def getCellByCordinates(x: Double, y: Double): Cell =
    getCell(Position(x.toInt / cellSize, y.toInt / cellSize))

  def getCell(p: Position): Cell =
    cells.getOrElse(p, Cell(p, false))

  def forCells(f: Cell => Unit) = positions.foreach(p => f(getCell(p)))

  def neighbouringMines(c: Cell): Int = cellNeighbours(c)
    .filter(_.mine)
    .size

  def withMines(mines: Map[Position, Cell]): Board =
    this.copy(cells = this.cells ++ mines)

  def withMines(n: Int): Board =
    import scala.util.Random.shuffle
    withMines(
      shuffle(positions)
        .take(n)
        .map(p => p -> Cell(p, true))
        .toMap
    )

case class Cell(p: Position, mine: Boolean, revealed: Boolean = false):
  def x(width: Double) = p.col * width
  def y(height: Double) = p.row * height
  def reveal = copy(revealed = true)

case class Position(col: Int, row: Int):
  def neighbours: Seq[Position] =
    val xs = for {
      c <- (-1 to 1)
      r <- (-1 to 1)
    } yield Position(col + c, row + r)
    xs.view
      .filterNot(_.col < 0)
      .filterNot(_.row < 0)
      .filterNot(p => p.col == col && p.row == row)
      .toSeq
