/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symbiose.GestionCommunication.Services;


import symbiose.GestionCommunication.Entities.Product;
import symbiose.GestionCommunication.Utils.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import symbiose.GestionCommunication.Entities.Reclamation;
import symbiose.GestionCommunication.Entities.User;
import symbiose.utils.MyDbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author Chaima
 */
public class ProductService {

    private Connection con = MyDbConnection.getInstance().getConnexion();
    private Statement ste;

    public ProductService() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
public int countp() throws SQLException {


         int nb = 0;

        String req1 = "SELECT count(*) FROM `product`";
        PreparedStatement preparedStatement = con.prepareStatement(req1);

        ResultSet result = preparedStatement.executeQuery();
        if (result.first()) {
            nb = result.getInt(1);
        }

        return nb;
    }
   
    public Vector<Product> getAll() throws SQLException {

        Vector<Product> Products = new Vector<Product>();

        String req1 = "SELECT * FROM product ";
        ResultSet result = ste.executeQuery(req1);
        while (result.next()) {
            User user = new User();
            user.setId(result.getInt("id"));
            user.setFirstname("first_Name");
            Product p = new Product(result.getInt(1), result.getString("filename"),result.getString("description"),result.getFloat("price"), result.getString("type"),result.getString("created_at"));
       

            Products.add(p);
        }
        System.out.println(Products);
        return Products;
    }
public Product getproductbytitle(String a) throws SQLException {


        String req1 = "SELECT * FROM product WHERE name=? ";
              PreparedStatement steprep = con.prepareStatement(req1);
        steprep.setString(1, a);
        ResultSet rs = steprep.executeQuery();

            User user = new User();
            user.setId(rs.getInt("id"));
            user.setFirstname("first_Name");
            Product p = new Product(rs.getInt(1), rs.getString("filename"),rs.getString("description"),rs.getFloat("price"), rs.getString("type"),rs.getString("created_at"));
       

        
        return p;
    }
  
}
