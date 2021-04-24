package GestionEvents.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MyConnection {

    public String url="jdbc:mysql://localhost:3306/symbiose";
    public String login="root";
    public String pwd="";
    static MyConnection instance=null;
    Connection cnx;

    private MyConnection() {
        try {
            cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion établie!");
        } catch (SQLException ex) {
            System.out.println("Pas de connexion");
        }
    }

    public static MyConnection getInstance(){
        if(instance== null){
            instance = new MyConnection();
        }
        return instance ;
    }

    public Connection getConnection(){
        return cnx;
    }
}
