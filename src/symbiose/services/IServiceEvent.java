package symbiose.services;

import symbiose.models.Event;

import java.sql.SQLException;
import java.util.List;

public interface IServiceEvent {
    public void AddEvent(Event e) throws SQLException;
    public List<Event> AfficherEvent();
}
