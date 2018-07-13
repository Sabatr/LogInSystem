package viewer;

import java.awt.Dimension;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CreateAccountPanel {
	private enum typesOfErrors {
		invalidName,invalidUser,passwordMismatch,passTooLong,passTooShort,noError;
	}
	JPanel panel = new JPanel();
	private static final int numberOfFields = 4;
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
	
	public static void checkCreate(TextFieldPanel panel) {
		if (StateSource.getstate() == StateSource.getPrevState()) {
			if (panel.returnText().size() != numberOfFields) {
				JOptionPane.showMessageDialog(panel, "Please fill in the fields.");
			} else {
				switch(checkEnteredDetails(panel.returnText())) {
				case noError:
					JOptionPane.showMessageDialog(panel, "Success! Account has been created!");
					break;
				case passwordMismatch:
					JOptionPane.showMessageDialog(panel, "ERROR:Passwords do not match.");
					break;
				case passTooLong:
					JOptionPane.showMessageDialog(panel, "ERROR:Password too long.");
					break;
				case passTooShort:
					JOptionPane.showMessageDialog(panel, "ERROR:Password too short");
					break;
				case invalidName:
					JOptionPane.showMessageDialog(panel, "ERROR:Invalid characters in name.");
					break;
				}

			}
		}
	}
	
	private static typesOfErrors checkEnteredDetails(List<String> enteredDetails) {
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

		return typesOfErrors.noError;
	}
	
	private static boolean containsIllegals(String toExamine) {
	    String[] arr = toExamine.split("[\\d+.,~!#@*+%{}<>/\'\"\\\\]", 2);
	    return arr.length > 1;
	}
}
