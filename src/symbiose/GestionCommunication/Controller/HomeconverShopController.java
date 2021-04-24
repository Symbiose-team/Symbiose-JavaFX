/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symbiose.GestionCommunication.Controller;

import symbiose.GestionCommunication.Entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class HomeconverShopController implements Initializable {

    
    @FXML
    private AnchorPane container;
    
    private static HomeconverShopController instance ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //goHomeSHop();
goReclamAdd();    } 

    public HomeconverShopController() {
        instance = this ;
    }
    
    public static HomeconverShopController getInstance(){
        return instance ;
    }
    
    
    
 
    
    public void goHomeSHop(){
        try {
            // TODO
            Pane newLoadedPane  =  FXMLLoader.load(getClass().getResource("../FXML/HomeProduct.fxml"));
            container.getChildren().clear();
            container.getChildren().add(newLoadedPane);
            
        } catch (IOException ex) {
            Logger.getLogger(HomeconverShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
   
    
    public void goAdminReclam(){
        try {
            // TODO
            Pane newLoadedPane  =  FXMLLoader.load(getClass().getResource("views/PromotionList.fxml"));
            container.getChildren().clear();
            container.getChildren().add(newLoadedPane);
            
        } catch (IOException ex) {
            Logger.getLogger(HomeconverShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void goReclamAdd(){
        try {
            // TODO
            Pane newLoadedPane  =  FXMLLoader.load(getClass().getResource("../FXML/ReclamationForm.fxml"));
            container.getChildren().clear();
            container.getChildren().add(newLoadedPane);
            
        } catch (IOException ex) {
            Logger.getLogger(HomeconverShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void goMesReclam(){
        try {
            // TODO
            Pane newLoadedPane  =  FXMLLoader.load(getClass().getResource("../FXML/MesReclamation.fxml"));
            container.getChildren().clear();
            container.getChildren().add(newLoadedPane);
            
        } catch (IOException ex) {
            Logger.getLogger(HomeconverShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
}
