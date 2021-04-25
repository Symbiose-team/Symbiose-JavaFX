<<<<<<< HEAD:src/symbiose/utils/MyConnection.java
package symbiose.utils;
=======
package symbiose.GestionTerrains.utils;
>>>>>>> terrains_integration:src/symbiose/GestionTerrains/utils/MyConnection.java

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
<<<<<<< HEAD:src/symbiose/utils/MyConnection.java
=======

>>>>>>> terrains_integration:src/symbiose/GestionTerrains/utils/MyConnection.java


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
