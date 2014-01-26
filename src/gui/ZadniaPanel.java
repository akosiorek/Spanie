package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class ZadniaPanel extends CardPanel<MainWindow> {

	public final static String ZWYKLE = "ZWYKLE";
	public final static String RAPORT = "RAPORT";
	
	private JPanel zwyklePanel;
	private JPanel adminPanel;
	
	 JButton btnNowyPobyt;
	 JButton btnIstniejcyPobyt;
	 JButton btnNowaRezerwacja;
	 JButton btnIstniejcaRezerwacja;
	 JButton btnAktualizujCennik;
	 JButton btnSezony;
	 private JButton btnRaport1;
	 private JButton btnRaport2;
	 private JButton btnRaport3;
	 
	 private PobytManager pobytManager = new PobytManager();
	 
	 
	/**
	 * Create the panel.
	 */
	public ZadniaPanel(MainWindow _parent) {
		super(_parent);
		
		zrobZwyklePanel();
		zrobAdminPanel();
		
		add(zwyklePanel, ZWYKLE);
		add(adminPanel, RAPORT);
		
	    wyswietl(ZWYKLE);	
	}
	
	private void zrobZwyklePanel() {
		
		zwyklePanel = new JPanel();
		
		btnNowyPobyt = new JButton("Nowy pobyt");
		btnNowyPobyt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(parent.sprawdzLogowanie()) {
					
					pobytManager.setState(PobytManager.Stan.NOWY_POBYT);
				}
			}
		});
		
		SpringLayout sl_zwyklePanel = new SpringLayout();
		sl_zwyklePanel.putConstraint(SpringLayout.NORTH, btnNowyPobyt, 15, SpringLayout.NORTH, zwyklePanel);
		sl_zwyklePanel.putConstraint(SpringLayout.WEST, btnNowyPobyt, 12, SpringLayout.WEST, zwyklePanel);
		sl_zwyklePanel.putConstraint(SpringLayout.SOUTH, btnNowyPobyt, 60, SpringLayout.NORTH, zwyklePanel);
		sl_zwyklePanel.putConstraint(SpringLayout.EAST, btnNowyPobyt, 214, SpringLayout.WEST, zwyklePanel);
		zwyklePanel.setLayout(sl_zwyklePanel);
		zwyklePanel.add(btnNowyPobyt);
		
		btnIstniejcyPobyt = new JButton("Istniejący pobyt");
		btnIstniejcyPobyt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(parent.sprawdzLogowanie()) {
					
					pobytManager.setState(PobytManager.Stan.ISTNIEJACY_POBYT);
				}
			}
		});
		sl_zwyklePanel.putConstraint(SpringLayout.NORTH, btnIstniejcyPobyt, 24, SpringLayout.NORTH, zwyklePanel);
		sl_zwyklePanel.putConstraint(SpringLayout.WEST, btnIstniejcyPobyt, 89, SpringLayout.WEST, zwyklePanel);
		zwyklePanel.add(btnIstniejcyPobyt);
		
		btnNowaRezerwacja = new JButton("Nowa rezerwacja");
		sl_zwyklePanel.putConstraint(SpringLayout.NORTH, btnNowaRezerwacja, 75, SpringLayout.NORTH, zwyklePanel);
		sl_zwyklePanel.putConstraint(SpringLayout.WEST, btnNowaRezerwacja, 12, SpringLayout.WEST, zwyklePanel);
		sl_zwyklePanel.putConstraint(SpringLayout.SOUTH, btnNowaRezerwacja, 120, SpringLayout.NORTH, zwyklePanel);
		sl_zwyklePanel.putConstraint(SpringLayout.EAST, btnNowaRezerwacja, 214, SpringLayout.WEST, zwyklePanel);
		btnNowaRezerwacja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(parent.sprawdzLogowanie()) {
					
					pobytManager.setState(PobytManager.Stan.NOWA_REZERWACJA);
				}
			}
		});
		zwyklePanel.add(btnNowaRezerwacja);
		
		btnIstniejcaRezerwacja = new JButton("Istniejąca rezerwacja");
		sl_zwyklePanel.putConstraint(SpringLayout.NORTH, btnIstniejcaRezerwacja, 135, SpringLayout.NORTH, zwyklePanel);
		sl_zwyklePanel.putConstraint(SpringLayout.WEST, btnIstniejcaRezerwacja, 12, SpringLayout.WEST, zwyklePanel);
		sl_zwyklePanel.putConstraint(SpringLayout.SOUTH, btnIstniejcaRezerwacja, 179, SpringLayout.NORTH, zwyklePanel);
		sl_zwyklePanel.putConstraint(SpringLayout.EAST, btnIstniejcaRezerwacja, 214, SpringLayout.WEST, zwyklePanel);
		btnIstniejcaRezerwacja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(parent.sprawdzLogowanie()) {
					pobytManager.setState(PobytManager.Stan.ISTNIEJACA_REZERWACJA);
				}
			}
		});
		btnIstniejcaRezerwacja.setBounds(-50, 137, 373, 44);
		zwyklePanel.add(btnIstniejcaRezerwacja);
		
		btnAktualizujCennik = new JButton("Aktualizuj cennik");
		sl_zwyklePanel.putConstraint(SpringLayout.NORTH, btnAktualizujCennik, 194, SpringLayout.NORTH, zwyklePanel);
		sl_zwyklePanel.putConstraint(SpringLayout.WEST, btnAktualizujCennik, 12, SpringLayout.WEST, zwyklePanel);
		sl_zwyklePanel.putConstraint(SpringLayout.SOUTH, btnAktualizujCennik, 238, SpringLayout.NORTH, zwyklePanel);
		sl_zwyklePanel.putConstraint(SpringLayout.EAST, btnAktualizujCennik, 214, SpringLayout.WEST, zwyklePanel);
		btnAktualizujCennik.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if(parent.sprawdzLogowanie()) {
					parent.widokPanel.wyswietl(WidokPanel.CENNIK);
				}		
			}
		});
		zwyklePanel.add(btnAktualizujCennik);
		
		btnSezony = new JButton("Zarządzaj sezonami");
		sl_zwyklePanel.putConstraint(SpringLayout.NORTH, btnSezony, 253, SpringLayout.NORTH, zwyklePanel);
		sl_zwyklePanel.putConstraint(SpringLayout.WEST, btnSezony, 12, SpringLayout.WEST, zwyklePanel);
		sl_zwyklePanel.putConstraint(SpringLayout.SOUTH, btnSezony, 298, SpringLayout.NORTH, zwyklePanel);
		sl_zwyklePanel.putConstraint(SpringLayout.EAST, btnSezony, 214, SpringLayout.WEST, zwyklePanel);
		btnSezony.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(parent.sprawdzLogowanie()) {
					
					parent.widokPanel.wyswietl(WidokPanel.SEZON);
				}
									
			}
		});
		zwyklePanel.add(btnSezony);
	}
	
	private void zrobAdminPanel() {
		adminPanel = new JPanel();
		
		SpringLayout sl_adminPanel = new SpringLayout();
		adminPanel.setLayout(sl_adminPanel);
		
		btnRaport1 = new JButton("Raport 1");
		btnRaport2 = new JButton("Raport 2");
		btnRaport3 = new JButton("Raport 3");
		
		
		//	btnUzytkownicy
		sl_adminPanel.putConstraint(SpringLayout.NORTH, btnRaport1, 15, SpringLayout.NORTH, adminPanel);
		sl_adminPanel.putConstraint(SpringLayout.WEST, btnRaport1, 12, SpringLayout.WEST, adminPanel);
		sl_adminPanel.putConstraint(SpringLayout.SOUTH, btnRaport1, 60, SpringLayout.NORTH, adminPanel);
		sl_adminPanel.putConstraint(SpringLayout.EAST, btnRaport1, 214, SpringLayout.WEST, adminPanel);		
		
		// btnUprawnienia
		sl_adminPanel.putConstraint(SpringLayout.NORTH, btnRaport2, 75, SpringLayout.NORTH, adminPanel);
		sl_adminPanel.putConstraint(SpringLayout.WEST, btnRaport2, 12, SpringLayout.WEST, adminPanel);
		sl_adminPanel.putConstraint(SpringLayout.SOUTH, btnRaport2, 120, SpringLayout.NORTH, adminPanel);
		sl_adminPanel.putConstraint(SpringLayout.EAST, btnRaport2, 214, SpringLayout.WEST, adminPanel);
		

		
		//	btnUzytkownicy
		sl_adminPanel.putConstraint(SpringLayout.NORTH, btnRaport3, 135, SpringLayout.NORTH, adminPanel);
		sl_adminPanel.putConstraint(SpringLayout.WEST, btnRaport3, 12, SpringLayout.WEST, adminPanel);
		sl_adminPanel.putConstraint(SpringLayout.SOUTH, btnRaport3, 179, SpringLayout.NORTH, adminPanel);
		sl_adminPanel.putConstraint(SpringLayout.EAST, btnRaport3, 214, SpringLayout.WEST, adminPanel);

	
		
		
		
		btnRaport1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				parent.widokPanel.wyswietl(WidokPanel.RAPORT1);
			}
		});
		
		btnRaport2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				parent.widokPanel.wyswietl(WidokPanel.RAPORT2);
			}
		});
		
		btnRaport3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.widokPanel.wyswietl(WidokPanel.RAPORT3);
			}
		});
		
		adminPanel.add(btnRaport1);
		adminPanel.add(btnRaport2);
		adminPanel.add(btnRaport3);
	}
}
