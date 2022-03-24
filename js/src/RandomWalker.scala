import scala.scalajs.js
import js.annotation.JSExportTopLevel
import js.annotation.JSExport
import scala.util.Random

@JSExportTopLevel("RandomWalker")
class RandomWalker() extends js.Object {
  val sketch: js.Function1[Sketch, Unit] = { s =>
    import s._

    val random = new scala.util.Random

    var ax = 150
    var ay = 150
    var bx = 300
    var by = 150

    setup = { () =>
      createCanvas(450, 300)
      background(200)

    }
    draw = { () =>
      point(ax, ay)

      var r = random.between(0, 4) 
      
      if r == 0 then ax = ax + 1
      else if r == 1 then ax = ax - 1
      else if r == 2 then ay = ay + 1
      else if r == 3 then ay = ay - 1
      
      point(bx, by)
      
      var r2 = random.between(0, 4)

      if r2 == 0 then bx = bx + 1
      else if r2 == 1 then bx = bx - 1
      else if r2 == 2 then by = by + 1
      else if r2 == 3 then by = by - 1
      

    }
  }
}
