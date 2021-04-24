/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symbiose.GestionCommunication.Services;


import symbiose.GestionCommunication.Entities.Conversation;
import symbiose.GestionCommunication.Entities.Product;
import symbiose.GestionCommunication.Utils.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import symbiose.GestionCommunication.Entities.Reclamation;
import symbiose.GestionCommunication.Entities.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javafx.collections.ObservableList;
import symbiose.utils.MyDbConnection;

/**
 *
 * @author Chaima
 */
public class UserService {

    private Connection con = MyDbConnection.getInstance().getConnexion();
    private Statement ste;

    public UserService() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

   public User GetUserbyname(String name) throws SQLException {
        String req1 = "SELECT * FROM user WHERE first_name=?";
        PreparedStatement steprep = con.prepareStatement(req1);
        steprep.setString(1, name);

        User u = null;
        ResultSet result = steprep.executeQuery();
        if (result.first()) {
            User user1 = new User();
            int id=result.getInt("id");
            String fn=result.getString("first_name");
            u = new User(id,name );
        }
        System.out.println(u);
        return u;
    }
    public String Getnamebyid(int id) throws SQLException {
        String req1 = "SELECT * FROM user WHERE id=?";
        PreparedStatement steprep = con.prepareStatement(req1);
        steprep.setInt(1, id);
        String fn=null;
        User u = null;
        ResultSet result = steprep.executeQuery();
        if (result.first()) {
            User user1 = new User();
           
             fn=result.getString("first_name");
      
        }
        System.out.println(u);
        return fn;
    }
    public String Getpicbyid(int id) throws SQLException {
        String req1 = "SELECT picture FROM user WHERE id=?";
        PreparedStatement steprep = con.prepareStatement(req1);
        steprep.setInt(1, id);
        String fn=null;
        User u = null;
        ResultSet result = steprep.executeQuery();
        if (result.first()) {
            User user1 = new User();
           
             fn=result.getString("picture");
      
        }
        System.out.println(u);
        return fn;
    }
    
    
    public List<String> Allusers() throws SQLException {
                

        String req1 = "SELECT id ,first_name FROM user ";
        PreparedStatement steprep = con.prepareStatement(req1);
                List<String> list = new ArrayList<>();


        User u = new User();
        ResultSet result = steprep.executeQuery();
          while (result.next())  {
            User user = new User();
            String name=result.getString("first_name");
            u = new User(result.getInt("id"),result.getString("first_name"));
            list.add(name);
        System.out.println("///////"+u);

        }
          
        return list;
     
    }
    
    
  
   
   

}
