package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.io.FileWriter;
import java.io.File;


public class ChangePasswordManager {
	
	@FXML
	private PasswordField newField;
	
	@FXML
	private PasswordField confirmField;
	
	@FXML
	private Label errorMessage;
	
	private Stage stage;
	private Scene scene;
	
	public void confirmLogin(ActionEvent event) throws IOException{
		if(confirmField.getText().length() > 0 && newField.getText().equals(confirmField.getText())) {
			File f = new File("password.txt");
			
			FileWriter writer = new FileWriter(f, false);
            writer.write("");
            writer.close();
            
            // Write new data to the file
            writer = new FileWriter(f, true);
            writer.write(confirmField.getText());
            writer.close();
	        
			Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		else
		{
			errorMessage.setOpacity(1);
		}
	}
}
