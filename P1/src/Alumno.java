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
import java.util.HashMap;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Alejandro Alcalde <contacto@elbauldelprogramador.com> on 3/11/16.
 */
public class Alumno {

    private static final Logger LOGGER = LogManager.getLogger(Alumno.class);

    /**
     * National Identity Document
     */
    private int Dni;

    private String nombre;

    private String apellidos;

    private String email;

    /**
     * Stores the assigments in a group
     */
    private HashMap<Asignatura, Integer> asignacion;

    public Alumno(String file) {
        // Se leen las lineas del archivo
        try {
            Stream<String> lineas = Files.lines(Paths.get(file));
        } catch (IOException e) {
            LOGGER.fatal("Can not read file " + file + "\nException: " + e.getMessage());
        }
    }
//
//    public boolean cursaAsignatura(Asignatura asignatura) {
//    }

}
