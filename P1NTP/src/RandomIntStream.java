import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Clase para generacion aleatoria de lanzamiento de dados
 */
public class RandomIntStream {
   // Metodo main
   public static void main(String[] args) {

      /******************** E1 ***************************/
      // Se crea el generador de numeros aleatorios
      SecureRandom random = new SecureRandom();

      // se lanza el dado un determinado número de veces y se muestra el
      // resumen del resultado
      System.out.printf("%-10s%-10s%-10s%n", "Resultado", "Contador", "Prob");

      // el valor 100_000_000 se indica de esta manera para facilitar
      // la legibilidad del codigo
      long muestras = 100_000_000;

      /******************** E2 ***************************/
      // Se generan las muestras que constituyen un flujo
      random.ints(muestras, 1, 7)
              .boxed()
              .collect(Collectors.groupingBy(Function.identity(),
                      Collectors.counting()))
              .forEach((resultado, contador) ->
                      System.out.printf("%-10d%-10d%f%n", resultado, contador,
                              ((double) contador / muestras)));


      // Realizacion de las operaciones paso a paso
      IntStream ints = random.ints(muestras, 1, 7);
      Stream<Integer> boxed = random.ints(muestras, 1, 7).boxed();

      // Paso 1: creacion de flujo con numeros aleatorios. Es preciso empaquetar
      // el resultado para tratar con objetos de la clase Integer
      Stream<Integer> flujoEnterosAleatorios = random.ints(muestras, 1, 7).boxed();

      // Paso 2: los valores enteros aleatorios se agrupan por su valor. De esta forma
      // se obtiene un diccionario en que la clave será el resultado del dado (valores
      // desde 1 a 6) y el valor el numero de veces en que aparece dicho resultado.
      // Si en groupingBy no se pasa como argumento Collectors.counting entonces se
      // obtendria un mapa tipo Map<Integer,List<Integer>>
      Map<Integer, Long> mapaValorContador = flujoEnterosAleatorios.collect
              (Collectors.groupingBy(Function.identity(),
                      Collectors.counting()));

      // Esto da problemas porque luego el entero no podria usarse en Function.identity
      //IntStream flujoEnterosPrimitivosAleatorios = random.ints(muestras, 1, 7);
      //flujoEnterosPrimitivosAleatorios.collect(Collectors.groupingBy(Function.identity(),
      //        Collectors.counting()));

      // Paso 3: se muestra la informacion
      mapaValorContador.forEach((resultado, contador) ->
              System.out.printf("%-10d%-10d%f%n", resultado, contador, ((double) contador / muestras))
      );
   }
} 