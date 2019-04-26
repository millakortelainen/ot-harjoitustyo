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
     * Tarkistaa onko käyttäjänimeä vastaama käyttäjä olemassa.
     *
     * @param username haettava käyttäjänimi
     * @return totuusarvo sen mukaan onko käyttäjänimi käytössä
     * @throws SQLException
     */
    public boolean userExist(String username) throws SQLException {
        List<String> usernames = userService.usernames(userDao.list());
        return usernames.contains(username);
    }

    /**
     * Luo uuden käyttäjän käyttäen userService-luokkaa.
     *
     * @param username luotavan käyttäjän käyttäjänimi
     * @throws SQLException
     */
    public void createNewUser(String username) throws SQLException {
        if (userService.usernameIsAvailable(userService.usernames(userDao.list()), username)) {
            userService.addNewUser(new User(userService.nextId(userDao.list()), username, false));
        }
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

}
