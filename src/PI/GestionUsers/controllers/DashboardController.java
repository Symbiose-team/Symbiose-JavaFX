package PI.GestionUsers.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import PI.GestionUsers.services.UserSession;
import symbiose.models.User;
import PI.GestionUsers.services.Usercrud;
import PI.Main;
import tray.animations.AnimationType;
import tray.notification.TrayNotification;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DashboardController implements Initializable {

    @FXML
    private Circle ExitButton;
    @FXML
    private Circle MinimizeButton;
    @FXML
    private Circle resizeButton;

    @FXML
    private StackPane contentPane;

    @FXML
    private JFXButton Projects;
    @FXML
    private JFXButton settings;
    @FXML
    private AnchorPane Dashboard;
    @FXML
    private JFXButton teams;

    int user_idd;
    String ava;
    @FXML
    public ImageView avatar;
    @FXML
    private JFXButton deconnexion;
    @FXML
    private JFXButton CalendarButton;
    @FXML
    private Label username;
    @FXML
    private JFXButton tasksOpen;
    @FXML
    private JFXButton activities;
    @FXML
    private JFXButton editprofile;
    @FXML
    private JFXDatePicker birthday;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            // TODO
            contentPane.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/PI/GestionUsers/views/Projects.fxml"));
            Parent root = (Parent) loader.load();
            SigninController s = new SigninController();
            user_idd = s.user.getUserId();
            ava = s.user.getAvatar(user_idd);
            this.username.setText("Hello ," + s.user.getUsername(user_idd));
            this.avatar.setImage(new Image("/PI/uploads/images/" + ava));
            contentPane.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setContentPane(StackPane contentPane) {
        this.contentPane = contentPane;
    }

    public StackPane getContentPane() {
        return contentPane;
    }

    public void setUserId(int user_id) {
        this.user_idd = user_id;
    }

    @FXML
    public void ExitButton(MouseEvent event) {
        if (event.getSource() == ExitButton) {
            // get a handle to the stage
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            // do what you have to do
            stage.close();
        }
    }

    @FXML
    public void MinimizeButton(MouseEvent event) {
        if (event.getSource() == MinimizeButton) {
            // get a handle to the stage
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            // do what you have to do
            stage.setIconified(true);
        }
    }

    @FXML
    public void resizeButton(MouseEvent event) {
        if (event.getSource() == resizeButton) {
            // get a handle to the stage
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            // do what you have to do
            if (stage.isMaximized()) {
                stage.setMaximized(false);
            } else {
                stage.setMaxWidth(1366);
                stage.setMaxHeight(720);
                stage.setMaximized(true);
            }

        }
    }



    public void loadUI(String module, String ui) {
        contentPane.getChildren().clear();

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/PI/" + module + "/views/" + ui + ".fxml"));

        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        contentPane.getChildren().add(root);
    }



    @FXML
    private void Projects(MouseEvent event) {

    }

    @FXML
    private void openTeams(MouseEvent event) {

    }

    @FXML
    private void deconnexion(MouseEvent event) {
        if (event.getSource() == deconnexion) {

            try {
                UserSession sess = UserSession.getInstace(user_idd);
                sess.cleanUserSession();
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                tray.setAnimationType(type);
                tray.setRectangleFill(Color.valueOf("#16cabd"));
                tray.setTitle("Symniose App");
                tray.setMessage("Logged out  !");
//                Image img = new Image(Main.class.getResourceAsStream("/scrumifyd/images/scrumify.png"));
//                tray.setImage(img);
                //tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(3000));
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/PI/GestionUsers/views/signin.fxml")));
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(SigninController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void CalendarButton(MouseEvent event) {

    }

    @FXML
    private void tasksOpen(MouseEvent event) {

    }

    @FXML
    private void activities(MouseEvent event) {

    }

    @FXML
    private void editprofile(ActionEvent event) {
        try {
            contentPane.getChildren().clear();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/PI/GestionUsers/views/Editprofile.fxml"));
            Parent root = (Parent) loader.load();
            editProfileController sp = loader.getController();
            sp.user_id = user_idd;
            System.out.println(user_idd);
            //User u = new User();
            Usercrud U = new Usercrud();
            User u = U.getUser(user_idd);
            sp.name.setText(u.getFirst_name());
            sp.lastname.setText(u.getLast_name());
            sp.email.setText(u.getEmail());
            String ava = u.getImage();
            //sp.avatar.setImage(new Image("/PI/GestionUsers/uploads/" + ava));

            contentPane.getChildren().add(root);
            //loadUI("GestionProjets","Projects");
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
