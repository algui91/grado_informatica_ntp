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
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Alejandro Alcalde <contacto@elbauldelprogramador.com> on 3/11/16.
 */
public class Listado {

    private static final Logger LOGGER = LogManager.getLogger(Listado.class);

    private Pattern patronComas = Pattern.compile("(,)");
    private Pattern patronEspacios = Pattern.compile("\\s+");
    private Map<String, Alumno> lista;
    private Map<Asignatura, Map<Integer, Long>> subjectGroupCounter;

    public Listado(String fichero) throws IOException {
        try {
            lista = Files.lines(Paths.get(fichero)) // Leer el fichero
                    .map(linea -> crearAlumno(linea))   // Aplicar un mapa a cada linea, para crear un alumno
                    .collect(Collectors.toMap(          // Crear a partir del stream de alumnos un mapa
                            alumno -> alumno.getDni(),  // Clave del mapa
                            Function.identity()         // Valor del mapa, el propio objeto alumno
                    ));
        } catch (IOException e) {
            LOGGER.fatal("Can not read file " + fichero + "\nException: " + e.getMessage());
        }
    }

    public Map<Asignatura, Map<Integer, Long>> obtenerContadoresGrupos() {
        return subjectGroupCounter;
    }

    //    private Map<Integer, Long> obtenerContadoresGruposDeAsignatura(Asignatura asignatura){}
//    private List<Alumno> buscarAlumnosNoAsignados(String asignatura){}
//    public cargarArchivoAsignacion(String filename){
//        Pattern patronComas = Pattern.compile("\\s+");
//
//        try {
//            Stream<String> lineas = Files.lines(Paths.get(filename)); // Leidas todas las lineas del fichero
//        } catch (IOException e) {
//            LOGGER.fatal("Can not read file " + file + "\nException: " + e.getMessage());
//        }
//
////        El nombre de la asignatura hay que hacerlo con lineas.findFirst();
//
//        // Luego hay que generar otra vez el flujo, saltarse la primera linea y la segunda que esta en blanco
//
////        cuando volvemos a tener lineas
//        lineas.skip(2).foreach.... o map .. o lo que sea.
//    }
    protected Alumno crearAlumno(String line) {
        List<String> infos =
                patronComas.splitAsStream(line)
                        .collect(Collectors.toList());

//        39809470,  AITOR,  SERNA CARRILLO,  aiseca@ugr.es
        return new Alumno(infos.get(3),
                infos.get(0),
                infos.get(1),
                infos.get(2));
    }
    // TODO: Implement toString

    void cargarArchivoAsignacion(String archivo) throws IOException {
        Stream<String> lineas = Files.lines(Paths.get(archivo)); // Leidas todas las lineas del fichero

        // Obtener la asignatura
        Asignatura asig = lineas.findFirst()
                .map(first -> Asignatura.valueOf(first))
                .get();

        // Volvemos a crear el flujo y nos saltamos el nombre de asigntura y el espacio en blanco.
        Files.lines(Paths.get(archivo))
                .skip(2)                                              // Nos saltamos las dos primeras entradas
                .map(linea -> patronEspacios.split(linea))            // Separar cada linea en un a lista (dni, grupo)
                .forEach(asignacion -> {                              // Para cada par (dni, grupo), buscamos al alumno y lo damos de alta
                            Alumno alumno = lista.get(asignacion[0]); // Obtenemos al alumno
                            alumno.asignarGrupoPracticas(asig, Integer.parseInt(asignacion[1]));

                            Stream.of(Asignatura.values())
                                    .filter(asignatura -> asignatura != asig) // Para cada asig no matriculada
                                    .forEach(noMatriculada -> {
                                        alumno.asignarGrupoPracticas(noMatriculada, -1); // Asignamos un -1
                                    });
                        }
                );
        System.out.printf(" ");

//        contadores.entrySet()
//                .stream()
//                .collect(
//                        Collectors.groupingBy(entrada -> entrada.getKey().charAt(0),
//                                TreeMap::new, Collectors.toList()))
//                .forEach((letra, listaPalabras) -> {
//                    System.out.printf("%n%C%n", letra);
//                    listaPalabras.stream().forEach(entrada -> System.out.printf(
//                            "%13s: %d%n", entrada.getKey(), entrada.getValue()));
//                });


//                .forEach(System.out::println);

//        List<String> dniGrupo = patronEspacios.split
//                .collect(Collectors.toList());
//                .forEach(System.out::println);


//               TODO: Primero tengo que pasar la linea a lista, para poder hacer el get del dni y del grupo, luego pasarlo
//                a la función asignarGrupo
//                .forEach(dni -> lista.get(dni).asignarGrupoPracticas(asig, ));

//        lista = Files.lines(Paths.get(fichero)) // Leer el fichero
//                .map(linea -> crearAlumno(linea))   // Aplicar un mapa a cada linea, para crear un alumno
//                .collect(Collectors.toMap(          // Crear a partir del stream de alumnos un mapa
//                        alumno -> alumno.getDni(),  // Clave del mapa
//                        Function.identity()         // Valor del mapa, el propio objeto alumno
//                ));


//        El nombre de la asignatura hay que hacerlo con lineas.findFirst();
//
//        // Luego hay que generar otra vez el flujo, saltarse la primera linea y la segunda que esta en blanco
//
//        cuando volvemos a tener lineas
//        lineas.skip(2).foreach.... o map .. o lo que sea.
    }

    int obtenerLongitud() {
        return 1;
    }

    List<Alumno> buscarAlumnosNoAsignados(String asignatura) {
        List<Alumno> s = null;
        return s;
    }

    Map<Integer, Long> obtenerContadoresGruposDeAsignatura(Asignatura asignatura) {
        Map<Integer, Long> s = null;
        return s;
    }
//
//    public static void main(String[] args) {
//        try {
//            Listado listado = new Listado("./data/datos.txt");
//            listado.cargarArchivoAsignacion("./data/asignacionES.txt");
//        } catch (IOException e) {
//            System.out.println("Error en lectura de archivo de datos");
//        }
//    }
}
