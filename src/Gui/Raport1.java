package Gui;

import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.*;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Raport1 extends RaportPanel {

	
	
	
	/**
	 * Create the panel.
	 */
	public Raport1() {
		
		lblUpperTable.setText("Tabela");
		lblLowerTable.setText("Tabela");

		
		showResultUpper(new dbDAO().query2);
		showResultLower(new dbDAO().query1);
	}
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.setSize(800, 600);
		Raport1 raport = new Raport1();
		frame.getContentPane().add(raport);
		frame.setVisible(true);
		
//		raport.setUpperData(null);
	}
	
	

}