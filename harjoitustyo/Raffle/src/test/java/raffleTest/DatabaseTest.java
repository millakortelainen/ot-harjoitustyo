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
