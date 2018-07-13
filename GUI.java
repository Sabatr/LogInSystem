package viewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI implements Observer {
	private Observable observable = null;
	private JFrame frame = new JFrame();
	/**
	 * On construction the GUI is set up.
	 */
	public GUI(Observable observable) {
		this.createGUI();
		this.observable = observable;
		this.observable.addObserver(this);
	}
	/**
	 * This sets up the components of the GUI.
	 */
	private void createGUI() {	
		frame.getContentPane().add(BorderLayout.CENTER,new DisplayPanel());
		//set up JFrame with necessary methods.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Authorization System");
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}
	public void update() {
			frame.getContentPane().removeAll();
			frame.getContentPane().add(BorderLayout.CENTER,new DisplayPanel());
			frame.validate();
			frame.repaint();
		}
}
