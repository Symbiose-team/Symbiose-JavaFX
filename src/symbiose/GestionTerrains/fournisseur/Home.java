package symbiose.GestionTerrains.fournisseur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Home {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public void SwitshToScene55(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fieldf.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void SwitshToScene17(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fieldf.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void SwitshToScene6(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fieldnew.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
