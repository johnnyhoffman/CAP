/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package database;
import java.sql.*;
/**
 *
 * @author Robert
 */

/* probably gonna want to have the opened connection to the database live in the DBAccess class */
/* all methods currently are static in the sqlServer class from Kasey */

public class DBTest {
    public static void main(String args[]){
        sqlServer.createDatabase();
    }
}
