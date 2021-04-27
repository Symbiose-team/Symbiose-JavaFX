package symbiose.GestionMatches.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import symbiose.models.Notification;
//import symbiose.GestionMatch.utils.*;
//import symbiose.test.MyConnection;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NotificationsController implements Initializable  {

    @FXML
    private TableView<Notification> tvnotifications;

    @FXML
    private TableColumn<Notification, Integer> colid;

    @FXML
    private TableColumn<Notification, Integer> coluser;

    @FXML
    private TableColumn<Notification, Integer> colgame;

    @FXML
    private TableColumn<Notification, Integer> coljoinedby;

    @FXML
    private TableColumn<Notification, Byte> colseen;

    @FXML
    private TableColumn<Notification, String> coldesc;

    @FXML
    private Button btnmarkasread;

    @FXML
    private Button btnscene1;

    public void handlescene1() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../gui/Client.fxml"));
        Stage window = (Stage) btnscene1.getScene().getWindow();
        window.setScene(new Scene(root, 1200,600));
    }


    @FXML
    void handleButtonAction(ActionEvent event) {
       if (event.getSource()==btnmarkasread){
           markasread();
       }


    }
   /* @FXML
    void handleMouseAction(MouseEvent event) {
            Game games = tvgames.getSelectionModel().getSelectedItem();
            tfname.setText(""+games.getName());
    }*/


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        shownotifications();
    }



    public Connection getConnection(){
        Connection conn;
        try {
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/symbiose","root","");
            return conn;
        }catch (Exception exception){
            System.out.println("Erreur : "+exception.getMessage());
            return null;
        }
    }



    public ObservableList<Notification> getNotificationsList(){
        ObservableList<Notification> notificationlist = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * from notification WHERE notification.user_id=9 AND seen=0";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Notification notifications;
            while (rs.next()){
                notifications = new Notification(rs.getInt("id"),rs.getInt("user_id"),rs.getInt("game_id"),rs.getInt("joined_by_id"),rs.getByte("seen"),rs.getString("discr"));
                notificationlist.add(notifications);
            }

        }catch (Exception exception){
            exception.printStackTrace();
        }
        return notificationlist;
    }


    public void shownotifications() {
        ObservableList<Notification> list = getNotificationsList();
        colid.setCellValueFactory(new PropertyValueFactory<Notification, Integer>("id"));
        coluser.setCellValueFactory(new PropertyValueFactory<Notification, Integer>("userId"));
        colgame.setCellValueFactory(new PropertyValueFactory<Notification, Integer>("gameId"));
        coljoinedby.setCellValueFactory(new PropertyValueFactory<Notification, Integer>("joinedById"));
        colseen.setCellValueFactory(new PropertyValueFactory<Notification, Byte>("seen"));
        coldesc.setCellValueFactory(new PropertyValueFactory<Notification, String>("discr"));

        tvnotifications.setItems(list);

    }


    public void markasread(){
        Notification notification = tvnotifications.getSelectionModel().getSelectedItem();
        int id = notification.getId();
        Byte seen = 1;
        // String query="UPDATE game SET name =('"+tfname.getText()+"') WHERE id =('"+id+"')";
        String query = "UPDATE notification SET seen = ('"+seen+"') WHERE id = ('"+id+"')";

        executeQuery(query);
        shownotifications();

    }




    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }




}
