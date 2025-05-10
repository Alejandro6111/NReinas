package model;

import java.util.ArrayList;
import java.util.List;

public class SolucionadorReinas {
    private Tablero tableroActual;
    private List<int[][]> solucionesEncontradas;
    private int tamanoTablero;
    private int filaInicialFija = -1; // Indica que no hay fila inicial fija
    private int columnaInicialFija = -1; // Indica que no hay columna inicial fija

    // Constructor
    public SolucionadorReinas(int tamano){
        this.tamanoTablero = tamano;
        this.solucionesEncontradas = new ArrayList<>();
        // El tablero se crea cuando se inicie la resolucion para una reina inicial
    }

    /**
     * Metodo principal para iniciar la busqueda solucones
     * El usuario ingresa la posicion de la primera Reina
     * @param filaReinaInicial Fila donde el usuiario comoloca la primera reina
     * @param columnaReinaInicial Columna donde el usuario coloca la segunda reina
     */
    public void mEncontrarSolucionesConReinaInicial(int filaReinaInicial, int columnaReinaInicial){
        this.solucionesEncontradas.clear(); // Limpiar soluciones previas
        this.tableroActual = new Tablero(this.tamanoTablero);
    }
    
}
