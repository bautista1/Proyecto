/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyecto.compilador.util;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Luis Fernando Leiva
 */
public class NodoAST {
    
    
    
    private int etiqueta;
    
    private String tipo;
    
    private String datos1;
    private String datos2;
    private String datos3;
    
    private int linea;
    
    private List<NodoAST> hijos;

    public static final int IF = 1;
    public static final int FOR = 2;
    public static final int WHILE = 3;
    public static final int DOWHILE = 4;
    public static final int SWITCH = 5;
    public static final int VALOR = 6;
    public static final int ID = 7;
    public static final int THIS = 8;
    public static final int VALORES = 9;
    public static final int CASTEO = 10;
    public static final int OPTERNARIO = 11;
    public static final int VALORVEC = 12;
    public static final int AMPERSAND = 13;
    public static final int PUNTO = 14;
    public static final int INCREMENTO = 15;
    public static final int DECREMENTO = 16;
    public static final int NEW = 17;
    public static final int NEWARRAY = 18;
    public static final int ASIGNACION = 19;
    public static final int MAS = 20;
    public static final int MENOS = 21;
    public static final int MULTI = 22;
    public static final int DIV = 23;
    public static final int EXPO = 24;
    public static final int OR = 25;
    public static final int AND = 26;
    public static final int COMPARACION = 27;
    public static final int NOT = 28;
    public static final int DECLARACION = 29;
    public static final int LLAMARFUNCION = 31;
    public static final int EXPLLAMARFUNCION = 32;
    public static final int RETURN = 33;
    public static final int CONTINUE = 34;
    public static final int BREAK = 35;
    public static final int FUNIMPRIMIR = 36;
    public static final int FUNLIENZO = 37;
    public static final int FUNPOLIGONO = 38;
    public static final int FUNOVALO = 39;
    public static final int FUNRECTANGULO = 40;
    public static final int FUNARCO = 41;
    public static final int FUNTEXTO = 42;
    public static final int FUNLINEA = 43;
    public static final int DECATRIBUTO = 44;
    
    
    public static final int INSTRUCCIONES = 100;
    public static final int VACIO = 101;

    public static final int METODO = 200;

    
    public NodoAST(int etiqueta, int linea) {
        this.etiqueta = etiqueta;
        this.linea = linea;
        this.hijos = new LinkedList<>();
    }

    public NodoAST(int etiqueta, String tipo, int linea) {
        this.etiqueta = etiqueta;
        this.tipo = tipo;
        this.linea = linea;
        this.hijos = new LinkedList<>();
    }

    public NodoAST(int etiqueta, String tipo, String datos1, String datos2, String datos3, List<NodoAST> hijos, int linea) {
        this.etiqueta = etiqueta;
        this.tipo = tipo;
        this.datos1 = datos1;
        this.datos2 = datos2;
        this.datos3 = datos3;
        this.linea = linea;
        this.hijos = hijos;
    }

    public NodoAST(int etiqueta, String tipo, String datos1, String datos2, String datos3, int linea) {
        this.etiqueta = etiqueta;
        this.tipo = tipo;
        this.datos1 = datos1;
        this.datos2 = datos2;
        this.datos3 = datos3;
        this.linea = linea;
        this.hijos = new LinkedList<>();
    }

    public NodoAST(int etiqueta, String tipo, String datos1, String datos2, int linea) {
        this.etiqueta = etiqueta;
        this.tipo = tipo;
        this.datos1 = datos1;
        this.datos2 = datos2;
        this.linea = linea;
        this.hijos = new LinkedList<>();
    }

    public NodoAST(int etiqueta, String tipo, String datos1, int linea) {
        this.etiqueta = etiqueta;
        this.tipo = tipo;
        this.datos1 = datos1;
        this.linea = linea;
        this.hijos = new LinkedList<>();
    }

    public int getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(int etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    public List<NodoAST> getHijos() {
        return hijos;
    }

    public void setHijos(List<NodoAST> hijos) {
        this.hijos = hijos;
    }

    public String getTipoVal() {
        return tipo;
    }

    public void setTipoVal(String tipo) {
        this.tipo = tipo;
    }

    public String getDatos1() {
        return datos1;
    }

    public void setDatos1(String datos1) {
        this.datos1 = datos1;
    }

    public String getDatos2() {
        return datos2;
    }

    public void setDatos2(String datos2) {
        this.datos2 = datos2;
    }

    public String getDatos3() {
        return datos3;
    }

    public void setDatos3(String datos3) {
        this.datos3 = datos3;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }
    
    
    
    
}
