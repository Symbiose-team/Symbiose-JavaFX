package symbiose.GestionEvents.models;

import javax.persistence.*;

@Entity
@Table(name = "publication")
public class Publication {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "contenu")
    private String contenu;

    @Column(name = "vote")
    private Double vote;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContenu() {
        return this.contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Double getVote() {
        return this.vote;
    }

    public void setVote(Double vote) {
        this.vote = vote;
    }
}
