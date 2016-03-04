
import java.util.Random;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.DoubleStream;

// Demostracion de operaciones sobre flujo con valores enteros
public class IntStreamOperations {

    // Dato miembro para almacenar los valores
    private int valores[];
    
    // Constructor de la clase
    public IntStreamOperations(int numeroValores) {
        // Se reserva espacio para el array
        valores = new int[numeroValores];

        // Se generan valores aleatorios entre 0 y 100 
        // para rellenar el array
        Random generador = new Random();
        for (int i = 0; i < numeroValores; i++) {
            valores[i] = generador.nextInt(101);
        }
    }

    // Metodo para mostrar los valores mediante expresiones
    // lambda y flujos
    public void mostrarValoresFuncional() {
        // Se muestran los valores almacenados mediante una
        // expresion lambda trabajando sobre un flujo de la
        // clase IntStream: se crea el flujo, se itera mediante
        // forEach y para cada valor se aplica la expresion
        // lambda
        IntStream.of(valores)
                .forEach(value -> System.out.printf("%d ", value));
        System.out.println();
    }

    // Metodo para mostrar los valores de la forma habitual
    public void mostrarValores() {
        // Se muestran los valores mediante un bucle
        for (int i = 0; i < valores.length; i++) {
            System.out.printf("%d ", valores[i]);
        }
        System.out.println();
    }
    
    // Metodo para obtener la cuenta de valores mediante flujo
    public long contarValoresFuncional(){
        // Se obtiene el numero de elementos y se devuelve
        long contadorValores=IntStream.of(valores).count();
        return contadorValores;
    }
    
    // Metodo para obtener la cuenta de valores mediante metodo
    // tradicional: uso de length. Este deberia ser mas rapido
    public long contarValores(){
        return valores.length;
    }
    
    // Metodo para obtener el valor minimo com aproximacion funcional
    public int obtenerMinimoFuncional(){
        int minimo=IntStream.of(valores).min().getAsInt();
        return minimo;
    }
    
    // Metodo para obtener el minimo con aproximacion usual
    public int obtenerMinimo(){
        int minimo=valores[0];
        
        // Se considera el resto de valores
        for(int i=1; i < valores.length; i++){
            if (valores[i] < minimo){
                minimo=valores[i];
            }
        }
        
        // Se devuelve el minimo
        return minimo;
    }
    
    // Metodo para obtener el valor maximo com aproximacion funcional
    public int obtenerMaximoFuncional(){
        int maximo=IntStream.of(valores).max().getAsInt();
        return maximo;
    }
    
    // Metodo para obtener el maximo con aproximacion usual
    public int obtenerMaximo(){
        int maximo=valores[0];
        
        // Se considera el resto de valores
        for(int i=1; i < valores.length; i++){
            if (valores[i] > maximo){
                maximo=valores[i];
            }
        }
        
        // Se devuelve el minimo
        return maximo;
    }
    
    // Metodo para obtener la suma de todos los valores con
    // aproximacion funcional
    public long obtenerSumaFuncional(){
        long suma=IntStream.of(valores).sum();
        return suma;
    }
    
    // Metodo para obtener suma con reduce y expresiones
    // lambda
    public long obtenerSumaReduceExpresionesLambda(){
        long suma=IntStream.of(valores).reduce(0, (x, y) -> x + y);
        return suma;
    }
    
    // Metodo para obtener la suma con la aproximacion habitual
    public long obtenerSuma(){
        long suma=0;
        
        // Se considera cada valor
        for(int i=0; i < valores.length; i++){
            suma+=valores[i];
        }
        
        // Se devuelve el valor de la suma
        return suma;
    }
    
    // Metodo para obtener la media con aproximacion funcional
    public double obtenerMediaFuncional(){
        double media=IntStream.of(valores).average().getAsDouble();
        return media;
    }
    
    // Metodo para obtener la media con aproximacion habitual
    public double obtenerMedia(){
        double suma=obtenerSuma();
        double numeroValores=contarValores();
        // Se calcula y devuelve la media
        return(suma/numeroValores);
    }
    
    // Metodo para obtener la suma de los valores al cuadrado: uso de
    // reduce y expresion lambda
    public double obtenerSumaCuadradosFuncional(){
        double sumaCuadrados=IntStream.of(valores).reduce(0, (x, y) -> x + y * y);
        return sumaCuadrados;
    }
    
    // Metodo para obtener la suma de los valores al cuadrado con
    // enfoque usual
    public double obtenerSumaCuadrados(){
        double sumaCuadrados=0;
        
        // Se suma cada valor
        for(int i=0; i < valores.length; i++){
            sumaCuadrados+=valores[i]*valores[i];
        }
        
        // Devuelve la suma de los cuadrados
        return sumaCuadrados;
    }
    
    // Metodo para obtener el producto de los valores con reduce y
    // expresion lambda
    public double obtenerProductoFuncional(){
        double producto=IntStream.of(valores).asDoubleStream().reduce(1, (x, y) -> x * y);
        return producto;
    }
    
    // Metodo para hacer producto con aproximacion habitual
    public double obtenerProducto(){
        double producto=1;
        
        // Se recorren todos los valores
        for(int i=0; i < valores.length; i++){
            producto*=valores[i];
        }
        
        // Devuelve el valor del producto
        return producto;
    }
    
