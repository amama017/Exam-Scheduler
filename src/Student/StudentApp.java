package Student;

import DataBase.Connection_MySQL;
import DataBase.Query_Result;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class StudentApp {
    public StudentApp(Connection_MySQL Connection,int ID) {
        this.ID = ID;
        //Set of Database columns
        LoginTable = "login";
        UNcol = "UserName";
        PWCol = "Password";
        LecturerTable = "lecturer";
        LecNameCol = "Name";
        StudentTable = "student";
        IDCol = "ID";
        FNameCol = "FName";
        LNameCol = "LName";
        YearCol = "Year";
        SemesterCol = "Semester";
        CreditCol = "Credit";
        AdvisorCol = "Advisor";
        CourseTable = "course";
        CodeCol = "Code";
        CourseCol = "Name";
        CourseCreditCol = "Credit";
        CourseTakenTable = "coursetaken";
        Coursetakenstd = "StudentID";
        //Get A Connection for the database
        conn = Connection;
    }

    // <editor-fold defaultstate="collapsed" desc="Student Manager Methods">
    void InitializeView(StudentView View) {
        System.out.println("Setting the Window Variables");
        ViewFrame = View;
        //Get the Data from the DB
        GetStudentData();
        //Set the TextBoxes of the View
        View.SetFName(FName);
        View.SetLName(LName);
        View.SetYear(Integer.toString(Year));
        View.SetSemester(Integer.toString(Sem));
        View.SetCredit(Integer.toString(Credit));
        View.SetAdvisor(Advisor);
        System.out.println("TextBoxes has been filled");
    }

    void InitializeData(){
        SetAdvisorField();
        //Set the TextBoxes of the View
        DataFrame.SetFName(FName);
        DataFrame.SetLName(LName);
        DataFrame.SetAdvisor(AdvisorID-1);
        DataFrame.SetUser(User);
        DataFrame.SetPW(PW);
        System.out.println("TextBoxes has been filled");
    }

    //Get the Student Data
    private void GetStudentData() {
        //Select all columns for the Student ID
        String Statement;
        Statement = "select * from " + StudentTable + "," + LoginTable + " where "
                + StudentTable + "." + IDCol + "=" + ID + " and " + LoginTable + "." + IDCol + "=" + ID + "";
        QResult = new Query_Result();
        //Excute the Query
        conn.Excute_Query(Statement, QResult);

        if(QResult.isSuccess())
        {
            try {
                if (QResult.getResult().next()){
                    System.out.println("Accessing User Data from DB");
                    //Set the Student data from the DB
                    FName = QResult.getResult().getString(FNameCol);
                    LName = QResult.getResult().getString(LNameCol);
                    Year = QResult.getResult().getInt(YearCol);
                    Sem = QResult.getResult().getInt(SemesterCol);
                    Credit = QResult.getResult().getInt(CreditCol);
                    AdvisorID = QResult.getResult().getInt(AdvisorCol);
                    PW = QResult.getResult().getString(PWCol);
                    User = QResult.getResult().getString(UNcol);
                    System.out.println("User Info has been Read");
                }
            } catch (SQLException ex) {
                System.out.println("Error in DB, Can't Access User with ID" + ID);
                Connection_MySQL.printSQLException(ex);
            }

            //to get the Advisor Name, Search for the AdvisorID in Lecturer Table.
            Statement = "select * from " + LecturerTable + " where " + IDCol + "=" + AdvisorID + "";
            //Excute Query
            conn.Excute_Query(Statement, QResult);
            if(QResult.isSuccess())
            {
                try {
                    if (QResult.getResult().next())
                        //Set the Name from the DB
                        Advisor = QResult.getResult().getString(LecNameCol);
                } catch (SQLException ex) {
                    System.out.println("Error in DB, Can't Access Lecturer with ID " + AdvisorID);
                    Connection_MySQL.printSQLException(ex);
                }
            }
        }
    }

    void SetCourseTable(StudentView View) {
        //Select all columns for the Student ID
        String Statement;
        Statement = "select * from " + CourseTable;
        QResult = new Query_Result();
        //Excute the Query
        conn.Excute_Query(Statement, QResult);

        if(QResult.isSuccess())
        {
            try {
                while (QResult.getResult().next()) {
                    System.out.println("Getting Course Data");
                    String r_Code = QResult.getResult().getString(CodeCol);
                    String r_Name = QResult.getResult().getString(CourseCol);
                    String r_Credit = QResult.getResult().getString(CourseCreditCol);
                    View.AddRow(r_Code, r_Name, r_Credit);
                }
            } catch (SQLException ex) {
                Connection_MySQL.printSQLException(ex);
            }
        }
        System.out.println("Course Table has been filled");

        System.out.println("Setting Courses that have been marked");
        //Select all columns for the coursetaken table for the req. student
        Statement = "select * from "+CourseTakenTable+" where "+Coursetakenstd +" = "+ ID;
        //Excute the Query
        conn.Excute_Query(Statement, QResult);

        if(QResult.isSuccess())
        {
            try {
                while (QResult.getResult().next()) {
                    View.editcourse(true, QResult.getResult().getRow());
                }
            } catch (SQLException ex) {
                Connection_MySQL.printSQLException(ex);
            }
        }
    }

    public void SetDataFrame(StudentData Dialog){
        DataFrame = Dialog;
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

    boolean CheckValidation(String text) {
        return algorithm.AlgorithmApp.getApplication().CheckValidation(text);
    }

    void SetAdvisorField() {
        JComboBox Ad = DataFrame.getAdvisorField();
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

    void Alter(String Name, String PW, String FName, String LName, int ID) {
        //Select all columns for the Student ID
        String Statement;
        Statement = "update "+StudentTable;
        Statement += " Set "+FNameCol+"='"+FName+"',"+LNameCol+"='"+LName+"',"+AdvisorCol+"="+ID;
        Statement += " where "+IDCol+"="+this.ID;
        //Excute the Query
        Connection_MySQL.Update(Statement);

        Statement = "update login";
        Statement += " Set UserName='"+Name+"', Password='"+PW+"'";
        Statement += " where "+IDCol+"="+this.ID+" and Type=3";
        //Excute the Query
        Connection_MySQL.Update(Statement);
    }

    StudentView GetSView() {
        return ViewFrame;
    }

    void SaveCourseChange(JTable CourseTable) {
        int IDColNum = 1;
        int RowNum = CourseTable.getRowCount();

        //Removing the courses taken by the Student
        System.out.println("Removing old Course Table");
        String Statement;
        Statement = "delete from " + CourseTakenTable + " where StudentID=" + ID;
        //Excute the Query
        Connection_MySQL.Update(Statement);

        //Resetting the new set of courses taken
        for(int i=0; i<RowNum; i++)
        {
            if(CourseTable.getValueAt(i, 0).equals(true))
                AddCourse(CourseTable.getValueAt(i, IDColNum).toString());
        }
    }

    private void AddCourse(String Code) {
        //Insert the course of Code->Code taken by the Student
        System.out.println("adding new Course to Student");
        String Statement;
        Statement = "insert into " + CourseTakenTable + " values (" + ID+",'"+Code+"')";
        //Excute the Query
        Connection_MySQL.Update(Statement);
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Set of Variables used by the class functions">
    //Controllers
    private StudentData DataFrame;
    private StudentView ViewFrame;
    private Connection_MySQL conn;
    private Query_Result QResult;

    //Set of Columns in the Student Table
    private String StudentTable;
    private String IDCol;
    private String FNameCol;
    private String LNameCol;
    private String YearCol;
    private String SemesterCol;
    private String AdvisorCol;
    private String CreditCol;

    //Set of variables taken from the Student Table
    private int ID;
    private String FName;
    private String LName;
    private int Year;
    private int Sem;
    private int AdvisorID;
    private String Advisor;
    private int Credit;

    //Set of other Tables in the DB
    private String LoginTable;
    private String UNcol;
    private String PWCol;
    private String PW;
    private String User;
    private String LecturerTable;
    private String LecNameCol;
    private String CourseTable;
    private String CodeCol;
    private String CourseCol;
    private String CourseCreditCol;
    private String CourseTakenTable;
    private String Coursetakenstd;// </editor-fold>
}
