package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.Event;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import utils.MyConnection;
import utils.NotificationAPI;
import utils.SceneDialog;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class FournisseurEvents implements Initializable {

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
    public AnchorPane anchorPane;


    Connection cnx;
    ValidationSupport validationSupport = new ValidationSupport();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            showEvent();
            btnParticipants.setDisable(true);
            //Validators();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    private void showEvent() throws SQLException {
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

    public void handleMouseAction(MouseEvent mouseEvent) {
        Event event = tvEvents.getSelectionModel().getSelectedItem();
        System.out.println(event.getDate());
        tfName.setText("" + event.getName());
        tfID.setText("" + event.getId());
        tfType.setText("" + event.getType());

        if(tfID.getText().isEmpty()){
            btnParticipants.setDisable(true);
        }else
            btnParticipants.setDisable(false);
    }

    public void AddEvent(ActionEvent actionEvent) throws SQLException {
        Validators();

        Integer numParticipants = 100;
        Integer numRemaining = 100;
        LocalDate date = Date.getValue();

        if (tfName.getText() == "" || tfType.getText() == "" || Date.getValue() == null){
            System.out.println("no");
            NotificationAPI.ErrorNotification("Cant add event","");

        }else{
            String query="INSERT INTO event(name,num_participants,num_remaining,type,date) " +
                    "VALUES ('"+tfName.getText()+"'," +
                    ""+numParticipants+"," +
                    ""+numRemaining+"," +
                    "'"+tfType.getText()+"'," +
                    "'"+date+"')";

            executeQuery(query);
            System.out.println("Event Added");
            NotificationAPI.SuccessNotification("Event Added","");
        }

        showEvent();
    }

    public void Validators(){
        validationSupport.registerValidator(tfName, Validator.createEmptyValidator("text is required"));
        validationSupport.registerValidator(tfType, Validator.createEmptyValidator("Type is required"));
        validationSupport.registerValidator(Date, Validator.createEmptyValidator("Date is required"));
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

    public void UpdateEvent(ActionEvent actionEvent) throws SQLException {
        LocalDate date = Date.getValue();

        if (tfName.getText() == "" || tfType.getText() == "" || Date.getValue() == null){
            System.out.println("no");
            NotificationAPI.ErrorNotification("Cant update event","");
        }else{
            String query="UPDATE event SET name = '"+tfName.getText()+"'," +
                    "type = '"+tfType.getText()+"', " +
                    "date = '"+date+"' " +
                    "WHERE id = '"+tfID.getText()+"'";

            executeQuery(query);
            System.out.println("Event Updated");
            NotificationAPI.SuccessNotification("Successfully updated event","");
        }

        showEvent();
    }

    public void DeleteEvent(ActionEvent actionEvent) throws SQLException {
        System.out.println(tfID.getText());
        String query="DELETE FROM event WHERE id ='" +tfID.getText() + "'";
        executeQuery(query);
        NotificationAPI.SuccessNotification("Event deleted","");
        System.out.println("Event Deleted");
        showEvent();
    }

    @FXML
    private void openUsers() {
        SceneDialog.openDialog("../gui/ParticipantsMain.fxml", anchorPane);
    }

}