    // Metodo para obtener la ordenacion de los valores pares con
    // aproximacion funcional: filter, sorted
    public void ordenarParesFuncional(){
        // Se realiza la tarea sobre el flujo: filtrado, ordenacion
        // y se muestran
        IntStream.of(valores).filter(value -> value % 2 == 0)
                .sorted()
                .forEach(value -> System.out.printf("%d ", value));
        System.out.println();
    }
    
    // Metodo para obtener la ordenacion de los valores pares de
    // forma manual
    public void ordenarPares(){
        // Se declara un array para almacenar los valores pares
        int [] pares=new int[valores.length];
        int contadorPares=0;
        
        // Se rellena con los pares
        for(int i=0; i < valores.length; i++){
            if (valores[i] % 2 == 0){
                pares[contadorPares]=valores[i];
                contadorPares++;
            }
        }
        
        // Una vez seleccionados, se ordenan usando el metodo
        // sort de la clase Arrays
        Arrays.sort(pares,0,contadorPares-1);
        
        // Ahora se muestra el resultado
        for(int i=0; i < contadorPares; i++){
            System.out.printf("%d ", pares[i]);
        }
        System.out.println();
    }
    
    // Metodo para obtener los valores pares multiplicados por un valor y
    // ordenados: uso de filtro, mapeo, ordenacion y forEach
    public void ordenarParesMultiplicadosFuncional(int factor){
        IntStream.of(valores).filter(value -> value % 2 == 0)
                .map(value -> value * factor)
                .sorted()
                .forEach(value -> System.out.printf("%d ", value));
        System.out.println();
    }
    
    // Metodo para obtener la ordenacion de los valores pares de
    // forma manual
    public void ordenarParesMultiplicados(int factor){
        // Se declara un array para almacenar los valores pares
        int [] pares=new int[valores.length];
        int contadorPares=0;
        
        // Se rellena con los pares
        for(int i=0; i < valores.length; i++){
            if (valores[i] % 2 == 0){
                pares[contadorPares]=valores[i]*factor;
                contadorPares++;
            }
        }
        
        // Una vez seleccionados, se ordenan usando el metodo
        // sort de la clase Arrays
        Arrays.sort(pares,0,contadorPares-1);
        
        // Ahora se muestra el resultado
        for(int i=0; i < contadorPares; i++){
            System.out.printf("%d ", pares[i]);
        }
        System.out.println();
    }
    
    // Metodo main para pruebas
    public static void main(String[] args) {
        long numeroValores, suma;
        int minimo, maximo;
        double media, sumaCuadrados, producto;

        // Se crea el objeto de la clase, con 10000 valores
        IntStreamOperations objeto = new IntStreamOperations(10000);
        
        // Se muestran los valores con la aproximacion funciona
        objeto.mostrarValoresFuncional();
                        
        // Se cuentan los valores
        numeroValores=objeto.contarValoresFuncional();
        
        // Se muestra el resultado
        System.out.printf("%nNumero de elementos: %d%n", numeroValores);
        
        // Se obtiene el valor minimo con flujos        
        minimo=objeto.obtenerMinimoFuncional();

        // Se muestra el resultado
        System.out.printf("%nValor minimo: %d%n", minimo);
        
        // Se obtiene el valor maximo con flujos
        maximo=objeto.obtenerMaximoFuncional();

        // Se muestra el resultado
        System.out.printf("%nValor maximo: %d%n", maximo);
                
        // Se obtiene la suma con flujos
        suma=objeto.obtenerSumaFuncional();

        // Se muestra el resultado
        System.out.printf("%nValor suma: %d%n", suma);
        
        // Se realiza la suma con reduce y expresiones lambda
        suma=objeto.obtenerSumaReduceExpresionesLambda();
        
        // Se muestra el resultado obtenido
        System.out.printf("%nValor suma: %d%n", suma);         
        
        // Se obtiene la media con flujos
        media=objeto.obtenerMediaFuncional();
        
        // Se muestra el resultado obtenido
        System.out.printf("%nValor media: %f%n", media);
                
        // Se obtiene la suma de los valores al cuadrado con reduce
        // y expresiones lambda
        sumaCuadrados=objeto.obtenerSumaCuadradosFuncional();
        
        // Se muestra el resultado obtenido
        System.out.printf("%nValor suma cuadrados: %f%n", sumaCuadrados);
        
        // Se obtiene el producto de los valores con reduce
        // y expresiones lambda
        producto=objeto.obtenerProductoFuncional();

        // Se muestra eel resultado obtenido
        System.out.printf("%nValor producto: %f%n", producto);
        
        // Se realiza la ordenacion con los pares usando filtrado,
        // sorted y forEach
        objeto.ordenarParesFuncional();  

        // Se realiza la ordenacion con los pares usando filtrado, mapeo, 
        // sorted y forEach
        objeto.ordenarParesMultiplicadosFuncional(10);
        
        // Ejemplos de otras operaciones
        // Suma de los valores entre 1 y 10 (no incluido). Ahora no
        // se trabaja con el conjunto de valores original
        System.out.printf("%nSum of integers from 1 to 9: %d%n",
                IntStream.range(1, 10).sum());

        // Suma de los valores de 1 a 10 (incluido). Como antes,
        // no se trabaja con los valores del array, sino que se
        // construye un rango para valores para la operacion
        System.out.printf("Sum of integers from 1 to 10: %d%n",
                IntStream.rangeClosed(1, 10).sum());
    }
}
