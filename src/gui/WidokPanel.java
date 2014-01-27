package gui;

import javax.swing.JPanel;

public class WidokPanel extends CardPanel<MainWindow> {

	
	 private JPanel sezonPanel;
	 private JPanel sezonCennikPanel;
	 private JPanel aktualizujCennikPanel;
	 private JPanel raport1Panel;
	 private JPanel raport2Panel;
	 private JPanel raport3Panel;
	 
	 final static String SEZON = "SEZON";
	 final static String CENNIK = "CENNIK";
	 final static String SEZON_CENNIK = "SEZON_CENNIK";
	 final static String EMPTY = "EMPTY";
	 final static String RAPORT1 = "RAPORT1";
	 final static String RAPORT2 = "RAPORT2";
	 final static String RAPORT3 = "RAPORT3";
	 
	 private String sezon;
	
	/**
	 * Create the panel.
	 */
	public WidokPanel(MainWindow _parent) {
		super(_parent);
		
		
		sezonPanel = new SezonPanel(this);
		sezonCennikPanel = new SezonCennikPanel(this);
		aktualizujCennikPanel = new CennikPanel(this);
	
		
		add(aktualizujCennikPanel, CENNIK);
		add(sezonPanel, SEZON);
		add(sezonCennikPanel, SEZON_CENNIK);	
		add(new JPanel(), EMPTY);		
		
		//	Raporty
		raport1Panel = new Raport1Panel(this);
		raport2Panel = new Raport2Panel(this);
		raport3Panel = new Raport3Panel(this);
		add(raport1Panel, RAPORT1);
		add(raport2Panel, RAPORT2);
		add(raport3Panel, RAPORT3);
		
		
		wyswietl(EMPTY);
		
	}
	
	public String getSezon() {
		
		return sezon;
	}
	
	public void setSezon(String sezon) {
		
		this.sezon = sezon;
	}
	
	public void addPanel(JPanel panel, String name) {
		
		add(panel, name);
	}

}
