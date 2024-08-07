/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package route.swing.view;

import java.awt.Color;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import route.swing.model.user.UserVerificationResponseDto;

/**
 *
 * @author Luis
 */
public class MenuPanel extends javax.swing.JPanel {

    /**
     * Creates new form MenuPanel
     */
    UserVerificationResponseDto user;
    History history;
    public MenuPanel(UserVerificationResponseDto user) throws IOException {
        initComponents();
        this.user = user;
        UserNameLabel.setText(user.getName());
        history = new History(this.user);
    }

    public void setMain(MainPanel main) {
        history.setMain(main);
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
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        PanelButtonHistory = new javax.swing.JPanel();
        HistoryButton = new javax.swing.JLabel();
        IconHistoryButton = new javax.swing.JLabel();
        LabelHistoryButton = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        PanelHorarButton = new javax.swing.JPanel();
        HorarButton = new javax.swing.JLabel();
        IconHorarButton = new javax.swing.JLabel();
        LabelHorarButton = new javax.swing.JLabel();
        UserNameLabel = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(213, 218, 248));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(213, 218, 248));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Symbols Nerd Font Mono", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(117, 132, 144));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 40));

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(117, 132, 144));
        jLabel6.setText("Menu");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 100, 40));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 290, 40));

        jPanel5.setBackground(new java.awt.Color(117, 132, 144));
        jPanel5.setPreferredSize(new java.awt.Dimension(290, 1));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 290, 1));

        PanelButtonHistory.setBackground(new java.awt.Color(213, 218, 248));
        PanelButtonHistory.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        HistoryButton.setForeground(new java.awt.Color(117, 132, 144));
        HistoryButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                HistoryButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                HistoryButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                HistoryButtonMousePressed(evt);
            }
        });
        PanelButtonHistory.add(HistoryButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 50));

        IconHistoryButton.setFont(new java.awt.Font("Symbols Nerd Font Mono", 1, 14)); // NOI18N
        IconHistoryButton.setForeground(new java.awt.Color(117, 132, 144));
        IconHistoryButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        IconHistoryButton.setText("");
        PanelButtonHistory.add(IconHistoryButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 30, 50));

        LabelHistoryButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        LabelHistoryButton.setForeground(new java.awt.Color(117, 132, 144));
        LabelHistoryButton.setText("Historial de viaje");
        PanelButtonHistory.add(LabelHistoryButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, 160, 50));

        jPanel1.add(PanelButtonHistory, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 250, 50));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Avatar_Container.png"))); // NOI18N
        jLabel7.setText("jLabel7");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 100, -1));

        PanelHorarButton.setBackground(new java.awt.Color(213, 218, 248));
        PanelHorarButton.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        HorarButton.setForeground(new java.awt.Color(117, 132, 144));
        HorarButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                HorarButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                HorarButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                HorarButtonMousePressed(evt);
            }
        });
        PanelHorarButton.add(HorarButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 50));

        IconHorarButton.setFont(new java.awt.Font("Symbols Nerd Font Mono", 1, 14)); // NOI18N
        IconHorarButton.setForeground(new java.awt.Color(117, 132, 144));
        IconHorarButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        IconHorarButton.setText("");
        PanelHorarButton.add(IconHorarButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 30, 50));

        LabelHorarButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        LabelHorarButton.setForeground(new java.awt.Color(117, 132, 144));
        LabelHorarButton.setText("Horario");
        PanelHorarButton.add(LabelHorarButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 140, 50));

        jPanel1.add(PanelHorarButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, -1, -1));

        UserNameLabel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        UserNameLabel.setForeground(new java.awt.Color(0, 0, 0));
        UserNameLabel.setText("User Name");
        jPanel1.add(UserNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void HistoryButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HistoryButtonMouseEntered
        // TODO add your handling code here:
        IconHistoryButton.setForeground(Color.white);
        LabelHistoryButton.setForeground(Color.white);
        PanelButtonHistory.setBackground(Color.decode("#556EE6"));
    }//GEN-LAST:event_HistoryButtonMouseEntered

    private void HistoryButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HistoryButtonMouseExited
        // TODO add your handling code here:
        IconHistoryButton.setForeground(Color.decode("#758490"));
        LabelHistoryButton.setForeground(Color.decode("#758490"));
        PanelButtonHistory.setBackground(Color.decode("#D5DAF8"));
    }//GEN-LAST:event_HistoryButtonMouseExited

    private void HorarButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HorarButtonMouseEntered
        // TODO add your handling code here:
        IconHorarButton.setForeground(Color.white);
        LabelHorarButton.setForeground(Color.white);
        PanelHorarButton.setBackground(Color.decode("#556EE6"));
    }//GEN-LAST:event_HorarButtonMouseEntered

    private void HorarButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HorarButtonMouseExited
        // TODO add your handling code here:
        IconHorarButton.setForeground(Color.decode("#758490"));
        LabelHorarButton.setForeground(Color.decode("#758490"));
        PanelHorarButton.setBackground(Color.decode("#D5DAF8"));
    }//GEN-LAST:event_HorarButtonMouseExited

    private void HistoryButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HistoryButtonMousePressed
        // TODO add your handling code here:
//        history.setMain(main);
        history.setVisible(true);
    }//GEN-LAST:event_HistoryButtonMousePressed

    private void HorarButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HorarButtonMousePressed
        try {
            // TODO add your handling code here:
            TimeTable horary = new TimeTable();
            horary.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(MenuPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_HorarButtonMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel HistoryButton;
    private javax.swing.JLabel HorarButton;
    private javax.swing.JLabel IconHistoryButton;
    private javax.swing.JLabel IconHorarButton;
    private javax.swing.JLabel LabelHistoryButton;
    private javax.swing.JLabel LabelHorarButton;
    private javax.swing.JPanel PanelButtonHistory;
    private javax.swing.JPanel PanelHorarButton;
    private javax.swing.JLabel UserNameLabel;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    // End of variables declaration//GEN-END:variables
}
