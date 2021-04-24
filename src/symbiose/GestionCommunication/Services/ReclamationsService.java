/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symbiose.GestionCommunication.Services;


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
public class ReclamationsService {

    private Connection con = MyDbConnection.getInstance().getConnexion();
    private Statement ste;

    public ReclamationsService() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public List<Reclamation> rechercherReclamationParID(int id) throws SQLException {
                

        String req1 = "SELECT r.* , u.first_name FROM Reclamation r ,user u WHERE r.id_user=? and u.id=? ";
        PreparedStatement steprep = con.prepareStatement(req1);
        steprep.setInt(1, id);
                steprep.setInt(2, id);

                List<Reclamation> list = new ArrayList<>();


        Reclamation r = new Reclamation();
        ResultSet result = steprep.executeQuery();
          while (result.next())  {
            User user = new User();
            user.setId(result.getInt("r.id_user"));
            r = new Reclamation(result.getInt("r.id"),result.getString("r.sujet") ,result.getString("r.description"),result.getString("r.etat"), user,result.getString("r.date_creation"),result.getString("u.first_name"));
            list.add(r);
        System.out.println("///////"+r);

        }
        System.out.println("///////"+r);
        return list;
    }
    
    
    public List<Reclamation> AllReclamation() throws SQLException {
                

        String req1 = "SELECT r.* , u.first_name FROM Reclamation r ,user u WHERE u.id=r.id_user";
        PreparedStatement steprep = con.prepareStatement(req1);
                List<Reclamation> list = new ArrayList<>();


        Reclamation r = new Reclamation();
        ResultSet result = steprep.executeQuery();
          while (result.next())  {
            User user = new User();
            user.setId(result.getInt("r.id_user"));
            r = new Reclamation(result.getInt("r.id"),result.getString("r.sujet") ,result.getString("r.description"),result.getString("r.etat"), user,result.getString("r.date_creation"),result.getString("u.first_name"));
            r.setEtat(result.getString("r.etat"));
            list.add(r);
        System.out.println("///////"+r);

        }
        System.out.println("///////"+r);
        return list;
    }
    
    
    public List<Reclamation> rechercherReclamationParIDOrderbyDate(int id) throws SQLException {
                

        String req1 = "SELECT r.* , u.first_name FROM Reclamation r ,user u WHERE u.id=r.id_user=? ORDER BY date_creation DESC";
        PreparedStatement steprep = con.prepareStatement(req1);
        steprep.setInt(1, id);
                List<Reclamation> list = new ArrayList<>();


        Reclamation r = new Reclamation();
        ResultSet result = steprep.executeQuery();
          while (result.next())  {
            User user = new User();
            user.setId(result.getInt("id"));
            r = new Reclamation(id,result.getString("sujet") ,result.getString("description"),result.getString("etat"), user,result.getString("date_creation"),result.getString("first_name"));
            r.setEtat(result.getString("etat"));
            list.add(r);
        System.out.println("///////"+r);

        }
        System.out.println("///////"+r);
        return list;
    }

    public boolean existReclamation(int id) throws SQLException {
        return rechercherReclamationParID(id) != null;
    }

    /*  ajout RecAutre */
    public void ajouterReclamation(Reclamation reclamation) throws SQLException {
        /*String req1 = "INSERT INTO `Reclamation` (`description`,`etudiant_id`,sujet,etat) "
                + "VALUES ('" + reclamation.getDesc() + "', '" + reclamation.getUser().getId_user() + "','" + reclamation.getSujet() + "',0);";
         */
        String req = "INSERT INTO `Reclamation` (`description`,`sujet`,`etat`, `date_creation`,`id_user`) VALUES(?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, reclamation.getDesc());

            ps.setString(2, reclamation.getSujet());
              ps.setString(3, reclamation.getEtat());
              ps.setInt(5, reclamation.getUser().getId());
       
      
        Date date1 = new Date();
        String date_now = new SimpleDateFormat("yyyy-MM-dd").format(date1);
        ps.setString(4, date_now);

        ps.executeUpdate();
     

        System.out.println("Reclamation  ajouté");
    }

