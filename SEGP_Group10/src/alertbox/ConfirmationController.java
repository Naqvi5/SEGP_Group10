package alertbox;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

public class ConfirmationController implements Initializable {

    @FXML
    JFXButton cancelButton;

    @FXML
    JFXButton okButton;
    
    @FXML
    public VBox vbox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

    }

}
