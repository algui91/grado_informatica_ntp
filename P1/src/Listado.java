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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Alejandro Alcalde <contacto@elbauldelprogramador.com> on 3/11/16.
 */
public class Listado {

    private static final Logger LOGGER = LogManager.getLogger(Listado.class);

    /**
     * Expresión regular para procesar los ficheros con campos separados por comas
     */
    private Pattern patronComas = Pattern.compile("(,)");

    /**
     * Expresión regular para eliminar espacios en blanco entre palabras
     */
    private Pattern patronEspacios = Pattern.compile("\\s+");

    /**
     * La lista de los alumnos, clave el dni, valor el propio alumno
     */
    private Map<String, Alumno> lista;

    /**
     * Mapa que almacena la cantidad de alumnos matriculados en cada asignatura, por grupos
     */
    private Map<Asignatura, Map<Integer, Long>> contadorGrupos;

    /**
     * Crea a partir del fichero el listado de alumnos
     * @param fichero El fichero de donde leer los datos
     * @throws IOException
     */
    public Listado(String fichero) throws IOException {
        lista = Files.lines(Paths.get(fichero))     // Leer el fichero
                .map(linea -> crearAlumno(linea))   // Aplicar un mapa a cada linea, para crear un alumno
                .collect(Collectors.toMap(          // Crear a partir del stream de alumnos un mapa
                        alumno -> alumno.getDni(),  // Clave del mapa
                        Function.identity()         // Valor del mapa, el propio objeto alumno
                ));
        contadorGrupos = new HashMap<>();
    }

    /**
     * Crea un alumno a partir de una linea del fichero de datos
     * @param line La linea a procesar con los datos del alumno
     * @return
     */
    private Alumno crearAlumno(String line) {
        List<String> infos =
                patronComas.splitAsStream(line)
                        .collect(Collectors.toList());

        return new Alumno(infos.get(3),
                infos.get(0),
                infos.get(1),
                infos.get(2));
    }

    /**
     * Lee el fichero de asignación de alumnos a prácticas y grupos de prácticas y carga los resultados
     *
     * @param archivo
     * @throws IOException
     */
    protected void cargarArchivoAsignacion(String archivo) throws IOException {

        // Obtener la asignatura
        Asignatura asig = Asignatura.valueOf(Files.lines(Paths.get(archivo))
                .findFirst().get());

        // En un principio ningún alumno tiene grupo asignado
        lista.forEach((dni, alumno) -> alumno.asignarGrupoPracticas(asig, -1));

        // Volvemos a crear el flujo y nos saltamos el nombre de asigntura y el espacio en blanco.
        Files.lines(Paths.get(archivo))
                .skip(2)                                              // Nos saltamos las dos primeras entradas
                .map(linea -> patronEspacios.split(linea))            // Separar cada linea en un a lista (dni, grupo)
                .forEach(asignacion -> {                              // Para cada par (dni, grupo), buscamos al alumno y lo damos de alta
                            Alumno alumno = lista.get(asignacion[0]); // Obtenemos al alumno
                            alumno.asignarGrupoPracticas(asig, Integer.parseInt(asignacion[1]));
                        }
                );
    }

    /**
     * Cuenta los alumnos que tiene cada grupo de prácticas
     * @return Un Map<Asignatura, Map<Integer, Long>> donde <Integer, Long> indica el grupo y cuantos alumnos tiene
     */
    protected Map<Asignatura, Map<Integer, Long>> obtenerContadoresGrupos() {
        Stream.of(Asignatura.values()) // Para cada asignatura
                .forEach(asignatura -> // Obtener para cada asignatura el contador de grupos
                        contadorGrupos.put(asignatura, obtenerContadoresGruposDeAsignatura(asignatura)));
        return contadorGrupos;
    }

    /**
     * Método auxiliar para facilitar el conteo de alumnos en grupos de prácticas
     * @param asignatura La asignatura a contar
     * @return Map<Integer, Long> Clave: El grupo de prácticas, valor: Cuantos alumnos tiene
     */
    protected Map<Integer, Long> obtenerContadoresGruposDeAsignatura(Asignatura asignatura) {
        return lista.values() // Obtenemos los alumnos
                .stream()
                .collect(Collectors.groupingBy(alumno -> alumno.getGrupoAsignado(asignatura), TreeMap::new, Collectors.counting()
//                .map(alumno -> alumno.getGrupoAsignado(asignatura)) // Otra opción
//                .collect(Collectors.groupingBy(Function.identity(), TreeMap::new, Collectors.counting()
                ));
    }

    List<Alumno> buscarAlumnosNoAsignados(String asignatura) {
        Asignatura asig = Asignatura.valueOf(asignatura);

        return lista.values()
                .stream()
                .filter(alumno -> !alumno.cursaAsignatura(asig)) // Obtener alumnos que no cursan la asignatura
                .collect(Collectors.toList());
    }

    protected long obtenerLongitud() {
        return lista.entrySet()
                .stream()
                .count();
    }

    @Override
    public String toString() {
        return lista.values()
                .toString();
    }
}
