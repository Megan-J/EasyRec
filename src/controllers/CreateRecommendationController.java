package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.CheckComboBox;

//CANNOT IMPORT THIS; HELP
//import org.controlsfx.control.CheckComboBox;

import java.sql.*;
import java.time.LocalDate;

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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateRecommendationController implements Initializable{
	
	private Stage stage;
	private Scene scene;
	
	private String[] genderList = {"Male", "Female", "Other"};
	private String[] programList = {"MS", "MBA", "PhD"};
	private String[] semesterList = {"Spring", "Fall", "Summer"};



    @FXML
    private Button submit;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private DatePicker date;

    @FXML
    private ChoiceBox<String> gender;

    @FXML
    private TextField targetSchool;

    @FXML
    private ChoiceBox<String> program;

    @FXML
    private ChoiceBox<String> firstSemester;

    @FXML
    private TextField firstSemesterYear;
    
    @FXML
    private CheckComboBox<String> addCourses;

    @FXML
    private TextField addCourseYears;

    @FXML
    private CheckComboBox<String> perCharsBox;

    @FXML
    private CheckComboBox<String> acaChars;
    
     
    
    @FXML
    void switchToScene1(ActionEvent event) throws IOException{
    	
		Parent root = FXMLLoader.load(getClass().getResource("gui/Scene1.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
    }
    
    
  	
    @FXML
    void submitRecommendation(ActionEvent event) throws IOException{
    	try {
    		LocalDate dateVal =	date.getValue();
    		String dateString = dateVal.toString();
    		String courses = "";
    		for(String x : addCourses.getCheckModel().getCheckedItems())
    		{
    			courses += x + ", ";
    		}
    		String perChars = "";
    		for(String x: perCharsBox.getCheckModel().getCheckedItems())
    		{
    			perChars += x + ", ";
    		}
    		String acadChars = "";
    		for(String x: acaChars.getCheckModel().getCheckedItems())
    		{
    			acadChars += x + ", ";
    		}
    		
    		
    		String url = "jdbc:sqlite:recommendation.db";
    		Connection conn = DriverManager.getConnection(url);
    		String sql = "INSERT INTO Recommendation (FirstName, LastName, Gender, TargetSchool, CurrentDate, Program, FirstSemester, FirstYear, OtherCourses, LetterGrade, PersonalCharacteristics, AcademicCharacteristics) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    		PreparedStatement st = conn.prepareStatement(sql);
    		st.setString(1, firstName.getText());
    		st.setString(2,  lastName.getText());
    		st.setString(3, gender.getValue());
    		st.setString(4, targetSchool.getText());
    		st.setString(5, dateString);
    		st.setString(6, program.getValue());
    		st.setString(7, firstSemester.getValue());
    		st.setString(8, firstSemesterYear.getText());
    		st.setString(9, courses);
    		st.setString(10, addCourseYears.getText());
    		st.setString(11, perChars);
    		st.setString(12, acadChars);
    		
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
    	gender.getItems().addAll(genderList);
    	program.getItems().addAll(programList);
    	firstSemester.getItems().addAll(semesterList);
    	
    	final ObservableList<String> acadChars = FXCollections.observableArrayList();
		final ObservableList<String> perChars = FXCollections.observableArrayList();
		final ObservableList<String> courses = FXCollections.observableArrayList();

    	
    	acadChars.add("submitted well-written assignments");
    	acadChars.add("participated in all of my class activities");
    	acadChars.add("worked hard");
    	acadChars.add("was very well prepared for every exam and assignment");
    	acadChars.add("picked up new skills very quickly");
    	acadChars.add("was able to excel academically at the top of my class");
    	
    	perChars.add("very passionate");
    	perChars.add("very enthusiastic");
    	perChars.add("punctual");
    	perChars.add("attentive");
    	perChars.add("polite");
    	
    	courses.add("CS151: Object-Oriented Design");
    	courses.add("CS166: Information Security");
    	courses.add("CS154: Theory of Computation");
    	courses.add("CS160: Software Engineering");
    	courses.add("CS256: Cryptography");
    	courses.add("CS146: Data Structures and Algorithms");
    	courses.add("CS152: Programming Language Paradigm");

    	perCharsBox.getItems().addAll(perChars);
    	acaChars.getItems().addAll(acadChars);
    	addCourses.getItems().addAll(courses);
    	
	}

}