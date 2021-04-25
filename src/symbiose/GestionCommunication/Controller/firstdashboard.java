/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symbiose.GestionCommunication.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import symbiose.utils.SceneDialog;

/**
 * FXML Controller class
 *
 * @author medal
 */
public class firstdashboard implements Initializable {

    @FXML
    private AnchorPane content;
    @FXML
    private Button btn_mes_achat1;
    @FXML
    private Button btn_mes_achat11;
    @FXML
    private Button btn_mes_achat12;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void reclamation(ActionEvent event) throws IOException {
        AnchorPane newLoadedPane = null;

                newLoadedPane = FXMLLoader.load(getClass().getResource("../FXML/MesReclamation.fxml"));
           
                content.getChildren().clear();
                content.getChildren().add(newLoadedPane);
    }

    @FXML
    private void product(ActionEvent event) throws IOException {
         Stage primaryStage = new Stage(); 
    Parent root = null;
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/HomeProduct.fxml"));
        root = loader.load();
        HomeChat controller = loader.getController();
       
    } catch (Exception e) {
        e.printStackTrace();
    }

    Scene scene = new Scene(root, 1400, 600);
    primaryStage.setResizable(false);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Conversations");
    primaryStage.show();

    }

    @FXML
    private void chat(ActionEvent event) throws IOException {
         Stage primaryStage = new Stage(); 
    Parent root = null;
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/chat.fxml"));
        root = loader.load();
        HomeChat controller = loader.getController();
       
    } catch (Exception e) {
        e.printStackTrace();
    }

    Scene scene = new Scene(root, 1400, 600);
    primaryStage.setResizable(false);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Conversations");
    primaryStage.show();

    }

    public void chart(ActionEvent actionEvent) {

        Stage primaryStage = new Stage();
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/chartgraph.fxml"));
            root = loader.load();
            HomeChat controller = loader.getController();

        } catch (Exception e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root, 1400, 600);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Chart");
        primaryStage.show();
    }
}
