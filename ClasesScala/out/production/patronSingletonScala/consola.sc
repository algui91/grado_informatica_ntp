/**
  * La más simple, usando parámetros de clase
  */
class Racional(n: Int, d: Int)

val obj1 = new Racional(2, 3)

class Racional2(n: Int, d: Int) {
  override def toString = s"$n/$d"
}

val obj2 = new Racional2(2, 3)

// Queremos evitar objetos donde d valga 0
class Racional3(n: Int, d: Int) {
  require(d != 0)

  override def toString = s"$n/$d"
}

val obj3 = new Racional3(1, 0)

// Implementar método de suma, Los parametrps
// de clase a datos miembros, declarandolos
// dentro del cuerpo de la clase.
class Racional4(n: Int, d: Int) {
  require(d != 0)

  val numerador : Int = n
  def sumar(r: Racional4): Racional4 {
    new Racional4 (n * r.d + r.n * d,
    d * r.d)
  }

  override def toString = s"$n/$d"
}


