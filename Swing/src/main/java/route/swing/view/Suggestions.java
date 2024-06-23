/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package route.swing.view;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.json.JSONArray;
import org.json.JSONObject;
import route.swing.model.user.UserVerificationResponseDto;

/**
 *
 * @author Luis
 */
public class Suggestions extends javax.swing.JFrame {

    /**
     * Creates new form Suggestions
     */
    private HashMap<String, JSONObject> suggestionsMap;
    private DefaultListModel<String> listModel;
    public boolean status = false;

    MainPanel instance;

    Map<String, String> osmTypeMap = new HashMap<>();

    public Suggestions(JFrame frame) {
        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);

        osmTypeMap.put("node", "N");
        osmTypeMap.put("relation", "R");

        this.instance = (MainPanel) frame;

        listModel = new DefaultListModel<>();
        suggestionsMap = new HashMap<>();
        jList1.setModel(listModel);
        jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        SuggestionsField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                updateSuggestions();
            }

            public void removeUpdate(DocumentEvent e) {
                updateSuggestions();
            }

            public void changedUpdate(DocumentEvent e) {
                updateSuggestions();
            }
        });

        jList1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = jList1.locationToIndex(e.getPoint());
                    if (index >= 0) {
                        String selectedSuggestion = listModel.getElementAt(index);
                        SuggestionsField.setText(selectedSuggestion);
                    }
                }
            }
        });

        jLabel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
        });

    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    private void handleSelection(String selectedSuggestion) throws IOException {
        listModel.clear(); 
        JSONObject selectedData = suggestionsMap.get(selectedSuggestion);
        if (selectedData != null) {
            String osmType = selectedData.getString("osm_type");
            String osmId = String.valueOf(selectedData.getLong("osm_id"));
            String name = selectedData.getString("name");
            System.out.println("Selected OSM Type: " + osmType);
            System.out.println("Selected OSM ID: " + osmId);
            System.out.println("Name: " + name + "\n");

            String OmsTypeConvert = osmTypeMap.get(osmType);

            if (status == false) {
                instance.SaveStartRegion(name, osmId, OmsTypeConvert);
            } else {
                instance.SaveEndRegion(name, osmId, OmsTypeConvert);
            }
            
            this.dispose();
        }
    }

    private void updateSuggestions() {
        String query = SuggestionsField.getText();
        if (query.isEmpty()) {
            listModel.clear();
            suggestionsMap.clear();
            return;
        }

        SwingWorker<Void, String> worker = new SwingWorker<Void, String>() {
            @Override
            protected Void doInBackground() throws Exception {
                String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8.toString());
                String url = "https://nominatim.openstreetmap.org/search?q=" + encodedQuery + "&format=json&addressdetails=1&countrycodes=PE";
                HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                conn.setRequestMethod("GET");
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
//                System.out.println(response.toString());

                JSONArray jsonArray = new JSONArray(response.toString());
                listModel.clear();
                suggestionsMap.clear();

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String displayName = jsonObject.getString("display_name");
                    listModel.addElement(displayName);
                    suggestionsMap.put(displayName, jsonObject);
                }

                return null;
            }

            @Override
            protected void process(java.util.List<String> chunks) {
                for (String suggestion : chunks) {
                    listModel.addElement(suggestion);
                }
            }
        };
        worker.execute();
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
        returnLAbel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        SuggestionsField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        returnLAbel.setFont(new java.awt.Font("Symbols Nerd Font Mono", 0, 18)); // NOI18N
        returnLAbel.setForeground(new java.awt.Color(0, 0, 0));
        returnLAbel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        returnLAbel.setText("");
        returnLAbel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                returnLAbelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                returnLAbelMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                returnLAbelMousePressed(evt);
            }
        });
        jPanel1.add(returnLAbel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 30, 50));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Selección de zona");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(165, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 50));

        jPanel2.setBackground(new java.awt.Color(85, 110, 230));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Seleccionar");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, 100, 50));

        SuggestionsField.setBackground(new java.awt.Color(255, 255, 255));
        SuggestionsField.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        SuggestionsField.setForeground(new java.awt.Color(0, 0, 0));
        SuggestionsField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        SuggestionsField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                SuggestionsFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                SuggestionsFieldFocusLost(evt);
            }
        });
        SuggestionsField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SuggestionsFieldActionPerformed(evt);
            }
        });
        jPanel1.add(SuggestionsField, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 240, 50));

        jList1.setBackground(new java.awt.Color(255, 255, 255));
        jList1.setBorder(null);
        jList1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jList1.setForeground(new java.awt.Color(0, 0, 0));
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jList1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 350, 240));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SuggestionsFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SuggestionsFieldFocusGained
        // TODO add your handling code here:
//        if (SuggestionsField.getText().equals("ejemplo@email.com")) {
//            SuggestionsField.setText("");
//            SuggestionsField.setForeground(Color.BLACK);
//        }
    }//GEN-LAST:event_SuggestionsFieldFocusGained

    private void SuggestionsFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SuggestionsFieldFocusLost
        // TODO add your handling code here:
//        if (SuggestionsField.getText().equals("")) {
//            SuggestionsField.setForeground(Color.decode("#CCCCCC"));
//            SuggestionsField.setText("ejemplo@email.com");
//        }
    }//GEN-LAST:event_SuggestionsFieldFocusLost

    private void SuggestionsFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SuggestionsFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SuggestionsFieldActionPerformed

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:

//        String selectedSuggestion = jList1.getSelectedValue();
            handleSelection(SuggestionsField.getText());
        } catch (IOException ex) {
            Logger.getLogger(Suggestions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel1MousePressed

    private void returnLAbelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returnLAbelMouseEntered
        // TODO add your handling code here:
        returnLAbel.setForeground(Color.red);
    }//GEN-LAST:event_returnLAbelMouseEntered

    private void returnLAbelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returnLAbelMouseExited
        // TODO add your handling code here:
        returnLAbel.setForeground(Color.black);
    }//GEN-LAST:event_returnLAbelMouseExited

    private void returnLAbelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returnLAbelMousePressed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_returnLAbelMousePressed

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
            java.util.logging.Logger.getLogger(Suggestions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Suggestions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Suggestions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Suggestions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Suggestions(new MainPanel(new UserVerificationResponseDto())).setVisible(true);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(Suggestions.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnknownHostException ex) {
                    Logger.getLogger(Suggestions.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField SuggestionsField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel returnLAbel;
    // End of variables declaration//GEN-END:variables
}
