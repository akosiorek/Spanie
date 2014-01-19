package Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class ZadniaPanel extends CardPanel<MainWindow> {

	final String ZWYKLE = "ZWYKLE";
	final String ADMIN = "ADMIN";
	
	private JPanel zwyklePanel;
	private JPanel adminPanel;
	
	 JButton btnNowyPobyt;
	 JButton btnIstniejcyPobyt;
	 JButton btnNowaRezerwacja;
	 JButton btnIstniejcaRezerwacja;
	 JButton btnAktualizujCennik;
	 JButton btnSezony;
	 private JButton btnUytkownicy;
	 private JButton btnUprawnienia;
	 private JButton btnUslugiDodatkowe;
	 private JButton btnPokoje;
	 private JButton btnRaporty;
	 private JButton btnUsunHistorieRezerwacji;
	 private JButton btnUsunHistoriePobytw;
	 
	 
	/**
	 * Create the panel.
	 */
	public ZadniaPanel(MainWindow _parent) {
		super(_parent);
		
		zrobZwyklePanel();
		zrobAdminPanel();
		
		add(zwyklePanel, ZWYKLE);
		add(adminPanel, ADMIN);
		
	    wyswietl(ZWYKLE);	
	}
	
	private void zrobZwyklePanel() {
		
		zwyklePanel = new JPanel();
		
		btnNowyPobyt = new JButton("Nowy pobyt");
		btnNowyPobyt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(parent.sprawdzLogowanie()) {
					
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
		
		btnUytkownicy = new JButton("Użytkownicy");
		btnUytkownicy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		sl_adminPanel.putConstraint(SpringLayout.NORTH, btnUytkownicy, 10, SpringLayout.NORTH, adminPanel);
		sl_adminPanel.putConstraint(SpringLayout.WEST, btnUytkownicy, 10, SpringLayout.WEST, adminPanel);
		sl_adminPanel.putConstraint(SpringLayout.EAST, btnUytkownicy, 177, SpringLayout.WEST, adminPanel);
		adminPanel.add(btnUytkownicy);
		
		btnUprawnienia = new JButton("Uprawnienia");
		btnUprawnienia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		sl_adminPanel.putConstraint(SpringLayout.NORTH, btnUprawnienia, 13, SpringLayout.SOUTH, btnUytkownicy);
		sl_adminPanel.putConstraint(SpringLayout.WEST, btnUprawnienia, 10, SpringLayout.WEST, adminPanel);
		sl_adminPanel.putConstraint(SpringLayout.EAST, btnUprawnienia, 177, SpringLayout.WEST, adminPanel);
		adminPanel.add(btnUprawnienia);
		
		btnUslugiDodatkowe = new JButton("Usługi dodatkowe");
		btnUslugiDodatkowe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		sl_adminPanel.putConstraint(SpringLayout.WEST, btnUslugiDodatkowe, 10, SpringLayout.WEST, adminPanel);
		sl_adminPanel.putConstraint(SpringLayout.EAST, btnUslugiDodatkowe, 177, SpringLayout.WEST, adminPanel);
		adminPanel.add(btnUslugiDodatkowe);
		
		btnPokoje = new JButton("Pokoje");
		btnPokoje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		sl_adminPanel.putConstraint(SpringLayout.NORTH, btnPokoje, 13, SpringLayout.SOUTH, btnUprawnienia);
		sl_adminPanel.putConstraint(SpringLayout.WEST, btnPokoje, 10, SpringLayout.WEST, adminPanel);
		sl_adminPanel.putConstraint(SpringLayout.EAST, btnPokoje, 177, SpringLayout.WEST, adminPanel);
		sl_adminPanel.putConstraint(SpringLayout.NORTH, btnUslugiDodatkowe, 13, SpringLayout.SOUTH, btnPokoje);
		adminPanel.add(btnPokoje);
		
		btnRaporty = new JButton("Raporty");
		btnRaporty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		sl_adminPanel.putConstraint(SpringLayout.NORTH, btnRaporty, 13, SpringLayout.SOUTH, btnUslugiDodatkowe);
		sl_adminPanel.putConstraint(SpringLayout.WEST, btnRaporty, 10, SpringLayout.WEST, adminPanel);
		sl_adminPanel.putConstraint(SpringLayout.EAST, btnRaporty, 177, SpringLayout.WEST, adminPanel);
		adminPanel.add(btnRaporty);
		
		btnUsunHistorieRezerwacji = new JButton("Usun historie rezerwacji");
		btnUsunHistorieRezerwacji.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		sl_adminPanel.putConstraint(SpringLayout.NORTH, btnUsunHistorieRezerwacji, 13, SpringLayout.SOUTH, btnRaporty);
		sl_adminPanel.putConstraint(SpringLayout.WEST, btnUsunHistorieRezerwacji, 10, SpringLayout.WEST, adminPanel);
		sl_adminPanel.putConstraint(SpringLayout.EAST, btnUsunHistorieRezerwacji, 177, SpringLayout.WEST, adminPanel);
		adminPanel.add(btnUsunHistorieRezerwacji);
		
		btnUsunHistoriePobytw = new JButton("Usun historie pobytów");
		btnUsunHistoriePobytw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		sl_adminPanel.putConstraint(SpringLayout.NORTH, btnUsunHistoriePobytw, 13, SpringLayout.SOUTH, btnUsunHistorieRezerwacji);
		sl_adminPanel.putConstraint(SpringLayout.WEST, btnUsunHistoriePobytw, 10, SpringLayout.WEST, adminPanel);
		adminPanel.add(btnUsunHistoriePobytw);
	}

}
