package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionUtil {
    Connection con =null;

    public static Connection conDB(){
        try {
           Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost/symbiose","admin","admin");

        }
        catch (Exception ex){
            System.err.println("ConnectionUtil : "+ex.getClass());
            return null;
        }

    }
}
