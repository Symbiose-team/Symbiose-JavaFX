package GestionEvents.models;

import javax.persistence.*;

@Entity
@Table(name = "special_event_user")
public class SpecialEventUser {
    @Id
    @Column(name = "special_event_id")
    private Integer specialEventId;

    @Id
    @Column(name = "user_id")
    private Integer userId;

    public Integer getSpecialEventId() {
        return this.specialEventId;
    }

    public void setSpecialEventId(Integer specialEventId) {
        this.specialEventId = specialEventId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
