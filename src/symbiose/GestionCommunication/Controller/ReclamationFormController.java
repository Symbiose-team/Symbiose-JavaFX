/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symbiose.GestionCommunication.Controller;

import symbiose.GestionCommunication.Entities.Reclamation;
import symbiose.GestionCommunication.Entities.User;
import symbiose.GestionCommunication.Services.ReclamationsService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.HTMLEditor;
import javax.mail.Session;


/**
 * FXML Controller class
 *
 * @author chaima
 */
public class ReclamationFormController implements Initializable {
    User user=new User();
    @FXML
    private Label lb_titre;
    @FXML
    private TextField txt_sujet;
    @FXML
    private ComboBox cbb_type;
    @FXML
    private TextArea txt_contenu;
    @FXML
    private Label lb_error;
    @FXML
    private Button btn_ok;
    @FXML
    private Button btn_back;
    @FXML
    private AnchorPane container;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbb_type.getItems().add("produit");
        cbb_type.getItems().add("joueur");
        cbb_type.getItems().add("autre");

        btn_ok.setOnAction(e -> {
            lb_error.setText("");
            if (txt_sujet.getText().trim().length() == 0) {
                lb_error.setText("vous devez saisir le sujet de la réclamation !");
            } else if (cbb_type.getSelectionModel().getSelectedIndex() < 0) {
                lb_error.setText("vous devez choisir un type !");
            } else if (txt_contenu.getText().trim().length() == 0) {
                lb_error.setText("vous devez saisir le contenu de la réclamation !");
            } else {
                ReclamationsService RS = new ReclamationsService();
                Reclamation r = new Reclamation();
                r.setUser(user);
                r.setSujet(txt_sujet.getText());
                r.setEtat((String) cbb_type.getValue());
                r.setDesc(txt_contenu.getText());
                try {
                    RS.ajouterReclamation(r);
                } catch (SQLException ex) {
                    Logger.getLogger(ReclamationFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                   // MailReclamation.sendMail(user.getEmail(), r);
                } catch (Exception ex) {
                    Logger.getLogger(ReclamationFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
                 AnchorPane newLoadedPane = null;
            try {
                newLoadedPane = FXMLLoader.load(getClass().getResource("../FXML/MesReclamation.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(ReclamationFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
                container.getChildren().clear();
                container.getChildren().add(newLoadedPane);
            }

        });
        btn_back.setOnAction(e -> {
             AnchorPane newLoadedPane = null;
            try {
                newLoadedPane = FXMLLoader.load(getClass().getResource("../FXML/MesReclamation.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(ReclamationFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
                container.getChildren().clear();
                container.getChildren().add(newLoadedPane);
        });
    }

}
