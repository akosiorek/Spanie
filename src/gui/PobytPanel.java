package gui;

import gui.PobytManager.Okno;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

public class PobytPanel extends JPanel {
	private final JButton btnZakonczPobyt = new JButton("Zakończ pobyt");
	private final JButton btnDodajUsugeDodatkowa = new JButton("Dodaj usługę dodatkową");
	private final JButton btnUsunPobyt = new JButton("Usuń pobyt");
	private final JPanel panel = new JPanel();
	private final JScrollPane panelScrollPane;
	private final JLabel lblKlient = new JLabel("Klient:");
	private final JLabel lblPokj = new JLabel("Pokój:");
	private final JLabel lblUslugiDodatkowe = new JLabel("Uslugi dodatkowe:");
	private final JLabel lblTypPokoju = new JLabel("TypPokoju");
	private final JLabel lblImieinazwisko = new JLabel("ImieINazwisko");
	
	private PobytManager parent;
	
	private List<JLabel> uslugiDodatkowe = new ArrayList<JLabel>();
	private List<JLabel> cenyUslugDodatkowych = new ArrayList<JLabel>();
	
	private final static int nazwaX = 144;
	private final static int cenaX = 256;
	private final static int interval = 27;
	private final static int height = 15;
	private final static int width = 108;
	private final static int first = 87;
	private final JLabel lblNazwa = new JLabel("Nazwa:");
	private final JLabel lblCena = new JLabel("Cena:");
	private final JLabel lblCenaPobytu = new JLabel("Cena pobytu:");
	private final JLabel lblCenaZaPobyt = new JLabel("");
	
	/**
	 * Create the panel.
	 */
	public PobytPanel() {
		
		setSize(800, 600);
		setLayout(null);
		btnZakonczPobyt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnZakonczPobyt.setBounds(137, 53, 125, 27);
		
		add(btnZakonczPobyt);
		btnDodajUsugeDodatkowa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				parent.show(Okno.USLUGA_DODATKOWA);
			}
		});
		btnDodajUsugeDodatkowa.setBounds(274, 53, 183, 27);
		
		add(btnDodajUsugeDodatkowa);
		btnUsunPobyt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				parent.usunPobyt();
				parent.hidePanel();
			}
		});
		btnUsunPobyt.setBounds(469, 53, 100, 27);
		
		add(btnUsunPobyt);
		
		
		panelScrollPane = new JScrollPane(panel);
		panelScrollPane.setBounds(137, 92, 432, 200);
		add(panelScrollPane);
		panel.setLayout(null);
		panelScrollPane.setBorder(BorderFactory.createTitledBorder("Szczegóły"));
		lblKlient.setBounds(6, 6, 60, 15);
		
		panel.add(lblKlient);
		lblPokj.setBounds(6, 33, 60, 15);
		
		panel.add(lblPokj);
		lblUslugiDodatkowe.setBounds(6, 60, 126, 15);
		
		panel.add(lblUslugiDodatkowe);
		lblTypPokoju.setBounds(144, 33, 108, 15);
		
		panel.add(lblTypPokoju);
		lblImieinazwisko.setBounds(144, 6, 108, 15);
		
		panel.add(lblImieinazwisko);
		lblNazwa.setBounds(144, 60, 60, 15);
		
		panel.add(lblNazwa);
		lblCena.setBounds(cenaX, 60, 60, 15);
		
		panel.add(lblCena);
		lblCenaPobytu.setBounds(147, 304, 100, 15);
		
		add(lblCenaPobytu);
		lblCenaZaPobyt.setBounds(259, 304, 60, 15);
		
		add(lblCenaZaPobyt);
		
		addComponentListener(new ComponentAdapter() {
			public void componentHidden(ComponentEvent e) 
			{
			    /* code run when component hidden*/
			}
			public void componentShown(ComponentEvent e) {

				lblImieinazwisko.setText(parent.getImie() + " " + parent.getNazwisko());
				lblTypPokoju.setText(parent.getTypPokoju() + " nr: " + parent.getNrPokoju() );
			}
			});

	}
	
	public void setParent(PobytManager parent) {
		this.parent = parent;
	}
	
	public void notifyDodanieUslugi() {
		
		List<String> nazwy = parent.getUslugiDodatkowe();
		List<String> ceny = parent.getCenyUslugDodatkowych();
		
		for(int i = uslugiDodatkowe.size(); i < nazwy.size(); i++) {
			
			JLabel nazwa = new JLabel(nazwy.get(i));
			JLabel cena = new JLabel(ceny.get(i));
			
			nazwa.setBounds(nazwaX, first + i * interval, width, height); 
			cena.setBounds(cenaX, first + i * interval, width, height); 
			
			uslugiDodatkowe.add(nazwa);
			cenyUslugDodatkowych.add(cena);
			panel.add(cena);
			panel.add(nazwa);
			panel.setPreferredSize(new Dimension(panel.getWidth() - 20, first + i * interval + height));
//			System.out.println("Added " + nazwa.getText() + " - " + cena.getText());
		}
		lblCenaZaPobyt.setText(parent.getCenaPobytu() + " zł");
		panel.revalidate();
		panelScrollPane.revalidate();
		repaint();
	}
}
