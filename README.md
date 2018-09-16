<h1> LogInSystem </h1>
<p>A personal project which involves a create/log in user system.
A local database is connected to the application. Whenever someone enters appropriate creation account details it is stored
in the database. If they log in with valid information, they will prompted with a successful log in. The application checks this 
by retrieving the data from the database and comparing entered information with the information from the database. </p>

<h4> A preview of the simple gui </h4>
<img src="https://i.imgur.com/r8a7rBk.png" />

<h3> How the connector works </h3>
<p> A singleton was used to establish the connection between classes. This is because there is only one connection and
we do not need to instantiate any more. This also helps the other classes retreive information from the database. </p>
<p> Some code which follows the singleton pattern. </p>

```
	private Connection connection;
	private static Connector connector = new Connector(); 
	private Connector() {
		getConnection();
	}
	public static Connector getConnector() {
		return connector;
	}
```

<p>Here is where we establish the connection to the database server. In my case, the server is hosted locally. <br>
Note: the username and password are not the real ones :)</p>

```
	private void getConnection() {
		try {
			 connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSL=false", "someusername", "somepassword");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
```

<p> For the other classes to access the connection, we simply create a connector object</p>

```
    Connector connector = Connector.getConnector();
```
<p> We then can choose if the connector needs to store data or retreive it. </p>
<p> Example of storing information. </p>

```
    connector.storeData(panel.returnText());
```

<p> Example of retreiving information.  </p>

```
    Connector connector = Connector.getConnector();
    HashMap<String,String> databaseInfo = connector.getData();
```

<h5> Below are the methods for the getData() and the storeData() methods </h5>
<p> Essentially, the data is retreived and stored in a hashmap. This is because the table in the database only contains two columns.</p>

```
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
```

<p>This method stores the information entered by the user into the database. </p>

```
	public void storeData(List<String> informationList) {
		try {
			String enteredStatement = "(";
			Statement statement = connection.createStatement();
			for (int i =0;i<informationList.size()-1;i++) {
				enteredStatement= enteredStatement + "'" + informationList.get(i) +"'" + ", ";
			}
			enteredStatement+= "NULL);";
			statement.executeUpdate("INSERT INTO userinfo VALUE" + enteredStatement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
```
