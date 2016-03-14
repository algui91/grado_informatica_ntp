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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by Alejandro Alcalde <contacto@elbauldelprogramador.com> on 3/11/16.
 */
public class Listado {

    private Map<Asignatura, Map<Integer, Long>> subjectGroupCounter;

    public Listado(String file) {
        try {
            Stream<String> lines = Files.lines(Paths.get(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<Asignatura, Map<Integer, Long>> obtenerContadoresGrupos() {
        return subjectGroupCounter;
    }

//    private Map<Integer, Long> obtenerContadoresGruposDeAsignatura(Asignatura asignatura){}
//    private List<Alumno> buscarAlumnosNoAsignados(String asignatura){}
    private Alumno crearAlumno(String line){

    }
    // TODO: Implement toString
}
