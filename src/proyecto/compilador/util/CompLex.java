/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyecto.compilador.util;

/**
 *
 * @author Luis Fernando Leiva
 */
public class CompLex {
    
    private String lexema;
    private int linea;
    private int columna;
    private int posicionAbsoluta;

    public CompLex(String lexema, int linea, int columna, int posicionAbsoluta) {
        this.lexema = lexema;
        this.linea = linea;
        this.columna = columna;
        this.posicionAbsoluta = posicionAbsoluta;
    }

    public CompLex(String lexema, int linea, int columna) {
        this.lexema = lexema;
        this.linea = linea;
        this.columna = columna;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public int getPosicionAbsoluta() {
        return posicionAbsoluta;
    }

    public void setPosicionAbsoluta(int posicionAbsoluta) {
        this.posicionAbsoluta = posicionAbsoluta;
    }
    
    
    
}
