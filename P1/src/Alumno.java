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

import java.util.HashMap;

/**
 * Created by Alejandro Alcalde <contacto@elbauldelprogramador.com> on 3/11/16.
 */
public class Alumno {

    /**
     * Datos personales
     */
    private String Dni;
    private String nombre;
    private String apellidos;
    private String email;

    /**
     * Destinado a almacenar la asignatura y grupo asignado del alumno
     */
    private HashMap<Asignatura, Integer> asignacion;

    public Alumno(String email, String dni, String nombre, String apellidos) {
        this.email = email;
        Dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        asignacion = new HashMap<>(0);
    }

    /**
     * Consultar si el alumno está cursando una asignatura
     *
     * @param asignatura La asignatura a comprobar
     * @return Verdadero si el alumno cursa la asignatura, falso si no
     */
    public boolean cursaAsignatura(Asignatura asignatura) {
        return asignacion.get(asignatura) != -1;
    }

    /**
     * Asigna al alumno un grupo de prácticas para la asignatura dada
     *
     * @param asig  La asignatura a matricular
     * @param grupo El grupo en el que se matricula
     */
    void asignarGrupoPracticas(Asignatura asig, int grupo) {
        if (asignacion != null) {
            this.asignacion.put(asig, grupo);
        }
    }

    /**
     * Getters
     */
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

    /**
     * Devuelve el grupo en el que está matriculado el alumno para la asignatura dada
     *
     * @param asig La asignatura de la que consultar el grupo
     * @return
     */
    public Integer getGrupoAsignado(Asignatura asig) {
        return asignacion.get(asig);
    }

    @Override
    public String toString() {
        return String.format("\n%-8s %-25s %-25s %-12s\t",
                getDni(), getApellidos(), getNombre(), getEmail()) + asignacion.toString();
    }
}
