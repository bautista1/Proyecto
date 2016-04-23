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
public class TablaSimbolos extends LinkedList<HashMap<String, Simbolo>> {

    //variable para controlar el tamaño actual de toda la tabla de simbolos...
    private int posicionSigStack;
    private int posicionSigHeap;

    public TablaSimbolos() {
        super();
        posicionSigStack = 0;
        posicionSigHeap = 0;
        //agregando el ambito global
        this.addFirst(new HashMap<String, Simbolo>());
    }

    public void agregarAmbito() {
        //agregando un nuevo ambito a la tabla de simbolos
        this.addFirst(new HashMap<String, Simbolo>());
    }

    public void eliminarAmbito() {
        //eliminando un ambito pero nunca eliminar el ambito global
        if (this.size() > 1) {
            this.posicionSigStack -= calcularTamanioAmbito(this.getFirst());
            this.removeFirst();
        }
    }

    public void agregarSimbolo(Simbolo o) {
        int incremento = 0;
        if (o.getTipo().equals("float")) {
            incremento = 2;
        } else {
            incremento = 1;
        }
        if (this.size() > 1) {
            o.setPosicion(posicionSigStack);
            posicionSigStack += incremento;
        } else {
            o.setPosicion(posicionSigHeap);
            posicionSigHeap += incremento;
        }
        this.getFirst().put(o.getNombre(), o);
    }

    public Simbolo obtenerSimbolo(String nombre) {
        //buscando desde el primer ambito hasta el global...
        for (HashMap<String, Simbolo> t : this) {
            if (t.containsKey(nombre)) {
                return t.get(nombre);
            }
        }
        return null;
    }

    public void cargarHerencia(TablaSimbolos tablas) {
        int tamanio = 0;
        for (Simbolo s : tablas.getLast().values()) {
            if (!s.getNombre().equals("this")) {
                if (s.getTipo().equals("float")) {
                    tamanio += 2;
                } else {
                    tamanio++;
                }
                this.getLast().put(s.getNombre(), s);
            }
        }
        posicionSigHeap += tamanio;
    }

    public int calcularTamanioAmbito(HashMap<String, Simbolo> tabla) {
        int tamanio = 0;
        for (Simbolo s : tabla.values()) {
            if (s.getTipo().equals("float")) {
                tamanio += 2;
            } else {
                tamanio++;
            }
        }
        return tamanio;
    }

    public void limpiarAmbitos(){
        while (this.size() > 1) {
            this.removeFirst();
        }
    }

    public int getPosicionSigStack() {
        return posicionSigStack;
    }

    public void setPosicionSigStack(int posicionSigStack) {
        this.posicionSigStack = posicionSigStack;
    }

    public int getPosicionSigHeap() {
        return posicionSigHeap;
    }

    public void setPosicionSigHeap(int posicionSigHeap) {
        this.posicionSigHeap = posicionSigHeap;
    }
    
    
    
}
