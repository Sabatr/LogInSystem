package accountApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
/**
 * A link that is established between a local database and the application. 
 * The application will call methods within the Connector class to 
 * store and retrieve data from the database.
 * @author Brian Nguyen
 *
 */
public class Connector {
	private Connection connection;
	private static Connector connector = new Connector();
	//We only want one connector class. Singleton pattern is used. 
	private Connector() {
		getConnection();
	}
	 /**
	  *Retrieving the single instance for classes to use. 
	  */
	public static Connector getConnector() {
		return connector;
	}
	
	/**
	 * Get the connection from the local host. 
	 * NOTE: the "root" password is not the actual password. For privacy sakes it is left empty. 
	 */
	private void getConnection() {
		try {
			 connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSL=false", "root", "");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Retrieves information from the database.
	 */
	public HashMap<String,String> getData() {
		HashMap<String,String> userAndPassMap = new HashMap<String,String>();
		try {
			Statement statement = connection.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM userinfo");
			while (results.next()) {
				userAndPassMap.put(results.getString("username").toLowerCase(), results.getString("password"));
			}
			return userAndPassMap;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
			
		}
	}
	/**
	 * This method stores the information entered by the user into the database. 
	 * @param informationList - a list of String that contains the user's entered information.
	 */
	public void storeData(List<String> informationList) {
		try {
			String enteredStatement = "(";
			Statement statement = connection.createStatement();
			for (int i =0;i<informationList.size()-1;i++) {
				//concatenate the string to form a sql statement.
				enteredStatement= enteredStatement + "'" + informationList.get(i) +"'" + ", ";
			}
			enteredStatement+= "NULL);";
			//enteredStatement should be in the form ('<fullname>', '<username>', '<password>',id);
			statement.executeUpdate("INSERT INTO userinfo VALUE" + enteredStatement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
