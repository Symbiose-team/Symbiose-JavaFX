/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symbiose.GestionUsers.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Callback;
import symbiose.models.User;
import symbiose.GestionUsers.services.Usercrud;

import java.net.URL;
import java.util.*;
import java.util.List;

/**
 * FXML Controller class
 *
 * @author SkanderThabet
 */
public class UsersAdminController implements Initializable {

    @FXML
    private StackPane contentPane;
    @FXML
    private TableView<User> UserTable;
    @FXML
    private TableColumn<User, String> column_firstname;
    @FXML
    private TableColumn<User, String> column_lastname;
    @FXML
    private TableColumn<User, String> column_email;
    @FXML
    private TableColumn<User, String> column_genre;
    @FXML
    private TableColumn<User, String> column_role;
    @FXML
    private TableColumn<User, Date> column_birthday;
    @FXML
    private TableColumn<User, String> column_adresse;
    @FXML
    private TableColumn<User, Integer> column_cin;
    @FXML
    private TableColumn<User, Integer> column_phone;
    @FXML
    private TableColumn<User, Integer> column_id;
    @FXML
    private TableColumn<User, JFXButton> column_action;

    private ObservableList<User> usersList;

    List<User> uList;
    Usercrud ur = new Usercrud();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refreshtTable();

    }

    public void refreshtTable() {
        //uList = new ArrayList<>();
        uList = ur.getAllUser();
        usersList = FXCollections.observableArrayList(uList);
        column_id.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        column_firstname.setCellValueFactory(new PropertyValueFactory<User, String>("first_name"));
        column_lastname.setCellValueFactory(new PropertyValueFactory<User, String>("last_name"));
        column_email.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        column_cin.setCellValueFactory(new PropertyValueFactory<User, Integer>("cin"));
        column_role.setCellValueFactory(new PropertyValueFactory<User, String>("role"));
        column_genre.setCellValueFactory(new PropertyValueFactory<User, String>("genre"));
        column_adresse.setCellValueFactory(new PropertyValueFactory<User, String>("adresse"));
        column_phone.setCellValueFactory(new PropertyValueFactory<User, Integer>("phone_number"));
        column_birthday.setCellValueFactory(new PropertyValueFactory<User, Date>("birthday"));
        Callback<TableColumn<User, JFXButton>, TableCell<User, JFXButton>> cellFactory;

        column_action.setCellFactory((param) -> {

            final TableCell<User, JFXButton> cell = new TableCell<User, JFXButton>() {
                final JFXButton btn1 = new JFXButton("Disable");
                final JFXButton btn2 = new JFXButton("Enable");

                @Override
                public void updateItem(JFXButton item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        User ur1 = (User) getTableRow().getItem();

                        if (ur1.getIs_enabled()) {
                            btn1.setTextFill(Paint.valueOf("#c91616"));
                            setGraphic(btn1);
                            setText(null);
                        } else {
                            btn2.setTextFill(Paint.valueOf("#16cabd"));
                            setGraphic(btn2);
                            setText(null);
                        }

                    }

                    btn1.setOnAction(event -> {
                        User user = getTableView().getItems().get(getIndex());
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Disable account", ButtonType.YES, ButtonType.CANCEL);
                        alert.setTitle("Disable account");
                        alert.setHeaderText("Are you sure to disable this account ?");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.YES) {

                            if (ur.SupprimerUser(user.getId())) {
                                System.out.println("done");
                                alert.hide();
                                refreshtTable();
                            } else {
                                System.out.println("error");
                                alert.hide();
                            }
                            System.out.println("Ok pressed");
                        } else {
                            System.out.println("canceled");
                            refreshtTable();
                        }
                    });
                    btn2.setOnAction(event -> {
                        User user = getTableView().getItems().get(getIndex());

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Enable account", ButtonType.YES, ButtonType.CANCEL);
                        alert.setTitle("Enable account");
                        alert.setHeaderText("Are you sure to enable this account ?");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.YES) {

                            if (ur.enableUser(user.getId())) {
                                System.out.println("done");
                                alert.hide();
                                refreshtTable();
                            } else {
                                System.out.println("error");
                                alert.hide();
                            }
                            System.out.println("Ok pressed");
                        } else {
                            System.out.println("canceled");
                            refreshtTable();
                        }

                    });

                }

            };

            return cell;

        });

        UserTable.getItems().setAll((Collection<? extends User>) (Collection<?>) usersList);

    }

//    public void loadUI(String ui) {
//        contentPane.getChildren().clear();
//        Parent root = null;
//        try {
//            root = FXMLLoader.load(getClass().getResource("/PI/GestionUsers/views/" + ui + ".fxml"));
//
//        } catch (IOException ex) {
//            Logger.getLogger(DashboardController.class
//                    .getName()).log(Level.SEVERE, null, ex);
//        }
//        contentPane.getChildren().add(root);
//    }

    private Stage getStage() {
        return (Stage) UserTable.getScene().getWindow();
    }
}
