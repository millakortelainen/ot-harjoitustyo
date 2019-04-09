/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import service.RaffleService;
import dao.ProjectDao;
import dao.UserDao;
import java.sql.*;
import javafx.application.Application;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author kortemil
 */
public class RaffleUi extends Application {

    RaffleService raffleService;

    @Override
    public void init() throws Exception {

        raffleService = new RaffleService(new UserDao(), new ProjectDao());
    }

    @Override
    public void start(Stage window) throws SQLException {

        int widthOfWindow = 400;
        int hightOfWindow = 280;

        //1. view
        Label logInText = new Label("Kirjoita käyttäjätunnus ja paina kirjaudu");
        TextField usernameField = new TextField();
        Button logInBtn = new Button("Kirjaudu");
        Button newUserBtn = new Button("Luo uusi käyttäjä");
        Label errorText = new Label("");
        GridPane firstWindowLayout = new GridPane();
        firstWindowLayout.add(logInText, 0, 0);
        firstWindowLayout.add(usernameField, 0, 1);
        firstWindowLayout.add(logInBtn, 0, 2);
        firstWindowLayout.add(newUserBtn, 0, 3);
        firstWindowLayout.add(errorText, 0, 4);
        firstWindowLayout.setPrefSize(widthOfWindow, hightOfWindow);
        firstWindowLayout.setAlignment(Pos.CENTER);
        firstWindowLayout.setVgap(10);
        firstWindowLayout.setHgap(10);
        firstWindowLayout.setPadding(new Insets(20, 20, 20, 20));
        Scene firstWindow = new Scene(firstWindowLayout);

        //creating new user -view
        Label newUserText = new Label("Anna käyttäjänimi");
        TextField username = new TextField();
        Button createNewUserBtn = new Button("Luo käyttäjä");
        GridPane createNewUserLayout = new GridPane();
        createNewUserLayout.setPrefSize(widthOfWindow, hightOfWindow);
        createNewUserLayout.add(newUserText, 0, 0);
        createNewUserLayout.add(username, 0, 1);
        createNewUserLayout.add(createNewUserBtn, 0, 2);
        createNewUserLayout.setAlignment(Pos.CENTER);
        Scene createNewUserWindow = new Scene(createNewUserLayout);

        //user has logged in view
        Label welcome = new Label("Tervetuloa");
        Label project = new Label("");
        Button button = new Button("Arvo projektiaihe");
        GridPane userHasLoggedInLayout = new GridPane();
        userHasLoggedInLayout.setPrefSize(widthOfWindow, hightOfWindow);
        userHasLoggedInLayout.add(welcome, 0, 0);
        userHasLoggedInLayout.add(project, 0, 1);
        userHasLoggedInLayout.add(button, 0, 2);
        userHasLoggedInLayout.setAlignment(Pos.CENTER);
        Scene userHasLoggedInWindow = new Scene(userHasLoggedInLayout);

        //from first window to create new user window
        newUserBtn.setOnAction((event) -> {
            window.setScene(createNewUserWindow);
        });

        //from create user window to first window
        createNewUserBtn.setOnAction((event) -> {
            try {
                raffleService.createNewUser(username.getText());
//              if () {
//              virheteksti.setText("Tuntematon salasana!");
//              return;
//        }
            } catch (SQLException ex) {
                Logger.getLogger(RaffleUi.class.getName()).log(Level.SEVERE, null, ex);
            }

            window.setScene(firstWindow);
        }
        );

        //from first window to user has logged in window
        logInBtn.setOnAction(
                (event) -> {
                    window.setScene(userHasLoggedInWindow);
                }
        );

        //raffle new project
        button.setOnAction(e
                -> {
            try {
                project.setText(raffleService.getRandomProject().toString());
            } catch (SQLException ex) {
                Logger.getLogger(RaffleUi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        );

        window.setScene(firstWindow);
        window.show();

    }

}
