/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.optimizador;

import java.util.LinkedList;
import proyecto.interprete3d.Instruccion;

/**
 *
 * @author Luis Fernando Leiva
 */
public class Bloque extends LinkedList<Instruccion> {

    public Bloque() {
        super();
    }

    public String generarCodigoBloque(String nombre, int numBloque) {
        String str = "\t\t" + nombre + "_bloque_" + numBloque + " [ style = \"filled, bold\" penwidth = 5 fillcolor = \"white\" shape = \"Mrecord\" "
                + "label =<<table border=\"0\" cellborder=\"0\" cellpadding=\"3\" bgcolor=\"white\">"
                + "<tr>"
                + "<td bgcolor=\"black\" align=\"center\" colspan=\"2\"><font color=\"white\">Bloque #" + numBloque + "</font></td>"
                + "</tr>";
        int contador = 0;
        for (Instruccion ins : this) {
            str += "<tr>"
                    + "<td align=\"left\" port=\"r0\">&#40;"+contador+"&#41; "+ins.toString().replaceAll("<", "&lt;").replaceAll(">", "&gt;")+" </td>"
                    + "</tr>";
            contador++;
        }
        str += "</table>> ];";

        return str;
    }

}
