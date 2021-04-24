/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PI.GestionUsers.controllers;

import PI.GestionUsers.services.Usercrud;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import PI.GestionUsers.services.UserSession;
import symbiose.models.User;
import PI.utils.BCrypt;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author SkanderThabet
 */
public class editProfileController implements Initializable {
    
    @FXML
    private StackPane contentPane;
    @FXML
    public ImageView avatar;
    @FXML
    public JFXTextField name;
    @FXML
    public JFXTextField lastname;
    @FXML
    public JFXTextField adresse;
    @FXML
    public JFXTextField email;
    private JFXButton edit;
    @FXML
    private JFXButton reset;
    @FXML
    private FontAwesomeIconView picture;

    
    public int user_id;
    @FXML
    private JFXTextField current;
    @FXML
    private JFXTextField new1;
    @FXML
    private JFXTextField new2;
    @FXML
    private JFXButton reset1;
    @FXML
    protected JFXDatePicker birthday;

    @FXML
    public JFXTextField cin;

    @FXML
    public JFXTextField phone;

    @FXML
    public JFXComboBox<String> genre;
    
    Usercrud uu = new Usercrud();
    @FXML
    private FontAwesomeIconView save;
    @FXML
    private FontAwesomeIconView back;
    @FXML
    private Label Errors;
    @FXML
    private Pane ch;
    @FXML
    private JFXButton choose;
    @FXML
    private ImageView av11;
    @FXML
    private ImageView av2;
    @FXML
    private ImageView av3;
    @FXML
    private ImageView av4;
    @FXML
    private ImageView av5;
    @FXML
    private ImageView av6;
    @FXML
    private JFXButton av;
    @FXML
    private Pane a5;
    @FXML
    private Pane a2;
    @FXML
    private Pane a4;
    @FXML
    private Pane a6;
    @FXML
    private Pane a3;
    @FXML
    private Pane a1;
    String c;
    public static UserSession user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        genre.getItems().addAll("Homme", "Femme");
        genre.getSelectionModel().select(uu.getUser(user_id).getGenre());
        // TODO
        ch.setVisible(false);
        current.setVisible(false);
        new1.setVisible(false);
        new2.setVisible(false);
        reset1.setVisible(false);
        back.setVisible(false);
    }
    
    @FXML
    private void reset(ActionEvent event) {
        //hide first form
        name.setVisible(false);
        lastname.setVisible(false);
        adresse.setVisible(false);
        email.setVisible(false);
        phone.setVisible(false);
        cin.setVisible(false);
        genre.setVisible(false);
        birthday.setVisible(false);
        avatar.setVisible(false);
        picture.setVisible(false);
        //draw_av.setVisible(false);
        reset.setVisible(false);
        //edit.setVisible(false);

        //show reset form 
        current.setVisible(true);
        new1.setVisible(true);
        new2.setVisible(true);
        reset1.setVisible(true);
        back.setVisible(true);
    }
    
    @FXML
    private void openchooser(MouseEvent event) {
        File dest = null;
        Stage stage = null;
        final FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(stage);
        int i = file.hashCode();
        
        if (file != null) {
            System.out.println(file);
            
            dest = new File("C:\\xampp\\htdocs\\Java\\Symbiose-JavaFX\\src\\PI\\GestionUsers\\uploads\\images\\av" + i + file.getName().substring(file.getName().length() - 4));
            try {
                FileUtils.copyFile(file, dest);
                FileUtils.copyFileToDirectory(dest, new File("C:\\Users\\msdal\\Desktop\\Avatars\\uploads\\images"));
                
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(dest.getAbsoluteFile());
            System.out.println(dest.getName());
            if (uu.updateAv(dest.getName(), user_id)) {
                System.out.println("done1");
                
                avatar.setImage(new Image("/PI/GestionUsers/uploads/images/" + dest.getName()));
                System.out.println("done");
            }
        }
    }


    @FXML
    private void openill(MouseEvent event) {
        ch.setVisible(true);
    }
    
    @FXML
    private void resetdone(ActionEvent event) throws SQLException {
        if (event.getSource() == reset1) {
            
            if (BCrypt.checkpw(current.getText(), uu.getP(user_id))) {
                System.out.println("done");
                if (new1.getText().equals(new2.getText())) {
                    System.out.println("conforme");
                } else {
                    System.out.println("non conforme");
                }
                
                if (uu.resetP(user_id, new1.getText())) {
                    System.out.println("succes");
                } else {
                    System.out.println("error");
                }
            } else {
                System.err.println("error bcrypt");
            }
        }
    }
    
    @FXML
    private void deleteAcc(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete account", ButtonType.YES, ButtonType.CANCEL);
        alert.setTitle("Delete account");
        alert.setHeaderText("Are you sure to delete this account ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            
            if (uu.deleteAcc(user_id)) {
                try {
                    System.out.println("done");
                    alert.hide();
                    //logout
                    UserSession sess = UserSession.getInstace(user_id);
                    sess.cleanUserSession();
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/PI/GestionUsers/views/signin.fxml")));
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(editProfileController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            } else {
                System.out.println("error");
                alert.hide();
            }
            System.out.println("Ok pressed");
        } else {
            System.out.println("canceled");
        }
    }
    
    @FXML
    private void back(MouseEvent event) {
        try {
            contentPane.getChildren().clear();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/PI/GestionUsers/views/Editprofile.fxml"));
            Parent root = (Parent) loader.load();
            editProfileController sp = loader.getController();
            sp.user_id = user_id;
            User u = new User();
            Usercrud U = new Usercrud();
            u = U.getUser(user_id);
            user = UserSession.getInstace(user_id);
            sp.name.setText(u.getFirst_name());
            sp.lastname.setText(u.getLast_name());
            sp.email.setText(u.getEmail());
            sp.birthday.setValue(LocalDate.parse(user.getBirthday(user_id).toString()));
            sp.adresse.setText(user.getAdresse(user_id));
            sp.genre.setValue(user.getGenre(user_id));
            sp.phone.setText(String.valueOf(user.getPhone(user_id)));
            sp.cin.setText((user.getCin(user_id)));
            String ava = u.getImage();
            //sp.avatar.setImage(new Image("/PI/GestionUsers/uploads/images/" + ava));
            
            contentPane.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(editProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setLblError(Color color, String text) {
        Errors.setTextFill(color);
        Errors.setText(text);
        System.out.println(text);
    }
    
    @FXML
    private void save(MouseEvent event) {

        // TODO
        if (name.getText().isEmpty() || lastname.getText().isEmpty() || adresse.getText().isEmpty() || email.getText().isEmpty() || phone.getText().isEmpty() || cin.getText().isEmpty() || genre.getItems().isEmpty() || birthday.getValue().equals(null)) {
            setLblError(Color.TOMATO, "Empty/wrong credentials");
            
        } else if (!uu.validateEmailAddress(email.getText())) {
            setLblError(Color.TOMATO, "Invalid Email");
            
        } else {
            
            User u = new User();
            u.setId(user_id);
            u.setFirst_name(name.getText());
            u.setLast_name(lastname.getText());
            u.setEmail(email.getText());
            u.setGenre(genre.getValue().toString());
            u.setAdresse(adresse.getText());
            u.setPhone_number(Integer.parseInt(phone.getText().toString()));
            u.setCin((cin.getText().toString()));
            u.setBirthday(Date.valueOf(birthday.getValue().toString()));

            if (uu.updateProfile(u)) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Update profile", ButtonType.OK);
                alert.setTitle("Update profile");
                alert.setHeaderText("Profile successfully updated");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    try {
                        alert.hide();
                        contentPane.getChildren().clear();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/PI/GestionUsers/views/UsersAdmin.fxml"));
                        Parent root = (Parent) loader.load();
                        PI.GestionUsers.controllers.SigninController s = new PI.GestionUsers.controllers.SigninController();
                        user_id = s.user.getUserId();
                        String ava = s.user.getAvatar(user_id);
                        this.email.setText("" + s.user.getUsername(user_id));
                        this.avatar.setImage(new Image("/PI/GestionUsers/uploads/images/" + ava));


                        contentPane.getChildren().add(root);
                    } catch (IOException ex) {
                        Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
    
    @FXML
    private void select(MouseEvent event) {
        if (event.getSource() == av11) {
            a1.setStyle("-fx-border-color:black");
            a2.setStyle("-fx-border-style:none");
            a3.setStyle("-fx-border-style:none");
            a4.setStyle("-fx-border-style:none");
            a5.setStyle("-fx-border-style:none");
            a6.setStyle("-fx-border-style:none");
            
            c = "person3";
            
        }
        if (event.getSource() == av2) {
            a2.setStyle("-fx-border-color:black");
            a1.setStyle("-fx-border-style:none");
            a3.setStyle("-fx-border-style:none");
            a4.setStyle("-fx-border-style:none");
            a5.setStyle("-fx-border-style:none");
            a6.setStyle("-fx-border-style:none");
            c = "person2";
        }
        if (event.getSource() == av3) {
            a3.setStyle("-fx-border-color:black");
            a2.setStyle("-fx-border-style:none");
            a1.setStyle("-fx-border-style:none");
            a4.setStyle("-fx-border-style:none");
            a5.setStyle("-fx-border-style:none");
            a6.setStyle("-fx-border-style:none");
            c = "person4";
        }
        if (event.getSource() == av4) {
            a4.setStyle("-fx-border-color:black");
            a2.setStyle("-fx-border-style:none");
            a3.setStyle("-fx-border-style:none");
            a1.setStyle("-fx-border-style:none");
            a5.setStyle("-fx-border-style:none");
            a6.setStyle("-fx-border-style:none");
            c = "person5";
        }
        if (event.getSource() == av5) {
            a5.setStyle("-fx-border-color:black");
            a2.setStyle("-fx-border-style:none");
            a3.setStyle("-fx-border-style:none");
            a4.setStyle("-fx-border-style:none");
            a1.setStyle("-fx-border-style:none");
            a6.setStyle("-fx-border-style:none");
            c = "person6";
        }
        if (event.getSource() == av6) {
            a6.setStyle("-fx-border-color:black");
            a2.setStyle("-fx-border-style:none");
            a3.setStyle("-fx-border-style:none");
            a4.setStyle("-fx-border-style:none");
            a5.setStyle("-fx-border-style:none");
            a1.setStyle("-fx-border-style:none");
            c = "person1";
        }
        
    }
    
    public void add(String img) {
        File dest = null;
        String ava = null;
        System.out.println(img);

        
        File file = new File("C:\\Users\\msdal\\Desktop\\Avatars\\src\\images\\" + img + ".png");
        int N=0;
        Random random = new Random();
        int io= random.nextInt(2367+N);
        N++;
        int i = file.hashCode()+io;
        
        if (file != null) {
           
                System.out.println(file);
                
                dest = new File("C:\\xampp\\htdocs\\Java\\Symbiose-JavaFX\\src\\PI\\GestionUsers\\uploads\\images\\av" + i + ".png");
                try {
                    FileUtils.copyFile(file, dest);
                    FileUtils.copyFileToDirectory(dest, new File("C:\\Users\\msdal\\Desktop\\Avatars\\src\\images"));
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("try again");
                }
                
                System.out.println(dest.getAbsoluteFile());
                Usercrud U = new Usercrud();
                System.out.println(dest.getName());
                U.updateAv(dest.getName(), user_id);
                System.out.println("yuyuyuyu:"+U.getUser(user_id).getImage());

       };
      
    }
    
    @FXML
    private void done(ActionEvent event) {
        add(c);
        ch.setVisible(false);        

    }
    
    @FXML
    private void openfile(ActionEvent event) {
    }
    
}
