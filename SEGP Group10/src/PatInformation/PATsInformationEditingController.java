/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatInformation;


import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author abdul
 */
public class PATsInformationEditingController implements Initializable{
    
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private JFXButton cancelButton;
    
    private AnchorPane PATsinformationAnchorPane;
    private BorderPane borderPane;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }  
    
    public AnchorPane getAnchorPane(){
       return anchorPane;
    }
    
    public JFXButton getCancelButton(){
        
        return cancelButton;
    }
    
    public void cancelButtonOnClicked(){
        borderPane.setCenter(PATsinformationAnchorPane);
    }
}
