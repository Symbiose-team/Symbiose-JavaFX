/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symbiose.GestionCommunication.Controller;

import static symbiose.GestionCommunication.Controller.MesReclamationsController.modif;
import symbiose.GestionCommunication.Services.ReclamationsService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author chaima
 */
public class ReclamationUpdateController implements Initializable {
    ReclamationsService RS = new ReclamationsService();

    @FXML
    private AnchorPane container;
    @FXML
    private TextField txt_sujet;
    @FXML
    private ComboBox<String> cbb_type;
    @FXML
    private TextArea txt_contenu;
    @FXML
    private Label lb_error;
    @FXML
    private Button btn_ok;
    @FXML
    private Button btn_back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          ObservableList list = FXCollections.observableArrayList("produit", "joueur", "autre");
                cbb_type.setItems(list);
   
                txt_sujet.setText( MesReclamationsController.modif.getSujet());
                txt_contenu.setText( MesReclamationsController.modif.getDesc());
                
        // TODO
    }    

    @FXML
    private void modifier(ActionEvent event) throws IOException, SQLException {
        MesReclamationsController.modif.setDesc(txt_contenu.getText());
        MesReclamationsController.modif.setSujet(txt_sujet.getText());    
                MesReclamationsController.modif.setSujet(txt_sujet.getText());         
        MesReclamationsController.modif.setEtat((String) cbb_type.getSelectionModel().getSelectedItem());
        RS.modifierReclamation(modif);
        
        
        AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("../FXML/MesReclamation.fxml"));
        container.getChildren().clear();
        container.getChildren().add(newLoadedPane);

    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("../FXML/MesReclamation.fxml"));
        container.getChildren().clear();
        container.getChildren().add(newLoadedPane);
    }
    
}
