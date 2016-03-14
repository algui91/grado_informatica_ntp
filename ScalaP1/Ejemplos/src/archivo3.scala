
import scala.io.Source

def calcularAnchoTamLinea(s : String) = s.length.toString.length

if (args.length > 0){
   val lineas=Source.fromFile(args(0)).getLines().toList
   val lineaMasLarga = lineas.reduceLeft(
       (a,b) => if(a.length > b.length) a else b)

   val maximoAnchoTam = calcularAnchoTamLinea(lineaMasLarga)

   for(linea <- lineas){
      // Se calcula el numero de espacios en blanco para igualar
      // con el maximo tamaño de ancho
      val numeroEspacios=maximoAnchoTam-calcularAnchoTamLinea(linea)
     
      // Se construye la cadena de blancos de relleno
      val relleno=" "*numeroEspacios

      // Se muestra la linea
      println(relleno + linea.length + "|" + linea)
   }
}
else
   Console.err.println("Introduzca nombre de archivo")

