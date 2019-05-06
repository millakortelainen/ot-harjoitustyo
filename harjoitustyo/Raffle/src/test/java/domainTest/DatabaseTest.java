/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domainTest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import domain.Database;

/**
 *
 * @author kortemil
 */
public class DatabaseTest {

    Database testDatabase;

    public DatabaseTest() throws SQLException {
        testDatabase = new Database(true);
    }

    @Test
    public void getConnectionTest() throws SQLException {
        Database realDatabase = new Database();
        Connection connection = realDatabase.getConnection();
        assertNotNull(connection);
    }

    @Test
    public void getTestConnectionTest() throws SQLException {
        Connection connection = testDatabase.getConnection();
        assertNotNull(connection);
    }

    @Test
    public void emptyTestDatabase() throws SQLException {
        testDatabase.emptyDatabase();
        String sqlStmtProject = "SELECT * FROM Project";
        String sqlStmtProjectCategory = "SELECT * FROM ProjectCategory";
        String sqlStmtUser = "SELECT * FROM User";

        PreparedStatement stmt = testDatabase.getConnection().prepareStatement(sqlStmtProject);
        PreparedStatement stmt2 = testDatabase.getConnection().prepareStatement(sqlStmtProjectCategory);
        PreparedStatement stmt3 = testDatabase.getConnection().prepareStatement(sqlStmtUser);
        ResultSet rs = stmt.executeQuery();
        ResultSet rs2 = stmt2.executeQuery();
        ResultSet rs3 = stmt3.executeQuery();

        List<Integer> existingStuff = new ArrayList<>();
        List<String> existingStuff2 = new ArrayList<>();
        while (rs.next()) {
            existingStuff.add(rs.getInt("id"));
        }
        while (rs2.next()) {
            existingStuff.add(rs2.getInt("id"));
        }
        while (rs3.next()) {
            existingStuff2.add(rs3.getString("username"));
        }

        stmt.close();
        rs.close();
        rs2.close();
        rs3.close();
        assertTrue(existingStuff.isEmpty());
        assertTrue(existingStuff2.isEmpty());
    }

    @Test
    public void initEmptyDatabase() throws SQLException {
        this.testDatabase.emptyDatabase();
        this.testDatabase.initTables();
        String sqlStmtProject = "SELECT * FROM Project";
        String sqlStmtProjectCategory = "SELECT * FROM ProjectCategory";
        String sqlStmtUser = "SELECT * FROM User";

        PreparedStatement stmt = testDatabase.getConnection().prepareStatement(sqlStmtProject);
        PreparedStatement stmt2 = testDatabase.getConnection().prepareStatement(sqlStmtProjectCategory);
        PreparedStatement stmt3 = testDatabase.getConnection().prepareStatement(sqlStmtUser);
        ResultSet rs = stmt.executeQuery();
        ResultSet rs2 = stmt2.executeQuery();
        ResultSet rs3 = stmt3.executeQuery();

        List<String> existingStuff = new ArrayList<>();
        List<String> existingStuff2 = new ArrayList<>();
        List<String> existingStuff3 = new ArrayList<>();
        while (rs.next()) {
            existingStuff.add(rs.getString("subject"));
        }
        while (rs2.next()) {
            existingStuff2.add(rs2.getString("category"));
        }
        while (rs3.next()) {
            existingStuff3.add(rs3.getString("username"));
        }
        stmt.close();
        rs.close();
        rs2.close();
        rs3.close();
        assertEquals(33, existingStuff.size());
        assertEquals(4, existingStuff2.size());
        assertEquals(1, existingStuff3.size());
    }

    public void tablesNeedReset() throws SQLException {
        this.testDatabase.emptyDatabase();
        assertTrue(this.testDatabase.tablesNeedReset());
    }

    public void tablesDoNotNeedReset() throws SQLException {
        this.testDatabase.emptyDatabase();
        this.testDatabase.resetDatabase();
        assertFalse(this.testDatabase.tablesNeedReset());
    }

}
