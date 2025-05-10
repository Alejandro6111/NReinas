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
        if (mEsPosicionValida(fila, columna)){
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
        for (int c = 0; c < columna; c++){
            if (this.tablero[fila][c] == 1){
                return false;
            }
        }
        // Verificar la fila hacia la derecha
        for (int c = columna + 1; c < this.tamano; c++){
            if (this.tablero[fila][c] == 1){
                return false;
            }
        }
        // Verificar columna entera 
        for (int r = 0; r < this.tamano; r++){
            if (r == fila) continue; // Para no recorrer la misma casilla
            if (this.tablero[r][columna] == 1){
                return false;
            }
        }
        // Verificar diagonal superrior izquierda   
        for (int r = fila - 1, c = columna -1; r >= 0 && c >= 0; r--, c--){
            if (this.tablero[r][c] == 1){
                return false;
            }
        }
        // Verificar diagonal inferior izquierfa
        for (int r = fila + 1, c = columna -1; r < this.tamano && c >= 0; r++, c--){
            if (this.tablero[r][c] == 1){
                return false;
            }
        }
        // Verificar diagonal superor derecha
        for (int r = fila - 1, c = columna + 1; r >= 0 && c < this.tamano; r--, c++){
            if (this.tablero[r][c] == 1){
                return false;
            }
        }
        // Verificar diagonal inferior derecha
        for (int r = fila + 1, c = columna + 1; r < this.tamano && c < this.tamano; r++, c++){
            if (this.tablero[r][c] == 1){
                return false;
            }
        }
        return true;
    }

    // Metodo para obtener una representacion del tablero 
    public int[][] mGetTableroState(){
        int[][] copiaTablero = new int[this.tamano][this.tamano];
        for (int i = 0; i < this.tamano; i++){
            System.arraycopy(this.tablero[i], 0 , copiaTablero [i], 0 , this.tamano);
        }
        return copiaTablero;
    }

    // Getter para el tamaño 
    public int mGeTamano(){
        return this.tamano;
    }

    // Metodo simple para impromir el tablero en la consola
    public void mImprimirTableroConsola(){
        System.out.println("Estado actual del tablero: ");
        for (int i = 0; i < tamano; i++){
            for (int j = 0; j < tamano; j++){
                System.out.println(this.tablero[i][j] + "");
            }
            System.out.println();
        }
        System.out.println("---------------------");
    }

    // Metodo para obtener el valor de una celda espeficica 
    public int mGetValorCelda(int fila, int columna){
        if (mEsPosicionValida(fila, columna)){
            return this.tablero[fila][columna];
        }
        return -1;
    }

    
}