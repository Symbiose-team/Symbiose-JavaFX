/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symbiose.GestionCommunication.Controller;

import symbiose.GestionCommunication.Entities.Commentaire;
import symbiose.GestionCommunication.Entities.Conversation;
import symbiose.GestionCommunication.Entities.Message;
import symbiose.GestionCommunication.Entities.Product;
import symbiose.GestionCommunication.Entities.Publication;
import symbiose.GestionCommunication.Entities.User;
import symbiose.GestionCommunication.Services.CommentaireService;
import symbiose.GestionCommunication.Services.MessageService;
import symbiose.GestionCommunication.Services.UserService;
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
import javafx.scene.paint.Color;

/**
 *
 * @author LENOVO
 */
public class ListConverItemController {
    
     User user= new User();
     
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
    private ListView<Message> listview;
    @FXML
    private ImageView img;
            ObservableList <Message> list = FXCollections.observableArrayList();

    public ListConverItemController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../FXML/ItemConver.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    Conversation prod =new Conversation();

    public void setInfo(Conversation data) throws SQLException {
        UserService US=new UserService();
        del1.setVisible(false);

        listview.setVisible(false);
        

        addb.setVisible(false);
        inputcom.setVisible(false);
        
        btn_comm.managedProperty().bind(btn_comm.visibleProperty());
        
        btn_comm.setVisible(true);
        
        if (data != null) {
            System.out.println(data.toString());
            lb_titre.setText("Conversation with :");
            lb_description.setText("");
            if (user.getId()!=data.getUser2().getId()){
                            lb_formateur.setText(US.Getnamebyid(data.getUser2().getId()));
                            img.setImage(  new Image(getClass().getResource("../FXML/"+US.Getpicbyid(data.getUser2().getId())).toString(), true));
            }
            else if (user.getId()!=data.getUser1().getId()){
                            lb_formateur.setText(US.Getnamebyid(data.getUser1().getId()));
                                                        img.setImage(  new Image(getClass().getResource("../FXML/chaima.jpg").toString(), true));

                         //   img.setImage(  new Image(getClass().getResource("/FXML/"+US.Getpicbyid(data.getUser1().getId())).toString(), true));


            }

            
                    MessageService ms=new MessageService();

           
            list.addAll(ms.GetMessagesParConverID(data.getId()));
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
                
                
            });
            
          
        } else {
            System.out.println("data empty !");
        }

    }
    Conversation trans=new Conversation();

    public Conversation transmission(Conversation data){
        trans=data;
        return trans;
    }
    @FXML
    void add(ActionEvent event) throws SQLException {
        System.out.println( "++++++++++++++++"+lb_titre.getText());
         Publication p =new Publication();
         p.setId(1);
        Message m =new Message();
        m.setContenu(inputcom.getText());
        m.setUser(user);
        m.setConversation_id(prod.getId());
        inputcom.clear();
                            MessageService ms=new MessageService();

            listview.getItems().clear();
            ObservableList a = FXCollections.observableArrayList();
            ms.AddMessage(m);

            listview.getItems().addAll(a);
                 a.addAll(ms.GetMessagesParConverID(trans.getId()));
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
