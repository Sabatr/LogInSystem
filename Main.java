package accountApp;

import javax.swing.SwingUtilities;

/**
 * This application is a personal project which tries to mimic an account authorization system.
 * This uses a simple GUI which connects to a data base and does simple user password protection.
 * @author Brian Nguyen
 *
 */
public class Main {
	public static void main(String[] args) {
		//This runs the program.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GUI(new StateSource());
			}
		});
	}

}
