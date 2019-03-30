package raffle;

import java.sql.*;

public class DatabaseControl {

    public Connection connectDatabase() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:projekti.db");

    }

    public void disconnectDatabase(Connection connection) throws SQLException {
        connection.close();
    }

    public Statement getStatement(Connection connection) throws SQLException {
        return connection.createStatement();
    }

    public void closeStatement(Statement stmt) throws SQLException {
        stmt.close();
    }

    public ResultSet getResultSet(Statement stmt, String sqlStmt) throws SQLException {
        return stmt.executeQuery(sqlStmt);
    }

    public void closeResultSet(ResultSet rs) throws SQLException {
        rs.close();
    }
}
