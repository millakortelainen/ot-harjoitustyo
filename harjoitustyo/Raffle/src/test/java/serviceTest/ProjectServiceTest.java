/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceTest;

import dao.ProjectCategoryDao;
import dao.ProjectDao;
import dao.UserDao;
import domain.Database;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import service.ProjectCategoryService;
import service.ProjectService;
import service.UserService;

/**
 *
 * @author kortemil
 */
public class ProjectServiceTest {

    Database database;
    ProjectDao projectDao;
    ProjectService projectService;

    public ProjectServiceTest() throws SQLException {
        database = new Database(true);
        projectDao = new ProjectDao(database);
        projectService = new ProjectService(projectDao,
                new ProjectCategoryService(new ProjectCategoryDao(database)));
    }

    @Test
    public void newProjectServiceIsCreated() {
        assertNotNull(this.projectService);
    }

}
