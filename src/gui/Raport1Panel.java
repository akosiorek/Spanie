package gui;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import dao.dbDAO;
import raporty.ListaPokoiTypQuery;
import raporty.Query.*;

public class Raport1Panel extends JPanel {
	
	protected JButton btnZapisz;
	protected JButton btnDrukuj;
	protected JButton btnSzczegoly;
	protected JButton btnWyszukaj;
	protected JTextField txtParametr;
	
	protected JTable lowerTable;
	protected JTable upperTable;	
	
	protected JTableHeader lowerTableHeader;
	protected JTableHeader upperTableHeader;
	protected JLabel lblLowerTable;
	protected JLabel lblUpperTable;
	
	protected String upperColumnNames[];
	protected String lowerColumnNames[];
	
	dbDAO db;
	
	JScrollPane lowerTableScrollPane;
	JScrollPane upperTableScrollPane;
	
	private WidokPanel parent;
	private boolean szczegoly = false;
	private ListaPokoiTypQuery listaPokoiTypQuery;
	

	/**
	 * Create the panel.
	 */
	public Raport1Panel(WidokPanel _parent) {
		this.parent = _parent;
		setLayout(null);
		
		btnZapisz = new JButton("Zapisz");
		btnZapisz.setBounds(358, 533, 82, 25);
		add(btnZapisz);
		
		btnDrukuj = new JButton("Drukuj");
		btnDrukuj.setBounds(446, 533, 80, 25);
		add(btnDrukuj);
		
		btnSzczegoly = new JButton("Szczegóły");
		btnSzczegoly.setBounds(532, 533, 105, 25);
		add(btnSzczegoly);
		
		upperTable = new JTable();
		upperTableScrollPane = new JScrollPane(upperTable);
		upperTableScrollPane.setBounds(48, 107, 589, 171);
		upperTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		upperTable.setAutoResizeMode(JTable.WIDTH);
		add(upperTableScrollPane);
		
		lowerTable = new JTable();		
		lowerTableScrollPane = new JScrollPane(lowerTable);
		lowerTableScrollPane.setBounds(48, 324, 363, 171);
		lowerTable.setAutoResizeMode(JTable.WIDTH);
		add(lowerTableScrollPane);
		
		
		lblLowerTable = new JLabel("New label");
		lblLowerTable.setBounds(48, 301, 70, 15);
		add(lblLowerTable);
		
		lblUpperTable = new JLabel("New label");
		lblUpperTable.setBounds(48, 86, 70, 15);
		add(lblUpperTable);
		
		txtParametr = new JTextField();
		txtParametr.setBounds(48, 10, 114, 19);
		add(txtParametr);
		txtParametr.setColumns(10);
		
		btnWyszukaj = new JButton("Wyszukaj");
		btnWyszukaj.setBounds(168, 10, 102, 25);
		add(btnWyszukaj);
		
		upperTableHeader = upperTable.getTableHeader();
		upperTableHeader.setReorderingAllowed(false);
		upperTableHeader.addMouseListener(new MouseAdapter() {
		      @Override
		      public void mouseClicked(MouseEvent mouseEvent) {
		        int index = lowerTable.convertColumnIndexToModel(lowerTable.columnAtPoint(mouseEvent.getPoint()));
		        if (index >= 0) {
		        	
		        	String sortedBy = listaPokoiTypQuery.getSort();
		        	String newSort = null;
		        		        
			        switch(index) {	        
			   
			        case 0: newSort = ListaPokoiTypQuery.NR_POKOJU; break;
			        case 1: newSort = ListaPokoiTypQuery.STAN_POKOJU; break;
			        case 2: newSort = ListaPokoiTypQuery.TYP_POKOJU; break;
			        case 3: newSort = ListaPokoiTypQuery.DATA_ROZP; break;
			        case 4: newSort = ListaPokoiTypQuery.DATA_ZAK; break;
			        }		       
			        
			        if(listaPokoiTypQuery.isSortedBy(newSort))
			        	listaPokoiTypQuery.setSort(newSort, !listaPokoiTypQuery.getSortOrder());
			        else
			        	listaPokoiTypQuery.setSort(newSort);
			        
			        if(szczegoly) showResultLower(listaPokoiTypQuery.toStringSzczegol());
			        else showResultLower(listaPokoiTypQuery.toString());
		        }
		      }
		});

		lowerTableHeader = lowerTable.getTableHeader();
		lowerTableHeader.setReorderingAllowed(false);
		lowerTableHeader.addMouseListener(new MouseAdapter() {
		      @Override
		      public void mouseClicked(MouseEvent mouseEvent) {
		        int index = lowerTable.convertColumnIndexToModel(lowerTable.columnAtPoint(mouseEvent.getPoint()));
		        if (index >= 0) {
		        	
		        	String sortedBy = listaPokoiTypQuery.getSort();
		        	String newSort = null;
		        		        
			        switch(index) {	        
			   
			        case 0: newSort = ListaPokoiTypQuery.NR_POKOJU; break;
			        case 1: newSort = ListaPokoiTypQuery.STAN_POKOJU; break;
			        case 2: newSort = ListaPokoiTypQuery.TYP_POKOJU; break;
			        case 3: newSort = ListaPokoiTypQuery.DATA_ROZP; break;
			        case 4: newSort = ListaPokoiTypQuery.DATA_ZAK; break;
			        }		       
			        
			        if(listaPokoiTypQuery.isSortedBy(newSort))
			        	listaPokoiTypQuery.setSort(newSort, !listaPokoiTypQuery.getSortOrder());
			        else
			        	listaPokoiTypQuery.setSort(newSort);
			        
			        if(szczegoly) showResultLower(listaPokoiTypQuery.toStringSzczegol());
			        else showResultLower(listaPokoiTypQuery.toString());
		        }
		      }
		});
		
		db = new dbDAO();
		listaPokoiTypQuery = new ListaPokoiTypQuery();
		
		
		
		showResultLower(new ListaPokoiTypQuery().toString());
		
		btnSzczegoly.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				szczegoly = !szczegoly;
				listaPokoiTypQuery.setSort(ListaPokoiTypQuery.NR_POKOJU);
				if(!szczegoly) {
					lowerTableScrollPane.setBounds(48, 324, 363, 171);
					showResultLower(listaPokoiTypQuery.toString());
				} else {
					lowerTableScrollPane.setBounds(48, 324, 589, 171);
					showResultLower(listaPokoiTypQuery.toStringSzczegol());
				}	
				
			}
		});
	}
	
	public void setUpperData(Object[][] data) {
			
			upperTable.setModel(new DefaultTableModel(data, upperColumnNames));
		}

	public void setLowerData(Object[][] data) {
		
		lowerTable.setModel(new DefaultTableModel(data, lowerColumnNames));
	}
	
	private void showResult(String query, JTable table) {
		
		dbDAO db = new dbDAO();
		
		db.establishConnection();
		
		ResultSet rs = db.executeQuery(query);
		
		try {
			table.setModel(new ResultSetTableModel(rs));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		db.closeConnection();
	}
	
	public void showResultUpper(String query) {
		
		showResult(query, upperTable);
	}
	
	public void showResultLower(String query) {
		
		showResult(query, lowerTable);
	}
}
