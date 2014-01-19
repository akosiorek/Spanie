package DAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class dbDAO {

	private final static String PROPS = "resources//db.properties";
	private final static String URL = "url";
	private final static String DB_NAME = "dbName";
	private final static String DRIVER = "driver";
	private final static String USER_NAME = "userName";
	private final static String PASSWORD = "password";
	
	private Connection connection;
	
	public dbDAO() {
		
		connection = null;
	}
	
	private void establishConnection() {
		
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(PROPS));
		} catch(IOException e) {
			System.err.println("Couldn't read any database properties");
		}
			
		try {
			Class.forName(prop.getProperty(DRIVER)).newInstance();
			connection = DriverManager.getConnection(prop.getProperty(URL) + prop.getProperty(DB_NAME)
					, prop.getProperty(USER_NAME), prop.getProperty(PASSWORD));
			System.out.println("Connected to a database");
		} catch (Exception e) {
			System.err.println("No connection");
		}
	}
	private void closeConnection() {
		
		if(connection != null) {
			
			try {
				connection.close();
				System.out.println("Disconnected from the database");
			} catch (SQLException e) {
				System.err.println("Couldn't close the connection");
			}
		}
	}
	
	
	public ResultSet executeQuery(String query) {
		
		establishConnection();
		
		
		ResultSet rs = null;
		try(Statement stmt = connection.createStatement()) {
		rs = stmt.executeQuery(query);
		} catch(SQLException e) {
			
			System.err.println("Couldn't execute the following query:" + query);
		}
		
		closeConnection();
		return rs;
	}
	
	public static void main(String[] args) {
		dbDAO dao = new dbDAO();
		dao.establishConnection();
		
		
		ResultSet rs = null;
		try(Statement stmt = dao.connection.createStatement()) {
		rs = stmt.executeQuery("SELECT title, author FROM classics");
		while(rs.next()) {
			System.out.println("Title: " + rs.getString(1) + ", Author: " + rs.getString(2));
		}
		} catch(SQLException e) {
			
			System.err.println("Couldn't execute a statement");
		}
		
		dao.closeConnection();
		
		
	}
}
