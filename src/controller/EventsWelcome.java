package controller;

import javafx.fxml.FXML;
import utils.SceneSelector;

public class EventsWelcome {
    // opens show flight gui
    @FXML
    private void openEvents() {
        SceneSelector.switchScreen("eventsMain");
    }
}
