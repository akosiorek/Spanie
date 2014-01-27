package gui;

import gui.PobytManager.Okno;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JButton;

import dao.dbDAO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RezerwacjeKlientaFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnWybierz;
	private JButton btnWstecz;
	
	private PobytManager parent;
	private JButton btnNowyPobyt;
	
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
					RezerwacjeKlientaFrame frame = new RezerwacjeKlientaFrame();
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
	public RezerwacjeKlientaFrame() {
		setTitle("Rezerwacje klienta");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 650, 275);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setAutoResizeMode(JTable.WIDTH);
		JScrollPane tableScrollPane = new JScrollPane(table);
		tableScrollPane.setBounds(56, 33, 546, 152);
		contentPane.add(tableScrollPane);
		
		btnWybierz = new JButton("Wybierz");
		btnWybierz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int i = table.getSelectedRow();
				if(i == -1) {
					
					JOptionPane.showMessageDialog(new JFrame(), "Nie zaznaczono żadnej rezerwacji.", "Niepoprawna rezerwacja.",
					        JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				parent.setNrRezerwacji(table.getModel().getValueAt(i, 0).toString());
				parent.show(Okno.REZERWACJA);
			}
		});
		btnWybierz.setBounds(390, 199, 100, 27);
		contentPane.add(btnWybierz);
		
		btnWstecz = new JButton("Wstecz");
		btnWstecz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
			}
		});
		btnWstecz.setBounds(502, 199, 100, 27);
		contentPane.add(btnWstecz);
		
		btnNowyPobyt = new JButton("Nowy pobyt");
		btnNowyPobyt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				parent.show(Okno.POKOJE);
			}
		});
		btnNowyPobyt.setBounds(278, 199, 100, 27);
		contentPane.add(btnNowyPobyt);
		
		
		
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
		String query = "select * from REZERWACJA where nr_dokumentu_klienta = '" 
				+ parent.getNumerDokumentu() + "';";
		
		db.establishConnection();
		ResultSet rs = db.executeQuery(query);
		try {
			table.setModel(new ResultSetTableModel(rs));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.closeConnection();
		
		if(table.getRowCount() != 0) {
			table.getColumnModel().getColumn(0).setMinWidth(80);
			table.getColumnModel().getColumn(0).setMaxWidth(80);
			table.getColumnModel().getColumn(2).setMinWidth(120);
			table.getColumnModel().getColumn(2).setMaxWidth(120);
			table.getColumnModel().getColumn(3).setMinWidth(120);
			table.getColumnModel().getColumn(3).setMaxWidth(120);
			table.getColumnModel().getColumn(4).setMinWidth(50);
			table.getColumnModel().getColumn(4).setMaxWidth(50);
		}
	}
}
