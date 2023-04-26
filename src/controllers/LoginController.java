package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import model.Password;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

import java.io.IOException;

public class LoginController implements SwitchInt{
	
	private Password password;
	
    @FXML
    protected PasswordField PasswordField;
    
    @FXML
    protected Label incorrectLabel;
    
    @FXML
    protected Button loginBtn;
    
	/**
	 * Checks if the default password is submitted
	 * @param event
	 * @throws IOException
	 */
    @FXML
    public void login(ActionEvent event) throws IOException{
    	
    	password = new Password();
    	String p = password.getPassword();
        
        if(p.equals("p") && PasswordField.getText().equals("p"))
        {
        	switchScene("/controllers/fxml/ChangePassword.fxml");
        }
        else if(PasswordField.getText().equals(p))
    	{
        	switchScene("/controllers/fxml/HomePage.fxml");
    	}
        else
        {
        	incorrectLabel.setOpacity(1);
        }
    }
}
