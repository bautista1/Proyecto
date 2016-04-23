/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.gui.dialog;

import java.util.List;
import javax.swing.JOptionPane;
import proyecto.gui.ProyectoAbierto;
import proyecto.util.GestorArchivos;

/**
 *
 * @author Luis Fernando Leiva
 */
public class NuevoArchivoDialog extends javax.swing.JDialog {

    boolean valido;

    private String nombre;
    private ProyectoAbierto proyecto;
    private List<ProyectoAbierto> proyectosAbiertos;

    /**
     * Creates new form NuevoProyectoDialog
     */
    public NuevoArchivoDialog(java.awt.Frame parent, boolean modal, List<ProyectoAbierto> proyectosAbiertos) {
        super(parent, modal);
        initComponents();
        jtfUbicacion.setText("");
        valido = false;
        jcbProyecto.removeAllItems();
        this.proyectosAbiertos = proyectosAbiertos;
        for (ProyectoAbierto p : proyectosAbiertos) {
            jcbProyecto.addItem(p.getNombre());
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jtfUbicacion = new javax.swing.JTextField();
        jbExaminar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jrbSinProyecto = new javax.swing.JRadioButton();
        jrbProyecto = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jtfNombre = new javax.swing.JTextField();
        jcbProyecto = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);

        jLabel1.setText("Ubicacion:");

        jtfUbicacion.setEnabled(false);

        jbExaminar.setText("Examinar");
        jbExaminar.setEnabled(false);
        jbExaminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbExaminarActionPerformed(evt);
            }
        });

        jButton2.setText("Crear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Cancelar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jrbSinProyecto);
        jrbSinProyecto.setText("Sin proyecto");

        buttonGroup1.add(jrbProyecto);
        jrbProyecto.setSelected(true);
        jrbProyecto.setText("Proyecto");
        jrbProyecto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jrbProyectoItemStateChanged(evt);
            }
        });

        jLabel2.setText("Nombre:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                                .addComponent(jButton3))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jrbProyecto))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfNombre)
                                    .addComponent(jcbProyecto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(116, 116, 116))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jrbSinProyecto)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(14, 14, 14)
                                .addComponent(jtfUbicacion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbExaminar)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbProyecto)
                    .addComponent(jcbProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrbSinProyecto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtfUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbExaminar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbExaminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbExaminarActionPerformed
        examinar();
    }//GEN-LAST:event_jbExaminarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        crear();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        cancelar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jrbProyectoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jrbProyectoItemStateChanged
        cambioEstadoOption();
    }//GEN-LAST:event_jrbProyectoItemStateChanged

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
            java.util.logging.Logger.getLogger(NuevoArchivoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NuevoArchivoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NuevoArchivoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NuevoArchivoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NuevoArchivoDialog dialog = new NuevoArchivoDialog(new javax.swing.JFrame(), true, null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton jbExaminar;
    private javax.swing.JComboBox jcbProyecto;
    private javax.swing.JRadioButton jrbProyecto;
    private javax.swing.JRadioButton jrbSinProyecto;
    private javax.swing.JTextField jtfNombre;
    private javax.swing.JTextField jtfUbicacion;
    // End of variables declaration//GEN-END:variables

    private void cancelar() {
        this.dispose();
    }

    private void crear() {
        if (!jtfNombre.getText().trim().equals("")) {
            if (jrbSinProyecto.isSelected()) {
                if (!jtfUbicacion.getText().trim().equals("")) {
                    nombre = jtfUbicacion.getText() + "/" + jtfNombre.getText() + ".frc";
                    if (GestorArchivos.escribirArchivo(nombre, "")) {
                        valido = true;
                        this.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al crear el nuevo archivo, no se pudo escribir el archivo.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Verifique que se haya agregado un nombre y una ubicación correcta.");
                }
            } else {
                if (jcbProyecto.getSelectedIndex() > -1){
                    proyecto = proyectosAbiertos.get(jcbProyecto.getSelectedIndex());
                    String ruta = proyecto.getRuta() + "/" + jtfNombre.getText() + ".frc";
                    nombre = jtfNombre.getText() + ".frc";
                    if (GestorArchivos.escribirArchivo(ruta, "")) {
                        valido = true;
                        proyecto.getArchivos().add(jtfNombre.getText()+".frc");
                        this.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al crear el nuevo archivo, no se pudo escribir el archivo.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Debe de seleccionar un proyecto para agregar el nuevo archivo.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe de escribir un nombre valido.");
        }
    }

    private void examinar() {
        jtfUbicacion.setText(GestorArchivos.examinar(this));
    }

    public boolean isValido() {
        return valido;
    }

    private void cambioEstadoOption() {
        if (this.jrbProyecto.isSelected()) {
            jcbProyecto.setEnabled(true);
            jbExaminar.setEnabled(false);
        } else {
            jcbProyecto.setEnabled(false);
            jbExaminar.setEnabled(true);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public ProyectoAbierto getProyecto() {
        return proyecto;
    }
    
    

}