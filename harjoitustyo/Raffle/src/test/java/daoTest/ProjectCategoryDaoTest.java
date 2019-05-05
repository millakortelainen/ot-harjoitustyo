/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoTest;

import dao.ProjectCategoryDao;
import domain.Database;
import domain.ProjectCategory;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kortemil
 */
public class ProjectCategoryDaoTest {

    Database testDatabase;
    ProjectCategoryDao testProjectCategoryDao;

    public ProjectCategoryDaoTest() throws SQLException {
        this.testDatabase = new Database(true);
        this.testProjectCategoryDao = new ProjectCategoryDao(testDatabase);
    }

    @Test
    public void testProjectCategoryDaoExists() {
        assertNotNull(this.testProjectCategoryDao);
    }

    @Test
    public void projectCategoryDaoIsCreatedWithoutParameters() throws SQLException {
        ProjectCategoryDao projectCategoryDao = new ProjectCategoryDao();
        assertNotNull(projectCategoryDao);
    }

    @Test
    public void newProjectCategoryIsAddedToDatabase() throws SQLException {
        testDatabase.emptyDatabase();
        testProjectCategoryDao.create(new ProjectCategory(1, "test category"));
        assertEquals(testProjectCategoryDao.list().size(), 1);
    }

    @Test
    public void projectCategoryIsReadFromDatabase() throws SQLException {
        testDatabase.emptyDatabase();
        testProjectCategoryDao.create(new ProjectCategory(1, "test category"));
        assertNotNull(testProjectCategoryDao.read(1));
    }

    @Test
    public void projectCategoryIsNotReadFromDatabaseIfNotExist() throws SQLException {
        testDatabase.emptyDatabase();
        assertNull(testProjectCategoryDao.read(1));
    }

    @Test
    public void projectcategoryDaoListsAllExistingCategories() throws SQLException {
        this.testDatabase.emptyDatabase();
        for (int i = 1; i <= 5; i++) {
            this.testProjectCategoryDao.create(new ProjectCategory(i, "test category"));
        }
        assertEquals(this.testProjectCategoryDao.list().size(), 5);
    }

}
