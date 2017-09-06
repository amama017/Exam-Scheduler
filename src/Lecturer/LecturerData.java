package Lecturer;

import java.awt.Color;

public class LecturerData extends javax.swing.JDialog {

    /** Creates new form LecturerData */
    public LecturerData(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        ValidName = false;
        PassCheck = false;
        NameCheck = false;
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
        jLabel4 = new javax.swing.JLabel();
        CPassword = new javax.swing.JPasswordField();
        Password = new javax.swing.JPasswordField();
        UserName = new javax.swing.JTextField();
        Name = new javax.swing.JTextField();
        CheckPassword = new javax.swing.JTextField();
        CheckUserName = new javax.swing.JTextField();
        CancelButton = new javax.swing.JButton();
        SaveButton = new javax.swing.JButton();
        Valid = new javax.swing.JButton();
        EditUserName = new javax.swing.JButton();
        EditPassword = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(algorithm.AlgorithmApp.class).getContext().getResourceMap(LecturerData.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        CPassword.setEditable(false);
        CPassword.setText(resourceMap.getString("CPassword.text")); // NOI18N
        CPassword.setName("CPassword"); // NOI18N
        CPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                CPasswordFocusLost(evt);
            }
        });

        Password.setEditable(false);
        Password.setText(resourceMap.getString("Password.text")); // NOI18N
        Password.setName("Password"); // NOI18N

        UserName.setEditable(false);
        UserName.setText(resourceMap.getString("UserName.text")); // NOI18N
        UserName.setName("UserName"); // NOI18N

        Name.setText(resourceMap.getString("Name.text")); // NOI18N
        Name.setName("Name"); // NOI18N

        CheckPassword.setEditable(false);
        CheckPassword.setBorder(null);
        CheckPassword.setName("CheckPassword"); // NOI18N

        CheckUserName.setEditable(false);
        CheckUserName.setBorder(null);
        CheckUserName.setName("CheckUserName"); // NOI18N

        CancelButton.setText(resourceMap.getString("CancelButton.text")); // NOI18N
        CancelButton.setName("CancelButton"); // NOI18N
        CancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButtonActionPerformed(evt);
            }
        });

        SaveButton.setText(resourceMap.getString("SaveButton.text")); // NOI18N
        SaveButton.setName("SaveButton"); // NOI18N
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });

        Valid.setText(resourceMap.getString("Valid.text")); // NOI18N
        Valid.setEnabled(false);
        Valid.setName("Valid"); // NOI18N
        Valid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ValidActionPerformed(evt);
            }
        });

        EditUserName.setText(resourceMap.getString("EditUserName.text")); // NOI18N
        EditUserName.setName("EditUserName"); // NOI18N
        EditUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditUserNameActionPerformed(evt);
            }
        });

        EditPassword.setText(resourceMap.getString("EditPassword.text")); // NOI18N
        EditPassword.setName("EditPassword"); // NOI18N
        EditPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditPasswordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Password)
                    .addComponent(CPassword)
                    .addComponent(UserName)
                    .addComponent(Name))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CheckPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(EditPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(EditUserName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CheckUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Valid)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(SaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(CancelButton)))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(UserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EditUserName)
                    .addComponent(Valid))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EditPassword)
                    .addComponent(CheckUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(CPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CheckPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SaveButton)
                    .addComponent(CancelButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // <editor-fold defaultstate="collapsed" desc="GUI Managers">
    private void EditUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditUserNameActionPerformed
        UserName.setEditable(true);
        Valid.setEnabled(true);
        NameCheck = true;
    }//GEN-LAST:event_EditUserNameActionPerformed

    private void EditPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditPasswordActionPerformed
        Password.setEditable(true);
        CPassword.setEditable(true);
        PassCheck = true;
    }//GEN-LAST:event_EditPasswordActionPerformed

    private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButtonActionPerformed
        MainApp.ShowViewFrame();
    }//GEN-LAST:event_CancelButtonActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        System.exit(0);
    }//GEN-LAST:event_formWindowClosed

    private void CPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CPasswordFocusLost
        CheckPassword();
    }//GEN-LAST:event_CPasswordFocusLost

    private void ValidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ValidActionPerformed
        CheckValidation();
    }//GEN-LAST:event_ValidActionPerformed

    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed
        if(NameCheck && PassCheck)
        {
            if(CheckValidation() && CheckPassword())
            {
                //add to DB
                MainApp.Alter(UserName.getText(),Password.getText(),Name.getText());
                //update the fields in the View before closing
                MainApp.InitializeView(MainApp.GetLView());
                //Hide Dialog
                MainApp.ShowViewFrame();
            }
        }
        else if(NameCheck)
        {
            if(CheckValidation())
            {
                //add to DB
                MainApp.Alter(UserName.getText(),Password.getText(),Name.getText());
                //update the fields in the View before closing
                MainApp.InitializeView(MainApp.GetLView());
                //Hide Dialog
                MainApp.ShowViewFrame();
            }
        }
        else
        {
            if(CheckPassword())
            {
                //add to DB
                MainApp.Alter(UserName.getText(),Password.getText(),Name.getText());
                //update the fields in the View before closing
                MainApp.InitializeView(MainApp.GetLView());
                //Hide Dialog
                MainApp.ShowViewFrame();
            }
        }
    }//GEN-LAST:event_SaveButtonActionPerformed
    // </editor-fold>

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField CPassword;
    private javax.swing.JButton CancelButton;
    private javax.swing.JTextField CheckPassword;
    private javax.swing.JTextField CheckUserName;
    private javax.swing.JButton EditPassword;
    private javax.swing.JButton EditUserName;
    private javax.swing.JTextField Name;
    private javax.swing.JPasswordField Password;
    private javax.swing.JButton SaveButton;
    private javax.swing.JTextField UserName;
    private javax.swing.JButton Valid;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables

    // <editor-fold defaultstate="collapsed" desc="Set of Variables used by the Class">
    private LecturerApp MainApp;
    private boolean NameCheck;
    private boolean PassCheck;
    private boolean ValidName;// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Manager Commands">
    void SetManager(LecturerApp Main) {
        MainApp = Main;
    }

    private boolean CheckPassword() {
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

    private boolean CheckValidation() {
        //Check UserName Validation
        ValidName = MainApp.CheckValidation(UserName.getText());
        if(ValidName)
        {
            CheckUserName.setForeground(Color.green);
            CheckUserName.setText("Vaild");
        }
        else
        {
            CheckUserName.setForeground(Color.red);
            CheckUserName.setText("Not Valid");
        }
        return ValidName;
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Setters for the TextBoxes & Table">
    void SetName(String Name) {
        this.Name.setText(Name);
    }

    void SetUser(String User) {
        UserName.setText(User);
    }

    void SetPW(String PW) {
        Password.setText(PW);
        CPassword.setText(PW);
    }// </editor-fold>

}