import scala.scalajs.js
import js.annotation.JSExportTopLevel
import js.annotation.JSExport
import org.scalajs.dom.raw._

import org.scalajs.dom

import upickle.default._
import shared.Protocol
import P5VectorExt._

@JSExportTopLevel("Bounce")
class Bounce() extends js.Object {
  val sketch: js.Function1[Sketch, Unit] = { s =>
    import s._
    val ballDiameter = 75.0
    val ballRadi = ballDiameter / 2
    var location = P5Vector(100, 100)
    var speed = P5Vector(1, 3.3)

    setup = { () =>
      createCanvas(800, 400)
    }
    
    draw = { () =>
      background(232)
      location = location + speed

      if (location.x + ballRadi > width) || (location.x < ballRadi) then {
        speed = speed.negX()
      }
      if (location.y + ballRadi > height) || (location.y < ballRadi) then {
        speed = speed.negY()
      }

      fill(128, 245, 128)
      ellipse(location.x, location.y, ballDiameter, ballDiameter)
    }
  }
}
