package application.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
//import java.util.Scanner;
//import java.io.File;
import application.model.LoginModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.SQLiteConnection;



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
		checkDBCred();
	}
	
	@FXML
    public void checkDBCred() throws IOException{
        
		/*String inputtedPassword = PasswordField.getText();
		
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
			
		}*/
		
		System.out.println("I work");
	
    }
	
	
    /*@FXML
    public void Login(ActionEvent event) throws IOException{
        
        
        Scanner scanner = new Scanner(new File("password.txt"));
        String line = scanner.nextLine();
        
        if(line.equals("p") && PasswordField.getText().equals("p"))
        {
    		Parent root = FXMLLoader.load(getClass().getResource("gui/ChangePassword.fxml"));
    		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		scene = new Scene(root);
    		stage.setScene(scene);
    		stage.show();
        }
        
        else if(PasswordField.getText().equals(line))
    	{
    		Parent root = FXMLLoader.load(getClass().getResource("gui/Scene1.fxml"));
    		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		scene = new Scene(root);
    		stage.setScene(scene);
    		stage.show();
    	}
        else
        {
        	incorrectLabel.setOpacity(1);
        }
    }*/


}
