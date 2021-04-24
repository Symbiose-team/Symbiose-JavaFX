/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symbiose.GestionCommunication.Controller;

import symbiose.GestionCommunication.Entities.Commentaire;
import symbiose.GestionCommunication.Entities.Message;
import symbiose.GestionCommunication.Entities.Product;
import symbiose.GestionCommunication.Entities.Publication;
import symbiose.GestionCommunication.Entities.User;
import symbiose.GestionCommunication.Services.CommentaireService;
import symbiose.GestionCommunication.Services.MessageService;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author LENOVO
 */
public class ListProductItemController {
     User user= new User(9,"Elliott","Bins");
     
    @FXML
    private TextField inputcom;

    @FXML
    private Button addb;
@FXML
    private Label con;
    
    @FXML
    private AnchorPane container;
    @FXML
    private Label lb_titre;
    @FXML
    private Label lb_description;
    @FXML
    private Label lb_formateur;
    @FXML
    private Label lb_prix;
    @FXML
    private Button btn_comm;
   @FXML
    private Button del1;

    @FXML
    private ListView<Commentaire> listview;
    @FXML
    private ImageView img;
            ObservableList <Commentaire> list = FXCollections.observableArrayList();

    public ListProductItemController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../FXML/ProductItemShop.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    Product prod =new Product();

    public void setInfo(Product data) throws SQLException {
        del1.setVisible(false);

        listview.setVisible(false);
        addb.setVisible(false);
        inputcom.setVisible(false);
        
        btn_comm.managedProperty().bind(btn_comm.visibleProperty());
        
        btn_comm.setVisible(true);
        
        if (data != null) {
            System.out.println(data.toString());
            lb_titre.setText("Product :");
            lb_description.setText(data.getDescription());
            lb_formateur.setText(data.getCreated_at());
            lb_prix.setText(""+data.getPrice()+" TND");

            img.setImage(  new Image(getClass().getResource("/FXML/"+data.getFilename()).toString(), true));
                    CommentaireService cs=new CommentaireService();

           
            list.addAll(cs.GetCommentPerProduct(data));
            listview.getItems().addAll(list);
            transmission(data);
            btn_comm.setOnAction(e->{
               System.out.println(data.getId()); 
               prod.setId(data.getId());
                addb.setVisible(true);
        inputcom.setVisible(true);
              listview.setVisible(true);
        del1.setVisible(true);
                
                btn_comm.setVisible(true);
                HomeProductController.getInstance().UpdatePanierText();                //ConfirmAchatController.data = data ;
                //HomeShopController.getInstance().goConfirmAchat();
                
                
            });
            
          
        } else {
            System.out.println("data empty !");
        }

    }
    Product trans=new Product();

    public Product transmission(Product data){
        trans=data;
        return trans;
    }
    @FXML
    void add(ActionEvent event) throws SQLException {
        System.out.println( "++++++++++++++++"+lb_titre.getText());
         Publication p =new Publication();
         p.setId(1);
        Commentaire c =new Commentaire();
        c.setContenu(inputcom.getText());
        c.setUser(user);
        c.setProduct(prod);
        c.setPublication(p);
        inputcom.clear();
                            CommentaireService cs=new CommentaireService();

            listview.getItems().clear();
            ObservableList a = FXCollections.observableArrayList();
        cs.AddComment(c);

            listview.getItems().addAll(a);
                 a.addAll(cs.GetCommentPerProduct(trans));
                 listview.setItems(a);
                 
                 

        
    }
    public AnchorPane getBox() {
        return container;
    }
    
    

    @FXML
    void del(ActionEvent event) throws SQLException {
          int selectedIndex = listview.getSelectionModel().getSelectedIndex();

        listview.getItems().remove(selectedIndex);
        Commentaire c = (Commentaire) listview.getSelectionModel().getSelectedItems();
        CommentaireService cs=new CommentaireService();
        cs.DeleteComment(c);
    }
}
