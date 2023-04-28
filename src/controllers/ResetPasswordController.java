package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import model.Password;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.SQLException;
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
	
	Main main = new Main();
	/**
	 * Check if password matches the one on file
	 * @param event
	 * @throws IOException
	 * @throws SQLException 
	 */
	@FXML
	public void changeLogin(ActionEvent event) throws IOException, SQLException{
		password = new Password();
		String p = password.getPassword();
        
        if(p.equals(currentField.getText()) && confirmField.getText().length() > 0 && newField.getText().equals(confirmField.getText())) {
			
			password.setPassword(confirmField.getText());
			p = password.getPassword();
			if (p.equals("p"))
	        {
	        	main.switchScene("/controllers/fxml/Login.fxml");
	        }
			else
			{
				main.switchScene("/controllers/fxml/Homepage.fxml");
			}
		}
		else
		{
			errorMessage.setOpacity(1);
		}
	}
	
	public void switchToHomePage(ActionEvent event) throws IOException, SQLException {
		password = new Password();
		String p = password.getPassword();
		if (p.equals("p"))
		{
			main.switchScene("/controllers/fxml/Login.fxml");
		}
		else
		{
			main.switchScene("/controllers/fxml/Homepage.fxml");
		}
	}
}
