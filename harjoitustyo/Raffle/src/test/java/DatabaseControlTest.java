/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;
import raffle.DatabaseControl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kortemil
 */
public class DatabaseControlTest {

    DatabaseControl control;
    Connection connection;
    Statement stmt;
//    ResultSet rs;

    public DatabaseControlTest() {
        control = new DatabaseControl();
    }

    @Before
    @Test
    public void connectDatabaseTest() throws SQLException {
        connection = control.connectDatabase();
        assertTrue(connection != null);
    }

    @Before
    @Test
    public void getStatementTest() throws SQLException {
        stmt = control.getStatement(connection);
        assertTrue(stmt != null);
    }

    // @Before
    @Test
    public void getResultSetTest() throws SQLException {
        String sqlStmt = "SELECT * FROM user;";
        ResultSet rs = control.getResultSet(stmt, sqlStmt);
        assertTrue(rs != null);
    }

    @Test
    public void disconnectDatabaseTest() throws SQLException {
        control.disconnectDatabase(connection);
        assertTrue(connection.isClosed());

    }

    @Test
    public void closeStatementTest() throws SQLException {
//.isClosed ei toimi.
    }

    @Test
    public void closeResultSetTest() throws SQLException {
        String sqlStmt = "SELECT * FROM user;";
        ResultSet rs = control.getResultSet(stmt, sqlStmt);
        control.closeResultSet(rs);
        assertTrue(rs.isClosed());
    }
}
