package viewer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import viewer.StateSource.ButtonState;
/**
 * This class allows us to add multiple buttons as a single component
 * when adding it to the JFrame.
 * @author Brian Nguyen
 *
 */
public class ButtonPanel extends JPanel{
	//Constants are create for the labels of the buttons.
	private final String LOG = "Log in";
	private final String CREATE_ACCOUNT = "Create account";
	private TextFieldPanel textFieldPanel;
	/**
	 * On Consturction the buttons are added to the panel.
	 */
	public ButtonPanel() {
		
		this.add(createButton(100,30,LOG));
		this.add(createButton(200,30,CREATE_ACCOUNT));

	}
	
	/**
	 * This method returns a JButton component with our desired parameters.
	 * @param width: the width of the button in pixels
	 * @param height: the height of the button in pixels
	 * @param text: the string displayed on the button
	 * @return Component, the button itself.
	 */
	public Component createButton(int width,int height,String text) {
		JButton button = new JButton(text);
		button.setPreferredSize(new Dimension(width,height));
		button.addActionListener(new ActionAdapter() {
			//this listens to whenever a button is pressed
			public void actionPerformed(ActionEvent event) {
				switch(button.getText()) {		
					case LOG:
						StateSource.setState(ButtonState.LOG);
						LogInPanel.checkLogIn(textFieldPanel);
						break;
					case CREATE_ACCOUNT:
						StateSource.setState(ButtonState.CREATE);
						CreateAccountPanel.checkCreate(textFieldPanel);
						break;
					}
				}
		});
		return button;
	}
	
	/**
	 * Gets the textpanel instances.
	 */
	public void getTextFieldPanel(TextFieldPanel panel) {
		if (panel == null) {
			throw new NullPointerException("TextFieldPanel cannot be null. ");
		} else {
			textFieldPanel = panel;
		}
	}

}
