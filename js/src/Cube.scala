import scala.scalajs.js
import js.annotation.JSExportTopLevel
import matrix._
import matrix.MatrixVector
import matrix.MatrixVectorExt._
import matrix.Matrix
import matrix.Matrix._
import matrix.MatrixExt._

@JSExportTopLevel("SpinningCube")
class SpinningCube() extends js.Object {
  val sketch: js.Function1[Sketch, Unit] = { s =>
    import s._

    var cube = Cube(0, 0, 0, 50)
    var angle = scala.util.Random.nextDouble

    setup = () => createCanvas(200, 200)
    //frameRate(1)

    draw = () =>
      background(255)
      translate(width / 2, height / 2)
      val c = cube
        .map(_.matmult(rotationX(angle)))
        .map(_.matmult(rotationY(angle + 0.005)))
        .map(_.matmult(rotationZ(angle + 0.007)))

      c.forConnections { (v1, v2) =>
        line(v1.x, v1.y, v2.x, v2.y)
      }
      c.forPoints { v =>
        val z = map(v.z, -100, 100, 5, 7)
        fill(199, 255, 43)
        circle(v.x, v.y, z)
      }

      angle = angle + 0.017
  }
}

object Cube {
  def apply(x: Double, y: Double, z: Double, r: Double = 0.5) =
    new Cube(
      Seq(
        Seq(r, r, r),
        Seq(r, r, -r),
        Seq(-r, r, -r),
        Seq(-r, r, r),
        Seq(r, -r, r),
        Seq(r, -r, -r),
        Seq(-r, -r, -r),
        Seq(-r, -r, r)
      )
    )
}

class Cube(points: Matrix) {
  def forPoints(f: MatrixVector => Unit) = points.foreach(f)
  def forConnections(f: (MatrixVector, MatrixVector) => Unit) =
    for (i <- 0 to 3) {
      f(points(i), points((i + 1) % 4))
      f(points(i + 4), points((i + 1) % 4 + 4))
      f(points(i), points(i + 4))
    }

  def map(f: Matrix => Matrix): Cube = new Cube(f(points))
}
