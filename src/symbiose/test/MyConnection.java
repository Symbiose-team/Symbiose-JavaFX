package symbiose.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MyConnection {

    public String url="jdbc:mysql://localhost:3306/Symbiose";
    public String login="root";
    public String pwd="";
    Connection cnx;
    public static MyConnection instance;

    private MyConnection() {
        try {
            cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion établie!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public Connection getCnx(){
        return cnx;
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
