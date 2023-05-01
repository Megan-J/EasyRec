package controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.PasswordModel;
import model.ProfileModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.controlsfx.control.CheckComboBox;

import application.Main;


public class ProfileController implements Initializable {
	private ProfileModel profile;
	
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
    
    Main main = new Main();
    /**
	 * Handles when the cancelled button is pressed
	 * @param event
	 * @throws IOException
	 */
    @FXML
    void backPressed(ActionEvent event) throws IOException{
        main.switchScene("/controllers/fxml/ViewProfile.fxml");
    }

    /**
	 * Handles when save button is pressed
	 * @param event
	 * @throws IOException
	 */
    @FXML
    void saveButtonPressed(ActionEvent event) throws IOException{
    	
    	new PasswordModel();
    	
		String name = nameField.getText();
		String title = titleField.getText();
		String school = schoolDepField.getText();
		String email = emailField.getText();
		String phone = phoneField.getText();

		String semesters = "";
		ObservableList<String> semesterList = semestersField.getCheckModel().getCheckedItems();
		for (int i = 0; i < semesterList.size()- 1; i++)
		{
			String checked = semesterList.get(i);
			semesters = semesters + checked + ", ";
		}
		semesters = semesters + semesterList.get(semesterList.size()-1);
		
		String courses = "";
		ObservableList<String> courseList = coursesField.getCheckModel().getCheckedItems();
		for (int i = 0; i < courseList.size() - 1; i++)
		{
			String checked = courseList.get(i);
			courses = courses + checked + ", ";
		}
		courses = courses + courseList.get(courseList.size()-1);
		try {
			profile.setProfile(name, title, school, email, phone, semesters, courses);
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		main.switchScene("/controllers/fxml/ViewProfile.fxml");
    }

    /**
	 * Overrides default initialize method and save possible options 
	 * @param URL and Resource Bundle
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		profile = new ProfileModel();
		try {
			semestersField.getItems().addAll(profile.getSemesters());
			coursesField.getItems().addAll(profile.getCourses());
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
    

}
