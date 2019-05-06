/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import service.UserService;
import dao.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import domain.*;

/**
 *
 * @author kortemil
 */
public class RaffleService {

    private Database database;
    private UserDao userDao;
    private UserService userService;
    private ProjectService projectService;
    private ProjectDao projectDao;
    private ProjectCategoryDao projectCategoryDao;
    private ProjectCategoryService projectCategoryService;

    /**
     * Konstruktori.
     *
     * @param userDao
     * @param projectDao
     * @param projectCategoryDao
     */
    public RaffleService(UserDao userDao, ProjectDao projectDao, ProjectCategoryDao projectCategoryDao) {

        this.userDao = userDao;
        this.projectDao = projectDao;
        this.userService = new UserService(userDao);
        this.projectCategoryDao = projectCategoryDao;
        this.projectCategoryService = new ProjectCategoryService(projectCategoryDao);
        this.projectService = new ProjectService(projectDao, projectCategoryService);
    }

    /**
     * Luo uuden käyttäjän käyttäen userService-luokkaa.
     *
     * @param username luotavan käyttäjän käyttäjänimi
     * @throws SQLException
     */
    public void createNewUser(String username) throws SQLException {
        if (userService.usernameIsAvailable(username)) {
            userService.addNewUser(username);
        }
    }

    public boolean userExist(String username) throws SQLException {
        return userService.usernameExists(username);
    }

    /**
     * Arpoo projektin käyttäen ProjectService-luokkaa.
     *
     * @param projectCategory kategoria, jonka käyttäjä on valinnut
     * @return arvottu projekti
     * @throws SQLException
     */
    public Project getRandomProject(List<String> projectCategory) throws SQLException {
        return projectService.getRandomProjectFromProjectCategory(projectCategoryService.stringListToProjectCategoryList(projectCategory));
    }

    /**
     * Luo merkkijono listan projektikategorioista käyttäen
     * ProjectCategoryService luokkaa.
     *
     * @return merkkijonolista projektikategorioista
     * @throws SQLException
     */
    public ArrayList<String> projectCategories() throws SQLException {
        return projectCategoryService.projectCategoriesAsString(projectCategoryDao.list());
    }

    /**
     *käyttäjän kirjautuminen järjestelmään
     * 
     * @param username käyttäjänimi merkkijonona
     * @return onko käyttäjätietokannassa
     * @throws SQLException
     */
    public boolean userLogInSuccesful(String username) throws SQLException {
        if (userService.usernameExists(username)) {
            return true;
        }
        return false;
    }

    /**
     * adminin kirjautuminen järjestelmään
     *
     * @param username käyttäjänimi merkkijonona
     * @return onko parametrina saatu käyttäjä admin
     * @throws SQLException
     */
    public boolean adminLogInSuccesful(String username) throws SQLException {
        return userService.usernameExists(username) && userService.userIsAdmin(username);

    }

    /**
     * luo uuden projekti kategorian
     *
     * @param projectCategory uusi projekti kategoria merkkijonona
     * @throws SQLException
     */
    public void createNewProjectCategory(String projectCategory) throws SQLException {
        projectCategoryService.createProjectCategory(projectCategory);
    }

    /**
     * luo uuden projektin tietokantaan
     *
     * @param project luotava projekti merkkijonona
     * @param projectCategory luotavan projektin kategoria merkkijonona
     * @throws SQLException
     */
    public void createNewProject(String project, String projectCategory) throws SQLException {
        projectService.createNewProject(project, projectCategoryService.getProjectCategoryFromString(projectCategory));
    }

    /**
     * Poistaa projektin tietokannasta
     *
     * @param project poistettava projekti
     * @throws SQLException
     */
    public void deleteProject(String project) throws SQLException {
        projectService.deleteProject(project);
    }

    /**
     * Listaa kaikki tietokannan projektit merkkijonoina
     *
     * @return tietokannan projektit merkkijonona
     * @throws SQLException
     */
    public ArrayList<String> projects() throws SQLException {
        return projectService.projectsAsStrings();
    }

    /**
     * Alustaa tietokannan
     *
     * @throws SQLException
     */
    public void initDatabase() throws SQLException {
        this.database = new Database();
        this.database.initTables();
    }

}
