package symbiose.models;

public class Event {
    private int num_participants;
    private int num_remaining;
    private String image_name;
    private int image_size;
    private int supplier_id;

    public int getNum_participants(){
        return num_participants;
    }

    public void setNum_participants(int num_participants){
        this.num_participants=num_participants;
    }

    public int getNum_remaining(){
        return num_remaining;
    }

    public void setNum_remaining(int num_remaining){
        this.num_remaining=num_remaining;
    }

    public String getImage_name(){
        return image_name;
    }

    public void setImage_name(String image_name){
        this.image_name=image_name;
    }

    public int getImage_size(){
        return image_size;
    }

    public void setImage_size(int image_size){
        this.image_size=image_size;
    }

    public int getSupplier_id(){
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id){
        this.supplier_id=supplier_id;
    }
}
