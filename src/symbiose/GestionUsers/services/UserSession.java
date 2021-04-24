 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symbiose.GestionUsers.services;

 import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.sql.Statement;
 import java.util.logging.Level;
 import java.util.logging.Logger;

 import static symbiose.GestionUsers.services.Usercrud.connexion;

 public final class UserSession {

     private static UserSession instance;

     private int userId;
     private String username;
     private String avatar;
     private String role;
     private String adresse;
     private String cin;
     private String genre;
     private String birthday;
     private String phone;


     private UserSession(int userId) {
         this.userId = userId;
     }

     public static UserSession getInstace(int userId) {
         if(instance == null) {
             instance = new UserSession(userId);
         }
         return instance;
     }

     public int getUserId() {
         return userId;
     }


     public String getUsername(int userId) {
         try {

             String req = "SELECT `first_name`, `last_name`, `email`,`image`,`hash`, `cin`, `birthday`, `role`, `adresse`, `phone_number`, `id`,`genre`FROM `user` WHERE ID=" + userId + " ";
             Statement stm = connexion.createStatement();
             ResultSet result = stm.executeQuery(req);

             while (result.next()) {
                 String username = result.getString("first_name");
                  return username;

             }
         } catch (SQLException ex) {
             Logger.getLogger(Usercrud.class.getName()).log(Level.SEVERE, null, ex);
         }
         return username;
     }
     public String getRole(int userId) {
         try {

             String req = "SELECT `first_name`, `last_name`, `email`,`image`,`hash`, `cin`, `birthday`, `role`, `adresse`, `phone_number`, `id`,`genre`FROM `user` WHERE ID=" + userId + " ";
             Statement stm = connexion.createStatement();
             ResultSet result = stm.executeQuery(req);

             while (result.next()) {
                 String role = result.getString("role");
                 return role;

             }
         } catch (SQLException ex) {
             Logger.getLogger(Usercrud.class.getName()).log(Level.SEVERE, null, ex);
         }
         return role;
     }
     public String getAdresse(int userId) {
         try {

             String req = "SELECT `first_name`, `last_name`, `email`,`image`,`hash`, `cin`, `birthday`, `role`, `adresse`, `phone_number`, `id`,`genre`FROM `user` WHERE ID=" + userId + " ";
             Statement stm = connexion.createStatement();
             ResultSet result = stm.executeQuery(req);

             while (result.next()) {
                 String adresse = result.getString("adresse");
                 return adresse;

             }
         } catch (SQLException ex) {
             Logger.getLogger(Usercrud.class.getName()).log(Level.SEVERE, null, ex);
         }
         return adresse;
     }
     public String getCin(int userId) {
         try {

             String req = "SELECT `first_name`, `last_name`, `email`,`image`,`hash`, `cin`, `birthday`, `role`, `adresse`, `phone_number`, `id`,`genre`FROM `user` WHERE ID=" + userId + " ";
             Statement stm = connexion.createStatement();
             ResultSet result = stm.executeQuery(req);

             while (result.next()) {
                 String cin = result.getString("cin");
                 return cin;

             }
         } catch (SQLException ex) {
             Logger.getLogger(Usercrud.class.getName()).log(Level.SEVERE, null, ex);
         }
         return cin;
     }
     public String getGenre(int userId) {
         try {

             String req = "SELECT `first_name`, `last_name`, `email`,`image`,`hash`, `cin`, `birthday`, `role`, `adresse`, `phone_number`, `id`,`genre`FROM `user` WHERE ID=" + userId + " ";
             Statement stm = connexion.createStatement();
             ResultSet result = stm.executeQuery(req);

             while (result.next()) {
                 String genre = result.getString("genre");
                 return genre;

             }
         } catch (SQLException ex) {
             Logger.getLogger(Usercrud.class.getName()).log(Level.SEVERE, null, ex);
         }
         return genre;
     }
     public String getBirthday(int userId) {
         try {

             String req = "SELECT `first_name`, `last_name`, `email`,`image`,`hash`, `cin`, `birthday`, `role`, `adresse`, `phone_number`, `id`,`genre`FROM `user` WHERE ID=" + userId + " ";
             Statement stm = connexion.createStatement();
             ResultSet result = stm.executeQuery(req);

             while (result.next()) {
                 String birthday = result.getString("birthday");
                 return birthday;

             }
         } catch (SQLException ex) {
             Logger.getLogger(Usercrud.class.getName()).log(Level.SEVERE, null, ex);
         }
         return birthday;
     }
     public String getPhone(int userId) {
         try {

             String req = "SELECT `first_name`, `last_name`, `email`,`image`,`hash`, `cin`, `birthday`, `role`, `adresse`, `phone_number`, `id`,`genre`FROM `user` WHERE ID=" + userId + " ";
             Statement stm = connexion.createStatement();
             ResultSet result = stm.executeQuery(req);

             while (result.next()) {
                 String phone = result.getString("phone_number");
                 return phone;

             }
         } catch (SQLException ex) {
             Logger.getLogger(Usercrud.class.getName()).log(Level.SEVERE, null, ex);
         }
         return phone;
     }

      public String getAvatar(int userId) {
         try {

             String req = "SELECT image FROM USER WHERE ID=" + userId + " ";
             Statement stm = connexion.createStatement();
             ResultSet result = stm.executeQuery(req);

             while (result.next()) {
                 String avatar = result.getString("image");
                  return avatar;

             }
         } catch (SQLException ex) {
             Logger.getLogger(Usercrud.class.getName()).log(Level.SEVERE, null, ex);
         }
         return avatar;
     }



     public void cleanUserSession() {
         userId = 0;// or null
     }

     @Override
     public String toString() {
         return "UserSession{" +
                 "id='" + userId + "' ";

     }
 }