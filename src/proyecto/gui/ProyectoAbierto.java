/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyecto.gui;

import java.util.LinkedList;
import java.util.List;
import proyecto.util.GestorArchivos;

/**
 *
 * @author Luis Fernando Leiva
 */
public class ProyectoAbierto {
    
    private String nombre;
    private String ruta;
    private String pathArchivo;
    private List<String> archivos;
    private String principal;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getArchivos() {
        return archivos;
    }

    public void setArchivos(List<String> archivos) {
        this.archivos = archivos;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getPathArchivo() {
        return pathArchivo;
    }

    public void setPathArchivo(String pathArchivo) {
        this.pathArchivo = pathArchivo;
    }

    
    public ProyectoAbierto(String nombre, String ruta, String pathArchivo) {
        this.nombre = nombre;
        this.ruta = ruta;
        this.pathArchivo = pathArchivo;
        archivos = new LinkedList<>();
    }

    public ProyectoAbierto(String nombre, String ruta, String pathArchivo, List<String> archivos, String principal) {
        this.nombre = nombre;
        this.ruta = ruta;
        this.pathArchivo = pathArchivo;
        this.archivos = archivos;
        this.principal = principal;
    }
    
    public boolean escribirArchivoPj(){
        return GestorArchivos.escribirArchivo(pathArchivo, this.toString());
    }
    
    @Override
    public String toString() {
        String contenido = "<Proyecto nombre=\""+nombre+"\" ruta=\""+ruta+"\">\n";
        contenido+="\t<Archivos>\n";
        for (String archivo: archivos) {
            contenido+="\t\t<Archivo nombre\""+archivo+"\" />\n";
        }
        contenido+="\t</Archivos>\n";
        contenido+="\t<Principal>\n";
        if (principal != null){
            contenido+="\t\t<Archivo nombre\""+principal+"\" />\n";
        }
        contenido+="\t</Principal>\n";
        contenido+="</Proyecto>";
        return contenido;
    }
    
}
