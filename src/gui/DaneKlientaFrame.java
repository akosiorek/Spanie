package gui;

import gui.PobytManager.Okno;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import raporty.RodzajeDokumentu;
import dao.dbDAO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class DaneKlientaFrame extends JFrame {

	private JPanel contentPane;
	private JLabel lblImi;
	private JLabel lblNazwisko;
	private JLabel lblRodzajDokumentu;
	private JLabel lblNumerDokumentu;
	private JComboBox<String> cmbRodzajDokumentu;
	private JTextField txtImie;
	private JTextField txtNazwisko;
	private JTextField txtNumerDokumentu;
	private JButton btnDodajKlienta;
	private JButton btnWyszukajKlienta;
	private JButton btnZatwierdz;
	private JButton btnWstecz;
	
	private PobytManager parent;
	
	private dbDAO db = new dbDAO();
	
	private final String[] daneKlienta = new String[] {"imie", "nazwisko", "nr_dokumentu_klienta"};
	private final String daneKlientaQuery = "select * from Klient";
	private final String[] defaultLabels = new String[] {"Imię", "Nazwisko", "Numer dokumentu"};
	
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
					DaneKlientaFrame frame = new DaneKlientaFrame();
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
	public DaneKlientaFrame() {
		setTitle("Dane klienta");
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 620, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblImi = new JLabel("Imię:");
		lblImi.setBounds(253, 65, 60, 15);
		contentPane.add(lblImi);
		
		lblNazwisko = new JLabel("Nazwisko:");
		lblNazwisko.setBounds(253, 92, 60, 15);
		contentPane.add(lblNazwisko);
		
		lblRodzajDokumentu = new JLabel("Rodzaj dokumentu:");
		lblRodzajDokumentu.setBounds(253, 119, 124, 15);
		contentPane.add(lblRodzajDokumentu);
		
		lblNumerDokumentu = new JLabel("Numer dokumentu:");
		lblNumerDokumentu.setBounds(253, 148, 139, 15);
		contentPane.add(lblNumerDokumentu);
		
		cmbRodzajDokumentu = new JComboBox<String>();
		cmbRodzajDokumentu.setEditable(true);
		cmbRodzajDokumentu.setModel(new DefaultComboBoxModel<String>(new RodzajeDokumentu().getEntriesArray()));
		cmbRodzajDokumentu.setBounds(389, 114, 122, 25);
		contentPane.add(cmbRodzajDokumentu);
		
		txtImie = new JTextField();
		txtImie.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtImie.selectAll();
			}
		});
		txtImie.setText("Imię");
		txtImie.setBounds(389, 59, 122, 27);
		contentPane.add(txtImie);
		txtImie.setColumns(10);
		
		txtNazwisko = new JTextField();
		txtNazwisko.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtNazwisko.selectAll();
			}
		});
		txtNazwisko.setText("Nazwisko");
		txtNazwisko.setBounds(389, 86, 122, 27);
		contentPane.add(txtNazwisko);
		txtNazwisko.setColumns(10);
		
		txtNumerDokumentu = new JTextField();
		txtNumerDokumentu.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtNumerDokumentu.selectAll();	
			}
		});
		txtNumerDokumentu.setText("Numer dokumentu");
		txtNumerDokumentu.setBounds(389, 142, 122, 27);
		contentPane.add(txtNumerDokumentu);
		txtNumerDokumentu.setColumns(10);
		
		btnDodajKlienta = new JButton("Dodaj klienta");
		btnDodajKlienta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				
				String query = "INSERT INTO Klient VALUES ('"
						+ txtNumerDokumentu.getText() + "', '"
						+ cmbRodzajDokumentu.getSelectedItem().toString() + "', '"
						+ txtNazwisko.getText() + "', '"
						+ txtImie.getText() + "');";

				db.establishConnection();
				
				int result = db.executeUpdate(query);
				
				db.closeConnection();
				
				if(result != 1) {
					JOptionPane.showMessageDialog(new JFrame(), "Nie udało się dodać klienta.", "Niepowodzenie",
					        JOptionPane.ERROR_MESSAGE);						
				}
				
				
			}
		});
		btnDodajKlienta.setBounds(55, 59, 156, 27);
		contentPane.add(btnDodajKlienta);
		
		btnWyszukajKlienta = new JButton("Wyszukaj klienta");
		btnWyszukajKlienta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String[] dane = new String[] {txtImie.getText(), txtNazwisko.getText(), txtNumerDokumentu.getText()};
				
				if(checkIfAllNull(dane)) {					
					JOptionPane.showMessageDialog(new JFrame(), "Proszę uzupełnić dane klienta.", "Niepoprawne dane",
					        JOptionPane.ERROR_MESSAGE);	
					return;
				}				
				
				StringBuilder queryBuilder = new StringBuilder();
				queryBuilder.append(daneKlientaQuery);
				
				int added = 0;
				for(int i = 0; i < dane.length; ++i) {
					
					if(dane[i] != null && !dane[i].equals("") && !dane[i].equals(defaultLabels[i])) {
						if(added == 0)
							queryBuilder.append(" where ");
						if(added > 0)
							queryBuilder.append(" and ");
						queryBuilder.append(daneKlienta[i] + " = '" + dane[i] + "'");
						added++;
					}
				}
				queryBuilder.append(";");				
				
				db.establishConnection();				
				ResultSet rs = db.executeQuery(queryBuilder.toString());				
				
				try {
					if(!rs.next()) {
						JOptionPane.showMessageDialog(new JFrame(), "Nie zaneziono klienta zgodne z podanymi danymi.", "Brak klienta",
						        JOptionPane.ERROR_MESSAGE);	
						return;
					}
					
					if(rs.next()) {
						JOptionPane.showMessageDialog(new JFrame(), "Podane dane nie zapewniają jednoznacznej identyfikacji klienta.", "Błąd wyszukiwania",
						        JOptionPane.ERROR_MESSAGE);	
						return;
						
					}
					
					rs.previous();
					
					txtNumerDokumentu.setText(rs.getString(1));
					txtImie.setText(rs.getString(4));
					txtNazwisko.setText(rs.getString(3));
					cmbRodzajDokumentu.setSelectedItem(rs.getString(2));
					
					db.closeConnection();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnWyszukajKlienta.setBounds(55, 86, 156, 27);
		contentPane.add(btnWyszukajKlienta);
		
		btnZatwierdz = new JButton("Zatwierdź");
		btnZatwierdz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!sprawdzDane()) return;					
				sprawdzDluznik();			
				
				parent.setImie(txtImie.getText());
				parent.setNazwisko(txtNazwisko.getText());
				parent.setNumerDokumentu(txtNumerDokumentu.getText());
				parent.setRodzajDokumentu(cmbRodzajDokumentu.getSelectedItem().toString());
				
				switch(parent.getState()) {
				
				case NOWA_REZERWACJA: 										
					parent.show(Okno.POKOJE);
					break;
					
				case ISTNIEJACA_REZERWACJA: 
					parent.show(Okno.REZERWACJA_KLIENTA);
					break;
					
				case NOWY_POBYT: 					
					if(checkIfRezerwacja())
						parent.show(Okno.REZERWACJA_KLIENTA);
					else
						parent.show(Okno.POKOJE);					
					break;
				
				case ISTNIEJACY_POBYT: 
					if(!parent.znajdzPobyt()) {
						JOptionPane.showMessageDialog(new JFrame(), "Nie znaleziono pobytu wskazanego klienta.", "Nie ma takiego pobytu",
						        JOptionPane.ERROR_MESSAGE);							
						return;
					}
					parent.show(Okno.POBYT); 
					break;
				
				}
			}
		});
		btnZatwierdz.setBounds(55, 142, 156, 27);
		contentPane.add(btnZatwierdz);
		
		btnWstecz = new JButton("Wstecz");
		btnWstecz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnWstecz.setBounds(55, 113, 156, 27);
		contentPane.add(btnWstecz);
	}
	
	private void sprawdzDluznik() {
		
		db.establishConnection();
		
		String id = txtNumerDokumentu.getText();
		String query = "select if( exists (select * from DLUZNICY where nr_dokumentu_klienta = \'" + id + "\'), \"tak\", \"nie\") as dluznik;";
		ResultSet rs = db.executeQuery(query);
		
		try {
			rs.next();
			if(rs.getString(1).equals("tak")) {
				
				JOptionPane.showMessageDialog(new JFrame(), "Klienta jest dłużnikiem!", "Dłużnik",
				        JOptionPane.WARNING_MESSAGE);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		db.closeConnection();		
	}
	
	private boolean sprawdzDane() {
		
		boolean dane = true;
		
		if(!dane)
			JOptionPane.showMessageDialog(new JFrame(), "Proszę uzupełnić dane klienta.", "Niepoprawne dane",
			        JOptionPane.ERROR_MESSAGE);	
		
		return dane;
	}
	
	private boolean checkIfAllNull(String[] strings) {
		
		boolean allNull = true;
		for(final String s : strings) {
			if(s != null && !s.equals("")) {
				allNull = false;
				break;
			}
		}			
		return allNull;		
	}
	
	private boolean checkIfRezerwacja() {
		
		boolean rezerwacja = false;
		
		String query = "select * from REZERWACJA where nr_dokumentu_klienta = '" 
				+ txtNumerDokumentu.getText() + "';";
		
		db.establishConnection();
		ResultSet rs = db.executeQuery(query);
		try {
			if(rs.next()) 
				rezerwacja = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.closeConnection();
		
		return rezerwacja;
	}
}
