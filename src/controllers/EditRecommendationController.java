package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.CheckComboBox;

import application.Main;

import java.sql.*;
import java.time.LocalDate;
import model.PasswordModel;
import model.ProfileModel;
import model.RecommendationModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileWriter;

public class EditRecommendationController implements Initializable {
	

	private ProfileModel profileModel = new ProfileModel();
	
	private RecommendationModel recommendationModel = new RecommendationModel();

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
    
    @FXML
    protected Button deleteRec;
    
    @FXML 
    protected Label errorLabel;
    
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
	 * Deletes Selected Recommendation
	 * @param event
	 * @throws IOException
	 */
    @FXML
    void executeDeleteRec(ActionEvent event) throws IOException{
    	File toDelete = new File("src/resources/recs/" + CommonLibrary.recTitle);
    	toDelete.delete();
    	
    	try {
    	String url = "jdbc:sqlite:src/database/recommendation.db";
		Connection conn = DriverManager.getConnection(url);
		
		String deleteQuery = "DELETE FROM Recommendation WHERE LastName = ?";
		PreparedStatement deleteSt = conn.prepareStatement(deleteQuery);
		deleteSt.setString(1, CommonLibrary.recTitle);
		deleteSt.execute();
		main.switchScene("/controllers/fxml/HomePage.fxml");
    	}
    	catch(Exception e)
    	{
    		System.err.println(e.getMessage());
    	}
    	
    }
    
    
    /**
	 * Submits recommendation to database
	 * @param event
	 * @throws IOException
	 */
    @FXML
    void submitRecommendation(ActionEvent event) throws IOException, SQLException{
    	if(!firstName.getText().equals("") && !lastName.getText().equals("") && !gender.getValue().equals("")
    			&& !program.getValue().equals("") && !targetSchool.getText().equals("") && !date.getValue().equals("")
    			&& !firstSemester.getValue().equals("") && !firstSemesterYear.getText().equals("") && addCourses.getCheckModel().getItemCount() > 0
    			&& perCharsBox.getCheckModel().getItemCount() > 0 && acaChars.getCheckModel().getItemCount() > 0){
    		
    
    	Main main = new Main();
    	new PasswordModel();

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
		
		ArrayList<String> profileData = profileModel.getProfile();
		String profName = profileData.get(0);
		String profTitle = profileData.get(1);
		String profDep = profileData.get(2);
		String profEmail = profileData.get(3);
		String profPhone = profileData.get(4);
		
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
		
		String gradesArr[] = addCourseYears.getText().split(",");
		String coursesArr[] = courses.split(",");

		String temp = "I first met " + firstName.getText() + " in " + firstSemester.getValue() + " of " + firstSemesterYear.getText() + " when " + pronoun.toLowerCase() + " earned ";
		for(int i = 0; i < coursesArr.length - 2; i++)
		{
			temp += "a " + gradesArr[i] + "from my " + coursesArr[i]
					+ ", ";
		}
		temp += "and a " + gradesArr[coursesArr.length-2] + " from my " + coursesArr[coursesArr.length-2] + ".";
		
		
		
		String academicsArr[] = acadChars.split(",");
		String academics = "";
		if(academicsArr.length > 1) {
			for(int i = 0; i < academicsArr.length - 2; i++)
			{
				academics += academicsArr[i] + ",";
			}
			
			academics += " and" + academicsArr[academicsArr.length-2];
		}
		else
		{
			academics = academicsArr[0];
		}
		
		String personalsArr[] = perChars.split(",");
		String personals = "";
		if (personalsArr.length > 1) {
			for(int i = 0; i < academicsArr.length - 2; i++)
			{
				personals += personalsArr[i] + ",";
			}
			
			personals += " and" + personalsArr[personalsArr.length-2];
		}
		else
		{
			personals = personalsArr[0];
		}
		recWriter.write(
				
				
				"Letter of Recommendation\n\n"
				
				+ "For: " + firstName.getText() + " " + lastName.getText() +
				
				"\n\nDate: " + dateString +
				
				"\n\nTo: Graduate Admissions Committee "
				
				+ "\n\nI am writing this letter to recommend my former student " + firstName.getText() + " " + lastName.getText() +
				
				" who is applying for the " + program.getValue() + " in your school. \n\n"
				
				+ temp + "\n\n"
				
				+ firstName.getText() + " " + academics + ". \n\n"
				
				+ pronoun + " was always " + personals + ". \n\n"
				
				+ "Furthermore, I noticed from the term project result, " + pronoun.toLowerCase() + " developed leadership, time management, and problem-solving skills.\n"
				+ pronoun + " worked effectively with the team members and delegated tasks appropriately. " + pronoun + " was able to deliver a successful project in a timely fashion.\n" 
				
				+ "I believe that " + firstName.getText() + " has the capacity to excel at higher education program and this is my pleasure to highly recommend him. \n\n"
				
				+ "Please do not hesitate to contact me with further questions.\n\n\n"
				
				
				+ "Very Respectfully,\n\n"
	
				+ profName + "\n\n"
				
				+ profTitle + "\n"
				
				+ profDep + "\n"
				
				+profEmail + "\n"
				
				+ profPhone + "\n"
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
    	else {
    		errorLabel.setOpacity(1);
    	}
        }

    /**
	 * Adds information to the database
	 * @param URL, ResourceBundle
	 */
	@Override	
	/*
	public void initialize(URL location, ResourceBundle resources) {
		
		recommendationModel = new RecommendationModel();
		
		try {
	    	perCharsBox.getItems().addAll(recommendationModel.getPersonalChars());
	    	acaChars.getItems().addAll(recommendationModel.getAcademicChars());
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		*/
	
    public void initialize(URL location, ResourceBundle resources) {
		
		
		recommendationModel = new RecommendationModel();
		
		try {
			gender.getItems().addAll(recommendationModel.getGenders());
			program.getItems().addAll(recommendationModel.getPrograms());
			firstSemester.getItems().addAll(recommendationModel.getSemesters());
			
	    	perCharsBox.getItems().addAll(recommendationModel.getPersonalChars());
	    	acaChars.getItems().addAll(recommendationModel.getAcademicChars());
	    	addCourses.getItems().addAll(recommendationModel.getCourses());
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

    	
	}

}