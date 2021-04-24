package symbiose.GestionEvents.models;

import javax.persistence.*;

@Entity
@Table(name = "calendar")
public class Calendar {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "field_id")
    private Integer fieldId;

    @Column(name = "title")
    private String title;

    @Column(name = "start")
    private java.sql.Timestamp start;

    @Column(name = "end")
    private java.sql.Timestamp end;

    @Column(name = "description")
    private String description;

    @Column(name = "all_day")
    private Byte allDay;

    @Column(name = "background_color")
    private String backgroundColor;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFieldId() {
        return this.fieldId;
    }

    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public java.sql.Timestamp getStart() {
        return this.start;
    }

    public void setStart(java.sql.Timestamp start) {
        this.start = start;
    }

    public java.sql.Timestamp getEnd() {
        return this.end;
    }

    public void setEnd(java.sql.Timestamp end) {
        this.end = end;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Byte getAllDay() {
        return this.allDay;
    }

    public void setAllDay(Byte allDay) {
        this.allDay = allDay;
    }

    public String getBackgroundColor() {
        return this.backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
