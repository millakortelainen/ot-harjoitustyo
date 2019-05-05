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
        raffleService.initDatabase();
    }

    @Override
    public void start(Stage window) throws SQLException {

        int widthOfWindow = 400;
        int hightOfWindow = 280;

        //1. view begins
        Label logInText = new Label("Kirjoita käyttäjätunnus ja paina kirjaudu");
        Label logInErrorText = new Label("");
        TextField logInUsernameField = new TextField();
        Button logInBtn = new Button("Kirjaudu");
        Button newUserBtn = new Button("Luo uusi käyttäjä");
        GridPane firstWindowLayout = new GridPane();
        firstWindowLayout.add(logInText, 0, 0);
        firstWindowLayout.add(logInUsernameField, 0, 1);
        firstWindowLayout.add(logInBtn, 0, 2);
        firstWindowLayout.add(newUserBtn, 0, 3);
        firstWindowLayout.add(logInErrorText, 0, 4);
        firstWindowLayout.setPrefSize(widthOfWindow, hightOfWindow);
        firstWindowLayout.setAlignment(Pos.CENTER);
        firstWindowLayout.setVgap(10);
        firstWindowLayout.setHgap(10);
        firstWindowLayout.setPadding(new Insets(20, 20, 20, 20));

        Scene firstWindow = new Scene(firstWindowLayout);
//1. view ends

        //creating new user -view begins
        Label newUserText = new Label("Anna käyttäjänimi");
        TextField newUserNameField = new TextField();
        Label newUserErrorText = new Label("");
        Button createNewUserBtn = new Button("Luo käyttäjä");
        GridPane createNewUserLayout = new GridPane();
        createNewUserLayout.setPrefSize(widthOfWindow, hightOfWindow);
        createNewUserLayout.add(newUserText, 0, 0);
        createNewUserLayout.add(newUserNameField, 0, 1);
        createNewUserLayout.add(createNewUserBtn, 0, 2);
        createNewUserLayout.add(newUserErrorText, 0, 3);
        createNewUserLayout.setAlignment(Pos.CENTER);
        Scene createNewUserWindow = new Scene(createNewUserLayout);
//creating new user -view ends

        //user has logged in view begins
        Label welcome = new Label("Tervetuloa");
        Label selectSubject = new Label("Minkä projektiaiheen haluat?");

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
        //user has logged in view ends

        //admin view begins
        Button createNewProjectBtn = new Button("Luo uusi projekti");
        Button deleteProjectBtn = new Button("Poista projekti");
        GridPane adminWindowLayout = new GridPane();
        adminWindowLayout.add(createNewProjectBtn, 0, 0);
        adminWindowLayout.add(deleteProjectBtn, 0, 1);
        adminWindowLayout.setPrefSize(widthOfWindow, hightOfWindow);
        adminWindowLayout.setAlignment(Pos.CENTER);
        adminWindowLayout.setVgap(10);
        adminWindowLayout.setHgap(10);
        adminWindowLayout.setPadding(new Insets(20, 20, 20, 20));
        Scene adminHasLoggedInWindows = new Scene(adminWindowLayout);
        //admin view ends

        //begin of creating new project
        Label createNewProjectLabel = new Label("Valitse kategoria \n"
                + "(Valitse \"Uusi kategoria\" \n"
                + "jos haluat lisätä uuden kategorian)");

        ObservableList<String> options
                = FXCollections.observableArrayList(raffleService.projectCategories());
        options.add("Uusi kategoria");
        ComboBox<String> comboBox = new ComboBox(options);
        Button nextPageBtn = new Button("Seuraava");
        Label errorTextLabel = new Label("");
        GridPane createNewProjectFirstView = new GridPane();
        createNewProjectFirstView.add(createNewProjectLabel, 0, 0);
        createNewProjectFirstView.add(comboBox, 0, 1);
        createNewProjectFirstView.add(nextPageBtn, 0, 2);
        createNewProjectFirstView.add(errorTextLabel, 0, 3);
        createNewProjectFirstView.setPrefSize(widthOfWindow, hightOfWindow);
        createNewProjectFirstView.setAlignment(Pos.CENTER);
        createNewProjectFirstView.setVgap(10);
        createNewProjectFirstView.setHgap(10);
        createNewProjectFirstView.setPadding(new Insets(20, 20, 20, 20));
        Scene createNewProjectFirstWindow = new Scene(createNewProjectFirstView);
        //end of creating new project

        //create new project with new category begins
        Label newCategoryLabel = new Label("Anna uusi kategoria:");
        TextField newCategoryField = new TextField();
        Label categoryErrorLabel = new Label();
        Button nextCreateProject = new Button("Seuraava");
        GridPane createNewProjectWithNewCategory = new GridPane();
        createNewProjectWithNewCategory.add(newCategoryLabel, 0, 0);
        createNewProjectWithNewCategory.add(newCategoryField, 0, 1);
        createNewProjectWithNewCategory.add(nextCreateProject, 0, 2);
        createNewProjectWithNewCategory.add(categoryErrorLabel, 0, 3);
        createNewProjectWithNewCategory.setPrefSize(widthOfWindow, hightOfWindow);
        createNewProjectWithNewCategory.setAlignment(Pos.CENTER);
        createNewProjectWithNewCategory.setVgap(10);
        createNewProjectWithNewCategory.setHgap(10);
        createNewProjectWithNewCategory.setPadding(new Insets(20, 20, 20, 20));
        Scene createNewProjectWithNewCategoryWindow = new Scene(createNewProjectWithNewCategory);
        //create new project with new ctegory ends

        //create new project with existing category begins
        Label newProjectsNameLabel = new Label("Anna projektin aihe:");
        TextField newProjectsNameField = new TextField();
        Button projectIsReadyBtn = new Button("Luo uusi projekti");
        GridPane createNewProjectLayout = new GridPane();
        createNewProjectLayout.add(newProjectsNameLabel, 0, 0);
        createNewProjectLayout.add(newProjectsNameField, 0, 1);
        createNewProjectLayout.add(projectIsReadyBtn, 0, 2);
        createNewProjectLayout.setPrefSize(widthOfWindow, hightOfWindow);
        createNewProjectLayout.setAlignment(Pos.CENTER);
        createNewProjectLayout.setVgap(10);
        createNewProjectLayout.setHgap(10);
        createNewProjectLayout.setPadding(new Insets(20, 20, 20, 20));
        Scene createNewProjectWindow = new Scene(createNewProjectLayout);
        //create new project with existing category ends

        //delete project begins
        Label deleteProjectsLabel = new Label("Valitse poistettava projekti");
        ObservableList<String> projects
                = FXCollections.observableArrayList(raffleService.projects());
        ComboBox<String> comboBox2 = new ComboBox(projects);
        Button deleteProjectBtn2 = new Button("Poista projekti");
        Label deleteProjectErrorLabel = new Label();
        GridPane deleteProjectLayout = new GridPane();
        deleteProjectLayout.add(deleteProjectsLabel, 0, 0);
        deleteProjectLayout.add(comboBox2, 0, 1);
        deleteProjectLayout.add(deleteProjectBtn2, 0, 2);
        deleteProjectLayout.add(deleteProjectErrorLabel, 0, 3);
        deleteProjectLayout.setPrefSize(widthOfWindow, hightOfWindow);
        deleteProjectLayout.setAlignment(Pos.CENTER);
        deleteProjectLayout.setVgap(10);
        deleteProjectLayout.setHgap(10);
        deleteProjectLayout.setPadding(new Insets(20, 20, 20, 20));
        Scene deleteProjectWindow = new Scene(deleteProjectLayout);
        //delete project ends

        //BUTTONS
        //from first window to create new user window
        newUserBtn.setOnAction((event) -> {
            window.setScene(createNewUserWindow);
        });

        //from create user window to first window
        createNewUserBtn.setOnAction((event) -> {
            try {
                if (raffleService.userExist(newUserNameField.getText())) {
                    newUserErrorText.setText("Käyttäjänimi on jo käytössä");
                } else {
                    raffleService.createNewUser(newUserNameField.getText());
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
                if (raffleService.adminLogInSuccesful(logInUsernameField.getText())) {
                    window.setScene(adminHasLoggedInWindows);

                } else if (raffleService.userLogInSuccesful(logInUsernameField.getText())) {
                    window.setScene(userHasLoggedInWindow);

                } else {
                    logInErrorText.setText("Väärä käyttäjätunnus");
                }
            } catch (SQLException ex) {
                Logger.getLogger(RaffleUi.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        //get random new project, when te button is pressed
        getRandomProjectButton.setOnAction((event) -> {
            try {
                List<String> selectedCategories = new ArrayList<>();
                for (CheckBox cb : cbList) {
                    if (cb.isSelected()) {
                        selectedCategories.add(cb.getText());
                    }
                }

                pickedRandomProjectLabel.setText(raffleService.getRandomProject(selectedCategories).toString());
            } catch (SQLException ex) {
                Logger.getLogger(RaffleUi.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        //begin creating new project
        createNewProjectBtn.setOnAction((event) -> {
            window.setScene(createNewProjectFirstWindow);
        });

        Label projectCategory = new Label();

        //creating new project continues
        nextPageBtn.setOnAction((event) -> {
            if (comboBox.getValue() == null) {
                errorTextLabel.setText("Valitse projektille kategoria");
            } else if (comboBox.getValue().equals("Uusi kategoria")) {
                window.setScene(createNewProjectWithNewCategoryWindow);
            } else {
                projectCategory.setText(comboBox.getValue());
                window.setScene(createNewProjectWindow);
            }
        });

        //user chose to create new category self
        nextCreateProject.setOnAction((event) -> {
            if (newCategoryField.getText().equals("")) {
                categoryErrorLabel.setText("Kategoria täytyy täyttää");
            } else {
                try {
                    raffleService.createNewProjectCategory(newCategoryField.getText());
                    projectCategory.setText(newCategoryField.getText());
                } catch (SQLException ex) {
                    Logger.getLogger(RaffleUi.class.getName()).log(Level.SEVERE, null, ex);
                }
                window.setScene(createNewProjectWindow);
            }
        });

        //create the project
        projectIsReadyBtn.setOnAction((event) -> {
            if (newProjectsNameField.getText().equals("")) {

            } else {
                try {
                    raffleService.createNewProject(newProjectsNameField.getText(), projectCategory.getText());
                } catch (SQLException ex) {
                    Logger.getLogger(RaffleUi.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            window.setScene(adminHasLoggedInWindows);
        });

        //delete project-option
        deleteProjectBtn.setOnAction((event) -> {
            window.setScene(deleteProjectWindow);
        });

        //delete project 
        deleteProjectBtn2.setOnAction((event) -> {
            if (comboBox2.getValue() == null) {
                deleteProjectErrorLabel.setText("Valitse poistettava projekti");
            } else {
                try {
                    raffleService.deleteProject(comboBox2.getValue());
                } catch (SQLException ex) {
                    Logger.getLogger(RaffleUi.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            window.setScene(adminHasLoggedInWindows);
        });

        // BUTTONS END
        window.setScene(firstWindow);
        window.show();

    }

    public static void logOut(Stage window, Scene firstWindow) {
        window.setScene(firstWindow);
    }

}
