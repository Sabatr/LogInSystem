package accountApp;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
/**
 * This class allows us to add multiple JTextFields as a single component
 * @author Brian Nguyen
 *
 */
public class TextFieldPanel extends JPanel {
	//To keep track of the textFields we store them into a list.
	private List<JTextField> textFieldList = new ArrayList<JTextField>();
	/**
	 * On construction we add the textfields to the panel
	 */
	public TextFieldPanel(int numberOfFields,String[] labels) {
		if (numberOfFields != labels.length) {
			throw new NullPointerException("Must have a label for each TextField.");
		} else {
			this.setLayout(new GridLayout(numberOfFields,numberOfFields,-100,20));
			for (int i =0;i<numberOfFields;i++) {
				this.add(new JLabel(labels[i]));
				this.add(createTextField(10));
			}
		}
	}
	/**
	 * This creates a JTextField on a desired width
	 * @param width in pixels
	 * @return
	 */
	public Component createTextField(int width) {
		JTextField field = new JTextField(width);
		field.setPreferredSize(new Dimension(width,30));
		field.requestFocus();
		textFieldList.add(field);
		return field;
	}
	/**
	 * This returns the text that is entered in the textfield.
	 */
	public List<String> returnText() {
		List<String> textList = new ArrayList<String>();
		for (JTextField field: textFieldList) {
				if (!(field.getText().equals(""))) {
					textList.add(field.getText());
				}	
		}
		return textList;
	}
	
	
}
