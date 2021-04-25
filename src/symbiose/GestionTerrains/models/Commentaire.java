package symbiose.GestionTerrains.models;

import javax.persistence.*;

@Entity
@Table(name = "commentaire")
public class Commentaire {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "publication_id")
    private Integer publicationId;

    @Column(name = "contenu")
    private String contenu;

    @Column(name = "date")
    private java.sql.Timestamp date;

    @Column(name = "productcomment_id")
    private Integer productcommentId;

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

    public Integer getPublicationId() {
        return this.publicationId;
    }

    public void setPublicationId(Integer publicationId) {
        this.publicationId = publicationId;
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

    public Integer getProductcommentId() {
        return this.productcommentId;
    }

    public void setProductcommentId(Integer productcommentId) {
        this.productcommentId = productcommentId;
    }
}
