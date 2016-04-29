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
  * Created by Alejandro Alcalde <contacto@elbauldelprogramador.com> on 4/18/16.
  */

def imprimirTablas(): Unit = {
  var i = 1
  while (i <= 10) {
    var j = 1

    while (j <= 10) {
      val prod = (i * j).toString
      var k = prod.length

      while (k < 4) {
        print(" ")
        k += 1
      }
      print(prod)
      j += 1
    }
    println
    i += 1
  }
}


imprimirTablas()

// Ahora lo hacemos bien:

def generarTablaNumero(fila: Int) =
  for (columna <- 1 to 10) yield {
    val producto = (fila * columna).toString
    val relleno = " " * (4 - producto.length)
    relleno + producto // De vuelve la concatenación
  }

// Para pasar la seccuancia indexada que devuelve generarTabla a cadena se hace con mkstring

def imprimirTablas2() = {
  val tablas = for (i <- 1 to 10) yield generarTablaNumero(i).mkString
  tablas.mkString("\n")
}

print(imprimirTablas2)


// 8 Reinas

// La posición está dada por un par de enteros, en una tupla

def enJaque(reina1: (Int, Int), reina2: (Int, Int)): Boolean =
// Si estan en la misma diagonal, la diferencia de las posiciones es la misma
  reina1._1 == reina2._1 || // Misma fila
    reina1._2 == reina2._2 || // Misma columna
    (reina1._1 - reina2._1).abs == (reina1._2 - reina2._2).abs

def posicionCorrecta(reina: (Int, Int), solucionParcial: List[(Int, Int)]): Boolean =
  solucionParcial forall (reinaPuesta => !enJaque(reina, reinaPuesta))


// Definir la función completa
// n tamaño tablero
def solucionarProblemaReinas(n: Int): List[List[(Int, Int)]] = {
  def posicionarReinas(fila: Int): List[List[(Int, Int)]] = {
    if (fila == 0) List(List())
    else for {
      solucion <- posicionarReinas(fila - 1)
      columna <- 1 to n
      reina = (fila, columna)
      if (posicionCorrecta(reina, solucion))
    } yield reina :: solucion
  }
  posicionarReinas(n)
}


print(solucionarProblemaReinas(8))