/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.optimizador;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyecto.interprete3d.Instruccion;
import proyecto.interprete3d.Metodo;
import proyecto.interprete3d.Parser3D;
import proyecto.interprete3d.Scanner3D;
import proyecto.util.GestorArchivos;

/**
 *
 * @author Luis Fernando Leiva
 */
public class Optimizador {

    public BufferedWriter log;
    public String logStr;    
    
    public Optimizador() {
    }

    public void optimizar(File archivo) {
        try {
            Scanner3D sc = new Scanner3D(new FileReader(archivo.getPath()));
            Parser3D pr = new Parser3D(sc);
            try {
                logStr = "";
                pr.parse();
                LinkedList<Metodo> metodos = pr.metodos;
                if (metodos != null) {
                    generarGrafo(archivo.getPath() + "_pre", metodos);
                    optimizar(metodos, archivo.getPath() + "_O2.3dr.log");
                    generarGrafo(archivo.getPath() + "_post", metodos);
                    writeCod3D(archivo, metodos);
                }
            } catch (Exception ex) {
                Logger.getLogger(Optimizador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Optimizador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void generarGrafo(String pathArchivo, LinkedList<Metodo> metodos) {
        String enlaces = "";
        String graphvizStr = "digraph G {\n \t\tgraph [fontsize=30 labelloc=\"t\" label=\"\" splines=true overlap=false rankdir = \"LR\"];\n" +
"  ratio = auto;";
        for (Metodo m : metodos) {
            //clusters
            graphvizStr += "\tsubgraph cluster_" + m.nombre + " {\n";
            graphvizStr += "\t\tlabel = \"" + m.nombre + "\";\n";
            graphvizStr += "\t\tstyle=filled;\n";
            graphvizStr += "\t\tcolor=lightgrey;\n";
            //generando bloques...
            int contadorBloques = 0;
            LinkedList<Bloque> bloques = generarBloques(m.ins);
            for (Bloque b : bloques) {
                graphvizStr += b.generarCodigoBloque(m.nombre, contadorBloques);
                if (contadorBloques > 0) {
                    enlaces += "\t\t" + m.nombre + "_bloque_" + (contadorBloques - 1) + " -> " + m.nombre + "_bloque_" + contadorBloques + ";\n";
                }
                //buscando enlaces a otros nodos...
                for (Instruccion ins: b) {
                    if (ins.isGoto()) {
                        for (int i = 0; i < bloques.size(); i++) {
                            for (Instruccion insBusqueda: bloques.get(i)) {
                                if (insBusqueda.isLabel()) {
                                    if (ins.ref2.equals(insBusqueda.ref1)) {
                                        enlaces += "\t\t" + m.nombre + "_bloque_" + contadorBloques + " -> " + m.nombre + "_bloque_" + i + ";\n";
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                contadorBloques++;
            }
            graphvizStr += "\t}\n";
        }
        graphvizStr += enlaces;
        graphvizStr += "}";
        GestorArchivos.escribirArchivo(pathArchivo + ".dot", graphvizStr);
        
    }

    private void writeCod3D(File archivo, LinkedList<Metodo> metodos) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(archivo.getPath() + "_O2.3dr"));
            Iterator<Metodo> iter = metodos.iterator();
            while (iter.hasNext()) {
                Metodo m = iter.next();
                if (m.nombre.equals("main")) {
                    out.write("method " + m.nombre + " (){\n");
                } else {
                    out.write("method " + m.nombre + " (){\n");
                }
                Iterator<Instruccion> iterIns = m.ins.iterator();
                while (iterIns.hasNext()) {
                    String insS = iterIns.next().toString();
                    out.write(insS + "\n");
                }
                out.write("}\n\n");
            }
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(Optimizador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void writeToLog(int linea, String descripcion) {
        try {
            logStr += "Linea: " + linea + "\t" + descripcion + "\n";
            log.write("Linea: " + linea + "\t" + descripcion + "\n");
        } catch (IOException ex) {
            Logger.getLogger(Optimizador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void optimizar(LinkedList<Metodo> metodos, String dirLog) {
        try {
            log = new BufferedWriter(new FileWriter(dirLog));
            Iterator<Metodo> iter = metodos.iterator();
            while (iter.hasNext()) {
                optimizarMetodo(iter.next());
            }
            log.close();
        } catch (IOException ex) {
            Logger.getLogger(Optimizador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void optimizarMetodo(Metodo metodo) {
        //optimizando instrucciones simples...
        boolean cambio = optimizarMirilla(metodo.ins);
        while (cambio) {
            cambio = optimizarMirilla(metodo.ins);
        }
        //eliminacion de saltos innecesarios...
        optimizarSaltos(metodo.ins);
        //eliminando codigo inalcanzable...
        optimizarElimCodInalcanzable(metodo.ins);
        //optimizar por bloques
        optimizarBloques(metodo.ins);
    }

    private boolean optimizarMirilla(LinkedList<Instruccion> instrucciones) {
        boolean cambio = false;
        int contadorIteracion = 0;
        //optimizacion simple... elimina cons+cons; tmp*1; tmp*0; tmp+0...
        ListIterator<Instruccion> iterIns = instrucciones.listIterator();
        while (iterIns.hasNext()) {
            contadorIteracion = iterIns.nextIndex();
            Instruccion ins = iterIns.next();
            if (ins.isEnUso() && ins.isAsignacion()) {
                //buscando el formato tmp = x opMat x;
                if (ins.refsUsed() == 4) {
                    if (ins.ref3.equals("+") || ins.ref3.equals("-")) {
                        if (Instruccion.isNumber(ins.ref2) && Instruccion.isNumber(ins.ref4)) {
                            String logd = "Mirilla: Reduccion Algebraica: " + ins.toString();
                            ins.ref2 = (Integer.valueOf(ins.ref2).intValue() + Integer.valueOf(ins.ref4).intValue()) + "";
                            ins.ref3 = ins.ref4 = null;
                            writeToLog(ins.linea, logd + " --->> " + ins.toString());
                            cambio = true;
                        } else if (ins.ref2.equals("0")) {
                            String logd = "Mirilla: Reduccion Algebraica: " + ins.toString();
                            ins.ref2 = ins.ref4;
                            ins.ref3 = ins.ref4 = null;
                            writeToLog(ins.linea, logd + " --> " + ins.toString());
                            cambio = true;
                        } else if (ins.ref4.equals("0")) {
                            String logd = "Mirilla: Reduccion Algebraica: " + ins.toString();
                            ins.ref3 = ins.ref4 = null;
                            writeToLog(ins.linea, logd + " --> " + ins.toString());
                            cambio = true;
                        }
                    } else if (ins.ref3.equals("*")) {
                        if (ins.ref2.equals("0") || ins.ref4.equals("0")) {
                            String logd = "Mirilla: Reduccion Algebraica: " + ins.toString();
                            ins.ref2 = "0";
                            ins.ref3 = ins.ref4 = null;
                            writeToLog(ins.linea, logd + " --> " + ins.toString());
                            cambio = true;
                        } else if (ins.ref2.equals("1")) {
                            String logd = "Mirilla: Reduccion Algebraica: " + ins.toString();
                            ins.ref2 = ins.ref4;
                            ins.ref3 = ins.ref4 = null;
                            writeToLog(ins.linea, logd + " --> " + ins.toString());
                            cambio = true;
                        } else if (ins.ref4.equals("1")) {
                            String logd = "Mirilla: Reduccion Algebraica: " + ins.toString();
                            ins.ref3 = ins.ref4 = null;
                            writeToLog(ins.linea, logd + " --> " + ins.toString());
                            cambio = true;
                        }
                    }
                } //fin optimizacion con 4 punteros....
                //comprobando asignaciones con 2 apuntadores tmp = x;
                else if (ins.refsUsed() == 2) {
                    ListIterator<Instruccion> iter2Refs = instrucciones.listIterator(contadorIteracion + 1);
                    while (iter2Refs.hasNext()) {
                        Instruccion ins2refs = iter2Refs.next();
                        if (ins2refs.isAsignacion() && ins2refs.isEnUso()) {
                            if (ins2refs.ref1.equals(ins.ref1)) {
                                break;
                            } else {
                                if (ins2refs.refsUsed() == 4) {
                                    if (ins2refs.ref2.equals(ins.ref1) && ins2refs.apuntadoresDifs()) {
                                        String logd = "Bloques: Propagacion de copias: " + ins2refs.toString();
                                        ins2refs.ref2 = ins.ref2;
                                        writeToLog(ins2refs.linea, logd + " --> " + ins2refs.toString());
                                        cambio = true;
                                    } else if (ins2refs.ref4.equals(ins.ref1) && ins2refs.apuntadoresDifs()) {
                                        String logd = "Bloques: Propagacion de copias: " + ins2refs.toString();
                                        ins2refs.ref4 = ins.ref2;
                                        writeToLog(ins2refs.linea, logd + " --> " + ins2refs.toString());
                                        cambio = true;
                                    }
                                } else if (ins2refs.refsUsed() == 3) {
                                    if (ins2refs.ref1.equals("stack") || ins2refs.ref1.equals("heap")) {
                                        if (ins2refs.ref2.equals(ins.ref1) && ins2refs.apuntadoresDifs()) {
                                            String logd = "Bloques: Propagacion de copias: " + ins2refs.toString();
                                            ins2refs.ref2 = ins.ref2;
                                            writeToLog(ins2refs.linea, logd + " --> " + ins2refs.toString());
                                            cambio = true;
                                        } else if (ins2refs.ref3.equals(ins.ref1) && ins2refs.apuntadoresDifs()) {
                                            String logd = "Bloques: Propagacion de copias: " + ins2refs.toString();
                                            ins2refs.ref3 = ins.ref2;
                                            writeToLog(ins2refs.linea, logd + " --> " + ins2refs.toString());
                                            cambio = true;
                                        }
                                    } else {
                                        if (ins2refs.ref1.equals(ins.ref1) && ins2refs.apuntadoresDifs()) {
                                            String logd = "Bloques: Propagacion de copias: " + ins2refs.toString();
                                            ins2refs.ref1 = ins.ref2;
                                            writeToLog(ins2refs.linea, logd + " --> " + ins2refs.toString());
                                            cambio = true;
                                        } else if (ins2refs.ref3.equals(ins.ref1) && ins2refs.apuntadoresDifs()) {
                                            String logd = "Bloques: Propagacion de copias: " + ins2refs.toString();
                                            ins2refs.ref3 = ins.ref2;
                                            writeToLog(ins2refs.linea, logd + " --> " + ins2refs.toString());
                                            cambio = true;
                                        }
                                    }
                                }
                            }
                        } else {
                            if (!ins2refs.isPrinf()) {
                                break;
                            }
                        }
                    }
                } //fin asignacion con 2 valores
            } // fin si es asignacion...........
        }
        return cambio;
    }

    private void optimizarElimCodInalcanzable(LinkedList<Instruccion> instrucciones) {
        ListIterator<Instruccion> iter = instrucciones.listIterator();
        LinkedList<Instruccion> lista_eliminar = new LinkedList<Instruccion>();
        while (iter.hasNext()) {
            boolean usado = false;
            Instruccion ins = iter.next();
            if (ins.isAsignacion() && ins.refsUsed() != 3 && !ins.ref1.equals("m") && !ins.ref1.equals("p") && ins.apuntadoresDifs()) {
                ListIterator<Instruccion> iterSig = instrucciones.listIterator();
                while (iterSig.hasNext()) {
                    Instruccion insS = iterSig.next();
                    if (insS.isAsignacion() && ins != insS) {
                        if (insS.refsUsed() == 4) {
                            if (insS.ref1.equals(ins.ref1) || insS.ref2.equals(ins.ref1) || insS.ref4.equals(ins.ref1)) {
                                usado = true;
                                break;
                            }
                        } else if (insS.refsUsed() == 3) {
                            if (insS.ref1.equals("heap") || insS.ref1.equals("stack")) {
                                if (insS.ref2.equals(ins.ref1) || insS.ref3.equals(ins.ref1)) {
                                    usado = true;
                                    break;
                                }
                            } else {
                                if (insS.ref1.equals(ins.ref1) || insS.ref3.equals(ins.ref1)) {
                                    usado = true;
                                    break;
                                }
                            }
                        } else if (insS.refsUsed() == 2) {
                            if (insS.ref1.equals(ins.ref1) || insS.ref2.equals(ins.ref1)) {
                                usado = true;
                                break;
                            }
                        }
                    } else if (insS.isIf()) {
                        if (insS.ref2.equals(ins.ref1) || insS.ref4.equals(ins.ref1)) {
                            usado = true;
                            break;
                        }
                    } else if (insS.isPrinf()) {
                        if (insS.ref2.equals(ins.ref1)) {
                            usado = true;
                            break;
                        }
                    }
                }
                if (!usado) {
                    lista_eliminar.add(ins);
                }
            }
        }
        iter = lista_eliminar.listIterator();
        while (iter.hasNext()) {
            Instruccion ins = iter.next();
            instrucciones.remove(ins);
            writeToLog(ins.linea, "Bloques: Eliminacion codigo inactivo --> " + ins.toString());
        }
    }

    private void optimizarSaltos(LinkedList<Instruccion> instrucciones) {
        ListIterator<Instruccion> iter = instrucciones.listIterator();
        LinkedList<Instruccion> saltos_eliminar = new LinkedList<Instruccion>();
        while (iter.hasNext()) {
            Instruccion ins = iter.next();
            if (ins.isIf()) {
                if (iter.hasNext()) {
                    Instruccion ins2 = iter.next();
                    if (ins2.isGoto()) {
                        if (iter.hasNext()) {
                            Instruccion ins3 = iter.next();
                            if (ins3.isLabel()) {
                                if (ins3.ref1.equals(ins.ref5)) {
                                    String logd = "Mirilla: Flujos de Control: " + ins.toString();
                                    ins.ref3 = invertirOpRel(ins.ref3);
                                    ins.ref5 = ins2.ref2;
                                    writeToLog(ins.linea, logd + " --> " + ins.toString());
                                    writeToLog(ins2.linea, "Mirilla: Flujos de Control: Eliminando --> " + ins2.toString());
                                    writeToLog(ins3.linea, "Mirilla: Flujos de Control: Eliminando --> " + ins3.toString());
                                    saltos_eliminar.add(ins2);
                                    saltos_eliminar.add(ins3);
                                }
                            }
                        }
                    }
                }
            }
        }
        instrucciones.removeAll(saltos_eliminar);
    }

    private void optimizarBloques(LinkedList<Instruccion> ins) {
        LinkedList<Bloque> bloques = generarBloques(ins);
        Iterator<Bloque> iter = bloques.iterator();
        boolean cambioAlguna = false;
        while (iter.hasNext()) {
            Bloque b = iter.next();
            boolean cambio = optimizarBloque(b);
            while (cambio) {
                cambioAlguna = true;
                cambio = optimizarBloque(b);
            }
        }
        if (cambioAlguna) {
            //optimizarElimCodInalcanzable(ins);
        }
    }

    private boolean optimizarBloque(Bloque b) {
        //viendo subexpresiones comunes
        boolean cambio = false;
        boolean ret = false;
        ListIterator<Instruccion> iter = b.listIterator();
        if (iter.hasNext()) {
            Instruccion ins = iter.next();
            if (ins.isAsignacion() && !ins.ref1.equals("m") && !ins.ref1.equals("p")) {
                if (ins.refsUsed() == 4) {
                    ListIterator<Instruccion> iterS = b.listIterator();
                    while (iterS.hasNext()) {
                        Instruccion insS = iterS.next();
                        if (insS.isAsignacion() && insS != ins) {
                            if (insS.ref1.equals(ins.ref1)) {
                                cambio = true;
                                break;
                            }
                        }
                    }
                    if (!cambio) {
                        iterS = b.listIterator();
                        while (iterS.hasNext()) {
                            Instruccion insS = iterS.next();
                            if (insS.isAsignacion() && insS != ins) {
                                if (insS.refsUsed() == 4) {
                                    if (insS.ref2.equals(ins.ref2) || insS.ref3.equals(ins.ref3) || insS.ref4.equals(ins.ref4)) {
                                        String logd = "Bloques GLOBAL: Subexpresiones comunes: " + insS.toString();
                                        insS.ref2 = ins.ref1;
                                        insS.ref3 = insS.ref4 = null;
                                        writeToLog(insS.linea, logd + " --> " + insS.toString());
                                        ret = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return ret;
    }

    public static LinkedList<Metodo> getMetodos(File archivo) {
        try {
            Scanner3D sc = new Scanner3D(new FileReader(archivo));
            Parser3D pr = new Parser3D(sc);
            try {
                pr.parse();
                return pr.metodos;
            } catch (Exception ex) {
                Logger.getLogger(Optimizador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Optimizador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private String invertirOpRel(String ref3) {
        if (ref3.equals("==")) {
            return "!=";
        }
        if (ref3.equals("!=")) {
            return "==";
        }
        if (ref3.equals(">=")) {
            return "<";
        }
        if (ref3.equals(">")) {
            return "<=";
        }
        if (ref3.equals("<=")) {
            return ">";
        }
        if (ref3.equals("<")) {
            return ">=";
        }
        return null;
    }

    private LinkedList<Bloque> generarBloques(LinkedList<Instruccion> instrucciones) {
        LinkedList<Bloque> lista = new LinkedList<Bloque>();
        Bloque b = new Bloque();
        Iterator<Instruccion> iter = instrucciones.iterator();
        while (iter.hasNext()) {
            Instruccion ins = iter.next();
            if (ins.isAsignacion()) {
                b.add(ins);
            } else if (ins.isIf()) {
                b.add(ins);
                lista.add(b);
                b = new Bloque();
            } else if (ins.isPrinf()) {
                b.add(ins);
            } else if (ins.isGoto()) {
                b.add(ins);
                lista.add(b);
                b = new Bloque();
            } else {
                if (b.size() > 0) {
                    lista.add(b);
                    b = new Bloque();
                    b.add(ins);
                } else {
                    b.add(ins);
                }
            }
        }
        return lista;
    }

}
