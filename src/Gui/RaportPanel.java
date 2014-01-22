package Gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.JScrollBar;
import javax.swing.JLabel;
import javax.swing.JTextField;

import DAO.dbDAO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RaportPanel extends JPanel {
	
	protected JButton btnZapisz;
	protected JButton btnDrukuj;
	protected JButton btnSzczegy;
	protected JButton btnWyszukaj;
	protected JTextField txtParametr;
	
	protected JTable lowerTable;
	protected JTable upperTable;	
	
	protected JTableHeader lowerTableHeader;
	protected JTableHeader upperTableHeader;
	protected JLabel lblLowerTable;
	protected JLabel lblUpperTable;
	
	protected SpringLayout springLayout;
	
	protected String upperColumnNames[];
	protected String lowerColumnNames[];
	
	dbDAO db;
	
	JScrollPane lowerTableScrollPane;
	JScrollPane upperTableScrollPane;
	

	
	

	/**
	 * Create the panel.
	 */
	public RaportPanel() {
		springLayout = new SpringLayout();
		setLayout(springLayout);
		
		btnZapisz = new JButton("Zapisz");
		add(btnZapisz);
		
		btnDrukuj = new JButton("Drukuj");
		springLayout.putConstraint(SpringLayout.NORTH, btnZapisz, 0, SpringLayout.NORTH, btnDrukuj);
		springLayout.putConstraint(SpringLayout.EAST, btnZapisz, -6, SpringLayout.WEST, btnDrukuj);
		add(btnDrukuj);
		
		btnSzczegy = new JButton("Szczegóły");
		springLayout.putConstraint(SpringLayout.NORTH, btnDrukuj, 0, SpringLayout.NORTH, btnSzczegy);
		springLayout.putConstraint(SpringLayout.EAST, btnDrukuj, -6, SpringLayout.WEST, btnSzczegy);
		springLayout.putConstraint(SpringLayout.SOUTH, btnSzczegy, -21, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, btnSzczegy, -36, SpringLayout.EAST, this);
		add(btnSzczegy);
		
		upperTable = new JTable();
		upperTableScrollPane = new JScrollPane(upperTable);
		springLayout.putConstraint(SpringLayout.NORTH, upperTableScrollPane, 107, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, upperTableScrollPane, -71, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.NORTH, upperTable, 335, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, upperTable, -161, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, upperTable, 188, SpringLayout.NORTH, this);
		upperTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		upperTable.setAutoResizeMode(JTable.WIDTH);
		upperTable.setAutoResizeMode(JTable.HEIGHT);
		add(upperTableScrollPane);
		
		lowerTable = new JTable();
		lowerTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
				      JTable target = (JTable)e.getSource();
				      int row = target.getSelectedRow();
				      int column = target.getSelectedColumn();
				    }
			}
		});
		lowerTableScrollPane = new JScrollPane(lowerTable);
		springLayout.putConstraint(SpringLayout.WEST, upperTableScrollPane, 0, SpringLayout.WEST, lowerTableScrollPane);
		springLayout.putConstraint(SpringLayout.WEST, lowerTableScrollPane, 48, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lowerTableScrollPane, -68, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, lowerTableScrollPane, 543, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, lowerTable, -90, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lowerTable, 303, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lowerTable, -188, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, lowerTable, 95, SpringLayout.WEST, this);
		lowerTable.setAutoResizeMode(JTable.WIDTH);
		lowerTable.setAutoResizeMode(JTable.HEIGHT);
		add(lowerTableScrollPane);
		
		lblLowerTable = new JLabel("New label");
		springLayout.putConstraint(SpringLayout.WEST, lblLowerTable, 50, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, upperTableScrollPane, -16, SpringLayout.NORTH, lblLowerTable);
		springLayout.putConstraint(SpringLayout.NORTH, lowerTableScrollPane, 6, SpringLayout.SOUTH, lblLowerTable);
		springLayout.putConstraint(SpringLayout.SOUTH, lblLowerTable, -245, SpringLayout.SOUTH, this);
		add(lblLowerTable);
		
		lblUpperTable = new JLabel("New label");
		springLayout.putConstraint(SpringLayout.WEST, lblUpperTable, 0, SpringLayout.WEST, upperTableScrollPane);
		springLayout.putConstraint(SpringLayout.SOUTH, lblUpperTable, -6, SpringLayout.NORTH, upperTableScrollPane);
		add(lblUpperTable);
		
		txtParametr = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtParametr, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, txtParametr, 0, SpringLayout.WEST, upperTableScrollPane);
		add(txtParametr);
		txtParametr.setColumns(10);
		
		btnWyszukaj = new JButton("Wyszukaj");
		springLayout.putConstraint(SpringLayout.NORTH, btnWyszukaj, 0, SpringLayout.NORTH, txtParametr);
		springLayout.putConstraint(SpringLayout.WEST, btnWyszukaj, 6, SpringLayout.EAST, txtParametr);
		add(btnWyszukaj);
		
		upperTableHeader = upperTable.getTableHeader();
		upperTableHeader.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (e.getClickCount() == 1) {
				      JTableHeader target = (JTableHeader)e.getSource();
				      int row = target.get
				      int column = target.getSelectedColumn();
				      
				      System.out.println(row);
				      System.out.println(column);
				    }
			}
		});
		
		lowerTableHeader = lowerTable.getTableHeader();
		lowerTableHeader.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (e.getClickCount() == 1) {
				      JTable target = (JTable)e.getSource();
				      int row = target.getSelectedRow();
				      int column = target.getSelectedColumn();
				      
				      System.out.println(row);
				      System.out.println(column);
				    }
			}
		});
		
		db = new dbDAO();
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
		
		final TableColumnModel model = lowerTable.getColumnModel();
	}
}
