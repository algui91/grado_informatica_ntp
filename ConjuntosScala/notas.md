Conjunto - función característica.

Si queremos representar los enteros negativos
  - Podemos hacer por enumeración (Sin sentido)
  - Fun. característica, lo correcto. 
  
Para los negativos exp lambda: (x: Int) => x < 0 # Func caract asociada al conjunto.
Se define un tipo conjunto 

type conjunto = Int => Boolean (Esto es el prototipo de una función.)

Como sería la función contiene:

// Recibe como argumento una función.
def contiene(c:Conjunto, elemento:Int):Boolean = c(elemento) // Si el elemento pertenece al conjunto


# Funciones básicas.

// Debe crear una función, que solo devuelva verdadero cuando el elemento es igual
// que el elemento único del conjunto
def conjuntoUnElemento(elemento: Int):Conjunto

// Función union
// Devuelve la función caracteristica asociada aa los elementos que son de uno o de otro conj.
def union(c1:conjunto, c2:conjunto):Conjunto

// Funcion intersec
// Dev. func caracteristica también, pertenecen a ambos a la vez.
def union(c1:conjunto, c2:conjunto):Conjunto

def diferencia(c1:conjunto, c2:conjunto):Conjunto En c1 pero no en c2

def filtrado(c1:conjunto, predicado:Int => Boolean):Conjunto // deja en el conjiunto de salida los que cumplen el predicado.

# Las siguientes funciones requieren iterar

def paraTodo(c:conjunto, predicado:Int => Boolean):Boolean

// definimos un rango para poder iterar. suponemos -1000 y 1000. 

Basándose en la relacion paraTodo, creamos

def existe(c:Conjunto, predicado: Int => Boolean): Boolean.

Otra última

def map(c:conjunto, funcion:Int=>Int):Conjunto // Transforma todos los elementos 



# Para hacer los tests

trait ConjuntosPrueba {
  val c1 = ConjuntoUnElemento(1)
  val c2 = ConjuntoUnElemento(2)
  val c3 = ConjuntoUnElemento(3)
}


test ( "Contiene valor 1") { // nombre
  new ConjuntoPrueba{ // Asi creamos los conjuntos de prueba
    assert(contiene(c1, 1))  // Así si debe ser true
    // Si queremos que aparezca u error
    assert(contiene(c1, 1), "C1 no contiene a 1")
  }
}


test ("Test de union"){
  val conjunto1 = (x:Int) => x>3 // Fun. caracteristica
  val conjunto1 = (x:Int) => x>5
  
  // Hacemos uncion de ambos en el test, para ver si esta bien implementada.
  val conjuntoUnion = union(conjunto1, conjunto2)
  // Comprobar que 4 \in conjuntounion
  assert(contiene(conjuntounion, 4))
  assert(contiene(conjuntounion, 3) == false)
}



test("Test de paraTodo"){
  val conjunto = (x:Int) => x<10
  assert(!paraTodo(conjunto, x => x > 0))
  assert(paraTodo(conjunto, x => x < 15))
}

test("Test Existe") {
  val conjunto = (x:Int) => x < 10
  
  assert(!existe(conjunto, x => x>10))
  assert(existe(conjunto, x => x<10))
}

test("Test de map") {
  val conjunto = (x:Int) => x < 10
  val resultado = map(conjunto, x => x+25))
  
  assert(contiene(resultado, 30))
  assert(!contiene(resultado, 36))
}










def conjuntoUnElemento (elemento:Int) = (x:Int) => x == elemento 
