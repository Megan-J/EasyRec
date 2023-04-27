package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import application.Main;
import java.io.File;


public class HomePageController {

	
    @FXML
    protected TextField searchField;

    @FXML
    protected Button searchButton;

    @FXML
    protected ListView<String> searchList;

    Main main = new Main();
    
    public void searchRecs(ActionEvent event) throws IOException{
    	File directory = new File ("src/resources/recs");
    	String contents[] = directory.list();
    	searchList.getItems().clear();
    	for(String i: contents) {
    		if(i.contains(searchField.getText()))
    		{
    			searchList.getItems().add(i);
    		}
    	}
    }
    
    public void handleMouseClick(MouseEvent event) throws IOException
    {
    	System.out.println("clicked on " + searchList.getSelectionModel().getSelectedItem());
    	
		main.switchScene("/controllers/fxml/ViewRecommendation.fxml");
		
		main.stg.setUserData(searchList.getSelectionModel().getSelectedItem());
	
    }
	/**
	 * Switches to Scene1
	 * @param event
	 * @throws IOException
	 */
	public void switchToHomePage(ActionEvent event) throws IOException {
		main.switchScene("/controllers/fxml/HomePage.fxml");
	}
	
	
	/**
	 * Switches to Create Recommendation page
	 * @param event
	 * @throws IOException
	 */
	public void switchToCreateRec(ActionEvent event) throws IOException{
		main.switchScene("/controllers/fxml/CreateRecommendation.fxml");
	}
	
	/**
	 * Switches to Login page
	 * @param event
	 * @throws IOException
	 */
	public void switchToLogin(ActionEvent event) throws IOException{
		main.switchScene("/controllers/fxml/Login.fxml");
	}
	
	/**
	 * Switches to Profile page
	 * @param event
	 * @throws IOException
	 */
	public void switchToProfile(ActionEvent event) throws IOException{
		main.switchScene("/controllers/fxml/ViewProfile.fxml");
	}
	
	/**
	 * Switches to change password page
	 * @param event
	 * @throws IOException
	 */
	public void switchToResetPassword(ActionEvent event) throws IOException{
		main.switchScene("/controllers/fxml/ResetPassword.fxml");
		
 	}
}