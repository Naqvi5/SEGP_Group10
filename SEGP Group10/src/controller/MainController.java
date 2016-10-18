package controller;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


<<<<<<< HEAD
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
=======
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
>>>>>>> 9d222ea11f4aafbdefa48fd20a7bd72657ba5efe
import javafx.stage.Stage;

/**
 *
 * @author badar
 */
public class MainController extends Application {
    
<<<<<<< HEAD
    
    private MainWindowController mainWindowController;
    private FXMLLoader loader;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        loader = new FXMLLoader(getClass().getResource("../gui/main.fxml"));
        loader.load();
        mainWindowController = loader.getController();
		
        BorderPane borderPane = mainWindowController.getBorderPane();
        Scene scene = new Scene(borderPane);
=======
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/main.fxml"));
        
        Scene scene = new Scene(root);
>>>>>>> 9d222ea11f4aafbdefa48fd20a7bd72657ba5efe
        
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
