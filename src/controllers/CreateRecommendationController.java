package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.CheckComboBox;

import application.Main;

import java.sql.*;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileWriter;

public class CreateRecommendationController implements Initializable {
	
	
	private String[] genderList = {"Male", "Female", "Other"};
	private String[] programList = {"MS", "MBA", "PhD"};
	private String[] semesterList = {"Spring", "Fall", "Summer"};

    @FXML
    protected Button submit;

    @FXML
    protected TextField firstName;

    @FXML
    protected TextField lastName;

    @FXML
    protected DatePicker date;

    @FXML
    protected ChoiceBox<String> gender;

    @FXML
    protected TextField targetSchool;

    @FXML
    protected ChoiceBox<String> program;

    @FXML
    protected ChoiceBox<String> firstSemester;

    @FXML
    protected TextField firstSemesterYear;
    
    @FXML
    protected CheckComboBox<String> addCourses;

    @FXML
    protected TextField addCourseYears;

    @FXML
    protected CheckComboBox<String> perCharsBox;

    @FXML
    protected CheckComboBox<String> acaChars;
    
    Main main = new Main();
    /**
	 * Switches to Scene1
	 * @param event
	 * @throws IOException
	 */
    @FXML
    void cancelBtnPressed(ActionEvent event) throws IOException{
    	main.switchScene("/controllers/fxml/HomePage.fxml");
    }
    
    
    /**
	 * Submits recommendation to database
	 * @param event
	 * @throws IOException
	 */
    @FXML
    void submitRecommendation(ActionEvent event) throws IOException{

		LocalDate dateVal =	date.getValue();
		String dateString = dateVal.toString();
		
		String pronoun = "";
		if(gender.getValue().equals("Male"))
		{
			pronoun = "He";
		}
		else if(gender.getValue().equals("Female"))
		{
			pronoun = "She";
			
		}
		else
		{
			pronoun = "They";
		}
		
		
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



		File newRec = new File("src/resources/recs/" + lastName.getText());
		boolean created = newRec.createNewFile();
		System.out.println(created);
		FileWriter recWriter = new FileWriter("src/resources/recs/" + lastName.getText());
		
		recWriter.write(
				
				"Letter of Recommendation\n\n"
				+ "For: " + firstName.getText() + 
				"\n\nDate: " + dateString +
				"\n\nTo: Graduate Admissions Committee "
				+ "\n\nI am writing this letter to recommend my former student " + firstName.getText() + " " + lastName.getText() +
				" who is applying for the " + program.getValue() + " in your school. "
				+ "\n\nI met " + firstName.getText() + " in " + firstSemester.getValue() + " of " + firstSemesterYear.getText() + 
				" when " + pronoun.toLowerCase() + " enrolled in my " + courses + " course.\n\n" +
				firstName.getText() + " earned an A from this tough course, and this shows how knowledgeable and "
				+ "hard working " + pronoun.toLowerCase() + " is.\n\n"
				
				+ pronoun + " also earned " + " from my " + "course.\n\n"
				
				+ firstName.getText() 
				
				
				
				+ "Please do not hesitate to contact me with further questions.\n\n\n\n"
				
				+ "Very Respectfully,\n\n"
	
				
				);
				
		
		recWriter.close();
 
    	
    	try {
    		
    		String url = "jdbc:sqlite:src/database/recommendation.db";
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
    	
    	main.switchScene("/controllers/fxml/HomePage.fxml");
    }

    /**
	 * Adds information to the database
	 * @param URL, ResourceBundle
	 */
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