package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.DBPushParams;
import common.User;
import forms.ScheduledPushModelAbstraction.FormType;
import network.UserType;

public class sqlServer {
    private static Connection c = null;

    // private static Statement stmt = null;

    // creates the database
    public static void CreateDatabase() {
        // Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:CAPFormTracker.db"); // create
                                                                              // DB
            // if it
            // does not
            // exist,
            // otherwise
            // get
            // connection
            Statement stmt = c.createStatement();
            // stmt.executeUpdate("CREATE TABLE IF NOT EXISTS MISSION "
            // + "(MISSIONNUMBER INT PRIMARY KEY NOT NULL,"
            // + "MISSIONNAME   TEXT            NOT NULL)");//create the Mission
            // table

            // System.out.println("Created Mission table successfully.");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS COMMLOG" // create
                                                                    // the
                                                                    // commlog
                                                                    // table
                    + "(COMMID        INT PRIMARY KEY NOT NULL,"
                    + "MISSIONNUMBER TEXT             NOT NULL,"
                    + "DATE          INT            NOT NULL,"
                    + "JSONDATA      TEXT            NOT NULL)");

            System.out.println("Created commlog table successfully.");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS SAR" // create the
                                                                // saR table
                    + "(SARID         INT PRIMARY KEY NOT NULL,"
                    + "MISSIONNUMBER TEXT             NOT NULL,"
                    + "DATE          INT            NOT NULL,"
                    + "JSONDATA      TEXT            NOT NULL)");

            System.out.println("Created SAR table successfully.");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS RADIOMESS" // create
                                                                      // the
                                                                      // commlog
                                                                      // table
                    + "(RADID        INT PRIMARY KEY  NOT NULL,"
                    + "MISSIONNUMBER TEXT             NOT NULL,"
                    + "DATE          INT            NOT NULL,"
                    + "JSONDATA      TEXT            NOT NULL)");

            System.out.println("Created radiomess table successfully.");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS USERS" // create
                                                                      // the
                                                                      // users
                                                                      // table
                    + "(USERNAME     TEXT PRIMARY KEY  NOT NULL,"
                    + "PASSWORD      TEXT              NOT NULL,"
                    + "USERTYPE      TEXT              NOT NULL)");

            System.out.println("Created users table successfully.");
            
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS GLOBALS"
                    + "(TYPE          TEXT PRIMARY KEY NOT NULL,"
                    + "ID            INT              NOT NULL)");
            setUpGlobals();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Opened database successfully");

    }

    /*
     * this sets up the globals table with initial values, currently only a
     * uniqueid to generate uniquee form ids
     */
    private static void setUpGlobals() {
        // TODO make sure that this only runs once, IE when the table is first
        // created upon intitial DB creation.
        // can query, see if result set is empty, if empty run, else do nothing
        try {
            PreparedStatement stmt = c
                    .prepareStatement("INSERT into GLOBALS(TYPE, ID) VALUES(?,?)");
            stmt.setString(1, "UNIQUEID");
            stmt.setInt(2, 1);
            stmt.execute();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    /*
     * this will retrieve the next id to be used as a unique identifier for
     * forms
     */
    public static int RetrieveNextFormId() {
        ResultSet result;
        int unqid;
        try {
            // c.setAutoCommit(false);
            PreparedStatement stmt = c
                    .prepareStatement("SELECT * from GLOBALS WHERE TYPE = ?");
            stmt.setString(1, "UNIQUEID");
            result = stmt.executeQuery();

            if (result.next()) {
                unqid = result.getInt("ID");
                stmt = c.prepareStatement("UPDATE GLOBALS set ID = ? WHERE TYPE = ?");
                stmt.setInt(1, unqid + 1);
                stmt.setString(2, "UNIQUEID");
                stmt.executeUpdate();
                // c.commit();
                return unqid;
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return 0;
    }

    public static void testClearSAR() {
        try {
            PreparedStatement stmt = c.prepareStatement("DELETE FROM SAR");
            stmt.execute();
            System.out.println("SAR CLEARED");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public static void testClearRAD() {
        try {
            PreparedStatement stmt = c
                    .prepareStatement("DELETE FROM RADIOMESS");
            stmt.execute();
            System.out.println("RAD CLEARED");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public static void testClearCommLog() {
        try {
            PreparedStatement stmt = c.prepareStatement("DELETE FROM COMMLOG");
            stmt.execute();
            System.out.println("COMMLOG CLEARED");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    /*
     * ---------------------------THE FOLLOWING ARE
     * INSERTS-----------------------------
     */
    // when inserting a Form, need to make sure that a mission exists for the
    // form
    public static void InsertUser(String userName, String password, String userType){
        try {
            PreparedStatement stmt = c
                    .prepareStatement("INSERT into USERS (USERNAME,PASSWORD,USERTYPE) "
                            + "VALUES(?,?,?)");
            stmt.setString(1, userName);
            stmt.setString(2, password);
            stmt.setString(3, userType);
            stmt.execute();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    
    public static void InsertCommLog(String json, int commid,
            String missionnum, long date) {
        try {
            PreparedStatement stmt = c
                    .prepareStatement("INSERT into COMMLOG (COMMID,MISSIONNUMBER,DATE,JSONDATA) "
                            + "VALUES(?,?,?,?)");
            stmt.setInt(1, commid);
            stmt.setString(2, missionnum);
            stmt.setLong(3, date);
            stmt.setString(4, json);
            stmt.execute();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public static void InsertSAR(String json, int sarid, String missionnum,
            long date) {
        try {
            PreparedStatement stmt = c
                    .prepareStatement("INSERT into SAR (SARID,MISSIONNUMBER,DATE,JSONDATA) "
                            + "VALUES(?,?,?,?)");
            stmt.setInt(1, sarid);
            stmt.setString(2, missionnum);
            stmt.setLong(3, date);
            stmt.setString(4, json);
            stmt.execute();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public static void InsertRADIOMESS(String json, int radid,
            String missionnum, long date) {
        try {
            PreparedStatement stmt = c
                    .prepareStatement("INSERT into RADIOMESS (RADID,MISSIONNUMBER,DATE,JSONDATA) "
                            + "VALUES(?,?,?,?)");
            stmt.setInt(1, radid);
            stmt.setString(2, missionnum);
            stmt.setLong(3, date);
            stmt.setString(4, json);
            stmt.execute();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    

    /*
     * --------------------------------------------------------------------------
     * ---------------
     */
    /* The following are selects */

    public static User SelectFromUsersWithUserName(String userName){
        try {
            ResultSet resultSet;
            PreparedStatement stmt = c
                    .prepareStatement("SELECT * FROM USERS WHERE USERNAME = ?");
            stmt.setString(1, userName);
            resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return new User(
                        resultSet.getString("USERNAME"),
                        resultSet.getString("PASSWORD"),
                        UserType.getType(resultSet.getString("USERTYPE")));
            } else {
                System.err.println("No value found for userName " + userName);
                return null;
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
                
    }
    
    /* for querying comlog table with just uid */
    public static DBPushParams SelectFromCommLogWithID(int id) {
        try {
            ResultSet resultSet;
            PreparedStatement stmt = c
                    .prepareStatement("SELECT * FROM COMMLOG WHERE COMMID = ?");
            stmt.setInt(1, id);
            resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return new DBPushParams(FormType.CL,
                        resultSet.getString("JSONDATA"),
                        resultSet.getInt("COMMID"),
                        resultSet.getString("MISSIONNUMBER"),
                        resultSet.getLong("DATE"));
            } else {
                System.err.println("No value found for id " + id);
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    /* for querying radio message table with just uid */
    public static DBPushParams SelectFromRADWithID(int id) {
        try {
            ResultSet resultSet;
            PreparedStatement stmt = c
                    .prepareStatement("SELECT * FROM RADIOMESS WHERE RADID = ?");
            stmt.setInt(1, id);
            resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return new DBPushParams(FormType.RM,
                        resultSet.getString("JSONDATA"),
                        resultSet.getInt("RADID"),
                        resultSet.getString("MISSIONNUMBER"),
                        resultSet.getLong("DATE"));
            } else {
                System.err.println("No value found for id " + id);
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    /* for querying sar table with just uid */
    public static DBPushParams SelectFromSARWithID(int id) {
        try {
            ResultSet resultSet;
            PreparedStatement stmt = c
                    .prepareStatement("SELECT * FROM SAR WHERE SARID = ?");
            stmt.setInt(1, id);
            resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return new DBPushParams(FormType.SAR,
                        resultSet.getString("JSONDATA"),
                        resultSet.getInt("SARID"),
                        resultSet.getString("MISSIONNUMBER"),
                        resultSet.getLong("DATE"));
            } else {
                System.err.println("No value found for id " + id);
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    /* for querying comlog table */
    public static List<DBPushParams> SelectFromCommLog(long date,
            String missionnum) {
        List<DBPushParams> results = new ArrayList<DBPushParams>();
        DBPushParams current;
        try {
            ResultSet result;
            PreparedStatement stmt = c
                    .prepareStatement("SELECT * FROM COMMLOG WHERE MISSIONNUMBER = ? AND DATE = ?");
            stmt.setString(1, missionnum);
            stmt.setLong(2, date);
            result = stmt.executeQuery();
            while (result.next()) {
                current = new DBPushParams(FormType.CL,
                        result.getString("JSONDATA"), result.getInt("COMMID"),
                        result.getString("MISSIONNUMBER"),
                        result.getLong("DATE"));
                results.add(current);
            }
            return results;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    /* for querying comlog table , all search criteria given */
    public static List<DBPushParams> SelectFromCommLogInitialSearch(
            long startdate, long enddate, String missionnum) {
        List<DBPushParams> results = new ArrayList<DBPushParams>();
        DBPushParams current;
        try {
            ResultSet result;
            PreparedStatement stmt = c
                    .prepareStatement("SELECT * FROM COMMLOG WHERE MISSIONNUMBER = ? AND DATE BETWEEN ?  AND ?");
            stmt.setString(1, missionnum);
            stmt.setLong(2, startdate);
            stmt.setLong(3, enddate);
            result = stmt.executeQuery();
            while (result.next()) {
                current = new DBPushParams(FormType.CL, null,
                        result.getInt("COMMID"),
                        result.getString("MISSIONNUMBER"),
                        result.getLong("DATE"));
                results.add(current);
            }
            return results;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    /* for querying comlog table , no missionno given */
    public static List<DBPushParams> SelectFromCommLogInitialSearch(
            long startdate, long enddate) {
        List<DBPushParams> results = new ArrayList<DBPushParams>();
        DBPushParams current;
        try {
            ResultSet result;
            PreparedStatement stmt = c
                    .prepareStatement("SELECT * FROM COMMLOG WHERE DATE BETWEEN ?  AND ?");
            stmt.setLong(1, startdate);
            stmt.setLong(2, enddate);
            result = stmt.executeQuery();
            while (result.next()) {
                current = new DBPushParams(FormType.CL, null,
                        result.getInt("COMMID"),
                        result.getString("MISSIONNUMBER"),
                        result.getLong("DATE"));
                results.add(current);
            }
            return results;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    public static List<DBPushParams> SelectFromCommLogWithDate(long date) {
        List<DBPushParams> results = new ArrayList<DBPushParams>();
        DBPushParams current;
        try {
            ResultSet result;
            PreparedStatement stmt = c
                    .prepareStatement("SELECT * FROM COMMLOG WHERE DATE = ?");
            stmt.setLong(1, date);
            result = stmt.executeQuery();
            while (result.next()) {
                current = new DBPushParams(FormType.CL,
                        result.getString("JSONDATA"), result.getInt("COMMID"),
                        result.getString("MISSIONNUMBER"),
                        result.getLong("DATE"));
                results.add(current);
            }
            return results;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    public static List<DBPushParams> SelectFromCommLogWithMissionNum(
            String missionnum) {
        List<DBPushParams> results = new ArrayList<DBPushParams>();
        DBPushParams current;
        try {
            ResultSet result;
            PreparedStatement stmt = c
                    .prepareStatement("SELECT * FROM COMMLOG WHERE MISSIONNUMBER = ?");
            stmt.setString(1, missionnum);
            result = stmt.executeQuery();
            while (result.next()) {
                current = new DBPushParams(FormType.CL,
                        result.getString("JSONDATA"), result.getInt("COMMID"),
                        result.getString("MISSIONNUMBER"),
                        result.getLong("DATE"));
                results.add(current);
            }
            return results;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    /* for querying sar table */
    /* for querying sar table , all search criteria given */
    public static List<DBPushParams> SelectFromSARInitialSearch(long startdate,
            long enddate, String missionnum) {
        List<DBPushParams> results = new ArrayList<DBPushParams>();
        DBPushParams current;
        try {
            ResultSet result;
            PreparedStatement stmt = c
                    .prepareStatement("SELECT * FROM SAR WHERE MISSIONNUMBER = ? AND DATE BETWEEN ?  AND ?");
            stmt.setString(1, missionnum);
            stmt.setLong(2, startdate);
            stmt.setLong(3, enddate);
            result = stmt.executeQuery();
            while (result.next()) {
                current = new DBPushParams(FormType.SAR, null,
                        result.getInt("SARID"),
                        result.getString("MISSIONNUMBER"),
                        result.getLong("DATE"));
                results.add(current);
            }
            return results;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    /* for querying sar table , no mission no given */
    public static List<DBPushParams> SelectFromSARInitialSearch(long startdate,
            long enddate) {
        List<DBPushParams> results = new ArrayList<DBPushParams>();
        DBPushParams current;
        try {
            ResultSet result;
            PreparedStatement stmt = c
                    .prepareStatement("SELECT * FROM SAR WHERE DATE BETWEEN ?  AND ?");

            stmt.setLong(1, startdate);
            stmt.setLong(2, enddate);
            result = stmt.executeQuery();
            while (result.next()) {
                current = new DBPushParams(FormType.SAR, null,
                        result.getInt("SARID"),
                        result.getString("MISSIONNUMBER"),
                        result.getLong("DATE"));
                results.add(current);
            }
            return results;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    public static List<DBPushParams> SelectFromSAR(long date, String missionnum) {
        List<DBPushParams> results = new ArrayList<DBPushParams>();
        DBPushParams current;
        try {
            ResultSet result;
            PreparedStatement stmt = c
                    .prepareStatement("SELECT * FROM SAR WHERE MISSIONNUMBER = ? AND DATE = ?");
            stmt.setString(1, missionnum);
            stmt.setLong(2, date);
            result = stmt.executeQuery();
            while (result.next()) {
                current = new DBPushParams(FormType.SAR,
                        result.getString("JSONDATA"), result.getInt("SARID"),
                        result.getString("MISSIONNUMBER"),
                        result.getLong("DATE"));
                results.add(current);
            }
            return results;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    public static List<DBPushParams> SelectFromSARWithDate(long date) {
        List<DBPushParams> results = new ArrayList<DBPushParams>();
        DBPushParams current;
        try {
            ResultSet result;
            PreparedStatement stmt = c
                    .prepareStatement("SELECT * FROM SAR WHERE DATE = ?");
            stmt.setLong(1, date);
            result = stmt.executeQuery();
            while (result.next()) {
                current = new DBPushParams(FormType.SAR,
                        result.getString("JSONDATA"), result.getInt("SARID"),
                        result.getString("MISSIONNUMBER"),
                        result.getLong("DATE"));
                results.add(current);
            }
            return results;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    public static List<DBPushParams> SelectFromSARWithMissionNum(
            String missionnum) {
        List<DBPushParams> results = new ArrayList<DBPushParams>();
        DBPushParams current;
        try {
            ResultSet result;
            PreparedStatement stmt = c
                    .prepareStatement("SELECT * FROM SAR WHERE MISSIONNUMBER = ?");
            stmt.setString(1, missionnum);
            result = stmt.executeQuery();
            while (result.next()) {
                current = new DBPushParams(FormType.SAR,
                        result.getString("JSONDATA"), result.getInt("SARID"),
                        result.getString("MISSIONNUMBER"),
                        result.getLong("DATE"));
                results.add(current);
            }
            return results;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    /* for querying sar table */
    /* for querying sar table , all search criteria given */
    public static List<DBPushParams> SelectFromRADInitialSearch(long startdate,
            long enddate, String missionnum) {
        List<DBPushParams> results = new ArrayList<DBPushParams>();
        DBPushParams current;
        try {
            ResultSet result;
            PreparedStatement stmt = c
                    .prepareStatement("SELECT * FROM RADIOMESS WHERE MISSIONNUMBER = ? AND DATE BETWEEN ?  AND ?");
            stmt.setString(1, missionnum);
            stmt.setLong(2, startdate);
            stmt.setLong(3, enddate);
            result = stmt.executeQuery();
            while (result.next()) {
                current = new DBPushParams(FormType.RM, null,
                        result.getInt("RADID"),
                        result.getString("MISSIONNUMBER"),
                        result.getLong("DATE"));
                results.add(current);
            }
            return results;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    /* for querying sar table , no mission no given */
    public static List<DBPushParams> SelectFromRADInitialSearch(long startdate,
            long enddate) {
        List<DBPushParams> results = new ArrayList<DBPushParams>();
        DBPushParams current;
        try {
            ResultSet result;
            PreparedStatement stmt = c
                    .prepareStatement("SELECT * FROM RADIOMESS WHERE DATE BETWEEN ?  AND ?");

            stmt.setLong(1, startdate);
            stmt.setLong(2, enddate);
            result = stmt.executeQuery();
            while (result.next()) {
                current = new DBPushParams(FormType.RM, null,
                        result.getInt("RADID"),
                        result.getString("MISSIONNUMBER"),
                        result.getLong("DATE"));
                results.add(current);
            }
            return results;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    public static List<DBPushParams> SelectFromRadMess(long date,
            String missionnum) {
        List<DBPushParams> results = new ArrayList<DBPushParams>();
        DBPushParams current;
        try {
            ResultSet result;
            PreparedStatement stmt = c
                    .prepareStatement("SELECT * FROM RADIOMESS WHERE MISSIONNUMBER = ? AND DATE = ?");
            stmt.setString(1, missionnum);
            stmt.setLong(2, date);
            result = stmt.executeQuery();
            while (result.next()) {
                current = new DBPushParams(FormType.RM,
                        result.getString("JSONDATA"), result.getInt("RADID"),
                        result.getString("MISSIONNUMBER"),
                        result.getLong("DATE"));
                results.add(current);
            }
            return results;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    public static List<DBPushParams> SelectFromRadMessWithDate(long date) {
        List<DBPushParams> results = new ArrayList<DBPushParams>();
        DBPushParams current;
        try {
            ResultSet result;
            PreparedStatement stmt = c
                    .prepareStatement("SELECT * FROM RADIOMESS WHERE DATE = ?");
            stmt.setLong(1, date);
            result = stmt.executeQuery();
            while (result.next()) {
                current = new DBPushParams(FormType.RM,
                        result.getString("JSONDATA"), result.getInt("RADID"),
                        result.getString("MISSIONNUMBER"),
                        result.getLong("DATE"));
                results.add(current);
            }
            return results;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    public static List<DBPushParams> SelectFromRadMessWithMissionNum(
            String missionnum) {
        List<DBPushParams> results = new ArrayList<DBPushParams>();
        DBPushParams current;
        try {
            ResultSet result;
            PreparedStatement stmt = c
                    .prepareStatement("SELECT * FROM RADIOMESS WHERE MISSIONNUMBER = ?");
            stmt.setString(1, missionnum);
            result = stmt.executeQuery();
            while (result.next()) {
                current = new DBPushParams(FormType.RM,
                        result.getString("JSONDATA"), result.getInt("RADID"),
                        result.getString("MISSIONNUMBER"),
                        result.getLong("DATE"));
                results.add(current);
            }
            return results;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    /*
     * ------------------------The following are
     * updates------------------------------------------
     */
    // TODO implement the update methods for updating a form in progress.
    public static void UpdateCommLog(String json, int id, String missionnum,
            long date) {
        try {
            PreparedStatement stmt = c
                    .prepareStatement("UPDATE COMMLOG set MISSIONNUMBER = ?, JSONDATA = ?, DATE = ?  WHERE COMMID = ?");
            stmt.setString(1, missionnum);
            stmt.setString(2, json);
            stmt.setLong(3, date);
            stmt.setInt(4, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public static void UpdateSAR(String json, int id, String missionnum,
            long date) {
        try {
            PreparedStatement stmt = c
                    .prepareStatement("UPDATE SAR set MISSIONNUMBER = ?, JSONDATA = ?, DATE = ?  WHERE SARID = ?");
            stmt.setString(1, missionnum);
            stmt.setString(2, json);
            stmt.setLong(3, date);
            stmt.setInt(4, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public static void UpdateRADMESS(String json, int id, String missionnum,
            long date) {
        try {
            PreparedStatement stmt = c
                    .prepareStatement("UPDATE RADIOMESS set MISSIONNUMBER = ?, JSONDATA = ?, DATE = ?  WHERE RADID = ?");
            stmt.setString(1, missionnum);
            stmt.setString(2, json);
            stmt.setLong(3, date);
            stmt.setInt(4, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    /*
     * --------------------------------------------------------------------------
     * -----------------
     */
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
            // c = DriverManager.getConnection("jdbc:sqlite:test.db");
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
            // c = DriverManager.getConnection("jdbc:sqlite:test.db");
            Statement stmt = c.createStatement();

            String sql = "DROP TABLE IF EXISTS " + tableName;
            stmt.executeUpdate(sql);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}