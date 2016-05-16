
/**
  * Clase de utilidad para representar conjuntos de tweets con temas de
  * google y apple, junto con un objeto de la clase Tendencia con todos
  * ellos
  */
object TerminosGoogleApple {
  // Lista de terminos de interes para google
  val google = List("android", "Android", "galaxy", "Galaxy", "nexus", "Nexus")

  // Lista de terminos de interes para apple
  val apple = List("ios", "iOS", "iphone", "iPhone", "ipad", "iPad")

  // Conjuntos de tweets para ambas listas de terminos
  // ------------------------ A IMPLEMENTAR -------------------------
  val mensajesGoogle: ConjuntoTweet = LectorTweets.obtenerConjuntoConTerminos(google)
  val mensajesApple: ConjuntoTweet = LectorTweets.obtenerConjuntoConTerminos(apple)

  // Se genera la lista completa de mensajes de ambos temas
  val tendencia: Tendencia = mensajesApple.union(mensajesGoogle).ordenacionAscendentePorRetweet
}

/**
  * Clase para probar la funcionalidad
  */
object Main extends App {

  // obtiene los mensajes que son comunes en ambos conjuntos
  val mensajes = TerminosGoogleApple.mensajesGoogle.interseccion(TerminosGoogleApple.mensajesApple)

  /**
    * Obtiene el tweet con mayor número de RTs
    * @param tend La tendencia actual
    * @return La tendencia con más RTs
    */
  def obtenerMayorRT(tend: Tendencia): Integer =
    if (tend.tail.isEmpty) tend.head.retweets
    else obtenerMayorRT(tend.tail)

  // A obtener informacion sobre:
  printf("1. numero de mensajes en mensajesGoogle = %d y mensajesApple = %d\n",
    TerminosGoogleApple.mensajesGoogle.numeroMensajes,
    TerminosGoogleApple.mensajesApple.numeroMensajes)

  printf("2. numero de mensajes en la tendencia: %d\n",
    TerminosGoogleApple.tendencia.length)

  printf("3. numero de mensajes comunes: %d\n", mensajes.numeroMensajes)

  print("4. Orden de influencia de los mensajes comunes:\n")
  mensajes.ordenacionAscendentePorRetweet.foreach(tweet => println("\t" + tweet.texto + ", #RTs: " + tweet.retweets ))
  print("\n")

  print("5. maximo y minimo numero de retweets en los mensajes comunes\n")
  printf("\tMaximo: %d\n",
    obtenerMayorRT(mensajes.ordenacionAscendentePorRetweet))
  printf("\tMinimo: %d\n",
    mensajes.ordenacionAscendentePorRetweet.head.retweets)
  print("\n\n")


  print("6. maximo y minimo de retweets en toda la coleccion de tendencia\n")
  printf("\tMaximo: %d\n",
    obtenerMayorRT(TerminosGoogleApple.tendencia))
  printf("\tMinimo: %d\n",
    TerminosGoogleApple.tendencia.head.retweets)
}
