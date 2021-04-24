//package PI;
//
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.paint.Color;
//import models.User;
//import javafx.scene.control.TableView;
//import PI.utils.MyDbConnection;
//
//import java.net.URL;
//import java.sql.*;
//import java.time.LocalDate;
//import java.util.Date;
//import java.util.ResourceBundle;
//
//public class HomeController implements Initializable {
//
//   @FXML
// private TextField txtFirstname;
//    @FXML
//    private TextField txtLastname;
//    @FXML
//    private TextField txtEmail;
//    @FXML
//    private DatePicker txtDOB;
//    @FXML
//    private Button btnSave;
//    @FXML
//    private TextField txtAdresse;
//    @FXML
//    private ComboBox<String> txtGender;
//    @FXML
//    private ComboBox<String> txtRole;
//    @FXML
//    private PasswordField txtPassword;
//    @FXML
//    private PasswordField txtConfirmPassword;
//    @FXML
//    private TextField txtPhone;
//    @FXML
//    private TextField txtCin;
//    @FXML
//    Label lblStatus;
//    @FXML
//    TableView<User> tblData;
//    @FXML
//    private TableColumn<User, String> column_firstname;
//
//    @FXML
//    private TableColumn<User, String> column_lastname;
//
//    @FXML
//    private TableColumn<User, String> column_email;
//
//    @FXML
//    private TableColumn<User, String> column_genre;
//
//    @FXML
//    private TableColumn<User, String> column_role;
//
//    @FXML
//    private TableColumn<User, String> column_password;
//
//    @FXML
//    private TableColumn<User, Date> column_birthday;
//
//    @FXML
//    private TableColumn<User, String> column_adresse;
//
//    @FXML
//    private TableColumn<User, Integer> column_cin;
//
//    @FXML
//    private TableColumn<User, Integer> column_phone;
//
//    @FXML
//    private TableColumn<User, Integer> column_id;
//
//    @FXML
//    private Button btnUpdate;
//    @FXML
//    private Button btnDelete;
//
//    //private int ID;
//
//
//    /**
//     * Initializes the controller class.
//     */
//
//    PreparedStatement preparedStatement;
//    Connection connection;
//
//    public HomeController() {
//        connection = (Connection) MyDbConnection.getInstance().getConnexion();
//    }
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//
//        txtGender.getItems().addAll("Homme", "Femme");
//        txtGender.getSelectionModel().select("Homme");
//        txtRole.getItems().addAll("Client", "Fournisseur");
//        txtRole.getSelectionModel().select("Fournisseur");
//        showusers();
//
//    }
//    //button qui permet l'insertion (l'ajout) avec une contrainte === notblank par analogie a symf
//    @FXML
//    private void HandleEvents(MouseEvent event) {
//        //check if not empty
//        if (txtEmail.getText().isEmpty() || txtFirstname.getText().isEmpty() || txtLastname.getText().isEmpty() || txtPassword.getText().isEmpty() || txtConfirmPassword.getText().isEmpty() || txtAdresse.getText().isEmpty() || txtCin.getText().isEmpty() || txtPhone.getText().isEmpty() || txtDOB.getValue().equals(null)) {
//            lblStatus.setTextFill(Color.TOMATO);
//            lblStatus.setText("Entrez tous les détails ! ");
//        } else {
//            saveData();
//        }
//    }
//    //function qui efface les champs apres l'insertion
//    private void clearFields() {
//        txtFirstname.clear();
//        txtLastname.clear();
//        txtEmail.clear();
//        txtPhone.clear();
//        txtCin.clear();
//        txtAdresse.clear();
//        txtPassword.clear();
//        txtConfirmPassword.clear();
//
//    }
//    //function qui fait l'ajout
//    private String saveData() {
//        try {
//            String st = "INSERT INTO `user` (`first_name`, `last_name`, `email`, `hash`, `cin`, `birthday`, `role`, `adresse`, `phone_number`, `genre`) VALUES (?,?,?,?,?,?,?,?,?,?)";
//            preparedStatement = (PreparedStatement) connection.prepareStatement(st);
//            preparedStatement.setString(1, txtFirstname.getText());
//            preparedStatement.setString(2, txtLastname.getText());
//            preparedStatement.setString(3, txtEmail.getText());
//            preparedStatement.setString(4, txtPassword.getText());
//            preparedStatement.setString(5, txtCin.getText());
//            preparedStatement.setString(6, txtDOB.getValue().toString());
//            preparedStatement.setString(7, txtRole.getValue().toString());
//            preparedStatement.setString(8, txtAdresse.getText());
//            preparedStatement.setString(9, txtPhone.getText());
//            preparedStatement.setString(10, txtGender.getValue().toString());
//
//            preparedStatement.executeUpdate();
//            lblStatus.setTextFill(Color.GREEN);
//            lblStatus.setText("Ajouté avec succés");
//
//            showusers();
//            clearFields();
//            return "Success";
//
//        } catch (SQLException throwables) {
//            System.out.println(throwables.getMessage());
//            lblStatus.setTextFill(Color.TOMATO);
//            lblStatus.setText(throwables.getMessage());
//            return "Exception";
//        }
//    }
//
//    /**
//     *
//     * Type d'action , track fired buttons
//     */
//    @FXML
//    private void ActionHandleEvents(ActionEvent event) {
//        if (event.getSource() == btnUpdate) {
//            Update();
//
//        } else if (event.getSource() == btnDelete) {
//            System.out.println("supprimé!");
//            deleteuser();
//
//        }
//    }
//
//
////    private ObservableList<ObservableList> data;
////    String SQL ="SELECT id,first_name,last_name,cin,birthday,role,hash,genre,adresse,phone_number FROM `user`";
//
//    //function qui fait la mise à jour
//    public void Update() {
//        try {
//            User users = tblData.getSelectionModel().getSelectedItem();
//            int id = users.getId();
//            String query = "update user set first_name= '"+txtFirstname.getText()+"',last_name= '"+txtLastname.getText()+"',hash= '"+txtPassword.getText()+"',email= '"+txtEmail.getText()+"',role= '"+txtRole.getValue().toString()+"',genre='"+txtGender.getValue().toString()+"',birthday='"+txtDOB.getValue().toString()+"',cin='"+txtCin.getText()+"',phone_number='"+txtPhone.getText()+"',adresse='"+txtAdresse.getText()+"' WHERE id in ('"+id+"')";
//
//
////            String query = "UPDATE user SET first_name = ('"+txtFirstname.getText()+"') WHERE id in ('"+id+"')";
//            preparedStatement = (PreparedStatement) connection.prepareStatement(query);
//            preparedStatement.executeUpdate(query);
//            showusers();
//            lblStatus.setTextFill(Color.GREEN);
//            lblStatus.setText("M.A.J avec succés");
//
//
//        } catch (Exception throwables) {
//            System.err.println(throwables.getMessage());
//            lblStatus.setTextFill(Color.TOMATO);
//            lblStatus.setText(throwables.getMessage());
//        }
//
//    }
//
//    /**
//     * Affichage des users
//     */
//        public void showusers() {
//            ObservableList<User> list = getUsersList();
//            column_id.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
//            column_firstname.setCellValueFactory(new PropertyValueFactory<User, String>("first_name"));
//            column_lastname.setCellValueFactory(new PropertyValueFactory<User, String>("last_name"));
//            column_email.setCellValueFactory(new PropertyValueFactory<User, String>("last_name"));
//            column_cin.setCellValueFactory(new PropertyValueFactory<User, Integer>("cin"));
//            column_password.setCellValueFactory(new PropertyValueFactory<User, String>("hash"));
//            column_role.setCellValueFactory(new PropertyValueFactory<User, String>("role"));
//            column_genre.setCellValueFactory(new PropertyValueFactory<User, String>("genre"));
//            column_adresse.setCellValueFactory(new PropertyValueFactory<User, String>("adresse"));
//            column_phone.setCellValueFactory(new PropertyValueFactory<User, Integer>("phone_number"));
//            column_birthday.setCellValueFactory(new PropertyValueFactory<User, Date>("birthday"));
//
//            tblData.setItems(list);
//
//        }
//
//    /**
//     *
//     * Récupération des users dans une liste qui autorise les listeners de suivre les changements
//     */
////    public ObservableList<User> getUsersList(){
////        ObservableList<User> userslist = FXCollections.observableArrayList();
////        String query = "SELECT first_name,last_name,email,genre,role,hash,birthday,adresse,cin,phone_number,id FROM `user`";
////
////        try {
////            ResultSet rs = connection.createStatement().executeQuery(query);
////            User users;
////            while (rs.next()){
////                users = new User(rs.getString("first_name"),rs.getString("last_name"),rs.getString("email"),rs.getString("hash"),rs.getInt("cin"),rs.getDate("birthday"),rs.getString("role"),rs.getString("adresse"),rs.getInt("phone_number"),rs.getString("genre"),rs.getInt("id"));
////                userslist.add(users);
////            }
////
////        }catch (Exception exception){
////            System.out.println(exception.getMessage());
////        }
////        return userslist;
////    }
//
//    /**
//     * Suppression des users
//     */
//    public void deleteuser(){
//        User users = tblData.getSelectionModel().getSelectedItem();
//        int id = users.getId();
//        String query="DELETE FROM user WHERE id in ('"+id+"')";
//        try {
//            preparedStatement = connection.prepareStatement(query);
//            preparedStatement.executeUpdate(query);
//            showusers();
//            lblStatus.setTextFill(Color.GREEN);
//            lblStatus.setText("supprimé avec succés");
//        } catch (Exception throwables) {
//            System.err.println(throwables.getMessage());
//            lblStatus.setTextFill(Color.TOMATO);
//            lblStatus.setText("Erreur!");
//        }
//
//    }
//
//    /**
//     *
//     * Permet d'afficher les champs lors de la selection
//     */
//    public void handleMouseAction(javafx.scene.input.MouseEvent mouseEvent) {
//        User users = tblData.getSelectionModel().getSelectedItem();
//        txtFirstname.setText(""+users.getFirst_name());
//        txtLastname.setText(""+users.getLast_name());
//        txtRole.setValue(""+users.getRole().toString());
//        txtGender.setValue(""+users.getGenre().toString());
//        txtPassword.setText(""+users.getHash());
//        txtConfirmPassword.setText(""+users.getHash());
//        txtEmail.setText(""+users.getEmail());
//        txtAdresse.setText(""+users.getAdresse());
//        txtCin.setText(""+users.getCin());
//        txtPhone.setText(""+users.getPhone_number());
//        txtDOB.setValue(LocalDate.parse(""+users.getBirthday().toString()));
//
//    }
//
//}
