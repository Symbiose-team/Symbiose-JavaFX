package GestionEvents.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import GestionEvents.utils.SceneSelector;

public class EventsWelcome {
    // opens show flight GestionEvents.gui
    @FXML
    private void openEvents() {
        SceneSelector.switchScreen("eventsMain");
    }


    public void openFournisseurEvents(ActionEvent actionEvent) {
        SceneSelector.switchScreen("fournisseurEvents");
    }

    public void openClientEvents(ActionEvent actionEvent) {
        SceneSelector.switchScreen("clientEvents");
    }
}
