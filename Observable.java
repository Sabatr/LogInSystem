package accountApp;
/**
 * Interface which contains the necessary observable methods.
 * @author Brian Nguyen.
 *
 */
public interface Observable {
	public void addObserver(Observer o);
	public void removeObserver(Observer o);

}
