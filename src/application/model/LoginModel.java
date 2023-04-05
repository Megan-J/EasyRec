package application.model;
import java.sql.*;
import application.SQLiteConnection;

public class LoginModel {
	
	Connection conn;
	
	public LoginModel() {
		conn = SQLiteConnection.ConnectDb();
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
	
	public boolean doesMatch(String password) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String query = "SELECT defaultPassword";
		
		try {
			preparedStatement = conn.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				String dbPassword = resultSet.getString("defaultPassword");
				if(dbPassword.equals(password)) {
					return true;
				}
				else {
					return false;
				}
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
