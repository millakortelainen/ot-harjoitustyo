/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raffle;

import dao.ProjectDao;
import java.sql.*;

/**
 *
 * @author kortemil
 */
public class Database {

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:projects.db");
       
    }

    public Connection getTestConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:test_projects.db");
    }

    public void resetDatabase() throws SQLException {
        System.out.println("WARNING");
        System.out.println("YOU ARE DELETING WHOLE DATABASE");

        String hyotyohjelmat = "Aritmetiikan harjoittelua\n"
                + "Tehtävägeneraattori, joka antaa käyttäjälle tehtävän sekä mallivastauksen (esim. matematiikkaa, fysiikkaa, kemiaa, ...)\n"
                + "Opintojen seurantajärjestelmä\n"
                + "Telegram- tai Slack-botti\n"
                + "Code Snippet Manageri\n"
                + "Laskin, funktiolaskin, graafinen laskin\n"
                + "Budjetointisovellus\n"
                + "Opintojen seurantasovellus\n"
                + "HTML WYSIWYG-editor (What you see is what you get)";

        String reaaliaikaisetPelit = "Reaaliaikaiset pelit\n"
                + "Tetris\n"
                + "Pong\n"
                + "Pacman\n"
                + "Tower Defence\n"
                + "Asteroids\n"
                + "Space Invaders\n"
                + "Yksinkertainen tasohyppypeli, esimerkiksi The Impossible Game";

        String vuoroPohjaisetPelit = "Tammi\n"
                + "Yatzy\n"
                + "Miinaharava\n"
                + "Laivanupotus\n"
                + "Yksinkertainen roolipeli tai luolastoseikkailu\n"
                + "Sudoku\n"
                + "Muistipeli\n"
                + "Ristinolla (mielivaltaisen kokoisella ruudukolla?)";

        String korttipelit = "En Garde\n"
                + "Pasianssi\n"
                + "UNO\n"
                + "Texas Hold'em";

        String[] category1 = hyotyohjelmat.split("\n");
        String[] category2 = reaaliaikaisetPelit.split("\n");
        String[] category3 = vuoroPohjaisetPelit.split("\n");
        String[] category4 = korttipelit.split("\n");

        ProjectDao pd = new ProjectDao();
        for (int i = 0; i < category1.length; i++) {
            pd.create(new Project(i, category1[i], "", 1));
        }

        for (int i = 0; i < category2.length; i++) {
            pd.create(new Project(i, category2[i], "", 2));
        }
        for (int i = 0; i < category3.length; i++) {
            pd.create(new Project(i, category3[i], "", 3));
        }
        for (int i = 0; i < category4.length; i++) {
            pd.create(new Project(i, category4[i], "", 4));
        }

    }

    public void resetTestDatabase() throws SQLException {
        
        

    }

    public void emptyTestDatabase() {

    }

}
