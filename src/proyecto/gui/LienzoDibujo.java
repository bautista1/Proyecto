/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import proyecto.interprete3d.Interprete;

/**
 *
 * @author Luis Fernando Leiva
 */
public class LienzoDibujo extends javax.swing.JDialog {

    private String archivo;
    private String pathImagen;
    private LinkedList<FiguraLienzo> figuras;
    private Lienzo lienzo;
    private Color colorFondo;

    /**
     * Creates new form LienzoDibujo
     *
     * @param parent
     * @param modal
     * @param archivo
     */
    public LienzoDibujo(java.awt.Frame parent, boolean modal, String archivo) {
        super(parent, modal);
        initComponents();

        figuras = new LinkedList<FiguraLienzo>();

        this.archivo = archivo;
        this.pathImagen = archivo;
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Imagen a guardar...");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "PNG Images", "png");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showSaveDialog(parent);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            this.pathImagen = chooser.getSelectedFile().getPath();
        }

        lienzo = new Lienzo();
        this.jpLienzo.add(lienzo);
        lienzo.setBounds(0, 0, 400, 400);
        colorFondo = new Color(255, 255, 255);
    }

    public class FiguraLienzo {

        private Shape figura;
        private Color color;
        private boolean fill;
        private boolean isOval;
        private boolean isTexto;
        private boolean isPoligono;
        private String texto;
        private int[] puntos;

        public FiguraLienzo(Shape figura, Color color, boolean fill) {
            this.figura = figura;
            this.color = color;
            this.fill = fill;
        }

        public Shape getFigura() {
            return figura;
        }

        public void setFigura(Shape figura) {
            this.figura = figura;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public boolean isFill() {
            return fill;
        }

        public void setFill(boolean fill) {
            this.fill = fill;
        }

        public boolean isIsOval() {
            return isOval;
        }

        public void setIsOval(boolean isOval) {
            this.isOval = isOval;
        }

        public int[] getPuntos() {
            return puntos;
        }

        public void setPuntos(int[] puntos) {
            this.puntos = puntos;
        }

        public boolean isIsTexto() {
            return isTexto;
        }

        public void setIsTexto(boolean isTexto) {
            this.isTexto = isTexto;
        }

        public String getTexto() {
            return texto;
        }

        public void setTexto(String texto) {
            this.texto = texto;
        }

        public boolean isIsPoligono() {
            return isPoligono;
        }

        public void setIsPoligono(boolean isPoligono) {
            this.isPoligono = isPoligono;
        }

    }

    public class Lienzo extends Canvas {

        @Override
        public void paint(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            //dibujando el fondo...
            g2d.setColor(colorFondo);
            g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
            //pintando las figuras...
            for (FiguraLienzo figura : figuras) {
                if (figura.isOval) {
                    g2d.setColor(figura.getColor());
                    g2d.drawOval(figura.getPuntos()[0], figura.getPuntos()[1], figura.getPuntos()[2], figura.getPuntos()[3]);
                    if (figura.isFill()) {
                        g.fillOval(figura.getPuntos()[0], figura.getPuntos()[1], figura.getPuntos()[2], figura.getPuntos()[3]);
                    }
                } else if (figura.isTexto) {
                    g2d.setColor(figura.getColor());
                    g2d.drawString(figura.getTexto(), figura.getPuntos()[0], figura.getPuntos()[1]);
                } else if (figura.isPoligono) {

                } else {
                    g2d.setColor(figura.getColor());
                    g2d.draw(figura.getFigura());
                    if (figura.isFill()) {
                        g2d.fill(figura.getFigura());
                    }
                }
            }
        }

        public void exportImage(String imageName) {
            BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = image.createGraphics();
            paint(graphics);
            try {
                FileOutputStream out = new FileOutputStream(imageName);
                ImageIO.write(image, "png", out);
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            graphics.dispose();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpLienzo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtpConsola = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jpLienzo.setBackground(new java.awt.Color(255, 255, 255));
        jpLienzo.setPreferredSize(new java.awt.Dimension(400, 400));

        javax.swing.GroupLayout jpLienzoLayout = new javax.swing.GroupLayout(jpLienzo);
        jpLienzo.setLayout(jpLienzoLayout);
        jpLienzoLayout.setHorizontalGroup(
            jpLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpLienzoLayout.setVerticalGroup(
            jpLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        jtpConsola.setEditable(false);
        jScrollPane1.setViewportView(jtpConsola);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                    .addComponent(jpLienzo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpLienzo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        File arch = new File(archivo);
        if (this.archivo != null && arch.exists()) {
            Interprete interprete = new Interprete(this);
            interprete.interpretar(arch);
            lienzo.exportImage(pathImagen + ".png");
        } else {
            JOptionPane.showMessageDialog(this, "El archivo no existe o es invalido: " + archivo);
        }
    }//GEN-LAST:event_formWindowOpened

    public void printInConsole(boolean isChar, int val) {
        if (isChar) {
            jtpConsola.setText(jtpConsola.getText() + ((char) val));
        } else {
            jtpConsola.setText(jtpConsola.getText() + val);
        }
    }

    public void printInConsole(boolean isChar, float val) {
        if (isChar) {
            jtpConsola.setText(jtpConsola.getText() + ((char) val));
        } else {
            jtpConsola.setText(jtpConsola.getText() + val);
        }
    }

    public void printInConsole(String string) {
        jtpConsola.setText(jtpConsola.getText() + string + "\n");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpLienzo;
    private javax.swing.JTextPane jtpConsola;
    // End of variables declaration//GEN-END:variables

    public void dibujarLinea(int x1, int y1, int x2, int y2, int r, int g, int b) {
        figuras.add(new FiguraLienzo(new Line2D.Float(x1, y1, x2, y2), new Color(r, g, b), false));
        lienzo.repaint();
    }

    public void dibujarArco(int x, int y, int alto, int ancho, int angInit, int grados, int r, int g, int b, int fill) {
        figuras.add(new FiguraLienzo(new Arc2D.Float(x, y, ancho, alto, angInit, grados, Arc2D.OPEN), new Color(r, g, b), fill == 1));
        lienzo.repaint();
    }

    public void dibujarRectangulo(int x, int y, int alto, int ancho, int r, int g, int b, int fill) {
        figuras.add(new FiguraLienzo(new Rectangle2D.Float(x, y, ancho, alto), new Color(r, g, b), fill == 1));
        lienzo.repaint();
    }

    public void dibujarOvalo(int x, int y, int ancho, int alto, int r, int g, int b, int fill) {
        int[] puntos = {x, y, ancho, alto};
        FiguraLienzo figura = new FiguraLienzo(null, new Color(r, g, b), fill == 1);
        figura.setIsOval(true);
        figura.setPuntos(puntos);
        figuras.add(figura);
        lienzo.repaint();
    }

    public void dibujarTexto(String texto, int x, int y, int r, int g, int b) {
        int[] puntos = {x, y};
        FiguraLienzo figura = new FiguraLienzo(null, new Color(r, g, b), false);
        figura.setIsTexto(true);
        figura.setTexto(texto);
        figura.setPuntos(puntos);
        figuras.add(figura);
        lienzo.repaint();
    }

    public void cambiarLienzo(int ancho, int alto, int r, int g, int b) {
        colorFondo = new Color(r, g, b);
        int diferenciax = ancho - this.jpLienzo.getWidth();
        int diferenciay = alto - this.jpLienzo.getHeight();
        this.setSize(this.jpLienzo.getWidth() + diferenciax, this.jpLienzo.getHeight() + diferenciay);
        this.lienzo.setSize(ancho, alto);
        lienzo.repaint();
    }

}
