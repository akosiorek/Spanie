package raporty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.dbDAO;

public class ColumnEntries {
	
	List<String> entries;
	
	public ColumnEntries(String tabela, String kolumna) {
		
		entries = new ArrayList<String>();
		
		dbDAO db =  new dbDAO();
		
		db.establishConnection();
		ResultSet rs = db.executeQuery(new SimpleSelectQuery(tabela, kolumna).toString());
		
		try {
			while(rs.next()) 
				entries.add(rs.getString(1));			
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();		
		}		
		db.closeConnection();			
	}

	public List<String> getEntries() {
		return entries;
	}
	
	public String[] getEntriesArray() {
		return entries.toArray(new String[entries.size()]);
	}
}
