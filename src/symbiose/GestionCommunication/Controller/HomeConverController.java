/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symbiose.GestionCommunication.Controller;

import symbiose.GestionCommunication.Entities.Conversation;
import symbiose.GestionCommunication.Entities.Product;
import symbiose.GestionCommunication.Entities.User;
import symbiose.GestionCommunication.Services.MessageService;
import symbiose.GestionCommunication.Services.ProductService;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;


/**
 *
 * @author LENOVO
 */
public class HomeConverController implements Initializable {
        User currentuser = new User();
 @FXML
    private AnchorPane content;
    @FXML
    private Label label;
    
    @FXML
    private ListView list_formation;
    
    @FXML
    private ImageView img_logo;
   
    
    @FXML
    private Label txt_panier;
    
    private List<Conversation> data ;
    ObservableList observ_list_data = FXCollections.observableArrayList();
    
    private static HomeConverController instance ;

    public HomeConverController() {
        instance = this ;
    }
    
    public static HomeConverController getInstance(){
        return instance ;
    }
    
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        img_logo.setImage(  new Image(getClass().getResource("../FXML/logo.png").toString(), true));
        img_logo.setFitWidth(200);        img_logo.setFitHeight(200);


        try {
            setListView();
        } catch (SQLException ex) {
            Logger.getLogger(HomeConverController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
    public void setListView () throws SQLException{
MessageService MS=new MessageService();

        data = new ArrayList<>();
        data= MS.GetAllConversation(currentuser.getId());
        
        
  
        
        observ_list_data.setAll(data);
        list_formation.setItems(observ_list_data);
        list_formation.setCellFactory(new Callback<ListView<Conversation>, javafx.scene.control.ListCell<Conversation>>()
        {
            @Override
            public ListCell<Conversation> call(ListView<Conversation> listView)
            {
                return new ListConverItem();
            }
        });
     }
    
    
      @FXML
    void back(ActionEvent event) {
           try {
            // TODO
            Pane newLoadedPane  =  FXMLLoader.load(getClass().getResource("../FXML/chat.fxml"));
            content.getChildren().clear();
            content.getChildren().add(newLoadedPane);
            
        } catch (IOException ex) {
            Logger.getLogger(HomeShopController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
