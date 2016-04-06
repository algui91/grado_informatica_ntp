import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;

/**
 * Práctica 1 NTP
 */
public class ListadoTest {
    private static Listado listado;

    /**
     * Codigo a ejecutar antes de realizar las llamadas a los métodos
     * de la clase; incluso antes de la propia instanciación de la
     * clase. Por eso el método debe ser estatico
     */
    @BeforeClass
    public static void inicializacion() {
        System.out.println("Metodo inicializacion conjunto pruebas");
        // Se genera el listado de alumnos
        try {
            listado = new Listado("./data/datos.txt");
        }catch(IOException e){
            System.out.println("Error en lectura de archivo de datos");
        }

        // Una vez disponibles los alumnos se leen las listas
        // de asignaciones de alumnos a cada grupo de las diferentes
        // asignaturas consideradas
        try {
            listado.cargarArchivoAsignacion("./data/asignacionES.txt");
            listado.cargarArchivoAsignacion("./data/asignacionLMD.txt");
            listado.cargarArchivoAsignacion("./data/asignacionMP.txt");
            listado.cargarArchivoAsignacion("./data/asignacionTOC.txt");
        } catch (IOException e) {
            System.out.println("Error en lectura de archivos de asignacion");
        }
        System.out.println();
    }

    /**
     * Test para comprobar que se ha leido de forma correcta la
     * informacion de los alumnos (al menos que el listado contiene
     * datos de 100 alumnos)
     * @throws Exception
     */
    @Test
    public void testConstruccionListado() throws Exception{
        assert(listado.obtenerLongitud() == 100);
    }

    /**
     * Test del procedimiento de asignacion de grupos procesando
     * los archivos de asignacion. Tambien implica la prueba de
     * busqueda de alumnos sin grupo asignado en alguna asignatura
     * @throws Exception
     */
    @Test
    public void testCargarArchivosAsignacion() throws Exception {
        // Se obtienen los alumnos no asignados a cada asignatura
        // y se comprueba su valor
        assert(listado.buscarAlumnosNoAsignados(Asignatura.LMD.toString()).size() == 2);
        assert(listado.buscarAlumnosNoAsignados(Asignatura.ES.toString()).size() == 2);
        assert(listado.buscarAlumnosNoAsignados(Asignatura.TOC.toString()).size() == 2);
        assert(listado.buscarAlumnosNoAsignados(Asignatura.MP.toString()).size() == 2);
    }

    /**
     * Prueba para el procedimiento de conteo de grupos para cada una
     * de las asignaturas
     */
    @Test
    public void testObtenerContadoresGruposDeAsignatura(){
        // Se obtienen los contadores para la asignatura ES
        Map<Integer, Long> contadoresES=listado.obtenerContadoresGruposDeAsignatura(
                Asignatura.ES);
        // Se comprueba que los valores son -1, 2 | 1, 37 | 2, 28 | 3, 33
        Long contadoresReferencia[]={2L,37L,28L,33L};
        Long contadoresCalculados[]=new Long[4];
        assertArrayEquals(contadoresES.values().toArray(contadoresCalculados),
                          contadoresReferencia);
    }

    /**
     * Prueba del procedimiento general de obtencion de contadores
     * para todas las asignaturas
     * @throws Exception
     */
    @Test
    public void testObtenerContadoresGrupos() throws Exception {
        // Se obtienen los contadores para todos los grupos
        Map<Asignatura, Map<Integer, Long>> contadores=
                                    listado.obtenerContadoresGrupos();

        // Se comprueban los valores obtenenidos con los valores por referencia
        Long contadoresReferenciaLMD[]={2L,40L,29L,29L};
        Long contadoresReferenciaES[]={2L,37L,28L,33L};
        Long contadoresReferenciaMP[]={2L,31L,30L,37L};
        Long contadoresReferenciaTOC[]={2L,33L,33L,32L};

        // Se comprueban los resultado de obtenerContadoresGrupo con los
        // de referencia
        Long contadoresCalculados[]=new Long[4];
        Map<Integer, Long> integerLongMap = contadores.get(Asignatura.ES);

        assertArrayEquals(contadores.get(Asignatura.LMD).values().
                toArray(contadoresCalculados),contadoresReferenciaLMD);
        assertArrayEquals(contadores.get(Asignatura.ES).values().
                toArray(contadoresCalculados),contadoresReferenciaES);
        assertArrayEquals(contadores.get(Asignatura.MP).values().
                toArray(contadoresCalculados),contadoresReferenciaMP);
        assertArrayEquals(contadores.get(Asignatura.TOC).values().
                toArray(contadoresCalculados),contadoresReferenciaTOC);
    }
}