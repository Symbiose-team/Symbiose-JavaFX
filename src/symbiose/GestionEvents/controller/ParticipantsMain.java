package symbiose.GestionEvents.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import symbiose.GestionEvents.models.User;
import symbiose.GestionEvents.utils.MyConnection;
import symbiose.GestionEvents.utils.SceneSelector;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;

public class ParticipantsMain implements Initializable {


    public TableColumn colfirstName;
    public TableColumn collastName;
    public TableColumn colEmail;
    public TableColumn colCIN;
    public TableColumn colBirthday;
    public TableView tvParticipants;
    Connection cnx;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            AfficherParticipants();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void AfficherParticipants() throws SQLException {
        ObservableList<User> userList= FXCollections.observableArrayList();
        cnx = MyConnection.getInstance().getConnection();
        Statement st = cnx.createStatement();
        String query="select * from user";
        ResultSet rst = st.executeQuery(query);
        User users;
        while (rst.next()){
            users= new User(rst.getInt("id"),rst.getString("first_name"),rst.getString("last_name"),
                    rst.getString("email"),rst.getInt("cin"),rst.getDate("birthday"));
            userList.add(users);
        }
        System.out.println(userList);

        //colID.setCellValueFactory(new PropertyValueFactory<Event, Integer>("id"));
        colfirstName.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
        collastName.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        colCIN.setCellValueFactory(new PropertyValueFactory<User, Integer>("cin"));
        colBirthday.setCellValueFactory(new PropertyValueFactory<User, Date>("birthday"));

        tvParticipants.setItems(userList);
    }

    public void handleMouseAction(MouseEvent mouseEvent) {
    }

    public void AjouterEvent(ActionEvent actionEvent) {
    }

    public void UpdateEvent(ActionEvent actionEvent) {
    }

    public void DeleteEvent(ActionEvent actionEvent) {
    }

    public void AddEvent(ActionEvent actionEvent) {
    }

    public void backToEvents() {
        SceneSelector.switchScreen("eventsMain");
    }
}
