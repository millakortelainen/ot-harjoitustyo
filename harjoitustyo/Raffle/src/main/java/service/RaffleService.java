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
    UserService userService = new UserService();
    ProjectService projectService;
    ProjectDao projectDao;

    public RaffleService(UserDao userDao, ProjectDao projectDao) {
        this.userDao = userDao;
        this.projectDao = projectDao;
        this.projectService = new ProjectService();
    }

    public void createNewUser(String username) throws SQLException {

        if (userService.usernameIsAvailable(userService.usernames(userDao.list()), username)) {
            userService.addNewUser(new User(userService.nextId(userDao.list()), username, false));
        }

    }

    public Project getRandomProject() throws SQLException {
        return projectService.getRandomProject(projectDao.list());
    }

}
