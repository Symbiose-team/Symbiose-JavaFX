package symbiose.service;

import symbiose.models.Event;
import symbiose.services.IServiceEvent;
import symbiose.test.MyConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ServiceEvent implements IServiceEvent {

    Connection cnx;

    public ServiceEvent() {
        cnx = MyConnection.getInstance().getConnection();
    }

    @Override
    public void AddEvent(Event e) throws SQLException {
        Statement stm = cnx.createStatement();
        //String query="INSERT INTO `event`(`name`, `type`, `date`, `state`) " + "VALUES ('"+e.g"')";
    }

    @Override
    public List<Event> AfficherEvent() {
        throw new UnsupportedOperationException("Not supported yet");
    }
}
