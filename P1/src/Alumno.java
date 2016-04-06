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

import java.util.HashMap;

/**
 * Created by Alejandro Alcalde <contacto@elbauldelprogramador.com> on 3/11/16.
 */
public class Alumno {

    private static final Logger LOGGER = LogManager.getLogger(Alumno.class);

    /**
     * National Identity Document
     */
    private String Dni;

    private String nombre;

    private String apellidos;

    private String email;

    /**
     * Stores the assignments in a group
     */
    private HashMap<Asignatura, Integer> asignacion;

    public Alumno(String email, String dni, String nombre, String apellidos) {
        this.email = email;
        Dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        asignacion = new HashMap<>(0);
    }

    //
//    public boolean cursaAsignatura(Asignatura asignatura) {
//    }


    void asignarGrupoPracticas(Asignatura asig, int grupo) {
        if (asignacion != null) {
            this.asignacion.put(asig, grupo);
        }
    }

    public String getEmail() {
        return email;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return Dni;
    }

    @Override
    public String toString() {
        return String.format("%-8s %-8s %s   %s",
                getDni(), getApellidos(), getNombre(), getEmail());
    }
}
