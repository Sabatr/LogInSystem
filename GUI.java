package accountApp;

import java.awt.BorderLayout;
import javax.swing.JFrame;

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
	/**
	 * Updates the GUI by removing all current components in the JFrame
	 * then adds a new display panel.
	 */
	public void update() {
			frame.getContentPane().removeAll();
			//The display panel determines which panel is actually displayed.
			frame.getContentPane().add(BorderLayout.CENTER,new DisplayPanel());
			//these methods are needed to add the components to the JFrame.
			frame.validate();
			frame.repaint();
		}
}
