package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import symbiose.models.Field;
import symbiose.servise.ServiceField;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class sample implements Initializable {

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
        Notifications notifications= Notifications.create().title("sucee").text("mkfdsfs").graphic(null).hideAfter(Duration.seconds(5)).position(Pos.BASELINE_LEFT).onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("cheima cheima");
            }
        });
        notifications.showConfirm();

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
