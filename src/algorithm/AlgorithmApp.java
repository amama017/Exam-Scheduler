package algorithm;

import DataBase.Connection_MySQL;
import DataBase.Query_Result;
import java.sql.SQLException;
import Admin.*;
import Lecturer.*;
import Student.*;
import java.util.Arrays;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;


public class AlgorithmApp extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        //Setting up the Frames used in login
        MainFrame = new AlgorithmView(this);
        RegFrame = new RegisterView(MainFrame.getFrame(),this,true);

        //Set of Database columns
        LoginTable = "login";
        LecturerTable = "lecturer";
        StudentTable = "student";
        UserNameCol = "UserName";
        PasswordCol = "Password";
        TypeCol = "Type";
        IDCol = "ID";
        
        //Get A Connection for the database
        conn = new Connection_MySQL();
        
        //show first frame
        show(MainFrame);
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of AlgorithmApp
     */
    public static AlgorithmApp getApplication() {
        return Application.getInstance(AlgorithmApp.class);
    }

    // <editor-fold defaultstate="collapsed" desc="Manager Commands">
    // A function that Login using UserName or Password sent from the form
    public String Login(String Name, char[] PW) {
        //Select all Statement for the Primary Key "UserNameCol"
        String Statement;
        Statement = "select * from " + LoginTable + " where " + UserNameCol + "= '" + Name + "'";
        //Get a new Query_Result
        QResult = new Query_Result();

        //String used for the output ref. for the frame
        String Error = null;

        conn.Excute_Query(Statement, QResult);

        try {
            if (QResult.isSuccess()) {
                if (QResult.getResult().next()) {
                    //Get the UserName
                    System.out.print(QResult.getResult().getString(UserNameCol));
                    System.out.println(" is trying to access his account");

                    //Check if password is Right for this UserName
                    if (Arrays.equals(PW, QResult.getResult().getString(PasswordCol).toCharArray())) {
                        //if valid password.
                        Error = "Loging";
                        System.out.println("Valid Password");
                        RunFrame(QResult.getResult().getInt(TypeCol), QResult.getResult().getInt(IDCol));
                    } else {
                        //if the password is wrong.
                        Error = "Invalid Password";
                        System.out.println(Error + ": " + PW + " is entered");
                    }
                } //Send to the user that the UserName is wrong
                else {
                    Error = "Name not found in the Database";
                    System.out.println("the User" + Error);
                }
            }
        } catch (SQLException ex) {
            Connection_MySQL.printSQLException(ex);
        }

        return Error;
    }

    // A function that Opens Register View to regester the new User
    public void Registeration() {
        System.out.println("Openning Registeration Window");
        RegFrame = new RegisterView(MainFrame.getFrame(), this, true);
        this.hide(MainFrame);
        show(RegFrame);
    }

    // A function that Opens Register View to regester the new User
    public void ExitRegisteration() {
        RegFrame.show(false);
        show(MainFrame);
    }

    // A function that Check Validation of UserName.
    public boolean CheckValidation(String UserName) {
        boolean valid = false;

        QResult = new Query_Result();
        //Get any UserName with the same data from DB
        String Statement;
        Statement = "select * from " + LoginTable + " where " + UserNameCol + "= '" + UserName + "'";
        //excute the querey
        conn.Excute_Query(Statement, QResult);

        if (QResult.isSuccess()) {
            try {
                if (QResult.getResult().next()) {
                    //The UserName is found
                    System.out.print("The UserName Req is found in the DB = ");
                    System.out.println(QResult.getResult().getString(UserNameCol));
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

    // A function that adds the new user to database
    public void add(String UserName, String PW, int Type, int ID) {
        //add username & password
        String Statement;
        Type += 2;
        Statement = "insert into " + LoginTable + "(" + UserNameCol + "," + PasswordCol + "," + TypeCol + "," + IDCol + ")";
        Statement += "values('" + UserName + "','" + PW + "'," + Type + "," + ID + ")";

        Connection_MySQL.Update(Statement);
        System.out.println("New User '" + UserName+ "' is added to login table");
    }

    boolean CheckID(int Type, int ID) {
        //Check if the ID is found
        boolean found = false;

        QResult = new Query_Result();
        String Statement;

        //check if the ID is already used by the user
        Statement = "select * from " + LoginTable + " where " + IDCol + "= '" + ID + "'";
        conn.Excute_Query(Statement, QResult);

        if (QResult.isSuccess()) {
            try {
                if (QResult.getResult().next()) {
                    //The ID is found
                    System.out.println("The ID inserted is used in the DB");
                    found = false;
                } else {
                    //if the ID isn't used before
                    if (Type == 0) //if Type = 1 "Instructor"
                    {
                        Statement = "select * from " + LecturerTable + " where " + IDCol + "= '" + ID + "'";

                    } else //if Type = 2 "Student"
                    {
                        Statement = "select * from " + StudentTable + " where " + IDCol + "= '" + ID + "'";


                    }
                    conn.Excute_Query(Statement, QResult);

                    if (QResult.isSuccess()) {
                        try {
                            if (QResult.getResult().next()) {
                                //The ID is found & free
                                System.out.print("The ID inserted is found in the DB = ");
                                System.out.println(QResult.getResult().getInt(IDCol));
                                found = true;
                            } else {
                                //ID isn't found
                                found = false;
                                System.out.println("the ID inserted not found in the DB");
                            }
                        } catch (SQLException ex) {
                            Connection_MySQL.printSQLException(ex);
                        }
                    }
                }
            } catch (SQLException ex) {
                Connection_MySQL.printSQLException(ex);
            }
        }

        if (found) {
            return true;

        } else {
            return false;

        }
    }

    // A function that runs a specific window
    public void RunFrame(int Type, int ID) {
        if (Type == 3) {
            System.out.println("Openning Student View Window");
            StdFrame = new StudentView(conn,this, true,ID);
            show(StdFrame);
        } else if (Type == 2) {
            System.out.println("Openning Lecturer View Window");
            LecFrame = new LecturerView(conn,this,true,ID);
            show(LecFrame);
        } else {
            System.out.println("Openning Admin View Window");
            AdFrame = new AdminView(conn,this,true);
            show(AdFrame);
        }
        hide(MainFrame);
    }

    // A function that runs a specific window
    public void RunDialog(int Type) {
        if (Type == 3) {
            System.out.println("Openning Student Data Window");
            StudentData D = new StudentData(StdFrame, true);
            StdFrame.setDialog(D);
        } else if (Type == 2) {
            System.out.println("Openning Lecturer Data Window");
            LecturerData D = new LecturerData(LecFrame, true);
            LecFrame.setDialog(D);
        } else {
            System.out.println("Openning Admin Data Window");
            AdminData D = new AdminData(AdFrame,true);
            AdFrame.setDialog(D);
        }
        //hide(MainFrame);
    }

    // Getters
    public AlgorithmView GetMainFrame() {
        return MainFrame;
    }

    public RegisterView GetRegFrame() {
        return RegFrame;
    }// </editor-fold>

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(AlgorithmApp.class, args);
    }

    // <editor-fold defaultstate="collapsed" desc="Set of Variables used by the class functions">
    //Set of frames in the Program
    private AlgorithmView MainFrame;
    private RegisterView RegFrame;
    private StudentView StdFrame;
    private LecturerView LecFrame;
    private AdminView AdFrame;

    //Variables used for the DB
    private Connection_MySQL conn;
    private Query_Result QResult;
    private String LoginTable;
    private String LecturerTable;
    private String StudentTable;
    private String UserNameCol;
    private String PasswordCol;
    private String TypeCol;
    private String IDCol;// </editor-fold>
}
