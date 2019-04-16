/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ProjectCategoryDao;
import domain.ProjectCategory;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author kortemil
 */
public class ProjectCategoryService {

    ProjectCategoryDao projectCategoryDao;

    public ProjectCategoryService(ProjectCategoryDao projectCategoryDao) {
        this.projectCategoryDao = projectCategoryDao;
    }

   

    public ArrayList<String> projectCategories(List<ProjectCategory> list) {

        ArrayList<String> projectCategories = new ArrayList<>();

        for (ProjectCategory projectCategory : list) {
            projectCategories.add(projectCategory.getCategory());
        }

        return projectCategories;

    }

    public ProjectCategory getProjectCategory(String projectCategory) throws SQLException {
        List<ProjectCategory> projectCategories = projectCategoryDao.list();
        for (ProjectCategory pc : projectCategories) {
            if (pc.getCategory().equals(projectCategory)) {
                return pc;
            }
        }
        return null;
    }

}
