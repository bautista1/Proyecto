/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.util;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import proyecto.compilador.proyectos.ParserProyectos;
import proyecto.compilador.proyectos.ScannerProyectos;
import proyecto.compilador.util.ErrorC;
import proyecto.gui.ProyectoAbierto;

/**
 *
 * @author Luis Fernando Leiva
 */
public class GestorArchivos {
    
    public static List<ErrorC> errores;

    public static String examinar(Component parent) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = chooser.showOpenDialog(parent);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getPath();
        }
        return "";
    }

    public static String abrirArchivo(Component parent) {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Archivos de proyecto (pj) o archivos de codigo fuente (frc)", "pj", "frc");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(parent);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getPath();
        }
        return "";
    }

    public static boolean escribirArchivo(String pathArchivo, String contenido) {
        File archivo = new File(pathArchivo);
        archivo.getParentFile().mkdirs();
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(archivo));
            out.write(contenido);
            out.close();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(GestorArchivos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static String leerArchivo(String pathArchivo) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(new File(pathArchivo)));
            String linea;
            String texto = "";
            while ((linea = in.readLine()) != null) {
                texto += linea + "\n";
            }
            return texto;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GestorArchivos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GestorArchivos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static ProyectoAbierto cargarProyectoFromArchivo(String pathArchivo){
        File archivo = new File(pathArchivo);
        if (archivo.exists()) {
            try {
                ScannerProyectos sc = new ScannerProyectos(new FileInputStream(archivo));
                ParserProyectos parser = new ParserProyectos(sc);
                parser.parse();
                errores = sc.errores;
                errores.addAll(parser.errores);
                if (errores.size() < 1) {
                    return new ProyectoAbierto(parser.nombre, parser.ruta, pathArchivo, parser.archivos, parser.principal);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GestorArchivos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(GestorArchivos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public static boolean eliminarArchivo(String path) {
        File archivo = new File(path);
        if (archivo.exists()) {
            return archivo.delete();
        }
        return false;
    }

}
