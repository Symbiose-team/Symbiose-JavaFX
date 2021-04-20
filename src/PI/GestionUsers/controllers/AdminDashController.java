package PI.GestionUsers.controllers;

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
    private JFXButton Projects;
    @FXML
    private JFXButton settings;
    @FXML
    private AnchorPane Dashboard;
    @FXML
    private JFXButton teams;

    @FXML
    Label user_id;

    int user_idd;
    @FXML
    private JFXButton Users;
    @FXML
    private JFXButton deconnexion;
    @FXML
    private JFXButton statistics;
    @FXML
    private StackPane contentPane1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO     
        contentPane.getChildren().clear();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/PI/GestionUsers/views/UsersAdmin.fxml"));

        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        contentPane.getChildren().add(root);

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

    public void loadUI(String module, String ui) {
        contentPane1.getChildren().clear();

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/PI/" + module + "/views/" + ui + ".fxml"));

        } catch (IOException ex) {
            Logger.getLogger(AdminDashController.class.getName()).log(Level.SEVERE, null, ex);
        }
        contentPane1.getChildren().add(root);
    }

    @FXML
    private void Projects(MouseEvent event) {

    }

    private void settings(MouseEvent event) {
        loadUI("PI", "/GestionUsers/views/settings.fxml");
    }

    private void settings(ActionEvent event) {
        loadUI("PI", "/GestionUsers/views/settings.fxml");

    }

    @FXML
    private void openTeams(MouseEvent event) {

    }

    @FXML
    private void Users(MouseEvent event) {

    }

    @FXML
    private void deconnexion(MouseEvent event) {
        if (event.getSource() == deconnexion) {

            try {

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
    private void OpenStatistics(MouseEvent event) {
//        contentPane1.getChildren().clear();
//
//        contentPane.getChildren().clear();
      
//        InterfaceProjet pr = new ProjectService();
//
//        charts = new PieChart(pr.getProjectGraphStatisticsB());
//        charts.setLayoutX(500);
//
//        charts1 = new PieChart(pr.getProjectTimeGraphStatisticsB());
//
//        contentPane.getChildren().addAll(charts, charts1);
//        contentPane1.getChildren().addAll(contentPane);

    }


    @FXML
    private void teams(ActionEvent event) {
    }


}
