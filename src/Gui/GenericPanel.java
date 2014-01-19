package Gui;

import javax.swing.JPanel;

public class GenericPanel<T> extends JPanel {

	protected final T parent;
	GenericPanel(T parent) {
		this.parent = parent;
	}
}
