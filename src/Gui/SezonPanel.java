package Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class SezonPanel extends JPanel {
	private JComboBox nazwaSezonuCombo;
	private JTextField txtDataRozpoczenia;
	private JTextField txtDataZakonczenia;
	private JLabel lblDataRozpoczecia;
	private JLabel lblDataZakonczenia;
	private JButton btnWyszukaj;
	private JButton btnDodaj;
	private JButton btnAnuluj;
	private JLabel lblNazwaSezonu;
	
	private WidokPanel parent;
	/**
	 * Create the panel.
	 */
	public SezonPanel(WidokPanel _parent) {
		parent = _parent;
		
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		
		nazwaSezonuCombo = new JComboBox();
		springLayout.putConstraint(SpringLayout.EAST, nazwaSezonuCombo, -130, SpringLayout.EAST, this);
		add(nazwaSezonuCombo);
		
		txtDataRozpoczenia = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, nazwaSezonuCombo, 0, SpringLayout.WEST, txtDataRozpoczenia);
		springLayout.putConstraint(SpringLayout.SOUTH, nazwaSezonuCombo, -8, SpringLayout.NORTH, txtDataRozpoczenia);
		springLayout.putConstraint(SpringLayout.EAST, txtDataRozpoczenia, 320, SpringLayout.WEST, this);
		add(txtDataRozpoczenia);
		txtDataRozpoczenia.setColumns(10);
		
		txtDataZakonczenia = new JTextField();
		springLayout.putConstraint(SpringLayout.SOUTH, txtDataRozpoczenia, -6, SpringLayout.NORTH, txtDataZakonczenia);
		springLayout.putConstraint(SpringLayout.NORTH, txtDataZakonczenia, 105, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, txtDataZakonczenia, 0, SpringLayout.WEST, nazwaSezonuCombo);
		springLayout.putConstraint(SpringLayout.EAST, txtDataZakonczenia, 0, SpringLayout.EAST, txtDataRozpoczenia);
		add(txtDataZakonczenia);
		txtDataZakonczenia.setColumns(10);
		
		lblDataRozpoczecia = new JLabel("Data rozpoczecia:");
		springLayout.putConstraint(SpringLayout.NORTH, lblDataRozpoczecia, 82, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, txtDataRozpoczenia, 15, SpringLayout.EAST, lblDataRozpoczecia);
		springLayout.putConstraint(SpringLayout.WEST, lblDataRozpoczecia, 10, SpringLayout.WEST, this);
		add(lblDataRozpoczecia);
		
		lblDataZakonczenia = new JLabel("Data zakonczenia:");
		springLayout.putConstraint(SpringLayout.NORTH, lblDataZakonczenia, 20, SpringLayout.SOUTH, lblDataRozpoczecia);
		springLayout.putConstraint(SpringLayout.WEST, lblDataZakonczenia, 10, SpringLayout.WEST, this);
		add(lblDataZakonczenia);
		btnWyszukaj = new JButton("Wyszuaj");
		btnWyszukaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				parent.wyswietl(WidokPanel.SEZON_CENNIK);	
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnWyszukaj, 75, SpringLayout.SOUTH, lblDataZakonczenia);
		add(btnWyszukaj);
		
		btnDodaj = new JButton("Dodaj");
		springLayout.putConstraint(SpringLayout.NORTH, btnDodaj, 75, SpringLayout.SOUTH, txtDataZakonczenia);
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				parent.wyswietl(WidokPanel.SEZON_CENNIK);	
			}
		});
		springLayout.putConstraint(SpringLayout.WEST, btnDodaj, 178, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, btnDodaj, -178, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.EAST, btnWyszukaj, -42, SpringLayout.WEST, btnDodaj);
		add(btnDodaj);
		
		btnAnuluj = new JButton("Anuluj");
		springLayout.putConstraint(SpringLayout.NORTH, btnAnuluj, 75, SpringLayout.SOUTH, txtDataZakonczenia);
		btnAnuluj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				parent.wyswietl(WidokPanel.EMPTY);		
			}
		});
		springLayout.putConstraint(SpringLayout.WEST, btnAnuluj, 42, SpringLayout.EAST, btnDodaj);
		springLayout.putConstraint(SpringLayout.EAST, btnAnuluj, -42, SpringLayout.EAST, this);
		add(btnAnuluj);
		
		lblNazwaSezonu = new JLabel("Nazwa sezonu:");
		springLayout.putConstraint(SpringLayout.WEST, lblNazwaSezonu, 0, SpringLayout.WEST, lblDataRozpoczecia);
		springLayout.putConstraint(SpringLayout.SOUTH, lblNazwaSezonu, 0, SpringLayout.SOUTH, nazwaSezonuCombo);
		add(lblNazwaSezonu);

	}

}
