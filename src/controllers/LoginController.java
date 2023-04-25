package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

import java.io.IOException;

import java.util.Scanner;
import java.io.File;

import application.Main;

public class LoginController {

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
    public void Login(ActionEvent event) throws IOException{
    	
    	Main main = new Main();
    	
        String FilePath = "src/resources/password.txt";
        File file = new File(FilePath);
        
        Scanner scanner = new Scanner(file);
        String line = scanner.nextLine();
        
        if(line.equals("p") && PasswordField.getText().equals("p"))
        {
        	main.setPassword(line);
        	main.switchScene("/controllers/fxml/Profile.fxml");
        }
        
        else if(PasswordField.getText().equals(line))
    	{
        	main.switchScene("/controllers/fxml/HomePage.fxml");
    	}
        else
        {
        	incorrectLabel.setOpacity(1);
        }
        scanner.close();
    }
}
