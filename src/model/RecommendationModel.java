package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dal.DbSqlite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RecommendationModel {
	
	private DbSqlite dal = DbSqlite.getInstance();
	private ArrayList<String> recommendationData = new ArrayList<>();
	
	private String firstName;
	private String lastName;
	private String gender;
	private String targetSchool;
	private String dateString;
	private String firstSemester;
	private String firstSemesterYear;
	private String course;
	private String courseYear;
	
	private ObservableList<String> personalChars = FXCollections.observableArrayList();
	private ObservableList<String> academicChars = FXCollections.observableArrayList();
	
	/**
	 * Constructor
	 */
	public RecommendationModel()
	{
		 dal = DbSqlite.getInstance();
	}
	
	public ArrayList<String> getRecommendation() throws SQLException {
		
		firstName = getRecommendationData(dal.getConnection(), "First Name", "Recommendation").get(0);
		lastName = getRecommendationData(dal.getConnection(), "Last Name", "Recommendation").get(0);
		gender = getRecommendationData(dal.getConnection(), "Gender", "Recommendation").get(0);
		targetSchool = getRecommendationData(dal.getConnection(), "TargetSchool", "Recommendation").get(0);
		dateString = getRecommendationData(dal.getConnection(), "Date", "Recommendation").get(0);
		firstSemester = getRecommendationData(dal.getConnection(), "FirstSemester", "Recommendation").get(0);
		firstSemesterYear = getRecommendationData(dal.getConnection(), "FirstSemesterYear", "Recommendation").get(0);
		course = getRecommendationData(dal.getConnection(), "Course", "Recommendation").get(0);
		courseYear = getRecommendationData(dal.getConnection(), "CourseYear", "Recommendation").get(0);
		
		recommendationData.add(firstName);
		recommendationData.add(lastName);
		recommendationData.add(gender);
		recommendationData.add(targetSchool);
		recommendationData.add(dateString);
		recommendationData.add(firstSemester);
		recommendationData.add(firstSemesterYear);
		recommendationData.add(course);
		recommendationData.add(courseYear);
		
		return recommendationData;
	}
	
	public void setRecommendation(String firstName, String lastName, String gender, String targetSchool,
			String dateString, String firstSemester, String firstSemesterYear, String course, String courseYear) throws SQLException {
		
		try {
			Connection conn = dal.getConnection();
			
			String delString = "DELETE FROM Recommendation";
			Statement del = conn.createStatement();
			del.executeUpdate(delString);
			
			String sql = "INSET INTO Recommendation (FirstName, LastName, Gender, TargetSchool, Date, FirstSemester, FirstSemesterYear, Course, CourseYear) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, firstName);
			st.setString(2, lastName);
			st.setString(3, gender);
			st.setString(4, targetSchool);
			st.setString(5, dateString);
			st.setString(6, firstSemester);
			st.setString(7, firstSemesterYear);
			st.setString(8, course);
			st.setString(9, courseYear);
			
			int rowsInserted = st.executeUpdate();
			if(rowsInserted > 0) {
				System.out.println("Success");
			}
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
	}
	
	public ObservableList<String> getPersonalChars() throws SQLException
	{
		ArrayList<String> personalChar = getRecommendationData(dal.getConnection(), "Personal Characteristic", "Personal Characteristics");
		personalChars.addAll(personalChar);
		return personalChars;
	}
	
	public ObservableList<String> getAcademicChars() throws SQLException
	{
		ArrayList<String> academicChar = getRecommendationData(dal.getConnection(), "Academic Characteristic", "Academic Characteristics");
		academicChars.addAll(academicChar);
		return academicChars;
	}
	
	
	private ArrayList<String> getRecommendationData(Connection conn, String column, String table) throws SQLException
	{
		ArrayList<String> dataValues = new ArrayList<>();
		String data = "";
		String select = "SELECT ";
		select = select + column + "FROM ";
		select = select + table;
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(select);
		
		while (rs.next()) {
			data = rs.getString(column);
			dataValues.add(data);
		}
		
		return dataValues;
		
	}

}
