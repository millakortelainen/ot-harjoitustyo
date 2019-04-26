/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoTest;

import dao.UserDao;
import domain.Database;
import domain.User;
import java.sql.SQLException;
import java.util.List;
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
public class UserDaoTest {

    UserDao userDaoTest;
    Database db;

    public UserDaoTest() throws SQLException {
        db = new Database(true);
        userDaoTest = new UserDao(db);
    }

    @Before
    public void setUp() throws SQLException {
        db.emptyDatabase();
    }

    @Test
    public void newUserDaoIsCreated() {
        assertNotNull(userDaoTest);
    }

    @Test
    public void newUserIsAdded() throws SQLException {
        User user = new User(1, "username", false);
        userDaoTest.create(user);
        User readUser = userDaoTest.read(1);
        assertEquals(user, readUser);
    }

    @Test
    public void projectsAreListed() throws SQLException {
        for (int i = 1; i <= 5; i++) {
            userDaoTest.create(new User(i, "username", false));
        }
        List<User> list = userDaoTest.list();
        assertEquals(5, list.size());
    }

    @Test
    public void notAddedUserIsNotFound() throws SQLException {
        User user = new User(1, "username", false);
        assertEquals(null, userDaoTest.read(1));
    }

    @Test
    public void createUserDaoWithEmptyConstuctor() throws SQLException {
        UserDao userDao = new UserDao();
        assertNotNull(userDao);
    }
}
