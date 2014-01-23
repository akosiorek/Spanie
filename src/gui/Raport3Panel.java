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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import raporty.ListaPokoiTypQuery;
import raporty.NumPokojeRezerwQuery;
import raporty.NumPokojeWolneQuery;
import raporty.NumPokojeZajeteQuery;

import com.michaelbaranov.microba.calendar.DatePicker;

import dao.dbDAO;

public class Raport3Panel extends JPanel {
	
	protected JButton btnZapisz;
	protected JButton btnDrukuj;
	protected JButton btnSzczegoly;
	protected JButton btnWyszukaj;
	
	private DatePicker datePickerOd;
	private DatePicker datePickerDo;
	
	protected JTable lowerTable;
	protected JTable upperTable;	
	
	protected JTableHeader lowerTableHeader;
	protected JTableHeader upperTableHeader;
	protected JLabel lblDo;
	protected JLabel lblOd;
	
	protected String upperColumnNames[];
	protected String lowerColumnNames[];
	
	dbDAO db = new dbDAO();;
	
	JScrollPane lowerTableScrollPane;
	JScrollPane upperTableScrollPane;
	
	private WidokPanel parent;
	private boolean szczegoly = false;
	private ListaPokoiTypQuery listaPokoiTypQuery  = new ListaPokoiTypQuery();;
	
	
	private NumPokojeWolneQuery numPokojeWolneQuery = new NumPokojeWolneQuery();
	private NumPokojeRezerwQuery numPokojeRezerwQuery =  new NumPokojeRezerwQuery();
	private NumPokojeZajeteQuery numPokojeZajeteQuery =  new NumPokojeZajeteQuery();
	
	/**
	 * Create the panel.
	 */
	public Raport3Panel(WidokPanel _parent) {
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
		
		
		lblDo = new JLabel("Do:");
		lblDo.setBounds(240, 32, 31, 15);
		add(lblDo);
		
		lblOd = new JLabel("Od:");
		lblOd.setBounds(48, 32, 31, 15);
		add(lblOd);
		
		datePickerOd = new DatePicker();
		datePickerOd.setBounds(74, 24, 148, 31);
		add(datePickerOd);
		
		datePickerDo = new DatePicker();
		datePickerDo.setBounds(263, 24, 148, 31);
		add(datePickerDo);
		
		
		btnWyszukaj = new JButton("Wyszukaj");
		btnWyszukaj.setBounds(435, 27, 102, 25);
		add(btnWyszukaj);
		btnWyszukaj.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent evt) {
				
				Date dataOd = datePickerOd.getDate();
				Date dataDo = datePickerDo.getDate();
				showResultUpper(dataOd, dataDo);
			}
		});
		
		upperTableHeader = upperTable.getTableHeader();
		upperTableHeader.setReorderingAllowed(false);

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
		
		showResultUpper();
		showResultLower(listaPokoiTypQuery.toString());
		
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
	
	public void showResultUpper() {
	dbDAO db = new dbDAO();
		
		db.establishConnection();
		
		ResultSet rs1 = db.executeQuery(numPokojeWolneQuery.toString());
		ResultSet rs2 = db.executeQuery(numPokojeRezerwQuery.toString());
		ResultSet rs3 = db.executeQuery(numPokojeZajeteQuery.toString());
		
		try {
			upperTable.setModel(new ResultSetNumPokoje(rs1, rs2, rs3));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		db.closeConnection();
	}
	
	public void showResultUpper(Date dataOd, Date dataDo) {
		
		numPokojeWolneQuery.setDataOd(dataOd);
		numPokojeWolneQuery.setDataDo(dataDo);
		numPokojeRezerwQuery.setDataOd(dataOd);
		numPokojeRezerwQuery.setDataDo(dataDo);
		numPokojeZajeteQuery.setDataOd(dataOd);
		numPokojeZajeteQuery.setDataDo(dataDo);
		
		showResultUpper();
	}
	
	public void showResultLower(String query) {
		
		showResult(query, lowerTable);
	}
}
