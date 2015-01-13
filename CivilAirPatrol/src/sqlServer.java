import java.sql.*;
import java.util.Date;

public class sqlServer {
	private static Connection c = null;
	private static Statement stmt = null;

	// creates the database
	public static void createDatabase() {
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		System.out.println("Opened database successfully");
	}

	// gets the name out of the JSON String that was sent to the database
	private static String getName(String newEntry) {
		String name;
		int i = 2;
		while (newEntry.charAt(i) != '"') {
			i++;
		}
		name = newEntry.substring(2, i);
		return name;
	}

	// adds a new entry to a table or if a table with that name does not exist,
	// it will be created
	public static void addEntry(String newEntry) {
		System.out.println("adding " + newEntry + " to table "
				+ getName(newEntry));
		String tableName = getName(newEntry);
		try {
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			stmt = c.createStatement();

			String sql = null;

			// create the table if it doesn't exist
			sql = "CREATE TABLE IF NOT EXISTS " + tableName
					+ "(NAME           TEXT    NOT NULL, "
					+ " JSON           TEXT    NOT NULL, "
					+ " UPDATED        LONG    NOT NULL)";
			stmt.executeUpdate(sql);
			System.out.println("table " + tableName + " created");

			// get the time that the object was added to the database
			Long currentTime = new Date().getTime();

			// inserts the String and time into the database
			sql = "INSERT INTO " + tableName + " (NAME,JSON,UPDATED) "
					+ "VALUES('" + tableName + "', '" + newEntry + "', "
					+ currentTime + ")";

			stmt.executeUpdate(sql);

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}

	}

	// gets all the entries out of the selected table
	// I wasn't sure how to return multiple strings with one function
	// so it returns a result set that can be processed client side
	public static ResultSet returnTable(String tableName) {
		ResultSet rs = null;
		try {
			stmt = c.createStatement();

			rs = stmt.executeQuery("SELECT * FROM " + tableName + ";");

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return rs;
	}

	// deletes a table, really only needed for testing purposes shouldn't be
	// kept in final implimentation
	public static void deleteTable(String newEntry) {

		String tableName = getName(newEntry);
		System.out.println("deleting table " + tableName);
		try {
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			stmt = c.createStatement();

			String sql = "DROP TABLE IF EXISTS " + tableName;
			stmt.executeUpdate(sql);

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
}