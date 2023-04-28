package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import dal.DbSqlite;

public class Password {
	private DbSqlite dal = DbSqlite.getInstance(); //instance of database connection
	
	private String passwordStr; //private instance variable that stores the User's password
	
	public Password() throws IOException //constructor for Password object that Controller will use to manipulate password
	{
		dal = DbSqlite.getInstance();
	}
	
	public String getPassword() throws SQLException //getter method for password
	{
		String select = "SELECT PasswordStr FROM Password";
		Connection conn = dal.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(select);
		
		while (rs.next())
		{
			passwordStr = rs.getString("passwordStr");
		}
		return passwordStr;
	}
	
	public void setPassword(String p) throws SQLException //sets the password to String param p
	{
		Connection conn = dal.getConnection();
		String currentPass = getPassword();
		String update = "UPDATE Password SET PasswordStr = '";
		update = update + p + "' WHERE PasswordStr = '" + currentPass + "'";
		PreparedStatement pstmt = conn.prepareStatement(update);
		if (pstmt.executeUpdate() > 0)
		{
			System.out.println("Password update successful");
		}
		else
		{
			System.out.println("Password update failed");
		}
	}
}
