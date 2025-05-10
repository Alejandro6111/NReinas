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

        // Valudar que la posicion inicial sea valida
        if (!this.tableroActual.mEsPosicionValida(filaReinaInicial, columnaReinaInicial)){
            System.err.println("Error: la posicion inicial de la reina esta fuera del tablero.");
            return;
        }
        // Colocar la primera reina especificada por el usuario
        this.tableroActual.mColocarReina(filaReinaInicial, columnaReinaInicial);

        // Guardar la posicion fija para no intentar mover esta reina
        this.filaInicialFija = filaReinaInicial;
        this.columnaInicialFija = columnaReinaInicial;

        /*Iniciar el proceso recursuvi para las reinas restantes
         * Empezamos a intentar colocar las reinas desde la columna 0
         * Se salta la columna donde ya esta la reina inicial
         */
        mResolverRecursivo(0);

        // Resetar posiciones fijas en caso de volver a llamar al metodo
        this.filaInicialFija = -1;
        this.columnaInicialFija = -1;

        if (this.solucionesEncontradas.isEmpty()){
            System.out.println("No se encontraron soluciones para la poscion inicial dada.");
        }
    }

    /**
     * Metodo recursivo (BackTracking) para intentar colocar las reinas
     * Se intenta colocar una reina en cada columna
     * @param columna La columna actual en la que se intenta ubicar una reina
     */
    private boolean mResolverRecursivo(int columna){
        // Caso uno: Si todas las reinas estan colocadas
        if (columna == this.tamanoTablero){
            // Se encuentra solucion valida
            this.solucionesEncontradas.add(this.tableroActual.mGetTableroState());
            //this.tableroActual.mImprimirTableroConsola(); para debug rapido
            return true; // Devuelve true para indicar que encontro una solucion
        }

        boolean seEncontroSolucionEnEstaRama = false;

        // Siguiente columna 
        if (columna == this.columnaInicialFija){
            if (mResolverRecursivo(columna + 1)){
                seEncontroSolucionEnEstaRama = true;
                // Aun no se retorna, buscamos todas las soluciones
            }
        } else {
            // Colocar una reina en cada fila de la columna actual
            for (int fila = 0; fila < this.tamanoTablero; fila++){
                // Verifica si es seguro colocar la reina en el tablero
                if (this.tableroActual.mEsSeguro(fila, columna)){
                    // Coloca la reina
                    this.tableroActual.mColocarReina(fila, columna);

                    // Llama recursiva para siguiente columna
                    if (mResolverRecursivo(columna + 1)){
                        seEncontroSolucionEnEstaRama = true;
                        // Aun no se retorna, buscamos todas las soluciones
                    }

                    // BackTrack: Quitar la reina si no lleva a una solucion
                    this.tableroActual.mQuitarReina(fila, columna);
                }
            }
        }
        return seEncontroSolucionEnEstaRama; // Indica si se encontraron soluciobnes
    }


    /**
     * Devuelve la lista de soluciones encontradas
     * Cada solucion es una representacion del estado del tablero
     * @return Lista de soluciones
     */
    public List<int[][]> mGetSoluciones(){
        return this.solucionesEncontradas;
    }

    public int mGetNumeroDeSoluciones(){
        return this.solucionesEncontradas.size();
    }



    
}
