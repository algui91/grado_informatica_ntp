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

/**
 * Created by Alejandro Alcalde <contacto@elbauldelprogramador.com> on 2/26/16.
 */

import java.util.Random;
import java.util.stream.IntStream;

/**
 * Demostración de operaciones sobre flujo con valores enteros
 */
public class IntStreamOperations {

    private int valores[];

    public IntStreamOperations(int numeroValores) {
        this.valores = new int[numeroValores];

        Random generator = new Random();
        for (int i = 0; i < numeroValores; i++) {
            valores[i] = generator.nextInt(101);
        }
    }

    /**
     * Muestra los valores usando funciones lambda
     */
    public void mostrarValoresFuncional() {
//        IntStream.of(this.valores).forEach(value -> System.out.printf("%d%n", value));
        // Equivalente, referencia a método
        IntStream.of(this.valores)
                .forEach(System.out::println); // Final, no da como salida flujo, da resultado
    }

    public long contarValoresFuncional() {
        return IntStream.of(this.valores)
                .count();
    }

    public int obtenerMinimo() {
        return IntStream.of(this.valores)
                .min()
                .getAsInt();
    }

    public int obtenerMaximo() {
        return IntStream.of(this.valores).max().getAsInt();
    }

    public long obtenerSuma() {
        return IntStream.of(this.valores).sum();
    }

    public long obtenerSumaReduce() {
//        0 es el valor inicial
//        luego expresión lambda con dos argumentos, y los suma.
//        Primera iter:
//                x=0, y=this.valores[0]
//                x=pasoanterior, y=this.valores[1]
        return IntStream.of(this.valores)
                .reduce(0, (x, y) -> (x + y));
    }

    public long obtenerSumaReduceCuadrado() {
        return IntStream.of(this.valores)
                .reduce(0, (x, y) -> (x + y * y));
    }

    public long obtenerProducto() {
        return IntStream.of(this.valores)
                .reduce(1, (x, y) -> (x * y));
    }

    /**
     * SUma de cuadrados con map
     */
    public long obtenerSumaCuadradosMap() {
        return IntStream.of(this.valores)
                .map(x -> x * x)
                .sum();
    }

    public void ordenarPares() {
        IntStream.of(this.valores)
                .filter(value -> (value & 1) == 0)
                .sorted()
                .forEach(System.out::println);
    }

    public void multiplicaFactorObtenParesOrdenarMostrar(int factor) {
        IntStream.of(this.valores)
                .map(x -> x * factor)
                .filter(value -> (value & 1) == 0)
                .sorted()
                .forEach(System.out::println);
    }

//    DEbe ser valores Integer, no int
//    public List<Integer> obtenerMayor4() {
//
//        return Arrays.stream(this.valores)
//                .filter(value -> value > 4)
//                .collect(Collectors.toList());
//    }

    public static void main(String[] args) {
        long numeroValores, suma;
        int min, max;
        double media, sumaCuadrados, producto;

        IntStreamOperations objeto = new IntStreamOperations(10000);

        objeto.mostrarValoresFuncional();

        System.out.printf("%nNúmero de elementos: %d%n", objeto.contarValoresFuncional());
        System.out.printf("Mínimo: %d%n", objeto.obtenerMinimo());
        System.out.printf("Mínimo: %d%n", objeto.obtenerMaximo());
        System.out.printf("Suma: %d%n", objeto.obtenerSuma());
        System.out.printf("Suma Reduce: %d%n", objeto.obtenerSumaReduce());
        System.out.printf("Suma Reduce cuadrada: %d%n", objeto.obtenerSumaReduceCuadrado());
        System.out.printf("Producto Reduce: %d%n", objeto.obtenerProducto());
        System.out.printf("Suma Cuadrado con MAp: %d%n", objeto.obtenerSumaCuadradosMap());

        objeto.ordenarPares();
        objeto.multiplicaFactorObtenParesOrdenarMostrar(2);
    }
}
