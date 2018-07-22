package accountApp;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * This class sets up the log in panels.
 * @author Brian Nguyen
 *
 */
public class LogInPanel {
	private JPanel panel = new JPanel();
	private TextFieldPanel textFieldPanel;
	private static final int numberOfFields = 2;
	//states of the log in errors.
	private enum logInErrors {
		IncorrectUserOrPass,NoError;
	}
	/**
	 * @return JPanel containing the necessary components.
	 */
	public JPanel setUpLogInPanel() {
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		//panel.setLayout(new BorderLayout());
		ButtonPanel buttonPanel = new ButtonPanel();
		 textFieldPanel = new TextFieldPanel(numberOfFields,new String[] {
				"User","Password"
		});
		
		//connects the button listener to the textfield 
		buttonPanel.getTextFieldPanel(textFieldPanel);
		panel.add(Box.createRigidArea(new Dimension(100,100)));
		panel.add(textFieldPanel);
		panel.add(Box.createRigidArea(new Dimension(100,20)));
		panel.add(buttonPanel);
		return panel;
	}
	/**
	 * This method displays option panes depending on the outcome of the log in attempt.
	 * @param panel, which contains the text values.
	 */
	public static void checkLogIn(TextFieldPanel panel) {
		if (StateSource.getstate() == StateSource.getPrevState()) {
			if (panel.returnText().size() != numberOfFields) {
				JOptionPane.showMessageDialog(panel, "Please fill in the fields.");
			} else {
				switch(checkLogInDetails(panel.returnText())) {
				case IncorrectUserOrPass:
					JOptionPane.showMessageDialog(panel, "Incorrect Details Entered.");
					break;
				case NoError:
					JOptionPane.showMessageDialog(panel, "You have logged in");
					break;
				}
				
			}
		}
	}
	/**
	 * This methods checks if the log in details are valid.
	 * They are valid if the user name exists and that the entered
	 * password correlates to the user name.
	 * @param logInDetails contains all of the entered information by the user.
	 * @return the state of the error.
	 */
	private static logInErrors checkLogInDetails(List<String> logInDetails) {
		Connector connector = Connector.getConnector();
		HashMap<String,String> databaseInfo = connector.getData();
		if (!databaseInfo.containsKey(logInDetails.get(0).toLowerCase())) {
			return logInErrors.IncorrectUserOrPass;
		} else {
			String pass = databaseInfo.get(logInDetails.get(0));
			if (!pass.equals(logInDetails.get(1))) {
				return logInErrors.IncorrectUserOrPass;
			}
		}
		return logInErrors.NoError;
	}
}
