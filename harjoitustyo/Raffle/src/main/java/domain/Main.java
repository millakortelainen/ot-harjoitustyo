/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import domain.Database;
import dao.ProjectDao;
import dao.UserDao;
import ui.RaffleUi;

/**
 *
 * @author kortemil
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {

//        UserDao ud = new UserDao(new Database(true));
//        ud.create(new User(1, "milla", false));
        Application.launch(RaffleUi.class);
    }

}
