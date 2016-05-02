
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

/**
  * Practica de conjuntos funcionales
  */
object ConjuntoFuncional {
  /**
    * Un conjunto funcional se representa mediante una funcion
    * caracteristica, un predicado. De esta forma, se declara
    * el tipo conjunto como un predicado que recibe un entero
    * (elemento) como argumento y dvuelve un valor booleano
    * que indica si pertenece o no al conjunto
    */
  type Conjunto = Int => Boolean

  /**
    * Metodo para determinar si un elemento pertenece al conjunto
    *
    * @param conjunto
    * @param elemento
    * @return
    */
  def contiene(conjunto: Conjunto, elemento: Int): Boolean = conjunto(elemento)

  /**
    * Devuelve un conjunto asociado al elemento pasado como
    * argumento
    *
    * @param elemento
    * @return
    *
    */
  def conjuntoUnElemento(elemento: Int): Conjunto = x => x == elemento

  /**
    * Union de dos conjuntos
    *
    * @param conjunto1
    * @param conjunto2
    * @return
    */
  def union(conjunto1: Conjunto, conjunto2: Conjunto): Conjunto = x => conjunto1(x) || conjunto2(x)

  /**
    * Interseccion de dos conjuntos
    *
    * @param conjunto1
    * @param conjunto2
    * @return
    */
  def interseccion(conjunto1: Conjunto, conjunto2: Conjunto): Conjunto = x => conjunto1(x) && conjunto2(x)

  /**
    * Diferencia entre dos conjuntos
    *
    * @param conjunto1
    * @param conjunto2
    * @return
    */
  def diferencia(conjunto1: Conjunto, conjunto2: Conjunto): Conjunto =
    x => contiene(conjunto1, x) && !contiene(conjunto2, x)

  /**
    * Filtrado para obtener el conjunto de los elementos que cumplen
    * el predicado pasado como argumento
    *
    * @param conjunto
    * @param p
    * @return
    */
  def filter(conjunto: Conjunto, p: Int => Boolean): Conjunto = x => p(x)

  /**
    * Limite para la iteracion necesaria con paraTodo y existe,
    * entre -1000 y 1000
    */
  private val LIMITE = 1000

  /**
    * Determina si todos los elementos del conjunto cumplen
    * la condicion indicada por el predicado
    *
    * @param conjunto
    * @param p
    * @return
    */
  def paraTodo(conjunto: Conjunto, p: Int => Boolean): Boolean = {
    // Funcion auxiliar para iterar sobre los valores desde
    // -LIMITE a LIMITE
    def iter(elemento: Int): Boolean = {
      if (elemento > LIMITE) true
      else if (!contiene(conjunto, elemento)) iter(elemento + 1)
      else p(elemento) && iter(elemento + 1)
    }

    iter(-LIMITE)
  }

  /**
    * Determina si existe al menos un elemento en el conjunto
    * que cumple el predicado indicado
    *
    * @param conjunto
    * @param p
    * @return
    */
  def existe(conjunto: Conjunto, p: Int => Boolean): Boolean = !paraTodo(conjunto, x => !p(x))

  /**
    * Genera un nuevo conjunto transformando los elementos del
    * conjunto pasado como argumento y aplicando la transformacion
    * dada por la funcion pasada como segundo argumento
    *
    * @param conjunto
    * @param funcion
    * @return
    */
  def map(conjunto: Conjunto, funcion: Int => Int): Conjunto = x => existe(conjunto, y => funcion(y) == x)

  /**
    * Crea una cadena con el contenido completo del conjunto
    *
    * @param conjunto
    * @return
    */
  def toString(conjunto: Conjunto): String = {
    val elementos = for (
      i <- -LIMITE to LIMITE if contiene(conjunto, i)) yield i
    elementos.mkString("{", ",", "}")
  }

  /**
    * Muestra el contenido completo del conjunto por pantalla
    *
    * @param conjunto
    */
  def printSet(conjunto: Conjunto) {
    println(toString(conjunto))
  }
}
