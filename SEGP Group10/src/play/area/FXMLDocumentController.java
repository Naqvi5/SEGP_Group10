/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package play.area;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author badar
 */
public class FXMLDocumentController implements Initializable{
    
 	@FXML
    private VBox drawerPane;

    @FXML
    private JFXButton patBt;

    @FXML
    private JFXButton studentBt;

    @FXML
    private JFXButton settingBt;

    @FXML
    private JFXButton helpBt;

    @FXML
    private JFXButton logoutBt;
    
    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private BorderPane boderpane;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			boderpane.setMinSize(600,400);
			VBox drawerBox = FXMLLoader.load(getClass().getResource("drawer.fxml"));
			drawer.setSidePane(drawerBox);
		
		
		HamburgerBasicCloseTransition burgerClose = new HamburgerBasicCloseTransition(hamburger);
		burgerClose.setRate(-1);
		hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e)->{
			burgerClose.setRate(burgerClose.getRate()*-1);
			burgerClose.play();
			
			if(drawer.isShown())
				drawer.close();
			else
				drawer.open();
			
		});
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
    
}
