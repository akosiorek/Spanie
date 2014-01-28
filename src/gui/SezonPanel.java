package gui;


import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import raporty.InsertQuery;
import raporty.RodzajeSezonow;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;

import com.michaelbaranov.microba.calendar.DatePicker;

import dao.dbDAO;

public class SezonPanel extends JPanel {
	private JComboBox<String> nazwaSezonuCombo;
	private DefaultComboBoxModel<String> nazwaSezonuModel;
	private DatePicker datePickerOd;
	private DatePicker datePickerDo;
	private JLabel lblDataRozpoczecia;
	private JLabel lblDataZakonczenia;
	private JButton btnWyszukaj;
	private JButton btnDodaj;
	private JButton btnAnuluj;
	private JLabel lblNazwaSezonu;
	
	private dbDAO db = new dbDAO();
	
	private WidokPanel parent;
	/**
	 * Create the panel.
	 */
	public SezonPanel(WidokPanel _parent) {
		parent = _parent;
		setLayout(null);
		
		
		nazwaSezonuCombo = new JComboBox<String>();
		nazwaSezonuCombo.setBounds(240, 37, 185, 25);
		nazwaSezonuCombo.setEditable(true);
		nazwaSezonuModel = new DefaultComboBoxModel<String>(new RodzajeSezonow().getEntriesArray());
		nazwaSezonuCombo.setModel(nazwaSezonuModel);
		add(nazwaSezonuCombo);
		
		datePickerOd = new DatePicker();
		datePickerOd.setBounds(240, 70, 185, 27);
		add(datePickerOd);
		
		datePickerDo = new DatePicker();
		datePickerDo.setBounds(240, 103, 185, 27);
		add(datePickerDo);
		
		lblDataRozpoczecia = new JLabel("Data rozpoczecia:");
		lblDataRozpoczecia.setBounds(115, 80, 110, 15);
		add(lblDataRozpoczecia);
		
		lblDataZakonczenia = new JLabel("Data zakonczenia:");
		lblDataZakonczenia.setBounds(115, 115, 113, 15);
		add(lblDataZakonczenia);
		btnWyszukaj = new JButton("Wyszukaj");
		btnWyszukaj.setBounds(165, 205, 94, 27);
		
		add(btnWyszukaj);
		
		btnDodaj = new JButton("Dodaj");
		btnDodaj.setBounds(283, 205, 94, 27);
		add(btnDodaj);
		
		btnAnuluj = new JButton("Anuluj");
		btnAnuluj.setBounds(419, 205, 94, 27);
		add(btnAnuluj);
		
		lblNazwaSezonu = new JLabel("Nazwa sezonu:");
		lblNazwaSezonu.setBounds(115, 47, 91, 15);
		add(lblNazwaSezonu);
		
		addEventHandling();

	}
	
	private void addEventHandling() {
		
		btnWyszukaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(nazwaSezonuCombo.getSelectedIndex() == -1) {
					
					JOptionPane.showMessageDialog(new JFrame(), "Sezon o podanej nazwie nie istnieje", "Błąd wyszukiwania.",
					        JOptionPane.ERROR_MESSAGE);	
				} else {
					
					parent.wyswietl(WidokPanel.SEZON_CENNIK);
				}
			}
		});
		
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
							
				
				if(nazwaSezonuCombo.getSelectedIndex() == -1) {
					String sezon = nazwaSezonuCombo.getSelectedItem().toString();
					if(!sezon.equals("")) {
						
						String dataOd = new Date(datePickerOd.getDate().getTime()).toString();
						String dataDo = new Date(datePickerDo.getDate().getTime()).toString();
						
						int result = db.executeUpdate(
							new InsertQuery("SEZON", new String[] {sezon, dataOd, dataDo}).toString());
						
						if(result == 1) {
							nazwaSezonuModel.addElement(sezon);
							parent.wyswietl(WidokPanel.SEZON_CENNIK);
						} else {
							
							JOptionPane.showMessageDialog(new JFrame(), "Sezon o tej nazwie już istnieje!", "Nie można dodać sezonu",
							        JOptionPane.ERROR_MESSAGE);	
						}
					}
				}
				
			}
		});
		
		btnAnuluj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				parent.wyswietl(WidokPanel.EMPTY);
				
				
			}
		});
		
		nazwaSezonuCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(nazwaSezonuCombo.getSelectedIndex() != -1) {
					
					parent.setSezon(nazwaSezonuCombo.getSelectedItem().toString());
					
					java.util.Date dateOd = null;
					java.util.Date dateDo = null;
						
					db.establishConnection();
					
					ResultSet rs = db.executeQuery("select data_rozpoczecia, data_zakonczenia from SEZON where nr_sezonu = '" + nazwaSezonuCombo.getSelectedItem().toString() + "';");
					try {
						rs.next();
						
						dateOd = Utils.parseDate(rs.getString(1));
						dateDo = Utils.parseDate(rs.getString(2));
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					try {
						datePickerOd.setDate(dateOd);
						datePickerDo.setDate(dateDo);
					} catch (PropertyVetoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
				} else {
					parent.setSezon("");
				}
			}
		});
		
	}
}
