package symbiose.models;

public class Availability {
    private java.util.Date date_start;
    private java.util.Date date_end;

    public java.util.Date getDate_start(){
        return date_start;
    }

    public void setDate_start(java.util.Date date_start){
        this.date_start=date_start;
    }

    public java.util.Date getDate_end(){
        return date_end;
    }

    public void setDate_end(java.util.Date date_end){
        this.date_end=date_end;
    }
}
