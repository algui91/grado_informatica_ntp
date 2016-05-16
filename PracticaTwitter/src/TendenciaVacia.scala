/**
  * Coleccion vacia de mensajes
  */
class TendenciaVacia extends Tendencia {

  // ---------------------- YA IMPLEMENTADOS ----------------------
  /**
    * Agrega nuevo mensaje
    *
    * @param mensaje
    * @return
    */
  def +(mensaje: Tweet) = new TendenciaNoVacia(mensaje, new TendenciaVacia)

  /**
    * Devolver primer mensaje, al no haber, se lanza excepcion
    *
    * @return
    */
  def head: Tweet = throw new Exception("TendenciaVacia.head")

  /**
    * Devuelve el resto de mensajes: se lanza excepcion
    *
    * @return
    */
  def tail: Tendencia = throw new Exception("TendenciaVacia.tail")

  /**
    * Indica si el conjunto esta vacio; true por definicion
    *
    * @return
    */
  def isEmpty: Boolean = true

  /**
    * Metodo toString
    *
    * @return
    */
  override def toString = "TendenciaVacia"
}
