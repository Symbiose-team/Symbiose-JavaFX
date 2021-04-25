package symbiose.GestionTerrains.models;

import javax.persistence.*;

@Entity
@Table(name = "lobby")
public class Lobby {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "owner")
    private String owner;

    @Column(name = "nbplayers")
    private Integer nbplayers;

    @Column(name = "maxplayers")
    private Integer maxplayers;

    @Column(name = "status")
    private Byte status;

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

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getNbplayers() {
        return this.nbplayers;
    }

    public void setNbplayers(Integer nbplayers) {
        this.nbplayers = nbplayers;
    }

    public Integer getMaxplayers() {
        return this.maxplayers;
    }

    public void setMaxplayers(Integer maxplayers) {
        this.maxplayers = maxplayers;
    }

    public Byte getStatus() {
        return this.status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
