/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Drawer;

import AddPAT.AddPATController;
import PatInformation.PATSInformationController;
import Setting.AssignPAT;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author badar
 */
public class DrawerController implements Initializable {

    @FXML
    private VBox drawerPane;

    @FXML
    public JFXButton PATBt;

    @FXML
    private JFXButton studentsButton;

    @FXML
    private JFXButton settingBt;

    @FXML
    private JFXButton helpBt;

    @FXML
    private JFXButton logoutBt;

    private ScrollPane scrollPane;
    private BorderPane borderPane;
    private PATSInformationController PATsInformationController;
    private AddPATController addPATController;
    private AllocatedPAT.AllocatedPATsController allocatedPATsController;
    private JFXTabPane tabPane;
    private FXMLLoader loader = null;
    private Tab tabPATsinformation, tabAddPAT, tabAllocatedPATs, studentsInformationTab, assignPATTab;
    private StudentsInformation.Studentsinformation studentsinformation;
    private AssignPAT assignPATController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loader = new FXMLLoader(getClass().getResource("../PatInformation/PATSInformationTab.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PATsInformationController = loader.getController();
        tabPATsinformation = PATsInformationController.getTab();

        loader = new FXMLLoader(getClass().getResource("../AddPAT/AddPatTab.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        addPATController = loader.getController();
        tabAddPAT = addPATController.getTab();

        loader = new FXMLLoader(getClass().getResource("../AllocatedPAT/AllocatedStudentsView.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        allocatedPATsController = loader.getController();
        tabAllocatedPATs = allocatedPATsController.getTab();

        loader = new FXMLLoader(getClass().getResource("../StudentsInformation/StudentInformationView.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        studentsinformation = loader.getController();
        studentsInformationTab = studentsinformation.getTab();

        loader = new FXMLLoader(getClass().getResource("../Setting/AssignPATView.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assignPATController = loader.getController();
        assignPATTab = assignPATController.getTab();
    }

    @FXML
    public void patButtonOnClicked() {

        while (!tabPane.getTabs().isEmpty()) {
            tabPane.getTabs().remove(0);
        }
        tabPane.getTabs().addAll(tabPATsinformation, tabAddPAT, tabAllocatedPATs);
    }

    @FXML
    public void studentsButtonOnClicked() {

        while (!tabPane.getTabs().isEmpty()) {
            tabPane.getTabs().remove(0);
        }
        tabPane.getTabs().add(studentsInformationTab);
    }

    public void setActionListeners() {

        System.out.println("The pat button is: " + PATBt);
        PATBt.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("working");
//                tabPane.getTabs().addAll(tabPATsinformation, tabAddPAT);
//                tabPane.getTabs().addAll(tabPATsinformation);//, tabAddPAT, tabAllocatedPATs);
            }
        });

        PATBt.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
    
        @FXML
    public void settingButtonOnClicked() {

        while (!tabPane.getTabs().isEmpty()) {
            tabPane.getTabs().remove(0);
        }
        tabPane.getTabs().add(assignPATTab);
    }

    public void setTabPane(JFXTabPane tabPane) {
        this.tabPane = tabPane;
    }

}
