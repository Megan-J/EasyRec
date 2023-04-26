package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dal.DbSqlite;

public class Profile {
	
	private DbSqlite dal = DbSqlite.getInstance();
	private ArrayList<String> profileData = new ArrayList<>();
	
	private String name;
	private String title;
	private String schoolDep;
	private String email;
	private String phone;
	private String sem;
	private String courses;
	
	public Profile()
	{
		 dal = DbSqlite.getInstance();
	}
	
	public ArrayList<String> getProfile() throws SQLException
	{
		name = getData(dal.getConnection(), "Name", "Profile");
		title = getData(dal.getConnection(), "Title", "Profile");
		schoolDep = getData(dal.getConnection(), "SchoolName", "Profile");
		email = getData(dal.getConnection(), "Email", "Profile");
		phone = getData(dal.getConnection(), "Phone", "Profile");
		sem = getData(dal.getConnection(), "SemestersTaught", "Profile");
		courses = getData(dal.getConnection(), "CoursesTaught", "Profile");
		
		profileData.add(name);
		profileData.add(title);
		profileData.add(email);
		profileData.add(schoolDep);
		profileData.add(phone);
		profileData.add(sem);
		profileData.add(courses);
		
		return profileData;
	}
	public void setProfile(String name, String title, String schoolDep, String email, String phone, String sem, String courses)
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
	
	private String getData(Connection conn, String column, String table) throws SQLException
	{
			String data = "";
			String select = "SELECT ";
			select = select + column + " FROM ";
			select = select + table;
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(select);
			
			while (rs.next())
			{
				data = rs.getString(column);
			}
	
		return data;
	}
	
//	public static void main (String args[]) throws SQLException
//	{
////		ArrayList<String> dataSet = new ArrayList<>();
////		Connection conn = dal.getConnection();
////		String name = getData(conn, "Name", "Profile");
////		System.out.println(name);
////		
////		try {
////			dataSet = getProfile();
////		} catch (SQLException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////		System.out.println(dataSet.toString());
////		setProfile("Ahmad", "Professor", "SJSU", "@", "510", "Fall, ", "CS151: Object-Oriented Design");
//	}
}
