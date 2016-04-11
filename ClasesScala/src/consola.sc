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

class Racional4(n: Int, d: Int) {
  require(d != 0)

  private val factor = mcd(numerador, denominador)

  val numerador: Int = n / factor
  val denominador: Int = d / factor

  /**
    * Constructor para numeros enteros, con denominador 1.
    */
  def this(n: Int) = this(n, 1)

  def sumar(r: Racional4): Racional4 =
    new Racional4(numerador * r.denominador + r.numerador * denominador,
      denominador * r.denominador)

  private def mcd(a: Int, b: Int): Int =
  // Este es el tipo de recursividad que interesa (Tail recursion), se llama
  // a la funcoinrecursiva al final, porque así se implementa como un bucle, y es más eficiente
    if (b == 0) a
    else mcd(b, a % b)


  def menorQue(o: Racional4) =
    numerador * o.denominador < o.numerador * denominador

  def max(o: Racional4) =
    if (this.menorQue(o)) o
    else this

  def +(otro: Racional4) =
    new Racional4(numerador * otro.denominador + otro.numerador * denominador,
      denominador * otro.denominador)

  /**
    * Igual que en java, el objeto a comparar es object, any aqui
    */
  override def equals(o: Any) =
    o.isInstanceOf[Racional4] &&
      o.asInstanceOf[Racional4].numerador == numerador &&
      o.asInstanceOf[Racional4].denominador == denominador

  override def toString = s"$n/$d"
}



