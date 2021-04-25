package symbiose.GestionTerrains.models;

import javax.persistence.*;

@Entity
@Table(name = "party")
public class Party {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "maxnumber")
    private Integer maxnumber;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaxnumber() {
        return this.maxnumber;
    }

    public void setMaxnumber(Integer maxnumber) {
        this.maxnumber = maxnumber;
    }
}
