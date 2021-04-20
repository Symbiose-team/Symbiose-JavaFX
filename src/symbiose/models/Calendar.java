package symbiose.models;

import javafx.scene.control.DatePicker;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;

@Entity
public class Calendar {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "field_id")
    private Field fielde;
    private int field_id;
    private String title;
    private Date start;
    private Date end;
    private Date heureDebut;
    private Date heureFin;



    public Calendar(String title, LocalDate datee) {
        this.title = title;

    }

    public Calendar(String title, Field field) {
        this.title=title;

        this.fielde=field;


    }


    @Override
    public String toString() {
        return "Calendar{" +
                "field_id=" + field_id +
                ", title='" + title + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", heureDebut=" + heureDebut +
                ", heureFin=" + heureFin +
                '}';
    }

    public Field getField() {
        return fielde;
    }

    public void setField(Field field) {
        this.fielde = field;
    }



    public Calendar(Collection<Calendar> calendarCollection, int field_id, String title, Date start, Date end, Date heureDebut, Date heureFin) {

        this.field_id = field_id;
        this.title = title;
        this.start = start;
        this.end = end;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
    }
    public Calendar(int field_id, String title, DatePicker start, DatePicker end, DatePicker heureDebut, DatePicker heureFin, Collection<Calendar> calendarCollection) {
        this.field_id = field_id;
        this.title = title;

        this.start = null;
        this.end = null;
        this.heureDebut = null;
        this.heureFin = null;
    }
    public Calendar(String title, Date date, Date valueOf, Collection<Calendar> calendarCollection) {

    }

    public Calendar(String ffs, Date value, Date of, Date valueOf, Collection<Calendar> calendarCollection, Date date) {

        this.title = title;
        this.start = date;


    }




    public int getField_id() {
        return field_id;
    }

    public void setField_id(int field_id) {
        this.field_id = field_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(Date heureDebut) {
        this.heureDebut = heureDebut;
    }

    public Date getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(Date heureFin) {
        this.heureFin = heureFin;
    }
}