/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symbiose.GestionCommunication.com.client.chatwindow;

import symbiose.GestionCommunication.Entities.Message;
import symbiose.GestionCommunication.Entities.User;
import symbiose.GestionCommunication.Services.MessageService;
import com.sun.javafx.property.adapter.PropertyDescriptor.Listener;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author medal
 */
public class ChatController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private ImageView userImageView;
    @FXML
    private HBox onlineUsersHbox;
    @FXML
    private Label onlineCountLabel;
    @FXML
    private ListView<User> userList;
    @FXML
    private ListView<?> chatPane;
    @FXML
    private TextArea messageBox;
    @FXML
    private Button buttonSend;
    @FXML
    private Button recordBtn;
    @FXML
    private ImageView microphoneImageView;
    MessageService MS=new MessageService();
    @FXML
    private ComboBox<String> JoueursComboBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        // TODO
    }    


    @FXML
    private void sendMethod(KeyEvent event) {
    }

    @FXML
    private void sendButtonAction(ActionEvent event) throws SQLException {
        Message m=new Message();
        User user=new User();
        String msg = messageBox.getText();
        m.setContenu(msg);
        m.setUser(user);
        if (!messageBox.getText().isEmpty()) {
            
            MS.AddMessage(m);
            messageBox.clear();
        
    }
    }

    @FXML
    private void recordVoiceMessage(MouseEvent event) {
    }
    
}
