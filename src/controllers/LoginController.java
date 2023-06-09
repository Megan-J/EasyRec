package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import model.PasswordModel;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import application.Main;
import java.io.IOException;
import java.sql.SQLException;

public class LoginController{
	
	private PasswordModel password;
	
    @FXML
    protected PasswordField PasswordField;
    
    @FXML
    protected Label incorrectLabel;
    
    @FXML
    protected Button loginBtn;
    
    Main main = new Main();
    
	/**
	 * Checks if the default password is submitted
	 * @param event
	 * @throws IOException
	 * @throws SQLException 
	 */
    @FXML
    public void login(ActionEvent event) throws IOException, SQLException{
    	
    	password = new PasswordModel();
    	String p = password.getPassword();
        
        if(p.equals("p") && PasswordField.getText().equals("p"))
        {
        	main.switchScene("/controllers/fxml/ResetPassword.fxml");
        }
        else if(PasswordField.getText().equals(p))
    	{
        	main.switchScene("/controllers/fxml/HomePage.fxml");
    	}
        else
        {
        	incorrectLabel.setOpacity(1);
        }
    }
}
