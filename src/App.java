import controller.ControladorJuego;

public class App {
    public static void main(String[] args) throws Exception {
        // Instancia controlador
        ControladorJuego controlador = new ControladorJuego();

        // Iniciar atraves del controlador

        controlador.mIniciarJuego();
    }
}
