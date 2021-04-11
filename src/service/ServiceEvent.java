package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Event;
import services.IServiceEvent;
import utils.MyConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceEvent implements IServiceEvent {

    Connection cnx;

    public ServiceEvent() {
        cnx = MyConnection.getInstance().getConnection();
    }

    @Override
    public void AddEvent(Event e) {
        try {
            Statement stm = cnx.createStatement();
            String query="INSERT INTO `event`(`name`, `type`) " +
                    "VALUES ('"+e.getName()+", "+e.getType()+","+e.getDate()+"')";
            stm.executeUpdate(query);
        }catch (SQLException ex){
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE,null, ex);
        }


    }

    @Override
    public ObservableList<Event> AfficherEvent() throws SQLException {

            ObservableList<Event> eventList= FXCollections.observableArrayList();
            Statement stm = cnx.createStatement();

            String query="select * from event";

            ResultSet rst = stm.executeQuery(query);

            Event events;
            while (rst.next()){
                    events= new Event(rst.getString("name"),rst.getInt("num_participants"),
                            rst.getInt("num_remaining"),rst.getString("type"),rst.getTimestamp("date"),rst.getByte("state"));
                    eventList.add(events);
            }
            return eventList;
    }
}
