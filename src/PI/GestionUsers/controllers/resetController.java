/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PI.GestionUsers.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import symbiose.models.User;
import PI.GestionUsers.services.Usercrud;
import PI.utils.MyDbConnection;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author SkanderThabet
 */
public class resetController implements Initializable {

    @FXML
    private StackPane stackPane;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label Errors;
    @FXML
    private Pane vbox;
    @FXML
    private JFXButton btnSignin;
    @FXML
    private JFXTextField Email;
    @FXML
    private JFXButton open_signup;
    @FXML
    private JFXButton open_signin;
    @FXML
    private Circle ExitButton;
    @FXML
    private Circle resizeButton;
    @FXML
    private Circle MinimizeButton;
    private User u;
    private Usercrud crud;
    Connection con = MyDbConnection.getInstance().getConnexion();
    @FXML
    private Text lab;
    int etatrecaptcha = 0;
    Stage window;
    WebView webView2;
    WebEngine webEngine;
    @FXML
    private ImageView recaptchaCheckMark;

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
        window = new Stage();
        webView2 = new WebView();
        webEngine = webView2.getEngine();
        window.setOnCloseRequest(e -> {
////            System.out.println(webEngine.getTitle());
////            if (webEngine != null && webEngine.getTitle().contains("success")) {
////                etatrecaptcha = 1;
////                recaptchaCheckMark.setImage(new Image("Images/checkMark.png"));
////            }
////            System.out.println("etat recaptcha=" + etatrecaptcha);
     Boolean test1 = crud.validateEmailAddress(Email.getText());
        Boolean test2 = crud.isExist(Email.getText());

        if (test1 && test2) {
            sendEmailReset(Email.getText());
            Email.setVisible(false);
            btnSignin.setVisible(false);
            lab.setText("Check your email , a link has been sent .");

        }
        });
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(250);

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
    private void sendButton(MouseEvent event) {
        Boolean test1 = crud.validateEmailAddress(Email.getText());
        Boolean test2 = crud.isExist(Email.getText());

        if (test1 && test2) {
            sendEmailReset(Email.getText());
            Email.setVisible(false);
            btnSignin.setVisible(false);
            lab.setText("Check your email , a link has been sent .");

        }
    }

    private void sendEmailReset(String email) {

        try {

            String to = Email.getText();//change accordingly  destinateur
            final String user = "symbiose.application@gmail.com";//change accordingly  eky yebath
            final String password = "symbiose2021";//change accordingly
            //1) get the session object     

            Properties properties = System.getProperties();
            properties.setProperty("mail.transport.protocol", "smtp");
            properties.setProperty("mail.host", "smtp.gmail.com");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.port", "587");

            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.socketFactory.port", "465");
            properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            properties.put("mail.smtp.socketFactory.fallback", "false");

            Session session = Session.getDefaultInstance(properties,
                    new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, password);
                }
            });

            //2) compose message     
            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(user));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                message.setSubject("Reset you Symbiose Account password");

                //3) create MimeBodyPart object and set your message text     
                BodyPart messageBodyPart1 = new MimeBodyPart();
                InternetHeaders headers = new InternetHeaders();
                headers.addHeader("Content-type", "text/html; charset=UTF-8");

                String html = "Symbiose\n" + "Reset Password" + "\n<a href='#"+crud.findByEmail(email)+"'>Reset now</a>";
                MimeBodyPart body = new MimeBodyPart(headers, html.getBytes("UTF-8"));
                //messageBodyPart1.setHeader("Scrmify ", "Activate account !");
                // messageBodyPart1.setText("Activate now");
                //5) create Multipart object and add MimeBodyPart objects to this object      
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(body);
                //6) set the multiplart object to the message object  
                message.setContent(multipart);
                //7) send message  
                Transport.send(message);

            } catch (MessagingException ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {

            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void open_signin(MouseEvent event) {
        try {
            // Animation
            Parent root = FXMLLoader.load(getClass().getResource("/PI/GestionUsers/views/signin.fxml"));
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
    private void recaptcha(MouseEvent event) {
if(!Email.getText().isEmpty()){

        webView2.setPrefSize(400, 500);
        webEngine.setUserAgent("use required / intended UA string");
        webEngine.load("#");

        Button closeButton = new Button("Fermer");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(5);
        layout.getChildren().addAll(webView2);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
}
else 
{
    Errors.setText("You need to enter your email");
}

    }

}
