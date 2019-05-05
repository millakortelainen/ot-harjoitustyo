/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceTest;

import dao.UserDao;
import domain.Database;
import domain.User;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import service.UserService;

/**
 *
 * @author kortemil
 */
public class UserServiceTest {

    Database database;
    UserDao userDao;
    UserService userService;

    public UserServiceTest() throws SQLException {
        database = new Database(true);
        userDao = new UserDao(database);
        userService = new UserService(userDao);

    }

    @Test
    public void userServiceIsCreated() {
        assertNotNull(userService);
    }

    @Test
    public void newUserIsAdded() throws SQLException {
        database.emptyDatabase();
        userService.addNewUser("username");
        assertEquals(userDao.list().size(), 1);
    }

    @Test
    public void usernameIsAvailableBecauseThereIsNoUsers() throws SQLException {
        database.emptyDatabase();
        assertTrue(userService.usernameIsAvailable("username"));
    }

    @Test
    public void usernameIsNotAvailable() throws SQLException {
        database.emptyDatabase();
        userDao.create(new User("username", false));
        assertFalse(userService.usernameIsAvailable("username"));
    }

    @Test
    public void usernameIsAvailable() throws SQLException {
        database.emptyDatabase();
        userDao.create(new User("username", false));
        assertTrue(userService.usernameIsAvailable("usernameTest"));
    }

    @Test
    public void userIsAdmin() throws SQLException {
        database.emptyDatabase();
        userDao.create(new User("adminUser", true));
        assertTrue(userService.userIsAdmin("adminUser"));
    }
}
