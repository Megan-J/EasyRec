package dao;
import java.sql.*;

public class SQLiteConnection {
	
	public static Connection Connect() {
		
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:easyrec.sqlite");
			
			//Creating table for user sign in info
			Statement statement = conn.createStatement();
			
			String sql = "CREATE TABLE IF NOT EXISTS user (\n"
	                + "	password	TEXT	DEFAULT 'p'"
	                + ");";	
					
			statement.executeUpdate(sql);
			statement.close();
			
			return conn;
		}
		catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
	}

}
