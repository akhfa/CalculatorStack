/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Calc;

import javax.swing.JOptionPane;

/**
 *
 * @author akhfa
 */
public class MainFrame extends javax.swing.JFrame {

    Calculator calc = new Calculator();
    /**
     * Creates new form Calculator
     */
    public MainFrame() {
        initComponents();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        InputTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        TombolCalculate = new javax.swing.JButton();
        TombolOpen = new javax.swing.JButton();
        TombolValidate = new javax.swing.JButton();
        TombolKonvert = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Calculator");

        jLabel1.setText("Input");

        TombolCalculate.setText("Calculate");
        TombolCalculate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TombolCalculateActionPerformed(evt);
            }
        });

        TombolOpen.setText("Open");
        TombolOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TombolOpenActionPerformed(evt);
            }
        });

        TombolValidate.setText("Validate");
        TombolValidate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TombolValidateActionPerformed(evt);
            }
        });

        TombolKonvert.setText("Convert2CFG");
        TombolKonvert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TombolKonvertActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(54, 54, 54)
                        .addComponent(InputTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(TombolValidate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TombolKonvert)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TombolOpen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TombolCalculate, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InputTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TombolCalculate)
                    .addComponent(TombolOpen)
                    .addComponent(TombolValidate)
                    .addComponent(TombolKonvert))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TombolCalculateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TombolCalculateActionPerformed
        // TODO add your handling code here:
        try
        {
            String input = InputTextField.getText();
            if(input.equals(""))
            {
                JOptionPane.showMessageDialog(null, "Masukkan input");
            }
            else
            {
                calc.SetInput(input);
                System.out.println(input.charAt(input.length()-1));
                if(input.charAt(input.length()-1) != '=')
                {
                    JOptionPane.showMessageDialog(null, "Kurang tanda \"=\"");

                }
                else
                {
                    input = input.substring(0, input.length()-1);
                    if(calc.InputValid())
                        calc.Hitung(input);
                    else
                        JOptionPane.showMessageDialog(null, "Input tidak valid");
                }
            }
            
        }
        catch (NullPointerException nil)
        {
            JOptionPane.showMessageDialog(null, "Masukkan inputnya");
        }
    }//GEN-LAST:event_TombolCalculateActionPerformed

    private void TombolOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TombolOpenActionPerformed
        // TODO add your handling code here:
        try
        {
            //String input = InputTextField.getText();
            calc.SetInput(InputTextField.getText());
        }
        catch (NullPointerException nil)
        {
            JOptionPane.showMessageDialog(null, "Masukkan inputnya");
        }
        
        calc.InputAturan();
        calc.PrintAturan();
        calc.PrintInput();
        calc.PrintFinalState();
        
    }//GEN-LAST:event_TombolOpenActionPerformed

    private void TombolValidateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TombolValidateActionPerformed
        // TODO add your handling code here:
        String input = InputTextField.getText();
        if(input.equals(""))
            JOptionPane.showMessageDialog(null, "Masukkan input terlebih dahulu");
        else
        {
            calc.SetInput(InputTextField.getText());
            boolean valid = calc.InputValid();
            if(valid)
                JOptionPane.showMessageDialog(null, "Input Valid");
            else
            {
                JOptionPane.showMessageDialog(null, "Input tidak Valid");
                System.exit(0);
            }

        }
        
    }//GEN-LAST:event_TombolValidateActionPerformed

    private void TombolKonvertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TombolKonvertActionPerformed
        // TODO add your handling code here:
        calc.ConvertToCFG();
        JOptionPane.showMessageDialog(null, "Konversi berhasil. Hasil tersimpan di file CFG.out");
    }//GEN-LAST:event_TombolKonvertActionPerformed

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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField InputTextField;
    private javax.swing.JButton TombolCalculate;
    private javax.swing.JButton TombolKonvert;
    private javax.swing.JButton TombolOpen;
    private javax.swing.JButton TombolValidate;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
