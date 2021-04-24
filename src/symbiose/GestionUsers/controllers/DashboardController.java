package symbiose.GestionUsers.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import symbiose.GestionUsers.services.UserSession;
import symbiose.models.User;
import symbiose.GestionUsers.services.Usercrud;
import symbiose.utils.SceneDialog;
import symbiose.utils.SceneSelector;
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
    private Label username;
    @FXML
    private JFXButton Product;

    @FXML
    private JFXButton events;

    @FXML
    private JFXButton reservation;

    @FXML
    private JFXButton leaderboard;

    @FXML
    private JFXButton chat;

    @FXML
    private JFXButton editprofile;
    @FXML
    private JFXDatePicker birthday;
    @FXML
    private Label log_user;
    public static UserSession user;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SigninController s = new SigninController();
        user_idd = s.user.getUserId();
        this.username.setText("Hello ," + s.user.getUsername(user_idd));
        this.log_user.setText("Logged in as "+s.user.getRole(user_idd));
//        try {
//            // TODO
//            contentPane.getChildren().clear();
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/PI/GestionUsers/views/Projects.fxml"));
//            Parent root = (Parent) loader.load();
//            SigninController s = new SigninController();
//            user_idd = s.user.getUserId();
//            ava = s.user.getAvatar(user_idd);
//            this.username.setText("Hello ," + s.user.getUsername(user_idd));
//            this.avatar.setImage(new Image("/PI/uploads/images/" + ava));
//            contentPane.getChildren().add(root);
//        } catch (IOException ex) {
//            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
//        }

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
//                Image img = new Image(Main.class.getResourceAsStream("/PI/Images/symbiose-logo.png"));
//                tray.setImage(img);
//                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(3000));
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/symbiose/GestionUsers/views/signin.fxml")));
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(SigninController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     *
     * Pour l'integration !!!!!!!!!
     * NB ! : dans FXML il faut ajouter onMouseClicked=#nom de la fonction de chaque module !!
     */
    @FXML
    private void reservation(MouseEvent event) {

    }

    @FXML
    private void teams(MouseEvent event) {

    }

    @FXML
    private void events(MouseEvent event) throws IOException {
        contentPane.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/symbiose/GestionEvents/gui/EventsMain.fxml"));
        Parent root = (Parent) loader.load();
        contentPane.getChildren().add(root);
    }
    @FXML
    private void eventsConfirm(MouseEvent event) throws IOException {
        contentPane.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/symbiose/GestionEvents/gui/InvalidEvents.fxml"));
        Parent root = (Parent) loader.load();
        contentPane.getChildren().add(root);
    }
    @FXML
    private void chat(MouseEvent event) throws IOException {
        contentPane.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/symbiose/GestionCommunication/FXML/firstdashboard.fxml"));
        Parent root = (Parent) loader.load();
        contentPane.getChildren().add(root);
        }
    @FXML
    private void leaderboard(MouseEvent event) {

        }
    @FXML
    private void Product(MouseEvent event) {

    }

    @FXML
    private void editprofile(ActionEvent event) {
        try {
            contentPane.getChildren().clear();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/symbiose/GestionUsers/views/Editprofile.fxml"));
            Parent root = (Parent) loader.load();
            editProfileController sp = loader.getController();
            sp.user_id = user_idd;
            System.out.println(user_idd);
            //User u = new User();
            Usercrud U = new Usercrud();
            User u = U.getUser(user_idd);
            user = UserSession.getInstace(user_idd);
            sp.adresse.setText(user.getAdresse(user_idd)); //fix with session !
            sp.name.setText(u.getFirst_name());
            sp.lastname.setText(u.getLast_name());
            sp.email.setText(u.getEmail());
            sp.genre.setValue(u.getGenre());
            //sp.adresse.setText(u.getAdresse());
            sp.phone.setText(user.getPhone(user_idd));
            sp.birthday.setValue(LocalDate.parse(user.getBirthday(user_idd)));
            sp.cin.setText(user.getCin(user_idd));

            String ava = u.getImage();
            //sp.avatar.setImage(new Image("/PI/GestionUsers/uploads/images/" + ava));

            contentPane.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
