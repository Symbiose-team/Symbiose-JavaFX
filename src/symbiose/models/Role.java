package symbiose.models;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public String setTitle(String title) {
        this.title = title;
        return title;
    }


    }

