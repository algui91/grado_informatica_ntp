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

// Al poner val o var en el parametro se hace automáticamente
// dato miembre de la clase.
class Usuario(val nombre: String) {
  def saludar: String = s"Saludos desde $nombre"

  override def toString = s"Usuario($nombre)"
}

val us1 = new Usuario("Alex")
us1.saludar
println(us1)
val usuarios = List(new Usuario("mgo"),
  new Usuario("Alex"))
// Forma abreviada de expresion lambda, mape cada elemento
// de la lista y el tamaño del nombre de cada lista
val tams = usuarios map (_.nombre.size)

// Ordenarlos por nombre
val ordenados = usuarios sortBy (_.nombre)

// Encontrar
val cong = usuarios find (_.nombre contains "g")
// Como cong es un option, y puede no tener valor porque
//no hay ningún nombre con g, se usa el getOrElse
val saludo = cong map (_.saludar) getOrElse "No Usuario"

// El equivalente de no usar el _ es:

val cong2 = usuarios find (
  usuario => usuario.nombre contains "g")

//////////////// HERENCIA


class A {
  def saludar = "Saludos desde clase A"

  override def toString = "Clase: " + getClass.getName
}

class B extends A

class C extends B {
  override def saludar = "Saludo desde C y " + super.saludar
}

val miA = new A
val miB = new B
val miC = new C

miA.saludar
miB.saludar
miC.saludar

// Polimorfirmos

val obj1: A = new A
val obj2: A = new B

class Coche(val marca: String, var reservado: Boolean) {
  def reservar(r: Boolean): Unit = {
    reservado = r
  }
}

val c1 = new Coche("Audi", false)
c1.reservar(true)

println(s"Coche ${c1.marca}, reservado")



// Funciones
def sumarEnteros(desde: Int, hasta: Int): Int =
  if (desde > hasta) 0
  else desde + sumarEnteros(desde + 1, hasta)

def elevarCuadrado(x: Int) = x * x

def sumarCuadrados(desde: Int, hasta: Int): Int =
  if (desde > hasta) 0
  else elevarCuadrado(desde) + sumarCuadrados(desde + 1, hasta)

sumarCuadrados(1, 20)

def obtenerPotenciaDos(x: Int): Int =
  if (x == 0) 1
  else 2 * obtenerPotenciaDos(x - 1)

def sumarPotenciaDos(desde: Int, hasta: Int): Int =
  if (desde > hasta) 0
  else obtenerPotenciaDos(desde) + sumarPotenciaDos(desde + 1, hasta)

// La sumatoria en los tres casos es la misma
//sum_{desde}^{hasta} f(x)
// Definimos un patrón genérico para usarlo
def sumatoria(f: Int => Int, desde: Int, hasta: Int): Int =
  if (desde > hasta) 0
  else f(desde) + sumatoria(f, desde + 1, hasta)

def identidad(x: Int): Int = x
def ObtenerPotenciaDos(x: Int) = if (x == 0) 1 else obtenerPotenciaDos(x - 1) * 2

// Creamos las mismas funciones anteriores en base a la sumar
sumatoria(identidad, 1, 2)
sumatoria(obtenerPotenciaDos, 2, 10)

def sumarEnteros2(desde: Int, hasta: Int) = sumatoria(identidad, _, _)

// Igual para el resto.

// Ahora con expresiones lambda (funciones anónimas)
def sumarEnteros3(desde: Int, hasta: Int) = sumatoria(x => x, _, _)
sumatoria(x => x * x, 2, 10)