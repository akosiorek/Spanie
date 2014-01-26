package gui;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CennikPanel extends JPanel{
	private JPanel pokojePanel;
	private JPanel uslugaPanel;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JLabel lblIloscLoze;
	private JLabel lblNazwa;
	private JButton btnWyszukajLoza;
	private JButton btnWyszukajUsluge;
	private JLabel lblStaraCena;
	private JLabel lblNowaCena;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnZatwierdz;
	private JButton btnAnuluj;
	
	
	private WidokPanel parent;
	/**
	 * Create the panel.
	 */
	public CennikPanel(WidokPanel _parent) {
		parent = _parent;
		setLayout(null);
		
		pokojePanel = new JPanel();
		pokojePanel.setBounds(84, 73, 228, 121);
		pokojePanel.setBorder(BorderFactory.createTitledBorder("Pokoje"));
		add(pokojePanel);
		
		uslugaPanel = new JPanel();
		uslugaPanel.setBounds(319, 73, 240, 116);
		uslugaPanel.setBorder(BorderFactory.createTitledBorder("Usługa dodatkowa"));
		pokojePanel.setLayout(new MigLayout("", "[79px][][12px][][80px][][][]", "[24px][25px]"));
		
		lblIloscLoze = new JLabel("Ilość łóżek:");
		pokojePanel.add(lblIloscLoze, "cell 0 0,alignx left,aligny center");
		
		comboBox = new JComboBox();
		pokojePanel.add(comboBox, "cell 1 0 7 1,growx,aligny top");
		add(uslugaPanel);
		uslugaPanel.setLayout(new MigLayout("", "[53px][102px][][]", "[24px][25px]"));
		
		lblNazwa = new JLabel("Nazwa:");
		uslugaPanel.add(lblNazwa, "cell 0 0,alignx left,aligny center");
		
		comboBox_1 = new JComboBox();
		uslugaPanel.add(comboBox_1, "cell 1 0 3 1,growx,aligny top");
		
		btnWyszukajUsluge = new JButton("Wyszukaj");
		btnWyszukajUsluge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		uslugaPanel.add(btnWyszukajUsluge, "cell 1 1,alignx left,aligny top");
		
		lblStaraCena = new JLabel(" Stara cena:");
		lblStaraCena.setBounds(280, 208, 77, 15);
		
		btnWyszukajLoza = new JButton("Wyszukaj");
		btnWyszukajLoza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		pokojePanel.add(btnWyszukajLoza, "cell 1 1,alignx right,aligny top");
		add(lblStaraCena);
		
		lblNowaCena = new JLabel("Nowa cena:");
		lblNowaCena.setBounds(285, 229, 73, 15);
		add(lblNowaCena);
		
		textField = new JTextField();
		textField.setBounds(387, 206, 122, 27);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(387, 227, 122, 27);
		add(textField_1);
		textField_1.setColumns(10);
		
		btnZatwierdz = new JButton("Zatwiedź");
		btnZatwierdz.setBounds(348, 266, 83, 27);
		btnZatwierdz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(btnZatwierdz);
		
		btnAnuluj = new JButton("Anuluj");
		btnAnuluj.setBounds(443, 266, 66, 27);
		btnAnuluj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				parent.wyswietl(parent.EMPTY);	
			}
		});
		add(btnAnuluj);

	}
}
