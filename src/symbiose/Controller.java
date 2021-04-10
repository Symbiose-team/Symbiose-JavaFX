package symbiose;

import javafx.event.ActionEvent;
import  models.Event;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import service.ServiceEvent;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {
    public TextField tfName;
    public TextField tfType;
    //public TextField tfState;
    public Label LAffiche;


    public void AjouterEvent(ActionEvent actionEvent) {
        ServiceEvent se = new ServiceEvent();
        Event e = new Event();

        e.setName(tfName.getText());
        e.setType(tfType.getText());

        se.AddEvent(e);

    }

    public void AfficherEvent(ActionEvent actionEvent) {

        ServiceEvent se = new ServiceEvent();

        try {
            LAffiche.setText(se.AfficherEvent().toString());
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
}
