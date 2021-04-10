package service;

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
    public List<Event> AfficherEvent() throws SQLException {
            Statement stm = cnx.createStatement();

            String query="select * from 'event'";

            ResultSet rst = stm.executeQuery(query);

            List<Event>events = new ArrayList<>();

            while (rst.next()){
                Event E = new Event();
                E.setId(rst.getInt("id"));
                E.setName(rst.getString("nom"));
                E.setType(rst.getString("type"));
                events.add(E);
            }

        return events;
    }
}
