package symbiose.GestionTerrains.models;

public class Product {
    private String filename;
    private String name;
    private String description;
    private double price;
    private int type;
    private int supplier_id;

    public String getFilename(){
        return filename;
    }

    public void setFilename(String filename){
        this.filename=filename;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        this.price=price;
    }

    public int getType(){
        return type;
    }

    public void setType(int type){
        this.type=type;
    }

    public int getSupplier_id(){
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id){
        this.supplier_id=supplier_id;
    }
}
