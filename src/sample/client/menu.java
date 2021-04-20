package sample.client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
public class menu {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void handleButtonAction(ActionEvent event) throws SQLException, IOException {


    }
    public void SwitshToScene6(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    public void SwitshToScene5(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("field.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void SwitshToScene7(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../admin/Scene1.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

/* @FXML
    private AnchorPane panne1,panne2;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    panne1.setVisible(false);
        FadeTransition fadeTransition=new FadeTransition(Duration.seconds(0.5),panne1);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

    TranslateTransition translateTransition=new TranslateTransition(Duration.seconds(.5),panne2);
    translateTransition.setByX(-600);
    translateTransition.play();
*/
}
