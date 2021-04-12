package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Event;
import utils.MyConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class EventsMainController implements Initializable {

    public TextField tfName;
    public TextField tfParticipants;
    public TextField tfRemaining;
    public TextField tfType;
    public DatePicker Date;
    public TableView<Event> tvEvents;
    public TableColumn<Event, String> colName;
    public TableColumn<Event, Integer> colParticipants;
    public TableColumn<Event, Integer> colRemaining;
    public TableColumn<Event, String> colType;
    public TableColumn<Event, Date> colDate;
    public Button btnInsert;
    public Button btnUpdate;
    public Button btnDelete;
    public TableColumn colState;
    public TableColumn colID;
    public TextField tfID;
    Connection cnx;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            AfficherEvent();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void AfficherEvent() throws SQLException {
        ObservableList<Event> eventList= FXCollections.observableArrayList();
        cnx = MyConnection.getInstance().getConnection();
        Statement st = cnx.createStatement();
        String query="select * from event";
        ResultSet rst = st.executeQuery(query);
        Event events;
        while (rst.next()){
            events= new Event(rst.getInt("id"),rst.getString("name"),rst.getInt("num_participants"),
                    rst.getInt("num_remaining"),rst.getString("type"),rst.getTimestamp("date"),rst.getByte("state"));
            eventList.add(events);
        }
        System.out.println(eventList);

        colID.setCellValueFactory(new PropertyValueFactory<Event, Integer>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<Event, String>("name"));
        colParticipants.setCellValueFactory(new PropertyValueFactory<Event, Integer>("numParticipants"));
        colRemaining.setCellValueFactory(new PropertyValueFactory<Event, Integer>("numRemaining"));
        colType.setCellValueFactory(new PropertyValueFactory<Event, String>("type"));
        colDate.setCellValueFactory(new PropertyValueFactory<Event, Date>("date"));
        colState.setCellValueFactory(new PropertyValueFactory<Event, Byte>("state"));

        tvEvents.setItems(eventList);
    }

    public void AjouterEvent(ActionEvent actionEvent) throws SQLException {
        System.out.println(tfName.getText());
        System.out.println(tfParticipants.getText());
        System.out.println(tfRemaining.getText());
        System.out.println(tfType.getText());
        System.out.println(Date.getAccessibleText());
        LocalDate date = Date.getValue();
        System.out.println(date);

        String query="INSERT INTO event(name,num_participants,num_remaining,type,date) " +
                "VALUES ('"+tfName.getText()+"',"+tfParticipants.getText()+","+tfRemaining.getText()+",'"+tfType.getText()+"','"+date+"')";
        executeQuery(query);
        AfficherEvent();
    }

    public void UpdateEvent(ActionEvent actionEvent) throws SQLException {
        LocalDate date = Date.getValue();
        String query="UPDATE event SET name = '"+tfName.getText()+"',num_participants = "+tfParticipants.getText()+",num_remaining = "+tfRemaining.getText()+", type = '"+tfType.getText()+"', date = '"+date+"' WHERE id = "+tfID.getText()+"";
        executeQuery(query);
        AfficherEvent();
    }

    public void DeleteEvent(ActionEvent actionEvent) throws SQLException {
        String query="DELETE FROM event WHERE name ='" +tfName.getText() + "'";
        executeQuery(query);
        AfficherEvent();
    }

    private void executeQuery(String query) {
        cnx = MyConnection.getInstance().getConnection();
        Statement st;
        try {
            st = cnx.createStatement();
            st.executeUpdate(query);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void handleMouseAction(MouseEvent mouseEvent) {
        Event event = tvEvents.getSelectionModel().getSelectedItem();
        System.out.println("name " + event.getName());
        System.out.println("num_participants " + event.getDate());

        tfID.setText("" + event.getId());
        tfName.setText("" + event.getName());
        tfParticipants.setText("" + event.getNumParticipants());
        tfRemaining.setText("" + event.getNumRemaining());
        tfType.setText("" + event.getType());
    }
}
