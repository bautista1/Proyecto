/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.compilador.lenguaje;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyecto.compilador.util.Clase;
import proyecto.compilador.util.ErrorC;
import proyecto.compilador.util.NodoAST;
import proyecto.compilador.util.Simbolo;
import proyecto.compilador.util.TablaSimbolos;
import proyecto.util.GestorArchivos;

/**
 *
 * @author Luis Fernando Leiva
 */
public class CompiladorLenguaje {

    //variables utilizadas para almacenar la informacion global obtenida al parsear los archivos de entrada
    public static HashMap<String, Clase> clasesCargadas = new HashMap<String, Clase>();
    public static List<ErrorC> errores = new LinkedList<ErrorC>();

    //compilador singleton...
    private static CompiladorLenguaje compiladorLenguaje;

    public static void compilarInicial(File archivo, String ruta) {
        Clase clasePrincipal = compilar(archivo, ruta);
        Simbolo metodoPrincipal = null;
        compiladorLenguaje = new CompiladorLenguaje();
        if (clasePrincipal != null) {
            if (!clasePrincipal.getMetodos().containsKey("main")) {
                errores.add(new ErrorC("Semantico", "La clase principal no ha definido un metodo \"main\"", 0, 0));
            } else {
                metodoPrincipal = clasePrincipal.getMetodos().remove("main");
            }
            compiladorLenguaje.setClasePrincipal(clasePrincipal);
            compiladorLenguaje.setMetodoPrincipal(metodoPrincipal);
            compiladorLenguaje.setPathArchivo(archivo.getPath());
        }
    }

