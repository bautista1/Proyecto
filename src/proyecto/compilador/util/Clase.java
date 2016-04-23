/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyecto.compilador.util;

import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author Luis Fernando Leiva
 */
public class Clase {
    
    private String nombre;
    private String padre;
    private HashMap<String,Simbolo> metodos;
    private TablaSimbolos tablasSimbolos;
    private LinkedList<NodoAST> valoresAtributos;

    public Clase(String nombre, String padre) {
        this.nombre = nombre;
        this.padre = padre;
        this.metodos = new HashMap<String, Simbolo>();
        this.tablasSimbolos = new TablaSimbolos();
        this.valoresAtributos = new LinkedList<NodoAST>();
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPadre() {
        return padre;
    }

    public void setPadre(String padre) {
        this.padre = padre;
    }

    public HashMap<String, Simbolo> getMetodos() {
        return metodos;
    }

    public void setMetodos(HashMap<String, Simbolo> metodos) {
        this.metodos = metodos;
    }
    
    public TablaSimbolos getTablasSimbolos() {
        return tablasSimbolos;
    }

    public void setTablasSimbolos(TablaSimbolos tablasSimbolos) {
        this.tablasSimbolos = tablasSimbolos;
    }

    public LinkedList<NodoAST> getValoresAtributos() {
        return valoresAtributos;
    }

    public void setValoresAtributos(LinkedList<NodoAST> valoresAtributos) {
        this.valoresAtributos = valoresAtributos;
    }

}
