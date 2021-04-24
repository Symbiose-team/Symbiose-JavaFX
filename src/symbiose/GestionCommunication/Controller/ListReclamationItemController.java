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
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;


/**
 *
 * @author LENOVO
 */
public class ListReclamationItemController {
User user=new User();
    @FXML
    private AnchorPane container;
    @FXML
    private Label lb_sujet;
    @FXML
    private Label lb_type;
    @FXML
    private Label lb_date;
    @FXML
    private Label lb_nbr_msg;
    @FXML
    private Label lb_user;
    @FXML
    private HBox hb_user;

    @FXML
    private ImageView img;

    public ListReclamationItemController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../FXML/ReclamationItem.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setInfo(Reclamation data) throws SQLException {

        if (data != null) {
            System.out.println(data.toString());
            lb_sujet.setText(data.getSujet());
            lb_type.setText(data.getDesc());
            lb_date.setText(data.getDate());
            
            
            ReclamationsService RS  = new ReclamationsService();
            lb_nbr_msg.setText(""+RS.getAll().size());
            
            if(user.getRole().equals("joueur")){
                hb_user.setVisible(false);
            }else{
                hb_user.setVisible(true);
                lb_user.setText(data.getUser().getFirstname());
            }

        } else {
            System.out.println("data empty !");
        }

    }

    public AnchorPane getBox() {
        return container;
    }
}
