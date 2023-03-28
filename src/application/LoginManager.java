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
import java.util.Scanner;
import java.io.File;


public class LoginManager {

    @FXML
    private PasswordField PasswordField;
    
    @FXML
    private Label incorrectLabel;
    

	private Stage stage;
	private Scene scene;
	

    @FXML
    public void Login(ActionEvent event) throws IOException{
        
        
        Scanner scanner = new Scanner(new File("password.txt"));
        String line = scanner.nextLine();
        
        if(line.equals("password") && PasswordField.getText().equals("password"))
        {
    		Parent root = FXMLLoader.load(getClass().getResource("ChangePassword.fxml"));
    		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		scene = new Scene(root);
    		stage.setScene(scene);
    		stage.show();
        }
        
        else if(PasswordField.getText().equals(line))
    	{
    		Parent root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
    		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		scene = new Scene(root);
    		stage.setScene(scene);
    		stage.show();
    	}
        else
        {
        	incorrectLabel.setOpacity(1);
        }
    }


}
