package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import model.Password;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;

import application.Main;


public class ResetPasswordController {
	private Password password;
	
	@FXML
	private PasswordField newField;
	
	@FXML
	private PasswordField confirmField;
	
	@FXML
	private Label errorMessage;
	
	@FXML
	private PasswordField currentField;
	
	/**
	 * Check if password matches the one on file
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void changeLogin(ActionEvent event) throws IOException{
		
		Main main = new Main();
		password = new Password();
		String p = password.getPassword();
        
        if(p.equals(currentField.getText()) && confirmField.getText().length() > 0 && newField.getText().equals(confirmField.getText())) {
			
			password.setPassword(confirmField.getText());
            main.switchScene("/controllers/fxml/Login.fxml");
		}
		else
		{
			errorMessage.setOpacity(1);
		}
	}
	
	public void switchToHomePage(ActionEvent event) throws IOException {
		
		Main main = new Main();
		main.switchScene("/controllers/fxml/HomePage.fxml");
	}
}
