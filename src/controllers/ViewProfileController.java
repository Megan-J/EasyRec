package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Password;
import model.Profile;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;


public class ViewProfileController implements Initializable {
	private Profile profile;
	
    @FXML
    private Text nameField;

    @FXML
    private Text titleField;

    @FXML
    private Text schoolDepField;

    @FXML
    private Text emailField;

    @FXML
    private Text phoneField;

    @FXML
    private Text semestersField;

    @FXML
    private Text coursesField;

    @FXML
    private Button editButton;

    @FXML
    private Button backButton;

    Stage stage;
    Scene scene;
    
    Main main = new Main();
    /**
	 * Handles when the cancelled button is pressed
	 * @param event
	 * @throws IOException
	 */
    @FXML
    void backPressed(ActionEvent event) throws IOException{
        main.switchScene("/controllers/fxml/HomePage.fxml");   
    }

    /**
	 * Handles when edit button is pressed
	 * @param event
	 * @throws IOException
	 */
    @FXML
    void editButtonPressed(ActionEvent event) throws IOException{
    	
    	Main main = new Main();
        main.switchScene("/controllers/fxml/Profile.fxml");
        
		
    }

    /**
	 * Overrides default initialize method and save possible options 
	 * @param URL and Resource Bundle
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		profile = new Profile();
		try {
			ArrayList<String> profileAttributes = profile.getProfile();
			String name = profileAttributes.get(0);
			String title = profileAttributes.get(1);
			String schoolDep = profileAttributes.get(2);
			String email = profileAttributes.get(3);
			String phone = profileAttributes.get(4);
			String sem = profileAttributes.get(5);
			String course = profileAttributes.get(6);
			
			nameField.setText(name);
			titleField.setText(title);
			schoolDepField.setText(schoolDep);
			emailField.setText(email);
			phoneField.setText(phone);
			semestersField.setText(sem);
			coursesField.setText(course);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
    

}
