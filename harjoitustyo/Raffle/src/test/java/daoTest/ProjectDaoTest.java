/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoTest;

import dao.ProjectDao;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import domain.Database;
import domain.Project;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author kortemil
 */
public class ProjectDaoTest {

    ProjectDao projectDaoTest;
    Database db;

    public ProjectDaoTest() throws SQLException {
        db = new Database(true);
        projectDaoTest = new ProjectDao(db);
    }

    @Before
    public void setUp() throws SQLException {
        db.emptyDatabase();
    }

    @Test
    public void newProjectDaoIsCreated() {
        assertNotNull(projectDaoTest);
    }

    @Test
    public void newProjectIsAdded() throws SQLException {
        Project project = new Project(1, "Test Project", "Description of test project", 1);
        projectDaoTest.create(project);
        Project readProject = projectDaoTest.read(1);
        assertEquals(project, readProject);
    }

    @Test
    public void projectsAreListed() throws SQLException {
        for (int i = 1; i <= 5; i++) {
            projectDaoTest.create(new Project(i, "Test Project", "Description of test project", 1));
        }
        List<Project> list = projectDaoTest.list();

        assertEquals(5, list.size());
    }

    @Test
    public void notAddedUserIsNotFound() throws SQLException {
        Project project = new Project(1, "subject of project", "description of project", 1);
        assertEquals(null, projectDaoTest.read(1));
    }

}
