package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import symbiose.models.Field;
import symbiose.servise.ServiceField;
import symbiose.utils.MyConnection;

import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import java.net.URL;
import java.sql.Statement;
import java.util.ResourceBundle;


import static java.lang.Integer.*;

public class New implements Initializable {

    @FXML
    private TextField sid;

    @FXML
    private TextField sname;

    @FXML
    private TextField saddress;

    @FXML
    private TextField sspace;

    @FXML
    private TextField sprovider;

    @FXML
    private TextField sprice;

    @FXML
    private DatePicker start;

    @FXML
    private DatePicker end;

    @FXML
    private TableView<Field> tab;

    @FXML
    private TableColumn<Field, Integer> showserialNumber;

    @FXML
    private TableColumn<Field, String> showName;

    @FXML
    private TableColumn<Field, String> showAddress;

    @FXML
    private TableColumn<Field, String> showSpace;

    @FXML
    private TableColumn<Field, String> showProvider;

    @FXML
    private TableColumn<Field, String> showPrice;

    @FXML
    private TableColumn<Field, Date> showStart;
Connection con;
    @FXML
    private TableColumn<Field, Date> showEnd;

    @FXML
    private Button addt;

    @FXML
    private Button modif;

    @FXML
    private Button delete;
     ServiceField sv =new ServiceField();
    @FXML
    private  void handleMousseAction(ActionEvent eventt) {
        Field field;
        field = tab.getSelectionModel().getSelectedItem();
        System.out.println("sucée");
        sname.setText("" + field.getName());
        saddress.setText("" + field.getAddress());
        sprovider.setText("" + field.getProvider());

    }

    @FXML
    void handleButtonAction(ActionEvent event) throws SQLException {
        if (event.getSource()==addt){
            String name=sname.getText();
            int serial= Integer.parseInt(sid.getText());
            String price=sprice.getText();
            String space=sspace.getText();
            String provider=sprovider.getText();
            String address=saddress.getText();

            Field field=new Field(serial,name,address,price,provider,price);
            sv.add(field);

        }else
            if (event.getSource()==modif){
                update();
            }else
                if(event.getSource()==delete){
                    delete();
                }

    }
    public void delete() throws SQLException {
        String requeteupdate = "DELETE from  `field`  where serial_number = '" + sid.getText() + "' ";
        executeQuery(requeteupdate);
        showField();
    }
    public void update() throws SQLException {
        String requeteupdate = "UPDATE  `field` SET serial_number = '" + sid.getText() + "', name = '" + sname.getText() + "', address='" + saddress.getText() + "', space = '" + sspace.getText() +"',provider = '"  + sprovider.getText() +"',price = '" + sprice.getText() +"' where serial_number = '" + sid.getText() + "' ";
        executeQuery(requeteupdate);
        showField();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void showField() throws SQLException {
        ServiceField serviceField=new ServiceField();
      ObservableList<Field> list= serviceField.fields();

        showserialNumber.setCellValueFactory(new  PropertyValueFactory<Field, Integer>("serial_number"));
        showName.setCellValueFactory(new  PropertyValueFactory<Field, String>("name"));
        showAddress.setCellValueFactory(new  PropertyValueFactory<Field, String>("address"));
        showSpace.setCellValueFactory(new PropertyValueFactory<Field, String>("space"));
        showProvider.setCellValueFactory(new  PropertyValueFactory<Field, String>("provider"));
        showPrice.setCellValueFactory(new  PropertyValueFactory<Field, String>("price"));
        showStart.setCellValueFactory(new  PropertyValueFactory<Field, Date>("date_start"));
        showEnd.setCellValueFactory(new  PropertyValueFactory<Field, Date>("date_end"));
        tab.setItems(list);
    }
    private void executeQuery(String query) {
        con = MyConnection.getInstance().getCnx();
        Statement st;
        try {
            st = con.createStatement();
            st.executeUpdate(query);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }




}
