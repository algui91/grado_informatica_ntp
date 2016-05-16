/**
  * Clase para representar colecciones no vacias
  *
  * @param mensajeInicial
  * @param resto
  */
class TendenciaNoVacia(mensajeInicial: Tweet, resto: Tendencia) extends Tendencia {

  // ---------------------- YA IMPLEMENTADOS ----------------------

  /**
    * Se agrega mensaje al final de la secuencia
    *
    * @param mensaje
    * @return
    */
  def +(mensaje: Tweet): Tendencia =
    new TendenciaNoVacia(mensajeInicial, resto + mensaje)

  /**
    * Devuelve el mensaje inicial
    *
    * @return
    */
  def head: Tweet = mensajeInicial

  /**
    * Devuelve el resto de mensajes
    *
    * @return
    */
  def tail: Tendencia = resto

  /**
    * Indica si la coleccion esta vacia: no por definicion
    *
    * @return
    */
  def isEmpty: Boolean = false

  /**
    * Metodo toString
    *
    * @return
    */
  override def toString =
    "TendenciaNoVacia(" + mensajeInicial.retweets + ", " + resto + ")"
}
