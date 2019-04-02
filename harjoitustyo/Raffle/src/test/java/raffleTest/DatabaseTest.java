/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raffleTest;

import java.sql.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import raffle.Database;

/**
 *
 * @author kortemil
 */
public class DatabaseTest {

    Database database;

    public DatabaseTest() {
        database = new Database();

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void getConnectionTest() throws SQLException {
        Connection connection = database.getConnection();
        assertNotNull(connection);
    }

    @Test
    public void getTestConnectionTest() throws SQLException {
        Connection connection = database.getConnection();
        assertNotNull(connection);
    }
}
