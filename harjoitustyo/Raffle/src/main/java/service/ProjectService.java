/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ProjectDao;
import domain.Project;
import domain.ProjectCategory;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author kortemil
 */
public class ProjectService {

    private ProjectDao projectDao;
    private ProjectCategoryService projectCategoryService;

    /**
     * Konstruktori
     *
     * @param projectDao
     * @param projectCategoryService
     */
    public ProjectService(ProjectDao projectDao, ProjectCategoryService projectCategoryService) {
        this.projectDao = projectDao;
        this.projectCategoryService = projectCategoryService;
    }

    /**
     * Arpoo satunnaisen projektin annetuista projektikategorioista.
     *
     * @param list lista valituista projektikategorioista
     * @return sekoitetun listan ensimmäinen alkio
     * @throws SQLException
     */
    public Project getRandomProjectFromProjectCategory(List<ProjectCategory> list) throws SQLException {
        List<Project> projectsFromCategory = this.getProjectsFromCategories(list);
        Collections.shuffle(projectsFromCategory);
        return projectsFromCategory.get(0);
    }

    /**
     * Hakee projektikategorioiden projektit
     *
     * @param projectCategories listä projektikategorioista, joista haetaan
     * projektit
     * @return kaikki projektit halutuista kategorioista
     * @throws SQLException
     */
    public List<Project> getProjectsFromCategories(List<ProjectCategory> projectCategories) throws SQLException {
        List<Project> projectsInCategory = new ArrayList();
        List<Integer> idProjects = projectCategoryService.getCategoriesIdList(projectCategories);

        for (Project p : projectDao.list()) {
            if (idProjects.contains(p.getProjectCategoryId())) {
                projectsInCategory.add(p);
            }
        }
        return projectsInCategory;
    }

    public int nextId() throws SQLException {
        return projectDao.list().size() + 1;
    }

    public void createNewProject(String projectname, ProjectCategory pc) throws SQLException {
        projectDao.create(new Project(this.nextId(), projectname, "", pc.getId()));
    }

    public ArrayList<String> projectsAsStrings() throws SQLException {
        ArrayList<String> projects = new ArrayList<>();
        for (Project p : projectDao.list()) {
            projects.add(p.getSubject());
        }
        return projects;
    }

    public void deleteProject(String project) throws SQLException {
        projectDao.delete(this.projectId(project));
    }

    public int projectId(String project) throws SQLException {
        for (Project p : projectDao.list()) {
            if (p.getSubject().equals(project)) {
                return p.getId();
            }
        }
        return -1;
    }

}
