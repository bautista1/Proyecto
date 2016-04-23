/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.interprete3d;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyecto.compilador.util.ErrorC;
import proyecto.gui.LienzoDibujo;

/**
 *
 * @author Luis Fernando Leiva
 */
public class Interprete {

    public static LinkedList<ErrorC> errores = new LinkedList<>();

    LinkedList<Metodo> metodos;
    float[] stack;
    float[] heap;
    HashMap<String, Float> variables;

    LienzoDibujo lienzo;

    public Interprete(LienzoDibujo lienzo) {
        this.lienzo = lienzo;
        stack = new float[10000000];
        heap = new float[10000000];
        variables = new HashMap<String, Float>();
        variables.put("m", 0f);
        variables.put("p", 0f);
    }

    public void limpiar() {
        errores.clear();
        stack = new float[10000000];
        heap = new float[10000000];
        variables = new HashMap<String, Float>();
        variables.put("m", 0f);
        variables.put("p", 0f);
    }

    public void interpretar(File archivo) {
        limpiar();

        metodos = getMetodosFrom3DFile(archivo);
        if (metodos != null) {
            Iterator<Metodo> iter = metodos.iterator();
            while (iter.hasNext()) {
                Metodo metodo = iter.next();
                if (metodo.nombre.equals("main")) {
                    interpretarMetodo(metodo);
                }
            }
        }
    }