    public static Clase compilar(File archivo, String ruta) {
        try {
            ScannerLenguaje sc = new ScannerLenguaje(new FileReader(archivo));
            ParserLenguaje parser = new ParserLenguaje(sc);
            parser.ruta = ruta;
            parser.parse();
            return parser.clase;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CompiladorLenguaje.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CompiladorLenguaje.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void reiniciar() {
        clasesCargadas.clear();
        errores.clear();
    }

    public static void generarCodigo() {
        compiladorLenguaje.generarCodigo3D();
    }

    //variables para el proceso de compilacion
    private Simbolo metodoPrincipal;
    private Clase clasePrincipal;
    private Simbolo metodoActual;
    private int cantidadConstructores;

    //uso de archivos...
    private String output = "";
    private String pathArchivo;

    private Clase claseActual;

    private TablaSimbolos tablaActual;

    private int contadorTemporales;
    private int contadorEtiquetas;

    private final String punteroS = "p";
    private final String punteroH = "m";
    private final String nomStack = "stack";
    private final String nomHeap = "heap";

    private String etiquetaInicio = null;
    private String etiquetaFinal = null;

    public CompiladorLenguaje() {
        cantidadConstructores = 0;
    }

    public String getCodigo3D() {
        return output;
    }

    public Simbolo getMetodoPrincipal() {
        return metodoPrincipal;
    }

    public void setMetodoPrincipal(Simbolo metodoPrincipal) {
        this.metodoPrincipal = metodoPrincipal;
    }

    public Clase getClasePrincipal() {
        return clasePrincipal;
    }

    public void setClasePrincipal(Clase clasePrincipal) {
        this.clasePrincipal = clasePrincipal;
    }

    public String getPathArchivo() {
        return pathArchivo;
    }

    public void setPathArchivo(String pathArchivo) {
        this.pathArchivo = pathArchivo;
    }

    /*
     * Generacion de texto para el codigo 3D
     */
    public void write(String str) {
        output = output + str;
    }

    public void writeln(String str) {
        this.write(str + "\n");
    }

    /*
     * Generacion de etiquetas y temporales
     */
    public String genTmp() {
        return "t" + contadorTemporales++;
    }

    public String genEtiqueta() {
        return "L" + contadorEtiquetas++;
    }

    private boolean isMetodoGeneral(String firma) {
        return firma.matches("(Linea_int_int_int_int_int_int_int)|(Texto_string_int_int_int_int_int)|(Arco_int_int_int_int_int_int_int_int_int_boolean)|(Rectangulo_int_int_int_int_int_int_int_boolean)|(Ovalo_int_int_int_int_int_int_int_boolean)|(Poligono_Array_int_Array_int_int_int_int_boolean)|(Lienzo_int_int_int_int_int)");
    }

    /*
     * GENERAR ERRORES
     */
    public void addError(int linea, String descripcion) {
        String inicioError = (claseActual != null) ? "Clase:" + claseActual.getNombre() + " ::: " : "";
        errores.add(new ErrorC("Semantico", inicioError + descripcion, linea, 0));
    }

    /* Clase utilitaria para generar el codigo 3D de las expresiones */
    private class Resultado {

        public String tipo;
        public String subtipo;
        public boolean ap;
        public boolean stack;
        public String r1, r2, r3, r4;

        public Resultado(String tipo, String subtipo, boolean ap, boolean stack, String r1, String r2) {
            this.tipo = tipo;
            this.subtipo = subtipo;
            this.ap = ap;
            this.stack = stack;
            this.r1 = r1;
            this.r2 = r2;
        }
    }

    public boolean isValidoTipo(String tipo) {
        tipo = tipo.replaceFirst("referencia:", tipo);
        tipo = tipo.replaceFirst("array:", tipo);
        if (tipo.equals("int")
                || tipo.equals("float")
                || tipo.equals("boolean")
                || tipo.equals("string")
                || tipo.equals("char")) {
            return true;
        } else {
            return clasesCargadas.containsKey(tipo);
        }
    }

    public void generarCodigo3D() {
        compiladorLenguaje.generarCodigoClases();
        /*String cabecera = "#include <stdio.h>\n\n"
         + "int " + nomHeap + "[10000];\n"
         + "int " + nomStack + "[10000];\n"
         + "int " + punteroS + "," + punteroH + ";\n\n";
         cabecera += "int t0";
         for (int i = 1; i <= contadorTemporales; i++) {
         cabecera += ",t" + i;
         }
         cabecera += ";\n\n";
         output = cabecera + output;*/
        GestorArchivos.escribirArchivo(pathArchivo + ".3dr", compiladorLenguaje.getCodigo3D());

    }

    private void generarCodigoClases() {
        for (Clase c : clasesCargadas.values()) {
            if (c != null && c != clasePrincipal) {
                generarClase(c);
            }
        }
        generarClase(clasePrincipal);
        generarMetodo(metodoPrincipal);
    }

    private void generarClase(Clase clase) {
        cantidadConstructores = 0;
        if (clase != null) {
            claseActual = clase;
            tablaActual = clase.getTablasSimbolos();
            for (Simbolo m : clase.getMetodos().values()) {
                if (m.getSubtipo().equals("CONSTRUCTOR")) {
                    cantidadConstructores++;
                }
            }
            if (cantidadConstructores == 0) {
                //si no se declaro ningun constructor entonces agrego uno por defecto...
                claseActual.getMetodos().put(claseActual.getNombre(), new Simbolo(claseActual.getNombre(), "metodo", "CONSTRUCTOR", true, true, true, null, new NodoAST(NodoAST.VACIO, 0)));
            }
            for (Simbolo m : clase.getMetodos().values()) {
                tablaActual.limpiarAmbitos();
                generarMetodo(m);
            }
        }
    }

    private void generarMetodo(Simbolo metodo) {
        if (metodo != null) {
            tablaActual.agregarAmbito();

            if (claseActual == clasePrincipal && metodo.getNombre().equals("main")) {
                writeln("void " + metodo.getNombre() + "() {");
            } else {
                writeln("void " + claseActual.getNombre() + "" + metodo.getNombre() + "() {");
            }

            metodoActual = metodo;
            prepararMetodo(metodo);

            generarInstrucciones((NodoAST) metodo.getMoreInfo2());
            writeln("}\n");

            tablaActual.eliminarAmbito();
        }
    }

    private void prepararMetodo(Simbolo metodo) {
        if (metodo.getMoreInfo() != null) {
            String params = metodo.getMoreInfo().toString();
            tablaActual.agregarSimbolo(new Simbolo("this", claseActual.getNombre(), false, true, false));
            for (String param : params.split(";;;")) {
                String[] datosparam = param.split(":::");
                tablaActual.agregarSimbolo(new Simbolo(datosparam[1], datosparam[0], false, true, false));
            }
        }
        if (metodo.getSubtipo().equals("CONSTRUCTOR")) {
            //generando la información necesaria para los constructores...
            cantidadConstructores++;
            String tmp1 = genTmp();
            writeln("    " + tmp1 + " = " + punteroS + " + 0;");
            writeln("    " + nomStack + "[" + tmp1 + "] = " + punteroH + ";");
            writeln("    " + punteroH + " = " + punteroH + " + " + (claseActual.getTablasSimbolos().getPosicionSigHeap() + 1) + ";");
            //iniciando valores por defecto para la clase
            for (NodoAST var : claseActual.getValoresAtributos()) {
                if (var.getHijos().size() > 0) {
                    NodoAST asig = new NodoAST(NodoAST.ASIGNACION, null, "=", var.getLinea());
                    asig.getHijos().add(new NodoAST(NodoAST.ID, "", var.getDatos1(), var.getLinea()));
                    asig.getHijos().add(var.getHijos().get(0));
                    genAsignacion(asig);
                }
            }
        }
    }

    private void generarInstrucciones(NodoAST n) {
        if (n == null || n.getEtiqueta() == NodoAST.VACIO) {
            return;
        }
        switch (n.getEtiqueta()) {
            case NodoAST.IF:
                genIf(n);
                break;
            case NodoAST.FOR:
                genFor(n);
                break;
            case NodoAST.WHILE:
                genWhile(n);
                break;
            case NodoAST.DOWHILE:
                genDoWhile(n);
                break;
            case NodoAST.ASIGNACION:
                genAsignacion(n);
                break;
            case NodoAST.DECLARACION:
                genDeclaracion(n);
                break;
            case NodoAST.LLAMARFUNCION:
                genLlamarFuncion(n, false, null);
                break;
            case NodoAST.PUNTO:
                generarExpresion(n);
                break;
            case NodoAST.SWITCH:
                genSwitch(n);
                break;
            case NodoAST.RETURN:
                genReturn(n);
                break;
            case NodoAST.FUNIMPRIMIR:
                genImprimir(n);
                break;
            case NodoAST.INSTRUCCIONES:
                Iterator<NodoAST> iter = n.getHijos().iterator();
                while (iter.hasNext()) {
                    generarInstrucciones(iter.next());
                }
                break;
            case NodoAST.INCREMENTO:
                generarExpresion(n);
                break;
            case NodoAST.DECREMENTO:
                generarExpresion(n);
                break;
            case NodoAST.FUNLINEA:
                genLlamarFuncion(n, false, null);
                break;
            case NodoAST.FUNLIENZO:
                genLlamarFuncion(n, false, null);
                break;
            case NodoAST.FUNARCO:
                genLlamarFuncion(n, false, null);
                break;
            case NodoAST.FUNOVALO:
                genLlamarFuncion(n, false, null);
                break;
            case NodoAST.FUNPOLIGONO:
                genLlamarFuncion(n, false, null);
                break;
            case NodoAST.FUNRECTANGULO:
                genLlamarFuncion(n, false, null);
                break;
            case NodoAST.FUNTEXTO:
                genLlamarFuncion(n, false, null);
                break;
            case NodoAST.CONTINUE:
                if (etiquetaInicio == null) {
                    addError(n.getLinea(), "La instruccion continue solo se puede usar dentro de ciclos.");
                } else {
                    this.writeln("    goto " + etiquetaInicio + ";");
                }
                break;
            case NodoAST.BREAK:
                if (etiquetaInicio == null) {
                    addError(n.getLinea(), "La instruccion break solo se puede usar dentro de ciclos.");
                } else {
                    this.writeln("    goto " + etiquetaFinal + ";");
                }
                break;
            default:
                System.out.println("Etiqueta invalida: " + n.getEtiqueta());
        }
    }

    private void genIf(NodoAST n) {
        tablaActual.agregarAmbito();
        Resultado exp = generarExpresion(n.getHijos().get(0));
        verificarBooleanNormalParaCondicion(exp);
        if (exp != null) {
            if (exp.tipo.equals("boolean")) {
                String etiqFin = genEtiqueta();
                this.writeln("  " + exp.r1 + ":");
                generarInstrucciones(n.getHijos().get(1));
                this.writeln("    goto " + etiqFin + ";");
                this.writeln("  " + exp.r2 + ":");
                generarInstrucciones(n.getHijos().get(2));
                this.writeln("  " + etiqFin + ":");
            } else {
                addError(n.getLinea(), "Error. Se esperaba una expresion booleana para el \"if\".");
            }
        }
        tablaActual.eliminarAmbito();
    }

    private void genFor(NodoAST n) {
        String tempEInicio = etiquetaInicio;
        String tempEFinal = etiquetaFinal;

        tablaActual.agregarAmbito();
        generarInstrucciones(n.getHijos().get(0));
        String etiquetaIni = genEtiqueta();

        etiquetaInicio = genEtiqueta();

        this.writeln("  " + etiquetaIni + ":");
        Resultado exp = generarExpresion(n.getHijos().get(1));
        verificarBooleanNormalParaCondicion(exp);
        if (exp != null) {

            etiquetaFinal = exp.r2;
            tablaActual.agregarAmbito();

            if (exp.tipo.equals("boolean")) {
                this.writeln("  " + exp.r1 + ":");
                generarInstrucciones(n.getHijos().get(3));
                this.writeln("  " + etiquetaInicio + ":");
                generarInstrucciones(n.getHijos().get(2));
                this.writeln("    goto " + etiquetaIni + ";");
                this.writeln("  " + exp.r2 + ":");
            } else {
                addError(n.getLinea(), "Error. Se esperaba una expresion booleana para el \"for\" como condicion.");
            }
        }
        tablaActual.eliminarAmbito();
        tablaActual.eliminarAmbito();

        etiquetaInicio = tempEInicio;
        etiquetaFinal = tempEFinal;
    }

    private void genWhile(NodoAST n) {
        String tempEInicio = etiquetaInicio;
        String tempEFinal = etiquetaFinal;
        tablaActual.agregarAmbito();
        String etiquetaIni = genEtiqueta();
        etiquetaInicio = etiquetaIni;
        this.writeln("  " + etiquetaIni + ":");
        Resultado exp = generarExpresion(n.getHijos().get(0));
        verificarBooleanNormalParaCondicion(exp);
        if (exp != null) {
            etiquetaFinal = exp.r2;
            if (exp.tipo.equals("boolean")) {
                this.writeln("  " + exp.r1 + ":");
                generarInstrucciones(n.getHijos().get(1));
                this.writeln("    goto " + etiquetaIni + ";");
                this.writeln("  " + exp.r2 + ":");
            } else {
                addError(n.getLinea(), "Error. Se esperaba una expresion booleana para el \"while\" como condicion.");
            }
        }
        tablaActual.eliminarAmbito();
        etiquetaInicio = tempEInicio;
        etiquetaFinal = tempEFinal;
    }

    private void genDoWhile(NodoAST n) {
        String tempEInicio = etiquetaInicio;
        String tempEFinal = etiquetaFinal;
        tablaActual.agregarAmbito();
        String etiquetaIni = genEtiqueta();
        String etiquetaFin = genEtiqueta();
        etiquetaInicio = etiquetaIni;
        etiquetaFinal = etiquetaFin;
        this.writeln("  " + etiquetaIni + ":");
        generarInstrucciones(n.getHijos().get(1));
        Resultado exp = generarExpresion(n.getHijos().get(0));
        verificarBooleanNormalParaCondicion(exp);
        if (exp != null) {
            if (exp.tipo.equals("boolean")) {
                this.writeln("  " + exp.r1 + ":");
                this.writeln("    goto " + etiquetaIni + ";");
                this.writeln("  " + exp.r2 + ":");
                this.writeln("  " + etiquetaFin + ":");
            } else {
                addError(n.getLinea(), "Error. Se esperaba una expresion booleana para el \"for\" como condicion.");
            }
        }
        tablaActual.eliminarAmbito();
        etiquetaInicio = tempEInicio;
        etiquetaFinal = tempEFinal;
    }

    private void genAsignacion(NodoAST n) {
        if (n.getHijos().size() == 1) {
            //caso de los incrementos y decrementos...
            NodoAST nodo = n.getHijos().get(0);
            if (nodo.getEtiqueta() == NodoAST.INCREMENTO || nodo.getEtiqueta() == NodoAST.DECREMENTO || nodo.getEtiqueta() == NodoAST.VACIO) {
                generarExpresion(nodo);
            } else {
                addError(n.getLinea(), "Instruccion invalida encontrada.");
            }
        } else {
            String varArreglo;
            Resultado ds = generarExpresion(n.getHijos().get(0));
            Resultado exp1 = generarExpresion(n.getHijos().get(1));
            if (ds != null && exp1 != null) {
                if (ds.stack) {
                    varArreglo = nomStack;
                } else {
                    varArreglo = nomHeap;
                }
                if (exp1.ap && !ds.tipo.startsWith("referencia:")) {
                    String valor = genTmp();
                    String pila = (exp1.stack) ? nomStack : nomHeap;
                    this.writeln("    " + valor + " = " + pila + "[" + exp1.r1 + "];");
                    exp1.r1 = valor;
                }
                if (ds.tipo.equals("boolean") && exp1.tipo.equals("boolean") && exp1.r2 != null) {
                    verificarBooleanNormalParaCondicion(exp1);
                    String etiquetaFin = genEtiqueta();
                    this.writeln("  " + exp1.r1 + ":");
                    this.writeln("    " + varArreglo + "[" + ds.r1 + "] = 1;");
                    this.writeln("    goto " + etiquetaFin + ";");
                    this.writeln("  " + exp1.r2 + ":");
                    this.writeln("    " + varArreglo + "[" + ds.r1 + "] = 0;");
                    this.writeln("  " + etiquetaFin + ":");
                } else if (esCompatible(ds.tipo, exp1.tipo)) {
                    this.writeln("    " + varArreglo + "[" + ds.r1 + "] = " + exp1.r1 + ";");
                } else {
                    addError(n.getLinea(), "Error, Tipos incompatibles para la asignacion, se encontraron: " + ds.tipo + " = " + exp1.tipo);
                }
            }
        }
    }

    private void genDeclaracion(NodoAST n) {
        if (n.getDatos1() != null) {
            String tipo = n.getDatos1();
            if (isValidoTipo(tipo)) {
                for (NodoAST var : n.getHijos().get(0).getHijos()) {
                    if (tablaActual.obtenerSimbolo(var.getDatos1()) == null) {
                        tablaActual.agregarSimbolo(new Simbolo(var.getDatos1(), tipo, false, true, false));
                        if (var.getHijos().size() > 0) {
                            NodoAST asig = new NodoAST(NodoAST.ASIGNACION, null, "=", var.getLinea());
                            asig.getHijos().add(new NodoAST(NodoAST.ID, "", var.getDatos1(), n.getLinea()));
                            asig.getHijos().add(var.getHijos().get(0));
                            genAsignacion(asig);
                        }
                    } else {
                        addError(n.getLinea(), "La variable: \"" + var + "\" ya ha sido declarada");
                    }
                }
            } else {
                addError(n.getLinea(), "El tipo: \"" + tipo + "\" no es valido");
            }
        }
    }

    private Resultado genLlamarFuncion(NodoAST n, boolean permitirConstructor, String direccionBase) {
        String firma = n.getDatos1();
        int cont = 1;
        String tmp1 = null, tmp2 = null;
        if (direccionBase == null || direccionBase.equals("")) {
            tmp1 = genTmp();
            this.writeln("    " + tmp1 + " = " + punteroS + " + 0;");
            tmp2 = genTmp();
            this.writeln("    " + tmp2 + " = " + nomStack + "[" + tmp1 + "];");
        } else {
            //caso en el que se accede a un metodo de una variable por lo tanto se tiene una direccion base y no es la 0 del stack...
            tmp2 = direccionBase;
        }
        tmp1 = genTmp();
        this.writeln("    " + tmp1 + " = " + punteroS + " + " + tablaActual.getPosicionSigStack() + ";");
        this.writeln("    " + nomStack + "[" + tmp1 + "] = " + tmp2 + ";");
        if (n.getHijos().size() > 0) {
            for (NodoAST expAST : n.getHijos()) {
                Resultado exp = generarExpresion(expAST);
                if (exp.ap) {
                    String valor = genTmp();
                    String pila = (exp.stack) ? nomStack : nomHeap;
                    this.writeln("    " + valor + " = " + pila + "[" + exp.r1 + "];");
                    exp.r1 = valor;
                }
                if (exp.tipo.equals("booleanN")) {
                    exp.tipo = "boolean";
                }
                firma += "_" + exp.tipo;
                tmp1 = genTmp();
                this.writeln("    " + tmp1 + " = " + punteroS + " + " + (tablaActual.getPosicionSigStack() + cont) + ";");
                this.writeln("    " + nomStack + "[" + tmp1 + "] = " + exp.r1 + ";");
                cont++;
            }
        }

        Simbolo dsM = null;
        String padre = "";
        Clase temporal = claseActual;
        while (temporal != null && dsM == null) {
            dsM = temporal.getMetodos().get(firma);
            if (dsM == null && temporal.getPadre() != null) {
                temporal = clasesCargadas.get(temporal.getPadre());
            }
        }

        if (dsM != null && temporal != null) {
            padre = temporal.getNombre();
        }

        if (dsM != null) {
            String tipo = dsM.getSubtipo();
            if (dsM.getSubtipo().equals("CONSTRUCTOR")) {
                if (!permitirConstructor) {
                    this.addError(n.getLinea(), "El uso del constructor como un metodo no esta permitido ::: " + firma);
                } else {
                    tipo = padre;
                }
            }

            this.writeln("    " + punteroS + " = " + punteroS + " + " + tablaActual.getPosicionSigStack() + ";");
            this.writeln("    " + padre + "" + firma + "();");
            this.writeln("    " + punteroS + " = " + punteroS + " - " + tablaActual.getPosicionSigStack() + ";");
            tmp1 = genTmp();
            this.writeln("    " + tmp1 + " = " + punteroS + " + " + (tablaActual.getPosicionSigStack()) + ";");
            return new Resultado(tipo, "", true, true, tmp1, null);
        } else {
            if (isMetodoGeneral(firma)) {
                this.writeln("    " + punteroS + " = " + punteroS + " + " + tablaActual.getPosicionSigStack() + ";");
                this.writeln("    " + firma + "();");
                this.writeln("    " + punteroS + " = " + punteroS + " - " + tablaActual.getPosicionSigStack() + ";");
                return new Resultado("void", "", true, true, tmp1, null);
            } else {
                this.addError(n.getLinea(), "El metodo no ha sido definido ::: " + firma);
            }
        }
        return null;
    }

    private void genSwitch(NodoAST n) {

    }

    private void genReturn(NodoAST n) {
        String tipoRetorno = metodoActual.getSubtipo();
        if (isValidoTipo(tipoRetorno)) {
            Resultado r = generarExpresion(n.getHijos().get(0));
            if (r.tipo.equals(tipoRetorno)) {
                if (r.ap) {
                    String valor = genTmp();
                    String pila;
                    if (r.stack) {
                        pila = nomStack;
                    } else {
                        pila = nomHeap;
                    }
                    this.writeln("    " + valor + " = " + pila + "[" + r.r1 + "];");
                    r.r1 = valor;
                }
                String tmp = genTmp();
                this.writeln("    " + tmp + " = " + punteroS + " + 0;");
                this.writeln("    " + nomStack + "[" + tmp + "] = " + r.r1 + ";");
                this.writeln("    return;");
            } else {
                addError(n.getLinea(), "Error, se esperaba un retorno de tipo \"" + tipoRetorno + "\" y se encontro un tipo \"" + r.tipo + "\"");
            }
        } else {
            addError(n.getLinea(), "El tipo de retorno \"" + tipoRetorno + "\" no es valido");
        }
    }

    private void genImprimir(NodoAST n) {
        Resultado exp = generarExpresion(n.getHijos().get(0));
        if (exp != null) {
            String valor = genTmp();
            String pila;
            if (exp.ap) {
                pila = (exp.stack) ? nomStack : nomHeap;
                this.writeln("    " + valor + " = " + pila + "[" + exp.r1 + "];");
                exp.r1 = valor;
            }
            if (exp.tipo.equals("string")) {
                String apuntador = genTmp();
                this.writeln("    " + apuntador + " = " + exp.r1 + " + 1;");
                String tamanioString = genTmp();
                this.writeln("    " + tamanioString + " = " + nomHeap + "[" + exp.r1 + "];");
                String contador = genTmp();
                this.writeln("    " + contador + " = 0;");
                String linicio = genEtiqueta();
                String lfin = genEtiqueta();
                this.writeln("  " + linicio + ":");
                this.writeln("    if (" + contador + " >= " + tamanioString + ") goto " + lfin + ";");
                String tmpV = genTmp();
                this.writeln("    " + tmpV + " = " + nomHeap + "[" + apuntador + "];");
                this.writeln("    printf(\"%c\", " + tmpV + ");");
                this.writeln("    " + apuntador + " = " + apuntador + " + 1;");
                this.writeln("    " + contador + " = " + contador + " + 1;");
                this.writeln("    goto " + linicio + ";");
                this.writeln("  " + lfin + ":");
                this.writeln("    printf(\"%c\", 10);");
            } else if (exp.tipo.equals("int")) {
                this.writeln("    printf(\"%d\", " + exp.r1 + ");");
                this.writeln("    printf(\"%c\", 10);");
            } else if (exp.tipo.equals("char")) {
                this.writeln("    printf(\"%c\", " + exp.r1 + ");");
                this.writeln("    printf(\"%c\", 10);");
            } else if (exp.tipo.equals("float")) {
                this.writeln("    printf(\"%f\", " + exp.r1 + ");");
                this.writeln("    printf(\"%c\", 10);");
            } else if (exp.tipo.equals("boolean") || exp.tipo.equals("booleanN")) {
                String etiquetaFin = genEtiqueta();
                verificarBooleanNormalParaCondicion(exp);
                this.writeln("  " + exp.r1 + ":");
                this.writeln("    printf(\"%c\", " + (int) ("t".charAt(0)) + ");");
                this.writeln("    printf(\"%c\", " + (int) ("r".charAt(0)) + ");");
                this.writeln("    printf(\"%c\", " + (int) ("u".charAt(0)) + ");");
                this.writeln("    printf(\"%c\", " + (int) ("e".charAt(0)) + ");");
                this.writeln("    printf(\"%c\", 10);");
                this.writeln("    goto " + etiquetaFin + ";");
                this.writeln("  " + exp.r2 + ":");
                this.writeln("    printf(\"%c\", " + (int) ("f".charAt(0)) + ");");
                this.writeln("    printf(\"%c\", " + (int) ("a".charAt(0)) + ");");
                this.writeln("    printf(\"%c\", " + (int) ("l".charAt(0)) + ");");
                this.writeln("    printf(\"%c\", " + (int) ("s".charAt(0)) + ");");
                this.writeln("    printf(\"%c\", " + (int) ("e".charAt(0)) + ");");
                this.writeln("    printf(\"%c\", 10);");
                this.writeln("  " + etiquetaFin + ":");
            }
        }
    }

    private Resultado generarExpresion(NodoAST n) {
        if (n != null && n.getEtiqueta() != NodoAST.VACIO) {
            try {
                /* CASO EN EL QUE NO TIENE HIJOS */
                if (n.getEtiqueta() == NodoAST.VALOR) {
                    if (n.getTipo().equals("int")) {
                        //NUMEROS ENTEROS
                        return new Resultado("int", "", false, false, n.getDatos1(), "");
                    } else if (n.getTipo().equals("float")) {
                        //NUMEROS FLOTANTES
                        return new Resultado("float", "", false, false, n.getDatos1(), "");
                    } else if (n.getTipo().equals("boolean")) {
                        //BOOLEANOS
                        return new Resultado("booleanN", "", false, false, (n.getDatos1().equals("true")) ? "1" : "0", "");
                    } else if (n.getTipo().equals("char")) {
                        //CHARS
                        return new Resultado("char", "", false, false, Integer.valueOf(n.getDatos1().charAt(0)).toString(), "");
                    } else if (n.getTipo().equals("string")) {
                        //MANEJO DE CADENAS...
                        String texto = n.getDatos1();
                        String apDir = genTmp();
                        this.writeln("    " + apDir + " = " + this.punteroH + ";");
                        this.writeln("    " + nomHeap + "[" + apDir + "] = " + texto.length() + ";");
                        for (int i = 0; i < texto.length(); i++) {
                            String tmp = genTmp();
                            this.writeln("    " + tmp + " = " + this.punteroH + " + " + (i + 1) + ";");
                            this.writeln("    " + nomHeap + "[" + tmp + "] = " + ((int) texto.charAt(i)) + ";");
                        }
                        this.writeln("    " + this.punteroH + " = " + this.punteroH + " + " + (texto.length() + 1) + ";");
                        apDir = genTmp();
                        this.writeln("    " + apDir + " = " + this.punteroH + " - " + (texto.length() + 1) + ";");
                        return new Resultado("string", "", false, false, apDir, null);
                    }
                } else if (n.getEtiqueta() == NodoAST.ID) {
                    Simbolo dvar = tablaActual.obtenerSimbolo(n.getDatos1());
                    if (dvar != null) {
                        String tmp = genTmp();
                        if (dvar.isMiembro()) {
                            this.writeln("    " + tmp + " = " + punteroS + " + 0;");
                            String tmpAux = genTmp();
                            this.writeln("    " + tmpAux + " = " + nomStack + "[" + tmp + "];");
                            tmp = genTmp();
                            this.writeln("    " + tmp + " = " + tmpAux + " + " + dvar.getPosicion() + ";");
                        } else {
                            this.writeln("    " + tmp + " = " + punteroS + " + " + dvar.getPosicion() + ";");
                        }
                        return new Resultado(dvar.getTipo(), "", true, dvar.isInStack(), tmp, null);
                    } else {
                        addError(n.getLinea(), "La variable \"" + n.getDatos1() + "\", no ha sido declarada");
                    }
                    /* CASO EN EL QUE TIENE 2 HIJOS.. */
                } else if (esOperacion(n)) {
                    Resultado exp1 = generarExpresion(n.getHijos().get(0));
                    if (exp1.ap) {
                        String valor = genTmp();
                        String pila = (exp1.stack) ? nomStack : nomHeap;
                        this.writeln("    " + valor + " = " + pila + "[" + exp1.r1 + "];");
                        exp1.r1 = valor;
                    }
                    Resultado exp2 = generarExpresion(n.getHijos().get(1));
                    if (exp2.ap) {
                        String valor = genTmp();
                        String pila = (exp2.stack) ? nomStack : nomHeap;
                        this.writeln("    " + valor + " = " + pila + "[" + exp2.r1 + "];");
                        exp2.r1 = valor;
                    }
                    if (sonValidosOperacionMatematica(exp1.tipo, exp2.tipo)) {
                        String tmpDir = genTmp();
                        String tipoResultado = tipoResultadoOpMatematica(exp1.tipo, exp2.tipo);
                        if (n.getEtiqueta() == NodoAST.MAS) {
                            this.writeln("    " + tmpDir + " = " + exp1.r1 + " + " + exp2.r1 + ";");
                            return new Resultado(tipoResultado, "", false, false, tmpDir, "");
                        } else if (n.getEtiqueta() == NodoAST.MENOS) {
                            this.writeln("    " + tmpDir + " = " + exp1.r1 + " - " + exp2.r1 + ";");
                            return new Resultado(tipoResultado, "", false, false, tmpDir, "");
                        } else if (n.getEtiqueta() == NodoAST.MULTI) {
                            this.writeln("    " + tmpDir + " = " + exp1.r1 + " * " + exp2.r1 + ";");
                            return new Resultado(tipoResultado, "", false, false, tmpDir, "");
                        } else if (n.getEtiqueta() == NodoAST.DIV) {
                            this.writeln("    " + tmpDir + " = " + exp1.r1 + " / " + exp2.r1 + ";");
                            return new Resultado(tipoResultado, "", false, false, tmpDir, "");
                        } else if (n.getEtiqueta() == NodoAST.EXPO) {
                            this.writeln("    " + tmpDir + " = " + exp1.r1 + " * " + exp2.r1 + ";");
                            return new Resultado(tipoResultado, "", false, false, tmpDir, "");
                        }
                    } else if (n.getEtiqueta() == NodoAST.MAS && (exp1.tipo.equals("string") || exp2.tipo.equals("string"))) {
                        String tmpDir = genTmp();
                        this.writeln("    " + tmpDir + " = " + this.punteroH + ";");
                        String tmpTam1 = genTmp();
                        if (exp1.tipo.equals("string") && exp2.tipo.equals("string")) {
                            String tmpTam2 = genTmp();
                            String tmpTam3 = genTmp();
                            this.writeln("    " + tmpTam1 + " = " + nomHeap + "[" + exp1.r1 + "];");
                            this.writeln("    " + tmpTam2 + " = " + nomHeap + "[" + exp2.r1 + "];");
                            this.writeln("    " + tmpTam3 + " = " + tmpTam1 + " + " + tmpTam2 + ";");
                            this.writeln("    " + nomHeap + "[" + tmpDir + "] = " + tmpTam3 + ";");
                            this.writeln("    " + this.punteroH + " = " + this.punteroH + " + " + tmpTam3 + ";");
                            this.writeln("    " + this.punteroH + " = " + this.punteroH + " + 1;");
                            //**********inicio primer cadena************************
                            //iniciando el contador del tamanio...
                            String contGeneral = genTmp();
                            this.writeln("    " + contGeneral + " = 0;");
                            String cont1 = genTmp();
                            this.writeln("    " + cont1 + " = 0;");
                            //generando la etiqueta de inicio
                            String eInicio = genEtiqueta();
                            String eFin = genEtiqueta();
                            this.writeln("  " + eInicio + ":");
                            this.writeln("    if (" + cont1 + " >= " + tmpTam1 + ") goto " + eFin + ";");
                            //obteninedo el char
                            this.writeln("    " + cont1 + " = " + cont1 + " + 1;");
                            String tmpAp1 = genTmp();
                            this.writeln("    " + tmpAp1 + " = " + exp1.r1 + " + " + cont1 + ";");
                            String charPasar = genTmp();
                            this.writeln("    " + charPasar + " = " + nomHeap + "[" + tmpAp1 + "];");
                            //pasando el char a la nueva posicion
                            this.writeln("    " + contGeneral + " = " + contGeneral + " + 1;");
                            String tmpAp2 = genTmp();
                            this.writeln("    " + tmpAp2 + " = " + tmpDir + " + " + contGeneral + ";");
                            this.writeln("    " + nomHeap + "[" + tmpAp2 + "] = " + charPasar + ";");
                            this.writeln("    goto " + eInicio + ";");
                            this.writeln("  " + eFin + ":");
                            //*********fin primer cadena****************************
                            //**********inicio segunda cadena************************
                            //iniciando el contador del tamanio...
                            cont1 = genTmp();
                            this.writeln("    " + cont1 + " = 0;");
                            //generando la etiqueta de inicio
                            eInicio = genEtiqueta();
                            eFin = genEtiqueta();
                            this.writeln("  " + eInicio + ":");
                            this.writeln("    if (" + cont1 + " >= " + tmpTam2 + ") goto " + eFin + ";");
                            //obteninedo el char
                            this.writeln("    " + cont1 + " = " + cont1 + " + 1;");
                            tmpAp1 = genTmp();
                            this.writeln("    " + tmpAp1 + " = " + exp2.r1 + " + " + cont1 + ";");
                            charPasar = genTmp();
                            this.writeln("    " + charPasar + " = " + nomHeap + "[" + tmpAp1 + "];");
                            //pasando el char a la nueva posicion
                            this.writeln("    " + contGeneral + " = " + contGeneral + " + 1;");
                            tmpAp2 = genTmp();
                            this.writeln("    " + tmpAp2 + " = " + tmpDir + " + " + contGeneral + ";");
                            this.writeln("    " + nomHeap + "[" + tmpAp2 + "] = " + charPasar + ";");
                            this.writeln("    goto " + eInicio + ";");
                            this.writeln("  " + eFin + ":");
                            //*********fin segunda cadena****************************

                            return new Resultado("string", null, false, false, tmpDir, null);
                            //*********fin primer cadena****************************
                        } else if (exp1.tipo.equals("string")) {
                            if (exp2.tipo.matches("(int)|(float)|(boolean)|(char)")) {
                                String tmpTamS = genTmp();
                                tmpTam1 = genTmp();
                                this.writeln("    " + tmpTamS + " = " + nomHeap + "[" + exp1.r1 + "];");
                                this.writeln("    " + tmpTam1 + " = " + tmpTamS + " + 1;");
                                this.writeln("    " + nomHeap + "[" + tmpDir + "] = " + tmpTam1 + ";");
                                this.writeln("    " + this.punteroH + " = " + this.punteroH + " + " + tmpTam1 + ";");
                                this.writeln("    " + this.punteroH + " = " + this.punteroH + " + 2;");
                                //**********inicio primer cadena************************
                                //iniciando el contador del tamanio...
                                String contGeneral = genTmp();
                                this.writeln("    " + contGeneral + " = 0;");
                                String cont1 = genTmp();
                                this.writeln("    " + cont1 + " = 0;");
                                //generando la etiqueta de inicio
                                String eInicio = genEtiqueta();
                                String eFin = genEtiqueta();
                                this.writeln("  " + eInicio + ":");
                                this.writeln("    if (" + cont1 + " >= " + tmpTam1 + ") goto " + eFin + ";");
                                //obteninedo el char
                                this.writeln("    " + cont1 + " = " + cont1 + " + 1;");
                                String tmpAp1 = genTmp();
                                this.writeln("    " + tmpAp1 + " = " + exp1.r1 + " + " + cont1 + ";");
                                String charPasar = genTmp();
                                this.writeln("    " + charPasar + " = " + nomHeap + "[" + tmpAp1 + "];");
                                //pasando el char a la nueva posicion
                                this.writeln("    " + contGeneral + " = " + contGeneral + " + 1;");
                                String tmpAp2 = genTmp();
                                this.writeln("    " + tmpAp2 + " = " + tmpDir + " + " + contGeneral + ";");
                                this.writeln("    " + nomHeap + "[" + tmpAp2 + "] = " + charPasar + ";");
                                this.writeln("    goto " + eInicio + ";");
                                this.writeln("  " + eFin + ":");
                                //*********fin primer cadena****************************
                                tmpAp2 = genTmp();
                                this.writeln("    " + tmpAp2 + " = " + tmpDir + " + " + contGeneral + ";");
                                this.writeln("    " + nomHeap + "[" + tmpAp2 + "] = " + exp2.r1 + ";");
                                return new Resultado("string", null, false, false, tmpDir, null);
                            } else {
                                addError(n.getLinea(), "Error. El operador + no puede ser aplicado a tipos no basicos.");
                            }
                        } else if (exp2.tipo.equals("string")) {
                            if (exp1.tipo.matches("(int)|(float)|(boolean)|(char)")) {
                                String tmpTamS = genTmp();
                                tmpTam1 = genTmp();
                                this.writeln("    " + tmpTamS + " = " + nomHeap + "[" + exp2.r1 + "];");
                                this.writeln("    " + tmpTam1 + " = " + tmpTamS + " + 1;");
                                this.writeln("    " + nomHeap + "[" + tmpDir + "] = " + tmpTam1 + ";");
                                this.writeln("    " + this.punteroH + " = " + this.punteroH + " + " + tmpTam1 + ";");
                                this.writeln("    " + this.punteroH + " = " + this.punteroH + " + 2;");

                                String contGeneral = genTmp();
                                this.writeln("    " + contGeneral + " = 1;");
                                String tmpAp2 = genTmp();
                                this.writeln("    " + tmpAp2 + " = " + tmpDir + " + " + contGeneral + ";");
                                this.writeln("    " + nomHeap + "[" + tmpAp2 + "] = " + exp1.r1 + ";");
                                //**********inicio segunda cadena************************
                                //iniciando el contador del tamanio...
                                String cont1 = genTmp();
                                this.writeln("    " + cont1 + " = 0;");
                                //generando la etiqueta de inicio
                                String eInicio = genEtiqueta();
                                String eFin = genEtiqueta();
                                this.writeln("  " + eInicio + ":");
                                this.writeln("    if (" + cont1 + " >= " + tmpTam1 + ") goto " + eFin + ";");
                                //obteninedo el char
                                this.writeln("    " + cont1 + " = " + cont1 + " + 1;");
                                String tmpAp1 = genTmp();
                                this.writeln("    " + tmpAp1 + " = " + exp2.r1 + " + " + cont1 + ";");
                                String charPasar = genTmp();
                                this.writeln("    " + charPasar + " = " + nomHeap + "[" + tmpAp1 + "];");
                                //pasando el char a la nueva posicion
                                tmpAp2 = genTmp();
                                this.writeln("    " + tmpAp2 + " = " + tmpDir + " + " + contGeneral + ";");
                                this.writeln("    " + nomHeap + "[" + tmpAp2 + "] = " + charPasar + ";");
                                this.writeln("    goto " + eInicio + ";");
                                this.writeln("  " + eFin + ":");
                                //*********fin segunda cadena****************************
                                return new Resultado("string", null, false, false, tmpDir, null);
                            } else {
                                addError(n.getLinea(), "Error. El operador + no puede ser aplicado a tipos no basicos.");
                            }
                        }
                    }
                } else if (n.getEtiqueta() == NodoAST.NEW) {
                    if (clasesCargadas.containsKey(n.getDatos1())) {
                        return genLlamarFuncion(n, true, null);
                    } else {
                        addError(n.getLinea(), "Error, la clase \"" + n.getDatos1() + "\" no ha sido importada o no existe.");
                    }
                } else if (n.getEtiqueta() == NodoAST.INCREMENTO || n.getEtiqueta() == NodoAST.DECREMENTO) {
                    Resultado res = generarExpresion(n.getHijos().get(0));
                    if (res != null) {
                        if (res.tipo.equals("int") || res.tipo.equals("float")) {
                            if (res.ap) {
                                String pila = (res.stack) ? nomStack : nomHeap;
                                String tmp1 = genTmp();
                                this.writeln("    " + tmp1 + " = " + pila + "[" + res.r1 + "];");
                                String tmp2 = genTmp();
                                if (n.getEtiqueta() == NodoAST.INCREMENTO) {
                                    this.writeln("    " + tmp2 + " = " + tmp1 + " + 1;");
                                } else {
                                    this.writeln("    " + tmp2 + " = " + tmp1 + " - 1;");
                                }
                                this.writeln("    " + pila + "[" + res.r1 + "] = " + tmp2 + ";");
                                String tmp3 = genTmp();
                                this.writeln("    " + tmp3 + " = " + pila + "[" + res.r1 + "];");
                                return new Resultado(res.tipo, "", false, res.stack, tmp3, null);
                            } else {
                                addError(n.getLinea(), "El operador de incremento no pudo ser aplicado al tipo: \"" + res.tipo + "\"");
                            }
                        } else {
                            addError(n.getLinea(), "El operador de incremento solo puede aplicarse a variables de tipo int y float");
                        }
                    }
                } else if (n.getEtiqueta() == NodoAST.COMPARACION) {
                    Resultado exp1 = generarExpresion(n.getHijos().get(0));
                    if (exp1.ap) {
                        String valor = genTmp();
                        String pila = (exp1.stack) ? nomStack : nomHeap;
                        this.writeln("    " + valor + " = " + pila + "[" + exp1.r1 + "];");
                        exp1.r1 = valor;
                    }
                    Resultado exp2 = generarExpresion(n.getHijos().get(1));
                    if (exp2.ap) {
                        String valor = genTmp();
                        String pila = (exp2.stack) ? nomStack : nomHeap;
                        this.writeln("    " + valor + " = " + pila + "[" + exp2.r1 + "];");
                        exp2.r1 = valor;
                    }
                    if (esTipoBasicoComparacion(exp1.tipo) && esTipoBasicoComparacion(exp2.tipo)) {
                        String lv = genEtiqueta();
                        String lf = genEtiqueta();
                        this.writeln("    if (" + exp1.r1 + " " + n.getTipo() + " " + exp2.r1 + ") goto " + lv + ";");
                        this.writeln("    goto " + lf + ";");
                        return new Resultado("boolean", "", false, false, lv, lf);
                    } else {
                        addError(n.getLinea(), "Error. no es posible utilizar operadores relacionales con los tipos: \"" + exp1.tipo + "\" y \"" + exp2.tipo + "\"");
                    }
                } else if (n.getEtiqueta() == NodoAST.OR) {
                    Resultado exp1 = generarExpresion(n.getHijos().get(0));
                    verificarBooleanNormalParaCondicion(exp1);
                    if (exp1.tipo.equals("boolean")) {
                        this.writeln("  " + exp1.r2 + ":");
                        Resultado exp2 = generarExpresion(n.getHijos().get(1));
                        verificarBooleanNormalParaCondicion(exp2);
                        if (exp2.tipo.equals("boolean")) {
                            exp2.r1 = exp1.r1 + ":\n  " + exp2.r1;
                            return exp2;
                        } else {
                            addError(n.getLinea(), "Error, se esperaba dos operandos booleanos para el operador ||, se encontro: " + exp1.tipo + ", " + exp2.tipo);
                        }
                    } else {
                        addError(n.getLinea(), "Error, se esperaba dos operandos booleanos para el operador ||, se encontro: " + exp1.tipo);
                    }
                } else if (n.getEtiqueta() == NodoAST.AND) {
                    Resultado exp1 = generarExpresion(n.getHijos().get(0));
                    verificarBooleanNormalParaCondicion(exp1);
                    if (exp1.tipo.equals("boolean")) {
                        this.writeln("  " + exp1.r1 + ":");
                        Resultado exp2 = generarExpresion(n.getHijos().get(1));
                        verificarBooleanNormalParaCondicion(exp2);
                        if (exp2.tipo.equals("boolean")) {
                            exp2.r2 = exp1.r2 + ":\n  " + exp2.r2;
                            return exp2;
                        } else {
                            addError(n.getLinea(), "Error, se esperaba dos operandos booleanos para el operador &&, se encontro: " + exp1.tipo + ", " + exp2.tipo);
                        }
                    } else {
                        addError(n.getLinea(), "Error, se esperaba dos operandos booleanos para el operador &&, se encontro: " + exp1.tipo);
                    }
                } else if (n.getEtiqueta() == NodoAST.NOT) {
                    Resultado exp1 = generarExpresion(n.getHijos().get(0));
                    verificarBooleanNormalParaCondicion(exp1);
                    if (exp1 != null && exp1.tipo.equals("boolean")) {
                        String tempL = exp1.r1;
                        exp1.r1 = exp1.r2;
                        exp1.r2 = tempL;
                        return exp1;
                    }
                } else if (n.getEtiqueta() == NodoAST.THIS) {
                    String tmp = genTmp();
                    this.writeln("    " + tmp + " = " + punteroS + " + 0;");
                    String tmpAux = genTmp();
                    this.writeln("    " + tmpAux + " = " + nomStack + "[" + tmp + "];");
                    return new Resultado(claseActual.getNombre(), "", false, false, tmpAux, null);
                } else if (n.getEtiqueta() == NodoAST.PUNTO) {
                    Resultado exp1 = generarExpresion(n.getHijos().get(0));
                    if (clasesCargadas.containsKey(exp1.tipo)) {
                        if (exp1.ap) {
                            String valor = genTmp();
                            String pila = (exp1.stack) ? nomStack : nomHeap;
                            this.writeln("    " + valor + " = " + pila + "[" + exp1.r1 + "];");
                            exp1.r1 = valor;
                        }
                        if (n.getHijos().get(1).getEtiqueta() == NodoAST.ID) {
                            Simbolo sim = clasesCargadas.get(exp1.tipo).getTablasSimbolos().getLast().get(n.getHijos().get(1).getDatos1());
                            if (sim != null) {
                                String tmp = genTmp();
                                this.writeln("    " + tmp + " = " + exp1.r1 + " + " + sim.getPosicion() + ";");
                                return new Resultado(sim.getTipo(), "", true, false, tmp, null);
                            } else {
                                addError(n.getLinea(), "Error, la clase \"" + exp1.tipo + "\" no contiene un atributo llamado: " + n.getHijos().get(1).getDatos1());
                            }
                        } else if (n.getHijos().get(1).getEtiqueta() == NodoAST.LLAMARFUNCION) {
                            genLlamarFuncion(n.getHijos().get(1), true, exp1.r1);
                        }
                    } else {
                        addError(n.getLinea(), "Error, no se puede realizar la operacion . al tipo: " + exp1.tipo);
                    }
                } else if (n.getEtiqueta() == NodoAST.LLAMARFUNCION) {
                    if (n.getHijos().size() > 0 && n.getHijos().get(0).getEtiqueta() == NodoAST.VALORES) {
                        n.getHijos().addAll(n.getHijos().remove(0).getHijos());
                    }
                    return genLlamarFuncion(n, false, null);
                } else if (n.getEtiqueta() == NodoAST.CASTEO) {
                    Resultado exp1 = generarExpresion(n.getHijos().get(0));
                    if (exp1 != null && esCompatible(n.getTipo(), exp1.tipo)) {
                        exp1.tipo = n.getTipo();
                        return exp1;
                    } else {
                        addError(n.getLinea(), "Error, El tipo \"" + ((exp1 != null) ? exp1.tipo : "null") + "\" no puede ser casteado al tipo: " + n.getTipo());
                    }
                }
            } catch (NullPointerException ex) {
                //alguna de las validaciones dio como resultado un null
                Logger.getLogger(CompiladorLenguaje.class.getName()).log(Level.SEVERE, null, ex);
                addError(n.getLinea(), "Sucedio un error en la ejecucion de la expresion.");
            }
        }
        return null;
    }

    public boolean esCompatible(String tipo1, String tipo2) {
        if (tipo1 != null && tipo2 != null) {
            if (tipo1.equals("int")) {
                return tipo2.equals("int") || tipo2.equals("float") || tipo2.equals("char");
            } else if (tipo1.equals("float")) {
                return tipo2.equals("int") || tipo2.equals("float") || tipo2.equals("char");
            } else if (clasesCargadas.containsKey(tipo2) && clasesCargadas.containsKey(tipo1) && esClasePadre(tipo1, tipo2)) {
                return true;
            } else if (tipo1.equals(tipo2)) {
                return true;
            } else if (tipo1.equals("boolean") && tipo2.equals("booleanN")) {
                return true;
            }
        }
        return false;
    }

    public boolean esClasePadre(String tipo1, String tipo2) {
        Clase claseTemp = clasesCargadas.get(tipo2);
        while (claseTemp != null && claseTemp.getPadre() != null) {
            if (claseTemp.getPadre().equals(tipo1)) {
                return true;
            } else {
                claseTemp = clasesCargadas.get(claseTemp.getPadre());
            }
        }
        return false;
    }

    public boolean esOperacion(NodoAST n) {
        return n.getEtiqueta() == NodoAST.MAS || n.getEtiqueta() == NodoAST.MENOS || n.getEtiqueta() == NodoAST.MULTI
                || n.getEtiqueta() == NodoAST.DIV || n.getEtiqueta() == NodoAST.EXPO;
    }

    public boolean sonValidosOperacionMatematica(String tipo1, String tipo2) {
        return (tipo1.equals("int") || tipo1.equals("float") || tipo1.equals("char"))
                && (tipo2.equals("int") || tipo2.equals("float") || tipo2.equals("char"));
    }

    public String tipoResultadoOpMatematica(String tipo1, String tipo2) {
        if (tipo1.equals("float") || tipo2.equals("float")) {
            return "float";
        } else {
            return "int";
        }
    }

    public boolean esTipoBasicoComparacion(String tipo) {
        return tipo.equals("int") || tipo.equals("float") || tipo.equals("char");
    }

    private void verificarBooleanNormalParaCondicion(Resultado exp) {
        if (exp != null) {
            if (exp.tipo.equals("boolean") && exp.ap) {
                String valor = genTmp();
                String pila = (exp.stack) ? nomStack : nomHeap;
                this.writeln("    " + valor + " = " + pila + "[" + exp.r1 + "];");
                exp.r1 = valor;
                exp.tipo = "booleanN";
            }
            if (exp.tipo.equals("booleanN")) {
                String lv = genEtiqueta();
                String lf = genEtiqueta();
                this.writeln("    if (" + exp.r1 + " == 1) goto " + lv + ";");
                this.writeln("    goto " + lf + ";");
                exp.tipo = "boolean";
                exp.r1 = lv;
                exp.r2 = lf;
            }
        }
    }

}
