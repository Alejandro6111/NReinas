package controller;

import model.SolucionadorReinas;
import view.VistaConsola;
import java.util.List;

/**
 * Clase controladora que gestiona el flujo de la aplicación del problema de las N-Reinas.
 * Actúa como intermediario entre el modelo ({@link SolucionadorReinas}) y la vista ({@link VistaConsola}).
 *
 * @author Equipo 
 * @version 1.0 10/05/2025
 */
public class ControladorJuego {
    /**
     * Instancia del solucionador del problema de las N-Reinas (Modelo).
     */
    private SolucionadorReinas solucionador;
    /**
     * Instancia de la vista de consola para la interacción con el usuario (Vista).
     */
    private VistaConsola vista;
    /**
     * Tamaño predeterminado del tablero, fijado a 8 para el problema de las 8 Reinas.
     */
    private final int TAMANO_TABLERO_PREDET = 8;

    /**
     * Constructor para ControladorJuego.
     * Inicializa la vista de consola. El modelo (solucionador) se inicializa
     * cuando se inicia el juego.
     */
    public ControladorJuego() {
        this.vista = new VistaConsola();
    }

    /**
     * Inicia y gestiona el flujo principal de la aplicación para resolver el problema de las N-Reinas.
     * Solicita la entrada del usuario, invoca al modelo para encontrar soluciones y
     * utiliza la vista para mostrar los resultados.
     */
    public void mIniciarJuego() {
        vista.mMostrarMensaje("Bienvenido al solucionador del problema de las N-Reinas");

        int tamanoTablero = TAMANO_TABLERO_PREDET;
        this.solucionador = new SolucionadorReinas(tamanoTablero);

        vista.mMostrarMensaje("El tablero es de " + tamanoTablero + "x" + tamanoTablero + ".");

        int[] posicionInicial = null;
        // Bucle para asegurar que se obtiene una posición inicial válida del usuario.
        while (posicionInicial == null) {
            posicionInicial = vista.mSolicitarPosicionInicial(tamanoTablero);
            if (posicionInicial == null) {
                vista.mMostrarMensaje("Entrada inválida. Ingrese la posicion nuevamente."); // Mensaje mejorado
            }
        }
        int filaInicial = posicionInicial[0];
        int columnaInicial = posicionInicial[1];

        vista.mMostrarMensaje("\nBuscando soluciones con la primera reina en la fila " + filaInicial + " y columna " + columnaInicial + "...");

        // Indicar al modelo que resuelva el problema
        solucionador.mEncontrarSolucionesConReinaInicial(filaInicial, columnaInicial);

        // Obtener las soluciones del modelo
        List<int[][]> soluciones = solucionador.mGetSoluciones();

        // Mostrar las soluciones usando la vista
        if (soluciones.isEmpty()) {
            vista.mMostrarMensaje("No se encontraron soluciones para la configuración inicial proporcionada."); // Mensaje mejorado
        } else {
            vista.mMostrarSoluciones(soluciones);
            vista.mMostrarMensaje("Se encontraron " + solucionador.mGetNumeroDeSoluciones() + " soluciones en total.");
        }
        vista.mMostrarMensaje("\nFin del programa. Solucionador del problema N-Reinas, para este trabajo, 8."); // Mensaje de fin más claro
        vista.mCerrarScanner(); // Asegura que el scanner se cierre al final.
    }
}