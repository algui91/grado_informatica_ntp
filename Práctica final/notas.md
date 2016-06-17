# Códigos Huffman

Texto -> código huffman

Se puede partir de un codigo huf genérico para un lenguaje, y que de lugar a un comprimido.

Texto normal -> todos los caracteres se representan con igual número de bytes
Texto codificado -> n;umero variable de bits por carácter

carácter más frecuentes -> menos bits, de ahí la compresión.

Si se hace para un código genérico de un lenguaje, se comprime a partir del análisis de frecuencias del lenguaje.

Lo vamos a hacer de la forma texto -> código hufman -> comprimido.

A los más frecuentes se las asignan menos bits, para que haya compresión, los más frecuentes se representan con 1 bit.

## Ejemplo

Suponemos alfabeto {A,B,C,D,E,F,G,H}

Posíble código huffman: 

Nodo raiz (ABCDEFGH) - 17 es el contador de cuantas veces aparecen los caracteres de ese nodo. contador/peso

se analiza y se hacen hijos (Binario)

nodo izda: (A) - 8
nodo drcha: (BCDEFGH) - 9

Un nodo hoja cuando tiene una sola letra, y ya sirve para codificar el carácter.

(BCDEFGH) - 9 lo dividimos
idza: (BCD) - 5
drcha: (EFGH) - 4

(BCD) - 5
izda: (B) - 3 // hoja
drcha: (CD) - 2

(CD):
(C) - 1
(D) - 1

Al final con todos los hoja se crea la codificación:

Ej, codificar ABF

Recorremos el árbol buscando el caracter correspoindiente: En el Caso de A, bifurcamos a la izda.
Cada vez que a la izsa, asignamos 0, a la derecha un 1

Resultado ABF = 0 100 1101 (cada bloque es una letra)

Para decodificar: 0 100 1101 cada vez que encontramos un 0, bifufco izda, si 0 cogemos la letra, si 1 a la derecha. Cada vez que econtramos nodo terminal, ponemos la letra.

Recomendable partir de una clase abstracta que represente un nodo del árbol.

abstrac - Nodo
Derivada de Nodo: Clase NodoHoja, que guarda un char y un int que es el contado o peso

NodoIntermedio, codifica lista de caracteres y un entero que es un contador

Estas clases convendría que fueran clases  case, para poder hacer:

nodo match {
 case NOdoHoja(_, _)
 case ....
}

Para poder distinguir qué clase estamos usando.

case class NodoHoja(_, _)
  parametros: val //datos miembro
  apply
  new nodoHoja
 
 
 
Todo esto se debe meter en un objeto singleton  que se llame Huffman

Huffman dentro tiene la definición de las clases {
  - Nodo
  - NodoHoja
  - NodoIntermedio
}

Funcionalidades dentro de Huffman:

- CalcularPeso: argumento un nodo, devuelve el peso asociado al nodo. Pensado para construir árboles. Tiene que reccorer el árbol para sumar los pesos desde abajo hacia arriba.
- ObtenerCaracteres: Argumento un nodo, devuelve una lista de caracteres, Para construir arboles de forma incremental, igualque el de arriba.
- Generar Árbol: Argumento dos nodos, salida un único nodo que une a los dos argumentos. El primero que recibe es izda y segundo drcha. Devuelve nodo como raiz e hijos izda y drcha.


## Cómo construirmos el árbol que hemos visto antes?

val hojaG = NodoHoja('G', 1) para el nodo hoja G
val hojaH = NodoHoja('H', 1) // No usamos new NodoHoja porque supoemos que son case con el apply implementado
val nodoGH = generarArbol(hojaG, hojaH)

// Seguir construyendo árbol.

nodoGH.calcularPeso , debe devolver un 2, porque G  H tienen peso 1.

Si llamamos a calcular peso de la raiz, debe devolver un 17.

La llamada final sería 
val raiz = generarArbol(HojaA, nodoBCDEFGH)  y así construimos la raiz y por tanto el arbol entero.
rail.calcularpeso.


CONSTRUCCIÓN DEL ARBOL

generarArbolCodificación

- Hay que obtener tuplas de ocurrencias // Obtendría la lista de tuplas del estilo List[(char, int), ..., (char, int)], una por cada caracter que aparece.
- generarListaHojasOrdenadas. // generar todos los nodos hoja asociados
    NodoHoja('w', 1), NodoHoja('z', 3) Hemos pasado de la tupla anterior, a objetos de la clase nodo, todos terminales, ordenados por menor frecuencia.
- Singleton comprobaría si la lista de nodos tiene un único elemento. De objetos de la clase Nodo, comprobaría si solo queda uno, y eso significa que ya es un
  árbol de codificación.
- Funcion combinar (TRabaja con la lista de nodos, inicialmente son hojas).
    - Selecciona los dos árboles (Nodos) de menos peso de la lista.
    - Genera un nuevo nodo árbol con ellos.
    - inserta nuevo nodo en la lista - (n-1) Todo es un proceso de reducción hasta que este combinado.
    - metodo hasta: Repetidas llamadas hasta generar un arbol lcompleto EJd:
        - hasta(singleton, combinar)(nodos árboles) // currying Singleton dice cuando para, y combinar le dice como reducir, ambas son funciones.

generarArbolCodificación se basa en todo esto de arriba.

DECODIFICACIÓN

def decodificar(arbol: Nodo, bits:List[Int]): List[Char] {
 si encuentra un 0 izda, 1 drcha. Terminal a la lista.
}

En el main nos da un codiguHuffmanFrances.

stringAsListaCaracteres(cadena:String): List[Char] = cadena.toList
pasa "HOLA" -> List[H, O, L, A]

CODIFICACIÓN

def codificar(arbol:Nodo, texto:List[Char]): List[Int] Muy ineficiente con nuestro árbol.

Hay que pasar de esta codificación a una nueva:

type TablaCodigo = List[Char, List[Int]] // a partir de un arbol generamos una tabla de codigos donde ya tenemos los códigos asignados a cada caracter.
A se codifica con 10101 // ej
la B con 101010/// etc

Para hacer eso necesitmoa un método:

def convertirArbolATabla(arbol:Nodo):TablaCodigo

def codificarConTabla(tabla:TablaCodigo)(caracter:Char):List[Int]


def codificarRapido  convierte a tabla como paso intermedio.
  
