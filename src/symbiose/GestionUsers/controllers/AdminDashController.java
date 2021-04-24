package symbiose.GestionUsers.controllers;

import symbiose.GestionUsers.services.UserSession;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SkanderThabet
 */
public class AdminDashController implements Initializable {

    @FXML
    private Circle ExitButton;

    @FXML
    private Circle MinimizeButton;

    @FXML
    private Circle resizeButton;

    @FXML
    private PieChart charts;

    @FXML
    private PieChart charts1;

    @FXML
    private Pane contentPane;

    @FXML
    private JFXButton Products;

    @FXML
    private JFXButton teams;

    @FXML
    private JFXButton reservations;

    @FXML
    private JFXButton communications;

    @FXML
    private JFXButton events;

    @FXML
    private JFXButton statistics;

    @FXML
    private AnchorPane Dashboard;

    @FXML
    Label user_id;

    int user_idd;

    @FXML
    private JFXButton Users;

    @FXML
    private JFXButton deconnexion;

    @FXML
    private StackPane contentPane1;

    public static UserSession user;

    @FXML
    private Label log_user;

    @FXML
    private Text user_name;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SigninController s = new SigninController();
        user_idd = s.user.getUserId();
        this.user_name.setText("Hello ," + s.user.getUsername(user_idd));
        this.log_user.setText("Logged in as "+s.user.getRole(user_idd));
        // TODO     
//        contentPane.getChildren().clear();
//        Parent root = null;
//        try {
//            root = FXMLLoader.load(getClass().getResource("/PI/GestionUsers/views/UsersAdmin.fxml"));
//
//        } catch (IOException ex) {
//            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        contentPane.getChildren().add(root);

    }

    public void setContentPane(StackPane contentPane) {
        this.contentPane1 = contentPane;
    }

    public StackPane getContentPane() {
        return contentPane1;
    }

    public void setUserId(int user_id) {
        this.user_idd = user_id;
        this.user_id.setText("" + user_id);
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



    @FXML
    private void Users(MouseEvent event) {
        if (event.getSource() == Users) {
//
            try {
//                Node node = (Node) event.getSource();
//                Stage stage = (Stage) node.getScene().getWindow();
//                stage.close();
//                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/PI/GestionUsers/views/UsersAdmin.fxml")));
//                stage.setScene(scene);
//                stage.show();
                contentPane.getChildren().clear();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/symbiose/GestionUsers/views/UsersAdmin.fxml"));
                Parent root = (Parent) loader.load();
                SigninController s = new SigninController();
                contentPane.getChildren().add(root);
            } catch (IOException ex) {
                Logger.getLogger(SigninController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }


    @FXML
    private void deconnexion(MouseEvent event) {
        if (event.getSource() == deconnexion) {

            try {

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
    private void statistics(MouseEvent event) {
//

    }
    @FXML
    private void Products(MouseEvent event) {

    }
    @FXML
    private void settings(MouseEvent event) {

    }

    private void settings(ActionEvent event) {


    }

    @FXML
    private void teams(MouseEvent event) {

    }
    @FXML
    private void events(ActionEvent event) {
    }
    @FXML
    private void communications(ActionEvent event) {
    }
    @FXML
    private void reservations(ActionEvent event) {
    }


}
