package gui;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;

import raporty.KlienciOpisQuery;
import raporty.KlienciQuery;

import com.michaelbaranov.microba.calendar.DatePicker;

import dao.dbDAO;

public class Raport3Panel extends JPanel {
	
	private WidokPanel parent;
	
	private KlienciQuery klienciQuery = new KlienciQuery();
	private KlienciOpisQuery klienciOpisQuery = new KlienciOpisQuery();
	
	protected JButton btnZapisz = new JButton("Zapisz");
	protected JButton btnSzczegoly = new JButton("Szczegóły");
	protected JButton btnWyszukaj = new JButton("Wyszukaj");
	protected JButton btnDrukuj = new JButton("Drukuj");
	
	protected JLabel lblOd = new JLabel("Od:");
	private DatePicker datePickerOd = new DatePicker();
	
	protected JTable upperTable = new JTable();	
	protected JTable lowerTable = new JTable();	
	protected JTableHeader lowerTableHeader;
	protected JTableHeader upperTableHeader;
	protected JScrollPane lowerTableScrollPane;
	protected JScrollPane upperTableScrollPane;
	
	dbDAO db = new dbDAO();	
	
	private boolean szczegoly = false;
	
	/**
	 * Create the panel.
	 */
	public Raport3Panel(WidokPanel _parent) {
		this.parent = _parent;
		setLayout(null);
		setSize(800, 600);
		
		btnZapisz.setBounds(358, 533, 82, 25);
		add(btnZapisz);
		
		btnDrukuj.setBounds(446, 533, 80, 25);
		add(btnDrukuj);
		
		btnSzczegoly.setBounds(532, 533, 105, 25);
		add(btnSzczegoly);
		
		upperTableScrollPane = new JScrollPane(upperTable);
		upperTableScrollPane.setBounds(48, 107, 589, 171);
		upperTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		upperTable.setAutoResizeMode(JTable.WIDTH);
		add(upperTableScrollPane);
			
		lowerTableScrollPane = new JScrollPane(lowerTable);
		lowerTableScrollPane.setBounds(48, 324, 0, 171);
//		lowerTable.setAutoResizeMode(JTable.WIDTH);
		add(lowerTableScrollPane);
		
		lblOd.setBounds(48, 32, 31, 15);
		add(lblOd);
		
		datePickerOd.setBounds(74, 24, 148, 31);
		add(datePickerOd);
		
		btnWyszukaj.setBounds(234, 27, 102, 25);
		add(btnWyszukaj);		
		
		upperTableHeader = upperTable.getTableHeader();
		upperTableHeader.setReorderingAllowed(false);

		lowerTableHeader = lowerTable.getTableHeader();
		lowerTableHeader.setReorderingAllowed(false);
		
		addEventHandling();		
		
		refreshUpperTable(null, null);
		refreshLowerTable(null, null);		
		
	}
	
	public void refreshUpperTable(Date dataOd, String sortBy) {
		
		if(dataOd != null) klienciQuery.setDate(dataOd);
		if(sortBy != null) {			
		
			boolean sortOrder = klienciQuery.getSortOrder();
			if(klienciQuery.getSort().equals(sortBy))
				sortOrder = !sortOrder;			
			klienciQuery.setSort(sortBy, sortOrder);		
			}
		
		
		db.establishConnection();
		
		ResultSet rs = db.executeQuery(klienciQuery.toString());
		
		try {
			upperTable.setModel(new ResultSetTableModel(rs));
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();		
		}		
		db.closeConnection();
		upperTable.getColumnModel().getColumn(1).setMinWidth(150);	
	}
	
public void refreshLowerTable(Date dataOd, String sortBy) {
		
		if(dataOd != null) klienciOpisQuery.setDate(dataOd);
		if(sortBy != null) {			
		
			boolean sortOrder = klienciOpisQuery.getSortOrder();
			if(klienciOpisQuery.getSort().equals(sortBy))
				sortOrder = !sortOrder;			
			klienciOpisQuery.setSort(sortBy, sortOrder);		
			}
		
		db.establishConnection();
		
		ResultSet rs = db.executeQuery(klienciOpisQuery.toString());
		
		try {
			lowerTable.setModel(new ResultSetTableModel(rs));
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();		
		}		
		db.closeConnection();
		lowerTable.getColumnModel().getColumn(1).setMinWidth(300);	
		
	}
	
	private void addEventHandling() {

		btnWyszukaj.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent evt) {
				
				refreshUpperTable(datePickerOd.getDate(), null);
				refreshLowerTable(datePickerOd.getDate(), null);
			}
		});
		
		upperTableHeader.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent mouseEvent) {
		      int index = upperTable.convertColumnIndexToModel(upperTable.columnAtPoint(mouseEvent.getPoint()));
		      if (index >= 0) {
		      	
	      		String newSort = null;	      		        
		        switch(index) {	      		   
		        case 0: newSort = KlienciQuery.NAZWISKO; break;
		        case 1: newSort = KlienciQuery.NUMER_DOKUMENTU; break;
		        case 2: newSort = KlienciQuery.NR_POBYTU; break;
		        case 3: newSort = KlienciQuery.OD; break;
		        case 4: newSort = KlienciQuery.DO; break;
		        case 5: newSort = KlienciQuery.DLUZNIK; break;
		        }		       		        
		        refreshUpperTable(null, newSort);
		      }
		    }
		});		

		lowerTableHeader.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent mouseEvent) {
		      int index = lowerTable.convertColumnIndexToModel(lowerTable.columnAtPoint(mouseEvent.getPoint()));
		      if (index >= 0) {
		      	
	      		String newSort = null;	      		        
		        switch(index) {	      		   
		        case 0: newSort = KlienciOpisQuery.NAZWISKO; break;
		        case 1: newSort = KlienciOpisQuery.OPIS; break;
		        }		       		        
		        refreshLowerTable(null, newSort);
		      }
		    }
		});		

		btnSzczegoly.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent arg0) {		
				
				szczegoly = !szczegoly;
				if(!szczegoly) lowerTableScrollPane.setBounds(48, 324, 0, 171);
				else lowerTableScrollPane.setBounds(48, 324, 450, 171);		
				
				refreshLowerTable(datePickerOd.getDate(), null);
				
			}
		});
	}
}