    private LinkedList<Metodo> getMetodosFrom3DFile(File archivo) {
        try {
            Scanner3D sc = new Scanner3D(new FileReader(archivo));
            Parser3D pr = new Parser3D(sc);
            try {
                pr.parse();
                return pr.metodos;
            } catch (Exception ex) {
                Logger.getLogger(Interprete.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Interprete.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void interpretarMetodo(Metodo metodo) {
        ListIterator<Instruccion> iter = metodo.ins.listIterator();
        while (iter.hasNext()) {
            Instruccion ins = iter.next();
            if (ins.isAsignacion()) {
                interpretarAsignacion(ins);
            } else if (ins.isFuncion()) {
                if (ins.ref2.equals("fin")) {
                    break;
                }
                Iterator<Metodo> iterM = metodos.iterator();
                while (iterM.hasNext()) {
                    Metodo m = iterM.next();
                    if (m.nombre.equals(ins.ref2)) {
                        interpretarMetodo(m);
                        break;
                    }
                }
                if (isMetodoGeneral(ins.ref2)) {
                    interpretarMetodoGlobal(ins.ref2);
                }
            } else if (ins.isGoto()) {
                while (iter.hasPrevious()) {
                    if (iter.previous().ref1.equals(ins.ref2)) {
                        break;
                    }
                }
                while (iter.hasNext()) {
                    if (iter.next().ref1.equals(ins.ref2)) {
                        break;
                    }
                }
            } else if (ins.isIf()) {
                boolean val = interpretarIf(ins);
                if (val) {
                    while (iter.hasPrevious()) {
                        if (iter.previous().ref1.equals(ins.ref5)) {
                            break;
                        }
                    }
                    while (iter.hasNext()) {
                        if (iter.next().ref1.equals(ins.ref5)) {
                            break;
                        }
                    }
                }
            } else if (ins.isPrinf()) {
                interpretarPrintf(ins);
            } else if (ins.isReturn()) {
                break;
            }
        }
    }

    private void interpretarAsignacion(Instruccion ins) {
        try {
            float v1 = 0, v2 = 0;
            if (ins.refsUsed() == 4) {
                if (Instruccion.isNumber(ins.ref2)) {
                    v1 = Float.valueOf(ins.ref2).floatValue();
                } else {
                    v1 = variables.get(ins.ref2);
                }
                if (Instruccion.isNumber(ins.ref4)) {
                    v2 = Float.valueOf(ins.ref4).floatValue();
                } else {
                    v2 = variables.get(ins.ref4);
                }

                if (ins.ref3.equals("+")) {
                    variables.put(ins.ref1, v1 + v2);
                } else if (ins.ref3.equals("-")) {
                    variables.put(ins.ref1, v1 - v2);
                } else if (ins.ref3.equals("*")) {
                    variables.put(ins.ref1, v1 * v2);
                } else if (ins.ref3.equals("/")) {
                    variables.put(ins.ref1, v1 / v2);
                } else if (ins.ref3.equals("^")) {
                    variables.put(ins.ref1, v1 % v2);
                }
            } else if (ins.refsUsed() == 3) {
                if (ins.ref1.equals("stack")) {
                    if (Instruccion.isNumber(ins.ref2)) {
                        v1 = Float.valueOf(ins.ref2).floatValue();
                    } else {
                        v1 = variables.get(ins.ref2);
                    }
                    if (Instruccion.isNumber(ins.ref3)) {
                        v2 = Float.valueOf(ins.ref3).floatValue();
                    } else {
                        v2 = variables.get(ins.ref3);
                    }

                    stack[(int) v1] = v2;
                } else if (ins.ref1.equals("heap")) {
                    if (Instruccion.isNumber(ins.ref2)) {
                        v1 = Float.valueOf(ins.ref2).floatValue();
                    } else {
                        v1 = variables.get(ins.ref2);
                    }
                    if (Instruccion.isNumber(ins.ref3)) {
                        v2 = Float.valueOf(ins.ref3).floatValue();
                    } else {
                        v2 = variables.get(ins.ref3);
                    }

                    heap[(int) v1] = v2;
                } else if (ins.ref2.equals("stack")) {
                    if (Instruccion.isNumber(ins.ref3)) {
                        v1 = Float.valueOf(ins.ref3).floatValue();
                    } else {
                        v1 = variables.get(ins.ref3);
                    }

                    variables.put(ins.ref1, stack[(int) v1]);
                } else if (ins.ref2.equals("heap")) {
                    if (Instruccion.isNumber(ins.ref3)) {
                        v1 = Float.valueOf(ins.ref3).floatValue();
                    } else {
                        v1 = variables.get(ins.ref3);
                    }

                    variables.put(ins.ref1, heap[(int) v1]);
                }
            } else if (ins.refsUsed() == 2) {
                if (Instruccion.isNumber(ins.ref2)) {
                    v1 = Float.valueOf(ins.ref2).floatValue();
                } else {
                    v1 = variables.get(ins.ref2);
                }
                variables.put(ins.ref1, v1);
            }
        } catch (Exception e) {
            System.out.println("Error en la asignacion en el 3d:" + e.getMessage());
        }
    }

    private boolean interpretarIf(Instruccion ins) {
        float op1 = 0, op2 = 0;
        if (Instruccion.isNumber(ins.ref2)) {
            op1 = Float.valueOf(ins.ref2).floatValue();
        } else {
            op1 = variables.get(ins.ref2);
        }
        if (Instruccion.isNumber(ins.ref4)) {
            op2 = Float.valueOf(ins.ref4).floatValue();
        } else {
            op2 = variables.get(ins.ref4);
        }

        if (ins.ref3.equals("!=")) {
            return op1 != op2;
        } else if (ins.ref3.equals("==")) {
            return op1 == op2;
        } else if (ins.ref3.equals(">=")) {
            return op1 >= op2;
        } else if (ins.ref3.equals("<=")) {
            return op1 <= op2;
        } else if (ins.ref3.equals(">")) {
            return op1 > op2;
        } else if (ins.ref3.equals("<")) {
            return op1 < op2;
        }
        return false;
    }

    private void interpretarPrintf(Instruccion ins) {
        float v1 = 0;
        if (Instruccion.isNumber(ins.ref3)) {
            v1 = Float.valueOf(ins.ref3);
        } else {
            v1 = variables.get(ins.ref3);
        }
        if (ins.ref2.equals("%d")) {
            lienzo.printInConsole(false, (int) v1);
        } else if (ins.ref2.equals("%f")) {
            lienzo.printInConsole(false, v1);
        } else {
            lienzo.printInConsole(true, (int) v1);
        }
    }

    private boolean isMetodoGeneral(String firma) {
        return firma.matches("(Linea_int_int_int_int_int_int_int)|(Texto_string_int_int_int_int_int)|(Arco_int_int_int_int_int_int_int_int_int_boolean)|(Rectangulo_int_int_int_int_int_int_int_boolean)|(Ovalo_int_int_int_int_int_int_int_boolean)|(Poligono_Array_int_Array_int_int_int_int_boolean)|(Lienzo_int_int_int_int_int)");
    }

    private void interpretarMetodoGlobal(String ref2) {
        try {
            if (ref2.matches("(Linea_int_int_int_int_int_int_int)|(Texto_string_int_int_int_int_int)|(Arco_int_int_int_int_int_int_int_int_int_boolean)|(Rectangulo_int_int_int_int_int_int_int_boolean)|(Ovalo_int_int_int_int_int_int_int_boolean)|(Poligono_Array_int_Array_int_int_int_int_boolean)|(Lienzo_int_int_int_int_int)")) {
                if (ref2.equals("Linea_int_int_int_int_int_int_int")) {
                    int val1 = (int) stack[variables.get("p").intValue() + 1];
                    int val2 = (int) stack[variables.get("p").intValue() + 2];
                    int val3 = (int) stack[variables.get("p").intValue() + 3];
                    int val4 = (int) stack[variables.get("p").intValue() + 4];
                    int val5 = (int) stack[variables.get("p").intValue() + 5];
                    int val6 = (int) stack[variables.get("p").intValue() + 6];
                    int val7 = (int) stack[variables.get("p").intValue() + 7];
                    lienzo.dibujarLinea(val1, val2, val3, val4, val5, val6, val7);
                } else if (ref2.equals("Texto_string_int_int_int_int_int")) {
                    int val1 = (int) stack[variables.get("p").intValue() + 1]; //este contiene la referencia a la cadena...
                    String texto = obtenerCadena(val1);
                    int val2 = (int) stack[variables.get("p").intValue() + 2];
                    int val3 = (int) stack[variables.get("p").intValue() + 3];
                    int val4 = (int) stack[variables.get("p").intValue() + 4];
                    int val5 = (int) stack[variables.get("p").intValue() + 5];
                    int val6 = (int) stack[variables.get("p").intValue() + 6];
                    lienzo.dibujarTexto(texto, val2, val3, val4, val5, val6);
                } else if (ref2.equals("Arco_int_int_int_int_int_int_int_int_int_boolean")) {
                    int val1 = (int) stack[variables.get("p").intValue() + 1];
                    int val2 = (int) stack[variables.get("p").intValue() + 2];
                    int val3 = (int) stack[variables.get("p").intValue() + 3];
                    int val4 = (int) stack[variables.get("p").intValue() + 4];
                    int val5 = (int) stack[variables.get("p").intValue() + 5];
                    int val6 = (int) stack[variables.get("p").intValue() + 6];
                    int val7 = (int) stack[variables.get("p").intValue() + 7];
                    int val8 = (int) stack[variables.get("p").intValue() + 8];
                    int val9 = (int) stack[variables.get("p").intValue() + 9];
                    int val10 = (int) stack[variables.get("p").intValue() + 10];
                    lienzo.dibujarArco(val1, val2, val3, val4, val5, val6, val7, val8, val9, val10);
                } else if (ref2.equals("Rectangulo_int_int_int_int_int_int_int_boolean")) {
                    int val1 = (int) stack[variables.get("p").intValue() + 1];
                    int val2 = (int) stack[variables.get("p").intValue() + 2];
                    int val3 = (int) stack[variables.get("p").intValue() + 3];
                    int val4 = (int) stack[variables.get("p").intValue() + 4];
                    int val5 = (int) stack[variables.get("p").intValue() + 5];
                    int val6 = (int) stack[variables.get("p").intValue() + 6];
                    int val7 = (int) stack[variables.get("p").intValue() + 7];
                    int val8 = (int) stack[variables.get("p").intValue() + 8];
                    lienzo.dibujarRectangulo(val1, val2, val3, val4, val5, val6, val7, val8);
                } else if (ref2.equals("Ovalo_int_int_int_int_int_int_int_boolean")) {
                    int val1 = (int) stack[variables.get("p").intValue() + 1];
                    int val2 = (int) stack[variables.get("p").intValue() + 2];
                    int val3 = (int) stack[variables.get("p").intValue() + 3];
                    int val4 = (int) stack[variables.get("p").intValue() + 4];
                    int val5 = (int) stack[variables.get("p").intValue() + 5];
                    int val6 = (int) stack[variables.get("p").intValue() + 6];
                    int val7 = (int) stack[variables.get("p").intValue() + 7];
                    int val8 = (int) stack[variables.get("p").intValue() + 8];
                    lienzo.dibujarOvalo(val1, val2, val3, val4, val5, val6, val7, val8);
                } else if (ref2.equals("Poligono_Array_int_Array_int_int_int_int_boolean")) {
                    //falta...
                } else if (ref2.equals("Lienzo_int_int_int_int_int")) {
                    int val1 = (int) stack[variables.get("p").intValue() + 1];
                    int val2 = (int) stack[variables.get("p").intValue() + 2];
                    int val3 = (int) stack[variables.get("p").intValue() + 3];
                    int val4 = (int) stack[variables.get("p").intValue() + 4];
                    int val5 = (int) stack[variables.get("p").intValue() + 5];
                    lienzo.cambiarLienzo(val1, val2, val3, val4, val5);
                }
            } else {
                lienzo.printInConsole("Se llamo al metodo \"" + ref2 + "\" y no se proceso por no existir...");
            }
        } catch (Exception e) {
            System.out.println("Error en la llamada al metodo en 3d: " + e.getMessage());
        }
    }

    public String obtenerCadena(float posicion) {
        String cadena = "";
        int tamanio = (int) heap[(int) posicion];
        for (int i = 1; i <= tamanio; i++) {
            int posInt = (int) posicion;
            int val = (int) heap[i + posInt];
            cadena += ((char) val);
        }
        return cadena;
    }
}
