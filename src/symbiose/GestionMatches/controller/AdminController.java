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
import symbiose.models.*;
import symbiose.models.Game;
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

public class AdminController implements Initializable  {

    @FXML
    private TextField tfname;

    @FXML
    private TableView<Game> tvgames;

    @FXML
    private TableColumn<Game, Integer> colid;

    @FXML
    private TableColumn<Game, String> colname;

    @FXML
    private TableColumn<Game, Timestamp> coltime;

    @FXML
    private Button btnadd;

    @FXML
    private Button btnupdate;

    @FXML
    private Button btndelete;

    @FXML
    private Button btnscene1;

    @FXML
    private Button btnscene2;

    public void handlescene1() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../gui/Client.fxml"));
        Stage window = (Stage) btnscene1.getScene().getWindow();
        window.setScene(new Scene(root, 1200,600));
     }
    public void handlescene2() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../gui/Admin.fxml"));
        Stage window = (Stage) btnscene2.getScene().getWindow();
        window.setScene(new Scene(root, 700,500));
    }



    @FXML
    void handleButtonAction(ActionEvent event) {
        if (event.getSource()==btnadd){
            addgame();

        }
        else if (event.getSource()==btndelete){
            deletegame();
        }
        else if (event.getSource()==btnupdate){
            updategame();
        }
    }
   /* @FXML
    void handleMouseAction(MouseEvent event) {
            Game games = tvgames.getSelectionModel().getSelectedItem();
            tfname.setText(""+games.getName());
    }*/


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showgames();
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



    public ObservableList<Game> getGamesList(){
        ObservableList<Game> gamelist = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "select id, name, time from game";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Game games;
        while (rs.next()){
            games = new Game(rs.getInt("id"),rs.getString("name"),rs.getTimestamp("time"));
          gamelist.add(games);
        }

        }catch (Exception exception){
            exception.printStackTrace();
        }
            return gamelist;
    }


    public void showgames() {
        ObservableList<Game> list = getGamesList();
        colid.setCellValueFactory(new PropertyValueFactory<Game, Integer>("id"));
      colname.setCellValueFactory(new PropertyValueFactory<Game, String>("name"));
       coltime.setCellValueFactory(new PropertyValueFactory<Game, Timestamp>("time"));

        tvgames.setItems(list);

    }

    public void addgame(){
        LocalDateTime time = LocalDateTime.now();
        String query="INSERT INTO game(name,time) VALUES ('"+tfname.getText()+"','"+time+"')";
        executeQuery(query);
        showgames();
    }

    public void updategame(){
        Game game = tvgames.getSelectionModel().getSelectedItem();
        int id = game.getId();
       // String query="UPDATE game SET name =('"+tfname.getText()+"') WHERE id =('"+id+"')";
        String query = "UPDATE game " +
                "SET name = ('"+tfname.getText()+"') WHERE id in ('"+id+"')";
        executeQuery(query);
        showgames();

    }

    public void deletegame(){
        Game game = tvgames.getSelectionModel().getSelectedItem();
        int id = game.getId();
        String query="DELETE FROM game WHERE id =('"+id+"')";
        executeQuery(query);
        showgames();
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

    public void handleMouseAction(javafx.scene.input.MouseEvent mouseEvent) {
        Game games = tvgames.getSelectionModel().getSelectedItem();
        tfname.setText(""+games.getName());
    }




}
