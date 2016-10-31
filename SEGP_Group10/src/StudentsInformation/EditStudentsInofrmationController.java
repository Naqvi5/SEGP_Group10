/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StudentsInformation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author abdul
 */
public class EditStudentsInofrmationController implements Initializable {

    @FXML
    JFXTextField contactNumberTextField;
    
    @FXML
    JFXButton cancelButton;
    
    @FXML
    JFXButton saveButton;
    
    @FXML
    AnchorPane ancorPane;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
