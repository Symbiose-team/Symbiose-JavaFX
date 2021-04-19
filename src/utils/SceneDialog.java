package utils;

import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class SceneDialog {
    // made single method to open dialog of different kinds
    public static Object openDialog(String controllerPath, AnchorPane anchorPane) {
        Dialog<ButtonType> dialog = new Dialog<>();
        //make current stage owner
        dialog.initOwner(anchorPane.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(SceneDialog.class.getResource(controllerPath));

        try {

            // set view
            dialog.getDialogPane().setContent(fxmlLoader.load());

        } catch (IOException e) {
            return null;
        }

        // add cancel button
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        // show
        dialog.show();

        // return general obj of controller
        return fxmlLoader.getController();
    }
}
