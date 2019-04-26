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

    private ProjectCategoryDao projectCategoryDao;

    /**
     * Konstruktori
     *
     * @param projectCategoryDao
     */
    public ProjectCategoryService(ProjectCategoryDao projectCategoryDao) {
        this.projectCategoryDao = projectCategoryDao;
    }

    /**
     * Palauttaa parametrina annetun merkkijonolistan projektikategoria listana.
     *
     * @param categories merkkijono lista
     * @return projektikategoria lista
     * @throws SQLException
     */
    public List<ProjectCategory> stringListToProjectCategoryList(List<String> categories) throws SQLException {
        List<ProjectCategory> projectCategories = new ArrayList<>();
        for (String category : categories) {
            projectCategories.add(this.getProjectCategoryFromString(category));
        }
        return projectCategories;
    }

    /**
     * Palauttaa projektikategoria listan merkkijonolistana
     *
     * @param list lista projektikategorioista
     * @return projektikategoriat merkkijonoina
     */
    public ArrayList<String> projectCategoriesAsString(List<ProjectCategory> list) {

        ArrayList<String> projectCategories = new ArrayList<>();

        for (ProjectCategory projectCategory : list) {
            projectCategories.add(projectCategory.getCategory());
        }

        return projectCategories;

    }

    /**
     * Hakee merkkijono projektikategoriaa vastaavan projektikategorian
     *
     * @param projectCategory projektikategoria merkkijonona
     * @return palautettava olemassa oleva projektikategoria
     * @throws SQLException
     */
    public ProjectCategory getProjectCategoryFromString(String projectCategory) throws SQLException {
        List<ProjectCategory> projectCategories = projectCategoryDao.list();
        for (ProjectCategory pc : projectCategories) {
            if (pc.getCategory().equals(projectCategory)) {
                return pc;
            }
        }
        return null;
    }

    /**
     * Listaa projektikategorioiden id-numerot.
     *
     * @param projectCategories lista projektikategorioista
     * @return lista projektikategorioiden id-numeroista
     */
    public List<Integer> getCategoriesIdList(List<ProjectCategory> projectCategories) {
        List<Integer> categoryId = new ArrayList<>();
        for (ProjectCategory pc : projectCategories) {
            categoryId.add(pc.getId());
        }
        return categoryId;
    }

}
