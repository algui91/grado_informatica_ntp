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

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Alejandro Alcalde <contacto@elbauldelprogramador.com> on 3/4/16.
 */
public class ProcesarEmpleados {
    public static void main(String[] args) {
        Empleado[] empleados = {
                new Empleado("Juan", "Lopez", 5000, "IT"),
                new Empleado("Antonio", "Garcia", 7600, "IT"),
                new Empleado("Mateo", "Insausti", 3587.5, "Ventas"),
                new Empleado("Joaquín", "Fernandez", 4700.77, "Marketing"),
                new Empleado("Lucas", "Martinez", 6200, "IT"),
                new Empleado("Pedro", "Garcia", 3200, "Ventas"),
                new Empleado("Fernado", "Gonzalez", 4236.4, "Marketing")};

        // E1 - Obtener lista de los empleados
        List<Empleado> lista = Arrays.asList(empleados);

        // E2 - Crear flujo
        Stream<Empleado> flujo = lista.stream();

        // E3 - Mostrar listado
        System.out.printf("Lista de empleados%n:");
        flujo.forEach(System.out::println);

        // E4 - Mostrar salarios en rango 4000 6000
        Predicate<Empleado> condicion
                = empleado -> (empleado.obtenerSueldo() >= 4000 &&
                empleado.obtenerSueldo() <= 6000);

        // E5 - Muestra los empleados con los sueldos que cumplen la condición
        System.out.printf("%nEmpleados seleccionados  y ordenados:%n");
        lista.stream()
                .filter(condicion)
                .sorted(Comparator.comparing(Empleado::obtenerSueldo))
                .forEach(System.out::println);

        // E6 - Muestra el primer empleado con el sueldo entre el rango
        System.out.printf("%nPrimer empleado con sueldo entre rango:%n%s%n",
                lista.stream()
                        .filter(condicion)
                        .findFirst()
                        .get());

        // E7 - Referencias a funciones
        Function<Empleado, String> refNombre = Empleado::obtenerNombre;
        Function<Empleado, String> refPrimerApellido = Empleado::obtenerPrimerApellido;

        // E8 - Comparador para empleados por nombre y apellido
        Comparator<Empleado> comparador =
                Comparator.comparing(refPrimerApellido)
                        .thenComparing(refNombre);

        // E9 - Mostrarlos ordenados
        System.out.printf("%nEmpleados en orden ascendente con mi comparador:%n");
        lista.stream()
                .sorted(comparador)
                .forEach(System.out::println);

        // E10 - Descendente
        System.out.printf("%nEmpleados en orden descendente con mi comparador:%n");
        lista.stream()
                .sorted(comparador.reversed())
                .forEach(System.out::println);

        // E11 - Apellidos sin repeticiones
        System.out.printf("%nApellidos sin repeticiones:%n");
        lista.stream()
                .map(Empleado::obtenerPrimerApellido)
                .distinct()
                .sorted()
                .forEach(System.out::println);

        // E12 - Muestra solo los nombres
        System.out.printf("%nNombre de los empleados:%n");
        lista.stream()
                .sorted(comparador)
                .map(Empleado::obtenerNombre)
                .forEach(System.out::println);

        // E13 - Agrupa empleados por departamentos
        System.out.printf("%nEmpleados por departamento:%n");
        Map<String, List<Empleado>> agrupadosPorDepartamentos =
                lista.stream()
                        .collect(Collectors.groupingBy(Empleado::obtenerDepartamento));

        agrupadosPorDepartamentos.forEach(
                (departamento, empleadosEnDepartamento) -> {
                    System.out.println(departamento);
                    empleadosEnDepartamento.forEach(System.out::println);
                }
        );

        // E14 - Cuenta el numero de empleados en cada departamento
        System.out.printf("%nCuenta el numero de empleados en cada departamento%n");
        Map<String, Long> cuentaEmpleadosPorDepartamento =
                lista.stream()
                        .collect(
                                Collectors.groupingBy(
                                        Empleado::obtenerDepartamento, TreeMap::new, Collectors.counting()
                                ));

        cuentaEmpleadosPorDepartamento.forEach(
                (departamento, cuenta) -> System.out.printf("%s tiene %d empleados(s)%n", departamento, cuenta));

        // E15 - Suma de sueldos
        System.out.printf("%nSuma del sueldo de los empleados: %.2f%n",
                lista.stream()
                        .mapToDouble(Empleado::obtenerSueldo) // empleado -> empleado.obtenerSueldo.
                        .sum());
        // Ahora con reduce
        System.out.printf("%nSuma con reduce: %.2f%n",
                lista.stream()
                        .mapToDouble(Empleado::obtenerSueldo)
                        .reduce(0, (value1, value2) -> value1 + value2));

        // E16 - MEdia
        System.out.printf("%nMedia del sueldo de los empleados: %.2f%n",
                lista.stream()
                        .mapToDouble(Empleado::obtenerSueldo) // empleado -> empleado.obtenerSueldo.
                        .average()
                        .getAsDouble());

        DoubleSummaryStatistics stadisticas = lista.stream()
                .mapToDouble(Empleado::obtenerSueldo)
                .summaryStatistics();
    }
}