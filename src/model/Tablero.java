package model; 

public class Tablero {
    private int [][] tablero;
    private int tamano;

    // Constructor 
    public Tablero(int tamano){
        this.tamano = tamano;
        this.tablero = new int[tamano][tamano];
        // Inicializamos tablero
        for (int i = 0; i < tamano; i++){
            for (int j = 0; j < tamano; j++){
                this.tablero[i][j];
            }
        }
    } 
    
    // Metodo para colocar una reina en una posicion 
    public boolean mColocarReina (int fila, int columna){
        if (mEsPosicionValida(fila, columna)) {
            this.tablero[fila][columna] = 1; // Marcamos que hay reina
            return true; 
        }
        return false;
    }    





    
}