package view;

import java.util.List;
import java.util.Scanner;
public class VistaConsola {
    private Scanner scanner;

    public VistaConsola(){
        this.scanner = new Scanner(System.in);
    }
    /**
     * Muestra un inico estado del tablero en la consola
     * "R" resepresenta una Reina y " ." una casilla vacia
     * @param tableroState Matriz bidimensional que representa el estdo del pablero
     * Es espera que 1 signifique Reina y 0 Vacio
     */
    public void mMostrarTablero(int[][] tableroState){
        if (tableroState == null){
            System.out.println("El Estado del tablero es nulo");
            return;
        }
        int tamano = tableroState.length;
        System.out.println("Tablero (" + tamano + "x" + tamano + "):");

        // Encabezado columnas
        System.out.print("    ");
        for (int j = 0; j < tamano; j++){
            System.out.print(String.format("%-3d", j));
        }
        System.out.println();

        // Linea separadora del tablero
        System.out.print("   +");
        for (int j = 0; j < tamano; j++){
            System.out.print("---");
        }
        System.out.println("+");

        // Imprimir cada fila del tablero
        for (int i = 0; i < tamano; i++){
            System.out.print(String.format("%3d|", i));

            // Imprimir el contenido de cada celda en la fila
            for (int j = 0; j < tamano; j++){
                if (tableroState[i][j] == 1){
                    System.out.print(" R ");
                } else {
                    System.out.print(" . ");
                }
            }
            System.out.println("|");
        }

        // Linea separadora inferior del tablero
        System.out.print("   +");
        for (int j = 0; j < tamano; j++){
            System.out.print("---");
        }
        System.out.println("+");
    }


    /**
     * Muestra todas las soluciones encontradas
     * @param soluciones Lista de tableros que representa las soluciones encontradas
     */
    public void mMostrarSoluciones(List<int[][]> soluciones){
        if (soluciones == null || soluciones.isEmpty()){
            System.out.println("No se encontraron soluciones o la lista esta vacia");
            return;
        }
        System.out.println("\n--- Se encontraron " + soluciones.size() + "soluciones: ---");
        for (int i = 0; i < soluciones.size(); i++){
            System.out.println("Solucion #" + (i + 1) + ":");
            mMostrarTablero(soluciones.get(i));
        }
    }

    /**
     * Solicita al usuario la posicion inicial de la primera reina
     * Valida que la entrada sean numeros y esten dentro del rango
     * @param tamanoTablero El tamaño del tablero para ayudar a validar la entrada
     * @return Un array de tamaño dos
     */
    public int[] mSolicitarPosicionInicial(int tamanoTablero){
        System.out.println("\nIngrese la posicion para la primera Reina (tablero " + tamanoTablero + "x" + tamanoTablero + ")");
        System.out.println("Las filas y columnas van de 0 a " + (tamanoTablero - 1) + ".");
        int fila = -1, columna = -1;

        try {
            System.out.println("Ingrese la fila (0-" + (tamanoTablero -1) + "): ");
            String lineaFila = scanner.nextLine();
            fila = Integer.parseInt(lineaFila);

            System.out.println("Ingrese la columna (0-" + (tamanoTablero - 1) + "): ");
            String lineaColumna = scanner.nextLine();
            columna = Integer.parseInt(lineaColumna);

            if (fila < 0 || fila >= tamanoTablero || columna < 0 || columna >= tamanoTablero){
                System.err.println("Error: Posicion fuera de los limites del tablero, intente nuevamente");
                return null;
            }
            return new int[]{fila, columna};
        } catch (NumberFormatException e){
            System.err.println("Entrada invalida");
            return null;
        }
    }

    /**
     * Muestra un mensaje generico al usuario
     * @param mensaje El mensaje a mostrar
     */
    public void mMostrarMensaje(String mensaje){
        System.out.println(mensaje);
    }

    public void mCerrarScanner(){
        if (scanner != null){
            scanner.close();
        }
    }





}
