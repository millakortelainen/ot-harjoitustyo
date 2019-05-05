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
import domain.Project;
import domain.ProjectCategory;
import domain.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

    @Test
    public void raffleServiceIsCreated() {
        assertNotNull(raffleService);
    }

    @Test
    public void createNewUser() throws SQLException {
        database.emptyDatabase();
        raffleService.createNewUser("username");
        assertTrue(raffleService.userExist("username"));
    }

    @Test
    public void creatingUserFails() throws SQLException {
        database.emptyDatabase();
        raffleService.createNewUser("username");
        raffleService.createNewUser("username");
        assertEquals(userDao.list().size(), 1);
    }

    @Test
    public void userDoesNotExist() throws SQLException {
        database.emptyDatabase();
        assertFalse(raffleService.userExist("username"));
    }

    @Test
    public void userExist() throws SQLException {
        database.emptyDatabase();
        raffleService.createNewUser("username");
        assertTrue(raffleService.userExist("username"));
    }

    @Test
    public void getRandomProject() throws SQLException {
        database.emptyDatabase();
        database.initTables();
        List<String> projectCategory = new ArrayList<>();
        projectCategory.add("Korttipeli");
        assertNotNull(raffleService.getRandomProject(projectCategory));
    }

    @Test
    public void projectCategoriesAsStringList() throws SQLException {
        database.emptyDatabase();
        database.initTables();
        assertNotNull(raffleService.projectCategories());
    }

    @Test
    public void userIsAbleToLogIn() throws SQLException {
        database.emptyDatabase();
        userDao.create(new User("username", false));
        assertTrue(raffleService.userLogInSuccesful("username"));
    }

    @Test
    public void userIsNotAbleToLogIn() throws SQLException {
        database.emptyDatabase();
        assertFalse(raffleService.userLogInSuccesful("username"));
    }

    @Test
    public void adminIsAbleToLogIn() throws SQLException {
        database.emptyDatabase();
        userDao.create(new User("adminUser", true));
        assertTrue(raffleService.adminLogInSuccesful("adminUser"));
    }

    @Test
    public void adminIsNotAbleToLogin() throws SQLException {
        database.emptyDatabase();
        assertFalse(raffleService.adminLogInSuccesful("adminUser"));
    }

    @Test
    public void userisNotAdminCannotLogInAsAdmin() throws SQLException {
        database.emptyDatabase();
        userDao.create(new User("user", false));
        assertFalse(raffleService.adminLogInSuccesful("user"));
    }

    @Test
    public void newProjectCategoryIsCreated() throws SQLException {
        database.emptyDatabase();
        raffleService.createNewProjectCategory("newProjectCategory");
        assertEquals(projectCategoryDao.list().size(), 1);
    }

    @Test
    public void newProjectisCreated() throws SQLException {
        database.emptyDatabase();
        raffleService.createNewProjectCategory("newProjectCategory");
        raffleService.createNewProject("testProject", "newProjectCategory");
        assertEquals(projectDao.list().size(), 1);
    }

    @Test
    public void deleteProjectTest() throws SQLException {
        database.emptyDatabase();
        projectCategoryDao.create(new ProjectCategory(1, "projectCategory"));
        projectDao.create(new Project(1, "subjectOfProject", "descriptionOfProject", 1));
        raffleService.deleteProject("subjectOfProject");
        assertTrue(projectDao.list().isEmpty());
    }

    @Test
    public void projectsAsStingListTest() throws SQLException {
        database.emptyDatabase();
        database.initTables();
        assertFalse(raffleService.projects().isEmpty());
    }

}
