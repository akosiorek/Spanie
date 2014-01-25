package raporty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.dbDAO;

public class RodzajeUslug {

	
	List<String> uslugi;
	
	public RodzajeUslug() {
		
		uslugi = new ArrayList<String>();
		
		dbDAO db =  new dbDAO();
		
		db.establishConnection();
		
		ResultSet rs = db.executeQuery(new RodzajUslugiQuery().toString());
		
		try {
			while(rs.next()) 
				uslugi.add(rs.getString(1));			
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();		
		}		
		db.closeConnection();
		
		
		
			
	}

	public List<String> getUslugi() {
		return uslugi;
	}
	
	public String[] getUslugiArray() {
		return uslugi.toArray(new String[uslugi.size()]);
	}
}
