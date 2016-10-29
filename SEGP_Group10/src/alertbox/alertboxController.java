package alertbox;

import java.awt.Button;
import java.awt.Dialog;

import com.jfoenix.controls.JFXButton.ButtonType;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

public class alertboxController extends Application{
	
	    public static void main(String[] args) {
	        launch(args);
	    }
	 	
	    private FXMLLoader loader; 
	    
	    @Override
	    public void start(Stage primaryStage) throws Exception {
	       
	    	loader = new FXMLLoader(getClass().getResource("/alertbox/Error Dialog"));
	    	loader.load();
	    	
	    }
	 
}
