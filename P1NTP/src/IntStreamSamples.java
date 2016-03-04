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

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alejandro Alcalde <contacto@elbauldelprogramador.com> on 3/4/16.
 */
public class IntStreamSamples {
    public static void main(String[] args) {
        Integer[] values = {2, 9, 5, 0, 3, 7, 1, 4, 8, 6};

        System.out.printf("Valores originales: %s%n", Arrays.asList(values));

        // E2 - ordenarlos
        List<Integer> ordenados = Arrays.stream(values)
                .sorted().collect(Collectors.toList());

        System.out.printf("Ordenados: %s%n", ordenados);


        // E3 - Mayores que 4
        System.out.printf("Valores mayores que 4: %s%n",
                Arrays.stream(values)
                        .filter(value -> value > 4)
                        .collect(Collectors.toList()));

        // E4 - Los valores mayores que 4 ordenados
        System.out.printf("Mayores que 4 ordenados: %s%n",
                Arrays.stream(values)
                        .filter(value -> value > 4)
                        .sorted()
                        .collect(Collectors.toList()));

        // E5 - Podemos reutilzar los objetos
//        ordenados.stream().filter(value -> value >4);
    }
}
