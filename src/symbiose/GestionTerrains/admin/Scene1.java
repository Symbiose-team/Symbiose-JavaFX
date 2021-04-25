package symbiose.GestionTerrains.admin;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import symbiose.GestionTerrains.models.Field;
import symbiose.GestionTerrains.servise.ServiceField;
import symbiose.GestionTerrains.utils.MyConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Scene1 implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;


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
    public void SwitshToScene2(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Scene3.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void SwitshToScene87(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Homee.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void ajouter(Field field){
        LocalDate date = start.getValue();
        LocalDate datee = end.getValue();
        System.out.println(date);
        String requeteInsert = "INSERT INTO `symbiose`.`field`(`serial_number`, `name`, `address`, `space`, `provider`, `price`,`date_start`,`date_end`,`booker_id`) VALUES ('" + field.getSerial_number() + "','" + field.getName() + "','" + field.getAddress() + "','" + field.getSpace() +"','" + field.getProvider() +"','" + field.getPrice() +"','" +date+ "','" +datee+ "',Null);";
        executeQuery(requeteInsert);

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
            Field field =new Field(serial,name,address,space,provider,price);
             ajouter(field);
           // Field field=new Field(serial,name,address,price,provider,price);
            //sv.add(field);

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
