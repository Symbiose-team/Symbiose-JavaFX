package symbiose.models;

public class Field {
    private int serial_number;
    private String name;
    private String address;
    private String space;
    private String provider;
    private double price;
    private java.util.Date date_start;
    private java.util.Date date_end;
    private int booker_id;

    public int getSerial_number(){
        return serial_number;
    }

    public void setSerial_number(int serial_number){
        this.serial_number=serial_number;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address=address;
    }

    public String getSpace(){
        return space;
    }

    public void setSpace(String space){
        this.space=space;
    }

    public String getProvider(){
        return provider;
    }

    public void setProvider(String provider){
        this.provider=provider;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        this.price=price;
    }

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

    public int getBooker_id(){
        return booker_id;
    }

    public void setBooker_id(int booker_id){
        this.booker_id=booker_id;
    }
}
