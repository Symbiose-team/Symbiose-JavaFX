package models;

import javax.persistence.*;

@Entity
@Table(name = "availability")
public class Availability {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "date_start")
    private java.sql.Date dateStart;

    @Column(name = "date_end")
    private java.sql.Date dateEnd;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public java.sql.Date getDateStart() {
        return this.dateStart;
    }

    public void setDateStart(java.sql.Date dateStart) {
        this.dateStart = dateStart;
    }

    public java.sql.Date getDateEnd() {
        return this.dateEnd;
    }

    public void setDateEnd(java.sql.Date dateEnd) {
        this.dateEnd = dateEnd;
    }
}
