package symbiose.GestionEvents.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import symbiose.GestionEvents.models.Event;
import symbiose.GestionEvents.utils.MyConnection;
import symbiose.GestionEvents.utils.NotificationAPI;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;

public class InvalidEvents implements Initializable {
    public TableView<Event> tvInvalidEvents;
    public TableColumn<Event, String> colName;
    public TableColumn<Event, Integer> colParticipants;
    public TableColumn<Event, Integer> colRemaining;
    public TableColumn<Event, String> colType;
    public TableColumn<Event, Date> colDate;
    public Button btnValid;
    public TableColumn colState;
    public TextField tfinvalidSearch;
    public TextField tfinvalidID;

    Connection cnx;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            showInvalidEvent();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void showInvalidEvent() throws SQLException {
        ObservableList<Event> invalidEventList= FXCollections.observableArrayList();

        cnx = MyConnection.getInstance().getConnection();
        Statement st = cnx.createStatement();
        String query="select * from event where state = 0";
        ResultSet rst = st.executeQuery(query);
        Event events;

        while (rst.next()){
            events= new Event(rst.getInt("id"),rst.getString("name"),rst.getInt("num_participants"),
                    rst.getInt("num_remaining"),rst.getString("type"),rst.getTimestamp("date"),rst.getByte("state"));
            invalidEventList.add(events);
        }

        colName.setCellValueFactory(new PropertyValueFactory<Event, String>("name"));
        colParticipants.setCellValueFactory(new PropertyValueFactory<Event, Integer>("numParticipants"));
        colRemaining.setCellValueFactory(new PropertyValueFactory<Event, Integer>("numRemaining"));
        colType.setCellValueFactory(new PropertyValueFactory<Event, String>("type"));
        colDate.setCellValueFactory(new PropertyValueFactory<Event, Date>("date"));
        colState.setCellValueFactory(new PropertyValueFactory<Event, Byte>("state"));

        tvInvalidEvents.setItems(invalidEventList);

        System.out.println(tvInvalidEvents);

        //Filtered list
        FilteredList<Event> filteredData = new FilteredList<>(invalidEventList, b -> true);

        tfinvalidSearch.textProperty().addListener((observable, oldValue, newValue) -> {
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

        sortedData.comparatorProperty().bind(tvInvalidEvents.comparatorProperty());

        tvInvalidEvents.setItems(sortedData);
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

    public void validateEvent(ActionEvent actionEvent) throws SQLException {
        System.out.println(tfinvalidID.getText());
        String updateQuery ="UPDATE event SET state = '"+1+"'" +
                "WHERE id = '"+tfinvalidID.getText()+"'";
        executeQuery(updateQuery);
        NotificationAPI.SuccessNotification("Event validated","");
        showInvalidEvent();
    }

    public void selectEvent() {
        Event event = tvInvalidEvents.getSelectionModel().getSelectedItem();
        tfinvalidID.setText("" + event.getId());

        if (tfinvalidID.getText().isEmpty() ){
            btnValid.setDisable(true);
        }else
            btnValid.setDisable(false);

    }
}
