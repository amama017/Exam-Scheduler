package Lecturer;

import DataBase.Connection_MySQL;
import DataBase.Query_Result;
import java.sql.SQLException;
import javax.swing.JTable;

public class LecturerApp {

    public LecturerApp(Connection_MySQL Connection, int ID) {
        //Set of Database columns
        this.ID = ID;
        //Set of Database columns
        LoginTable = "login";
        UNcol = "UserName";
        PWCol = "Password";
        LecturerTable = "lecturer";
        IDCol = "ID";
        NameCol = "Name";
        TypeCol = "Type";
        LecturerID = "LecturerID";
        WeekTable = "Week";
        //Get A Connection for the database
        conn = Connection;
    }

    // <editor-fold defaultstate="collapsed" desc="Lecturer Manager Methods">
    void InitializeView(LecturerView View) {
        System.out.println("Setting the Window Variables");
        ViewFrame = View;
        //Get data from DB
        GetLecturerData();
        //Set the Text Boxes of the view
        View.SetName(Name);
        View.setType(Type);
        System.out.println("TextBoxes has been filled");
    }

    private void GetLecturerData() {
        //Select all columns for the Lecturer ID
        String Statement;
        Statement = "select * from " + LecturerTable + "," + LoginTable + " where "
                + LecturerTable + "." + IDCol + "=" + ID + " and " + LoginTable + "." + IDCol + "=" + ID + "";
        QResult = new Query_Result();
        //Excute the Query
        conn.Excute_Query(Statement, QResult);

        if(QResult.isSuccess())
        {
            try {
                if (QResult.getResult().next()){
                    System.out.println("Accessing User Data from DB");
                    //Set the Lecturer data from the DB
                    Name = QResult.getResult().getString(NameCol);
                    Type = QResult.getResult().getInt(TypeCol);
                    PW = QResult.getResult().getString(PWCol);
                    User = QResult.getResult().getString(UNcol);
                    System.out.println("User Info has been Read");
                }
            } catch (SQLException ex) {
                System.out.println("Error in DB, Can't Access User with ID" + ID);
                Connection_MySQL.printSQLException(ex);
            }
        }
    }

    void SetTimeTable(LecturerView View) {
        //Select all columns for the Lecturer ID
        String Statement;
        Statement = "select * from " + WeekTable + " where " + LecturerID + " = " + ID;
        QResult = new Query_Result();
        //Excute the Query
        conn.Excute_Query(Statement, QResult);

        if(QResult.isSuccess())
        {
            try {
                while (QResult.getResult().next()) {
                    View.edittime(true, QResult.getResult().getInt("Day"),QResult.getResult().getInt("Slot"));
                }
            } catch (SQLException ex) {
                Connection_MySQL.printSQLException(ex);
            }
        }
    }

    public void SetDataFrame(LecturerData Dialog){
        DataFrame = Dialog;
    }

    LecturerView GetLView() {
        return ViewFrame;
    }

    public void ShowDataFrame(){
        ViewFrame.hide();
        DataFrame.SetManager(this);
        InitializeData();
        DataFrame.show();
    }

    public void ShowViewFrame(){
        DataFrame.hide();
        ViewFrame.show();
    }

    private void InitializeData() {
        //Set the TextBoxes of the View
        DataFrame.SetName(Name);
        DataFrame.SetUser(User);
        DataFrame.SetPW(PW);
        System.out.println("TextBoxes has been filled");
    }

    boolean CheckValidation(String text) {
        return algorithm.AlgorithmApp.getApplication().CheckValidation(text);
    }

    void Alter(String User, String PW, String Name) {
        //Select all columns for the Student ID
        String Statement;
        Statement = "update "+LecturerTable;
        Statement += " Set "+NameCol+"='"+Name+"' where "+IDCol+" = "+this.ID+"";
        //Excute the Query
        Connection_MySQL.Update(Statement);

        Statement = "update login";
        Statement += " Set UserName='"+User+"', Password='"+PW+"'";
        Statement += " where "+IDCol+" = "+this.ID+" and Type=2";
        //Excute the Query
        Connection_MySQL.Update(Statement);
    }

    void SaveTimeChange(JTable TimeTable) {
        int RowNum = TimeTable.getRowCount();
        int ColNum = TimeTable.getColumnCount();

        //Removing the Slots set by the Lecturer
        System.out.println("Removing old Time Table");
        String Statement;
        Statement = "delete from " + WeekTable + " where " + LecturerID + "=" + ID;
        //Excute the Query
        Connection_MySQL.Update(Statement);

        //Resetting the new set of Time Slots
        for(int i=1; i<ColNum; i++)
        {
            if(i==4)
                i++;
            for(int j=0; j<RowNum; j++)
            {
                if(TimeTable.getValueAt(j, i).equals(true))
                    AddSlot(j+1,i);
            }
        }
    }

    private void AddSlot(int Day, int Slot) {
        //Insert the course of Code->Code taken by the Student
        System.out.println("adding new Time to Lecturer");
        String Statement;
        Statement = "insert into " + WeekTable + " values ("+ID+","+Day+","+Slot+")";
        //Excute the Query
        Connection_MySQL.Update(Statement);
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Set of Variables used by the class functions">
    //Controllers
    private LecturerData DataFrame;
    private LecturerView ViewFrame;
    private Connection_MySQL conn;
    private Query_Result QResult;

    //Set of columns of Lecturer Table
    private String LecturerTable;
    private String IDCol;
    private String NameCol;
    private String TypeCol;

    //Set of varables taken from the Lecturer Table
    private int ID;
    private int Type;
    private String Name;

    //Set of other Tables in the DB
    private String LoginTable;
    private String UNcol;
    private String PWCol;
    private String PW;
    private String User;
    private String WeekTable;
    private String LecturerID;// </editor-fold>
}
