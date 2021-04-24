/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symbiose.GestionCommunication.Entities;

/**
 *
 * @author Chaima
 */
public class Product {
        int id       ;
        String filename;
        String Description;
        float price;
        String type;
        String  created_at;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Product(int id, String filename, String Description, float price, String type, String created_at) {
        this.id = id;
        this.filename = filename;
        this.Description = Description;
        this.price = price;
        this.type = type;
        this.created_at = created_at;
    }
       public Product() {
      
    }
}
