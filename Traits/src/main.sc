/**
  * Este TRait solo se puede usar con objetos que deriven de
  * colaenteros.
  */
trait Doblar extends ColaEnteros {
  /**
    * Lo dejamos como abstracto, porque en realidad
    * vamos a hacer una llamada a super.
    * Hay que poner abstract porque hay una llamada a super.
    *
    * Decimos como se hace el método put en base a cómo se cree
    * el método concreto posteriormente.
    *
    * Se usa ligadura dinámica aquí.
    *
    * @param x
    */
  abstract override def put(x: Int): Unit = {
    super.put(2 * x)
  }
}

import scala.collection.mutable.ArrayBuffer

trait FiltrarNegativos extends ColaEnteros {
  abstract override def put(x: Int): Unit = {
    if (x >= 0) super.put(x)
  }
}

trait Incrementar extends ColaEnteros {
  abstract override def put(x: Int): Unit = {
    super.put(x + 1)
  }
}

abstract class ColaEnteros {
  def get(): Int

  def put(x: Int)
}

class ColaBasicaEnteros extends ColaEnteros {
  private val buffer = new ArrayBuffer[Int]

  def get() = buffer.remove(0)

  def put(x: Int): Unit = {
    buffer += x
  }
}