package GestionEvents.models;

import javax.persistence.*;

@Entity
@Table(name = "event_user")
public class EventUser {
    @Id
    @Column(name = "event_id")
    private Integer eventId;

    @Id
    @Column(name = "user_id")
    private Integer userId;

    public Integer getEventId() {
        return this.eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
