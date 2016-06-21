/*
 * Copyright 2016 Alejandro Alcalde
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

object Huffman {

  /**
    * La codificacíon de Huffman es un algoritmo de compresíon que puede usarse para codificar archivos de texto.
    * En un texto normal, no comprimido, todos los caracteres se representan mediante el mismo número de bits (8
    * usualmente, el tamaño de un byte). Sin embargo, con esta codificacíon cada caŕacter puede tener una
    * representación con diferente número de bits.
    *
    * Los caracteres que aparecen más frecuentemente se codifican con menos bits que aquellos que tienen menor
    * frecuencia de aparición
    *
    */
  abstract class Nodo
  case class NodoIntermedio(nodoIzquierdo: Nodo, nodoDerecho: Nodo, caracteres: List[Char], contador: Int) extends Nodo
  case class NodoHoja(caracter: Char, contador: Int) extends Nodo

  /**
    * Función que calcula el número de veces que aparecen los caracteres en un nodo
    *
    * @param tree El nodo al que calcularle el peso/contador de caracteres
    * @return El peso/contador calculado
    */
  def calcularPeso(tree: Nodo): Int = tree match {
    case NodoHoja(_, w) => w // Es un nodo hoja, Devolvemos su peso
    case NodoIntermedio(_, _, _, w) => w // Es un nodo Intermedio, devolvemos su peso
  }

  /**
    * Devuelve la lista de caracteres que representa un árbol
    *
    * @param arbol El nodo raíz del árbol, del que se sacará la lista de caracteres
    * @return La lista de caracteres
    */
  def obtenerCaracteres(arbol: Nodo): List[Char] = arbol match {
    case NodoHoja(c, _) => c :: Nil // Una lista de un solo elemento
    case NodoIntermedio(_, _, c, _) => c // Si nodo intermedio, devolvemos los caracteres
  }

  /**
    * Recibe como argumento dos sub árboles, izquierda y derecha, y los junta en un único árbol
    *
    * @param izda Árbol izquierdo
    * @param dcha Árbol derecho
    * @return
    */
  def generarArbol(izda: Nodo, dcha: Nodo) =
    NodoIntermedio(
      izda, dcha, obtenerCaracteres(izda) ::: obtenerCaracteres(dcha), calcularPeso(izda) + calcularPeso(dcha)
    )

  /**
    * Dada una lista de caracteres, genera un árbol codificado con sus caracteres y
    * contadores de ocurrencias.
    *
    * @param caracteres La lista de caracteres a partir de la que generar el árbol
    * @return
    */
  def generarArbolCodificacion(caracteres: List[Char]): Nodo = {
    if (caracteres.isEmpty)
      throw new Error("La lista de caracteres no puede estar vacía!")
    hasta(singleton, combine)(generarListaHojasOrdenadas(obtenerTuplasOcurrencias(caracteres))).head
  }

  /**
    * Cuenta el número de ocurrencias de un caracter dada una lista de caracteres.
    *
    * @param texto La lista de caracteres de la que se quiere calcular las ocurrencias
    * @return Una lista de tuplas (Char, Int) que representan cuantas veces aparece el caracter Char
    */
  def obtenerTuplasOcurrencias(texto: List[Char]): List[(Char, Int)] =
    // Contamos cuantas veces aparece el caracter c en el texto, y generamos las tuplas (Char, Int) con map
    texto.map(c => (c, texto.count(cc => cc == c))).distinct

  /**
    * Genera una lista con todos los nodos hoja del ́arbol de codificacíon ordenada de forma ascendente
    *
    * @param ocurrencias La lista de frecuencias de caracteres
    * @return La lista ordenada
    */
  def generarListaHojasOrdenadas(ocurrencias: List[(Char, Int)]): List[NodoHoja] =
    // Ordenamos en forma ascendente en base al segundo componente de la tupla (el peso)
    // Luego lo mapeamos a un nodo hoja (Creando un nuevo nodo hoja)
    ocurrencias.sortWith(_._2 < _._2).map(w => NodoHoja(w._1, w._2))

  /**
    * Comprueba si la lista de nodos tiene un solo elemento
    *
    * @param nodos La lista de nodos (árbol) a comprobar
    * @return Verdadero si tiene un solo elemento, falso en otro caso
    */
  def singleton(nodos: List[Nodo]): Boolean = nodos.size == 1

  /**
    * Combina todos los nodos terminales
    *
    * @param nodos
    * @return Los nodos combinados
    */
  def combine(nodos: List[Nodo]): List[Nodo] = nodos match {
    // Partimos la lista en tres listas, los dos primeros y el resto de la lista, generamos un arbol con los dos
    // menores y los ordenamos por peso
    case menor :: segundoMenor :: resto => (generarArbol(menor, segundoMenor) :: resto).sortBy(a => calcularPeso(a))
    case _ => nodos
  }

  /**
    * Convierte una cadena a lista
    */
  def stringAListaCaracteres(cadena: String): List[Char] = cadena.toList

  /**
    * Usando currying se define esta función que irá aplicando la combinación de los nodos hasta que solo quede uno.
    *
    * @param isSingleton Función que determina cuando queda un único nodo
    * @param combinar    Función que combina dos nodos en un árbol
    * @param arboles     La lista de nodos
    * @return
    */
  def hasta(isSingleton: List[Nodo] => Boolean, combinar: List[Nodo] => List[Nodo])(arboles: List[Nodo]): List[Nodo] =
    isSingleton(arboles) match {
      case true => arboles // Aquí ya solo queda un elemento (Es singleton), caso base
      case false => hasta(isSingleton, combinar)(combinar(arboles)) // Seguimos combinando, llamamos a combinar
      // (arboles) para combinar dos nodos más
    }


  /**
    * Decodifica una lista de 0s y 1s que han sido codificados mediante un árbol.
    *
    * Se le pasa un arbol Huffman y una secuencia de bits codificada con dicho árbol. Cuando se procesa desde la
    * raíz, se mira si es un nodo hoja o uno intermedio.
    *
    * Si es intermedio se mira la secuencia codificada para saber si debemos seguir por el hijo izquierdo (el bit ==
    * 0), o a la derecha (bit == 1).
    *
    * Si es un nodo hoja, y aún quedan bits codificados, se añade al acumulador el caracter correspondiente y se
    * sigue decodificado. Si es nodo hoja y ya no quedan más bits a procesar, se añade el último caracter a la lista
    * y se termina.
    *
    * @param arbol
    * @param bits
    * @return
    */
  def decodificar(arbol: Nodo, bits: List[Int]): List[Char] = {
    def decodificar0(arbol0: Nodo, bits0: List[Int], acumulador: List[Char]): List[Char] = (arbol0,
      bits0) match {
      // Hemos procesado toda la secuencia de bits codificados, añadimos el último caracter decodificado
      case (NodoHoja(c, _), Nil) => acumulador ::: List(c)
      // Nodo hoja, añadimos caracter y seguimos
      case (NodoHoja(c, _), bitsHoja) => decodificar0(arbol, bitsHoja, acumulador ::: List(c))
      // A la izquierda (Porque empieza por 0)
      case (NodoIntermedio(izda, _, _, _), 0 :: tail) => decodificar0(izda, tail, acumulador)
      // A la izquierda (Porque empieza por 1)
      case (NodoIntermedio(_, dcha, _, _), 1 :: tail) => decodificar0(dcha, tail, acumulador)
      // Cualquier otra cosa
      case (_, _) => acumulador
    }
    decodificar0(arbol, bits, List())
  }

  /**
    * Dado un código Huffman, para codificar un caracter dado se recorre desde la raíz hasta llegar al nodo hoja que
    * contiene dicho caracter. Conforme se va recorriendo el árbol, se añade un 0 si se escoge la rama de la
    * izquierda y un 1 si se escoge la rama de la derecha.
    *
    * @param arbol El árbol de codificación Huffman
    * @param texto El texto a codificar
    * @return Una lista codificada
    */
  def codificar(arbol: Nodo)(texto: List[Char]): List[Int] = {
    def codificar0(arbol0: Nodo, texto0: List[Char], bits: List[Int]): List[Int] =
      (arbol0, texto0) match {
        // Si en la rama izquierda está el caracter que estamos procesando, nos metemos dentro del case true
        case (NodoIntermedio(iza, dcha, _, _), head :: tail) => (obtenerCaracteres(iza).contains(head)) match {
          case true => iza match { // Rama izquierda, meteremos un 0
            // Hemos llegado a nodo hoja, codificamos y empezamos por raíz de nuevo
            case NodoHoja(_, _) => codificar0(arbol, tail, bits ::: List(0))
            // Aún estamos en una rama, metemos el 0 y avanzamos
            case NodoIntermedio(_, _, _, _) => codificar0(iza, texto0, bits ::: List(0))
          }
          // Si no está en el izquierdo, está en el derecho, nos metemos en la rama derecha
          case false => dcha match { // Rama derecha, meteremos un 1
            // LLegamos a nodo hoja, codificamos y empezamos por raíz de nuevo
            case NodoHoja(_, _) => codificar0(arbol, tail, bits ::: List(1))
            // Aún estamos en la rama, metemos 1 y avanzamos
            case NodoIntermedio(_, _, _, _) => codificar0(dcha, texto0, bits ::: List(1))
          }
        }
        // Hemos terminado de procesar el árbol
        case (_, _) => bits
      }
    codificar0(arbol, texto, List())
  }

  /**
    * Implementación basada en tabla de códigos
    */
  type TablaCodigo = List[(Char, List[Int])]

  /**
    * Codifica usando una tabla de códigos, para que sea más eficiente
    *
    * @param tabla La tabla de códigos a usar
    * @param char  El texto a codificar
    * @return
    */
  def codificarConTabla(tabla: TablaCodigo)(char: Char): List[Int] = tabla match {
    // Cogemos la primera tupla de la tabla de códigos para comprobar si es igual al caracter que queremos codificar
    case (caracterTabla, bits) :: tail =>
      // Si el caracter a codificar es igual a la tupla que hemos seleccionado de la tabla de codificación,
      // devolvemos la codificación para ese caracter, si no, iteramos al la siguiente tupla de la tabla
      if (caracterTabla == char) bits else codificarConTabla(tail)(char)
    case _ => Nil
  }

  /**
    * Dado un árbol de codificación, crea una tabla de codificación.
    *
    * @param arbolCodificacion El arbol a partir del cual generar la tabla
    * @return La tabla
    */
  def convertirArbolTabla(arbolCodificacion: Nodo): TablaCodigo = {
    def convertToTable(arbol: Nodo, bits: List[Int]): TablaCodigo = arbol match {
      // Cuando llegamos a un nodo hoja, creamos su lista de codificación, (Char, codificacion)
      case NodoHoja(c, _) => List((c, bits))
      // Si es un nodo intermedio, seguimos generando la tabla, dividiendo entre rama izquierda y derecha
      case NodoIntermedio(ida, dcha, _, _) =>
        fusionarTablas(convertToTable(ida, bits ::: List(0)), convertToTable(dcha, bits ::: List(1)))
    }
    convertToTable(arbolCodificacion, List())
  }

  /**
    * Fusiona dos tablas en una
    *
    * @param a
    * @param b
    * @return
    */
  def fusionarTablas(a: TablaCodigo, b: TablaCodigo): TablaCodigo = a:::b

  /**
    * Realiza una codificación rápida usando la tabla de códigos
    *
    * @param arbol El árbol de codificación
    * @param texto El texto a codificar
    * @return El texto codificado
    */
  def codificacionRapida(arbol: Nodo)(texto: List[Char]): List[Int] = {
    def codificar(tabla: TablaCodigo)(texto: List[Char])(acumuladorBits: List[Int]): List[Int] = texto match {
      case Nil => acumuladorBits // Hemos acabado de procesar el texto, devolvemos la codificación
      case head :: tail => // Hay texto, codificamos recusrivamente  usando la tabla de códigos, vamos consumiendo el
        // texto en cada iteración. Para el caracter de la cabeza, codificamos con la tabla
        codificar(tabla)(tail)(acumuladorBits ::: codificarConTabla(tabla)(head))
    }
    codificar(convertirArbolTabla(arbol))(texto)(List())
  }
}