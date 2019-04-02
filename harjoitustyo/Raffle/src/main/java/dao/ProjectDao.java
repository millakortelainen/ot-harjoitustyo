/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.Dao;
import java.sql.*;
import java.util.*;
import raffle.Database;
import raffle.Project;

/**
 *
 * @author kortemil
 */
public class ProjectDao implements Dao<Project, Integer> {

    Database database;

    public ProjectDao() {
        database = new Database();
    }

    @Override
    public void create(Project project) throws SQLException {

        PreparedStatement stmt = database.getConnection().prepareStatement("INSERT INTO project"
                + " (subject, description, projectCategory_id)"
                + " VALUES (?, ?, ?)");
        stmt.setString(1, project.getSubject());
        stmt.setString(2, project.getDescription());
        stmt.setInt(3, project.getProjectCategoryId());
        stmt.executeUpdate();
        stmt.close();
        database.getConnection().close();
    }

    @Override
    public Project read(Integer key) throws SQLException {

        PreparedStatement stmt = database.getConnection().prepareStatement("SELECT * FROM project WHERE id = ?");
        stmt.setInt(1, key);
        ResultSet rs = stmt.executeQuery();

        if (!rs.next()) {
            System.out.println("omf");
            return null;
        }

        Project p = new Project(rs.getInt("id"), rs.getString("subject"),
                rs.getString("description"), rs.getInt("projectCategory_id"));

        stmt.close();
        rs.close();
        database.getConnection().close();

        return p;
    }

    @Override
    public Project update(Project object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Project> list() throws SQLException {

        PreparedStatement stmt = database.getConnection().prepareStatement("SELECT * FROM Project");
        ResultSet rs = stmt.executeQuery();

        List<Project> projects = new ArrayList<>();

        while (rs.next()) {
            projects.add(this.read(rs.getInt("id")));
        }

        stmt.close();
        rs.close();
        database.getConnection().close();
        return projects;
    }

}
