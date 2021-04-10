package models;

import javax.persistence.*;

@Entity
@Table(name = "game_joines")
public class GameJoines {
    @Id
    @Column(name = "game_id")
    private Integer gameId;

    @Id
    @Column(name = "user_id")
    private Integer userId;

    public Integer getGameId() {
        return this.gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
