/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import domain.Database;
import domain.Project;
import domain.ProjectCategory;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author kortemil
 */
public class ProjectCategoryDao implements Dao<ProjectCategory, Integer> {

    private Database database;

    /**
     * Konstruktori, jolle voi asettaa haluamansa tietokanta yhteyden
     *
     * @param database
     */
    public ProjectCategoryDao(Database database) {
        this.database = database;
    }

    /**
     * Konstruktori ilman parametreja luo oletus muotoisen tietokanta yhteyden
     *
     * @throws SQLException
     */
    public ProjectCategoryDao() throws SQLException {
        this(new Database());
    }

    /**
     * luo uuden projekti kategorian tietokantaan
     *
     * @param category luotava projekti kategoria
     * @throws SQLException
     */
    @Override
    public void create(ProjectCategory category) throws SQLException {
        PreparedStatement stmt = database.getConnection().prepareStatement("INSERT INTO projectCategory"
                + " (id, category)"
                + " VALUES (?,?)");
        stmt.setInt(1, category.getId());
        stmt.setString(2, category.getCategory());
        stmt.executeUpdate();
        stmt.close();
        database.getConnection().close();
    }

    /**
     * Lukee projekti kategorian tietokannasta
     *
     * @param key luettavan projekti kategorian id-numero
     * @return haettu projekti kategoria
     * @throws SQLException
     */
    @Override
    public ProjectCategory read(Integer key) throws SQLException {
        PreparedStatement stmt = database.getConnection().prepareStatement("SELECT * FROM projectCategory WHERE id = ?");
        stmt.setInt(1, key);
        ResultSet rs = stmt.executeQuery();

        if (!rs.next()) {
            return null;
        }

        ProjectCategory pc = new ProjectCategory(rs.getInt("id"),
                rs.getString("category"));

        stmt.close();
        rs.close();
        database.getConnection().close();

        return pc;
    }

    /**
     * Listaa kaikki projekti kategoriat tietokannasta
     *
     * @return lista projekti kategorioista
     * @throws SQLException
     */
    @Override
    public List<ProjectCategory> list() throws SQLException {
        PreparedStatement stmt = database.getConnection().prepareStatement("SELECT * FROM ProjectCategory");
        ResultSet rs = stmt.executeQuery();

        List<ProjectCategory> projectCategories = new ArrayList<>();

        while (rs.next()) {
            projectCategories.add(this.read(rs.getInt("id")));
        }

        stmt.close();
        rs.close();
        database.getConnection().close();
        return projectCategories;
    }

}
