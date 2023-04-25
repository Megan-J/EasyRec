package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

import application.Main;


public class ChangePasswordController {
	
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
		
		if(confirmField.getText().length() > 0 && newField.getText().equals(confirmField.getText())) {
			
			File f = new File("src/resources/password.txt");
			FileWriter writer = new FileWriter(f, false);
            writer.write("");
            writer.close();
            
            // Write new data to the file
            writer = new FileWriter(f, true);
            writer.write(confirmField.getText());
            writer.close();
	        
            main.switchScene("/controllers/fxml/Login.fxml");
            
            
		}
		else
		{
			errorMessage.setOpacity(1);
		}
	}
}
