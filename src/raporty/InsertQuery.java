package raporty;

public class InsertQuery {
	
	private String[] insertQuery = new String[] { "INSERT INTO ", "", " VALUES (", "", ");"};
	private String table;
	private String values[];
	
	public String toString() {
		
		insertQuery[3] = "";
		for(int i = 0; i < values.length; ++i) {
			
			insertQuery[3] += "'" + values[i] + (i == values.length - 1 ? "'" : "',");
		}
		
		insertQuery[1] = table;		
		String query = "";
		for(final String part : insertQuery){
			query += part;
		}
		
		return query;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public void setValues(String[] values) {
		this.values = values;
	}
	
	public InsertQuery() {
		
		values = null;
		table = null;
	}
	
	public InsertQuery(String table, String[] values) {
		
		this.table = table;
		this.values = values;
	}

}



