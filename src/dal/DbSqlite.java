package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbSqlite implements DbConnectionInt{

	private static DbSqlite instance = null;
	private static Connection connection;
	private static final String urlStr = "jdbc:sqlite:src/database/recommendation.db";
	
	private static Statement statement;
	private static ResultSet resultSet;
	
	private DbSqlite()
	{
		try 
		{
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection(urlStr);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static DbSqlite getInstance()
	{
		if (instance == null)
		{
			instance = new DbSqlite();
		}
		return instance;
	}
	
	public Connection getConnection() {
		return connection;
	}

	@Override
	public void closeConnection() throws SQLException {
		try
		{
			if (resultSet != null)
			{
				resultSet.close();
			}
			if (statement != null)
			{
				statement.close();
			}
			if (connection != null)
			{
				connection.close();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	
}
