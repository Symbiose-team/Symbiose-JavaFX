/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symbiose.GestionUsers.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Callback;
import symbiose.models.User;
import symbiose.GestionUsers.services.Usercrud;
import symbiose.utils.BCrypt;
import symbiose.utils.MyDbConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.List;
import java.util.function.Predicate;

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
    @FXML
    private TextField search;

    @FXML
    private TextField txt_firstname;

    @FXML
    private TextField txt_lastname;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_pass1;

    @FXML
    private TextField txt_pass2;

    @FXML
    private TextField txt_adresse;

    @FXML
    private TextField txt_cin;

    @FXML
    private TextField txt_phone;

    @FXML
    private ComboBox<String> txt_role;

    @FXML
    private ComboBox<String> txt_genre;

    @FXML
    private DatePicker txt_birthday;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnLoad;

    @FXML
    private Label lblStatus;

    private ObservableList<User> usersList;
    PreparedStatement preparedStatement;
    Connection Cn = MyDbConnection.getInstance().getConnexion();
    ObservableList<User> observableUserList = FXCollections.observableArrayList();

    List<User> uList;
    Usercrud ur = new Usercrud();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //add Listener to filterField
        search.textProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                filterUserList((String) oldValue, (String) newValue);

            }
        });

                txt_genre.getItems().addAll("Homme", "Femme");
                txt_genre.getSelectionModel().select("Homme");
                txt_role.getItems().addAll("Client", "Fournisseur");
                txt_role.getSelectionModel().select("Fournisseur");
                refreshtTable();


    }
    @FXML
    private void HandleEvents(MouseEvent event) {
        //check if not empty
        if (txt_email.getText().isEmpty() || txt_firstname.getText().isEmpty() || txt_lastname.getText().isEmpty() || txt_pass1.getText().isEmpty() || txt_pass2.getText().isEmpty() || txt_adresse.getText().isEmpty() || txt_cin.getText().isEmpty() || txt_phone.getText().isEmpty() || txt_birthday.getValue().equals(null)) {
            lblStatus.setTextFill(Color.TOMATO);
            lblStatus.setText("Empty details !");
        } else {
            saveData();
        }
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

    private void clearFields() {
        txt_firstname.clear();
        txt_lastname.clear();
        txt_email.clear();
        txt_phone.clear();
        txt_cin.clear();
        txt_adresse.clear();
        txt_pass1.clear();
        txt_pass2.clear();

    }
    private String saveData() {
        try {
            String st = "INSERT INTO `user` (`first_name`, `last_name`, `email`, `hash`, `cin`, `birthday`, `role`, `adresse`, `phone_number`, `genre`) VALUES (?,?,?,?,?,?,?,?,?,?)";
            preparedStatement = Cn.prepareStatement(st);
            preparedStatement.setString(1, txt_firstname.getText());
            preparedStatement.setString(2, txt_lastname.getText());
            preparedStatement.setString(3, txt_email.getText());
            preparedStatement.setString(4, txt_pass1.getText());
            preparedStatement.setString(5, txt_cin.getText());
            preparedStatement.setString(6, txt_birthday.getValue().toString());
            preparedStatement.setString(7, txt_role.getValue().toString());
            preparedStatement.setString(8, txt_adresse.getText());
            preparedStatement.setString(9, txt_phone.getText());
            preparedStatement.setString(10, txt_genre.getValue().toString());

            preparedStatement.executeUpdate();
            lblStatus.setTextFill(Color.GREEN);
            lblStatus.setText("Ajouté avec succés");

            refreshtTable();
            clearFields();
            return "Success";

        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            lblStatus.setTextFill(Color.TOMATO);
            lblStatus.setText(throwables.getMessage());
            return "Exception";
        }
    }

    /**
     //     *
     //     * Permet d'afficher les champs lors de la selection
     //     */
    public void handleMouseAction(javafx.scene.input.MouseEvent mouseEvent) {
        User users = UserTable.getSelectionModel().getSelectedItem();
        txt_firstname.setText(""+users.getFirst_name());
        txt_lastname.setText(""+users.getLast_name());
        txt_role.setValue(""+users.getRole().toString());
        txt_genre.setValue(""+users.getGenre().toString());
        txt_pass1.setText(""+users.getHash());
        txt_pass2.setText(""+users.getHash());
        txt_email.setText(""+users.getEmail());
        txt_adresse.setText(""+users.getAdresse());
        txt_cin.setText(""+users.getCin());
        txt_phone.setText(""+users.getPhone_number());
        txt_birthday.setValue(LocalDate.parse(""+users.getBirthday().toString()));

    }
    /**
     //     * Suppression des users
     //     */
    public void deleteuser(){
        User users = UserTable.getSelectionModel().getSelectedItem();
        int id = users.getId();
        String query="DELETE FROM user WHERE id in ('"+id+"')";
        try {
            preparedStatement = Cn.prepareStatement(query);
            preparedStatement.executeUpdate(query);
            refreshtTable();
            lblStatus.setTextFill(Color.GREEN);
            lblStatus.setText("supprimé avec succés");
        } catch (Exception throwables) {
            System.err.println(throwables.getMessage());
            lblStatus.setTextFill(Color.TOMATO);
            lblStatus.setText("Erreur!");
        }

    }

    public void Update() {

            User users = UserTable.getSelectionModel().getSelectedItem();
            if (txt_email.getText().isEmpty() || txt_pass1.getText().isEmpty() || txt_firstname.getText().isEmpty() || txt_lastname.getText().isEmpty() || txt_role.getValue().isEmpty() || txt_phone.getText().isEmpty() || txt_genre.getValue().isEmpty() || txt_birthday.getValue().equals(null) || txt_cin.getText().isEmpty() || txt_adresse.getText().isEmpty()) {
                lblStatus.setTextFill(Color.TOMATO);
                lblStatus.setText("Empty credentials");
            } else if (!txt_pass1.getText().equals(txt_pass2.getText())) {
                lblStatus.setTextFill(Color.TOMATO);
                lblStatus.setText("Passwords not identical");
            }

            else {
                try {
                int id = users.getId();
                String query = "update user set first_name= '" + txt_firstname.getText() + "',last_name= '" + txt_lastname.getText() + "',hash= '" + BCrypt.hashpw(txt_pass1.getText(), BCrypt.gensalt(13)) + "',email= '" + txt_email.getText() + "',role= '" + txt_role.getValue().toString() + "',genre='" + txt_genre.getValue().toString() + "',birthday='" + txt_birthday.getValue().toString() + "',cin='" + txt_cin.getText() + "',phone_number='" + txt_phone.getText() + "',adresse='" + txt_adresse.getText() + "' WHERE id in ('" + id + "')";


                preparedStatement = Cn.prepareStatement(query);
                preparedStatement.executeUpdate(query);
                refreshtTable();
                lblStatus.setTextFill(Color.GREEN);
                lblStatus.setText("M.A.J avec succés");



        }catch(Exception throwables){
                    System.err.println(throwables.getMessage());
                    lblStatus.setTextFill(Color.TOMATO);
                    lblStatus.setText(throwables.getMessage());
                }
    }
    }
    @FXML
    private void ActionHandleEvents(ActionEvent event) {
        if (event.getSource() == btnUpdate) {
            Update();

        } else if (event.getSource() == btnDelete) {
            System.out.println("supprimé!");
            deleteuser();

        }
        else if (event.getSource() == btnLoad){
            System.out.println("Refresh!");
            refreshtTable();
        }
    }
    //filter table by first or last name
    public void filterUserList(String oldValue, String newValue) {
        ObservableList<User> filteredList = FXCollections.observableArrayList();
        if(search == null || (newValue.length() < oldValue.length()) || newValue == null) {
            UserTable.setItems(observableUserList);
        }
        else {
            newValue = newValue.toUpperCase();
            for(User users : UserTable.getItems()) {
                String filterFirstName = users.getFirst_name();
                String filterLastName = users.getLast_name();
//                String filterEmail= users.getEmail();
//                String filterGenre= users.getGenre();
//                String filterRole= users.getRole();
                if(filterFirstName.toUpperCase().contains(newValue) || filterLastName.toUpperCase().contains(newValue)) {
                    filteredList.add(users);
                }
            }
            UserTable.setItems(filteredList);
        }
    }


    private Stage getStage() {
        return (Stage) UserTable.getScene().getWindow();
    }
}

