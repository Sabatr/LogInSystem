package accountApp;

import java.awt.Component;
import javax.swing.JPanel;

/**
 * This class displays what is on the GUI.
 * @author Brian Nguyen
 *
 */
@SuppressWarnings("serial")
public class DisplayPanel extends JPanel  {
	
	JPanel panel = new JPanel();
	/**
	 * On construction the JPanel is created
	 */
	public DisplayPanel() {	
		this.add(create());
	}
	/**
	 * Depending on the state, the JPanel is created.
	 * @return
	 */
	public Component create() {
		switch(StateSource.getstate()) {
		case LOG:
			panel = new LogInPanel().setUpLogInPanel();
			break;
		case CREATE:
			panel = new CreateAccountPanel().setUpCreateAccountPanel();
			break;
		}
		return panel;
	}
	}


