package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
    /*    Parent root = FXMLLoader.load(getClass().getResource("admin/Homee.fxml"));*/
      Parent root = FXMLLoader.load(getClass().getResource("fournisseur/home.fxml"));
    /*   Parent root = FXMLLoader.load(getClass().getResource("client/menu.fxml"));*/


        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
    }

