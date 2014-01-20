package Logic;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import DAO.dbDAO;

public class Database {
	
	private static final String QUERIES = "resources//query.properties";
	private static final String reportA = "report1";
	private static final String reportB = "report2";
	private static final String reportC = "report3";
	private static final String reportD = "report4";
	
	private dbDAO db;
	
	private String query1;
	private String query2;
	private String query3;
	private String query4;
	
	
	public Database() {
		
		db = new dbDAO();
		readQueries();
	}
	
	private void readQueries() {
		
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(QUERIES));
			
			query1 = prop.getProperty(reportA);
			query2 = prop.getProperty(reportB);
			query3 = prop.getProperty(reportC);
			query4 = prop.getProperty(reportD);
			
		} catch(IOException e) {
			System.err.println("Couldn't read any database properties");
		}		
	}
	
	public List<String> executeA() {
		
		db.establishConnection();
		
		ResultSet rs = db.executeQuery(query1);
		
		List<String> ans = new ArrayList<String>();
		
		try {
			while(rs.next()) {
				
				ans.add(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		db.closeConnection();
		
		return ans;
	}
	
	public static void main(String[] args) {
		
		Database db = new Database(); 
		List<String> result = db.executeA();
		for(String str : result)
			System.out.println(str);
	}
}



