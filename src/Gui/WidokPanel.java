package Gui;

import javax.swing.JPanel;

public class WidokPanel extends CardPanel<MainWindow> {

	
	 private JPanel sezonPanel;
	 private JPanel sezonCennikPanel;
	 private JPanel aktualizujCennikPanel;
	 
	 final static String SEZON = "SEZON";
	 final static String CENNIK = "CENNIK";
	 final static String SEZON_CENNIK = "SEZON_CENNIK";
	 final static String EMPTY = "EMPTY";
	
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
		
		wyswietl(EMPTY);
		
	}

}
