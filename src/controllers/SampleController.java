package controllers;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.io.IOException;

import javafx.event.ActionEvent;

public class SampleController {
	
	private Stage stage;
	private Scene scene;
	
	/**
	 * Switches to Scene1
	 * @param event
	 * @throws IOException
	 */
	public void switchToScene1(ActionEvent event) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("/controllers/fxml/HomePage.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	
	/**
	 * Switches to Create Recommendation page
	 * @param event
	 * @throws IOException
	 */
	public void switchToCreateRec(ActionEvent event) throws IOException{

		Parent root = FXMLLoader.load(getClass().getResource("/controllers/fxml/CreateRecommendation.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * Switches to Login page
	 * @param event
	 * @throws IOException
	 */
	public void switchToLogin(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("/controllers/fxml/Login.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * Switches to Profile page
	 * @param event
	 * @throws IOException
	 */
	public void switchToProfile(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("/controllers/fxml/Profile.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * Switches to change password page
	 * @param event
	 * @throws IOException
	 */
	public void switchToChangePassword(ActionEvent event) throws IOException{
 		Parent root = FXMLLoader.load(getClass().getResource("/controllers/fxml/ChangePassword.fxml"));
 		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
 		scene = new Scene(root);
 		stage.setScene(scene);
 		stage.show();
 	}
}