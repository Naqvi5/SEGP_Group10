/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatInformation;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author abdul
 */
public class PATSInformationController implements Initializable {

    @FXML
    private Tab tab;

    @FXML
    private ListView list;

    @FXML
    private BorderPane borderPane;

    @FXML
    private AnchorPane subAnchorPane;

    private AnchorPane anchorPane;
    private PATsInformationEditingController PATsInformationEditingController;
    private FXMLLoader loader;
    private JFXButton cancelButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        System.err.println("It is working");
        for (int i = 1; i <= 10; i++) {
            list.getItems().add(new Label("Label " + i));
        }

        loader = new FXMLLoader(getClass().getResource("../PatInformation/EditPatInformation.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PATsInformationEditingController = loader.getController();
        cancelButton = PATsInformationEditingController.getCancelButton();
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                list.setDisable(false);
                    borderPane.setCenter(subAnchorPane);
            }

        });
        anchorPane = PATsInformationEditingController.getAnchorPane();
    }

    public Tab getTab() {
        return tab;
    }

    @FXML
    public void viewAssignedPATsClicked() {
    }

    @FXML
    public void editButtonOnClicked() {
        borderPane.setCenter(anchorPane);
        list.setDisable(true);
    }
}
