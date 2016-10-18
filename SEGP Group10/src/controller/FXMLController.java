/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author badar
 */
public class FXMLController implements Initializable{
    
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
    
    
    //hbox tabs
    @FXML
    private HBox hboxTabs;

    @FXML
    private HBox hboxTab;

    @FXML
    private JFXDrawer drawerTab;

    @FXML
    private JFXButton tab1PatsInfo;

    @FXML
    private JFXButton Tab2AddPat;

    @FXML
    private JFXButton tab3AllocatedStd;
    
    //Pats Information pane
    @FXML
    private AnchorPane patsInformationPane;

    @FXML
    private JFXButton viewAssignStudentButton;

    @FXML
    private JFXButton editInfoButton;

    @FXML
    private Label setEmail;

    @FXML
    private Label setOfficeNo;

    @FXML
    private Label setLoad;

    @FXML
    private Label setDepartment;

    
    
    //hamburger buttons listener
   
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			boderpane.setMinSize(600,400);
			
			VBox drawerBox = FXMLLoader.load(getClass().getResource("../gui/drawer.fxml"));
			drawer.setSidePane(drawerBox);
			
			HBox tabBox = FXMLLoader.load(getClass().getResource("../gui/tabs.fxml"));
			drawerTab.setSidePane(tabBox);
			
			patBt.addEventHandler(MouseEvent.MOUSE_PRESSED, (e)->{
				if(drawerTab.isShown())
					drawerTab.close();
				else
					drawerTab.open();
					
			});
			
			//Hamburger listener
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
