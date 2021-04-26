package symbiose.models;

import symbiose.models.User;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "game")
public class Game {
    private List<User> users;



    public List<User> getUsers() {
        return users;
    }

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userid;

    private String userId;

    @Column(name = "name")
    private String name;

    @Column(name = "time")
    private Timestamp time;


    public Game(Integer id, String name, Timestamp time) {
        this.id = id;
        this.name=name;
        this.time=time;
    }
    public Game(Integer id, String name, Timestamp time, String owner) {
        this.id = id;
        this.name=name;
        this.time=time;
        this.userId=owner;
    }

    public Game(Integer id,Integer userid) {
        this.id=id;
        this.userid = userid;

    }

    public Integer getUserid() {
        return userid;
    }

    public Game(String name) {
        this.name=name;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getTime() {
        return this.time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
