/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.ProjectCategory;
import java.util.*;

/**
 *
 * @author kortemil
 */
public class ProjectCategoryService {

    public ArrayList<String> projectCategories(List<ProjectCategory> list) {

        ArrayList<String> projectCategories = new ArrayList<>();

        for (ProjectCategory projectCategory : list) {
            projectCategories.add(projectCategory.getCategory());
        }

        return projectCategories;

    }

}
