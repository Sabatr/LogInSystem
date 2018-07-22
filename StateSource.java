package accountApp;

import java.util.ArrayList;
import java.util.List;
/**
 * This class makes sure that the program is aware when
 * the state is updated and keeps track of what it is 
 * updated to.
 * @author Brian Nguyen
 *
 */
public class StateSource implements Observable {
	private static List<Observer> observers = new ArrayList<Observer>();
	public enum ButtonState {
	LOG,CREATE;
		}
	private static ButtonState currentButtonState = ButtonState.LOG;
	private static ButtonState previousButtonState = ButtonState.LOG;
	/**
	 * The button state is set depending on the button that is clicked.
	 * @param state
	 */
	public static void setState(ButtonState state) {
		previousButtonState = currentButtonState;
		currentButtonState = state;
		//whenever a state changes the observers are notified.
		if (currentButtonState != previousButtonState) {
			notifyObservers();
		}
	}
	/**
	 * @return the current button state.
	 */
	public static ButtonState getstate() {
		return currentButtonState;
	}
	/**
	 * @return the previous button state.
	 */
	public static ButtonState getPrevState() {
		return previousButtonState;
	}
	/**
	 * Adds an observer to the observers list.
	 */
	public void addObserver(Observer o) {
		observers.add(o);
	}
	/**
	 * Removes an observer from the observer list.
	 */
	public void removeObserver(Observer o) {
		observers.remove(o);
	}
	/**
	 * Calls the update() methods for every observer. 
	 */
	public static void notifyObservers() {
		List<Observer> newObserver = new ArrayList<Observer>(observers);
		for (Observer observer: newObserver) {
			observer.update();
		}
	}
}
