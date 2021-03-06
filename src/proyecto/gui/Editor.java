/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.gui;

import java.awt.event.MouseEvent;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import proyecto.compilador.lenguaje.CompiladorLenguaje;
import proyecto.gui.dialog.MostrarErrores;
import proyecto.gui.dialog.MostrarTablaSimbolos;
import proyecto.gui.dialog.NuevoArchivoDialog;
import proyecto.gui.dialog.NuevoProyectoDialog;
import proyecto.interprete3d.Interprete;
import proyecto.optimizador.Optimizador;
import proyecto.util.GestorArchivos;

/**
 *
 * @author Luis Fernando Leiva
 */
public class Editor extends javax.swing.JFrame {

    private final List<ProyectoAbierto> proyectosAbiertos;
    private final List<ArchivoAbierto> archivosAbiertos;

    /**
     * Creates new form A
     */
    public Editor() {
        initComponents();
        proyectosAbiertos = new LinkedList<>();
        archivosAbiertos = new LinkedList<>();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpmTabPanel = new javax.swing.JPopupMenu();
        jmiPopUPGuardar = new javax.swing.JMenuItem();
        jmiPopUpCerrarPestania = new javax.swing.JMenuItem();
        jpmArbolProyectos = new javax.swing.JPopupMenu();
        jmiPopUpAbrirProyecto = new javax.swing.JMenuItem();
        jmiPopUpCerrarProyecto = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jmiPopUpEliminarArchivo = new javax.swing.JMenuItem();
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtProyectos = new javax.swing.JTree();
        jtpTextos = new javax.swing.JTabbedPane();
        jmbBarraPrincipal = new javax.swing.JMenuBar();
        jmArchivo = new javax.swing.JMenu();
        jmNuevo = new javax.swing.JMenu();
        jmiNuevoArchivo = new javax.swing.JMenuItem();
        jmiNuevoProyecto = new javax.swing.JMenuItem();
        jmiAbrir = new javax.swing.JMenuItem();
        jmiGuardar = new javax.swing.JMenuItem();
        jmiGuardarTodos = new javax.swing.JMenuItem();
        jmiSalir = new javax.swing.JMenuItem();
        jmCompilar = new javax.swing.JMenu();
        jmiTablaSimb = new javax.swing.JMenuItem();
        jmiGenCod = new javax.swing.JMenuItem();
        jmiEjecutar = new javax.swing.JMenuItem();
        jmiEjecutarArch3d = new javax.swing.JMenuItem();
        jmiOptimizar = new javax.swing.JMenuItem();
        jmErrores = new javax.swing.JMenu();
        jmiLexicos = new javax.swing.JMenuItem();
        jmiSintacticos = new javax.swing.JMenuItem();
        jmiSemanticos = new javax.swing.JMenuItem();

        jpmTabPanel.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                jpmTabPanelPopupMenuWillBecomeVisible(evt);
            }
        });

        jmiPopUPGuardar.setText("Guardar Pestaña");
        jmiPopUPGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiPopUPGuardarActionPerformed(evt);
            }
        });
        jpmTabPanel.add(jmiPopUPGuardar);

        jmiPopUpCerrarPestania.setText("Cerrar Pestaña");
        jmiPopUpCerrarPestania.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiPopUpCerrarPestaniaActionPerformed(evt);
            }
        });
        jpmTabPanel.add(jmiPopUpCerrarPestania);

        jpmArbolProyectos.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                jpmArbolProyectosPopupMenuWillBecomeVisible(evt);
            }
        });

        jmiPopUpAbrirProyecto.setText("Abrir proyecto");
        jmiPopUpAbrirProyecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiPopUpAbrirProyectoActionPerformed(evt);
            }
        });
        jpmArbolProyectos.add(jmiPopUpAbrirProyecto);

        jmiPopUpCerrarProyecto.setText("Cerrar Proyecto");
        jmiPopUpCerrarProyecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiPopUpCerrarProyectoActionPerformed(evt);
            }
        });
        jpmArbolProyectos.add(jmiPopUpCerrarProyecto);
        jpmArbolProyectos.add(jSeparator1);

        jmiPopUpEliminarArchivo.setText("Eliminar Archivo");
        jmiPopUpEliminarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiPopUpEliminarArchivoActionPerformed(evt);
            }
        });
        jpmArbolProyectos.add(jmiPopUpEliminarArchivo);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Proyectos");
        jtProyectos.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jtProyectos.setComponentPopupMenu(jpmArbolProyectos);
        jtProyectos.setShowsRootHandles(true);
        jtProyectos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtProyectosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jtProyectos);

        jSplitPane1.setLeftComponent(jScrollPane2);

        jtpTextos.setComponentPopupMenu(jpmTabPanel);
        jSplitPane1.setRightComponent(jtpTextos);

        jmArchivo.setText("Archivo");

        jmNuevo.setText("Nuevo");

        jmiNuevoArchivo.setText("Archivo");
        jmiNuevoArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiNuevoArchivoActionPerformed(evt);
            }
        });
        jmNuevo.add(jmiNuevoArchivo);

        jmiNuevoProyecto.setText("Proyecto");
        jmiNuevoProyecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiNuevoProyectoActionPerformed(evt);
            }
        });
        jmNuevo.add(jmiNuevoProyecto);

        jmArchivo.add(jmNuevo);

        jmiAbrir.setText("Abrir");
        jmiAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiAbrirActionPerformed(evt);
            }
        });
        jmArchivo.add(jmiAbrir);

        jmiGuardar.setText("Guardar");
        jmiGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiGuardarActionPerformed(evt);
            }
        });
        jmArchivo.add(jmiGuardar);

        jmiGuardarTodos.setText("Guardar Todos");
        jmiGuardarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiGuardarTodosActionPerformed(evt);
            }
        });
        jmArchivo.add(jmiGuardarTodos);

        jmiSalir.setText("Salir");
        jmiSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiSalirActionPerformed(evt);
            }
        });
        jmArchivo.add(jmiSalir);

        jmbBarraPrincipal.add(jmArchivo);

        jmCompilar.setText("Compilar");

        jmiTablaSimb.setText("Tabla de simbolos");
        jmiTablaSimb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiTablaSimbActionPerformed(evt);
            }
        });
        jmCompilar.add(jmiTablaSimb);

        jmiGenCod.setText("Generar Codigo");
        jmiGenCod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiGenCodActionPerformed(evt);
            }
        });
        jmCompilar.add(jmiGenCod);

        jmiEjecutar.setText("Ejecutar");
        jmiEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiEjecutarActionPerformed(evt);
            }
        });
        jmCompilar.add(jmiEjecutar);

        jmiEjecutarArch3d.setText("Ejecutar Archivo 3D");
        jmiEjecutarArch3d.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiEjecutarArch3dActionPerformed(evt);
            }
        });
        jmCompilar.add(jmiEjecutarArch3d);

        jmiOptimizar.setText("Optimizar");
        jmiOptimizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiOptimizarActionPerformed(evt);
            }
        });
        jmCompilar.add(jmiOptimizar);

        jmbBarraPrincipal.add(jmCompilar);

        jmErrores.setText("Errores");

        jmiLexicos.setText("Lexicos");
        jmiLexicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiLexicosActionPerformed(evt);
            }
        });
        jmErrores.add(jmiLexicos);

        jmiSintacticos.setText("Sintacticos");
        jmiSintacticos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiSintacticosActionPerformed(evt);
            }
        });
        jmErrores.add(jmiSintacticos);

        jmiSemanticos.setText("Semanticos");
        jmiSemanticos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiSemanticosActionPerformed(evt);
            }
        });
        jmErrores.add(jmiSemanticos);

        jmbBarraPrincipal.add(jmErrores);

        setJMenuBar(jmbBarraPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiNuevoArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiNuevoArchivoActionPerformed
        nuevoArchivo();
    }//GEN-LAST:event_jmiNuevoArchivoActionPerformed

    private void jmiNuevoProyectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiNuevoProyectoActionPerformed
        nuevoProyecto();
    }//GEN-LAST:event_jmiNuevoProyectoActionPerformed

    private void jmiAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiAbrirActionPerformed
        abrir();
    }//GEN-LAST:event_jmiAbrirActionPerformed

    private void jmiGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiGuardarActionPerformed
        guardar();
    }//GEN-LAST:event_jmiGuardarActionPerformed

    private void jmiGuardarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiGuardarTodosActionPerformed
        guardarTodos();
    }//GEN-LAST:event_jmiGuardarTodosActionPerformed

    private void jmiSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSalirActionPerformed
        salir();
    }//GEN-LAST:event_jmiSalirActionPerformed

    private void jmiTablaSimbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiTablaSimbActionPerformed
        mostrarTablaSimbolos();
    }//GEN-LAST:event_jmiTablaSimbActionPerformed

    private void jmiGenCodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiGenCodActionPerformed
        generarCodigo();
    }//GEN-LAST:event_jmiGenCodActionPerformed

    private void jmiEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiEjecutarActionPerformed
        ejecutar();
    }//GEN-LAST:event_jmiEjecutarActionPerformed

    private void jmiOptimizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiOptimizarActionPerformed
        optimizar();
    }//GEN-LAST:event_jmiOptimizarActionPerformed

    private void jmiLexicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiLexicosActionPerformed
        erroresLexicos();
    }//GEN-LAST:event_jmiLexicosActionPerformed

    private void jmiSintacticosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSintacticosActionPerformed
        erroresSintacticos();
    }//GEN-LAST:event_jmiSintacticosActionPerformed

    private void jmiSemanticosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSemanticosActionPerformed
        erroresSemanticos();
    }//GEN-LAST:event_jmiSemanticosActionPerformed

    private void jtProyectosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtProyectosMouseClicked
        //boton izquierdo y doble click
        if (evt.getButton() == MouseEvent.BUTTON1 && evt.getClickCount() == 2 && jtProyectos.getLastSelectedPathComponent() != null) {
            DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) jtProyectos.getLastSelectedPathComponent();
            //verificando que no sea el root y que no sea el root el padre, si es asi entonces es un archivo...
            if (nodo != null && nodo != jtProyectos.getModel().getRoot() && nodo.getParent() != jtProyectos.getModel().getRoot()) {
                //buscando al proyecto al que pertenece...
                int indiceProyectoAbierto = jtProyectos.getModel().getIndexOfChild(nodo.getParent().getParent(), nodo.getParent());
                abrirArchivo(nodo.getUserObject().toString(), proyectosAbiertos.get(indiceProyectoAbierto));
            }
        }
    }//GEN-LAST:event_jtProyectosMouseClicked

    private void jmiPopUpCerrarPestaniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiPopUpCerrarPestaniaActionPerformed
        cerrarPestania();
    }//GEN-LAST:event_jmiPopUpCerrarPestaniaActionPerformed

    private void jmiPopUPGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiPopUPGuardarActionPerformed
        guardar();
    }//GEN-LAST:event_jmiPopUPGuardarActionPerformed

    private void jpmTabPanelPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jpmTabPanelPopupMenuWillBecomeVisible
        if (jtpTextos.getSelectedIndex() < 0) {
            jmiPopUPGuardar.setEnabled(false);
            jmiPopUpCerrarPestania.setEnabled(false);
        } else {
            jmiPopUPGuardar.setEnabled(true);
            jmiPopUpCerrarPestania.setEnabled(true);
        }
    }//GEN-LAST:event_jpmTabPanelPopupMenuWillBecomeVisible

    private void jmiPopUpCerrarProyectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiPopUpCerrarProyectoActionPerformed
        cerrarProyecto();
    }//GEN-LAST:event_jmiPopUpCerrarProyectoActionPerformed

    private void jmiPopUpAbrirProyectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiPopUpAbrirProyectoActionPerformed
        abrir();
    }//GEN-LAST:event_jmiPopUpAbrirProyectoActionPerformed

    private void jmiPopUpEliminarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiPopUpEliminarArchivoActionPerformed
        eliminarArchivo();
    }//GEN-LAST:event_jmiPopUpEliminarArchivoActionPerformed

    private void jpmArbolProyectosPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jpmArbolProyectosPopupMenuWillBecomeVisible
        jmiPopUpEliminarArchivo.setVisible(false);
        jmiPopUpCerrarProyecto.setVisible(false);
        if (jtProyectos.getLastSelectedPathComponent() != null) {
            DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) jtProyectos.getLastSelectedPathComponent();
            if (nodo != null && nodo != jtProyectos.getModel().getRoot() && nodo.getParent() != jtProyectos.getModel().getRoot()) {
                jmiPopUpEliminarArchivo.setVisible(true);
            } else if (nodo != null && nodo.getParent() == jtProyectos.getModel().getRoot()) {
                jmiPopUpCerrarProyecto.setVisible(true);
            }
        }
    }//GEN-LAST:event_jpmArbolProyectosPopupMenuWillBecomeVisible

    private void jmiEjecutarArch3dActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiEjecutarArch3dActionPerformed
        ejecutarArchivo3D();
    }//GEN-LAST:event_jmiEjecutarArch3dActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Editor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JMenu jmArchivo;
    private javax.swing.JMenu jmCompilar;
    private javax.swing.JMenu jmErrores;
    private javax.swing.JMenu jmNuevo;
    private javax.swing.JMenuBar jmbBarraPrincipal;
    private javax.swing.JMenuItem jmiAbrir;
    private javax.swing.JMenuItem jmiEjecutar;
    private javax.swing.JMenuItem jmiEjecutarArch3d;
    private javax.swing.JMenuItem jmiGenCod;
    private javax.swing.JMenuItem jmiGuardar;
    private javax.swing.JMenuItem jmiGuardarTodos;
    private javax.swing.JMenuItem jmiLexicos;
    private javax.swing.JMenuItem jmiNuevoArchivo;
    private javax.swing.JMenuItem jmiNuevoProyecto;
    private javax.swing.JMenuItem jmiOptimizar;
    private javax.swing.JMenuItem jmiPopUPGuardar;
    private javax.swing.JMenuItem jmiPopUpAbrirProyecto;
    private javax.swing.JMenuItem jmiPopUpCerrarPestania;
    private javax.swing.JMenuItem jmiPopUpCerrarProyecto;
    private javax.swing.JMenuItem jmiPopUpEliminarArchivo;
    private javax.swing.JMenuItem jmiSalir;
    private javax.swing.JMenuItem jmiSemanticos;
    private javax.swing.JMenuItem jmiSintacticos;
    private javax.swing.JMenuItem jmiTablaSimb;
    private javax.swing.JPopupMenu jpmArbolProyectos;
    private javax.swing.JPopupMenu jpmTabPanel;
    private javax.swing.JTree jtProyectos;
    private javax.swing.JTabbedPane jtpTextos;
    // End of variables declaration//GEN-END:variables

    private void nuevoArchivo() {
        NuevoArchivoDialog dialog = new NuevoArchivoDialog(this, true, proyectosAbiertos);
        dialog.setVisible(true);
        if (dialog.isValido()) {
            abrirArchivo(dialog.getNombre(), dialog.getProyecto());
            if (dialog.getProyecto() != null) {
                actualizarArbolProyectos();
                dialog.getProyecto().escribirArchivoPj();
            }
        }
    }

    private void nuevoProyecto() {
        NuevoProyectoDialog dialog = new NuevoProyectoDialog(this, true);
        dialog.setVisible(true);
        if (dialog.isValido()) {
            String ruta = dialog.getUbicacion() + "/" + dialog.getDirectorio();
            String nombre = dialog.getNombre();
            ProyectoAbierto p = new ProyectoAbierto(nombre, ruta, ruta + "/" + nombre + ".pj");
            p.escribirArchivoPj();
            agregarProyectoAbierto(p);
        }
    }

    private void abrir() {
        String pathArchivo = GestorArchivos.abrirArchivo(this);
        if (pathArchivo != null && !pathArchivo.trim().equals("")) {
            String extension = pathArchivo.substring(pathArchivo.length() - 3, pathArchivo.length());
            if (extension.equalsIgnoreCase(".pj")) {
                ProyectoAbierto p = GestorArchivos.cargarProyectoFromArchivo(pathArchivo);
                if (p != null) {
                    agregarProyectoAbierto(p);
                } else if (GestorArchivos.errores.size() > 0) {
                    JOptionPane.showMessageDialog(this, "El archivo contiene errores.");
                }
            } else {
                abrirArchivo(pathArchivo, null);
            }
        }
    }

    private void guardar() {
        if (jtpTextos.getSelectedIndex() > -1) {
            if (!archivosAbiertos.get(jtpTextos.getSelectedIndex()).guardarArchivo()) {
                JOptionPane.showMessageDialog(this, "Error al guardar el archivo.");
            }
        }
    }

    private void guardarTodos() {
        for (ArchivoAbierto arch : archivosAbiertos) {
            if (!arch.guardarArchivo()) {
                JOptionPane.showMessageDialog(this, "Error al guardar el archivo: " + arch.getPath());
            }
        }
    }

    private void salir() {
        this.dispose();
    }

    private void mostrarTablaSimbolos() {
        compilar();
        if (CompiladorLenguaje.errores.size() > 0) {
            JOptionPane.showMessageDialog(this, "Se encontraron errores al momento de compilar.");
        }
        new MostrarTablaSimbolos(this, true).setVisible(true);
    }

    private void generarCodigo() {
        if (jtpTextos.getSelectedIndex() > -1) {
            compilar();
            CompiladorLenguaje.generarCodigo();
            if (CompiladorLenguaje.errores.size() > 0) {
                JOptionPane.showMessageDialog(this, "Se encontraron errores al momento de compilar.");
                erroresTodos();
            } else {
                JOptionPane.showMessageDialog(this, "Proceso de compilacion correcto, codigo 3D generado.");
            }
        }
    }

    private void ejecutar() {
        if (jtpTextos.getSelectedIndex() > -1) {
            compilar();
            CompiladorLenguaje.generarCodigo();
            if (CompiladorLenguaje.errores.size() > 0) {
                JOptionPane.showMessageDialog(this, "Se encontraron errores al momento de compilar.");
                erroresTodos();
            } else {
                ArchivoAbierto arch = archivosAbiertos.get(jtpTextos.getSelectedIndex());
                LienzoDibujo ld = new LienzoDibujo(this, true, arch.getPath() + ".3dr");
                ld.setVisible(true);
            }
        }
    }

    private void optimizar() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Codigo 3 direcciones (3dr)", "3dr");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            Optimizador o = new Optimizador();
            o.optimizar(chooser.getSelectedFile());
            if (Interprete.errores.size() > 0) {
                JOptionPane.showMessageDialog(this, "Se encontraron errores al momento de optimizar");
            } else {
                JOptionPane.showMessageDialog(this, "Optimización correcta");
            }
        }
    }

    private void erroresLexicos() {
        compilar();
        new MostrarErrores(this, true, "Errores Lexicos", CompiladorLenguaje.errores, "Lexico").setVisible(true);
    }

    private void erroresSintacticos() {
        compilar();
        new MostrarErrores(this, true, "Errores Sintacticos", CompiladorLenguaje.errores, "Sintactico").setVisible(true);
    }

    private void erroresSemanticos() {
        if (jtpTextos.getSelectedIndex() > -1) {
            compilar();
            CompiladorLenguaje.generarCodigo();
            new MostrarErrores(this, true, "Errores Semanticos", CompiladorLenguaje.errores, "Semantico").setVisible(true);
        }
    }

    private void erroresTodos() {
        new MostrarErrores(this, true, "Errores de Compilacion", CompiladorLenguaje.errores, "TODOS").setVisible(true);
    }

    private void agregarProyectoAbierto(ProyectoAbierto p) {
        proyectosAbiertos.add(p);
        agregarNodoProyectoAlArbol(p);
        actualizarArbolProyectos();
    }

    private void agregarNodoProyectoAlArbol(ProyectoAbierto p) {
        DefaultMutableTreeNode raiz = (DefaultMutableTreeNode) jtProyectos.getModel().getRoot();
        DefaultMutableTreeNode nuevaRama = new DefaultMutableTreeNode(p.getNombre());
        raiz.add(nuevaRama);
        for (String arch : p.getArchivos()) {
            nuevaRama.add(new DefaultMutableTreeNode(arch));
        }
        jtProyectos.repaint();
    }

    private void abrirArchivo(String nombre, ProyectoAbierto proyecto) {
        JTextPane panelTexto = null;
        JScrollPane panelScroll = null;

        String pathArchivo = (proyecto == null) ? nombre : proyecto.getRuta() + "/" + nombre;

        String texto = GestorArchivos.leerArchivo(pathArchivo);
        if (texto != null) {
            panelTexto = new JTextPane();
            panelTexto.setText(texto);
            panelScroll = new JScrollPane(panelTexto, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            archivosAbiertos.add(new ArchivoAbierto(pathArchivo, proyecto, panelTexto));
        }

        if (panelTexto == null) {
            JOptionPane.showMessageDialog(this, "Error, el archivo no existe, " + pathArchivo);
        } else {
            jtpTextos.addTab(nombre, panelScroll);
            jtpTextos.setSelectedIndex(jtpTextos.getTabCount() - 1);
        }
    }

    private void actualizarArbolProyectos() {
        DefaultMutableTreeNode nuevoRoot = new DefaultMutableTreeNode("Proyectos");
        jtProyectos.setModel(new javax.swing.tree.DefaultTreeModel(nuevoRoot));
        for (ProyectoAbierto p : proyectosAbiertos) {
            agregarNodoProyectoAlArbol(p);
        }
        jtProyectos.repaint();
    }

    private void cerrarPestania() {
        if (jtpTextos.getSelectedIndex() > -1) {
            archivosAbiertos.remove(jtpTextos.getSelectedIndex());
            jtpTextos.removeTabAt(jtpTextos.getSelectedIndex());
        }
    }

    private void cerrarProyecto() {
        if (jtProyectos.getLastSelectedPathComponent() != null) {
            int indice = jtProyectos.getModel().getIndexOfChild(jtProyectos.getModel().getRoot(), jtProyectos.getLastSelectedPathComponent());
            if (indice > -1) {
                proyectosAbiertos.remove(indice);
                actualizarArbolProyectos();
            }
        }
    }

    private void eliminarArchivo() {
        if (jtProyectos.getLastSelectedPathComponent() != null) {
            DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) jtProyectos.getLastSelectedPathComponent();
            //verificando que no sea el root y que no sea el root el padre, si es asi entonces es un archivo...
            if (nodo != null && nodo != jtProyectos.getModel().getRoot() && nodo.getParent() != jtProyectos.getModel().getRoot()) {
                //buscando al proyecto al que pertenece...
                int indiceProyectoAbierto = jtProyectos.getModel().getIndexOfChild(nodo.getParent().getParent(), nodo.getParent());
                if (indiceProyectoAbierto > -1) {
                    ProyectoAbierto p = proyectosAbiertos.get(indiceProyectoAbierto);
                    if (p != null) {
                        String path = p.getRuta() + "/" + nodo.getUserObject().toString();
                        if (GestorArchivos.eliminarArchivo(path)) {
                            p.getArchivos().remove(nodo.getUserObject().toString());
                            p.escribirArchivoPj();
                            actualizarArbolProyectos();
                        } else {
                            JOptionPane.showMessageDialog(this, "Error al eliminar el archivo: " + path);
                        }
                    }
                }
            }
        }
    }

    public void compilar() {
        CompiladorLenguaje.reiniciar();
        if (jtpTextos.getSelectedIndex() > -1) {
            guardar();
            ArchivoAbierto arch = archivosAbiertos.get(jtpTextos.getSelectedIndex());
            if (arch != null) {
                if (arch.getProyecto() != null) {
                    CompiladorLenguaje.compilarInicial(new File(arch.getPath()), arch.getProyecto().getRuta());
                } else {
                    File archivo = new File(arch.getPath());
                    CompiladorLenguaje.compilarInicial(archivo, archivo.getParentFile().getPath());
                }
            }
        }
    }

    public void ejecutarArchivo3D() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Codigo 3 direcciones (3dr)", "3dr");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            LienzoDibujo ld = new LienzoDibujo(this, true, chooser.getSelectedFile().getPath());
            ld.setVisible(true);
        }
    }

}
