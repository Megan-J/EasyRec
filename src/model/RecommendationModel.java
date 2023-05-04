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
	
	private ObservableList<String> personalChars = FXCollections.observableArrayList();
	private ObservableList<String> academicChars = FXCollections.observableArrayList();
	private ObservableList<String> programs = FXCollections.observableArrayList();
	private ObservableList<String> genders = FXCollections.observableArrayList();
	
	private ObservableList<String> courses = FXCollections.observableArrayList();
	private ObservableList<String> semesters = FXCollections.observableArrayList();
	/**
	 * Constructor
	 */
	public RecommendationModel()
	{
		 dal = DbSqlite.getInstance();
	}
	
	public ArrayList<String> getRecommendation(String columnVal) throws SQLException {
		
		recommendationData.addAll(getRecommendationData(dal.getConnection(), columnVal));
		
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
		ArrayList<String> personalChar = getRecommendationData(dal.getConnection(), "PersonalCharacteristic", "PersonalCharacteristics");
		personalChars.addAll(personalChar);
		return personalChars;
	}
	
	public ObservableList<String> getAcademicChars() throws SQLException
	{
		ArrayList<String> academicChar = getRecommendationData(dal.getConnection(), "AcademicCharacteristic", "AcademicCharacteristics");
		academicChars.addAll(academicChar);
		return academicChars;
	}
	
	public ObservableList<String> getPrograms() throws SQLException
	{
		ArrayList<String> program = getRecommendationData(dal.getConnection(), "Program", "Programs");
		programs.addAll(program);
		return programs;
	}
	
	public ObservableList<String> getGenders() throws SQLException
	{
		ArrayList<String> gender = getRecommendationData(dal.getConnection(), "Gender", "Genders");
		genders.addAll(gender);
		return genders;
	}
	
	public ObservableList<String> getSemesters() throws SQLException
	{
		ArrayList<String> semester = getRecommendationData(dal.getConnection(), "Semester", "Semesters");
		semesters.addAll(semester);
		return semesters;
	}
	
	/**
	 * Get the courses in database
	 * @throws SQLException
	 */
	public ObservableList<String> getCourses() throws SQLException
	{
		ArrayList<String> courseList = getRecommendationData(dal.getConnection(), "Course", "Courses");
		courses.addAll(courseList);
		return courses;
	}
	
	private ArrayList<String> getRecommendationData(Connection conn, String column, String table) throws SQLException
	{
		ArrayList<String> dataValues = new ArrayList<>();
		String data = "";
		String select = "SELECT ";
		select = select + column + " FROM ";
		select = select + table;
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(select);
		
		while (rs.next()) {
			data = rs.getString(column);
			dataValues.add(data);
		}
		
		return dataValues;
		
	}
	private ArrayList<String> getRecommendationData(Connection conn, String columnVal) throws SQLException
	{
		ArrayList<String> dataValues = new ArrayList<>();
		String select = "SELECT * FROM Recommendation WHERE LastName=?";
		PreparedStatement stmt = conn.prepareStatement(select);
		stmt.setString(1, columnVal);
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			dataValues.add(rs.getString("FirstName"));
			dataValues.add(rs.getString("LastName"));
			dataValues.add(rs.getString("Gender"));
			dataValues.add(rs.getString("TargetSchool"));
			dataValues.add(rs.getString("CurrentDate"));
			dataValues.add(rs.getString("Program"));
			dataValues.add(rs.getString("FirstSemester"));
			dataValues.add(rs.getString("FirstYear"));
			dataValues.add(rs.getString("OtherCourses"));
			dataValues.add(rs.getString("LetterGrade"));
			dataValues.add(rs.getString("PersonalCharacteristics"));
			dataValues.add(rs.getString("AcademicCharacteristics"));
			dataValues.add(rs.getString("FirstCourse"));
			dataValues.add(rs.getString("FirstCourseGrade"));
			
			
		}

		return dataValues;
		
		
	}

}
