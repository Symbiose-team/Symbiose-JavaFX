package symbiose;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import symbiose.utils.BCrypt;
import symbiose.utils.MyDbConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class LoginController implements Initializable {
    @FXML
    private Label lblErrors;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private Button btnSignin;

    @FXML
    private AnchorPane signup1;
    @FXML
    private JFXButton signup_ins;
    @FXML
    private Button btnSignup;

    @FXML
    private JFXButton back_to_auth;

    @FXML
    private AnchorPane auth;

    @FXML
    private AnchorPane bg_symbiose;
    @FXML
    private Pane login_auth;
    @FXML
    private JFXTextField nom;

    @FXML
    private JFXTextField Email;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXTextField prenom;

    @FXML
    private JFXPasswordField confirmpassword;

    @FXML
    private JFXDatePicker birthday;

    @FXML
    private JFXComboBox<String> role;

    @FXML
    private JFXComboBox<String> Genre;

    @FXML
    private JFXTextField cin;

    @FXML
    private JFXTextField adresse;

    @FXML
    private JFXTextField phone;
    /// --
    Connection con;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @FXML
    public void handleButtonAction(MouseEvent event) {

        if (event.getSource() == btnSignin) {
            //login here
            if (logIn().equals("Success")) {
                try {

                    //add you loading or delays - ;-)
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/symbiose/home.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }

            }
        }
    }

    /**
     *
     * Redirection avant l'inscription (scene)
     */
    @FXML
    public void handleButtonActionOnSignup(ActionEvent event) {

        if (event.getSource() == btnSignup) {

            bg_symbiose.setVisible(false);
            login_auth.setVisible(false);
            signup1.setVisible(true);
        } else if (event.getSource() == back_to_auth) {

            bg_symbiose.setVisible(true);
            login_auth.setVisible(true);
            signup1.setVisible(false);
        }
    }

    /**
     *
     * Apres redirection finalization de l'inscription
     */
    @FXML
    public void handleSignup(MouseEvent event) {
        if (signup() > 0) {
            handleButtonAction(event);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        role.getItems().addAll("Client", "Fournisseur");
        role.getSelectionModel().select("Fournisseur");
        Genre.getItems().addAll("Homme", "Femme");
        Genre.getSelectionModel().select("Homme");

        if (con == null) {
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Serveur en arrêt : Vérifiez la connexion!");
        } else {
            lblErrors.setTextFill(Color.GREEN);
            lblErrors.setText("Serveur en marche : Allons-y !");
        }


    }

    public LoginController() {
        con = MyDbConnection.getInstance().getConnexion();
    }

    //we gonna use string to check for status
    private String logIn() {
        String status = "Success";
        String email = txtUsername.getText();
        String hash = txtPassword.getText();
        if (email.isEmpty() || hash.isEmpty()) {
            setLblError(Color.TOMATO, "Identifiants vides");
            status = "Error";
        } else {
            //query
            String sql = "SELECT * FROM user Where email = ? and hash = ?";
            try {
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, hash);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    setLblError(Color.TOMATO, "Entrez le bon e-mail / mot de passe");
                    status = "Error";
                } else {
                    setLblError(Color.GREEN, "Connexion réussie..Redirection ..");
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                status = "Exception";
            }
        }

        return status;
    }

    private void setLblError(Color color, String text) {
        lblErrors.setTextFill(color);
        lblErrors.setText(text);
        System.out.println(text);
    }

    private int signup() {
        int result = 0;
        String name = nom.getText();
        String lastname = prenom.getText();
        String email = Email.getText();
        String Password = password.getText();
        String ConfirmPassword = confirmpassword.getText();
        String pass = BCrypt.hashpw(Password, BCrypt.gensalt(13));
        String numero = phone.getText();
        String Role = role.getValue().toString();
        String genre = Genre.getValue().toString();
        String Birthday = birthday.getValue().toString();
        String Adresse = adresse.getText();
        String Cin = cin.getText();

        if (email.isEmpty() || Password.isEmpty() || name.isEmpty() || lastname.isEmpty() || ConfirmPassword.isEmpty() || Adresse.isEmpty() || numero.isEmpty() || Birthday.isEmpty() || Cin.isEmpty()) {
            setLblError(Color.TOMATO, "Empty credentials");
        } else if (!Password.equals(ConfirmPassword)) {
            setLblError(Color.TOMATO, "Passwords not identical");
            result = 0;
        } else if (!validateEmailAddress(email)) {
            setLblError(Color.TOMATO, "Invalid Email");
            result = 0;
        } else if (isUserExist(email)) {
            setLblError(Color.TOMATO, "An account with this email/username already exists , try logging in .");
            result = 0;
        }
        else {
            try {
                String st = "INSERT INTO `user` (`first_name`, `last_name`, `email`, `hash`, `cin`, `birthday`, `role`, `adresse`, `phone_number`, `genre`) VALUES (?,?,?,?,?,?,?,?,?,?)";
                preparedStatement = con.prepareStatement(st);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastname);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, pass);
                preparedStatement.setString(5, Cin);
                preparedStatement.setString(6, Birthday);
                preparedStatement.setString(7, Role);
                preparedStatement.setString(8, Adresse);
                preparedStatement.setString(9, numero);
                preparedStatement.setString(10, genre);
                result = preparedStatement.executeUpdate();
                if (result > 0) {
                    setLblError(Color.GREEN, "Registration Successful..Redirecting..");

                } else {
                    setLblError(Color.TOMATO, "Enter Correct Email/Password");
                    result = 0;

                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }

        return result;
    }

    public static boolean validateEmailAddress(String emailID) {
        String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(emailID).matches();
    }

    public Boolean isUserExist(String userEmail) {
        boolean userId = false;

        String req = "SELECT * FROM user where email = ?";
        PreparedStatement prpStm;
        try {
            prpStm = con.prepareStatement(req);
            prpStm.setString(1, userEmail);
            ResultSet result = prpStm.executeQuery();
            if (result.next()) {
                userId = true;
            } else {
                userId = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return userId;
    }

}

