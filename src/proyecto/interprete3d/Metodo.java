/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.interprete3d;

import java.util.LinkedList;

/**
 *
 * @author Luis Fernando Leiva
 */
public class Metodo {
    public String nombre;
    public LinkedList<Instruccion> ins;

    public Metodo(String nombre, LinkedList<Instruccion> ins) {
        this.nombre = nombre;
        this.ins = ins;
    }    
}
