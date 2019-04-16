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
import service.RaffleService;
import service.UserService;

/**
 *
 * @author kortemil
 */
public class RaffleServiceTest {

    Database database;
    UserDao userDao;
    ProjectDao projectDao;
    UserService userService;
    RaffleService raffleService;
    ProjectCategoryDao projectCategoryDao;
    ProjectCategoryService projectCategoryService;

    public RaffleServiceTest() throws SQLException {
        database = new Database(true);
        userDao = new UserDao(database);
        projectDao = new ProjectDao(database);
        userService = new UserService(userDao);
        projectCategoryDao = new ProjectCategoryDao(database);
        projectCategoryService = new ProjectCategoryService(projectCategoryDao);
        raffleService = new RaffleService(userDao, projectDao, projectCategoryDao);
    }

    @Before
    public void setUp() throws SQLException {
        database.emptyDatabase();
    }

    @Test
    public void raffleServiceIsCreated() {
        assertNotNull(raffleService);
    }

    @Test
    public void createNewUser() throws SQLException {
        raffleService.createNewUser("username");
        assertTrue(raffleService.userExist("username"));
    }

    @Test
    public void userDoesNotExist() throws SQLException {
        assertFalse(raffleService.userExist("username"));
    }

}
