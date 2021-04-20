/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PI.GestionUsers.controllers;

import PI.GestionUsers.services.UserSession;
import PI.GestionUsers.services.Usercrud;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import symbiose.models.User;
import PI.Main;
import PI.utils.BCrypt;
import PI.utils.MyDbConnection;
import tray.animations.AnimationType;
import tray.notification.TrayNotification;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author SkanderThabet
 */
public class SigninController implements Initializable {

    @FXML
    private JFXButton open_signup;
    @FXML
    public Label Errors;
    @FXML
    private Pane vbox;
    @FXML
    private JFXButton btnSignin;
    @FXML
    private JFXTextField Email;
    @FXML
    private JFXPasswordField Password;
    @FXML
    private Circle ExitButton;
    @FXML
    private Circle MinimizeButton;
    @FXML
    private Circle resizeButton;

    Connection con = MyDbConnection.getInstance().getConnexion();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    @FXML
    private StackPane stackPane;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private JFXButton adminButton;
    int user_id;
    public static UserSession user;
    private User u;
    private Usercrud crud;
    @FXML
    private JFXButton reset;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (con == null) {
            Errors.setTextFill(Color.TOMATO);
            Errors.setText("Server Error : Check");
        } else {
            Errors.setTextFill(Color.GREEN);
            Errors.setText("Server is up : Good to go");
        }
        crud = new Usercrud();

        u = new User();
    }

    public int AuthenticateUser(String email, String password) {
        User u = crud.VerifyUser(email);

        if ((email.equals(u.getEmail())) && (BCrypt.checkpw(password, u.getHash())) && (crud.VerifyEnable(email))) {
            return u.getId();
        } else {
            return 0;
        }
    }

    @FXML
    private void open_signup(MouseEvent event) {

        try {

            // Animation
            Parent root = FXMLLoader.load(getClass().getResource("/PI/GestionUsers/views/Home.fxml"));
            Scene SigninScene = open_signup.getScene();
            root.translateYProperty().set(SigninScene.getHeight());
            stackPane.getChildren().add(root);
            Timeline timeline = new Timeline();
            KeyValue keyValue = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_OUT);
            KeyFrame keyFrame = new KeyFrame(Duration.millis(200), keyValue);
            timeline.getKeyFrames().add(keyFrame);
            timeline.play();
            timeline.setOnFinished((ActionEvent event2) -> {
                stackPane.getChildren().remove(anchorPane);

            });

        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void signInButton(MouseEvent event) {
        if (event.getSource() == btnSignin) {
            //login here

            if (logIn() != 0) {
                try {
                    int res = logIn();
                    //add you loading or delays - ;-)
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();

                    user = UserSession.getInstace(res);
                    TrayNotification tray = new TrayNotification();
                    AnimationType type = AnimationType.SLIDE;

                    tray.setAnimationType(type);
                    tray.setRectangleFill(Color.valueOf("#16cabd"));
                    tray.setTitle("Scrumify App");
                    tray.setMessage("You are logged in  !");
//                    Image img = new Image(Main.class.getResourceAsStream("/PI/Images/scrumify.png"));
//                    tray.setImage(img);
                    //tray.setNotificationType(NotificationType.SUCCESS);
                    tray.showAndDismiss(Duration.millis(3000));
//                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/scrumifyd/GestionProjets/views/Dashboard.fxml"))));
//                    sp.setUserId(res);
//                    stage.show();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/PI/GestionUsers/views/Dashboard.fxml"));
                    Parent root = (Parent) loader.load();
                    DashboardController sp = loader.getController();
                    sp.setUserId(res);
                    stage.setScene(new Scene(root));
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }

            }
        }
    }

    public SigninController() {
        con = MyDbConnection.getInstance().getConnexion();

    }

    //we gonna use string to check for status
    private int logIn() {
        int user_id = 0;
        String status = "Success";
        String email = Email.getText();
        String password = Password.getText();
        if (email.isEmpty() || password.isEmpty()) {
            setLblError(Color.TOMATO, "Empty credentials");
            status = "Error";
        } else {
            if ((AuthenticateUser(Email.getText(), Password.getText())) == 0) {
                setLblError(Color.TOMATO, "Enter Correct Email/Password or verify if your account is activated");
                status = "Error";
                user_id = 0;
            } else {
                setLblError(Color.GREEN, "Login Successful..Redirecting..");
                user_id = AuthenticateUser(Email.getText(), Password.getText());
            }
        }

        return user_id;
    }

    private void setLblError(Color color, String text) {
        Errors.setTextFill(color);
        Errors.setText(text);
        System.out.println(text);
    }

    @FXML
    private void ExitButton(MouseEvent event) {
        if (event.getSource() == ExitButton) {
            // get a handle to the stage
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            // do what you have to do
            stage.close();
        }
    }

    @FXML
    private void MinimizeButton(MouseEvent event) {
        if (event.getSource() == MinimizeButton) {
            // get a handle to the stage
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            // do what you have to do
            stage.setIconified(true);
        }
    }

    @FXML
    void resizeButton(MouseEvent event) {
        if (event.getSource() == resizeButton) {
            // get a handle to the stage
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            // do what you have to do
            stage.setMaxWidth(1920);
            stage.setMaxHeight(1080);
        }
    }



    @FXML
    private void adminButton(MouseEvent event) {
        if (event.getSource() == adminButton) {

            try {

                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/PI/GestionUsers/views/AdminDash.fxml")));
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(SigninController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void resetAction(ActionEvent event) {
        try {

            // Animation
            Parent root = FXMLLoader.load(getClass().getResource("/PI/GestionUsers/views/resetP.fxml"));
            Scene SigninScene = open_signup.getScene();
            root.translateYProperty().set(SigninScene.getHeight());
            stackPane.getChildren().add(root);
            Timeline timeline = new Timeline();
            KeyValue keyValue = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_OUT);
            KeyFrame keyFrame = new KeyFrame(Duration.millis(200), keyValue);
            timeline.getKeyFrames().add(keyFrame);
            timeline.play();
            timeline.setOnFinished((ActionEvent event2) -> {
                stackPane.getChildren().remove(anchorPane);

            });

        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
