package models;

import javax.persistence.*;

@Entity
@Table(name = "conversation")
public class Conversation {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "user1_id")
    private Integer user1Id;

    @Column(name = "user2_id")
    private Integer user2Id;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser1Id() {
        return this.user1Id;
    }

    public void setUser1Id(Integer user1Id) {
        this.user1Id = user1Id;
    }

    public Integer getUser2Id() {
        return this.user2Id;
    }

    public void setUser2Id(Integer user2Id) {
        this.user2Id = user2Id;
    }
}
