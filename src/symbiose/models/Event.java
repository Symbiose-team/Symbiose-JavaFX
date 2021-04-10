package symbiose.models;

public class Event {
    private int num_participants;
    private int num_remaining;
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

    public int getSupplier_id(){
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id){
        this.supplier_id=supplier_id;
    }
}
