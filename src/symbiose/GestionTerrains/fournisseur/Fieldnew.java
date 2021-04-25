package symbiose.GestionTerrains.fournisseur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import symbiose.GestionTerrains.models.Field;
import symbiose.GestionTerrains.servise.ServiceField;
import symbiose.GestionTerrains.utils.MyConnection;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

public class Fieldnew implements Initializable {
    Connection con;

    @FXML
    private Button about;
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
    ServiceField sv = new ServiceField();


    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    void handleButtonAction(ActionEvent event) throws SQLException {

            String name = sname.getText();
            int serial = Integer.parseInt(sid.getText());
            String price = sprice.getText();
            String space = sspace.getText();
            String provider = sprovider.getText();
            String address = saddress.getText();
            Field field = new Field(serial, name, address, space, provider, price);
            ajou(field);

    }
    @FXML
    public void SwitshToScene3(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
        public void ajou(Field field){
            GregorianCalendar  todayDate = new GregorianCalendar();
            LocalDate date = start.getValue();
            LocalDate datee = end.getValue();

            LocalDate now = LocalDate.now();

            System.out.println(now.compareTo(date));

        if ((now.compareTo(date)<0)){
            if (datee.compareTo(date)>0) {
                String requeteInsert = "INSERT INTO `symbiose`.`field`(`serial_number`, `name`, `address`, `space`, `provider`, `price`,`date_start`,`date_end`,`booker_id`) VALUES ('" + field.getSerial_number() + "','" + field.getName() + "','" + field.getAddress() + "','" + field.getSpace() + "','" + field.getProvider() + "','" + field.getPrice() + "','" + date + "','" + datee + "',Null);";
            executeQuery(requeteInsert);
            System.out.println(date);
            Notifications notifications= Notifications.create().title("sucee").text("Votre ajout a été effectué avec succès").graphic(null).hideAfter(Duration.seconds(5)).position(Pos.BASELINE_CENTER).onAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("cheima cheima");
                }
            });
            notifications.showConfirm();

        }else {      Notifications notifications= Notifications.create().title("La date de fin ne doit pas être inférieure à la date debut").text("Veuillez confirmer votre date de disponibilité ").graphic(null).hideAfter(Duration.seconds(5)).position(Pos.BASELINE_CENTER).onAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                }
            });
                notifications.showConfirm();
            }}
        else {
            Notifications notifications= Notifications.create().title("La date de début ne doit pas être inférieure à la date du jour\n").text("Veuillez confirmer votre date de disponibilité ").graphic(null).hideAfter(Duration.seconds(5)).position(Pos.BASELINE_CENTER).onAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                }
            });
            notifications.showConfirm();
        }

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



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}