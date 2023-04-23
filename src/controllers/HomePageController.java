package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;

import application.Main;

public class HomePageController {

	
	/**
	 * Switches to Scene1
	 * @param event
	 * @throws IOException
	 */
	public void switchToHomePage(ActionEvent event) throws IOException {
		
		Main main = new Main();
		main.switchScene("/controllers/fxml/HomePage.fxml");
	}
	
	
	/**
	 * Switches to Create Recommendation page
	 * @param event
	 * @throws IOException
	 */
	public void switchToCreateRec(ActionEvent event) throws IOException{

		Main main = new Main();
		main.switchScene("/controllers/fxml/CreateRecommendation.fxml");
	}
	
	/**
	 * Switches to Login page
	 * @param event
	 * @throws IOException
	 */
	public void switchToLogin(ActionEvent event) throws IOException{
		
		Main main = new Main();
		main.switchScene("/controllers/fxml/Login.fxml");
	}
	
	/**
	 * Switches to Profile page
	 * @param event
	 * @throws IOException
	 */
	public void switchToProfile(ActionEvent event) throws IOException{
		
		Main main = new Main();
		main.switchScene("/controllers/fxml/Profile.fxml");
		
	}
	
	/**
	 * Switches to change password page
	 * @param event
	 * @throws IOException
	 */
	public void switchToChangePassword(ActionEvent event) throws IOException{
		Main main = new Main();
		main.switchScene("/controllers/fxml/ChangePassword.fxml");
		
 	}
}