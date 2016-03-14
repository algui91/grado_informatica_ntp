# Apuntes de Scala

- Las Var se pueden modificar luego, los Val no, son constantes.
- Se pueden hacer funciones anidadas, útil para mantener el código limpio.
- Puede ser muy conciso, simplificar las expresiones al máximo, el infiere los tipos si hace falta:

    ``` scala 
      def max (x: Int, y: Int) = if (x>y) x else y
    ```

- Los arrays son colecciones mutables, se puede cambiar su contenido aunque estén definidos como val, cambia el contenido, podemos agregarle contenido ect, pero el puntero al objeto no se puede cambiar.
- Todo es basado en orientación a objetos, 1 + 2 es en realidad (1).+(2)

## Listas

- Es inmmutable.
- Todos los elementos tienen que ser del mismo tipo

  ```scala
    val lista1 = List(1,2,3)
    val lista2 = List(4,5,6)
    val lista12 = lista1 ::: lista2

    // Para agregar en la cabeza
    val lista3 = 1::lista2
    
    // Contar
    lista1.count(s => s > 2)

    // Las tuplas pueden contener elementos de cualquier tipo
    val tupla2 = (1, "lunes", "L")
    println(tupla2._1)
  ``` 
