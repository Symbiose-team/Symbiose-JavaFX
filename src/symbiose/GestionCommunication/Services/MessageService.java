/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symbiose.GestionCommunication.Services;


import symbiose.GestionCommunication.Entities.Commentaire;
import symbiose.GestionCommunication.Entities.Conversation;
import symbiose.GestionCommunication.Entities.Message;
import symbiose.GestionCommunication.Utils.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import symbiose.GestionCommunication.Entities.User;
import symbiose.GestionCommunication.Entities.chat;
import symbiose.utils.MyDbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 * @author Chaima
 */
public class MessageService {

    private Connection con = MyDbConnection.getInstance().getConnexion();
    private Statement ste;

    public MessageService() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
  
    
    public List GetAllConversation(int sender) throws SQLException {
        List<Conversation> list = new ArrayList<>();
        String req1 = "SELECT c.id ,c.user1_id,c.user2_id , u.first_name , u.picture  FROM conversation c , user u WHERE u.id=c.user1_id and c.user1_id=? OR u.id=c.user2_id  and c.user2_id=? ";
        PreparedStatement steprep = con.prepareStatement(req1);
        steprep.setInt(1, sender);
        steprep.setInt(2, sender);
        
        chat ch = null;
        String name=null;
      String picture=null;

        
        ResultSet result = steprep.executeQuery();
        while (result.next()) {
            int user1_id=result.getInt("user1_id");
            int user2_id=result.getInt("user2_id");
            int id=result.getInt("id");                 
            name=result.getString("first_name");
            picture=result.getString("picture");


            User user1=new User();
            user1.setId(user1_id);
            User user2=new User();
            user2.setId(user2_id);

            System.out.println("list"+id+user2_id+user1_id);
            Conversation c= new Conversation(id,user1,user2);

    
            
            list.add(c);
            System.out.println("list"+list);

        }
        System.out.println(ch);
        return list;
    }
    

    public List<Message> GetMessagesParConverID(int conversation_id) throws SQLException {
                 List<Message> arr = new ArrayList<>();

        String req1 = "SELECT m.* , u.first_name FROM Message m , user u WHERE m.user_id=u.id and m.conversation_id=?";
        PreparedStatement steprep = con.prepareStatement(req1);
        steprep.setInt(1, conversation_id);

        Message m = null;
        ResultSet result = steprep.executeQuery();
        while (result.next()) {
            User user = new User();
            user.setId(result.getInt("user_id"));
            m = new Message(result.getInt("m.id"),result.getInt("m.conversation_id"),user,result.getString("m.contenu"), result.getString("u.first_name"));
            arr.add(m);
        }
        System.out.println(m);
        return arr;
    }


    public void AddConversation(User user1 , User user2) throws SQLException {
       
        String req = "INSERT INTO `conversation` (`user1_id`,`user2_id`) VALUES(?,?)";
        PreparedStatement ps = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, user1.getId());
        ps.setInt(2, user2.getId());
       
        ps.executeUpdate();
       
        

        System.out.println("Conversation  ajouté");
    }
    
     public void DeleteConversation(Conversation c) throws SQLException {
       
        String req1 = "DELETE FROM `conversation` where id=?";
        PreparedStatement preparedStatement = con.prepareStatement(req1);
        preparedStatement.setInt(1, c.getId());

        if (preparedStatement.executeUpdate() > 0) {
            System.out.println("conversation supprimée");
        } else {
            System.out.println("conversation non supprimée");
        }
    }
        public void AddMessage(Message m ) throws SQLException {
       
        String req = "INSERT INTO `message` (`conversation_id`,`user_id`,`contenu`,`date`) VALUES(?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, m.getConversation_id());
        ps.setInt(2, m.getUser().getId());
        ps.setString(3, m.getContenu());
        Date date1 = new Date();
        String date_now = new SimpleDateFormat("yyyy-MM-dd").format(date1);
        ps.setString(4, date_now);
     

       
        

        ps.executeUpdate();
       
        

        System.out.println("Message  ajouté");
    }

 
    
    /*  Modif RecAutre */
    public void UpdateMessage(Message m) throws SQLException {
        String req1 = "UPDATE `Message` "
                + "SET `conversation_id`=?,`user_id`=?, `contenu`=?  where id=?";
        PreparedStatement preparedStatement = con.prepareStatement(req1);
        preparedStatement.setInt(1, m.getConversation_id());

        preparedStatement.setInt(2, m.getUser().getId());
        preparedStatement.setString(3, m.getContenu());//ordinal=id

        if (preparedStatement.executeUpdate() > 0) {
            System.out.println("Message modifié");
        } else {
            System.out.println("Message non modifié");
        }
    }

   

    public void DeleteMessage(Message m) throws SQLException {
        String req1 = "DELETE FROM `Message` where id=?";
        PreparedStatement preparedStatement = con.prepareStatement(req1);
        preparedStatement.setInt(1, m.getId());

        if (preparedStatement.executeUpdate() > 0) {
            System.out.println("Message supprimer");
        } else {
            System.out.println("Message non supprimer");
        }
    }

 
    
    public User findUSerById_user(int id_user) throws SQLException {

        User user = new User();
        String req1 = "SELECT * FROM `user` where id=?";
        PreparedStatement preparedStatement = con.prepareStatement(req1);
        preparedStatement.setInt(1, id_user);

        ResultSet result = preparedStatement.executeQuery();
        if (result.next()) {
            user.setId(result.getInt("id"));
            user.setFirstname(result.getString(2));
            user.setLastname(result.getString(3));
        }
        return user;
    }

   
   
   

}
