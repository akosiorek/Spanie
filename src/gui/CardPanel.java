package gui;

import java.awt.CardLayout;

import javax.swing.JPanel;

public class CardPanel<T> extends JPanel {

	protected T parent;
	protected CardLayout layout;
	
	CardPanel(T parent) {
		
		this.parent = parent;
		layout = new CardLayout();
		setLayout(layout);		
	}
	
	
	public void wyswietl(String str) {
		layout.show(this, str);		
	}
}
