/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symbiose.GestionCommunication.Controller;

import symbiose.GestionCommunication.Entities.Product;
import symbiose.GestionCommunication.Services.ProductService;
import com.sun.javafx.scene.control.skin.VirtualFlow;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;


/**
 *
 * @author LENOVO
 */
public class HomeProductController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private ListView list_formation;
    
    @FXML
    private ImageView img_logo;
    
    @FXML
    private Button btn_panier;
    
    @FXML
    private Button btn_mes_achat;
    
    @FXML
    private Button btn_deco;
    
    @FXML
    private Label txt_panier;
    
    private List<Product> data ;
    ObservableList observ_list_data = FXCollections.observableArrayList();
    
    private static HomeProductController instance ;

    public HomeProductController() {
        instance = this ;
    }
    
    public static HomeProductController getInstance(){
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
        img_logo.setFitWidth(100);        img_logo.setFitHeight(100);


        try {
            setListView();
        } catch (SQLException ex) {
            Logger.getLogger(HomeProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
    public void setListView () throws SQLException{
                ProductService PS=new ProductService();

        txt_panier.setText(String.valueOf(PS.countp()));
        data = new ArrayList<>();
        data= PS.getAll();
        
        
  
        
        observ_list_data.setAll(data);
        list_formation.setItems(observ_list_data);
        list_formation.setCellFactory(new Callback<ListView<Product>, javafx.scene.control.ListCell<Product>>()
        {
            @Override
            public ListCell<Product> call(ListView<Product> listView)
            {
                return new ListProductItem();
            }
        });
     }
    
    public  void UpdatePanierText(){

    }
    
    
}
