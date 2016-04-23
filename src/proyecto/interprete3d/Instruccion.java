/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.interprete3d;

/**
 *
 * @author Luis Fernando Leiva
 */
public class Instruccion {
    
    public int linea;
    public String ref1, ref2, ref3, ref4, ref5;
    public boolean enUso = true;

    public Instruccion(int linea, String ref1) {
        this.linea = linea;
        this.ref1 = ref1;
    }

    public Instruccion(int linea, String ref1, String ref2) {
        this.linea = linea;
        this.ref1 = ref1;
        this.ref2 = ref2;
    }

    public Instruccion(int linea, String ref1, String ref2, String ref3) {
        this.linea = linea;
        this.ref1 = ref1;
        this.ref2 = ref2;
        this.ref3 = ref3;
    }

    public Instruccion(int linea, String ref1, String ref2, String ref3, String ref4) {
        this.linea = linea;
        this.ref1 = ref1;
        this.ref2 = ref2;
        this.ref3 = ref3;
        this.ref4 = ref4;
    }

    public Instruccion(int linea, String ref1, String ref2, String ref3, String ref4, String ref5) {
        this.linea = linea;
        this.ref1 = ref1;
        this.ref2 = ref2;
        this.ref3 = ref3;
        this.ref4 = ref4;
        this.ref5 = ref5;
    }
    
    public boolean isAsignacion(){
        return (this.ref1.matches("t[0-9]*")||this.ref1.equals("stack") ||this.ref1.equals("heap")||this.ref1.equals("m")||this.ref1.equals("p"));
    }
    
    public boolean isIf(){
        return this.ref1.equals("if");
    }
    
    public boolean isLabel(){
        return this.ref1.matches("L[0-9]*");
    }
    
    public boolean isPrinf(){
        return this.ref1.equals("printf");
    }
    
    public boolean isReturn(){
        return this.ref1.equals("return");
    }
    
    public boolean isGoto(){
        return this.ref1.equals("goto");
    }
    
    public boolean isFuncion(){
        return this.ref1.equals("funcion");
    }
    
    public static boolean isOpMat(String ref){
        if (ref != null) return ref.matches("'+'|'-'|'*'|'/'|'%'");
        return false;
    }
    
    public static boolean isNumber(String ref){
        if (ref != null) return ref.matches("([0-9]+)|([0-9]+.[0-9]+)");
        return false;
    }

    public static boolean isTemp(String ref){
        return ref.matches("t[0-9]*");
    }
    
    @Override
    public String toString(){
        if (isAsignacion()) {
            if (refsUsed() == 3) {
                if (ref1.equals("stack")||ref1.equals("heap")) {
                    return "    "+ref1+"["+ref2+"] = "+ref3+";";
                } else {
                    return "    "+ref1+" = "+ref2+"["+ref3+"];";
                }
            } else if (refsUsed() == 2){
                return "    "+ref1+" = "+ref2+";";
            } else if (refsUsed() == 4){
                return "    "+ref1+" = "+ref2+" "+ref3+" "+ref4+";";
            }
        } else if (isFuncion()) {
            return "    call "+ref2+"();";
        } else if (isGoto()){
            return "    "+ref1+" "+ref2+";";
        } else if (isIf()){
            return "    "+ref1+" ("+ref2+" "+ref3+" "+ref4+") goto "+ref5+";";
        } else if (isLabel()){
            return "  "+ref1+":";
        } else if (isPrinf()){
            return "    printf(\""+ref2+"\", "+ref3+");";
        } else if(isReturn()){
            return "    return;";
        }
        return "retorno nuuuulo ::: "+ref1+";"+ref2+";"+ref3+";"+ref4+";"+ref5;
    }
    
    public int refsUsed(){
        int cont = 0;
        if (ref1 != null) cont++;
        if (ref2 != null) cont++;
        if (ref3 != null) cont++;
        if (ref4 != null) cont++;
        if (ref5 != null) cont++;
        return cont;
    }
    
    public boolean apuntadoresDifs(){
        if (isAsignacion()) {
            if (refsUsed() == 4) {
                return !(ref1.equals(ref2)||ref2.equals(ref4)||ref1.equals(ref5));
            } else if (refsUsed() == 3){
                if (ref1.equals("heap")||ref1.equals("stack")) {
                    return !(ref2.equals(ref3));
                } else {
                    return !(ref1.equals(ref3));
                }
            } else if (refsUsed() == 2){
                return !(ref1.equals(ref2));
            }
        }
        return false;
    }

    public boolean isEnUso() {
        return enUso;
    }

    public void setEnUso(boolean enUso) {
        this.enUso = enUso;
    }
    
    
    
}
