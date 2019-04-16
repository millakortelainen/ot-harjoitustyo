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
    
    UserDao userDao;
    UserService userService;
    ProjectService projectService;
    ProjectDao projectDao;
    ProjectCategoryDao projectCategoryDao;
    ProjectCategoryService projectCategoryService;
    
    public RaffleService(UserDao userDao, ProjectDao projectDao, ProjectCategoryDao projectCategoryDao) {
        this.userDao = userDao;
        this.projectDao = projectDao;
        this.projectService = new ProjectService();
        this.userService = new UserService(userDao);
        this.projectCategoryDao = projectCategoryDao;
        this.projectCategoryService = new ProjectCategoryService(projectCategoryDao);
    }
    
    public boolean userExist(String username) throws SQLException {
        List<String> usernames = userService.usernames(userDao.list());
        return usernames.contains(username);
    }
    
    public void createNewUser(String username) throws SQLException {
        if (userService.usernameIsAvailable(userService.usernames(userDao.list()), username)) {
            userService.addNewUser(new User(userService.nextId(userDao.list()), username, false));
        }
        
    }
    
    public Project getRandomProject(String projectCategory) throws SQLException {
        if (projectCategory.equals("Kaikki")) {
            
            return projectService.getRandomProject(projectDao.list());
        }
        return projectService.getRandomProjectFromProjectCategory(projectDao.list(), projectCategoryService.getProjectCategory(projectCategory));
    }
    
    public void selectProjectCategory() {
        
    }
    
    public ArrayList<String> projectCategories() throws SQLException {
        return projectCategoryService.projectCategories(projectCategoryDao.list());
    }
    
}
