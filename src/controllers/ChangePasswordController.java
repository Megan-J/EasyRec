package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import model.Password;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

import java.io.IOException;
import application.Main;

public class ChangePasswordController {
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
		Main main = new Main();
		password = new Password();
		
		if(confirmField.getText().length() > 0 && newField.getText().equals(confirmField.getText())) {
			password.setPassword(confirmField.getText());
            main.switchScene("/controllers/fxml/Profile.fxml");
		}
		else
		{
			errorMessage.setOpacity(1);
		}
	}
}
