
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlujoArraysCadenas {

   // Metodo main para probar
   public static void main(String[] args){
      // Se define un array de objetos de la clase String
      String[] cadenas = 
         {"Rojo", "Naranja", "Amarillo", "Verde", "azul",
          "indigo", "Violeta"};

      // Se muestran las cadenas almacenadas
      System.out.printf("Cadenas originales: %s%n", Arrays.asList(cadenas));

      /******************** E1 ***************************/
      // Se pasan a mayuscula
      System.out.printf("Cadenas en mayuscula: %s%n",
         Arrays.stream(cadenas)
               .map(String::toUpperCase)
               .collect(Collectors.toList()));

      /******************** E2 ***************************/
      // las cadenas mayores que "m" (sin tener en cuenta mayusculas
      // o minusculas) se ordenan de forma ascendente
      System.out.printf("Cadenas filtradas y ordenadas (orden ascendente): %s%n",
         Arrays.stream(cadenas)
               .filter(s -> s.compareToIgnoreCase("m") > 0)
               .sorted(String.CASE_INSENSITIVE_ORDER)
               .collect(Collectors.toList()));

      /******************** E3 ***************************/
      // las cadenas mayores que "m" se filtran y se ordenan
      // en modo descendente
      System.out.printf("cadenas filtradas y ordenadas (orden descendente): %s%n",
         Arrays.stream(cadenas)
               .filter(s -> s.compareToIgnoreCase("m") > 0)
               .sorted(String.CASE_INSENSITIVE_ORDER.reversed())
               .collect(Collectors.toList()));
   }
} 









