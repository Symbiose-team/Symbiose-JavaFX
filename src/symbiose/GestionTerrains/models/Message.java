package symbiose.GestionTerrains.models;

import javax.persistence.*;

@Entity
@Table(name = "message")
public class Message {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "conversation_id")
    private Integer conversationId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "contenu")
    private String contenu;

    @Column(name = "date")
    private java.sql.Timestamp date;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getConversationId() {
        return this.conversationId;
    }

    public void setConversationId(Integer conversationId) {
        this.conversationId = conversationId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContenu() {
        return this.contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public java.sql.Timestamp getDate() {
        return this.date;
    }

    public void setDate(java.sql.Timestamp date) {
        this.date = date;
    }
}
