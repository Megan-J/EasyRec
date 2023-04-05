package application;

import java.sql.*;

public class SQLiteConnection {
	
	public static Connection ConnectDb() {
		
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
			
			//Creating table for user sign in info
			Statement statement = conn.createStatement();
			
			String sql = "CREATE TABLE IF NOT EXIST user (\n"
			        + "	defaultPassword	TEXT	NOT NULL"
			        + ");";	
					
			statement.executeUpdate(sql);
			statement.close();
			
			return conn;
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}
		
	}

}
