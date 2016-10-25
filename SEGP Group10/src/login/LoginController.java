package login;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.swing.plaf.synth.SynthSeparatorUI;
import javax.xml.transform.Result;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.PreparedStatement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

public class LoginController implements Initializable{
	private String username;
	private String password;
	  	@FXML
	    private JFXTextField loginUsernameTextfield;

	    @FXML
	    private JFXPasswordField loginPasswordTextfield;

	    @FXML
	    private JFXButton loginButton;
	    
	    @FXML
	    public void loginListner(MouseEvent event) {
	    	username=loginUsernameTextfield.getText();
	    	password=loginPasswordTextfield.getText();
	    	System.out.println(valid_Login(username, password));
	    }
	    
	   
	    private Connection con;
	    private java.sql.PreparedStatement pst;
	    private Statement st;
	    private ResultSet rs;
	    public boolean valid_Login(String username, String password){
		try{
			con = DriverManager.getConnection("jdbc:mysql://localhost/seqp"+"user=root&password=badar5822634");
			pst= con.prepareStatement("Selec * from client where username=? and password=?");
			pst.setString(1, username);
			pst.setString(2,password);
			rs =pst.executeQuery();
			
			if(rs.next())
				return true;
			else 
				return false;
			
			}catch(Exception e){
				System.out.println(e);
				return false;
			}
		}
	    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
	
	}
}
