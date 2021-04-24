/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symbiose.GestionCommunication.Controller;
import symbiose.GestionCommunication.Services.ReclamationsService;
import symbiose.GestionCommunication.Entities.Formation;
import symbiose.GestionCommunication.Entities.Reclamation;
import symbiose.GestionCommunication.Entities.User;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author chaima
 */
public class MesReclamationsController implements Initializable {
    ReclamationsService RS=new ReclamationsService();
    User user=new User(9,"Elliott","Brins");
  public static Reclamation modif = new Reclamation();

    @FXML
    private AnchorPane content;
    @FXML
    private TextField txt_search;
    @FXML
    private Button btn_add;
    @FXML
    private Button btn_deco;
    @FXML
    private Button btn_tri;
    @FXML
    private TableView<Reclamation> tableview;
    @FXML
    private Button btn_add1;
    @FXML
    private Button btn_deco1;
    
    @FXML
    private TableColumn<Reclamation, String> Titre;
    @FXML
    private TableColumn<Reclamation, String> description;
    @FXML
    private TableColumn<Reclamation, String> date;
    @FXML
    private TableColumn<Reclamation, String> categorie;
    @FXML
    private TableColumn<Reclamation, String> sender;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if ( user.getRole()=="joueur") {
             try {
            
            tableview.getItems().setAll(RS.rechercherReclamationParID(user.getId()));
        } catch (SQLException ex) {
            Logger.getLogger(MesReclamationsController.class.getName()).log(Level.SEVERE, null, ex);
        }
   
       
         Titre.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("sujet"));
        description.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("desc"));
        categorie.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("etat"));
        date.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("Date"));
                sender.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("first_name"));
                
  

        try {
            //        sender.setCellValueFactory(new PropertyValueFactory<Reclamation, String>(user.getFirstname()));
            tableview.getItems().setAll(RS.rechercherReclamationParID(user.getId()));
            

            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(MesReclamationsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        else {
            btn_add.setVisible(false);
            btn_add1.setVisible(false);
            btn_deco1.setVisible(false);
              try {
            
            tableview.getItems().setAll(RS.AllReclamation());
        } catch (SQLException ex) {
            Logger.getLogger(MesReclamationsController.class.getName()).log(Level.SEVERE, null, ex);
        }
   
       // ObservableList list = FXCollections.observableArrayList("Informatique", "langues", "Graphic design","Soft Skills");
                //combo.setItems(list);
         Titre.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("sujet"));
        description.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("desc"));
        categorie.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("etat"));
        date.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("Date"));
                sender.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("first_name"));
                
  

        try {
            //        sender.setCellValueFactory(new PropertyValueFactory<Reclamation, String>(user.getFirstname()));
            tableview.getItems().setAll(RS.AllReclamation());
            

            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(MesReclamationsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
      
       
    }    

    @FXML
    private void Rclamer(ActionEvent event) throws IOException {
         AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("../FXML/ReclamationForm.fxml"));
       content.getChildren().clear();
        content.getChildren().add(newLoadedPane);
    }

    @FXML
    private void tr(ActionEvent event) throws SQLException {
                    tableview.getItems().setAll(RS.rechercherReclamationParIDOrderbyDate(user.getId()));

    }

    @FXML
    private void modifier(ActionEvent event) throws IOException {
         AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("../FXML/ReclamationUpdate.fxml"));
        content.getChildren().clear();
        content.getChildren().add(newLoadedPane);
        modif = tableview.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        Reclamation f1 = (Reclamation) tableview.getSelectionModel().getSelectedItem();
   int selectedIndex = tableview.getSelectionModel().getSelectedIndex();
        System.out.println("++++++"+f1);
        RS.suprimerReclamation(f1);

   
       tableview.getItems().remove(selectedIndex);
    }
    
}
