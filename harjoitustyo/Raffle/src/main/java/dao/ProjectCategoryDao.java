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

    Database database;

    public ProjectCategoryDao(Database database) {
        this.database = database;
    }

    public ProjectCategoryDao() throws SQLException {
        this(new Database());
    }

    @Override
    public void create(ProjectCategory category) throws SQLException {
        PreparedStatement stmt = database.getConnection().prepareStatement("INSERT INTO projectCategory"
                + " (category)"
                + " VALUES (?)");
        stmt.setString(1, category.getCategory());
        stmt.executeUpdate();
        stmt.close();
        database.getConnection().close();
    }

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

    @Override
    public ProjectCategory update(ProjectCategory object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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
