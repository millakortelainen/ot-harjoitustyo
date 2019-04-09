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

/**
 *
 * @author kortemil
 */
public class ProjectDaoTest {

    ProjectDao projectDaoTest;
    Database db;

    public ProjectDaoTest() {
        projectDaoTest = new ProjectDao();
        db = new Database();
    }

    @Before
    public void setUp() throws Exception {
        
        
//        userFile = testFolder.newFile("testfile_users.txt");  
//        
//        try (FileWriter file = new FileWriter(userFile.getAbsolutePath())) {
//            file.write("testertester;Teppo Testaaja\n");
//        }
//        
//        dao = new FileUserDao(userFile.getAbsolutePath());
    }

    @Test
    public void newProjectDaoIsCreated() {
        assertNotNull(projectDaoTest);
    }

}
