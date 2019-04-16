/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.Project;
import domain.ProjectCategory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author kortemil
 */
public class ProjectService {

    public Project getRandomProject(List<Project> projects) {
        Collections.shuffle(projects);
        return projects.get(0);
    }

    public Project getRandomProjectFromProjectCategory(List<Project> projects, ProjectCategory projectCategory) {
        List<Project> projectsFromCategory = new ArrayList<>();
        for (Project p : projects) {
            if (p.getProjectCategoryId() == projectCategory.getId()) {
                projectsFromCategory.add(p);
            }
        }
        Collections.shuffle(projectsFromCategory);
        return projectsFromCategory.get(0);
    }

}
