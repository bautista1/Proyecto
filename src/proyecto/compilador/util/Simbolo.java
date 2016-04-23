/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.compilador.util;

/**
 *
 * @author Luis Fernando Leiva
 */
public class Simbolo {

    private String nombre;
    private String tipo;
    private String subtipo;
    private int posicion;
    private boolean isPublic;
    private Object moreInfo;
    private Object moreInfo2;
    private Object moreInfo3;
    private boolean inStack;
    private boolean miembro;

    public Simbolo(String nombre, String tipo, boolean isPublic, boolean inStack, boolean miembro) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.isPublic = isPublic;
        this.inStack = inStack;
        this.miembro = miembro;
    }

    public Simbolo(String nombre, String tipo, String subtipo, boolean isPublic, boolean inStack, boolean miembro) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.subtipo = subtipo;
        this.isPublic = isPublic;
        this.inStack = inStack;
        this.miembro = miembro;
    }

    public Simbolo(String nombre, String tipo, String subtipo, boolean isPublic, boolean inStack, boolean miembro, Object moreInfo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.subtipo = subtipo;
        this.isPublic = isPublic;
        this.inStack = inStack;
        this.miembro = miembro;
        this.moreInfo = moreInfo;
    }

    public Simbolo(String nombre, String tipo, String subtipo, boolean isPublic, boolean inStack, boolean miembro, Object moreInfo, Object moreInfo2) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.subtipo = subtipo;
        this.isPublic = isPublic;
        this.inStack = inStack;
        this.miembro = miembro;
        this.moreInfo = moreInfo;
        this.moreInfo2 = moreInfo2;
    }
    
    public Simbolo(String nombre, String tipo, String subtipo, boolean isPublic, boolean inStack, boolean miembro, Object moreInfo, Object moreInfo2, Object moreInfo3) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.subtipo = subtipo;
        this.isPublic = isPublic;
        this.inStack = inStack;
        this.miembro = miembro;
        this.moreInfo = moreInfo;
        this.moreInfo2 = moreInfo2;
        this.moreInfo3 = moreInfo3;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSubtipo() {
        return subtipo;
    }

    public void setSubtipo(String subtipo) {
        this.subtipo = subtipo;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public Object getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(Object moreInfo) {
        this.moreInfo = moreInfo;
    }

    public Object getMoreInfo2() {
        return moreInfo2;
    }

    public void setMoreInfo2(Object moreInfo2) {
        this.moreInfo2 = moreInfo2;
    }

    public Object getMoreInfo3() {
        return moreInfo3;
    }

    public void setMoreInfo3(Object moreInfo3) {
        this.moreInfo3 = moreInfo3;
    }

    public boolean isInStack() {
        return inStack;
    }

    public void setInStack(boolean inStack) {
        this.inStack = inStack;
    }

    public boolean isMiembro() {
        return miembro;
    }

    public void setMiembro(boolean miembro) {
        this.miembro = miembro;
    }

    public String getParametrosInfo() {
        if (moreInfo != null && this.getTipo().equals("metodo")) {
            String salida = "Parametros:";
            for (String param : moreInfo.toString().split(";;;")) {
                String[] paramdata = param.split(":::");
                if (true) {
                    salida += " - " + paramdata[1] + ":" + paramdata[0]; 
                }
            }
            return salida;
        }
        return "";
    }

    public int getTamanio() {
        if (this.getTipo().equals("float")) {
            return 2;
        } else if (this.getTipo().equals("metodo")) {
            if (moreInfo == null) {
                return 0;
            } else {
                int conteo = 0;
                for (String param : moreInfo.toString().split(";;;")) {
                    if (param.split(":::")[0].equals("float")) {
                        conteo += 2;
                    } else {
                        conteo++;
                    }
                }
                return conteo;
            }
        } else {
            return 1;
        }
    }

}
