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
import dao.UserDao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kortemil
 */
public class Database {

    private boolean testing;

    /**
     * Konstruktori parametrilla
     *
     * @param testing true-jos kyseessä on testaus
     * @throws SQLException
     */
    public Database(boolean testing) throws SQLException {
        this.testing = testing;
    }

    /**
     * Konstruktori ilman parametria asettaa testauksen automaattisesti pois
     * päältä
     *
     * @throws SQLException
     */
    public Database() throws SQLException {
        this.testing = false;
    }

    /**
     * hakee yhteyden tietokantaan
     *
     * @return yhteys tietokantaan
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        if (testing) {
            return DriverManager.getConnection("jdbc:sqlite:test_projects.db");
        }
        return DriverManager.getConnection("jdbc:sqlite:projects.db");
    }

    /**
     * alustaa tietokannan ja sen taulut, jos tarvetta
     *
     * @throws SQLException
     */
    public void initTables() throws SQLException {

        Statement stmt = this.getConnection().createStatement();

        String sqlStatement = "CREATE TABLE IF NOT EXISTS  user (username varchar(20) primary key, isModerator boolean);";
        String sqlStatement2 = "CREATE TABLE IF NOT EXISTS project (id integer primary key, subject "
                + "varchar(50), description varchar(350), projectCategory_id integer,"
                + " foreign key(projectCategory_id) references projectCategory(id));";
        String sqlStatement3 = "CREATE TABLE IF NOT EXISTS projectCategory (id integer primary key, "
                + "category varchar(100));";
        stmt.executeUpdate(sqlStatement);
        stmt.executeUpdate(sqlStatement2);
        stmt.executeUpdate(sqlStatement3);

        if (this.tablesNeedReset()) {
            this.resetDatabase();
        }

        stmt.close();
        this.getConnection().close();

    }

    /**
     * alustaa tietokannan taulut
     *
     * @throws SQLException
     */
    public void resetDatabase() throws SQLException {

        String projectCategories = "Hyötyohjelma\n"
                + "Reaaliaikainen peli\n"
                + "Vuoropohjainen peli\n"
                + "Korttipeli";

        String[] categories = projectCategories.split("\n");
        ProjectCategoryDao categoryDao = new ProjectCategoryDao(this);
        for (int i = 0; i < categories.length; i++) {
            categoryDao.create(new ProjectCategory(i + 1, categories[i]));
        }

        String hyotyohjelmat = "Tehtävägeneraattori, matematiikka \n"
                + "Tehtävägeneraattori fysiikka \n"
                + "Tehtävägeneraattori kemia\n"
                + "Opintojen seurantajärjestelmä\n"
                + "Telegrambotti\n"
                + "Slackbotti\n"
                + "Code Snippet Manageri\n"
                + "Laskin\n"
                + "Funktiolaskin\n"
                + "Graafinen laskin\n"
                + "Budjetointisovellus\n"
                + "Opintojen seurantasovellus\n"
                + "HTML WYSIWYG-editor (What you see is what you get)";

        String reaaliaikaisetPelit = "Tetris\n"
                + "Ping Pong\n"
                + "Pacman\n"
                + "Tower Defence\n"
                + "Asteroids\n"
                + "Space Invaders\n"
                + "Yksinkertainen tasohyppypeli";

        String vuoroPohjaisetPelit = "Tammi\n"
                + "Yatzy\n"
                + "Miinaharava\n"
                + "Laivanupotus\n"
                + "Yksinkertainen roolipeli\n"
                + "Yksinkertainen luolastoseikkailupeli\n"
                + "Sudoku\n"
                + "Muistipeli\n"
                + "Ristinolla";

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

        UserDao ud = new UserDao(this);
        ud.create(new User("admin", true));

    }

    /**
     * tarkistaa onko tietokannan taulut tyhjiä
     *
     * @return true, jos taulut ovat tyhjiä
     * @throws SQLException
     */
    public boolean tablesNeedReset() throws SQLException {
        Statement stmt = this.getConnection().createStatement();
        String sqlStatement4 = "SELECT * FROM Project;";
        ResultSet resultSet = stmt.executeQuery(sqlStatement4);
        List<Integer> projects = new ArrayList<>();
        while (resultSet.next()) {
            projects.add(resultSet.getInt("id"));
        }
        return projects.isEmpty();
    }

    /**
     * tyhjentää tietokanta taulut
     *
     * @throws SQLException
     */
    public void emptyDatabase() throws SQLException {
        Statement stmt = this.getConnection().createStatement();
        String sqlStatement = "DELETE FROM Project;"
                + "DELETE FROM ProjectCategory;"
                + "DELETE FROM User;";
        stmt.executeUpdate(sqlStatement);

        stmt.close();
        this.getConnection().close();
    }

}
