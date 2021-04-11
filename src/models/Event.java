package models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "event")
public class Event {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "num_participants")
    private Integer numParticipants;

    @Column(name = "num_remaining")
    private Integer numRemaining;

    @Column(name = "type")
    private String type;

    @Column(name = "date")
    private java.sql.Timestamp date;

    @Column(name = "state")
    private Byte state;

    @Column(name = "supplier_id")
    private Integer supplierId;

    public Event(String name, Integer numParticipants, Integer numRemaining, String type, Timestamp date, Byte state) {
        this.name = name;
        this.numParticipants = numParticipants;
        this.numRemaining = numRemaining;
        this.type = type;
        this.date = date;
        this.state = state;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumParticipants() {
        return this.numParticipants;
    }

    public void setNumParticipants(Integer numParticipants) {
        this.numParticipants = numParticipants;
    }

    public Integer getNumRemaining() {
        return this.numRemaining;
    }

    public void setNumRemaining(Integer numRemaining) {
        this.numRemaining = numRemaining;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public java.sql.Timestamp getDate() {
        return this.date;
    }

    public void setDate(java.sql.Timestamp date) {
        this.date = date;
    }

    public Byte getState() {
        return this.state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Integer getSupplierId() {
        return this.supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }
}
