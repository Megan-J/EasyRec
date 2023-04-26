package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import model.Password;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

import java.io.IOException;


public class ChangePasswordController implements SwitchInt {
	private Password password;
	
	@FXML
	private PasswordField newField;
	
	@FXML
	private PasswordField confirmField;
	
	@FXML
	private Label errorMessage;
	
	/**
	 * Check if password matches the one on file
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void confirmLogin(ActionEvent event) throws IOException{
		
		password = new Password();
		
		if(confirmField.getText().length() > 0 && newField.getText().equals(confirmField.getText())) {
			password.setPassword(confirmField.getText());
            switchScene("/controllers/fxml/Profile.fxml");
		}
		else
		{
			errorMessage.setOpacity(1);
		}
	}
}
