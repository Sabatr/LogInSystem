package viewer;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class LogInPanel {
	private JPanel panel = new JPanel();
	private TextFieldPanel textFieldPanel;
	private static final int numberOfFields = 2;
	
	public JPanel setUpLogInPanel() {
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		//panel.setLayout(new BorderLayout());
		ButtonPanel buttonPanel = new ButtonPanel();
		 textFieldPanel = new TextFieldPanel(numberOfFields,new String[] {
				"User","Password"
		});
		
		//connects the button listener to the textfield 
		buttonPanel.getTextFieldPanel(textFieldPanel);
		//panel.add(new JLabel("Authorization System"));
		panel.add(Box.createRigidArea(new Dimension(100,100)));
		panel.add(textFieldPanel);
		panel.add(Box.createRigidArea(new Dimension(100,20)));
		panel.add(buttonPanel);
		return panel;
	}
	
	public static void checkLogIn(TextFieldPanel panel) {
		if (StateSource.getstate() == StateSource.getPrevState()) {
			if (panel.returnText().size() != numberOfFields) {
				JOptionPane.showMessageDialog(panel, "Please fill in the fields.");
			} else {
				JOptionPane.showMessageDialog(panel, "You have logged in");
			}
		}
	}
}
