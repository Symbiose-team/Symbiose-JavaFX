package symbiose.models;

public class Calendar {
    private int field_id;
    private String title;
    private String background_color;

    public int getField_id(){
        return field_id;
    }

    public void setField_id(int field_id){
        this.field_id=field_id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public String getBackground_color(){
        return background_color;
    }

    public void setBackground_color(String background_color){
        this.background_color=background_color;
    }
}
