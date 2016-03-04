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
import java.util.stream.Collectors;

/**
 * Created by Alejandro Alcalde <contacto@elbauldelprogramador.com> on 3/4/16.
 */
public class StringStreamSamples {
    public static void main(String[] args) {
        String[] cadenas =
                {"Rojo", "Naranja", "Amarillo", "Verde", "azul", "indigo", "Violeta"};

        System.out.printf("Cadenas almacenadas: %s%n", Arrays.asList(cadenas));

        // E1 - PAsar a mayus
        System.out.printf("Cadenas en mayus: %s%n",
                Arrays.stream(cadenas)
                        .map(String::toUpperCase)
                        .collect(Collectors.toList()));

        // E2 - Las cadenas mayores que m, sin tener en cuenta mayus y minus se ordenan
        System.out.printf("Cadenas filtradas y ordenadas: %s%n",
                Arrays.stream(cadenas)
                        .filter(s -> s.compareToIgnoreCase("m") > 0)
                        .sorted(String.CASE_INSENSITIVE_ORDER)
                        .collect(Collectors.toList()));

        // E3 - Ordenas mayores que m descendentemente
        System.out.printf("Cadenas filtradas y ordenadas descendentemente: %s%n",
                Arrays.stream(cadenas)
                        .filter(s -> s.compareToIgnoreCase("m") > 0)
                        .sorted(String.CASE_INSENSITIVE_ORDER.reversed())
                        .collect(Collectors.toList()));
    }
}
