package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementa el algoritmo de backtracking para resolver el problema de las N-Reinas.
 * Encuentra todas las posibles configuraciones de N reinas en un tablero de N x N
 * de tal manera que ninguna reina amenace a otra, dada una posición inicial para la primera reina.
 *
 * @author Equipo 
 * @version 1.0 10/05/2025
 */
public class SolucionadorReinas {
    /**
     * Instancia del tablero actual sobre el que se está trabajando para encontrar soluciones.
     */
    private Tablero tableroActual;
    /**
     * Lista que almacena todas las configuraciones de tablero que representan soluciones válidas.
     * Cada solución es una matriz {@code int[][]}.
     */
    private List<int[][]> solucionesEncontradas;
    /**
     * Tamaño del tablero (N).
     */
    private int tamanoTablero;
    /**
     * Fila (0-indexada) de la primera reina colocada por el usuario.
     * Se considera fija durante la búsqueda de soluciones. Vale -1 si no hay reina inicial fija.
     */
    private int filaInicialFija = -1;
    /**
     * Columna (0-indexada) de la primera reina colocada por el usuario.
     * Se considera fija durante la búsqueda de soluciones. Vale -1 si no hay reina inicial fija.
     */
    private int columnaInicialFija = -1;

    /**
     * Constructor para la clase SolucionadorReinas.
     *
     * @param tamano El tamaño N del tablero para el cual se resolverá el problema.
     */
    public SolucionadorReinas(int tamano) {
        this.tamanoTablero = tamano;
        this.solucionesEncontradas = new ArrayList<>();
        // El tablero se crea cuando se invoca mEncontrarSolucionesConReinaInicial.
    }

    /**
     * Método principal para iniciar la búsqueda de todas las soluciones válidas
     * a partir de una posición inicial para la primera reina especificada por el usuario.
     *
     * @param filaReinaInicial    La fila (0-indexada) donde el usuario coloca la primera reina.
     * @param columnaReinaInicial La columna (0-indexada) donde el usuario coloca la primera reina.
     */
    public void mEncontrarSolucionesConReinaInicial(int filaReinaInicial, int columnaReinaInicial) {
        this.solucionesEncontradas.clear(); // Limpiar soluciones de ejecuciones previas
        this.tableroActual = new Tablero(this.tamanoTablero);

        // Validar que la posición inicial sea válida
        if (!this.tableroActual.mEsPosicionValida(filaReinaInicial, columnaReinaInicial)) {
            System.err.println("Error: la posicion inicial de la reina esta fuera del tablero.");
            // Considerar lanzar una excepción aquí en lugar de solo imprimir en System.err
            return;
        }
        // Colocar la primera reina especificada por el usuario
        this.tableroActual.mColocarReina(filaReinaInicial, columnaReinaInicial);

        // Guardar la posición fija para no intentar mover esta reina
        this.filaInicialFija = filaReinaInicial;
        this.columnaInicialFija = columnaReinaInicial;

        // Iniciar el proceso recursivo para las reinas restantes.
        // Se empieza a intentar colocar reinas desde la columna 0.
        mResolverRecursivo(0);

        // Resetear las posiciones fijas para futuras llamadas.
        this.filaInicialFija = -1;
        this.columnaInicialFija = -1;

        if (this.solucionesEncontradas.isEmpty()) {
            // Este mensaje podría ir a la vista en un diseño MVC más estricto.
            System.out.println("No se encontraron soluciones para la posicion inicial dada.");
        }
    }

    /**
     * Implementa el algoritmo de backtracking de forma recursiva para colocar las reinas.
     * Intenta colocar una reina en cada columna, una por una.
     *
     * @param columna La columna actual (0-indexada) en la que se intenta colocar una reina.
     * @return {@code true} si se encontró al menos una solución a partir de esta rama de
     * la recursión, {@code false} en caso contrario. Este valor de retorno ayuda
     * a saber si una rama particular fue fructífera, pero el algoritmo continúa
     * buscando todas las soluciones independientemente.
     */
    private boolean mResolverRecursivo(int columna) {
        // Caso base: Si todas las reinas están colocadas (hemos procesado todas las columnas)
        if (columna == this.tamanoTablero) {
            // Se encontró una configuración válida completa.
            this.solucionesEncontradas.add(this.tableroActual.mGetTableroState());
            return true; // Indica que esta rama llevó a una solución.
        }

        boolean seEncontroSolucionEnEstaRama = false;

        // Si la columna actual es donde el usuario fijó la primera reina,
        // no intentamos colocar otra reina aquí; avanzamos a la siguiente columna.
        if (columna == this.columnaInicialFija) {
            if (mResolverRecursivo(columna + 1)) {
                seEncontroSolucionEnEstaRama = true;
                // No retornamos inmediatamente para buscar todas las soluciones.
            }
        } else {
            // Intentar colocar una reina en cada fila de la columna actual
            for (int fila = 0; fila < this.tamanoTablero; fila++) {
                // Verificar si es seguro colocar la reina en tableroActual[fila][columna]
                if (this.tableroActual.mEsSeguro(fila, columna)) {
                    // Colocar la reina
                    this.tableroActual.mColocarReina(fila, columna);

                    // Llamada recursiva para la siguiente columna
                    if (mResolverRecursivo(columna + 1)) {
                        seEncontroSolucionEnEstaRama = true;
                        // No retornamos inmediatamente para buscar todas las soluciones.
                    }

                    // Backtrack: Quitar la reina para probar otras posibilidades en esta columna
                    // y para permitir que llamadas recursivas anteriores prueben otras ramas.
                    this.tableroActual.mQuitarReina(fila, columna);
                }
            }
        }
        // Este retorno es más para el flujo interno del backtracking,
        // no detiene la búsqueda de todas las soluciones.
        return seEncontroSolucionEnEstaRama;
    }


    /**
     * Devuelve la lista de todas las soluciones encontradas.
     * Cada solución es una representación del estado del tablero (matriz {@code int[][]}).
     *
     * @return Una {@code List<int[][]>} con todas las soluciones válidas.
     * La lista estará vacía si no se encontraron soluciones.
     */
    public List<int[][]> mGetSoluciones() {
        return this.solucionesEncontradas;
    }

    /**
     * Devuelve el número total de soluciones encontradas.
     *
     * @return El conteo de soluciones.
     */
    public int mGetNumeroDeSoluciones() {
        return this.solucionesEncontradas.size();
    }
}