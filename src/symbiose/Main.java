package symbiose;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("GestionTerrains/admin/Homee.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("GestionTerrains/fournisseur/home.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("GestionTerrains/client/menu.fxml"));

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
    }

