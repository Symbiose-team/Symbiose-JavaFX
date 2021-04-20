package sample.client;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import symbiose.models.Calendar;
import symbiose.models.Field;
import symbiose.servise.ServiceCalendar;
import symbiose.servise.ServiceField;
import symbiose.utils.MyConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import static java.sql.Date.valueOf;

public class Scene2  implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button adde;
    @FXML
    private ComboBox comb;
    @FXML
    private TextField texte;
    @FXML
    private DatePicker datee;
    @FXML
    private DatePicker end;

    Connection con;
    ServiceCalendar sv =new ServiceCalendar();
    @FXML
    private Label label;
    private ObservableList list;

    @FXML
    void Select(ActionEvent event) {
        String s = comb.getSelectionModel().getSelectedItem().toString();
        label.setText(s);
    }

    public void SwitshToScene2(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    void handleButtonAction(ActionEvent event) throws SQLException {
        if (event.getSource()==adde){
          String title =texte.getText();
            LocalDate dat = datee.getValue();
            Field field= (Field) comb.getValue();
            System.out.println(dat);

            Calendar calendar=new Calendar(title,field);
            ajouter(calendar);
            /*sv.add(calendar);*/

        }

    }
    public void ajouter(Calendar calendar){
        LocalDate start = datee.getValue();
        LocalDate endd = end.getValue();

        System.out.println(start);
        System.out.println(endd);
        LocalDate now = LocalDate.now();
       Date auj= valueOf(String.valueOf(now));
        Date date=calendar.getField().getDate_start();

        String req = "INSERT INTO `symbiose`.`calendar`( `title`,`field_id`, `start`, `end`, `startDate`, `endDate`, `heureDebut`, `heureFin`, `description`, `all_day`, `background_color`) VALUES('" + calendar.getTitle() + "','" + calendar.getField().getSerial_number() + "','" +start+ "','" + endd+ "','" +endd+ "',Null,Null,Null,Null,Null,Null);";
        executeQuery(req);
        System.out.println("sucées");  }

         /*   Notifications notifications= Notifications.create().title("La date de début ne doit pas être inférieure à la date du jour\n").text("Veuillez confirmer votre date de disponibilité ").graphic(null).hideAfter(Duration.seconds(5)).position(Pos.BASELINE_CENTER).onAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                }
            });*/

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
        ServiceField serviceField = new ServiceField();
        ObservableList list = null;
        try {
            list = serviceField.fields();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        comb.setItems(list);
       /* ObservableList<Field> list = (ObservableList<Field>) new ArrayList<Field>();
        ObservableList<Field> list2 = (ObservableList<Field>) new ArrayList<Field>();
        ObservableList  listee = FXCollections.observableArrayList();
        try {
            listee = serviceField.fields();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        for(Field s : listee){
            System.out.println(s.getName());
            list2.add(s);


        }*/

    }
}
