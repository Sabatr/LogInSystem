package accountApp;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import accountApp.StateSource.ButtonState;
/**
 * This class sets up the JPanel for account creation. 
 * @author Brian Nguyen
 *
 */
public class CreateAccountPanel {
	//states of errors.
	private enum typesOfErrors {
		invalidName,invalidUser,passwordMismatch,passTooLong,passTooShort,userExists,noError;
	}
	JPanel panel = new JPanel();
	private static final int numberOfFields = 4;
	/**
	 * @return JPanel containing the necessary components.
	 */
	public JPanel setUpCreateAccountPanel() {
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		//panel.setLayout(new BorderLayout());
		ButtonPanel buttonPanel = new ButtonPanel();
		TextFieldPanel textFieldPanel = new TextFieldPanel(numberOfFields,new String[] {
				"FullName","Username","Password","Confirm Password"
		});
		
		//connects the button listener to the textfield 
		buttonPanel.getTextFieldPanel(textFieldPanel);
		panel.add(Box.createRigidArea(new Dimension(100,60)));
		panel.add(textFieldPanel);
		panel.add(Box.createRigidArea(new Dimension(100,20)));
		panel.add(buttonPanel);
		return panel;
	}
	/**
	 * This method displays option panes depending on the outcome of the account creation.
	 * @param panel, which contains the text values.
	 */
	public static void checkCreate(TextFieldPanel panel) {
		if (StateSource.getstate() == StateSource.getPrevState()) {
			if (panel.returnText().size() != numberOfFields) {
				JOptionPane.showMessageDialog(panel, "Please fill in the fields.");
			} else {
				switch(checkEnteredCreateDetails(panel.returnText())) {
				case noError:
					//on successful creation the user information is stored.
					Connector connector = Connector.getConnector();
					connector.storeData(panel.returnText());
					JOptionPane.showMessageDialog(panel, "Success! Account has been created!");
					//After successful account creation, the gui switches to the log in screen.
					StateSource.setState(ButtonState.LOG);
					break;
				case passwordMismatch:
					JOptionPane.showMessageDialog(panel, "ERROR: Passwords do not match.");
					break;
				case passTooLong:
					JOptionPane.showMessageDialog(panel, "ERROR: Password too long.");
					break;
				case passTooShort:
					JOptionPane.showMessageDialog(panel, "ERROR: Password too short");
					break;
				case invalidName:
					JOptionPane.showMessageDialog(panel, "ERROR: Invalid characters in name.");
					break;
				case userExists:
					JOptionPane.showMessageDialog(panel, "ERROR: User is taken!");
					break;
				}

			}
		}
	}
	
	/**
	 * This method checks whether or not the details entered during the creation process
	 * can be done.
	 * @param enteredDetails contains all of the entered information by the user.
	 * @return the state of the error.
	 */
	private static typesOfErrors checkEnteredCreateDetails(List<String> enteredDetails) {
		if (containsIllegals(enteredDetails.get(0))) {
			return typesOfErrors.invalidName;
		}
		if (enteredDetails.get(2).length() >= 16) {
			return typesOfErrors.passTooLong;
		} else if (enteredDetails.get(2).length() <= 5) {
			return typesOfErrors.passTooShort;
		}
		if (!enteredDetails.get(numberOfFields-2).equals(enteredDetails.get(numberOfFields-1))) {
			return typesOfErrors.passwordMismatch;
		}
		
		if (checkExists(enteredDetails.get(1))) {
			return typesOfErrors.userExists;
		}
		return typesOfErrors.noError;
	}
	
	/** 
	 * Checks if a string contains unwanted characters
	 * @param toExamine
	 * @return
	 */
	private static boolean containsIllegals(String toExamine) {
	    String[] arr = toExamine.split("[\\d+.,~!#@*+%{}<>/\'\"\\\\]", 2);
	    return arr.length > 1;
	}
	
	/**
	 * Checks if the entered username already exists in the database.
	 * @param username
	 * @return
	 */
	private static boolean checkExists(String username) {
		Connector connector = Connector.getConnector();
		HashMap<String,String> databaseInfo = connector.getData();
		if (databaseInfo.containsKey(username.toLowerCase())) {
			return true;
		} else {
			return false;
		}
	}
}
