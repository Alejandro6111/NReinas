package controller;

import model.SolucionadorReinas;
import view.VistaConsola;
import java.util.List;

public class ControladorJuego {
    private SolucionadorReinas solucionador;
    private VistaConsola vista;
    private final int TAMANO_TABLERO_PREDET = 8; // Problema de las ocho reinas
    
    public ControladorJuego(){
        // Se crea vista
        this.vista = new VistaConsola();
    }

    public void mIniciarJuego(){
        vista.mMostrarMensaje("Bienvenido al solucionador del problema de las N-Reinas");

        int tamanoTablero = TAMANO_TABLERO_PREDET;
        this.solucionador = new SolucionadorReinas(tamanoTablero);

        vista.mMostrarMensaje("El tablero es de " + tamanoTablero + "x" + tamanoTablero + ".");

        int[] posicionInicial = null;
        // Bucle para asegurar poscion valida
        while (posicionInicial == null) {
            posicionInicial = vista.mSolicitarPosicionInicial(tamanoTablero);
            if (posicionInicial == null){
                vista.mMostrarMensaje("Ingrese la posicion nuevamente.");
            }
        }
        int filaInicial = posicionInicial[0];
        int columnaInicial = posicionInicial[1];

        vista.mMostrarMensaje("\nBuscando soluciones con la primera reina en la fila" + filaInicial + " y columna " + columnaInicial + "...");

        // Indicar al modelo que resuelva el problema
        solucionador.mEncontrarSolucionesConReinaInicial(filaInicial, columnaInicial);

        // Obtener las soluciones del modelo
        List<int[][]> soluciones = solucionador.mGetSoluciones();

        // Mostrar las soluciones usando la vista
        if (soluciones.isEmpty()){
            vista.mMostrarMensaje("No se encontraron soluciones");
        } else {
            vista.mMostrarSoluciones(soluciones);
            vista.mMostrarMensaje("Se encontraron " + solucionador.mGetNumeroDeSoluciones() + " soluciones en total.");

        }
        vista.mMostrarMensaje("\nSolucionador del problema N-Reinas, para este trabajo, 8.");
        vista.mCerrarScanner();
    }
}
