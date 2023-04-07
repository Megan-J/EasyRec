package controllers;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.LoginModel;
import javafx.event.ActionEvent;

import java.io.IOException;
/*
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.SQLiteConnection;
*/
import java.util.Scanner;
import java.io.File;



public class LoginController {

    @FXML
    private PasswordField PasswordField;
    
    @FXML
    private Label incorrectLabel;
    
    @FXML
    private Button loginBtn;
    

	private Stage stage;
	private Scene scene;
	
	public LoginModel loginModel = new LoginModel();
	

	public void loginBtnClicked(ActionEvent event) throws Exception {
		//checkDBCred();
	}
	
	/*@FXML
    public void Login(ActionEvent event) throws IOException{
        
		String inputtedPassword = PasswordField.getText();
		
		try {
			if(loginModel.defaultMatch(inputtedPassword)) {
				Parent root = FXMLLoader.load(getClass().getResource("gui/ChangePassword.fxml"));
	    		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    		scene = new Scene(root);
	    		stage.setScene(scene);
	    		stage.show();
			}
			else {
				incorrectLabel.setOpacity(1);
			}
		}
		
		catch (SQLException e) {
			e.printStackTrace();
			
		}
	
    }
	*/
	
    @FXML
    public void Login(ActionEvent event) throws IOException{
        String FilePath = "src/resources/password.txt";
        File file = new File(FilePath);
        
        Scanner scanner = new Scanner(file);
        String line = scanner.nextLine();
        
        if(line.equals("p") && PasswordField.getText().equals("p"))
        {
    		Parent root = FXMLLoader.load(getClass().getResource("/controllers/fxml/ChangePassword.fxml"));
    		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		scene = new Scene(root);
    		stage.setScene(scene);
    		stage.show();
        }
        
        else if(PasswordField.getText().equals(line))
    	{
    		Parent root = FXMLLoader.load(getClass().getResource("/controllers/fxml/Scene1.fxml"));
    		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		scene = new Scene(root);
    		stage.setScene(scene);
    		stage.show();
    	}
        else
        {
        	incorrectLabel.setOpacity(1);
        }
        scanner.close();
    }
}
