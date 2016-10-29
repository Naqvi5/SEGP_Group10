package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import gui.Studentsinformation;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author badar
 */
public class MainController extends Application {

    private MainWindowController mainWindowController;
    private FXMLLoader loader;

    @Override
    public void start(Stage stage) throws Exception {

        loader = new FXMLLoader(getClass().getResource("../controller/main.fxml"));
//        loader = new FXMLLoader(getClass().getResource("../gui/Student.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainWindowController = loader.getController();
//        gui.Studentsinformation studentsinformation = loader.getController();

        BorderPane borderPane = mainWindowController.getBorderPane();
        Scene scene = new Scene(borderPane);
//        Scene scene = new Scene(studentsinformation.getBorderPane());
//        scene.getStylesheets().add("/StudentsInformation/Testing.css");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
