/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domainTest;

import domain.User;
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
public class UserTest {

    User user;

    public UserTest() {
        user = new User(1, "username", false);
    }

    @Test
    public void userIsCreated() {
        assertTrue(user != null);
    }

}
