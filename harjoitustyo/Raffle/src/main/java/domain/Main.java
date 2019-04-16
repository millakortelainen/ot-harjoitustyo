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
        Database database = new Database();
//        database.emptyDatabase();
//        database.initTables();
//        UserDao ud = new UserDao(new Database(true));
//        ud.create(new User(1, "milla", false));
//        Application.launch(RaffleUi.class);
database.emptyDatabase();
database.initTables();
//        database.resetDatabase();
        String sqlStmtProject = "SELECT * FROM Project";
        String sqlStmtProjectCategory = "SELECT * FROM ProjectCategory";
        String sqlStmtUser = "SELECT * FROM User";

        PreparedStatement stmt = database.getConnection().prepareStatement(sqlStmtProject);
        PreparedStatement stmt2 = database.getConnection().prepareStatement(sqlStmtProjectCategory);
        PreparedStatement stmt3 = database.getConnection().prepareStatement(sqlStmtUser);
        ResultSet rs = stmt.executeQuery();
        ResultSet rs2 = stmt2.executeQuery();
        ResultSet rs3 = stmt3.executeQuery();

        List<String> existingStuff = new ArrayList<>();
        List<String> existingStuff2 = new ArrayList<>();
        List<String> existingStuff3 = new ArrayList<>();
        while (rs.next()) {
            existingStuff.add(rs.getString("subject"));
        }
        while (rs2.next()) {
            existingStuff2.add(rs2.getString("category"));
        }
        while (rs3.next()) {
            existingStuff3.add(rs3.getString("username"));
        }
        System.out.println(existingStuff.size());
        stmt.close();
        rs.close();
        rs2.close();
        rs3.close();

    }

}
