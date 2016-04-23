/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyecto.gui;

import javax.swing.JTextPane;
import proyecto.util.GestorArchivos;

/**
 *
 * @author Luis Fernando Leiva
 */
public class ArchivoAbierto {
    
    private String path;
    private ProyectoAbierto proyecto;
    private JTextPane panelTexto;

    public ArchivoAbierto(String path, ProyectoAbierto proyecto, JTextPane panelTexto) {
        this.path = path;
        this.proyecto = proyecto;
        this.panelTexto = panelTexto;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ProyectoAbierto getProyecto() {
        return proyecto;
    }

    public void setProyecto(ProyectoAbierto proyecto) {
        this.proyecto = proyecto;
    }

    public JTextPane getPanelTexto() {
        return panelTexto;
    }

    public void setPanelTexto(JTextPane panelTexto) {
        this.panelTexto = panelTexto;
    }
    
    public boolean guardarArchivo(){
        return GestorArchivos.escribirArchivo(path, panelTexto.getText());
    }
    
}
