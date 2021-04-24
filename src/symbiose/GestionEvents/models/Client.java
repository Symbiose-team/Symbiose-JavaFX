package symbiose.GestionEvents.models;

import javax.persistence.*;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "party_id")
    private Integer partyId;

    @Column(name = "username")
    private String username;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPartyId() {
        return this.partyId;
    }

    public void setPartyId(Integer partyId) {
        this.partyId = partyId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
