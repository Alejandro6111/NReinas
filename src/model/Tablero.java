package model;

/**
 * Representa el tablero de ajedrez y la lógica para manipularlo en el contexto
 * del problema de las N-Reinas. Gestiona la colocación y validación de
 * posiciones de las reinas.
 *
 * @author Equipo 
 * @version 1.0 10/05/2025
 */
public class Tablero {
    /**
     * Matriz bidimensional que representa el estado del tablero.
     * Un valor de 0 indica una casilla vacía, y 1 indica una reina.
     */
    private int[][] tablero;
    /**
     * Dimensión del tablero (N x N). Para un tablero de 8x8, tamano es 8.
     */
    private int tamano;

    /**
     * Constructor para la clase Tablero.
     * Inicializa un tablero vacío del tamaño especificado.
     *
     * @param tamano La dimensión N del tablero (N x N).
     */
    public Tablero(int tamano) {
        this.tamano = tamano;
        this.tablero = new int[tamano][tamano];
        // Inicializamos tablero con 0s (casillas vacías)
        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                this.tablero[i][j] = 0;
            }
        }
    }

    /**
     * Coloca una reina en la posición especificada del tablero.
     *
     * @param fila    La fila (0-indexada) donde se colocará la reina.
     * @param columna La columna (0-indexada) donde se colocará la reina.
     * @return {@code true} si la reina fue colocada exitosamente (posición válida),
     * {@code false} si la posición está fuera de los límites del tablero.
     */
    public boolean mColocarReina(int fila, int columna) {
        if (mEsPosicionValida(fila, columna)) {
            this.tablero[fila][columna] = 1; // Marcamos que hay reina
            return true;
        }
        return false;
    }

    /**
     * Quita una reina de la posición especificada del tablero, marcando la casilla como vacía.
     * Es útil para el algoritmo de backtracking.
     *
     * @param fila    La fila (0-indexada) de donde se quitará la reina.
     * @param columna La columna (0-indexada) de donde se quitará la reina.
     */
    public void mQuitarReina(int fila, int columna) {
        if (mEsPosicionValida(fila, columna)) {
            this.tablero[fila][columna] = 0; // Marcamos como vacía
        }
    }

    /**
     * Verifica si una posición dada (fila, columna) está dentro de los límites del tablero.
     *
     * @param fila    La fila (0-indexada) a verificar.
     * @param columna La columna (0-indexada) a verificar.
     * @return {@code true} si la posición es válida, {@code false} en caso contrario.
     */
    public boolean mEsPosicionValida(int fila, int columna) {
        return fila >= 0 && fila < this.tamano && columna >= 0 && columna < this.tamano;
    }

    /**
     * Verifica si es seguro colocar una reina en la posición especificada (fila, columna)
     * sin que sea amenazada por otras reinas ya presentes en el tablero.
     * Una posición es segura si no hay otra reina en la misma fila, columna o diagonales.
     *
     * @param fila    La fila (0-indexada) donde se intentaría colocar la reina.
     * @param columna La columna (0-indexada) donde se intentaría colocar la reina.
     * @return {@code true} si es seguro colocar la reina, {@code false} si la posición
     * está amenazada o fuera de los límites, o si ya hay una reina allí.
     */
    public boolean mEsSeguro(int fila, int columna) {
        // Si la posición no es válida o ya hay una reina, no es seguro.
        if (!mEsPosicionValida(fila, columna) || this.tablero[fila][columna] == 1) {
            return false;
        }

        // Verificar fila hacia la izquierda
        for (int c = 0; c < columna; c++) {
            if (this.tablero[fila][c] == 1) {
                return false;
            }
        }
        // Verificar la fila hacia la derecha
        for (int c = columna + 1; c < this.tamano; c++) {
            if (this.tablero[fila][c] == 1) {
                return false;
            }
        }
        // Verificar columna entera (excepto la propia casilla)
        for (int r = 0; r < this.tamano; r++) {
            if (r == fila) continue; // No chequear la misma casilla (ya cubierto arriba)
            if (this.tablero[r][columna] == 1) {
                return false;
            }
        }
        // Verificar diagonal superior izquierda
        for (int r = fila - 1, c = columna - 1; r >= 0 && c >= 0; r--, c--) {
            if (this.tablero[r][c] == 1) {
                return false;
            }
        }
        // Verificar diagonal inferior izquierda
        for (int r = fila + 1, c = columna - 1; r < this.tamano && c >= 0; r++, c--) {
            if (this.tablero[r][c] == 1) {
                return false;
            }
        }
        // Verificar diagonal superior derecha
        for (int r = fila - 1, c = columna + 1; r >= 0 && c < this.tamano; r--, c++) {
            if (this.tablero[r][c] == 1) {
                return false;
            }
        }
        // Verificar diagonal inferior derecha
        for (int r = fila + 1, c = columna + 1; r < this.tamano && c < this.tamano; r++, c++) {
            if (this.tablero[r][c] == 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Devuelve una copia del estado actual del tablero.
     * Esto previene modificaciones externas directas a la matriz interna del tablero.
     *
     * @return Una nueva matriz {@code int[][]} que es una copia del estado del tablero.
     */
    public int[][] mGetTableroState() {
        int[][] copiaTablero = new int[this.tamano][this.tamano];
        for (int i = 0; i < this.tamano; i++) {
            System.arraycopy(this.tablero[i], 0, copiaTablero[i], 0, this.tamano);
        }
        return copiaTablero;
    }

    /**
     * Obtiene el tamaño (dimensión N) del tablero.
     *
     * @return El tamaño del tablero.
     */
    public int mGetTamano() { // Corregido: mGeTamano a mGetTamano
        return this.tamano;
    }

    /**
     * Imprime el estado actual del tablero en la consola.
     * Útil principalmente para propósitos de depuración rápida.
     * Utiliza 0 para casillas vacías y 1 para reinas.
     */
    public void mImprimirTableroConsola() {
        System.out.println("Estado actual del tablero (debug): ");
        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                System.out.print(this.tablero[i][j] + " "); // Modificado para imprimir en una línea
            }
            System.out.println();
        }
        System.out.println("---------------------");
    }

    /**
     * Obtiene el valor de una celda específica del tablero.
     *
     * @param fila    La fila (0-indexada) de la celda.
     * @param columna La columna (0-indexada) de la celda.
     * @return El valor de la celda (0 si vacía, 1 si reina), o -1 si la posición es inválida.
     */
    public int mGetValorCelda(int fila, int columna) {
        if (mEsPosicionValida(fila, columna)) {
            return this.tablero[fila][columna];
        }
        return -1; // Indica error o posición inválida
    }
}