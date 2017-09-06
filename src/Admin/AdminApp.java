package Admin;

import Computation.ConflictManager;
import Computation.ConflictTable;
import Computation.TimeTable;
import Computation.Error;
import DataBase.Connection_MySQL;
import DataBase.Query_Result;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class AdminApp {

    public AdminApp(Connection_MySQL Connection) {
        //Set of Database columns
        loginTable = "login";
        IDCol = "ID";
        UserCol = "UserName";
        PWCol = "Password";
        StdTable = "Student";
        FNameCol = "FName";
        LNameCol = "LName";
        YearCol = "Year";
        SemesterCol = "Semester";
        CreditCol = "Credit";
        AdvisorCol = "Advisor";
        LecturerTable = "Lecturer";
        LecNameCol = "Name";
        CourseInstTable = "CourseInst";
        InstCodeCol = "Code";
        CourseTable = "Course";
        CourseStdTable = "CourseTaken";
        StdCodeCol = "CourseCode";
        StdIDCol = "StudentID";

        //Get A Connection for the database
        conn = Connection;

        TableCreationCounter = 0;
    }

    //Set of Variables that is used in the Class
    private AdminData DataFrame;
    private AdminView ViewFrame;
    private DepEntry DepFrame;
    private StudentEntry StdFrame;
    private Connection_MySQL conn;
    private Query_Result QResult;
    private int TableCreationCounter;
    private boolean TableSuccess;
    private ConflictManager ConflictMain;

    //Department Data
    private int RoomNum;
    private int LabNum;

    //DataBase interface Variables
    private String loginTable;
    private String IDCol;
    private String UserCol;
    private String PWCol;

    //Student Table
    private String StdTable;
    private String FNameCol;
    private String LNameCol;
    private String YearCol;
    private String SemesterCol;
    private String AdvisorCol;
    private String CreditCol;

    //Lecturer Table
    private String LecturerTable;
    private String LecNameCol;

    //Course Instructor Table
    private String CourseTable;
    private String CourseInstTable;
    private String InstCodeCol;

    //Course Taken Table
    private String CourseStdTable;
    private String StdCodeCol;
    private String StdIDCol;

    void ShowDataFrame() {
        ViewFrame.hide();
        DataFrame.SetManager(this);
        InitializeData();
        DataFrame.show();
    }

    public void ShowViewFrame(){
        DataFrame.hide();
        ViewFrame.show();
    }

    void SetView(AdminView View) {
        ViewFrame = View;
    }

    public void SetDataFrame(AdminData Dialog){
        DataFrame = Dialog;
    }

    private void InitializeData() {
        //Select the admin UserName & Password
        String Statement;
        Statement = "select * from " + loginTable + " where "+ IDCol + "=0";
        QResult = new Query_Result();
        //Excute the Query
        conn.Excute_Query(Statement, QResult);

        if(QResult.isSuccess())
        {
            try {
                if (QResult.getResult().next()){
                    System.out.println("Accessing User Data from DB");
                    //Set the Student data from the DB
                    DataFrame.SetUser(QResult.getResult().getString(UserCol));
                    DataFrame.SetPW(QResult.getResult().getString(PWCol));
                    System.out.println("TextBoxes has been filled");
                }
            } catch (SQLException ex) {
                System.out.println("Error in DB, Can't Access User");
                Connection_MySQL.printSQLException(ex);
            }
        }
    }

    // A function that Check Validation of UserName.
    public boolean CheckValidation(String UserName) {
        boolean valid = false;

        QResult = new Query_Result();
        //Get any UserName with the same data from DB
        String Statement;
        Statement = "select * from " + loginTable + " where " + UserCol + "= '" + UserName + "'";
        //excute the querey
        conn.Excute_Query(Statement, QResult);

        if (QResult.isSuccess()) {
            try {
                if (QResult.getResult().next()) {
                    //The UserName is found
                    System.out.print("The UserName Req is found in the DB = ");
                    System.out.println(QResult.getResult().getString(UserCol));
                    valid = false;
                } else {
                    valid = true;
                    System.out.println("the UserName Req not found in the DB");
                }
            } catch (SQLException ex) {
                Connection_MySQL.printSQLException(ex);
            }
        }

        if (valid) {
            return true;

        }
        return false;
    }

    void Alter(String Name, String PW) {
        //Set the Admin Attribute in the DB
        String Statement;
        Statement = "update login";
        Statement += " Set UserName='"+Name+"', Password='"+PW+"'";
        Statement += " where "+IDCol+"=0 and Type=1";
        //Excute the Query
        Connection_MySQL.Update(Statement);
    }

    void ShowDepFrame() {
        DepEntry D = new DepEntry(ViewFrame, this, true);
        DepFrame = D;
        InitDep();
        ViewFrame.hide();
        DepFrame.show();
    }

    private void InitDep() {
        ReadDep();
        DepFrame.SetRoom(RoomNum);
        DepFrame.SetLab(LabNum);
    }

    void returnDep() {
        ViewFrame.show();
        DepFrame.hide();
    }

    void SaveDepData(String RoomNum, String LabNum) {
        this.RoomNum = Integer.parseInt(RoomNum);
        this.LabNum = Integer.parseInt(LabNum);
        try {
            FileWriter Writer = new FileWriter("src/Admin/resources/DepData");
            Writer.write(RoomNum);
            Writer.write('\n');
            Writer.write(LabNum);
            Writer.close();
        } catch (IOException ex) {
            Logger.getLogger(AdminApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void ShowStdFrame() {
        StudentEntry S = new StudentEntry(ViewFrame, this, true);
        StdFrame = S;
        InitStd();
        SetAdvisorField();
        ViewFrame.hide();
        StdFrame.show();
    }

    void InitStd() {
        JTable T = StdFrame.getTable();
        DefaultTableModel Model;
        Model = (DefaultTableModel)T.getModel();

        String Statement;
        Statement = "Select * From "+ StdTable;
        QResult = new Query_Result();

        conn.Excute_Query(Statement, QResult);

        if(QResult.isSuccess())
        {
            try {
                while (QResult.getResult().next()) {
                    System.out.println("Getting Students Data");
                    int ID = QResult.getResult().getInt(IDCol);
                    String FName = QResult.getResult().getString(FNameCol);
                    String LName = QResult.getResult().getString(LNameCol);
                    int Year = QResult.getResult().getInt(YearCol);
                    int Sem = QResult.getResult().getInt(SemesterCol);
                    int Credit = QResult.getResult().getInt(CreditCol);
                    int Advisor = QResult.getResult().getInt(AdvisorCol);
                    if(TableCreationCounter <= Model.getRowCount())
                        Model.insertRow(TableCreationCounter++, new Object[]{ID,FName,LName,Year,Sem,Credit,Advisor});
                }
            } catch (SQLException ex) {
                Connection_MySQL.printSQLException(ex);
            }
        }
    }

    void SetAdvisorField() {
        JComboBox Ad = StdFrame.getAdvisorField();
        DefaultComboBoxModel Model;
        Model = (DefaultComboBoxModel)Ad.getModel();
        Model.removeAllElements();

        //Select all columns for the Student ID
        String Statement;
        Statement = "select * from " + LecturerTable;
        QResult = new Query_Result();
        //Excute the Query
        conn.Excute_Query(Statement, QResult);

        if(QResult.isSuccess())
        {
            try {
                while (QResult.getResult().next()) {
                    Model.addElement(QResult.getResult().getString(LecNameCol));
                }
            } catch (SQLException ex) {
                Connection_MySQL.printSQLException(ex);
            }
        }

    }

    void returnStd() {
        ViewFrame.show();
        StdFrame.hide();
    }

    void CreateConflict() {
        ConflictMain = new ConflictManager();
        int coursenum = GetCount(InstCodeCol,CourseTable);
        int Lecnum = GetCount(IDCol,LecturerTable);
        int stdnum = GetCount(IDCol,StdTable);
        String[] codeindex = GetArrayCourse(coursenum);
        ConflictMain.SetArrayCourse(codeindex);
        ConflictMain.SetConflictData(coursenum, Lecnum, stdnum);
        boolean[][] Instcourse = ConflictInst(coursenum,Lecnum,codeindex);
        boolean[][] Stdcourse = ConflictStd(coursenum,stdnum,codeindex);
        System.out.println("Setting Courses with same instructor");
        for(int i=0; i<Lecnum; i++)
            for(int j=0; j<coursenum-1; j++)
                for(int k=j+1; k<coursenum; k++)
                {
                    if(Instcourse[i][j] && Instcourse[i][k])
                        ConflictMain.SetConflict(codeindex[j], codeindex[k]);
                }
        System.out.println("Setting Courses taken by same Student");
        for(int i=0; i<stdnum; i++)
            for(int j=0; j<coursenum-1; j++)
                for(int k=j+1; k<coursenum; k++)
                {
                    if(Stdcourse[i][j] && Stdcourse[i][k])
                        ConflictMain.SetConflict(codeindex[j], codeindex[k]);
                }
        System.out.println("Conflict Table have been Created");
        ConflictMain.SetConflictGraph();
        System.out.println("Conflict Graph have been Created");
    }

    private int GetCount(String Index, String Table){
        //Get the number of objects in the Table
        String Statement;
        Statement = "select count("+Index+") as num from " + Table;
        QResult = new Query_Result();
        //Excute the Query
        conn.Excute_Query(Statement, QResult);
        try {
            if(QResult.getResult().next())
            {
                System.out.println("Getting the Number of "+Table+"s");
                return QResult.getResult().getInt("num");
            }
        } catch (SQLException ex) {
            Connection_MySQL.printSQLException(ex);
        }
        return -1;
    }

    boolean[][] ConflictInst(int coursenum,int Lecnum, String[] CodeArr){
        int[] InstArr = GetArrayID(Lecnum, IDCol, LecturerTable);
        int IDindex,courseindex;
        
        //Martix have the Instructor & Courses done
        boolean[][] InstCourse = new boolean[Lecnum][coursenum];

        //Select all Courses having the same Instructor.
        String Statement;
        Statement = "select "+CourseInstTable+"."+InstCodeCol+","+CourseInstTable+"."+IDCol;
        Statement+= " from " + LecturerTable + "," + CourseInstTable;
        Statement+= " where "+LecturerTable+"."+IDCol+"="+CourseInstTable+"."+IDCol;
        QResult = new Query_Result();
        //Excute the Query
        conn.Excute_Query(Statement, QResult);

        if(QResult.isSuccess())
        {
            try {
                while (QResult.getResult().next()) {
                    IDindex = GetIndex(QResult.getResult().getInt(IDCol),InstArr);
                    courseindex = GetIndex(QResult.getResult().getString(InstCodeCol),CodeArr);
                    InstCourse[IDindex][courseindex] = true;
                }
            } catch (SQLException ex) {
                Connection_MySQL.printSQLException(ex);
            }
        }
        return InstCourse;
    }

    boolean[][] ConflictStd(int coursenum,int stdnum, String[] CodeArr){
        int[] StdArr = GetArrayID(stdnum, IDCol, StdTable);
        int IDindex,courseindex;

        //Martix have the Instructor & Courses done
        boolean[][] StdCourse = new boolean[stdnum][coursenum];

        //Select all Courses having the same Instructor.
        String Statement;
        Statement = "select "+CourseStdTable+"."+StdCodeCol+","+CourseStdTable+"."+StdIDCol;
        Statement+= " from " + StdTable + "," + CourseStdTable;
        Statement+= " where "+StdTable+"."+IDCol+"="+CourseStdTable+"."+StdIDCol;
        QResult = new Query_Result();
        //Excute the Query
        conn.Excute_Query(Statement, QResult);

        if(QResult.isSuccess())
        {
            try {
                while (QResult.getResult().next()) {
                    IDindex = GetIndex(QResult.getResult().getInt(StdIDCol),StdArr);
                    courseindex = GetIndex(QResult.getResult().getString(StdCodeCol),CodeArr);
                    StdCourse[IDindex][courseindex] = true;
                }
            } catch (SQLException ex) {
                Connection_MySQL.printSQLException(ex);
            }
        }
        return StdCourse;
    }

    private String[] GetArrayCourse(int coursenum) {
        //Get the Course Code and save it in an array of Strings to use it in the conflict table
        String[] Index = new String[coursenum];
        int i = 0;

        String Statement;
        Statement = "select * from " + CourseTable;
        QResult = new Query_Result();
        //Excute the Query
        conn.Excute_Query(Statement, QResult);

        if(QResult.isSuccess())
        {
            try {
                while (QResult.getResult().next()) {
                    Index[i] = QResult.getResult().getString(InstCodeCol);
                    i++;
                }
            } catch (SQLException ex) {
                Connection_MySQL.printSQLException(ex);
            }
        }

        return Index;
    }

    private int[] GetArrayID(int num, String Key, String Table) {
        //Get the Object Key and save it in an array of Strings to use it in the conflict table
        int[] Index = new int[num];
        int i = 0;

        String Statement;
        Statement = "select "+Key+" from " + Table;
        QResult = new Query_Result();
        //Excute the Query
        conn.Excute_Query(Statement, QResult);

        if(QResult.isSuccess())
        {
            try {
                while (QResult.getResult().next()) {
                    Index[i] = QResult.getResult().getInt(Key);
                    i++;
                }
            } catch (SQLException ex) {
                Connection_MySQL.printSQLException(ex);
            }
        }

        return Index;
    }

    private int GetIndex(int key, int[] Arr) {
        for(int i=0; i<Arr.length; i++)
        {
            if(Arr[i] == key)
                return i;
        }
        return -1;
    }

    private int GetIndex(String key, String[] Arr) {
        for(int i=0; i<Arr.length; i++)
        {
            if(Arr[i].contentEquals(key))
                return i;
        }
        return -1;
    }

    void ShowConflictTable() {
        String[] CArr = ConflictMain.GetArrayCourse();
        ConflictTable Table = new ConflictTable(ViewFrame, true);
        Table.setTitle("Conflict Table");
        DefaultTableModel Model = (DefaultTableModel) Table.GetTable().getModel();
        SetTableIndex(Model,CArr);
        SetTableCells(Model,CArr);
        Table.show(true);
    }

    private void SetTableIndex(DefaultTableModel Model,String[] Arr) {
        Model.addColumn("");
        for(int i=0; i<Arr.length; i++)
            Model.addColumn(Arr[i]);
    }

    private void SetTableCells(DefaultTableModel Model,String[] Arr) {
        boolean[][] T = ConflictMain.GetConflictTable();
        for(int i=0; i<Arr.length; i++)
        {
            Model.addRow(new Object[Arr.length]);
            Model.setValueAt(Arr[i], i, 0);
            for(int j=0; j<T[i].length; j++)
                if(T[i][j])
                    Model.setValueAt("X", i, j+1);
        }
    }

    void ShowTimeTable() {
        if(TableSuccess)
        {
            TimeTable[] Table = new TimeTable[RoomNum];
            for(int i=0; i<RoomNum; i++)
            {
                //Create Set of Tables by the number of halls
                Table[i] = new TimeTable(ViewFrame, true);
                Table[i].setTitle("Hall "+(i+1));
                DefaultTableModel Model = (DefaultTableModel) Table[i].GetTable().getModel();
                PrintTable(Model,i);
                Table[i].show(true);
            }
        }
        else
        {
            System.out.println("Error Happened in the Table Creation Check the Inputs");
            Error Message = new Error(ViewFrame, true);
            Message.setTitle("Error");
            Message.SetStatement("Error Happened in the Table Creation Check the Inputs");
            Message.show(true);
        }
    }

    void CreateTimeTable(){
        ReadDep();
        //Create The Time Table using Coloring Algorithm
        //String[] CArr = ConflictMain.GetArrayCourse();
        int coursenum = GetCount(InstCodeCol,CourseTable);
        String[] CArr = GetArrayCourse(coursenum);
        int[][] CData = GetCourseData(CArr);
        TableSuccess = ConflictMain.SetScheduele(RoomNum,CArr,CData,GetCFreeSlot(CArr));
    }

    private int[][] GetCourseData(String[] Arr) {
        //Get the Course Code and save it in an array of Strings to use it in the conflict table
        int[][] Index = new int[Arr.length][2];
        int i = 0;

        String Lec = "Thours";
        String Lab = "Lhours";
        String Sec = "Ahours";
        String Statement;
        Statement = "select * from " + CourseTable;
        QResult = new Query_Result();
        //Excute the Query
        conn.Excute_Query(Statement, QResult);

        if(QResult.isSuccess())
        {
            try {
                while (QResult.getResult().next()) {
                    Index[i][0] = QResult.getResult().getInt(Lec) + QResult.getResult().getInt(Sec);
                    Index[i][1] = QResult.getResult().getInt(Lab);
                    i++;
                }
            } catch (SQLException ex) {
                Connection_MySQL.printSQLException(ex);
            }
        }

        return Index;
    }

    private Vector<Integer>[][] GetCFreeSlot(String[] Arr) {
        //Get the free slots for a course and save it in a Matrix of vectors to use it in the Coloring
        Vector<Integer>[][] Index = new Vector[Arr.length][5];
        for(int j=0; j<Arr.length; j++)
            for(int k=0; k<5; k++)
                Index[j][k] = new Vector();
        int tempday = -1;
        int Cindex = -1;

        String Code = "CourseCode";
        String Day = "Day";
        String Slot = "Slot";
        String Constraint = "CourseConstraint";

        String Statement;
        Statement = "select * from " + Constraint;
        QResult = new Query_Result();
        //Excute the Query
        conn.Excute_Query(Statement, QResult);

        if(QResult.isSuccess())
        {
            try {
                while (QResult.getResult().next()){
                    tempday = QResult.getResult().getInt(Day)-1;
                    Cindex = GetIndex(QResult.getResult().getString(Code), Arr);
                    Index[Cindex][tempday].add(QResult.getResult().getInt(Slot)-1);
                }
            } catch (SQLException ex) {
                Connection_MySQL.printSQLException(ex);
            }
        }

        return Index;
    }

    private void ReadDep() {
        Scanner Input;
        try {
            Input = new Scanner(new FileInputStream("src/Admin/resources/DepData"));
            RoomNum = Input.nextInt();
            LabNum = Input.nextInt();
            Input.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AdminApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void PrintTable(DefaultTableModel Model, int hall) {
        String[][] Table = ConflictMain.GetTimeTable(hall);
        for(int i=0; i<5; i++)
            for(int j=0; j<9; j++)
            {
                if(j!=3)
                {
                    Model.setValueAt(Table[i][j], i, j+1);
                }
            }
    }
}
