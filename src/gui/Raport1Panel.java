package gui;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
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
import com.michaelbaranov.microba.common.CommitEvent;
import com.michaelbaranov.microba.common.CommitListener;

import dao.dbDAO;

public class Raport1Panel extends JPanel {
	
	private WidokPanel parent;
	
	private ListaPokoiTypQuery listaPokoiTypQuery  = new ListaPokoiTypQuery();	
	private NumPokojeWolneQuery numPokojeWolneQuery = new NumPokojeWolneQuery();
	private NumPokojeRezerwQuery numPokojeRezerwQuery =  new NumPokojeRezerwQuery();
	private NumPokojeZajeteQuery numPokojeZajeteQuery =  new NumPokojeZajeteQuery();
	
	protected JButton btnZapisz = new JButton("Zapisz");
	protected JButton btnSzczegoly = new JButton("Szczegóły");
	protected JButton btnWyszukaj = new JButton("Wyszukaj");
	protected JButton btnDrukuj = new JButton("Drukuj");
	
	protected JLabel lblDo = new JLabel("Do:");
	protected JLabel lblOd = new JLabel("Od:");
	private DatePicker datePickerOd = new DatePicker();
	private DatePicker datePickerDo = new DatePicker();
	
	protected JTable upperTable = new JTable();	
	protected JTable lowerTable = new JTable();	
	protected JTableHeader lowerTableHeader;
	protected JTableHeader upperTableHeader;
	protected JScrollPane lowerTableScrollPane;
	protected JScrollPane upperTableScrollPane;
	
	protected String upperColumnNames[];
	protected String lowerColumnNames[];
	
	dbDAO db = new dbDAO();
	
	
	
	/**
	 * Create the panel.
	 */
	public Raport1Panel(WidokPanel _parent) {
		this.parent = _parent;
		setLayout(null);
		
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
		lowerTableScrollPane.setBounds(48, 324, 363, 171);
		lowerTable.setAutoResizeMode(JTable.WIDTH);
		add(lowerTableScrollPane);
		
		lblDo.setBounds(240, 32, 31, 15);
		add(lblDo);
		
		lblOd.setBounds(48, 32, 31, 15);
		add(lblOd);
		
		datePickerOd.setBounds(74, 24, 148, 31);
		add(datePickerOd);
		
		
		datePickerDo.setBounds(263, 24, 148, 31);		
		add(datePickerDo);	
		
		btnWyszukaj.setBounds(435, 27, 102, 25);
		add(btnWyszukaj);		
		
		upperTableHeader = upperTable.getTableHeader();
		upperTableHeader.setReorderingAllowed(false);

		lowerTableHeader = lowerTable.getTableHeader();
		lowerTableHeader.setReorderingAllowed(false);
		
		
		addEventHandling();		
		
		showResultUpper();
		refreshLowerTable(listaPokoiTypQuery.NR_POKOJU, false);		
	}
	
	public void showResultUpper() {
		
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
	
	public void refreshUpperTable(Date dataOd, Date dataDo) {
		
		numPokojeWolneQuery.setDataOd(dataOd);
		numPokojeWolneQuery.setDataDo(dataDo);
		numPokojeRezerwQuery.setDataOd(dataOd);
		numPokojeRezerwQuery.setDataDo(dataDo);
		numPokojeZajeteQuery.setDataOd(dataOd);
		numPokojeZajeteQuery.setDataDo(dataDo);
		
		showResultUpper();
	}
	
	public void refreshLowerTable(String sortBy) {
		
		refreshLowerTable(null, null, sortBy, listaPokoiTypQuery.getSzczegol());
	}
	
	
	public void refreshLowerTable(String sortBy, boolean szczegol) {
		
		refreshLowerTable(null, null, sortBy, szczegol);
	}
	
	public void refreshLowerTable(Date dataOd, Date dataDo, boolean szczegol) {
		
		refreshLowerTable(dataOd, dataDo, listaPokoiTypQuery.getSort(), szczegol);
	}
	
	public void refreshLowerTable(Date dataOd, Date dataDo, String sortBy, boolean szczegol) {
		
		if(dataOd != null) listaPokoiTypQuery.setOd(dataOd);
		if(dataDo != null) listaPokoiTypQuery.setDo(dataDo);
		
		boolean sortOrder = listaPokoiTypQuery.getSortOrder();;
		if(listaPokoiTypQuery.getSort().equals(sortBy))
			sortOrder = !sortOrder;
		
		listaPokoiTypQuery.setSort(sortBy, sortOrder);
		listaPokoiTypQuery.setSzczegol(szczegol);
		
		db.establishConnection();
		
		ResultSet rs = db.executeQuery(listaPokoiTypQuery.toString());
		
		try {
			lowerTable.setModel(new ResultSetTableModel(rs));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		db.closeConnection();
	}
	
	private void addEventHandling() {
		
		datePickerOd.addActionListener(new ActionListener() {					
					public void actionPerformed(ActionEvent evt) {						
						if(datePickerOd.getDate().getTime() > datePickerDo.getDate().getTime())
							try {
								datePickerOd.setDate(datePickerDo.getDate());
							} catch (PropertyVetoException e) {
								e.printStackTrace();
							}
					}
				});

		datePickerDo.addActionListener(new ActionListener() {					
			public void actionPerformed(ActionEvent evt) {						
				if(datePickerOd.getDate().getTime() > datePickerDo.getDate().getTime())
					try {
						datePickerDo.setDate(datePickerOd.getDate());
					} catch (PropertyVetoException e) {
						e.printStackTrace();
					}
			}
		});

		btnWyszukaj.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent evt) {
				
				refreshUpperTable(datePickerOd.getDate(), datePickerDo.getDate());
				refreshLowerTable(datePickerOd.getDate(), datePickerDo.getDate(), listaPokoiTypQuery.getSzczegol());
			}
		});

		lowerTableHeader.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent mouseEvent) {
		      int index = lowerTable.convertColumnIndexToModel(lowerTable.columnAtPoint(mouseEvent.getPoint()));
		      if (index >= 0) {
		      	
	      		String newSort = null;	      		        
		        switch(index) {	      		   
		        case 0: newSort = ListaPokoiTypQuery.NR_POKOJU; break;
		        case 1: newSort = ListaPokoiTypQuery.STAN_POKOJU; break;
		        case 2: newSort = ListaPokoiTypQuery.TYP_POKOJU; break;
		        case 3: newSort = ListaPokoiTypQuery.DATA_ROZP; break;
		        case 4: newSort = ListaPokoiTypQuery.DATA_ZAK; break;
		        }		       		        
		        refreshLowerTable(newSort);
		      }
		    }
		});		

		btnSzczegoly.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent arg0) {			
				
				boolean szczegoly = !listaPokoiTypQuery.getSzczegol();
				if(!szczegoly) lowerTableScrollPane.setBounds(48, 324, 363, 171);
				else lowerTableScrollPane.setBounds(48, 324, 589, 171);
				
				refreshLowerTable(ListaPokoiTypQuery.NR_POKOJU, szczegoly);
				
			}
		});		
	}
}
