package Gui;

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
		
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		pokojePanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, pokojePanel, 46, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, pokojePanel, 21, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, pokojePanel, -180, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, pokojePanel, -302, SpringLayout.EAST, this);
		pokojePanel.setBorder(BorderFactory.createTitledBorder("Pokoje"));
		add(pokojePanel);
		
		uslugaPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, uslugaPanel, 46, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, uslugaPanel, 29, SpringLayout.EAST, pokojePanel);
		springLayout.putConstraint(SpringLayout.SOUTH, uslugaPanel, 0, SpringLayout.SOUTH, pokojePanel);
		springLayout.putConstraint(SpringLayout.EAST, uslugaPanel, -45, SpringLayout.EAST, this);
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
		springLayout.putConstraint(SpringLayout.NORTH, lblStaraCena, 38, SpringLayout.SOUTH, pokojePanel);
		
		btnWyszukajLoza = new JButton("Wyszukaj");
		btnWyszukajLoza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		pokojePanel.add(btnWyszukajLoza, "cell 1 1,alignx right,aligny top");
		add(lblStaraCena);
		
		lblNowaCena = new JLabel("Nowa cena:");
		springLayout.putConstraint(SpringLayout.NORTH, lblNowaCena, 6, SpringLayout.SOUTH, lblStaraCena);
		springLayout.putConstraint(SpringLayout.EAST, lblNowaCena, 0, SpringLayout.EAST, pokojePanel);
		add(lblNowaCena);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.EAST, lblStaraCena, -30, SpringLayout.WEST, textField);
		springLayout.putConstraint(SpringLayout.NORTH, textField, -2, SpringLayout.NORTH, lblStaraCena);
		springLayout.putConstraint(SpringLayout.WEST, textField, 0, SpringLayout.WEST, uslugaPanel);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_1, -2, SpringLayout.NORTH, lblNowaCena);
		springLayout.putConstraint(SpringLayout.WEST, textField_1, 0, SpringLayout.WEST, uslugaPanel);
		add(textField_1);
		textField_1.setColumns(10);
		
		btnZatwierdz = new JButton("Zatwiedź");
		btnZatwierdz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		springLayout.putConstraint(SpringLayout.WEST, btnZatwierdz, 0, SpringLayout.WEST, lblNowaCena);
		add(btnZatwierdz);
		
		btnAnuluj = new JButton("Anuluj");
		btnAnuluj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				parent.wyswietl(parent.EMPTY);	
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnZatwierdz, 0, SpringLayout.NORTH, btnAnuluj);
		springLayout.putConstraint(SpringLayout.NORTH, btnAnuluj, 25, SpringLayout.SOUTH, textField_1);
		springLayout.putConstraint(SpringLayout.EAST, btnAnuluj, 0, SpringLayout.EAST, textField);
		add(btnAnuluj);

	}
}
