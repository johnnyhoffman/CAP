package database;

import java.sql.*;
import java.util.Date;

public class sqlServer {
	private static Connection c = null;
	//private static Statement stmt = null;

        
	// creates the database
	public static void createDatabase() {
		//Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db"); //create DB if it does not exist, otherwise get connection
                        Statement stmt = c.createStatement();
                        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS MISSION "
                                        + "(MISSIONNUMBER INT PRIMARY KEY NOT NULL,"
                                        +  "MISSIONNAME   TEXT            NOT NULL)");//create the Mission table
                        
                        System.out.println("Created Mission table successfully.");
                        
                        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS COMMLOG"     //create the commlog table
                                        + "(COMMID        INT PRIMARY KEY NOT NULL,"
                                        +  "MISSIONNUMBER INT             NOT NULL,"
                                        +  "DATE          TEXT            NOT NULL,"
                                        +  "JSONDATA      TEXT            NOT NULL)");
                        
                        System.out.println("Created commlog table successfully.");
                        
                        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS SAR"     //create the saR table
                                        + "(SARID         INT PRIMARY KEY NOT NULL,"
                                        +  "MISSIONNUMBER INT             NOT NULL,"
                                        +  "DATE          TEXT            NOT NULL,"
                                        +  "JSONDATA      TEXT            NOT NULL)");
                        
                        System.out.println("Created SAR table successfully.");
                        
                        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS RADIOMESS"     //create the commlog table
                                        + "(RADID        INT PRIMARY KEY  NOT NULL,"
                                        +  "MISSIONNUMBER INT             NOT NULL,"
                                        +  "DATE          TEXT            NOT NULL,"
                                        +  "JSONDATA      TEXT            NOT NULL)");
                        
                        System.out.println("Created radiomess table successfully.");
                                                
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		System.out.println("Opened database successfully");
                
	}

        public static void testInsertMission(String name, int num){
            try{
                PreparedStatement stmt = c.prepareStatement("INSERT into MISSION (MISSIONNUMBER, MISSIONNAME) " 
                                 + "VALUES(?,?)");
                
                stmt.setInt(1,num);
                stmt.setString(2,name);
                stmt.execute();
            }catch(Exception e){
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
            
        }
        public static void testQueryMission(String name, int num){
            try{
                ResultSet result;
                PreparedStatement stmt = c.prepareStatement("SELECT * FROM MISSION WHERE MISSIONNUMBER = ?");
                stmt.setInt(1, num);
                
                result = stmt.executeQuery();
                if (result.getString("MISSIONNAME").equals(name))
                    System.out.println("Result = " + result.getString("MISSIONNAME"));
                else System.out.println("Query failed.");
            }catch(Exception e){
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }
        public static void testClearMission(){
            try{
                PreparedStatement stmt = c.prepareStatement("DELETE FROM MISSION");
                stmt.execute();
                System.out.println("MISSION CLEARED");
            }catch(Exception e){
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }
        
        /* ---------------------------THE FOLLOWING ARE INSERTS----------------------------- */        
        //when inserting a Form, need to make sure that a mission exists for the form 
        
        public static void insertCommLog(String json, int commid, int missionnum, String date){
            try{
                PreparedStatement stmt = c.prepareStatement("INSERT into COMMLOG (COMMID,MISSIONNUM,DATE,JSONDATA) "
                                                           + "VALUES(?,?,?,?)");
                stmt.setInt(1, commid);
                stmt.setInt(2, missionnum);
                stmt.setString(3, date);
                stmt.setString(4, json);
                stmt.execute();
            }catch(Exception e){
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }
        
        public static void insertSAR(String json, int sarid, int missionnum, String date){
            try{
                PreparedStatement stmt = c.prepareStatement("INSERT into SAR (SARID,MISSIONNUM,DATE,JSONDATA) "
                                                           + "VALUES(?,?,?,?)");
                stmt.setInt(1, sarid);
                stmt.setInt(2, missionnum);
                stmt.setString(3, date);
                stmt.setString(4, json);
                stmt.execute();
            }catch(Exception e){
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }
        
        public static void insertRADIOMESS(String json, int radid, int missionnum, String date){
            try{
                PreparedStatement stmt = c.prepareStatement("INSERT into RADIOMESS (RADID,MISSIONNUM,DATE,JSONDATA) "
                                                           + "VALUES(?,?,?,?)");
                stmt.setInt(1, radid);
                stmt.setInt(2, missionnum);
                stmt.setString(3, date);
                stmt.setString(4, json);
                stmt.execute();
            }catch(Exception e){
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }
        
        public static void InsertMission(String name, int num){
            try{
                PreparedStatement stmt = c.prepareStatement("INSERT into MISSION (MISSIONNUMBER, MISSIONNAME) " 
                                 + "VALUES(?,?)");                
                stmt.setInt(1,num);
                stmt.setString(2,name);
                stmt.execute();
            }catch(Exception e){
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
            
        }
        /* ----------------------------------------------------------------------------------------- */
        
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
			//c = DriverManager.getConnection("jdbc:sqlite:test.db");
			Statement stmt = c.createStatement();

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
			Statement stmt = c.createStatement();

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
			//c = DriverManager.getConnection("jdbc:sqlite:test.db");
			Statement stmt = c.createStatement();

			String sql = "DROP TABLE IF EXISTS " + tableName;
			stmt.executeUpdate(sql);

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
}