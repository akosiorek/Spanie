package gui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ResultSetNumPokoje extends AbstractTableModel {
	
    private String[] columnNames;
    @SuppressWarnings("rawtypes")
	private Class[] columnClasses;
    private String[] types = new String[] {"dwojk", "trojk", "piatk", "para", "exclu"};
    private String[] properTypes = new String[] {"Dwójka", "Trójka", "Piątka", "Para", "Exclusive"};
    private List<List<Object>> cells = new ArrayList<List<Object>>();
    
    
    private void fillColumn(ResultSet rs) throws SQLException {
    	
    	boolean matched[] = new boolean[] {false, false, false, false, false};
    	while(rs.next()) {
    		
    		String type = rs.getString(2);
    		for(int i = 0; i < 5; i++) {
    			if(type.equals(types[i])) {
    				
    				matched[i] = true;
    				cells.get(i).add(rs.getString(1));
    			}
    		}
    	}
    	
    	for(int i = 0; i < 5; i++) {
    		
    		if(!matched[i])
    			cells.get(i).add(Integer.toString(0));
    	}
    }
    
    private void addTypes() {
    	
    	for(int i = 0; i < 5; i++)
    		cells.get(i).add(properTypes[i]);
    }
    

    public ResultSetNumPokoje(ResultSet rs1, ResultSet rs2, ResultSet rs3) throws SQLException {
        
        columnNames = new String[] {"pokoje_wolne", "pokoje_zarezerwowane", "pokoje_zajete", "rodzaj_pokoju"};
        columnClasses = new Class[] {String.class, String.class, String.class, String.class};
        
        for(int i = 0; i < 5; i++) {
    		cells.add(new ArrayList<Object>());
		}
       
        fillColumn(rs1);
        fillColumn(rs2);
        fillColumn(rs3);
        addTypes();
    }

    public int getRowCount() {
        return cells.size();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    @SuppressWarnings("unchecked")
	@Override
    public Class getColumnClass(int columnIndex) {
        if (columnClasses != null) {
            return columnClasses[columnIndex];
        } else {
            return Object.class;
        }
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return cells.get(rowIndex).get(columnIndex);
    }

    @Override
    public String getColumnName(int columnIndex) {
    	String str = columnNames[columnIndex].replace("_", " ");
    	return str.substring(0, 1).toUpperCase() + str.substring(1);
         
    }
}