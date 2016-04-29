def generarTablaNumero(fila: Int) =
  for (columna <- 1 to 10) yield {
    val producto = (fila * columna).toString
    val relleno = " " * (4 - producto.length)
    relleno + producto // De vuelve la concatenación
  }

print(generarTablaNumero(5))