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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import raporty.*;

import com.michaelbaranov.microba.calendar.DatePicker;
import com.michaelbaranov.microba.common.CommitEvent;
import com.michaelbaranov.microba.common.CommitListener;

import dao.dbDAO;

public class Raport2Panel extends JPanel {
	
	private WidokPanel parent;
	
	private UslugiQuery uslugiQuery = new UslugiQuery();
	
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
	
	private JComboBox<String> cbFiltr = new JComboBox<String>();
	
	protected String upperColumnNames[];
	protected String lowerColumnNames[];
	
	dbDAO db = new dbDAO();
	
	
	
	/**
	 * Create the panel.
	 */
	public Raport2Panel(WidokPanel _parent) {
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
		
		btnWyszukaj.setBounds(573, 27, 102, 25);
		add(btnWyszukaj);		
		
		upperTableHeader = upperTable.getTableHeader();
		upperTableHeader.setReorderingAllowed(false);

		lowerTableHeader = lowerTable.getTableHeader();
		lowerTableHeader.setReorderingAllowed(false);
		
		cbFiltr.setModel(new DefaultComboBoxModel<String>(new String[] {"nazwa usługi", "nazwisko klienta"}));
		cbFiltr.setBounds(423, 27, 138, 25);
		add(cbFiltr);		
		
		addEventHandling();		
		
		refreshUpperTable(null, null, null, null);
//		refreshLowerTable(listaPokoiTypQuery.NR_POKOJU, false);		
	}
	
	public void refreshUpperTable(Date dataOd, Date dataDo) {
		
		refreshUpperTable(dataOd, dataDo, null, null);
	}
	
	public void refreshUpperTable(String sortBy, String typ) {
		
		refreshUpperTable(null, null, sortBy, typ);
	}
	
	public void refreshUpperTable(Date dataOd, Date dataDo, String sortBy, String typ) {
		
		if(dataOd != null) uslugiQuery.setOd(dataOd);
		if(dataDo != null) uslugiQuery.setDo(dataDo);
		if(sortBy != null) {			
		
			boolean sortOrder = uslugiQuery.getSortOrder();
			if(uslugiQuery.getSort().equals(sortBy))
				sortOrder = !sortOrder;			
			uslugiQuery.setSort(sortBy, sortOrder);		
			}
		
		if(typ != null) uslugiQuery.setTyp(typ);
		
		db.establishConnection();
		
		ResultSet rs = db.executeQuery(uslugiQuery.toString());
		
		try {
			upperTable.setModel(new ResultSetTableModel(rs));
		} catch (SQLException e) {
			System.err.println(e.getMessage());
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
//				refreshLowerTable(datePickerOd.getDate(), datePickerDo.getDate(), listaPokoiTypQuery.getSzczegol());
			}
		});
		
		upperTableHeader.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent mouseEvent) {
		      int index = upperTable.convertColumnIndexToModel(upperTable.columnAtPoint(mouseEvent.getPoint()));
		      if (index >= 0) {
		      	
	      		String newSort = null;	      		        
		        switch(index) {	      		   
		        case 3: newSort = UslugiQuery.ILOSC_USLUGI; break;
		        case 0: newSort = UslugiQuery.NAZWA_USLUGI; break;
		        case 2: newSort = UslugiQuery.NAZWISKO; break;
		        case 1: newSort = UslugiQuery.NR_POBYTU; break;
		        }		       		        
		        refreshUpperTable(newSort, uslugiQuery.getTyp());
		      }
		    }
		});		

//		lowerTableHeader.addMouseListener(new MouseAdapter() {
//		    @Override
//		    public void mouseClicked(MouseEvent mouseEvent) {
//		      int index = lowerTable.convertColumnIndexToModel(lowerTable.columnAtPoint(mouseEvent.getPoint()));
//		      if (index >= 0) {
//		      	
//	      		String newSort = null;	      		        
//		        switch(index) {	      		   
//		        case 0: newSort = UslugiQuery.ILOSC_USLUGI; break;
//		        case 1: newSort = UslugiQuery.NAZWA_USLUGI; break;
//		        case 2: newSort = UslugiQuery.NAZWISKO; break;
//		        case 3: newSort = UslugiQuery.NR_POBYTU; break;
//		        }		       		        
//		        refreshLowerTable(newSort);
//		      }
//		    }
//		});		

//		btnSzczegoly.addActionListener(new ActionListener() {		
//			@Override
//			public void actionPerformed(ActionEvent arg0) {			
//				
////				boolean szczegoly = !listaPokoiTypQuery.getSzczegol();
//				if(!szczegoly) lowerTableScrollPane.setBounds(48, 324, 0, 171);
//				else lowerTableScrollPane.setBounds(48, 324, 450, 171);
//				
//				refreshLowerTable(ListaPokoiTypQuery.NR_POKOJU, szczegoly);
//				
//			}
//		});		
		
		cbFiltr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				switch(cbFiltr.getSelectedIndex()) {
					case 0: refreshUpperTable(null, uslugiQuery.NAZWA); break;
					case 1: refreshUpperTable(null, uslugiQuery.KLIENT); break;
				}
			}
		});
	}
}
