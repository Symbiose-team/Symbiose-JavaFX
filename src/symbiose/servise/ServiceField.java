package symbiose.servise;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import symbiose.Iservice.IService;
import symbiose.models.Field;
import symbiose.utils.MyConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.sql.Date.valueOf;

public class ServiceField implements IService<Field> {
    private Connection con;
    private Statement ste;

    public ServiceField() {
    con= MyConnection.getInstance().getCnx();
    }

    @Override
    public void add(Field field) throws SQLException {
        ste =con.createStatement();
        String requeteInsert = "INSERT INTO `symbiose`.`field`(`serial_number`, `name`, `address`, `space`, `provider`, `price`,`date_start`,`date_end`,`booker_id`) VALUES ('" + field.getSerial_number() + "','" + field.getName() + "','" + field.getName() + "','" + field.getAddress() + "','" + field.getSpace() +"','" + field.getProvider() +"','" + field.getPrice() +"',Null,Null,Null);";
        ste.executeUpdate(requeteInsert);
    }

    @Override
    public boolean delete(Field field) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Field field) throws SQLException {
        return false;
    }


    @Override
    public List<Field> readAll() throws SQLException {
        List<Field> list=new ArrayList<>();
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select * from field");
        while (rs.next()) {
            String name=rs.getString("name");
            Field p=new Field(name);
            list.add(p);
        }
        return list;
    }

    @Override
    public ObservableList<Field> fields() throws SQLException {
        ObservableList<Field> listTerrain= FXCollections.observableArrayList();
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select * from field");
        while (rs.next()) {
            int serial_number=rs.getInt("serial_number");
            String name=rs.getString("name");
            String address=rs.getString("address");
            String provider=rs.getString("provider");
            String price=rs.getString("price");
            String space=rs.getString("space");

            Date stat = valueOf(String.valueOf(rs.getString( "date_start")));
           Date end=valueOf(String.valueOf(rs.getString("date_end")));


            Field p=new Field(serial_number,name,address,space,provider,price/*stat,end*/);
            listTerrain.add(p);
        }
        return listTerrain;}
     /*   String query="select * from field";
        ResultSet rst = ste.executeQuery(query);
            Field fields;
            while (rst.next()){
                 fields=new Field(rst.getInt("serial_number"),rst.getString("name"),rst.getString("address"),rst.getString("space"),rst.getString("provider"),rst.getString("price"),rst.getDate("date_start"),rst.getDate("date_end"),rst.getInt("booker_id"));
                listTerrain.add(fields);
            }


        return listTerrain;

    }*/



    public boolean deleteTerrain(Field field) {
        boolean etat = false;
        try {
            String requete = "DELETE FROM Field WHERE name=?";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setString(1, field.getName());
            pst.executeUpdate();
            System.out.println("terrain supprimer");
            etat = true;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            etat = false;
        }
        return etat;
    }
}
