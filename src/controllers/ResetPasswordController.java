package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;

import application.Main;


public class ResetPasswordController {
	
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
	public void confirmLogin(ActionEvent event) throws IOException{
		
		Main main = new Main();
		String FilePath = "src/resources/password.txt";
        File file = new File(FilePath);
        
        Scanner scanner = new Scanner(file);
        String line = scanner.nextLine();
        
        if(line.equals(currentField.getText()) && confirmField.getText().length() > 0 && newField.getText().equals(confirmField.getText())) {
			
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
        scanner.close();
	}
	
	public void switchToHomePage(ActionEvent event) throws IOException {
		
		Main main = new Main();
		main.switchScene("/controllers/fxml/HomePage.fxml");
	}
}
