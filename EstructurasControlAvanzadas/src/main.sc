//def sumar(desde: Int, hasta: Int, funcion: (Int) => Int): Int = {
//  if (desde > hasta) 0
//  else funcion(desde) + sumar(desde + 1, hasta, funcion)
//}
//
//def sumarEnteros(desde: Int, hasta: Int): Int =
//  sumar(desde, hasta, x => x)

// Vamos a simplicarlo para que no haya que pasar el desde y hasta


// Va a devolver una función, que harán el papel de desde, hasta
def sumar(funcion: Int => Int): (Int, Int) => Int = {
  def sumar0(desde: Int, hasta: Int): Int =
    if (desde > hasta) 0
    else funcion(desde) + sumar0(desde + 1, hasta)
  sumar0 // Devolvemos la función
}

def sumarEnteros = sumar(x => x)
def sumarCuadrados = sumar(x => x * x)
//def sumarPotenciasDos = sumar(ObtenerPotenciasDos)

sumarEnteros(10, 20)

// Podemos usar sumar de forma directa, pra que se comporte como queramos.
sumar(x => x)(1, 10) // Currying, estamos pasando los parámetros desde, hasta
//f(args)(args2) // Se evalua de izquierda a derecha

// Para definir sumar usando currying.

def sumaCurrying(funcion: Int => Int)(desde: Int, hasta: Int): Int =
  if (desde > hasta) 0
  else funcion(desde) + sumaCurrying(funcion)(desde + 1, hasta)


// Ejercicios sobre Currying

// Encontrar el punto fijo de una función (Que cumple f(x) = x
// f(x), f(f(x)) .... converge a x = f(x), paramos cuando obtengamos tolerancia

// Def puntofico
//argumentos:(funcion)(valorinicial)
// Implementar un auxiliar dentro, iterar, que calcule el siguiente, f(valor),
// Si bien aprox, devuelve siguiente, en caso contrario seguir iteranto sobre siguiente
// Para la raiz por ejemplo, sqrt(x), queremos un y => y*y = x, lo que hacemos
//es y = x/y

def puntoFijo(f: Double => Double)(inicio: Double) = {

  val tolerancia = 1e-10
  def bienAproximado(valor: Double, siguiente: Double): Boolean =
    Math.abs((valor - siguiente) / valor) < tolerancia

  def iterar(valor: Double): Double = {
    val siguiente = f(valor)
    if (bienAproximado(valor, siguiente)) siguiente
    else iterar(siguiente)
  }
  iterar(inicio)
}


// La del profesor

def sqrt2(x: Double) = puntoFijo(y => (y + x / y) / 2)(1.0)
//def sqrt(x: Double) = puntoFijo(y => x / y)(1.0)
sqrt2(10)
Math.sqrt(10)

// Promediar muestras
// Esta función es genérica, aplicable a cualquier función.j
def promediarMuestras(f: Double => Double)(x: Double) = (x + f(x)) / 2

def sqrt3(x:Double) = puntoFijo(promediarMuestras(y=>x/y))(1.0)

sqrt3(60)