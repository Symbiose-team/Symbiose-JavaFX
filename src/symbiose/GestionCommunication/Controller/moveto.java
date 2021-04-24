///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Controller;
//
//import com.jfoenix.controls.JFXButton;
//import com.jfoenix.controls.JFXCheckBox;
//import java.io.IOException;
//import java.net.URL;
//import java.util.ResourceBundle;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Button;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.TextArea;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.AnchorPane;
//
///**
// * FXML Controller class
// *
// * @author KattaX
// */
//public class moveto implements Initializable {
//   @FXML
//    private AnchorPane content;
//
//    @FXML
//    private JFXButton consulterBtn;
//
//    @FXML
//    private JFXButton ajouterBtn;
//
//    @FXML
//    private JFXButton btnDcx;
//
//    @FXML
//    private JFXButton supprimerBtn;
//
//    /**
//     * Initializes the controller class.
//     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//    }    
//
//    @FXML
//    private void ajouter(ActionEvent event) throws IOException {
//        AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/FXML/AjouterFormation.fxml"));
//        content.getChildren().clear();
//        content.getChildren().add(newLoadedPane);
//    }
//
//    @FXML
//    private void consulter(ActionEvent event) throws IOException {
//        AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/FXML/AfficherFormation.fxml"));
//        content.getChildren().clear();
//        content.getChildren().add(newLoadedPane);
//    }
//   
//     @FXML
//    private void supprimer(ActionEvent event) throws IOException {
//        AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/FXML/AfficherFormation.fxml"));
//        content.getChildren().clear();
//        content.getChildren().add(newLoadedPane);
//    }
//
//    
//}
