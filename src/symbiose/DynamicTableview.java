package symbiose;

/**
 * This class for dynamic Tableview, i'll use it in later purposes
 */
public class DynamicTableview {
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
//    TableView tblData;
//
//    @FXML
//    private Button btnUpdate;
//    @FXML
//    private Button btnDelete;
//    /**
//     * Initializes the controller class.
//     */
//
//    PreparedStatement preparedStatement;
//    Connection connection;
//
//    public HomeController() {
//        connection = (Connection) ConnectionUtil.conDB();
//    }
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//
//        txtGender.getItems().addAll("Homme", "Femme");
//        txtGender.getSelectionModel().select("Homme");
//        txtRole.getItems().addAll("Client", "Fournisseur");
//        txtRole.getSelectionModel().select("Fournisseur");
//        fetColumnList();
//        fetRowList();
//    }
//    //button qui permet l'insertion (l'ajout) avec une contrainte === notblank par analgoie
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
//            fetRowList();
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
//
//
//
//    private ObservableList<ObservableList> data;
//    String SQL ="SELECT id,first_name,last_name,cin,birthday,role,hash,genre,adresse,phone_number FROM `user`";
//
//    //only fetch columns
//    private void fetColumnList() {
//
//        try {
//            ResultSet rs = connection.createStatement().executeQuery(SQL);
//
//            //SQL FOR SELECTING ALL OF CUSTOMER
//            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
//                //We are using non property style for making dynamic table
//                final int j = i;
//                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1).toUpperCase());
//                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
//                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
//                        System.out.println(param.getValue().get(j).toString());
//                        System.out.println(new SimpleStringProperty(param.getValue().get(j).toString()));
//                        return new SimpleStringProperty(param.getValue().get(j).toString());
//                    }
//                });
//
//                tblData.getColumns().removeAll(col);
//                tblData.getColumns().addAll(col);
//
//                System.out.println("Column [" + i + "] ");
//
//            }
//
//        } catch (Exception e) {
//            System.out.println("Error " + e.getMessage());
//
//        }
//    }
//
//    //fetches rows and data from the list
//    private void fetRowList() {
//        data = FXCollections.observableArrayList();
//        ResultSet rs;
//        try {
//            rs = connection.createStatement().executeQuery(SQL);
//
//            while (rs.next()) {
//                //Iterate Row
//                ObservableList row = FXCollections.observableArrayList();
//                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
//                    //Iterate Column
//                    row.add(rs.getString(i));
//                }
//                System.out.println("Row [1] added " + row);
//                data.add(row);
//            }
//
//            tblData.setItems(data);
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//
//        }
//
//    }
//    //function qui fait la mise à jour
//    public void Update() {
//        try {
//
//            String id = tblData.getId();
//            String query = "update user set first_name= '"+txtFirstname.getText()+"',last_name= '"+txtLastname.getText()+"',hash= '"+txtPassword.getText()+"',email= '"+txtEmail.getText()+"',role= '"+txtRole.getValue().toString()+"',genre='"+txtGender.getValue().toString()+"',birthday='"+txtDOB.getValue().toString()+"',cin='"+txtCin.getText()+"',phone_number='"+txtPhone.getText()+"',adresse='"+txtAdresse.getText()+"' WHERE id in ('"+id+"')";
//            preparedStatement.executeUpdate(query);
//            lblStatus.setTextFill(Color.GREEN);
//            lblStatus.setText("M.A.J avec succés");
//
//        } catch (SQLException throwables) {
//            System.out.println(throwables.getMessage());
//            lblStatus.setTextFill(Color.TOMATO);
//            lblStatus.setText(throwables.getMessage());
//        }
//
//    }
}
