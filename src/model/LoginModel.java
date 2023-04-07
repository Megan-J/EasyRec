package model;
import java.sql.*;

import dao.SQLiteConnection;

public class LoginModel {
	
	Connection conn;
	
	public LoginModel() {
		conn = SQLiteConnection.Connect();
		if(conn == null) {
			System.exit(1);
		}
	}
	
	public boolean isDBConnected() {
		try {
			return (!conn.isClosed());
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean defaultMatch(String password) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String query = "SELECT * FROM user WHERE password = ?";
		
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, password);
			
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				return true;
			}
			else {
				return false;
			}
		
		}
		catch(Exception e) {
			return false;
		}
		finally {
			preparedStatement.close();
			resultSet.close();
		}
		
		
	}

}
