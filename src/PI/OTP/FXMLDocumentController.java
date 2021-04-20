/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PI.OTP;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author ms
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane login1;

    @FXML
    private AnchorPane login2;

    @FXML
    private JFXButton Enter_signin;

    @FXML
    private AnchorPane signup1;
    // If you have not this jar file I will give you in description box you download it.
    @FXML
    private AnchorPane signup2;

    @FXML
    private JFXButton Enter_signup;
    @FXML
    private JFXButton close;

    @FXML
    private void handleButtonAction(ActionEvent event) {

        if(event.getSource()==Enter_signin)
        {
            login1.setVisible(true);
            login2.setVisible(false);
            signup2.setVisible(true);
            signup1.setVisible(false);
        }
        else if(event.getSource()==Enter_signup)
        {
            login1.setVisible(false);
            login2.setVisible(true);
            signup2.setVisible(false);
            signup1.setVisible(true);
        }
        else if(event.getSource()==close)
        {
            System.exit(0);
        }
    }
    //Part 1 has been complete.
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
