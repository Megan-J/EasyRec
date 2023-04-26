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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import org.controlsfx.control.CheckComboBox;

import com.sun.xml.internal.bind.v2.runtime.Name;

import application.Main;


public class ProfileController implements Initializable{
	private Password password;
	
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
    	
    	Main main = new Main();
    	password = new Password();
    	String p = password.getPassword();
    	
        if(p.equals("p"))
        {
        	main.switchScene("/controllers/fxml/ChangePassword.fxml");
        } else {
        	main.switchScene("/controllers/fxml/HomePage.fxml");
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
		try {
			String url = "jdbc:sqlite:src/database/recommendation.db";
			Connection conn = DriverManager.getConnection(url);
			
			String delString = "DELETE FROM Profile";
			Statement del = conn.createStatement();
			del.executeUpdate(delString);
			
			String sql = "INSERT INTO Profile (Name, Title, SchoolName, Email, Phone, SemestersTaught, CoursesTaught) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, nameField.getText());
			st.setString(2, titleField.getText());
			st.setString(3, schoolDepField.getText());
			st.setString(4, emailField.getText());
			st.setString(5, phoneField.getText());
			st.setString(6, semesters);
			st.setString(7, courses);
			
    		int rowsInserted = st.executeUpdate();
    		if(rowsInserted > 0)
    			System.out.println("Success");
    		conn.close();
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		
		if(main.getPassword().equals("p"))
        {
        	main.switchScene("/controllers/fxml/ChangePassword.fxml");
        } else {
        	main.switchScene("/controllers/fxml/HomePage.fxml");
        }
		
    }

    /**
	 * Overrides default initialize method and save possible options 
	 * @param URL and Resource Bundle
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
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
