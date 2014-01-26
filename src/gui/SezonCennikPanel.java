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
	private JLabel lblTypPokoju;
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
		setLayout(null);
		
		pokojePanel = new JPanel();
		pokojePanel.setBounds(149, 36, 243, 182);
		pokojePanel.setBorder(BorderFactory.createTitledBorder("Pokoje"));
		add(pokojePanel);
		SpringLayout sl_pokojePanel = new SpringLayout();
		pokojePanel.setLayout(sl_pokojePanel);
		
		lblTypPokoju = new JLabel("Typ pokoju:");
		sl_pokojePanel.putConstraint(SpringLayout.WEST, lblTypPokoju, 0, SpringLayout.WEST, pokojePanel);
		pokojePanel.add(lblTypPokoju);
		
		lblStaraCena = new JLabel("Stara cena:");
		sl_pokojePanel.putConstraint(SpringLayout.WEST, lblStaraCena, 0, SpringLayout.WEST, lblTypPokoju);
		pokojePanel.add(lblStaraCena);
		
		lblNowaCena = new JLabel("Nowa cena:");
		sl_pokojePanel.putConstraint(SpringLayout.SOUTH, lblStaraCena, -14, SpringLayout.NORTH, lblNowaCena);
		sl_pokojePanel.putConstraint(SpringLayout.WEST, lblNowaCena, 0, SpringLayout.WEST, lblTypPokoju);
		pokojePanel.add(lblNowaCena);
		
		comboLozka = new JComboBox();
		sl_pokojePanel.putConstraint(SpringLayout.WEST, comboLozka, 12, SpringLayout.EAST, lblTypPokoju);
		sl_pokojePanel.putConstraint(SpringLayout.EAST, comboLozka, -14, SpringLayout.EAST, pokojePanel);
		sl_pokojePanel.putConstraint(SpringLayout.NORTH, lblTypPokoju, 5, SpringLayout.NORTH, comboLozka);
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
		btnZatwierd.setBounds(174, 238, 88, 27);
		btnZatwierd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				parent.wyswietl(parent.SEZON);	
			}
		});
		add(btnZatwierd);
		
		
		
		btnAnuluj = new JButton("Anuluj");
		btnAnuluj.setBounds(268, 238, 66, 27);
		btnAnuluj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				parent.wyswietl(parent.SEZON);	
			}
		});
		add(btnAnuluj);

	}
}
