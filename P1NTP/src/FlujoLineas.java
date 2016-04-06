
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

/**
 * Clase para mostrar el uso de flujos con texto
 */
public class FlujoLineas {

   // Metodo main para probar
   public static void main(String[] args) throws IOException {
      /******************** E1 ***************************/
      // Se define un patron que representa varios espacios en blanco
      // (uno o mas consecutivos)
      Pattern pattern = Pattern.compile("\\s+");

      // Se cuentan las ocurrencias de cada palabra
      // Se usa un mapa para almacenar pares tipo <palabra - contador>
      // 1. las lineas se toman del archivo de texto. El metodo lines
      //    crea un flujo con el texto de cada linea
      // 2. cada linea se trata como una cadena, un objeto de la clase
      //    String
      // 3. se hace mapeo para quitar de cada linea los apostrofes
      //      y cualquier signo de puntuacion
      // 4. separa cada linea en las palabras que contiene
      // 5. itera sobre el flujo
      Files.lines(Paths.get("/home/gte/mgomez/alicia.txt"),StandardCharsets.ISO_8859_1).
              forEach(System.out::println);

      /******************** E2 ***************************/
      Map<String, Long> contadores
              = Files.lines(Paths.get("/home/gte/mgomez/alicia.txt"), StandardCharsets.ISO_8859_1)
              .map(linea -> linea.replaceAll("(?!')\\p{Punct}", ""))
              .flatMap(linea -> pattern.splitAsStream(linea))
              .filter(palabra -> !palabra.isEmpty())
              .collect(Collectors.groupingBy(String::toLowerCase,
                              TreeMap::new, Collectors.counting()));

       // Conveniencia de ir haciendo las operaciones paso a paso para ver que
       // obtenemos:
       // 1. obtencion de las lines del archivo
       Stream<String> lineas = Files.lines(Paths.get("/home/gte/mgomez/alicia.txt"), StandardCharsets.ISO_8859_1);

       // 2. mapeo para obtener las lineas quitando los signos de puntuacion
       Stream<String> flujoLineas = lineas.map(linea -> linea.replaceAll("(?!')\\p{Punct}", ""));

       // 3. flatMap para pasar de cadenas que representan lineas a cadenas
       // que representan las palabras sueltas, una a una
       Stream<String> flujoPalabras = flujoLineas.flatMap(linea -> pattern.splitAsStream(linea));

       // 4. se eliminan las palabras vacias
       Stream<String> flujoPalabrasOk = flujoPalabras.filter(palabra -> !palabra.isEmpty());

       // 5. agrupamiento: se agrupan por su contenido en minuscula (todas las
       // aparariciones de una misma palabra estaran agrupadas), se almacenan en
       // un TreeMap y se reduce la coleccion (serian cadenas identicas, con todas
       // las repeticiones de una misma palabra) mediante conteo
       TreeMap<String, Long> coleccion = flujoPalabrasOk.collect(
               Collectors.groupingBy(String::toLowerCase,
                                     TreeMap::new, Collectors.counting()));

       /******************** E3 ***************************/
      // Muestra las entradas de la coleccion
      // 1. creacion del flujo sobre las entradas del mapa (palabras)
      // 2. agrupa las palabras por su primera letra
      // 3. iteracion sobre cada entrada del agrupamiento formado
      //    (letra - lista de palabras)
      // 4. muestra todas las palabras debidamente agrupadas
      contadores.entrySet()
              .stream()
              .collect(
                      Collectors.groupingBy(entrada -> entrada.getKey().charAt(0),
                              TreeMap::new, Collectors.toList()))
              .forEach((letra, listaPalabras) -> {
                 System.out.printf("%n%C%n", letra);
                 listaPalabras.stream().forEach(entrada -> System.out.printf(
                                 "%13s: %d%n", entrada.getKey(), entrada.getValue()));
              });

       // Se nuevo se podia descomponer la operacion paso a paso
       // 1. creacion de flujo con las entradas del mapa de contadores
       Stream<Map.Entry<String, Long>> flujoEntradas = contadores.entrySet().stream();

       // 2. recogemos todas las entradas con otra estructura: un mapa donde la clave
       // sera la primera letra y el valor una lista de entradas del mapa anterior,
       // tipo <String, Long> (palabra - contador de apariciones)
       TreeMap<Character, List<Map.Entry<String, Long>>> coleccionFinal = flujoEntradas.collect(
               Collectors.groupingBy(entrada -> entrada.getKey().charAt(0),
                       TreeMap::new, Collectors.toList()));

       // 3. ahora se itera para mostrar su contenido: hace falta una doble iteracion,
       // ya que cada entrada a su vez termina refiriendose a una lista
       coleccionFinal.forEach((letra, listaPalabras) -> {
           System.out.printf("%n%C%n", letra);

           // Se itera sobre la lista de palabras
           listaPalabras.stream().forEach(entrada -> System.out.printf(
                   "%13s: %d%n", entrada.getKey(), entrada.getValue()));
       });
   }
}
