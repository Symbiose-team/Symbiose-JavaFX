package symbiose;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.SceneSelector;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // making pane obj
        Pane homePage = FXMLLoader.load(getClass().getResource("../gui/EventsWelcome.fxml"));

        // setting the main stage
        primaryStage.setScene(new Scene(homePage));
        primaryStage.setTitle("Event Management System");
        primaryStage.show();

        // the class to change scenes intuitively
        SceneSelector sceneSelector = new SceneSelector(primaryStage.getScene());

        // making objs of all views
        Pane eventsMain = FXMLLoader.load(getClass().getResource("../gui/EventsMain.fxml"));
        Pane participantsMain = FXMLLoader.load(getClass().getResource("../gui/ParticipantsMain.fxml"));
        Pane invalidEvents = FXMLLoader.load(getClass().getResource("../gui/InvalidEvents.fxml"));

        // adding all views objs to scene selector collection
        sceneSelector.addScreen("homePage",homePage);
        sceneSelector.addScreen("eventsMain",eventsMain);
        sceneSelector.addScreen("participantsMain", participantsMain);
        sceneSelector.addScreen("invalidEvents",invalidEvents);
    }

    public static void main(String[] args) {
        launch(args);
    }

    // the last method that called
    @Override
    public void stop() {
        Platform.exit();
    }

}
