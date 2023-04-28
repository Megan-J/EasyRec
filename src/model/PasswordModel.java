package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import dal.DbSqlite;

public class PasswordModel {
	private DbSqlite dal = DbSqlite.getInstance(); //instance of database connection
	
	private String passwordStr; //private instance variable that stores the User's password
	
	/**
	 * Constructor for Password object that 
	 * Controller will use to manipulate password
	 * @throws SQLException
	 */
	public PasswordModel() throws IOException
	{
		dal = DbSqlite.getInstance();
	}
	
	
	/**
	 * Getter method for password
	 * @throws SQLException
	 */
	public String getPassword() throws SQLException
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
	
	/**
	 * Sets the password to String param p
	 * @param p
	 * @throws SQLException
	 */
	public void setPassword(String p) throws SQLException
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
