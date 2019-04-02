/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import dao.ProjectDao;
import java.sql.*;
import javafx.application.Application;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import raffle.*;
import java.util.*;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author kortemil
 */
public class RaffleUi extends Application {

    ProjectDao projectDao;
    RandomProject random;

    @Override
    public void init() throws Exception {

        projectDao = new ProjectDao();
        random = new RandomProject();
    }

    @Override
    public void start(Stage primaryStage) throws SQLException {

        StackPane root = new StackPane();

        List<Project> projects = projectDao.list();

        Label project = new Label("");
        Button button = new Button("Arvo projektiaihe");

        button.setOnAction(e -> {
            project.setText(random.getRandomProject(projects).toString());
        });

        HBox group = new HBox();
        group.setSpacing(20);
        group.getChildren().addAll(project, button);

        Scene scene = new Scene(group, 500, 300);

        primaryStage.setTitle("Raffle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static void alustaTietokanta() throws SQLException {
    }
}
