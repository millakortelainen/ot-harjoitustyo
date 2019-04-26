/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import dao.ProjectCategoryDao;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
        raffleService = new RaffleService(new UserDao(), new ProjectDao(), new ProjectCategoryDao());

    }

    @Override
    public void start(Stage window) throws SQLException {

        int widthOfWindow = 400;
        int hightOfWindow = 280;

        //1. view
        Label logInText = new Label("Kirjoita käyttäjätunnus ja paina kirjaudu");
        Label logInErrorText = new Label("");
        TextField usernameField = new TextField();
        Button logInBtn = new Button("Kirjaudu");
        Button newUserBtn = new Button("Luo uusi käyttäjä");
        GridPane firstWindowLayout = new GridPane();
        firstWindowLayout.add(logInText, 0, 0);
        firstWindowLayout.add(usernameField, 0, 1);
        firstWindowLayout.add(logInBtn, 0, 2);
        firstWindowLayout.add(newUserBtn, 0, 3);
        firstWindowLayout.add(logInErrorText, 0, 4);
        firstWindowLayout.setPrefSize(widthOfWindow, hightOfWindow);
        firstWindowLayout.setAlignment(Pos.CENTER);
        firstWindowLayout.setVgap(10);
        firstWindowLayout.setHgap(10);
        firstWindowLayout.setPadding(new Insets(20, 20, 20, 20));
        Scene firstWindow = new Scene(firstWindowLayout);

        //creating new user -view
        Label newUserText = new Label("Anna käyttäjänimi");
        TextField username = new TextField();
        Label newUserErrorText = new Label("");
        Button createNewUserBtn = new Button("Luo käyttäjä");
        GridPane createNewUserLayout = new GridPane();
        createNewUserLayout.setPrefSize(widthOfWindow, hightOfWindow);
        createNewUserLayout.add(newUserText, 0, 0);
        createNewUserLayout.add(username, 0, 1);
        createNewUserLayout.add(createNewUserBtn, 0, 2);
        createNewUserLayout.add(newUserErrorText, 0, 3);
        createNewUserLayout.setAlignment(Pos.CENTER);
        Scene createNewUserWindow = new Scene(createNewUserLayout);

        //user has logged in view
        Label welcome = new Label("Tervetuloa");
        Label selectSubject = new Label("Minkä projektiaiheen haluat?");

        ObservableList<String> options
                = FXCollections.observableArrayList(raffleService.projectCategories());
        options.add("Kaikki");
        ComboBox<String> comboBox = new ComboBox(options);

        ArrayList<CheckBox> cbList = new ArrayList<>();
        ArrayList<String> categories = raffleService.projectCategories();
        for (String s : categories) {
            cbList.add(new CheckBox(s));
        }

        Label pickedRandomProjectLabel = new Label("");
        Button getRandomProjectButton = new Button("Arvo projektiaihe");
        GridPane userHasLoggedInLayout = new GridPane();
        userHasLoggedInLayout.setPrefSize(widthOfWindow, hightOfWindow);
        userHasLoggedInLayout.add(welcome, 0, 0);

        int n = 1;
        for (CheckBox cbObj : cbList) {
            userHasLoggedInLayout.add(cbObj, 0, n);
            cbObj.setIndeterminate(false);
            n++;
        }

        userHasLoggedInLayout.add(getRandomProjectButton, 0, n + 1);
        userHasLoggedInLayout.add(pickedRandomProjectLabel, 0, n + 2);
        userHasLoggedInLayout.setAlignment(Pos.CENTER);
        Scene userHasLoggedInWindow = new Scene(userHasLoggedInLayout);

        //admin view
        Button createNewProject = new Button("Luo uusi projekti");
        
        //from first window to create new user window
        newUserBtn.setOnAction((event) -> {
            window.setScene(createNewUserWindow);
        });

        //from create user window to first window
        createNewUserBtn.setOnAction((event) -> {
            try {
                if (raffleService.userExist(username.getText())) {
                    newUserErrorText.setText("Käyttäjänimi on jo käytössä");
                } else {
                    raffleService.createNewUser(username.getText());
                    window.setScene(firstWindow);
                }
            } catch (SQLException ex) {
                Logger.getLogger(RaffleUi.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        //from first window to user has logged in window
        // logging in
        logInBtn.setOnAction((event) -> {
            try {
                if (raffleService.userExist(usernameField.getText())) {
                    window.setScene(userHasLoggedInWindow);
                } else {
                    logInErrorText.setText("Väärä käyttäjätunnus");
                }
            } catch (SQLException ex) {
                Logger.getLogger(RaffleUi.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        //get random new project
        getRandomProjectButton.setOnAction((event) -> {
            try {
                List<String> selectedCategories = new ArrayList<>();
                for (CheckBox cb : cbList) {
                    if (cb.isSelected()) {
                        selectedCategories.add(cb.getText());
                    }
                }
//                System.out.println(selectedCategories.toString());

                pickedRandomProjectLabel.setText(raffleService.getRandomProject(selectedCategories).toString());
            } catch (SQLException ex) {
                Logger.getLogger(RaffleUi.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        window.setScene(firstWindow);
        window.show();

    }

}
