package Admin;

import java.awt.Color;

public class AdminData extends javax.swing.JDialog {

    public AdminData(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        CPassword = new javax.swing.JPasswordField();
        Password = new javax.swing.JPasswordField();
        UserName = new javax.swing.JTextField();
        CheckPassword = new javax.swing.JTextField();
        CheckUserName = new javax.swing.JTextField();
        Valid = new javax.swing.JButton();
        SaveButton = new javax.swing.JButton();
        CancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(algorithm.AlgorithmApp.class).getContext().getResourceMap(AdminData.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        CPassword.setText(resourceMap.getString("CPassword.text")); // NOI18N
        CPassword.setName("CPassword"); // NOI18N
        CPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                CPasswordFocusLost(evt);
            }
        });

        Password.setText(resourceMap.getString("Password.text")); // NOI18N
        Password.setName("Password"); // NOI18N

        UserName.setText(resourceMap.getString("UserName.text")); // NOI18N
        UserName.setName("UserName"); // NOI18N

        CheckPassword.setEditable(false);
        CheckPassword.setBorder(null);
        CheckPassword.setName("CheckPassword"); // NOI18N

        CheckUserName.setEditable(false);
        CheckUserName.setBorder(null);
        CheckUserName.setName("CheckUserName"); // NOI18N

        Valid.setText(resourceMap.getString("Valid.text")); // NOI18N
        Valid.setName("Valid"); // NOI18N
        Valid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ValidActionPerformed(evt);
            }
        });

        SaveButton.setText(resourceMap.getString("SaveButton.text")); // NOI18N
        SaveButton.setName("SaveButton"); // NOI18N
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });

        CancelButton.setText(resourceMap.getString("CancelButton.text")); // NOI18N
        CancelButton.setName("CancelButton"); // NOI18N
        CancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButtonActionPerformed(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Password, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                            .addComponent(CPassword)
                            .addComponent(UserName, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CheckPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Valid)))
                    .addComponent(SaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(CancelButton)
                    .addComponent(CheckUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Valid)
                    .addComponent(CheckUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(CheckPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CancelButton)
                    .addComponent(SaveButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // <editor-fold defaultstate="collapsed" desc="GUI Manager">
    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        System.exit(0);
    }//GEN-LAST:event_formWindowClosed

    private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButtonActionPerformed
        //Hide Dialog
        Manager.ShowViewFrame();
    }//GEN-LAST:event_CancelButtonActionPerformed

    private void ValidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ValidActionPerformed
        CheckValidation();
    }//GEN-LAST:event_ValidActionPerformed

    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed
        if(CheckValidation() && CheckPassword())
        {
            //add to DB
            Manager.Alter(UserName.getText(),Password.getText());
            //Hide Dialog
            Manager.ShowViewFrame();
        }
    }//GEN-LAST:event_SaveButtonActionPerformed

    private void CPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CPasswordFocusLost
        CheckPassword();
    }//GEN-LAST:event_CPasswordFocusLost
// </editor-fold>
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField CPassword;
    private javax.swing.JButton CancelButton;
    private javax.swing.JTextField CheckPassword;
    private javax.swing.JTextField CheckUserName;
    private javax.swing.JPasswordField Password;
    private javax.swing.JButton SaveButton;
    private javax.swing.JTextField UserName;
    private javax.swing.JButton Valid;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables

    //Set of Variables
    private AdminApp Manager;
    private boolean valid;

    // <editor-fold defaultstate="collapsed" desc="Data Setters">
    void SetManager(AdminApp App) {
        Manager = App;
    }

    void SetUser(String User) {
        UserName.setText(User);
    }

    void SetPW(String PW) {
        Password.setText(PW);
        CPassword.setText(PW);
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Registeration Manager Method">
    //Check Confirmation
    private boolean CheckPassword(){
        //Check Password Confirmation
        if(CPassword.getText().equals(Password.getText()))
        {
            CheckPassword.setForeground(Color.green);
            CheckPassword.setText("Match");
            return true;
        }
        else
        {
            CheckPassword.setForeground(Color.red);
            CheckPassword.setText("Not Match");
            return false;
        }
    }

    //Check Validation
    private boolean CheckValidation(){
        //Check UserName Validation
        valid = Manager.CheckValidation(UserName.getText());
        if(valid)
        {
            CheckUserName.setForeground(Color.green);
            CheckUserName.setText("Vaild");
        }
        else
        {
            CheckUserName.setForeground(Color.red);
            CheckUserName.setText("Not Valid");
        }
        return valid;
    }// </editor-fold>

}
