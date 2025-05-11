package view;

import java.util.List;
import java.util.Scanner;

/**
 * Maneja la interacción con el usuario a través de la consola de texto.
 * Es responsable de mostrar el tablero, las soluciones y de solicitar
 * la entrada del usuario, como la posición inicial de la reina.
 *
 * @author Equipo 
 * @version 1.0 10/05/2025
 */
public class VistaConsola {
    /**
     * Objeto Scanner utilizado para leer la entrada del usuario desde la consola.
     */
    private Scanner scanner;

    /**
     * Constructor de VistaConsola.
     * Inicializa el objeto Scanner para leer la entrada del sistema.
     */
    public VistaConsola() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Muestra un único estado del tablero en la consola de forma alineada.
     * 'R' representa una reina y '.' una casilla vacía.
     *
     * @param tableroState Matriz bidimensional {@code int[][]} que representa el estado del tablero.
     * Se espera que 1 signifique Reina y 0 Vacío.
     */
    public void mMostrarTablero(int[][] tableroState) {
        if (tableroState == null) {
            System.out.println("El Estado del tablero es nulo"); // Corregido: Estado con mayúscula
            return;
        }
        int tamano = tableroState.length;
        System.out.println("Tablero (" + tamano + "x" + tamano + "):");

        // Encabezado columnas
        System.out.print("    "); // 4 espacios para alinear con " F |"
        for (int j = 0; j < tamano; j++) {
            System.out.print(String.format("%-3d", j)); // Cada encabezado de columna ocupa 3 espacios
        }
        System.out.println();

        // Linea separadora del tablero
        System.out.print("   +"); // 3 espacios + "+"
        for (int j = 0; j < tamano; j++) {
            System.out.print("---"); // Tres guiones por columna
        }
        System.out.println("+"); // Cierre de la línea

        // Imprimir cada fila del tablero
        for (int i = 0; i < tamano; i++) {
            // Formato para el número de fila: ej. "  0|", " 10|". Ocupa 4 caracteres.
            System.out.print(String.format("%-3s|", i)); // Usar %-3s por si tamano > 9

            // Imprimir el contenido de cada celda en la fila
            for (int j = 0; j < tamano; j++) {
                if (tableroState[i][j] == 1) {
                    System.out.print(" R "); // " R " ocupa 3 espacios
                } else {
                    System.out.print(" . "); // " . " ocupa 3 espacios
                }
            }
            System.out.println("|"); // Barra vertical al final de la fila
        }

        // Linea separadora inferior del tablero
        System.out.print("   +");
        for (int j = 0; j < tamano; j++) {
            System.out.print("---");
        }
        System.out.println("+");
    }


    /**
     * Muestra todas las soluciones encontradas en la consola.
     * Itera sobre la lista de soluciones y llama a {@link #mMostrarTablero(int[][])} para cada una.
     *
     * @param soluciones Lista de tableros (matrices {@code int[][]}) que representan las soluciones encontradas.
     */
    public void mMostrarSoluciones(List<int[][]> soluciones) {
        if (soluciones == null || soluciones.isEmpty()) {
            System.out.println("No se encontraron soluciones o la lista esta vacia"); // está con minúscula
            return;
        }
        // Corregido: espacio antes de "soluciones"
        System.out.println("\n--- Se encontraron " + soluciones.size() + " soluciones: ---");
        for (int i = 0; i < soluciones.size(); i++) {
            System.out.println("Solucion #" + (i + 1) + ":");
            mMostrarTablero(soluciones.get(i));
        }
    }

    /**
     * Solicita al usuario la posición inicial (fila y columna) para la primera reina.
     * Valida que la entrada sean números y que estén dentro de los límites del tablero.
     *
     * @param tamanoTablero El tamaño N del tablero, usado para la validación y los mensajes al usuario.
     * @return Un array de enteros de tamaño 2, donde {@code array[0]} es la fila y {@code array[1]} es la columna.
     * Retorna {@code null} si la entrada es inválida o está fuera de los límites.
     */
    public int[] mSolicitarPosicionInicial(int tamanoTablero) {
        System.out.println("\nIngrese la posicion para la primera Reina (tablero " + tamanoTablero + "x" + tamanoTablero + ")");
        System.out.println("Las filas y columnas van de 0 a " + (tamanoTablero - 1) + ".");
        int fila = -1, columna = -1;

        try {
            // Corregido: Solicitar fila con print en lugar de println para entrada en misma línea
            System.out.print("Ingrese la fila (0-" + (tamanoTablero - 1) + "): ");
            String lineaFila = scanner.nextLine();
            fila = Integer.parseInt(lineaFila);

            System.out.print("Ingrese la columna (0-" + (tamanoTablero - 1) + "): ");
            String lineaColumna = scanner.nextLine();
            columna = Integer.parseInt(lineaColumna);

            if (fila < 0 || fila >= tamanoTablero || columna < 0 || columna >= tamanoTablero) {
                System.err.println("Error: Posicion fuera de los limites del tablero, intente nuevamente");
                return null;
            }
            return new int[]{fila, columna};
        } catch (NumberFormatException e) {
            System.err.println("Entrada invalida. Por favor, ingrese solo números."); // Mensaje de error más claro
            return null;
        }
    }

    /**
     * Muestra un mensaje genérico al usuario en la consola.
     *
     * @param mensaje El mensaje {@code String} a mostrar.
     */
    public void mMostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    /**
     * Cierra el objeto Scanner para liberar los recursos del sistema.
     * Debería llamarse cuando la aplicación termina y ya no se necesita leer más entradas.
     */
    public void mCerrarScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}