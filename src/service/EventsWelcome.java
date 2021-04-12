package service;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import utils.SceneSelector;

public class EventsWelcome {
    // opens show flight gui
    @FXML
    private void openEvents() {
        SceneSelector.switchScreen("eventsMain");
        SceneSelector.setWidth(900);
        SceneSelector.setHight(500);
    }
}
