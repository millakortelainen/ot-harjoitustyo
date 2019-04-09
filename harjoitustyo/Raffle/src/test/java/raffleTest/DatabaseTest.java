/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raffleTest;

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

    public DatabaseTest() {
        database = new Database(true);
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
            existingStuff.add(rs.getInt("id"));
        }
        while (rs3.next()) {
            existingStuff.add(rs.getInt("id"));
        }

        stmt.close();
        rs.close();
        rs2.close();
        rs3.close();
        assertTrue(existingStuff.isEmpty());
    }

    @Test
    public void resetDatabase() throws SQLException {
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

        stmt.close();
        rs.close();
        rs2.close();
        rs3.close();
        assertEquals(existingStuff.toString(), "[Aritmetiikan harjoittelua, "
                + "Tehtävägeneraattori, joka antaa käyttäjälle tehtävän sekä mallivastauksen (esim. matematiikkaa, fysiikkaa, kemiaa, ...), "
                + "Opintojen seurantajärjestelmä, "
                + "Telegram- tai Slack-botti, "
                + "Code Snippet Manageri, "
                + "Laskin, funktiolaskin, graafinen laskin, "
                + "Budjetointisovellus, "
                + "Opintojen seurantasovellus, "
                + "HTML WYSIWYG-editor (What you see is what you get), "
                + "Reaaliaikaiset pelit, "
                + "Tetris, "
                + "Pong, "
                + "Pacman, "
                + "Tower Defence, "
                + "Asteroids, "
                + "Space Invaders, "
                + "Yksinkertainen tasohyppypeli, esimerkiksi The Impossible Game, "
                + "Tammi, "
                + "Yatzy, "
                + "Miinaharava, "
                + "Laivanupotus, "
                + "Yksinkertainen roolipeli tai luolastoseikkailu, "
                + "Sudoku, "
                + "Muistipeli, "
                + "Ristinolla (mielivaltaisen kokoisella ruudukolla?), "
                + "En Garde, "
                + "Pasianssi, "
                + "UNO, Texas Hold'em]");
        assertEquals(existingStuff2.toString(), "[Hyötyohjelmat, "
                + "Reaaliaikaiset pelit, "
                + "Vuoropohjaiset pelit, "
                + "Korttipelit]");

        assertEquals(existingStuff3.toString(), "[]");

    }

}
