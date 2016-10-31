package LogIn;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

public class LogInController implements Initializable {

    private String username = "Admin";
    private String password = "123";

    @FXML
    private JFXTextField loginUsernameTextfield;

    @FXML
    private JFXPasswordField loginPasswordTextfield;

    @FXML
    public JFXButton loginButton;
    
    @FXML
    public GridPane gridPane;

    /*******************************************************************************
     * This methods checks wheteher the login name and passwors is correct or not. *
     *******************************************************************************/
    public boolean login() {
        if (username.equals(loginUsernameTextfield.getText()) && password.equals(loginPasswordTextfield.getText())) {
            clearField();
            return true;
        }
        return false;
    }
    
    public void clearField(){
        
        loginUsernameTextfield.setText("");
        loginPasswordTextfield.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
