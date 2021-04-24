/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symbiose.GestionCommunication.Controller;

import symbiose.GestionCommunication.Entities.Product;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

/**
 *
 * @author chaima
 */
public class ListProductItem extends ListCell<Product>{

    ListProductItem() {
        super();
    }
    
    
    @Override
    public void updateItem(Product data, boolean empty)
    {
        super.updateItem(data,empty);
        if(data != null)
        {
            ListProductItemController data_controller = new ListProductItemController();
            try {
                data_controller.setInfo(data);
            } catch (SQLException ex) {
                Logger.getLogger(ListProductItem.class.getName()).log(Level.SEVERE, null, ex);
            }
            setGraphic(data_controller.getBox());
        }
    }
    
    

}




