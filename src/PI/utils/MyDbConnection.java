package PI.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author gogo-
 */
public class MyDbConnection {

    private static MyDbConnection instance;
    private Connection connexion;
    private String url = "jdbc:mysql://localhost/symbiose";
    private String user = "root";
    private String password = "";
    private static Statement stmt = null;

    private MyDbConnection() {
        try {
            connexion = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            System.out.println("Probleme de connexion");
        }
    }

    public static MyDbConnection getInstance() {
        if (MyDbConnection.instance == null) {
            MyDbConnection.instance = new MyDbConnection();
        }
        return MyDbConnection.instance;
    }

    public Connection getConnexion() {
        return this.connexion;
    }


}
