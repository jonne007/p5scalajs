package mandelbrot

object Mandelbrot {
  val MAX_ITERATIONS = 200
  
  def mandelbrot(originalA: Double, originalB: Double): Option[Int] =
    def _f(a: Double, b: Double, i: Int = 0): Option[Int] =
      val aa = a * a - b * b
      val bb = 2 * a * b
      if Math.abs(aa + bb) >= 17 then
        Some(i)
      else if i > MAX_ITERATIONS then
        None
      else _f(aa + originalA, bb + originalB, i + 1)

    _f(originalA, originalB)
}
