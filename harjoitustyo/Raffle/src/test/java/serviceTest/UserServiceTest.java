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

    @Before
    public void setUp() throws SQLException {
        database.emptyDatabase();
    }

    @Test
    public void newUserIsAdded() throws SQLException {
        User user = new User(1, "newUsername", false);
        userService.addNewUser(user);
        assertNotNull(userDao.read(1));
    }

    @Test
    public void usernameIsUnavailable() throws SQLException {
        User user = new User(1, "newUsername", false);
        userService.addNewUser(user);
        assertFalse(userService.usernameIsAvailable(userService.usernames(userDao.list()), "newUsername"));
    }

    @Test
    public void usernameIsAvailableWhenListIsEmpty() throws SQLException {
        assertTrue(userService.usernameIsAvailable(userService.usernames(userDao.list()), "newUsername"));
    }

    @Test
    public void usernameIsAvailable() throws SQLException {
        User user = new User(1, "newUsername", false);
        userService.addNewUser(user);
        assertTrue(userService.usernameIsAvailable(userService.usernames(userDao.list()), "username"));
    }

    @Test
    public void whenListIsEmptyGetNextId() throws SQLException {
        assertEquals(1, userService.nextId(userDao.list()));
    }

    @Test
    public void getNextId() throws SQLException {
        for (int i = 1; i <= 5; i++) {
            userService.addNewUser(new User(i, "username", false));
        }
        assertEquals(6, userService.nextId(userDao.list()));
    }

}
