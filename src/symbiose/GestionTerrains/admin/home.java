package symbiose.GestionTerrains.admin;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import symbiose.GestionTerrains.entities.Field;
import symbiose.GestionTerrains.servise.ServiceField;

import java.io.IOException;
import java.sql.SQLException;

public class home {
    @FXML
    private TableView<Field> tabb;
    @FXML
    private TableColumn<Field, Integer> showserialNumber;

    @FXML
    private TableColumn<Field, String> showName;

    @FXML
    private TableColumn<Field, String> showAddress;


    @FXML
    private TableColumn<Field, String> showProvider;

    @FXML
    private TableColumn<Field, String> showPrice;
    private Stage stage;
    private Scene scene;

    @FXML
    public void showFieldd() throws SQLException {
        ServiceField serviceField = new ServiceField();
        ObservableList<Field> liste = serviceField.fields();
        System.out.println("sfs");
        showserialNumber.setCellValueFactory(new PropertyValueFactory<Field, Integer>("serial_number"));
        showName.setCellValueFactory(new PropertyValueFactory<Field, String>("name"));
        showAddress.setCellValueFactory(new PropertyValueFactory<Field, String>("address"));

        showProvider.setCellValueFactory(new PropertyValueFactory<Field, String>("provider"));
        showPrice.setCellValueFactory(new PropertyValueFactory<Field, String>("price"));
        tabb.setItems(liste);
    }
    @FXML
    public void SwitshToScene50(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void SwitshToScene25(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Homee.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
