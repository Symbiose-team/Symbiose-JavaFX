/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symbiose.GestionCommunication.Controller;

import symbiose.GestionCommunication.Entities.Conversation;
import symbiose.GestionCommunication.Entities.Message;
import symbiose.GestionCommunication.Entities.User;
import symbiose.GestionCommunication.Entities.chat;
import symbiose.GestionCommunication.Services.MessageService;
import symbiose.GestionCommunication.Services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author medal
 */
public class HomeChat implements Initializable {
    User currentuser = new User();
UserService US=new UserService();
MessageService MS=new MessageService();
    @FXML
    private AnchorPane content;
   
    @FXML
    private ComboBox<String> usercombo;
    @FXML
    private Button startc;
    @FXML
    private Button startc1;
         ObservableList convos = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
         ObservableList list = FXCollections.observableArrayList();
    try {
        list.addAll(US.Allusers());
        System.out.println(list);
    } catch (SQLException ex) {
        Logger.getLogger(HomeChat.class.getName()).log(Level.SEVERE, null, ex);
    }

                usercombo.setItems(list);
    
       
        // TODO
    }    


    @FXML
    private void startconvo(ActionEvent event) throws SQLException, IOException {
        
        String name = usercombo.getSelectionModel().getSelectedItem();
        
        MS.AddConversation(currentuser,US.GetUserbyname(name));
        convos.clear();
        convos.addAll(MS.GetAllConversation(currentuser.getId()));
        Stage primaryStage = new Stage(); 
    Parent root = null;
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/HomeConver.fxml"));
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
    private void check(ActionEvent event) {
            Stage primaryStage = new Stage(); 
    Parent root = null;
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/HomeConver.fxml"));
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
}
