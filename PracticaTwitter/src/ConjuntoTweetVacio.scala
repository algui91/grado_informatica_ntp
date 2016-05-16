/**
  * Clase para representar conjuntos vacios (sin mensajes)
  */
class ConjuntoTweetVacio extends ConjuntoTweet {

  // ------------------- A IMPLEMENTAR ----------------------------
  // quizas algunos de los metodos pedidos se dejen como abstractos y
  // haya que ofrecer las implementaciones aqui
  // -------------------------------------------------------------

  override def filtrar0(predicado: (Tweet) => Boolean, conjunto: ConjuntoTweet): ConjuntoTweet = conjunto
  override def interseccion(otro: ConjuntoTweet): ConjuntoTweet = this
  override def numeroMensajes: Integer = 0

  /**
    * Metodo para incluir un mensaje en el conjunto
    *
    * @param x
    * @return
    */
  def incluir(x: Tweet): ConjuntoTweet = new ConjuntoTweetNoVacio(x, new ConjuntoTweetVacio, new ConjuntoTweetVacio)

  /**
    * Metodo para determinar si el conjunto contiene un cierto mensaje. Al
    * tratarse de un conjunto vacio se devuelve false
    *
    * @param mensaje
    * @return
    */
  def contiene(mensaje: Tweet): Boolean = false

  /**
    * Indica si el conjunto esta vacio. Basta con devolver true
    *
    * @return
    */
  def estaVacio = true

  /**
    * Devuelve el primer mensaje de la coleccion. Como se trata de un
    * conjunto vacio, se genera una excepcion
    *
    * @return
    */
  def head = throw new Exception("ConjuntoTweetVacio.head")

  /**
    * Devuelve el resto de elementos de la coleccion. Como se trata
    * de un conjunto vacio se genera una excepcion
    *
    * @return
    */
  def tail = throw new Exception("ConjuntoTweetVacio.tail")

  /**
    *
    * @param tw
    * @return
    */
  def eliminar(tw: Tweet): ConjuntoTweet = this
}
