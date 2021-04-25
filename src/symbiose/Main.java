package symbiose;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import symbiose.utils.SceneSelector;

public class Main extends Application {

    //define your offsets here
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage stage) throws Exception {

        // making pane obj
        Parent root = FXMLLoader.load(getClass().getResource("GestionUsers/views/Home.fxml"));

        //Parent root = FXMLLoader.load(getClass().getResource("GestionTerrains/admin/Homee.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("GestionTerrains/fournisseur/home.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("GestionTerrains/client/menu.fxml"));

        // setting the main stage
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.setTitle("Event Management System");
        stage.show();

        // the class to change scenes intuitively
        SceneSelector sceneSelector = new SceneSelector(stage.getScene());

        // making objs of all views
        Pane eventsWelcome = FXMLLoader.load(getClass().getResource("GestionEvents/gui/EventsWelcome.fxml"));
        Pane eventsMain = FXMLLoader.load(getClass().getResource("GestionEvents/gui/EventsMain.fxml"));
        Pane participantsMain = FXMLLoader.load(getClass().getResource("GestionEvents/gui/ParticipantsMain.fxml"));
        Pane invalidEvents = FXMLLoader.load(getClass().getResource("GestionEvents/gui/InvalidEvents.fxml"));
        Pane clientEvents = FXMLLoader.load(getClass().getResource("GestionEvents/gui/ClientEvents.fxml"));
        Pane fournisseurEvents = FXMLLoader.load(getClass().getResource("GestionEvents/gui/FournisseurEvents.fxml"));

        // adding all views objs to scene selector collection
        sceneSelector.addScreen("eventsWelcome",eventsWelcome);
        sceneSelector.addScreen("eventsMain",eventsMain);
        sceneSelector.addScreen("participantsMain", participantsMain);
        sceneSelector.addScreen("invalidEvents",invalidEvents);
        sceneSelector.addScreen("clientEvents",clientEvents);
        sceneSelector.addScreen("fournisseurEvents",fournisseurEvents);
        

        //borderless .
        /*
        stage.initStyle(StageStyle.UNDECORATED);
         */


        //stage.setMaximized(false);
        //grab your root here
        root.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });

        /*
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        */

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

    }

}
