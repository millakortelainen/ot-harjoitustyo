/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raffle;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author kortemil
 */
public class RandomProject {

    public Project getRandomProject(List<Project> projects) {
        Collections.shuffle(projects);
        return projects.get(0);
    }

}
