package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import symbiose.models.Field;
import symbiose.servise.ServiceField;

import java.sql.Date;
import java.sql.SQLException;

import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Integer.*;

public class Controller implements Initializable {

    @FXML
    public Button ajouter;
    @FXML
    public Button supp;
    @FXML
    public Label label;
    @FXML
    public TextField namee;
    @FXML
    public TextField  serialnumber;
    @FXML
    public TextField  adr;
    @FXML
    public TextField  prss;
    @FXML
    public TextField  spacee;
    @FXML
    public TextField  providerr;
    @FXML
    public DatePicker startt;
    @FXML
    public DatePicker endd;
    @FXML
    public Label  affichee;

    @FXML
    private void handleButtonAction(ActionEvent event ) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
    @FXML
    private void AfficherTerrain(ActionEvent event ) throws SQLException {
        ServiceField sp =new ServiceField();
        affichee.setText(sp.readAll().toString());

    }
    @FXML
    private void supprimer(ActionEvent event ) throws SQLException {
        ServiceField sp =new ServiceField();


    }

    @FXML
    private void AjouterTerrain(ActionEvent event ) throws SQLException {

        ServiceField sf= new ServiceField();
        Field field=new Field();
        String name =namee.getText();
        String address= adr.getText();
        String price = prss.getText();
        String provider=providerr.getText();
        String space=spacee.getText();
        int serial= Integer.parseInt(serialnumber.getText());
        Date start = Date.valueOf(startt.getValue());
        Date  end=   Date.valueOf(startt.getValue());
        field.setSerial_number(serial);
        field.setName(name);
        field.setAddress(address);
        field.setPrice(price);
        field.setBooker_id(2);
        field.setSpace(space);
        field.setDate_start(start);
        field.setDate_end(end);

        field.setProvider(provider);
        sf.add(field);
        label.setText("votre terrain est ajouter avec succés");


    }

}
