package symbiose.GestionMatches.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import symbiose.models.Game;
import symbiose.models.GameJoines;
import symbiose.utils.GameNotification;

import javax.mail.MessagingException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.ResourceBundle;

//import symbiose.test.MyConnection;

public class ClientController implements Initializable  {

    @FXML
    private TextField filterfield;

    @FXML
    private TextField captcha_text;

    @FXML
    private Label captcha_label;

    @FXML
    private TextField uname;

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
    private TableColumn<Game, String> colowner;

    @FXML
    private Button btnadd;

    @FXML
    private Button btnupdate;

    @FXML
    private Button btndelete;

    @FXML
    private Text tfuser;

    @FXML
    private Button btnscene2;

    @FXML
    private Button btnscene3;

    @FXML
    private Button joingame;

    @FXML
    private Button unjoingame;

    @FXML
    private TextField nbplayers;


    public void handlescene2() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../gui/Admin.fxml"));
        Stage window = (Stage) btnscene2.getScene().getWindow();
        window.setScene(new Scene(root, 1200,600));
    }

    public void handlescene3() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../gui/Notifications.fxml"));
        Stage window = (Stage) btnscene3.getScene().getWindow();
        window.setScene(new Scene(root, 1200,600));
    }



    @FXML
    void handleButtonAction(ActionEvent event) throws MessagingException, SQLException {
        if (event.getSource()==btnadd){
            addgame();

        }
        else if (event.getSource()==btndelete){
            deletegame();
        }
        else if (event.getSource()==btnupdate){
            updategame();
        }
        else if (event.getSource()==joingame){
            joingame();
        }
        else if (event.getSource()==unjoingame){
            unjoingame();
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
        captcha_label.setText(CaptchaUret());
        tfuser.setText(showuser());




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
        //String query = "select id, name, time from game";
        String query = "SELECT t1.id, t1.name, t1.time, t2.first_name FROM game t1 INNER JOIN user t2 ON t1.user_id = t2.id";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Game games;
            while (rs.next()){
                games = new Game(rs.getInt("id"),rs.getString("name"),rs.getTimestamp("time"),rs.getString(4));
                gamelist.add(games);
              //  System.out.println(rs.getString(4));
            }

        }catch (Exception exception){
            exception.printStackTrace();
        }
        return gamelist;
    }


    public String showuser (){
        Connection conn = getConnection();
        int id=9;
        String query = "SELECT first_name FROM user WHERE id = ('"+id+"')";
        Statement st;
        ResultSet rs;
        String s = new String();
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            TextField uname = new TextField();
            if (rs.next()) {
               s = rs.getString("first_name");
               // System.out.println(s);
            }

        }catch (Exception exception){
            exception.printStackTrace();
        }
        return s;
    }

    public Integer nbplayers (){
        Connection conn = getConnection();
        Game game = tvgames.getSelectionModel().getSelectedItem();
        int gid = game.getId();
        String query = "SELECT COUNT(user_id) FROM game_joines WHERE game_id = ('"+gid+"')";
        Statement st;
        ResultSet rs;
        Integer x = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            if (rs.next()) {
                x = rs.getInt("COUNT(user_id)");
                System.out.println(x);
            }

        }catch (Exception exception){
            exception.printStackTrace();
        }
        return x;
    }



    public void showgames() {
        ObservableList<Game> list = getGamesList();
        colid.setCellValueFactory(new PropertyValueFactory<Game, Integer>("id"));
        colname.setCellValueFactory(new PropertyValueFactory<Game, String>("name"));
        coltime.setCellValueFactory(new PropertyValueFactory<Game, Timestamp>("time"));
        colowner.setCellValueFactory(new PropertyValueFactory<Game, String>("userId"));

        tvgames.setItems(list);

    }

    public void addgame(){
            String s = captcha_label.getText();
            String x = captcha_text.getText();
        if ((s.equals(x)) && (!x.isEmpty()) && (x.length()>=5)){
            LocalDateTime time = LocalDateTime.now();
            Integer u =9;
            String query="INSERT INTO game(name,time,user_id) VALUES ('"+tfname.getText()+"','"+time+"','"+u+"')";
            executeQuery(query);
            showgames();
            captcha_label.setText(CaptchaUret());
        }
        else {
            GameNotification.ErrorNotification("Name must be atleast 5 characters!\nCheck captcha!","");
            captcha_label.setText(CaptchaUret());

        }





    }

    public void updategame(){
        Game game = tvgames.getSelectionModel().getSelectedItem();
        int id = game.getId();
        int uid = 9;
        // String query="UPDATE game SET name =('"+tfname.getText()+"') WHERE id =('"+id+"')";
        String query = "UPDATE game " +
                "SET name = ('"+tfname.getText()+"') WHERE id in ('"+id+"') AND user_id =('"+uid+"')";

        executeQuery(query);
        showgames();

    }

    public void deletegame(){
        Connection conn = getConnection();
        //String query = "select id, name, time from game";
        String query = "SELECT id, user_id from game";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Game games = null;
            while (rs.next()) {
                games = new Game(rs.getInt("id") ,rs.getInt("user_id"));
            }
            int uid = 9;
            Game game = tvgames.getSelectionModel().getSelectedItem();
            if ((game.getId() == games.getId()) && (games.getUserid() != uid) ){
                GameNotification.ErrorNotification("You dont have permession!","");

            }
            else {

                int gid = game.getId();
                String query2 = "DELETE FROM game WHERE id =('"+gid+"') AND user_id =('"+uid+"')";
                executeQuery(query2);
             //   GameNotification.SuccessNotification("Deleted!", "");
                showgames();

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
        nbplayers.setText(String.valueOf(nbplayers()));

    }

    public String CaptchaUret() {
        String CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder str = new StringBuilder();
        Random rnd = new Random();
        while (str.length() < 5) {
            int index = (int) (rnd.nextFloat() * CHARS.length());
            str.append(CHARS.charAt(index));
        }
        String generatedCaptcha = str.toString();
        return generatedCaptcha;
    }

     @FXML
    private void search(){
         FilteredList<Game> filterData = new FilteredList<>(getGamesList(), b -> true);
         filterfield.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
             filterData.setPredicate(pers -> {

                 if (newvalue == null || newvalue.isEmpty()) {
                     return true;
                 }
                 String typedText = newvalue.toLowerCase();
                 if (pers.getName().toLowerCase().indexOf(typedText) != -1) {

                     return true;
                 }

                 return false;
             }
             );
             SortedList<Game> sortedList = new SortedList<>(filterData);
             sortedList.comparatorProperty().bind(tvgames.comparatorProperty());
             tvgames.setItems(sortedList);


         });

     }

     public void joingame() throws SQLException, MessagingException {
         Connection conn = getConnection();
         //String query = "select id, name, time from game";
         String query = "SELECT * from game_joines";
         Statement st;
         ResultSet rs;
         try {
             st = conn.createStatement();
             rs = st.executeQuery(query);
             GameJoines gamejoines = null;
             while (rs.next()) {
                 gamejoines = new GameJoines(rs.getInt("game_id"), rs.getInt("user_id"));
                 //  System.out.println(rs.getString(4));
             }
             int uid = 9;
             Game game = tvgames.getSelectionModel().getSelectedItem();
             if ((gamejoines.getUserId()==uid) &&(gamejoines.getGameId()== game.getId()) ){
                 joingame.setDisable(true);
                 GameNotification.ErrorNotification("You are already joined to this match!","");

             }
             else {
                 int gid = game.getId();
                 //    Integer uid = 9;
                 String query2 = "INSERT INTO game_joines(game_id,user_id) VALUES ('" + gid + "','" + uid + "')";
                 //TOVERIFY
                 // String querty = "INSERT INTO game_joines (game_id, user_id) SELECT 1, 'S-BOX', projects.ID  FROM projects WHERE projects.PROJECT_NAME = 'BOX'";
                 executeQuery(query2);
                 sendnotifications();
                 GameNotification.SuccessNotification("You have joined this match!", "");
                 //Remove this for mail
                 //GameMail.sendMail("tpadbergv_m830v@bylup.com");

                 showgames();

             }
         } catch (SQLException throwables) {
             throwables.printStackTrace();
         }
     }

    public void sendnotifications(){
        Game game = tvgames.getSelectionModel().getSelectedItem();
        int gid = game.getId();
        Integer uid =9;
       // String query="DELETE FROM game_joines WHERE game_id =('"+gid+"') AND user_id =('"+uid+"')";
        String query = "INSERT INTO notification (user_id,game_id,joined_by_id,seen,discr) VALUES ('" + uid + "','" + gid + "','" + uid + "' , '0', 'joine')";
        executeQuery(query);

    }



    public void unjoingame(){
        Game game = tvgames.getSelectionModel().getSelectedItem();
        int gid = game.getId();
        Integer uid =9;
        String query="DELETE FROM game_joines WHERE game_id =('"+gid+"') AND user_id =('"+uid+"')";
        executeQuery(query);
        GameNotification.ErrorNotification("You have unjoined this match!","");
        joingame.setDisable(false);
        showgames();


    }


     }




