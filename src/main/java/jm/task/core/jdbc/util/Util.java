package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static String dbURl = "jdbc:mysql://localhost:3306/jaja";
    private static String dbUser = "vpr";
    private static String dbPass = "piupiu";

    public static Connection getMySQlConnection(){
        Connection connection = null;
        try {
           //connection = DriverManager.getConnection("jdbc:mysql://localhost/jaja?user=vpr&password=piupiu");
           connection = DriverManager.getConnection(dbURl, dbUser, dbPass);
           if (!connection.isClosed()) System.out.println("Connected...");

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return connection;
    }

}
