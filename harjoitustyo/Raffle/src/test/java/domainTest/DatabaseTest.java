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

    Database database;

    public DatabaseTest() throws SQLException {
        database = new Database(true);
    }

    @Test
    public void getConnectionTest() throws SQLException {
        Database realDatabase = new Database();
        Connection connection = realDatabase.getConnection();
        assertNotNull(connection);
    }

    @Test
    public void getTestConnectionTest() throws SQLException {
        Connection connection = database.getConnection();
        assertNotNull(connection);
    }

    @Test
    public void initTablesTest() throws SQLException {
        database.emptyDatabase();
        database.initTables();

        String sqlStmtProject = "SELECT * FROM Project";
        String sqlStmtProjectCategory = "SELECT * FROM ProjectCategory";
        String sqlStmtUser = "SELECT * FROM User";

        PreparedStatement stmt = database.getConnection().prepareStatement(sqlStmtProject);
        PreparedStatement stmt2 = database.getConnection().prepareStatement(sqlStmtProjectCategory);
        PreparedStatement stmt3 = database.getConnection().prepareStatement(sqlStmtUser);
        ResultSet rs = stmt.executeQuery();
        ResultSet rs2 = stmt2.executeQuery();
        ResultSet rs3 = stmt3.executeQuery();

        List<Integer> existingStuff = new ArrayList<>();

        while (rs.next()) {
            existingStuff.add(rs.getInt("id"));
        }
        while (rs2.next()) {
            existingStuff.add(rs2.getInt("id"));
        }
        while (rs3.next()) {
            existingStuff.add(rs3.getInt("id"));
        }

        stmt.close();
        rs.close();
        rs2.close();
        rs3.close();
        assertFalse(existingStuff.isEmpty());

    }

    @Test
    public void emptyTestDatabase() throws SQLException {
        database.emptyDatabase();
        String sqlStmtProject = "SELECT * FROM Project";
        String sqlStmtProjectCategory = "SELECT * FROM ProjectCategory";
        String sqlStmtUser = "SELECT * FROM User";

        PreparedStatement stmt = database.getConnection().prepareStatement(sqlStmtProject);
        PreparedStatement stmt2 = database.getConnection().prepareStatement(sqlStmtProjectCategory);
        PreparedStatement stmt3 = database.getConnection().prepareStatement(sqlStmtUser);
        ResultSet rs = stmt.executeQuery();
        ResultSet rs2 = stmt2.executeQuery();
        ResultSet rs3 = stmt3.executeQuery();

        List<Integer> existingStuff = new ArrayList<>();

        while (rs.next()) {
            existingStuff.add(rs.getInt("id"));
        }
        while (rs2.next()) {
            existingStuff.add(rs2.getInt("id"));
        }
        while (rs3.next()) {
            existingStuff.add(rs3.getInt("id"));
        }

        stmt.close();
        rs.close();
        rs2.close();
        rs3.close();
        assertTrue(existingStuff.isEmpty());
    }

    @Test
    public void resetDatabase() throws SQLException {
        database.emptyDatabase();
        database.resetDatabase();

        String sqlStmtProject = "SELECT * FROM Project";
        String sqlStmtProjectCategory = "SELECT * FROM ProjectCategory";
        String sqlStmtUser = "SELECT * FROM User";

        PreparedStatement stmt = database.getConnection().prepareStatement(sqlStmtProject);
        PreparedStatement stmt2 = database.getConnection().prepareStatement(sqlStmtProjectCategory);
        PreparedStatement stmt3 = database.getConnection().prepareStatement(sqlStmtUser);
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
//        System.out.println(existingStuff.hashCode());
        stmt.close();
        rs.close();
        rs2.close();
        rs3.close();
        assertEquals(33, existingStuff.size());
        assertEquals(4, existingStuff2.size());
        assertEquals(0, existingStuff3.size());

    }

}
