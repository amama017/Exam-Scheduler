package Lecturer;

import DataBase.Connection_MySQL;
import algorithm.AlgorithmApp;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class LecturerView extends javax.swing.JFrame {

    /** Creates new form LecturerView */
    public LecturerView(Connection_MySQL Conn, AlgorithmApp app, boolean modal, int ID) {
        MainApp = app;
        Manager = new LecturerApp(Conn,ID);
        initComponents();
        for(int i=1; i<TimeTable.getColumnCount(); i++)
        {
            if(i==4)
                i++;
            for(int j=0; j<TimeTable.getRowCount(); j++)
            {
                TimeTable.setValueAt(false, j, i);
            }
        }
        InitializeView();
        this.ID.setText(Integer.toString(ID));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SaveButton = new javax.swing.JButton();
        ExitButton = new javax.swing.JButton();
        EditButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        ID = new javax.swing.JTextField();
        Name = new javax.swing.JTextField();
        Position = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TimeTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("Form"); // NOI18N
        setResizable(false);

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(algorithm.AlgorithmApp.class).getContext().getResourceMap(LecturerView.class);
        SaveButton.setText(resourceMap.getString("SaveButton.text")); // NOI18N
        SaveButton.setEnabled(false);
        SaveButton.setName("SaveButton"); // NOI18N
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });

        ExitButton.setText(resourceMap.getString("ExitButton.text")); // NOI18N
        ExitButton.setName("ExitButton"); // NOI18N
        ExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitButtonActionPerformed(evt);
            }
        });

        EditButton.setText(resourceMap.getString("EditButton.text")); // NOI18N
        EditButton.setName("EditButton"); // NOI18N
        EditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditButtonActionPerformed(evt);
            }
        });

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        ID.setText(resourceMap.getString("ID.text")); // NOI18N
        ID.setEnabled(false);
        ID.setName("ID"); // NOI18N

        Name.setText(resourceMap.getString("Name.text")); // NOI18N
        Name.setEnabled(false);
        Name.setName("Name"); // NOI18N

        Position.setText(resourceMap.getString("Position.text")); // NOI18N
        Position.setEnabled(false);
        Position.setName("Position"); // NOI18N

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setName("jScrollPane1"); // NOI18N

        TimeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Sunday", new Boolean(false), null, null, "", null, null, null, null, null},
                {"Monday", null, null, null, "", null, null, null, null, null},
                {"Tuesday", null, null, null, "", null, null, null, null, null},
                {"Wednesady", null, null, null, "", null, null, null, null, null},
                {"Thurasday", null, null, null, "", null, null, null, null, null}
            },
            new String [] {
                "Days", "08:30 to 09:30", "09:45 to 10:45", "11:00 to 12:00", "Break", "13:00 to 14:00", "14:15 to 15:15", "15:30 to 16:30", "16:45 to 17:45", "18:00 to 19:00"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, false, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TimeTable.setName("CheckedTable"); // NOI18N
        TimeTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(TimeTable);
        TimeTable.getColumnModel().getColumn(0).setResizable(false);
        TimeTable.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("CheckedTable.columnModel.title0")); // NOI18N
        TimeTable.getColumnModel().getColumn(1).setResizable(false);
        TimeTable.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("CheckedTable.columnModel.title1")); // NOI18N
        TimeTable.getColumnModel().getColumn(2).setResizable(false);
        TimeTable.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("CheckedTable.columnModel.title2")); // NOI18N
        TimeTable.getColumnModel().getColumn(3).setResizable(false);
        TimeTable.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("CheckedTable.columnModel.title3")); // NOI18N
        TimeTable.getColumnModel().getColumn(4).setResizable(false);
        TimeTable.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("CheckedTable.columnModel.title9")); // NOI18N
        TimeTable.getColumnModel().getColumn(5).setResizable(false);
        TimeTable.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("CheckedTable.columnModel.title4")); // NOI18N
        TimeTable.getColumnModel().getColumn(6).setResizable(false);
        TimeTable.getColumnModel().getColumn(6).setHeaderValue(resourceMap.getString("CheckedTable.columnModel.title5")); // NOI18N
        TimeTable.getColumnModel().getColumn(7).setResizable(false);
        TimeTable.getColumnModel().getColumn(7).setHeaderValue(resourceMap.getString("CheckedTable.columnModel.title6")); // NOI18N
        TimeTable.getColumnModel().getColumn(8).setResizable(false);
        TimeTable.getColumnModel().getColumn(8).setHeaderValue(resourceMap.getString("CheckedTable.columnModel.title7")); // NOI18N
        TimeTable.getColumnModel().getColumn(9).setResizable(false);
        TimeTable.getColumnModel().getColumn(9).setHeaderValue(resourceMap.getString("CheckedTable.columnModel.title8")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Position)
                            .addComponent(Name)
                            .addComponent(ID, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(EditButton)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(SaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 822, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(Position, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EditButton)
                            .addComponent(SaveButton)
                            .addComponent(ExitButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // <editor-fold defaultstate="collapsed" desc="GUI Managers">
    private void ExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_ExitButtonActionPerformed

    private void EditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditButtonActionPerformed
        MainApp.RunDialog(2);
        Manager.ShowDataFrame();
    }//GEN-LAST:event_EditButtonActionPerformed

    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed
        SaveTimeChange();
        SaveButton.setEnabled(false);
    }//GEN-LAST:event_SaveButtonActionPerformed
    // </editor-fold>

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton EditButton;
    private javax.swing.JButton ExitButton;
    private javax.swing.JTextField ID;
    private javax.swing.JTextField Name;
    private javax.swing.JTextField Position;
    private javax.swing.JButton SaveButton;
    private javax.swing.JTable TimeTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    // <editor-fold defaultstate="collapsed" desc="Set of Variables used by the Class">
    private AlgorithmApp MainApp;
    private LecturerApp Manager;
    private TableModelListener TableListener;// </editor-fold>

    private void InitializeView() {
        //set the default parameters in the View
        Manager.InitializeView(this);
        System.out.println("Setting Time Table");
        //set the courses found in the course table
        Manager.SetTimeTable(this);
        //add a listener to the Course table to sense any change in the data.
        TableListener = new TableModelListener() {

            public void tableChanged(TableModelEvent e) {
                SaveButton.setEnabled(true);
            };
        };
        //add the listener to the table
        TimeTable.getModel().addTableModelListener(TableListener);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Setters for the TextBoxes & Table">
    public void SetName(String Name){
        this.Name.setText(Name);
    }

    void setType(int Type) {
        if(Type == 1)
           this.Position.setText("Instructor");
        else
           this.Position.setText("TA");
    }

    public void setDialog(LecturerData D) {
        Manager.SetDataFrame(D);
    }

    public void edittime(boolean State,int row,int col){
        //Set the check box of the row_index row by the State
        DefaultTableModel model;
        model = (DefaultTableModel) TimeTable.getModel();
        //Returns a model to control the set of rows & columns in the Table
        if(row <= model.getRowCount())
            model.setValueAt(State, row-1, col);
    }

    private void SaveTimeChange() {
        Manager.SaveTimeChange(TimeTable);
    }// </editor-fold>

}
