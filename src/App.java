import controller.ControladorJuego;

/**
 * Clase principal de la aplicación N-Reinas.
 * Contiene el método {@code main} que sirve como punto de entrada para iniciar el programa.
 *
 * @author Equipo 
 * @version 1.0 10/05/2025
 */
public class App {
    /**
     * Método principal que se ejecuta al iniciar la aplicación.
     * Crea una instancia del {@link ControladorJuego} e inicia la lógica del juego/solucionador.
     *
     * @param args Argumentos de línea de comandos (no se utilizan en esta aplicación).
     * @throws Exception Si ocurre alguna excepción no controlada durante la ejecución.
     * (Considerar un manejo de excepciones más específico si es necesario).
     */
    public static void main(String[] args) throws Exception {
        // Instancia del controlador principal del juego.
        ControladorJuego controlador = new ControladorJuego();

        // Iniciar la ejecución del programa a través del controlador.
        controlador.mIniciarJuego();
    }
}