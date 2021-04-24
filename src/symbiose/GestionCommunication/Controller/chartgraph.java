/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symbiose.GestionCommunication.Controller;

import symbiose.GestionCommunication.Entities.Product;
import symbiose.GestionCommunication.Services.ProductService;
import symbiose.GestionCommunication.Services.ReclamationsService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author chayma
 */
public class chartgraph implements Initializable {
ProductService PS=new ProductService();

    @FXML
    private PieChart chart;
    private List<Product> data ;
    ReclamationsService RS=new ReclamationsService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    
    ObservableList<PieChart.Data> pieChartData;
    try {
        pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("produit", RS.nbReclamationParType("produit")),
                new PieChart.Data("joueur", RS.nbReclamationParType("joueur")),
                new PieChart.Data("autre", RS.nbReclamationParType("autre")));
                chart.setData(pieChartData);

    } catch (SQLException ex) {
        Logger.getLogger(chartgraph.class.getName()).log(Level.SEVERE, null, ex);
    }

        // TODO
    }    
    

}