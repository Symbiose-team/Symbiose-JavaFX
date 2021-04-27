package symbiose.models;

import javax.persistence.*;

@Entity
@Table(name = "notification")
public class Notification {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "game_id")
    private Integer gameId;

    @Column(name = "joined_by_id")
    private Integer joinedById;

    @Column(name = "seen")
    private Byte seen;

    @Column(name = "discr")
    private String discr;

    public Notification(Integer id, Integer userId, Integer gameId, Integer joinedById, Byte seen, String discr) {
        this.id = id;
        this.userId = userId;
        this.gameId = gameId;
        this.joinedById = joinedById;
        this.seen = seen;
        this.discr = discr;
    }

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

    public Integer getGameId() {
        return this.gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Integer getJoinedById() {
        return this.joinedById;
    }

    public void setJoinedById(Integer joinedById) {
        this.joinedById = joinedById;
    }

    public Byte getSeen() {
        return this.seen;
    }

    public void setSeen(Byte seen) {
        this.seen = seen;
    }

    public String getDiscr() {
        return this.discr;
    }

    public void setDiscr(String discr) {
        this.discr = discr;
    }
}
