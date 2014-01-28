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

public class RezerwacjaPanel extends JPanel {
	private final JButton btnStworzPobyt = new JButton("Stwórz pobyt");
	private final JButton btnAnulujRezerwacje = new JButton("Anuluj rezerwacje");
	private final JPanel panel = new JPanel();
	private final JScrollPane panelScrollPane;
	private final JLabel lblKlient = new JLabel("Klient:");
	private final JLabel lblPokj = new JLabel("Pokój:");
	private final JLabel lblTypPokoju = new JLabel("TypPokoju");
	private final JLabel lblImieinazwisko = new JLabel("ImieINazwisko");
	
	private PobytManager parent;
	
	
	private final static int nazwaX = 144;
	private final static int cenaX = 256;
	private final static int interval = 27;
	private final static int height = 15;
	private final static int width = 108;
	private final static int first = 87;
	private final JLabel lblCenaPobytu = new JLabel("Cena:");
	private final JLabel lblCenaLiczbowa = new JLabel("");
	private final JLabel lblCenaPokoju = new JLabel("");
	
	/**
	 * Create the panel.
	 */
	public RezerwacjaPanel() {
		
		setSize(800, 600);
		setLayout(null);
		btnStworzPobyt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				parent.przeksztalcRezerwacjeWPobyt();
				parent.show(Okno.POBYT);
			}
		});
		btnStworzPobyt.setBounds(137, 53, 125, 27);
		
		add(btnStworzPobyt);
		btnAnulujRezerwacje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				parent.anulujRezerwacje();
				parent.hidePanel();
			}
		});
		btnAnulujRezerwacje.setBounds(274, 53, 144, 27);
		
		add(btnAnulujRezerwacje);		
		
		panelScrollPane = new JScrollPane(panel);
		panelScrollPane.setBounds(137, 92, 397, 200);
		add(panelScrollPane);
		panel.setLayout(null);
		panelScrollPane.setBorder(BorderFactory.createTitledBorder("Szczegóły"));
		lblKlient.setBounds(6, 6, 60, 15);
		
		panel.add(lblKlient);
		lblPokj.setBounds(6, 33, 60, 15);
		
		panel.add(lblPokj);
		lblTypPokoju.setBounds(144, 33, 108, 15);
		
		panel.add(lblTypPokoju);
		lblImieinazwisko.setBounds(144, 6, 108, 15);
		
		panel.add(lblImieinazwisko);
		lblCenaPobytu.setBounds(6, 60, 100, 15);
		panel.add(lblCenaPobytu);
		lblCenaLiczbowa.setBounds(144, 60, 60, 15);
		panel.add(lblCenaLiczbowa);
		lblCenaPokoju.setBounds(269, 33, 60, 15);
		
		panel.add(lblCenaPokoju);
		
		addComponentListener(new ComponentAdapter() {
			public void componentHidden(ComponentEvent e) 
			{
			    /* code run when component hidden*/
			}
			public void componentShown(ComponentEvent e) {

				odswiez();
			}
			});

	}
	
	public void setParent(PobytManager parent) {
		this.parent = parent;
	}
	
	public void odswiez() {
		
		lblImieinazwisko.setText(parent.getImie() + " " + parent.getNazwisko());
		lblTypPokoju.setText(parent.getTypPokoju() + " nr: " + parent.getNrPokoju() );
		lblCenaLiczbowa.setText(parent.getCenaPobytu() + " zł");
		lblCenaPokoju.setText(parent.getCenaPokoju() + " zł");
	}
}