    public static double getRandomCodeSuivie() {
        double x = (int) (Math.random() * ((999 - 100) + 1)) + 100;
        return x;
    }

    
    /*  Modif RecAutre */
    public void modifierReclamation(Reclamation reclamation) throws SQLException {
        String req1 = "UPDATE `Reclamation` "
                + "SET `sujet`=?,`description`=?, etat=? where id=?";
        PreparedStatement preparedStatement = con.prepareStatement(req1);
        preparedStatement.setString(1, reclamation.getSujet());

        preparedStatement.setString(2, reclamation.getDesc());
        preparedStatement.setString(3, reclamation.getEtat());//ordinal=id
        preparedStatement.setInt(4, reclamation.getId());

        if (preparedStatement.executeUpdate() > 0) {
            System.out.println("Reclamation modifier");
        } else {
            System.out.println("Reclamation non modifier");
        }
    }

   

    public void suprimerReclamation(Reclamation reclamation) throws SQLException {
        String req1 = "DELETE FROM `Reclamation` where id=?";
        PreparedStatement preparedStatement = con.prepareStatement(req1);
        preparedStatement.setInt(1, reclamation.getId());

        if (preparedStatement.executeUpdate() > 0) {
            System.out.println("Reclamation supprimer");
        } else {
            System.out.println("Reclamation non supprimer");
        }
    }

 

    

    public int nbReclamationParType(String etat) throws SQLException {
        int nb = 0;

        String req1 = "SELECT count(*) FROM `Reclamation` where etat=?";
        PreparedStatement preparedStatement = con.prepareStatement(req1);
        preparedStatement.setString(1, etat);

        ResultSet result = preparedStatement.executeQuery();
        if (result.first()) {
            nb = result.getInt(1);
        }

        return nb;
    }

    public int nbReclamationTotal() throws SQLException {
        int nb = 0;

        String req1 = "SELECT count(*) FROM `Reclamation`";
        ResultSet result = ste.executeQuery(req1);
        if (result.first()) {
            nb = result.getInt(1);
        }

        System.out.println(nb);
        return nb;

    }

    public double statReclamationParType(String rec) throws SQLException {
        float res = ((float) nbReclamationParType(rec) / nbReclamationTotal());
        System.out.println(Double.valueOf(new DecimalFormat("##.##").format(res * 100)) + "%");
        return Double.valueOf(new DecimalFormat("##.##").format(res * 100));
    }

    //jointure
    public Vector<Reclamation> getAll() throws SQLException {

        Vector<Reclamation> reclamations = new Vector<Reclamation>();

        String req1 = "SELECT r.*,user.* FROM Reclamation r, user where r.id = user.id";
        ResultSet result = ste.executeQuery(req1);
        while (result.next()) {
            User user = new User();
            user.setId(result.getInt("id"));
            user.setFirstname("first_Name");
            Reclamation r = new Reclamation(result.getInt(1), result.getString("sujet"),result.getString("description"),result.getString("etat"), user , result.getString("date_creation"));
            r.setEtat(result.getString("etat"));

            reclamations.add(r);
        }
        System.out.println(reclamations);
        return reclamations;
    }
    //get all reclamations by userID

    public Vector<Reclamation> getAllParUserID(int id_user) throws SQLException {

        Vector<Reclamation> reclamations = new Vector<Reclamation>();

        String req1 = "SELECT * FROM `Reclamation` where id=?";
        PreparedStatement preparedStatement = con.prepareStatement(req1);
        preparedStatement.setInt(1, id_user);

        ResultSet result = preparedStatement.executeQuery();
        while (result.next()) {
            User user = new User();
            user.setId(result.getInt("id"));
            Reclamation r = new Reclamation(result.getInt(1), result.getString("sujet"),result.getString("description"),result.getString("etat"), user);


            reclamations.add(r);

        }
        System.out.println(reclamations);
        return reclamations;
    }
    //get all notes by userID

    
    public List<User> findUSerById_user(int id_user) throws SQLException {
                List<User> list = new ArrayList<>();

        User user = new User();
        String req1 = "SELECT * FROM `user` where id=?";
        PreparedStatement preparedStatement = con.prepareStatement(req1);
        preparedStatement.setInt(1, id_user);

        ResultSet result = preparedStatement.executeQuery();
        if (result.next()) {
            user.setId(result.getInt("id"));
            user.setFirstname(result.getString("first_name"));
            user.setLastname(result.getString(3));
            list.add(user);
        }
        return list;
    }

   
   
   

}
