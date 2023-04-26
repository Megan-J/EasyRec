package dal;

import java.sql.Connection;
import java.sql.SQLException;

public interface DbConnectionInt {
	
	public Connection getConnection();
	
	public void closeConnection() throws SQLException;
}
