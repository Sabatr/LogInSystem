package viewer;

import java.awt.Component;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class DisplayPanel extends JPanel  {
	
	JPanel panel = new JPanel();
	public DisplayPanel() {	
		this.add(create());
	}
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


