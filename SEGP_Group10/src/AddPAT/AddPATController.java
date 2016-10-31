/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AddPAT;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author abdul
 */
public class AddPATController implements Initializable {

    @FXML
    private AnchorPane anchorPaneAddPAT;
    
    @FXML
    private Tab addPATTab;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    
    public Tab getTab(){
        return addPATTab;
    }
}
