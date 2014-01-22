package gui;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SezonCennikPanel extends JPanel {
	private JPanel pokojePanel;
	private JLabel lblIloek;
	private JLabel lblStaraCena;
	private JLabel lblNowaCena;
	private JComboBox comboLozka;
	private JTextField txtStaraCena;
	private JTextField txtNowaCena;
	private JButton btnZatwierdzPokoje;
	private JButton btnAnulujPokoje;
	private JButton btnZatwierd;
	private JButton btnAnuluj;
	
	
	private WidokPanel parent;
	/**
	 * Create the panel.
	 */
	public SezonCennikPanel(WidokPanel _parent) {
		parent = _parent;
		
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		pokojePanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, pokojePanel, 37, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, pokojePanel, 80, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, pokojePanel, 219, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, pokojePanel, 323, SpringLayout.WEST, this);
		pokojePanel.setBorder(BorderFactory.createTitledBorder("Pokoje"));
		add(pokojePanel);
		SpringLayout sl_pokojePanel = new SpringLayout();
		pokojePanel.setLayout(sl_pokojePanel);
		
		lblIloek = new JLabel("Ilość łóżek:");
		sl_pokojePanel.putConstraint(SpringLayout.WEST, lblIloek, 0, SpringLayout.WEST, pokojePanel);
		pokojePanel.add(lblIloek);
		
		lblStaraCena = new JLabel("Stara cena:");
		sl_pokojePanel.putConstraint(SpringLayout.WEST, lblStaraCena, 0, SpringLayout.WEST, lblIloek);
		pokojePanel.add(lblStaraCena);
		
		lblNowaCena = new JLabel("Nowa cena:");
		sl_pokojePanel.putConstraint(SpringLayout.SOUTH, lblStaraCena, -14, SpringLayout.NORTH, lblNowaCena);
		sl_pokojePanel.putConstraint(SpringLayout.WEST, lblNowaCena, 0, SpringLayout.WEST, lblIloek);
		pokojePanel.add(lblNowaCena);
		
		comboLozka = new JComboBox();
		sl_pokojePanel.putConstraint(SpringLayout.WEST, comboLozka, 12, SpringLayout.EAST, lblIloek);
		sl_pokojePanel.putConstraint(SpringLayout.EAST, comboLozka, -14, SpringLayout.EAST, pokojePanel);
		sl_pokojePanel.putConstraint(SpringLayout.NORTH, lblIloek, 5, SpringLayout.NORTH, comboLozka);
		pokojePanel.add(comboLozka);
		
		txtStaraCena = new JTextField();
		sl_pokojePanel.putConstraint(SpringLayout.NORTH, txtStaraCena, 46, SpringLayout.NORTH, pokojePanel);
		sl_pokojePanel.putConstraint(SpringLayout.SOUTH, comboLozka, -10, SpringLayout.NORTH, txtStaraCena);
		txtStaraCena.setEditable(false);
		pokojePanel.add(txtStaraCena);
		txtStaraCena.setColumns(10);
		
		txtNowaCena = new JTextField();
		sl_pokojePanel.putConstraint(SpringLayout.WEST, txtNowaCena, 6, SpringLayout.EAST, lblNowaCena);
		sl_pokojePanel.putConstraint(SpringLayout.NORTH, lblNowaCena, 6, SpringLayout.NORTH, txtNowaCena);
		sl_pokojePanel.putConstraint(SpringLayout.NORTH, txtNowaCena, 2, SpringLayout.SOUTH, txtStaraCena);
		sl_pokojePanel.putConstraint(SpringLayout.EAST, txtStaraCena, 0, SpringLayout.EAST, txtNowaCena);
		pokojePanel.add(txtNowaCena);
		txtNowaCena.setColumns(10);
		
		btnZatwierdzPokoje = new JButton("Zatwierdz");
		btnZatwierdzPokoje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		sl_pokojePanel.putConstraint(SpringLayout.NORTH, btnZatwierdzPokoje, 6, SpringLayout.SOUTH, txtNowaCena);
		sl_pokojePanel.putConstraint(SpringLayout.WEST, btnZatwierdzPokoje, 10, SpringLayout.WEST, pokojePanel);
		pokojePanel.add(btnZatwierdzPokoje);
		
		btnAnulujPokoje = new JButton("Anuluj");
		btnAnulujPokoje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		sl_pokojePanel.putConstraint(SpringLayout.NORTH, btnAnulujPokoje, 6, SpringLayout.SOUTH, txtNowaCena);
		sl_pokojePanel.putConstraint(SpringLayout.WEST, btnAnulujPokoje, 6, SpringLayout.EAST, btnZatwierdzPokoje);
		pokojePanel.add(btnAnulujPokoje);
		
		btnZatwierd = new JButton("Zatwierdź");
		btnZatwierd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				parent.wyswietl(parent.SEZON);	
			}
		});
		add(btnZatwierd);
		
		btnAnuluj = new JButton("Anuluj");
		btnAnuluj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				parent.wyswietl(parent.SEZON);	
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnAnuluj, 20, SpringLayout.SOUTH, pokojePanel);
		springLayout.putConstraint(SpringLayout.WEST, btnAnuluj, 199, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, btnZatwierd, 0, SpringLayout.NORTH, btnAnuluj);
		springLayout.putConstraint(SpringLayout.EAST, btnZatwierd, -6, SpringLayout.WEST, btnAnuluj);
		add(btnAnuluj);

	}
}
