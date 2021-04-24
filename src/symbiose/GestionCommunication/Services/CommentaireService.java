/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symbiose.GestionCommunication.Services;
import symbiose.GestionCommunication.Entities.Commentaire;
import symbiose.GestionCommunication.Entities.Product;
import symbiose.GestionCommunication.Entities.Publication;
import symbiose.GestionCommunication.Entities.User;
import symbiose.GestionCommunication.Utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 *
 * @author Chaima
 */
public class CommentaireService {
        private Connection con = DataSource.getInstance().getConnection();
    private Statement ste;
    
    User user =new User();
    
    
    public CommentaireService() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
   
    
    public void AddComment(Commentaire c) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO `commentaire` ( `user_id`, `contenu`, `productcomment_id`,`date`) VALUES ( ?, ?, ?, ?);");

              ps.setInt(1, c.getUser().getId());
        ps.setString(2, c.getContenu());
        ps.setInt(3, c.getProduct().getId());
       
Date date1 = new Date();
        String date_now = new SimpleDateFormat("yyyy-MM-dd").format(date1);
        ps.setString(4, date_now);
        ps.executeUpdate();
        System.out.println("Commentaire ajoutée !!");

    }
       

     public List GetCommentPerProduct(Product p) throws SQLException {
         List<Commentaire> arr = new ArrayList<>();
        String req1 = "SELECT c.user_id,c.publication_id,c.contenu,c.date,c.productcomment_id,u.first_name FROM commentaire c, user u WHERE productcomment_id=? and c.user_id=u.id";
        PreparedStatement steprep = con.prepareStatement(req1);
        steprep.setInt(1, p.getId());
        ResultSet rs = steprep.executeQuery();
         while (rs.next()) {
            int id = rs.getInt(1);
            int user_id = rs.getInt("c.user_id");
            int publication_id = rs.getInt("c.publication_id");
            String contenu = rs.getString("c.contenu");
            String date = rs.getString("c.date");
            int productcomment_id = rs.getInt("c.productcomment_id");
             String username = rs.getString("u.first_name");
        System.out.println("aaa"+username);

            User user=new User();
            Publication pub=new Publication();
            Product prod=new Product();
            Commentaire c = new Commentaire(id,user,pub,prod,contenu,date,username);
            arr.add(c);
         }
return  arr;
     }
     public void UpadateCommentaire(Commentaire c) throws SQLException {
        String req1 = "UPDATE `commentaire` "
                + "SET `contenu` where id=?";
        PreparedStatement ps = con.prepareStatement(req1);
        ps.setString(1, c.getContenu());
        ps.setInt(2, c.getId());
       

        if (ps.executeUpdate() > 0) {
            System.out.println("Commentaire modifier");
        } else {
            System.out.println("Commentaire non modifier");
        }
    }
     
      
       public void DeleteComment(Commentaire c) throws SQLException {
        String req1 = "DELETE FROM `commentaire` where id=? ; ";
        PreparedStatement preparedStatement = con.prepareStatement(req1);
        preparedStatement.setInt(1, c.getId());

        if (preparedStatement.executeUpdate() > 0) {
            System.out.println("Commentaire supprimé");
        } else {
            System.out.println("Commentaire non supprimer");
        }
    }
     
}

