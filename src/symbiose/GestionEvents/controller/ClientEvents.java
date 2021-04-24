package symbiose.GestionEvents.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import symbiose.GestionEvents.models.Event;
import org.controlsfx.validation.ValidationSupport;
import symbiose.GestionEvents.utils.MailAPI;
import symbiose.GestionEvents.utils.MyConnection;
import symbiose.GestionEvents.utils.NotificationAPI;
import symbiose.GestionEvents.utils.SceneDialog;

import javax.mail.MessagingException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ClientEvents implements Initializable {

    public TableView<Event> tvEvents;
    public TableColumn<Event, String> colName;
    public TableColumn<Event, Integer> colParticipants;
    public TableColumn<Event, Integer> colRemaining;
    public TableColumn<Event, String> colType;
    public TableColumn<Event, java.util.Date> colDate;

    public TextField tfName;
    public TextField tfType;
    public DatePicker Date;

    public Button btnInsert;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnParticipants;
    public TextField tfSearch;
    public TextField tfID;
    public Button btnJoin;
    public AnchorPane anchorPane;
    Connection cnx;
    ValidationSupport validationSupport = new ValidationSupport();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            showEvent();
            //Validators();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void handleMouseAction(MouseEvent mouseEvent) {
        //Validators();
        Event event = tvEvents.getSelectionModel().getSelectedItem();
        tfID.setText("" + event.getId());

        if (tfID.getText().isEmpty() ){
            btnJoin.setDisable(true);
            btnParticipants.setDisable(true);
        }else
            btnJoin.setDisable(false);
            btnParticipants.setDisable(false);
    }

    public void showEvent() throws SQLException {
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

        colName.setCellValueFactory(new PropertyValueFactory<Event, String>("name"));
        colParticipants.setCellValueFactory(new PropertyValueFactory<Event, Integer>("numParticipants"));
        colRemaining.setCellValueFactory(new PropertyValueFactory<Event, Integer>("numRemaining"));
        colType.setCellValueFactory(new PropertyValueFactory<Event, String>("type"));
        colDate.setCellValueFactory(new PropertyValueFactory<Event, java.util.Date>("date"));

        tvEvents.setItems(eventList);

        //Filtered list
        FilteredList<Event> filteredData = new FilteredList<>(eventList, b -> true);

        tfSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(event -> {

                if (newValue == null || newValue.isEmpty()){
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (event.getName().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }else if(event.getType().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }else if (String.valueOf(event.getState()).indexOf(lowerCaseFilter)!=-1){
                    return true;
                }
                else return false;
            });
        });

        SortedList<Event> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(tvEvents.comparatorProperty());

        tvEvents.setItems(sortedData);
    }

    public void openUsers(ActionEvent actionEvent) {
        SceneDialog.openDialog("../symbiose.GestionEvents.gui/ParticipantsMain.fxml", anchorPane);
    }

    public void joinEvent(ActionEvent actionEvent) throws SQLException, MessagingException {
        System.out.println("joined" + tfID.getText());
        cnx = MyConnection.getInstance().getConnection();
        Statement st = cnx.createStatement();
        String query="SELECT * FROM event WHERE id = " +tfID.getText() +"";
        ResultSet rst = st.executeQuery(query);

        Event events = null;

        while (rst.next()){
            events= new Event(rst.getInt("id"),rst.getString("name"),rst.getInt("num_participants"),
                    rst.getInt("num_remaining"),rst.getString("type"),rst.getTimestamp("date"),rst.getByte("state"));
        }

        System.out.println(events.getName());

        if (events.getNumRemaining() <= 0){
            btnJoin.setDisable(true);
            System.out.println("you cant join this event");

            NotificationAPI.ErrorNotification("Cant join event","");

        }else{
            Integer x = events.getNumRemaining();
            x -= 1;
            System.out.println("there are now :" + x);

            NotificationAPI.SuccessNotification("Event join successfull","Thank you for joining :)");

            MailAPI.sendMail("norgoddev@gmail.com");

            NotificationAPI.SuccessNotification("Successfully joined event","wait for email");

            String updateQuery ="UPDATE event SET num_remaining = '"+x+"'" +
                    "WHERE id = '"+tfID.getText()+"'";

            executeQuery(updateQuery);
            showEvent();

        }
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
}
