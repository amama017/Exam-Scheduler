package DataBase;


import java.sql.*;
import java.util.Properties;


public class Connection_MySQL {

    // <editor-fold defaultstate="collapsed" desc="Variables used in the Class">
    private static Connection conn;
    private static String userName;
    private static String password;
    private static String DBMS;
    private static String serverName;
    private static String portNumber;
    private static String DB;// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Class Constructor">
    public Connection_MySQL()
    {
        DBMS = "mysql";
        serverName = "localhost";
        portNumber = "3306";
        DB = "algorithm";
        userName = "root";
        password = "";

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            //set the Driver by jdbc:mysql.

            conn = getConnection();
            //set conn by the Database connection
        }
        catch (SQLException e)
        {
            printSQLException(e);
            //if there is an exception in the connection
        }
        catch (ClassNotFoundException cE)
        {
            System.out.println("Class Not Found Exception: " + cE.toString());
        }
    }// </editor-fold>

    public Connection getConnection() throws SQLException
    {
        Connection connect = null;

        //Set User Name & Password
        Properties connectionProps = new Properties();
        connectionProps.put("user", userName);
        connectionProps.put("password", password);

        if (DBMS.equals("mysql"))
        {
            //set the connection by the connection of the DataBase
            connect = DriverManager.
                        getConnection("jdbc:" + DBMS + "://" + serverName +
                                      ":" + portNumber + "/" + DB, connectionProps);
        }

        System.out.println("Connected to database");
        return connect;
  }

    public void Excute_Query(String command,Query_Result result)
    {
        Statement stmt = null;
        ResultSet rs = null;
        result.setSuccess(true);
        try
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(command);
        }
        catch(SQLException e)
        {
           result.setSuccess(false);
           printSQLException(e);
           result.setException(e.getMessage());
        }

        if(result.isSuccess())
            result.setResult(rs);
        else
            result.setResult(null);
    }

    public static void Update(String command)
    {
        try
        {
            Statement stmt = conn.createStatement();
            int rowsEffected = stmt.executeUpdate(command);
            System.out.println(rowsEffected + " rows effected");
        }
        catch (SQLException ex)
        {
            printSQLException(ex);
        }
    }

    public static void printSQLException(SQLException ex)
    {
        for (Throwable e : ex)
        {
            if (e instanceof SQLException)
            {
                if (ignoreSQLException(((SQLException)e).getSQLState()) == false)
                {
                    e.printStackTrace(System.err);
                    System.err.println("SQLState: " + ((SQLException)e).getSQLState());
                    System.err.println("Error Code: " + ((SQLException)e).getErrorCode());
                    System.err.println("Message: " + ((SQLException)e).getMessage());
                    Throwable t = ex.getCause();
                    while(t != null)
                    {
                        System.out.println("Cause: " + t);
                        t = t.getCause();
                    }
                }
            }
        }
   }

    public static boolean ignoreSQLException(String sqlState)
    {
        // X0Y32: Jar file already exists in schema
        if (sqlState.equalsIgnoreCase("X0Y32")) return true;
        // 42Y55: Table already exists in schema
        if (sqlState.equalsIgnoreCase("42Y55")) return true;
        return false;
    }

    // <editor-fold defaultstate="collapsed" desc="Getters">
    public static String getDB() {
        return DB;
    }

    public static String getDBMS() {
        return DBMS;
    }

    public static Connection getConn() {
        return conn;
    }

    public static String getPassword() {
        return password;
    }

    public static String getPortNumber() {
        return portNumber;
    }

    public static String getServerName() {
        return serverName;
    }

    public static String getUserName() {
        return userName;
    }// </editor-fold>

}