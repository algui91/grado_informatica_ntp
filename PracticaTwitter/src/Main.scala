
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
  // ------------------------ A IMPLEMENTAR -------------------------
  // A obtener informacion sobre: 
  // 1. numero de mensajes en mensajesGoogle y mensajesApple
  // 2. numero de mensajes en la tendencia
  // 3. numero de mensajes comunes
  // 4. orden de influencia de los mensajes comunes
  // 5. maximo y minimo numero de retweets en los mensajes comunes
  // 6. maximo y minimo de retweets en toda la coleccion de tendencia

  // obtiene los mensajes que son comunes en ambos conjuntos
  val mensajesComunes = TerminosGoogleApple.mensajesGoogle.interseccion(TerminosGoogleApple.mensajesApple)

  // funcion recursiva auxiliar para obtener el tweet con mas RT de una tendencia, es decir, el ultimo
  def obtenerRTUltimoMensajeDeTendencia(tendencia: Tendencia): Integer =
    if (tendencia.tail.isEmpty) tendencia.head.retweets
    else obtenerRTUltimoMensajeDeTendencia(tendencia.tail)

  print("1. Numero de mensajes en:\n")
  print("Google: ")
  print(TerminosGoogleApple.mensajesGoogle.numeroMensajes)
  print("\nApple: ")
  print(TerminosGoogleApple.mensajesApple.numeroMensajes)
  print("\n\n")

  print("2. Numero de mensajes en la tendencia: ")
  print(TerminosGoogleApple.tendencia.length)
  print("\n\n")

  print("3. Numero de mensajes comunes: ")
  print(mensajesComunes.numeroMensajes)
  print("\n\n")

  print("4. Orden de influencia de los mensajes comunes:\n")
  mensajesComunes.ordenacionAscendentePorRetweet.foreach(t => println(t))
  print("\n")

  print("5. Maximo y minimo numero de retweets en los mensajes comunes:\n")
  print("Maximo: ")
  print(obtenerRTUltimoMensajeDeTendencia(mensajesComunes.ordenacionAscendentePorRetweet))
  print("\nMinimo: ")
  print(mensajesComunes.ordenacionAscendentePorRetweet.head.retweets)
  print("\n\n")

  print("6. Maximo y minimo de retweets en toda la coleccion de tendencia:\n")
  print("Maximo: ")
  print(obtenerRTUltimoMensajeDeTendencia(TerminosGoogleApple.tendencia))
  print("\nMinimo: ")
  print(TerminosGoogleApple.tendencia.head.retweets)
  print("\n\n")


}
