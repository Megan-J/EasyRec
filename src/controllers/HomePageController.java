package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import application.Main;
import java.io.File;



public class HomePageController implements Initializable{

	
    @FXML
    protected TextField searchField;

    @FXML
    protected Button searchButton;

    @FXML
    protected ListView<String> searchList;
    

    Main main = new Main();
    
    /**
	 * Searches Recommendations
	 * @param event
	 * @throws IOException
	 */
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
    
    /**
	 * handles if the mouse is cliked
	 * @param event
	 * @throws IOException
	 */
    public void handleMouseClick(MouseEvent event) throws IOException
    {
    	if(searchList.getSelectionModel().getSelectedItem() != null) {
	    	System.out.println("clicked on " + searchList.getSelectionModel().getSelectedItem());
	    	
			CommonLibrary.recTitle = searchList.getSelectionModel().getSelectedItem();
	    	
			main.switchScene("/controllers/fxml/ViewRecommendation.fxml");
    	}
	
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
	/**
	 * Closes the Program
	 * @param event
	 * @throws IOException
	 */
	public void closeProgram(ActionEvent event) throws IOException{
		Platform.exit();
		
 	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
    	File directory = new File ("src/resources/recs");
    	String contents[] = directory.list();
    	searchList.getItems().clear();
    	for(String i: contents) {
			searchList.getItems().add(i);
			
    	}
	}
}