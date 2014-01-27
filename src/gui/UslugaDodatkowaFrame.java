package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import org.joda.time.DateTime;
import org.joda.time.Days;

import com.michaelbaranov.microba.calendar.DatePicker;

import dao.dbDAO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;

public class UslugaDodatkowaFrame extends JFrame {

	private JPanel contentPane;
	private final JTable table = new JTable();
	private final JLabel lblDataRozpoczecia = new JLabel("Data rozpoczęcia:");
	private final JLabel lblDataZakonczenia = new JLabel("Data zakończenia:");
	private final JLabel lblNowaCena = new JLabel("Nowa cena:");
	private final DatePicker datePickerOd = new DatePicker();
	private final DatePicker datePickerDo = new DatePicker();
	private final JTextField txtNowaCena = new JTextField();
	private final JButton btnWybierz = new JButton("Wybierz");
	private final JButton btnAnuluj = new JButton("Anuluj");
	
	private PobytManager parent;
	
	private final dbDAO db = new dbDAO();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UslugaDodatkowaFrame frame = new UslugaDodatkowaFrame();
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
	public UslugaDodatkowaFrame() {
		txtNowaCena.setBounds(248, 320, 152, 27);
		txtNowaCena.setColumns(10);
		datePickerDo.setBounds(248, 293, 152, 27);
		datePickerOd.setBounds(248, 267, 152, 27);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 601, 431);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane tableScrollPane = new JScrollPane(table);
		tableScrollPane.setBounds(45, 58, 502, 157);		
		contentPane.add(tableScrollPane);
		
		lblDataRozpoczecia.setBounds(113, 273, 117, 15);		
		contentPane.add(lblDataRozpoczecia);
		
		lblDataZakonczenia.setBounds(113, 299, 117, 15);		
		contentPane.add(lblDataZakonczenia);
		
		lblNowaCena.setBounds(113, 326, 117, 15);		
		contentPane.add(lblNowaCena);
		
		contentPane.add(datePickerOd);
		
		contentPane.add(datePickerDo);
		
		contentPane.add(txtNowaCena);
		btnWybierz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selected[] = table.getSelectedRows();
				if(selected.length != 0) {
					
					int days = Days.daysBetween(new DateTime(datePickerOd.getDate()), new DateTime(datePickerDo.getDate())).getDays();
					
					
					for(int i : selected) {
						String cena = txtNowaCena.getText();
						if(cena == null || cena.equals("")) {
							cena = table.getValueAt(i, 1).toString();
						}
						
						String nazwa = table.getValueAt(i, 0).toString();
						
						if(days > 0) {
							
							float cenaFloat = Float.parseFloat(cena) * (days + 1);
							cena = Float.toString(cenaFloat);
							nazwa += " x " + Integer.toString(days + 1);
						}
						
						parent.getCenyUslugDodatkowych().add(cena);
						
						parent.getUslugiDodatkowe().add(nazwa);
						parent.getCenyUslugDodatkowych().add(cena);
					}
					parent.notifyDodanieUslugi();
					setVisible(false);
				} else {
					
					JOptionPane.showMessageDialog(new JFrame(), "Proszę wybrać przynajmniej jedną usługę.", "Błąd dodawania usług.",
					        JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnWybierz.setBounds(412, 267, 100, 27);
		
		contentPane.add(btnWybierz);
		btnAnuluj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnAnuluj.setBounds(412, 293, 100, 27);
		
		contentPane.add(btnAnuluj);
		
		addComponentListener(new ComponentAdapter() {
			public void componentHidden(ComponentEvent e) 
			{
			    /* code run when component hidden*/
			}
			public void componentShown(ComponentEvent e) {
			    /* code run when component shown */
				showResults();
			}
			});
	}
	
	private void showResults() {
		
		String query = "SELECT * FROM USLUGA_DODATKOWA;";
		db.establishConnection();
		try {
			table.setModel(new ResultSetTableModel(db.executeQuery(query)));
			table.getColumnModel().getColumn(0).setMaxWidth(200);
			table.getColumnModel().getColumn(0).setMinWidth(100);
			table.getColumnModel().getColumn(1).setMaxWidth(45);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.closeConnection();
		
		table.clearSelection();
		txtNowaCena.setText("");
		try {
			datePickerOd.setDate(new Date());
			datePickerDo.setDate(new Date());
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void setParent(PobytManager parent) {
		this.parent = parent;
	}
}
