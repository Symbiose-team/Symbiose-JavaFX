package PI.GestionUsers.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import symbiose.models.User;
import PI.utils.BCrypt;
import PI.utils.MyDbConnection;

import java.security.SecureRandom;
import java.sql.*;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class Usercrud {

    Connection Cn = MyDbConnection.getInstance().getConnexion();
    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe

    static Connection connexion;

    public Usercrud() {
        connexion = MyDbConnection.getInstance().getConnexion();

    }

    public void ajouterUser(User U) {
        try {
            Statement st = Cn.createStatement(); //l'element qui va éxécuter la requete sql

            String req = "INSERT INTO `user` (`first_name`, `last_name`, `email`,`image`,`hash`, `cin`, `birthday`, `role`, `adresse`, `phone_number`, `genre` ,`is_enabled`,`registration_token` ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ste = Cn.prepareStatement(req);
            ste.setString(1, U.getFirst_name());
            ste.setString(2, U.getLast_name());
            ste.setString(3, U.getEmail());
            ste.setString(4, U.getImage());
            ste.setString(5, BCrypt.hashpw(U.getHash(), BCrypt.gensalt(12)));
            ste.setInt(6, U.getCin());
            ste.setString(7, U.getBirthday().toString());
            ste.setString(8, U.getRole());
            ste.setString(9, U.getAdresse());
            ste.setInt(10, U.getPhone_number());
            ste.setString(11, U.getGenre());
            ste.setBoolean(12,U.getIs_enabled());
            ste.setString(13, U.getRegistration_token());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error sql : " + ex.getMessage());
        }

    }
    public Boolean SupprimerUser(int id) {
        try {
            Statement st = Cn.createStatement();
            String req = "Update user set is_enabled=0  where id=" + id;
            st.executeUpdate(req);
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
    public Boolean enableUser(int id) {
        try {
            Statement st = Cn.createStatement();
            String req = "Update user set is_enabled=1  where id=" + id;
            st.executeUpdate(req);
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
    public ObservableList<User> getAllUser() {
        ObservableList<User> l = FXCollections.observableArrayList();

        try {
            Statement st = Cn.createStatement();

            String req = "SELECT `first_name`, `last_name`, `email`,`image`,`hash`, `cin`, `birthday`, `role`, `adresse`, `phone_number`, `genre` ,`is_enabled`,`registration_token` FROM `user`";


            ResultSet rs = st.executeQuery(req); //retourne un résulat

            while (rs.next()) {
                User U = new User(rs.getString("first_name"),rs.getString("last_name"),rs.getString("email"),rs.getString("image"),rs.getString("hash"),rs.getInt("cin"),rs.getDate("birthday"),rs.getString("role"),rs.getString("adresse"),rs.getInt("phone_number"),rs.getString("genre"),rs.getInt("id"),rs.getBoolean("is_enabled"),rs.getString("registration_token"));
                l.add(U);
            }

            return l;
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
            return null;
        }
    }
    public void modifierUser(User u) {
        try {
            PreparedStatement ps = Cn.prepareStatement("update user set first_name=?,last_name=?,email=?,genre=?,role=?,hash=?,birthday=?,adresse=?,cin=?,phone_number=?,is_enabled=? where id=?");
            ps.setString(1, u.getFirst_name());
            ps.setString(2, u.getLast_name());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getRole());
            ps.setString(5, BCrypt.hashpw(u.getHash(), BCrypt.gensalt(12)));
            ps.setString(6, u.getBirthday().toString());
            ps.setString(7, u.getAdresse());
            ps.setInt(8, u.getCin());
            ps.setInt(9,u.getPhone_number());
            ps.setBoolean(10,u.getIs_enabled());
            ps.setInt(11, u.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("error : " + ex.getCause());
        }
    }
    public User VerifyUser(String email) {
        User u = new User();

        String query = "SELECT first_name,last_name,email,genre,role,hash,birthday,adresse,cin,phone_number,id,is_enabled FROM `user` where email = '" + email + "'";
        try {
            Statement st = Cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                u.setId(rs.getInt("id"));
                u.setEmail(rs.getString(3));
                u.setHash(rs.getString(6));
            }
            return u;
        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
            return null;
        }

    }
    public Boolean isUserExist(String userEmail) {
        boolean userId = false;

        String req = "SELECT first_name,last_name,email,genre,role,hash,birthday,adresse,cin,phone_number,id,is_enabled FROM `user` where email = ?";
        PreparedStatement prpStm;
        try {
            prpStm = Cn.prepareStatement(req);
            prpStm.setString(1, userEmail);
            ResultSet result = prpStm.executeQuery();
            if (result.next()) {
                userId = true;
            } else {
                userId = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usercrud.class.getName()).log(Level.SEVERE, null, ex);
        }

        return userId;
    }

    public Boolean isExist(String userEmail) {
        boolean userId = false;

        String req = "SELECT first_name,last_name,email,genre,role,hash,birthday,adresse,cin,phone_number,id,is_enabled FROM `user` where email = ? ";
        PreparedStatement prpStm;
        try {
            prpStm = Cn.prepareStatement(req);
            prpStm.setString(1, userEmail);
            ResultSet result = prpStm.executeQuery();
            if (result.next()) {
                userId = true;
            } else {
                userId = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usercrud.class.getName()).log(Level.SEVERE, null, ex);
        }

        return userId;
    }
    public static boolean validateEmailAddress(String emailID) {
        String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(emailID).matches();
    }
    public Boolean VerifyEnable(String u) {
        int c = 0;
        String req = "SELECT is_enabled FROM user where email = ?";
        PreparedStatement prpStm;
        try {
            prpStm = Cn.prepareStatement(req);
            prpStm.setString(1, u);
            ResultSet result = prpStm.executeQuery();
            if (result.next()) {
                c = result.getInt("is_enabled");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usercrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (c == 1) {
            return true;
        } else {
            return false;
        }

    }
    public String findByEmail(String email) {
        String c = null;

        String req = "SELECT email FROM user where email = ?";
        PreparedStatement prpStm;
        try {
            prpStm = Cn.prepareStatement(req);
            prpStm.setString(1, email);
            ResultSet result = prpStm.executeQuery();
            if (result.next()) {
                c = result.getString("email");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usercrud.class.getName()).log(Level.SEVERE, null, ex);
        }

        return c;
    }
    public User getUser(int id) {
        User u = new User();

        String req = "SELECT first_name,last_name,email,image,genre,role,hash,birthday,adresse,cin,phone_number,id,is_enabled FROM `user` where id = ?";
        PreparedStatement prpStm;
        try {
            prpStm = Cn.prepareStatement(req);
            prpStm.setInt(1, id);
            ResultSet result = prpStm.executeQuery();
            if (result.next()) {
                u.setId(result.getInt("id"));
                u.setFirst_name(result.getString("first_name"));
                u.setLast_name(result.getString("last_name"));
                u.setEmail(result.getString("email"));
                u.setRole(result.getString("role"));
                u.setGenre(result.getString("genre"));
                u.setImage(result.getString("image"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(Usercrud.class.getName()).log(Level.SEVERE, null, ex);
        }

        return u;
    }
    public Boolean resetP(int id, String new1) throws SQLException {
        String newp = BCrypt.hashpw(new1, BCrypt.gensalt(13));

        String req = "UPDATE user set hash =? where id =" + id + " ";
        PreparedStatement stm = Cn.prepareStatement(req);
        stm.setString(1, newp);

        if (stm.executeUpdate() > 0) {

            return true;

        } else {
            return false;
        }
    }

    public String getP(int id) {
        String p = null;
        String req = "SELECT hash FROM user where id = ?";
        PreparedStatement prpStm;
        try {
            prpStm = Cn.prepareStatement(req);
            prpStm.setInt(1, id);
            ResultSet result = prpStm.executeQuery();
            if (result.next()) {
                p = result.getString("hash");

            }
        } catch (SQLException ex) {
            Logger.getLogger(Usercrud.class.getName()).log(Level.SEVERE, null, ex);
        }

        return p;
    }

    public boolean deleteAcc(int id) {
        try {
            String delete = "DELETE FROM user WHERE ID=" + id + "";
            PreparedStatement stm = Cn.prepareStatement(delete);

            int res = stm.executeUpdate();
            return (res > 0);

        } catch (SQLException ex) {
            Logger.getLogger(Usercrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public Boolean updateProfile(User u) {
        try {
            String update = "UPDATE user set first_name=? , last_name=? , email=? WHERE ID=" + u.getId() + "";
            PreparedStatement stm = Cn.prepareStatement(update);
            stm.setString(1, u.getFirst_name());
            stm.setString(2, u.getLast_name());
            stm.setString(3, u.getEmail());


            int res = stm.executeUpdate();
            return (res > 0);

        } catch (SQLException ex) {
            Logger.getLogger(Usercrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }
    public Boolean updateAv(String img, int id) {
        try {
            String update = "UPDATE user set image=?  WHERE ID='" + id + "'";
            PreparedStatement stm = Cn.prepareStatement(update);
            stm.setString(1, img);
            int res = stm.executeUpdate();
            return (res > 0);

        } catch (SQLException ex) {
            Logger.getLogger(Usercrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public String getConf(String userEmail) {
        String c = null;

        String req = "SELECT registration_token FROM user where email = ?";
        PreparedStatement prpStm;
        try {
            prpStm = Cn.prepareStatement(req);
            prpStm.setString(1, userEmail);
            ResultSet result = prpStm.executeQuery();
            if (result.next()) {
                c = result.getString("registration_token");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usercrud.class.getName()).log(Level.SEVERE, null, ex);
        }

        return c;
    }
    public static String generateNewToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }
}
