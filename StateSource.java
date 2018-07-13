package viewer;

import java.util.ArrayList;
import java.util.List;

public class StateSource implements Observable {
	private static List<Observer> observers = new ArrayList<Observer>();
	public enum ButtonState {
	LOG,CREATE;
		}
	private static ButtonState currentButtonState = ButtonState.LOG;
	private static ButtonState previousButtonState = ButtonState.LOG;
	public static void setState(ButtonState state) {
		previousButtonState = currentButtonState;
		currentButtonState = state;
		if (currentButtonState != previousButtonState) {
			notifyObservers();
		}
	}
	
	public static ButtonState getstate() {
		return currentButtonState;
	}
	
	public static ButtonState getPrevState() {
		return previousButtonState;
	}
	
	public void addObserver(Observer o) {
		observers.add(o);
	}
	
	public void removeObserver(Observer o) {
		observers.remove(o);
	}
	
	public static void notifyObservers() {
		List<Observer> newObserver = new ArrayList<Observer>(observers);
		for (Observer observer: newObserver) {
			observer.update();
		}

		
	}
}
