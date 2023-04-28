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

public class ProfileModel {
	
	private DbSqlite dal = DbSqlite.getInstance();
	private ArrayList<String> profileData = new ArrayList<>();
	
	private String name;
	private String title;
	private String schoolDep;
	private String email;
	private String phone;
	private String sem;
	private String coursesSelect;
	
	private ObservableList<String> semesters = FXCollections.observableArrayList();
	private ObservableList<String> courses = FXCollections.observableArrayList();
	
	/**
	 * Constructor
	 */
	public ProfileModel()
	{
		 dal = DbSqlite.getInstance();
	}
	
	
	/**
	 * Gets profile data
	 * @throws SQLException
	 */
	public ArrayList<String> getProfile() throws SQLException
	{
		name = getProfileData(dal.getConnection(), "Name", "Profile").get(0);
		title = getProfileData(dal.getConnection(), "Title", "Profile").get(0);
		schoolDep = getProfileData(dal.getConnection(), "SchoolName", "Profile").get(0);
		email = getProfileData(dal.getConnection(), "Email", "Profile").get(0);
		phone = getProfileData(dal.getConnection(), "Phone", "Profile").get(0);
		sem = getProfileData(dal.getConnection(), "SemestersTaught", "Profile").get(0);
		coursesSelect = getProfileData(dal.getConnection(), "CoursesTaught", "Profile").get(0);
		
		profileData.add(name);
		profileData.add(title);
		profileData.add(email);
		profileData.add(schoolDep);
		profileData.add(phone);
		profileData.add(sem);
		profileData.add(coursesSelect);
		
		return profileData;
	}
	
	
	/**
	 * Stores information given in profile to database
	 * @param name, title, school department, email, phone, semester, courses
	 * @throws SQLException
	 */
	public void setProfile(String name, String title, String schoolDep, String email, String phone, String sem, String courses) throws SQLException
	{
		try {
			Connection conn = dal.getConnection();
			
			String delString = "DELETE FROM Profile";
			Statement del = conn.createStatement();
			del.executeUpdate(delString);
			
			String sql = "INSERT INTO Profile (Name, Title, SchoolName, Email, Phone, SemestersTaught, CoursesTaught) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, name);
			st.setString(2, title);
			st.setString(3, schoolDep);
			st.setString(4, email);
			st.setString(5, phone);
			st.setString(6, sem);
			st.setString(7, courses);
		
			int rowsInserted = st.executeUpdate();
			if(rowsInserted > 0)
				System.out.println("Success");
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * Get the semesters in database
	 * @throws SQLException
	 */
	public ObservableList<String> getSemesters() throws SQLException
	{
		ArrayList<String> semester = getProfileData(dal.getConnection(), "Semester", "Semesters");
		semesters.addAll(semester);
		return semesters;
	}
	
	/**
	 * Get the semesters in database
	 * @throws SQLException
	 */
	public ObservableList<String> getCourses() throws SQLException
	{
		ArrayList<String> courseList = getProfileData(dal.getConnection(), "Course", "Courses");
		courses.addAll(courseList);
		return courses;
	}
	
	/**
	 * Gets data from the database
	 * @param connection, column, table
	 * @throws SQLException
	 */
	private ArrayList<String> getProfileData(Connection conn, String column, String table) throws SQLException
	{
			ArrayList<String> dataValues = new ArrayList<>();
			String data = "";
			String select = "SELECT ";
			select = select + column + " FROM ";
			select = select + table;
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(select);
			
			while (rs.next())
			{
				data = rs.getString(column);
				dataValues.add(data);
			}
	
		return dataValues;
	}
	
}
