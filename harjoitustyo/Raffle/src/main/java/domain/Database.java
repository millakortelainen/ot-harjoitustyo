/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.ProjectCategory;
import domain.Project;
import dao.ProjectCategoryDao;
import dao.ProjectDao;
import java.sql.*;

/**
 *
 * @author kortemil
 */
public class Database {

    private boolean testing = false;

    public Database(boolean testing) {
        this.testing = testing;
    }

    public Database() {
        this(false);
    }

    public Connection getConnection() throws SQLException {
        if (testing) {
            return DriverManager.getConnection("jdbc:sqlite:test_projects.db");
        }
        return DriverManager.getConnection("jdbc:sqlite:projects.db");
    }

    public void resetDatabase() throws SQLException {
        System.out.println("WARNING");
        System.out.println("YOU ARE DELETING WHOLE DATABASE");

        String projectCategories = "Hyötyohjelmat\n"
                + "Reaaliaikaiset pelit\n"
                + "Vuoropohjaiset pelit\n"
                + "Korttipelit";

        String[] categories = projectCategories.split("\n");
        ProjectCategoryDao categoryDao = new ProjectCategoryDao(this);
        for (int i = 0; i < categories.length; i++) {
            categoryDao.create(new ProjectCategory(i + 1, categories[i]));
        }

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
        int id = 1;
        ProjectDao pd = new ProjectDao(this);
        for (int i = 0; i < category1.length; i++) {
            pd.create(new Project(id, category1[i], "", 1));
            id++;
        }

        for (int i = 0; i < category2.length; i++) {
            pd.create(new Project(id, category2[i], "", 2));
            id++;
        }
        for (int i = 0; i < category3.length; i++) {
            pd.create(new Project(id, category3[i], "", 3));
            id++;
        }
        for (int i = 0; i < category4.length; i++) {
            pd.create(new Project(id, category4[i], "", 4));
            id++;
        }

    }

    public void emptyDatabase() throws SQLException {
        System.out.println("DELETING WHOLE DATABSE");
        Statement stmt = this.getConnection().createStatement();
        String sqlStatement = "DELETE FROM Project;"
                + "DELETE FROM ProjectCategory;"
                + "DELETE FROM User;";
        String[] sqlStatements = sqlStatement.split("\n");
        for (int i = 0; i < sqlStatements.length; i++) {
            stmt.executeUpdate(sqlStatements[i]);
        }

        stmt.close();
        this.getConnection().close();
    }

}