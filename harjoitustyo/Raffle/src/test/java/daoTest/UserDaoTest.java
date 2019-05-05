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
    Database testDatabase;

    public UserDaoTest() throws SQLException {
        testDatabase = new Database(true);
        userDaoTest = new UserDao(testDatabase);
    }

    @Test
    public void newUserDaoIsCreated() {
        assertNotNull(userDaoTest);
    }

    @Test
    public void newUserIsAdded() throws SQLException {
        testDatabase.emptyDatabase();
        userDaoTest.create(new User("testUser", false));
        assertEquals(userDaoTest.list().size(), 1);

    }

    @Test
    public void usersAreListed() throws SQLException {
        testDatabase.emptyDatabase();
        for (int i = 1; i <= 5; i++) {
            userDaoTest.create(new User("username" + i, false));
        }
        assertEquals(userDaoTest.list().size(), 5);
    }

    @Test
    public void notAddedUserIsNotFound() throws SQLException {
        testDatabase.emptyDatabase();
        assertNull(userDaoTest.read("username"));
    }

    @Test
    public void createUserDaoWithEmptyConstuctor() throws SQLException {
        UserDao userDao = new UserDao();
        assertNotNull(userDao);
    }

}
