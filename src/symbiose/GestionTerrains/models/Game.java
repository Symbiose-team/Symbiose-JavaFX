package symbiose.GestionTerrains.models;

import javax.persistence.*;

@Entity
@Table(name = "game")
public class Game {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "name")
    private String name;

    @Column(name = "time")
    private java.sql.Timestamp time;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public java.sql.Timestamp getTime() {
        return this.time;
    }

    public void setTime(java.sql.Timestamp time) {
        this.time = time;
    }
}
