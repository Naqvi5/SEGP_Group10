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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author badar
 */
public class DrawerController implements Initializable {

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

    //hbox tabs
//    @FXML
//    private HBox hboxTabs;
//
//    @FXML
//    private JFXButton tab1PatsInfo;
//
//    @FXML
//    private JFXButton Tab2AddPat;
//
//    @FXML
//    private JFXButton tab3AllocatedStd;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setActionListeners();
    }

    public void setActionListeners() {

        patBt.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });

        studentBt.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });
        settingBt.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });
        helpBt.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });
        logoutBt.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });
    }

    public VBox getDrawerPane() {
        return drawerPane;
    }

}
