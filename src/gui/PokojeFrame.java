package gui;

import gui.PobytManager.Okno;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JButton;

import raporty.RodzajePokojow;

import com.michaelbaranov.microba.calendar.DatePicker;

import dao.dbDAO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PokojeFrame extends JFrame {

	private JPanel contentPane;
	private DatePicker datePickerOd;
	private DatePicker datePickerDo;
	private JComboBox<String> cmbTypPokoju;
	private JLabel lblTypPokoju;
	private JLabel lblDataRozpoczcia;
	private JLabel lblDataZakoczenia;
	private JTable tableWyniki;
	private JButton btnZatwierdz;
	private JButton btnWstecz;
	
	private PobytManager parent;
	private JButton btnWyszukaj;
	
	private dbDAO db = new dbDAO();

	public void setParent(PobytManager parent) {
		this.parent = parent;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PokojeFrame frame = new PokojeFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PokojeFrame() {
		setTitle("Pokoje");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		datePickerOd = new DatePicker();
		datePickerOd.setBounds(218, 72, 134, 27);
		contentPane.add(datePickerOd);
		
		datePickerDo = new DatePicker();
		datePickerDo.setBounds(218, 111, 134, 27);
		contentPane.add(datePickerDo);
		
		cmbTypPokoju = new JComboBox<String>();
		cmbTypPokoju.setModel(new DefaultComboBoxModel<String>(new RodzajePokojow().getEntriesArray()));
		cmbTypPokoju.setBounds(218, 35, 134, 25);
		contentPane.add(cmbTypPokoju);
		
		lblTypPokoju = new JLabel("Typ Pokoju:");
		lblTypPokoju.setBounds(91, 45, 122, 15);
		contentPane.add(lblTypPokoju);
		
		lblDataRozpoczcia = new JLabel("Data rozpoczęcia:");
		lblDataRozpoczcia.setBounds(91, 78, 122, 15);
		contentPane.add(lblDataRozpoczcia);
		
		lblDataZakoczenia = new JLabel("Data zakończenia:");
		lblDataZakoczenia.setBounds(91, 117, 122, 15);
		contentPane.add(lblDataZakoczenia);
		
		tableWyniki = new JTable();
		tableWyniki.setAutoResizeMode(JTable.WIDTH);
		JScrollPane tableWynikiScrollPane = new JScrollPane(tableWyniki);
		tableWynikiScrollPane.setBounds(64, 238, 305, 104);
		contentPane.add(tableWynikiScrollPane);
		
		btnZatwierdz = new JButton("Zatwierdź");
		btnZatwierdz.addActionListener(new ActionListener() {
			@SuppressWarnings("incomplete-switch")
			public void actionPerformed(ActionEvent e) {
				
				
				if(tableWyniki.getRowCount() == 0) {
					
					JOptionPane.showMessageDialog(new JFrame(), "Nie znaleziono wolnych pokoi danego typu.", "Brak pokoi danego typu",
					        JOptionPane.ERROR_MESSAGE);	
					return;
				}
				
				parent.setDataOd(datePickerOd.getDate());
				parent.setDataDo(datePickerDo.getDate());
				
				int index = tableWyniki.getSelectedRow();
				if(index == -1){
					if(tableWyniki.getRowCount() == 1) {
						
						index = 0;
						
					} else {
					JOptionPane.showMessageDialog(new JFrame(), "Proszę wybrać pokój z listy..", "Bład wyboru pokoju",
					        JOptionPane.ERROR_MESSAGE);	
					return;
					}
				}
				
				parent.setTypPokoju(tableWyniki.getValueAt(index, 1).toString());
				parent.setNrPokoju(tableWyniki.getValueAt(index, 0).toString());
				
				switch(parent.getState()) {
				
				case NOWA_REZERWACJA: 
					parent.dodajRezerwacje();
					parent.show(Okno.REZERWACJA); break;
				case NOWY_POBYT: 
					parent.dodajPobyt();
					parent.show(Okno.POBYT); break;
				}
			}
		});
		btnZatwierdz.setBounds(116, 367, 100, 27);
		contentPane.add(btnZatwierdz);
		
		btnWstecz = new JButton("Wstecz");
		btnWstecz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnWstecz.setBounds(228, 367, 100, 27);
		contentPane.add(btnWstecz);
		
		btnWyszukaj = new JButton("Wyszukaj");
		btnWyszukaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				
				showResults();
			}
		});
		btnWyszukaj.setBounds(218, 150, 100, 27);
		contentPane.add(btnWyszukaj);
		
		showResults();
	}
	
	private void showResults() {
		
		String query = "select  POKOJE.nr_pokoju, rodzaj_pokoju from "
				+ "POKOJE where POKOJE.nr_pokoju not in(select POKOJE.nr_pokoju from POKOJE, REZERWACJA_POKOJE "
				+ "left join REZERWACJA on REZERWACJA_POKOJE.nr_rezerwacji=REZERWACJA.nr_rezerwacji where "
				+ "REZERWACJA_POKOJE.nr_pokoju = POKOJE.nr_pokoju and REZERWACJA.data_rozpoczecia < '"
				+ new java.sql.Date(datePickerOd.getDate().getTime()).toString()
				+ "' and REZERWACJA.data_zakonczenia >= '"
				+ new java.sql.Date(datePickerDo.getDate().getTime()).toString()
				+ "') and POKOJE.nr_pokoju not in(select POBYT_POKOJE.nr_pokoju from POKOJE,POBYT_POKOJE left "
				+ "join POBYT on POBYT_POKOJE.nr_pobytu=POBYT.nr_pobytu where POBYT_POKOJE.nr_pokoju = "
				+ "POKOJE.nr_pokoju and POBYT.data_rozpoczecia < '"
				+ new java.sql.Date(datePickerOd.getDate().getTime()).toString()
				+ "' and POBYT.data_zakonczenia >= '"
				+ new java.sql.Date(datePickerDo.getDate().getTime()).toString()
				+ "') and rodzaj_pokoju = '"
				+ cmbTypPokoju.getSelectedItem().toString()
				+ "';";
		
		db.establishConnection();		
		
		try {
			tableWyniki.setModel(new ResultSetTableModel(db.executeQuery(query)));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		db.closeConnection();
	}
}
