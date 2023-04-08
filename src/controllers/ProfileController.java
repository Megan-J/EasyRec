package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ResourceBundle;

import org.controlsfx.control.CheckComboBox;

public class ProfileController implements Initializable{

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
    private Button cancelButton;

    Stage stage;
    Scene scene;
    @FXML
    void cancelPressed(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("/controllers/fxml/Scene1.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    void saveButtonPressed(ActionEvent event) throws IOException{
    	
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
    }

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
