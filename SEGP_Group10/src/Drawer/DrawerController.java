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
import javafx.event.Event;
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
    private VBox drawerPane; // contains the buttons of drawer

    @FXML
    public JFXButton PATBt; // pat button is clicked to view the pats infomation

    @FXML
    private JFXButton studentsButton; // students button is clicked when user wsnts to veiw the students information

    @FXML
    private JFXButton settingBt; // same is the case as above buttons

    @FXML
    private JFXButton helpBt; // same case

    @FXML
    public JFXButton logoutBt; // Logout buutton is to logout the user.

    private ScrollPane scrollPane; // scroll pane object 
    private BorderPane borderPane; // border pane object
    private PATSInformationController PATsInformationController; //pats information contoller
    private AddPATController addPATController; // add pat controller
    private AllocatedPAT.AllocatedPATsController allocatedPATsController; // allocated pat controller
    private JFXTabPane tabPane; // tab pane
    private FXMLLoader loader = null; // laoder to load fxml files
    private Tab tabPATsinformation, tabAddPAT, tabAllocatedPATs, studentsInformationTab, assignPATTab; //containing all tabs
    private StudentsInformation.Studentsinformation studentsinformation; // stundents information controler
    private AssignPAT assignPATController; // assign pats controller

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

    /**
     * When the pat button is clicked set the pats tabs in the tab pane.
     */
    @FXML
    public void patButtonOnClicked() {

        if (!borderPane.getChildren().contains(tabPane)) {
            borderPane.setCenter(tabPane);
        }

        while (!tabPane.getTabs().isEmpty()) {
            tabPane.getTabs().remove(0);
        }
        tabPane.getTabs().addAll(tabPATsinformation, tabAddPAT, tabAllocatedPATs);
    }

    /**
     * when the studensts button is clicked in the drawer it sets the students tabs in the tab pane.
     */
    @FXML
    public void studentsButtonOnClicked() {

        if (!borderPane.getChildren().contains(tabPane)) {
            borderPane.setCenter(tabPane);
        }
        while (!tabPane.getTabs().isEmpty()) {
            tabPane.getTabs().remove(0);
        }
        tabPane.getTabs().add(studentsInformationTab);
    }

    public VBox getDrawerPane() {
        return drawerPane;
    }

    /**
     * when the setting button is clicked set the setting tabs in the tabpane.
     */
    @FXML
    public void settingButtonOnClicked() {

        if (!borderPane.getChildren().contains(tabPane)) {
            borderPane.setCenter(tabPane);
        }

        Tab tab = new Tab("Change Password");
        while (!tabPane.getTabs().isEmpty()) {
            tabPane.getTabs().remove(0);
        }
        tabPane.getTabs().addAll(assignPATTab, tab);
    }

    /**
     * getting the tabpane and borderpane. 
     */
    public void setTabPane(JFXTabPane tabPane, BorderPane borderPane) {
        this.tabPane = tabPane;
        System.out.println("Tab Pane is: " + tabPane);
        this.borderPane = borderPane;
    }

}
