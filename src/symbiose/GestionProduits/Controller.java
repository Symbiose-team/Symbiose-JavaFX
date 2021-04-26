package symbiose.GestionProduits;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import symbiose.utils.JavaMailUtil;
import symbiose.utils.NotificationsApi;
import symbiose.models.ProductJava;
import symbiose.utils.MyConnection;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public TextField tfId;
    public TextField tfName;
    public TextField tfType;
    public TextField tfPrice;
    public TextField tfQuantity;

    public TableView<ProductJava> tvProduct;
    public TableColumn<ProductJava, Integer> colId;
    public TableColumn<ProductJava, String> colName;
    public TableColumn<ProductJava, String> colType;
    public TableColumn<ProductJava, Float> colPrice;
    public TableColumn<ProductJava, Integer> colQuantity;
    public TableColumn<ProductJava, Integer> colState;

    public Button btnAdd;
    public Button btnEdit;
    public Button btnDelete;

    public Label lbState;
    public Label lbQuantity;

    public ToggleGroup tgState;
    public RadioButton rbInStock;
    public RadioButton rbSold;
    public TextField filterField;
    public AnchorPane anchorpane;
    public ImageView imgTshirt;
    public ImageView imgSneakers;
    public ImageView imgShorts;
    public ImageView imgSocks;
    public ImageView imgCrampons;
    public Button btnTshirt;
    public Button btnSneakers;
    public Button btnShorts;
    public Button btnSocks;
    public Button btnCrampons;


    Connection cnx;

    public void handleButtonAction(ActionEvent actionEvent) throws Exception {
        if (actionEvent.getSource() == btnAdd) {
            addProduct();
        } else if (actionEvent.getSource() == btnEdit) {
            editProduct();
        } else if (actionEvent.getSource() == btnDelete) {
            deleteProduct();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showProduct();
    }

    public ObservableList<ProductJava> getProductList() {
        ObservableList<ProductJava> productJavaList = FXCollections.observableArrayList();
        cnx = MyConnection.getInstance().getConnection();
        String query = "SELECT * FROM product_java";
        Statement statement;
        ResultSet resultSet;

        try {
            statement = cnx.createStatement();
            resultSet = statement.executeQuery(query);
            ProductJava productJava;

            while (resultSet.next()) {

                productJava = new ProductJava(

                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("type"),
                        resultSet.getInt("state"),
                        resultSet.getInt("quantity"));

                productJavaList.add(productJava);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return productJavaList;
    }

    public void showProduct() {
        ObservableList<ProductJava> list = getProductList();

        colId.setCellValueFactory(new PropertyValueFactory<ProductJava, Integer>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<ProductJava, String>("name"));
        colType.setCellValueFactory(new PropertyValueFactory<ProductJava, String>("type"));
        colPrice.setCellValueFactory(new PropertyValueFactory<ProductJava, Float>("price"));
        colState.setCellValueFactory(new PropertyValueFactory<ProductJava, Integer>("state"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<ProductJava, Integer>("quantity"));

        tvProduct.setItems(list);

        FilteredList<ProductJava> filteredData = new FilteredList<>(list, b -> true);

        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(productJava -> {

                if (newValue == null || newValue.isEmpty()){
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (productJava.getName().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }else if (String.valueOf(productJava.getPrice()).indexOf(lowerCaseFilter)!= -1){
                    return true;
                }else if (String.valueOf(productJava.getId()).indexOf(lowerCaseFilter)!= -1){
                    return true;
                }
                else return false;
            });
        });

        SortedList<ProductJava> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(tvProduct.comparatorProperty());

        tvProduct.setItems(sortedData);
    }

    private void addProduct() throws Exception {
        if (tfName.getText().equals("") || tfType.getText().equals("")) {
            NotificationsApi.ErrorNotification("Fill the remaining fields", "");
        } else {
            if (rbInStock.isSelected()) {
                String query =
                        "INSERT INTO `product_java`(`name`, `type`, `price`, `quantity`, `state`) " +
                                "VALUES ('" + tfName.getText() + "', '" + tfType.getText() + "', " + tfPrice.getText() + ", " + tfQuantity.getText() + ", " + 1 + ")";
                executeQuery(query);
                showProduct();
                } else if (rbSold.isSelected()) {
                String query =
                        "INSERT INTO `product_java`(`name`, `type`, `price`, `quantity`, `state`) " +
                                "VALUES ('" + tfName.getText() + "', '" + tfType.getText() + "', " + tfPrice.getText() + ", " + tfQuantity.getText() + ", " + 0 + ")";
                executeQuery(query);
                showProduct();
            }
            JavaMailUtil.sendMail("oussema.mestiri@esprit.tn");
            NotificationsApi.SuccessNotification("Product Added","");
        }


    }

    private void editProduct() {
        if (tfName.getText().equals("") || tfType.getText().equals("")) {
            NotificationsApi.ErrorNotification("Please select a product", "");
        } else {
            String query=
                    "UPDATE product_java SET name = '"+tfName.getText()+"', type = "+ tfType.getText() +"," +
                            "price = "+tfPrice.getText()+" WHERE id = "+tfId.getText()+"";
            executeQuery(query);
            NotificationsApi.SuccessNotification("Successfully updated product","");
            showProduct();
        }
    }

    private void deleteProduct() {
        if (tfName.getText().equals("") || tfType.getText().equals("")) {
            NotificationsApi.ErrorNotification("Please select a product", "");
        } else {
            String query = "DELETE FROM product_java WHERE id =" + tfId.getText() + "";
            executeQuery(query);
            NotificationsApi.SuccessNotification("Product Deleted", "");
            showProduct();
        }
    }

    public void changeScreenButton(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Shop.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    private void executeQuery(String query) {
        cnx = MyConnection.getInstance().getConnection();
        Statement statement;
        try {
            statement = cnx.createStatement();
            statement.executeUpdate(query);
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }


    public void handleMouseAction(MouseEvent mouseEvent) {
        ProductJava productJava = tvProduct.getSelectionModel().getSelectedItem();

        tfId.setText("" + productJava.getId());
        tfName.setText("" + productJava.getName());
        tfType.setText("" + productJava.getType());
        tfPrice.setText("" + productJava.getPrice());
        tfQuantity.setText("" + productJava.getQuantity());

    }

    public void chooseTshirtImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.JPG)", " *.JPG");
        FileChooser.ExtensionFilter extFilterjpg = new FileChooser.ExtensionFilter("jpg files (*.jpg)", " *.jpg");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.PNG)", " *.PNG");
        FileChooser.ExtensionFilter extFilterpng = new FileChooser.ExtensionFilter("png files (*.png)", " *.png");

        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);

        File file = fileChooser.showOpenDialog(null);

        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            imgTshirt.setImage(image);
            imgTshirt.getImage();

        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    public void chooseSneakersImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.JPG)", " *.JPG");
        FileChooser.ExtensionFilter extFilterjpg = new FileChooser.ExtensionFilter("jpg files (*.jpg)", " *.jpg");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.PNG)", " *.PNG");
        FileChooser.ExtensionFilter extFilterpng = new FileChooser.ExtensionFilter("png files (*.png)", " *.png");

        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);

        File file = fileChooser.showOpenDialog(null);

        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            imgSneakers.setImage(image);
            imgSneakers.getImage();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void chooseShortsImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.JPG)", " *.JPG");
        FileChooser.ExtensionFilter extFilterjpg = new FileChooser.ExtensionFilter("jpg files (*.jpg)", " *.jpg");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.PNG)", " *.PNG");
        FileChooser.ExtensionFilter extFilterpng = new FileChooser.ExtensionFilter("png files (*.png)", " *.png");

        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);

        File file = fileChooser.showOpenDialog(null);

        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            imgShorts.setImage(image);
            imgShorts.getImage();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void chooseSocksImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.JPG)", " *.JPG");
        FileChooser.ExtensionFilter extFilterjpg = new FileChooser.ExtensionFilter("jpg files (*.jpg)", " *.jpg");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.PNG)", " *.PNG");
        FileChooser.ExtensionFilter extFilterpng = new FileChooser.ExtensionFilter("png files (*.png)", " *.png");

        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);

        File file = fileChooser.showOpenDialog(null);

        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            imgSocks.setImage(image);
            imgSocks.getImage();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void chooseCramponsImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.JPG)", " *.JPG");
        FileChooser.ExtensionFilter extFilterjpg = new FileChooser.ExtensionFilter("jpg files (*.jpg)", " *.jpg");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.PNG)", " *.PNG");
        FileChooser.ExtensionFilter extFilterpng = new FileChooser.ExtensionFilter("png files (*.png)", " *.png");

        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);

        File file = fileChooser.showOpenDialog(null);

        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            imgCrampons.setImage(image);
            imgCrampons.getImage();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
