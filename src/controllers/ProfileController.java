package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Password;
import model.Profile;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.CheckComboBox;

import application.Main;
import dal.DbSqlite;


public class ProfileController implements Initializable, SwitchInt {
	private Password password;
	
	private Profile profile;
	
    @FXML
    private TextField nameField;

    @FXML
    private TextField titleField;

    @FXML
    private TextField schoolDepField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private CheckComboBox<String> semestersField;

    @FXML
    private CheckComboBox<String> coursesField;

    @FXML
    private Button saveButton;

    @FXML
    private Button backButton;

    Stage stage;
    Scene scene;
    
    /**
	 * Handles when the cancelled button is pressed
	 * @param event
	 * @throws IOException
	 */
    @FXML
    void backPressed(ActionEvent event) throws IOException{
    	
    	password = new Password();
    	String p = password.getPassword();
    	
        if(p.equals("p"))
        {
        	switchScene("/controllers/fxml/ChangePassword.fxml");
        } else {
        	switchScene("/controllers/fxml/HomePage.fxml");
        }
    }

    /**
	 * Handles when save button is pressed
	 * @param event
	 * @throws IOException
	 */
    @FXML
    void saveButtonPressed(ActionEvent event) throws IOException{
    	
    	Main main = new Main();
    	password = new Password();
    	
    	String semesters = "";
		for(String x: semestersField.getCheckModel().getCheckedItems())
		{
			semesters += x + ", ";
		}
		String courses = "";
		for(String x: coursesField.getCheckModel().getCheckedItems())
		{
			courses += x + ", ";
		}
		String name = nameField.getText();
		String title = titleField.getText();
		String school = schoolDepField.getText();
		String email = emailField.getText();
		String phone = phoneField.getText();
		try {
			profile.setProfile(name, title, school, email, phone, semesters, courses);
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		
		if(password.getPassword().equals("p"))
        {
        	switchScene("/controllers/fxml/ChangePassword.fxml");
        } else {
        	switchScene("/controllers/fxml/HomePage.fxml");
        }
		
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
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		final ObservableList<String> courses = FXCollections.observableArrayList();

    	courses.add("CS151: Object-Oriented Design");
    	courses.add("CS166: Information Security");
    	courses.add("CS154: Theory of Computation");
    	courses.add("CS160: Software Engineering");
    	courses.add("CS256: Cryptography");
    	courses.add("CS146: Data Structures and Algorithms");
    	courses.add("CS152: Programming Language Paradigm");
    	
    	final ObservableList<String> semesters = FXCollections.observableArrayList();

    	semesters.add("Fall");
    	semesters.add("Summer");
    	semesters.add("Spring");
    	
    	semestersField.getItems().addAll(semesters);
    	coursesField.getItems().addAll(courses);
		
	}
    

}
