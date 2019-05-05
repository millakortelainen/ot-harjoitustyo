/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domainTest;

import domain.Project;
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
public class ProjectTest {

    Project project;

    public ProjectTest() {
        project = new Project(1, "subject", "description", 1);
    }

    @Test
    public void projectIsCreated() {
        assertTrue(project != null);
    }

    @Test
    public void toStringIsRight() {
        assertEquals(project.toString(), "subject");
    }
}
