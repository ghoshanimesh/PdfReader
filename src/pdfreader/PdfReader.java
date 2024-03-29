/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfreader;

import java.awt.CardLayout;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JButton;

/**
 *
 * @author jaynam
 */
public class PdfReader extends javax.swing.JFrame {

    private final JFileChooser fc ;
    private final CardLayout cardlayout;
    private final JButton[] handler ;
    private final ImageHandler imagehandler;
    private int count = 0;
    Thread t;
    private String text;
                    
    /**
     * Creates new form PdfReader
     */
    public PdfReader() {
        initComponents();
        fc = new JFileChooser() ;
        
        handler = new JButton[2];
        handler[0] = prevBtn;
        handler[1] = nextBtn;
        prevBtn.setVisible(false);
        nextBtn.setVisible(false);
        cardlayout = new CardLayout();
        imagePanel.setLayout(cardlayout);
        imagehandler = ImageHandler.getInstance();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanel1 = new javax.swing.JPanel();
        searchFile = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        displayText = new javax.swing.JTextArea();
        titleLabel = new javax.swing.JLabel();
        imagePanel = new javax.swing.JPanel();
        prevBtn = new javax.swing.JButton();
        nextBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        searchFile.setText("Search");
        searchFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFileActionPerformed(evt);
            }
        });
        jPanel1.add(searchFile);
        searchFile.setBounds(40, 340, 130, 31);

        displayText.setColumns(20);
        displayText.setRows(5);
        jScrollPane1.setViewportView(displayText);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(20, 50, 490, 270);

        titleLabel.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jPanel1.add(titleLabel);
        titleLabel.setBounds(100, 0, 280, 40);

        imagePanel.setLayout(new java.awt.CardLayout());
        jPanel1.add(imagePanel);
        imagePanel.setBounds(520, 50, 200, 240);

        prevBtn.setText("Previous");
        prevBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevBtnActionPerformed(evt);
            }
        });
        jPanel1.add(prevBtn);
        prevBtn.setBounds(560, 340, 100, 31);

        nextBtn.setText("Next");
        nextBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextBtnActionPerformed(evt);
            }
        });
        jPanel1.add(nextBtn);
        nextBtn.setBounds(670, 340, 100, 31);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 864, Short.MAX_VALUE)
                .addContainerGap())
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                .addContainerGap())
        );
        jLayeredPane1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /*
            when search button is clicked a file chooeser box will be shown and ask to select a file
            then it will check if its pdf or not
            will pass the file to isPDF methode to check if its pdf or not
            then the flag status will pass to displayButton so that if its true
            then button would be visible the button
            or else invisible
            its its not a pdf then a pop up msg will occur else the text will be display in the textbox
    */
    private void searchFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFileActionPerformed
        // TODO add your handling code here:
        
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION ){
            File file = fc.getSelectedFile();
            count =0;
            try{
                boolean flag = PDFHandler.isPDF(file);
                
                if(!flag){
                    JOptionPane.showMessageDialog(null,"Please Select A PDF File","Error",JOptionPane.ERROR_MESSAGE);
                }else{
                    text = PDFHandler.display(file);
                    
                    imagehandler.addImage(file,imagePanel);
                    imagehandler.handelButton(handler);
                    SetString setstring = new SetString(text, file.getName().toUpperCase());
                    setstring.setTextOfComponent(displayText, titleLabel);
                }
            }catch(HeadlessException | IOException e){
                JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_searchFileActionPerformed
    
    
    private void prevBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevBtnActionPerformed
        // TODO add your handling code here:
        cardlayout.previous(imagePanel);
        count--;
        imagehandler.handelButton(handler, count);
    }//GEN-LAST:event_prevBtnActionPerformed

    private void nextBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextBtnActionPerformed
        // TODO add your handling code here:
        cardlayout.next(imagePanel);
        count++;
        imagehandler.handelButton(handler , count);
    }//GEN-LAST:event_nextBtnActionPerformed


    /*
        @author:- jaynam
            this will visible or invisible the button
        @param :-
            boolean :- if true then visible or else invisible
    */
    
    
     
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
            java.util.logging.Logger.getLogger(PdfReader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PdfReader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PdfReader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PdfReader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PdfReader().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea displayText;
    private javax.swing.JPanel imagePanel;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton nextBtn;
    private javax.swing.JButton prevBtn;
    private javax.swing.JButton searchFile;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
