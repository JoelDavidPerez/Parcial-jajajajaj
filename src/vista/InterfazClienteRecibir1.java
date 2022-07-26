/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import controlador.ClienteRecibir;
import java.io.IOException;

/**
 *
 * @author ssant
 */
public class InterfazClienteRecibir1 extends javax.swing.JFrame {

    private ClienteRecibir cliente;
   
    public InterfazClienteRecibir1(ClienteRecibir recibir) {
        setTitle("Recibir");
        setResizable(false);
        setLocationRelativeTo(null);
        initComponents();
        cliente=recibir;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        RecibirRuta = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        campoIPR = new javax.swing.JTextField();
        campoStatusR = new javax.swing.JTextField();
        campoRutaR = new javax.swing.JTextField();
        btonRecibir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        RecibirRuta.setText("Ruta Nuevo Archivo:");

        jLabel2.setText("RecibirStatus");

        jLabel3.setText("RecibirIP");

        campoIPR.setEditable(false);
        campoIPR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoIPRActionPerformed(evt);
            }
        });

        campoStatusR.setEditable(false);
        campoStatusR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoStatusRActionPerformed(evt);
            }
        });

        campoRutaR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoRutaRActionPerformed(evt);
            }
        });

        btonRecibir.setText("Recibir");
        btonRecibir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btonRecibirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(RecibirRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(campoRutaR, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(campoStatusR, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(campoIPR, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btonRecibir, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RecibirRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoRutaR, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(btonRecibir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoIPR, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoStatusR, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 private void receiveActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
        
    try {
        if(campoRutaR.getText().length()>0){
            cliente.conectarRecibir(campoRutaR.getText(),this);
            cliente.recibirPaquetes(this); 
        }else{
            cliente.conectarRecibir("NuevoArchivo",this);
            cliente.recibirPaquetes(this);
    }
    
    }catch (IOException e) {
            System.out.println("IClienteRecibir >> Error al conectar el Cliente que recibe");
        }
 }
    private void campoStatusRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoStatusRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoStatusRActionPerformed

    private void campoRutaRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoRutaRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoRutaRActionPerformed

    private void campoIPRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoIPRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoIPRActionPerformed

    private void btonRecibirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btonRecibirActionPerformed
        try {
        if(campoRutaR.getText().length()>0){
            cliente.conectarRecibir(campoRutaR.getText(),this);
            cliente.recibirPaquetes(this); 
        }else{
            cliente.conectarRecibir("Descarga",this);
            cliente.recibirPaquetes(this);
    }
    
    }catch (IOException e) {
            System.out.println("IClienteRecibir >> Error al conectar el Cliente que recibe");
        }
    }//GEN-LAST:event_btonRecibirActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel RecibirRuta;
    public javax.swing.JButton btonRecibir;
    public javax.swing.JTextField campoIPR;
    public javax.swing.JTextField campoRutaR;
    public javax.swing.JTextField campoStatusR;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
public void cambioRuta(String x){
        campoRutaR.setText(x);
    }
     public void cambioIP(String x){
        campoIPR.setText(x);
    }
     public void cambioStatus(String x){
         campoStatusR.setText(x);
     }

}
