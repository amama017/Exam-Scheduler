package DataBase;

import java.sql.*;

/*
 * this class is used to save the results of any query
 * in ResultSet -> Result
 * ResultSet is a set where the result tuples is saved (set of rows)
 * each raw is set of diffrent data.
 * where "success" is a boolean that trace is the query is performed successfuly.
 * and "exception" is a string that illustrate the error done during the transaction.
 */

public class Query_Result {

    private boolean success;
    private ResultSet Result;
    private String exception;

    public ResultSet getResult() {
        return Result;
    }

    public void setResult(ResultSet Result) {
        this.Result = Result;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
