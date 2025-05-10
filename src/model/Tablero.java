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
                this.tablero[i][j] = 0; // 0 Vaio 1 Reina
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

    
    // Metodo para quitar una reina 
    public void mQuitarReina(int fila, int columna){
        if (mEsPosicionValida){
            this.tablero[fila][columna] = 0; // Marcamos como vacia 
        }
    }

    // Metodo para verificar si una posicion es valida dentro de los limites del tablero 
    public boolean mEsPosicionValida(int fila, int columna){
        return fila >= 0 && fila < this.tamano && columna >= 0 && columna < this.tamano;
    }

    // Metodo para verificar si es seguro colocar una reina (Fila, Columna)
    public boolean mEsSeguro(int fila, int columna){
        // Si ya hay Reina en una casilla, no es seguro
        if (!mEsPosicionValida(fila, columna) || this.tablero[fila][columna] == 1){
            return false;
        }

        // Verificar fila hacia la izquiera
    }




    
}