package symbiose.GestionProduits;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import symbiose.models.ProductJava;

import java.io.*;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ShopController implements Initializable {

    public Label lbSize;
    public Label lbType;
    public Label lbQuantity;
    public Label lbCoupon;



    public Button btnOrder;
    public Button btnReset;
    public Button btnCalculateSales;

    public TextArea taSummary;
    public TextArea taSalesSummary;

    public TextField tfQuantity;

    public ChoiceBox<String> cbSize;
    private final String[] sizeItems = {"Small", "Medium", "Large"};
    private final ObservableList<String> sizeList = FXCollections.observableArrayList(sizeItems);

    public CheckBox chkOne;
    public CheckBox chkTwo;

    public ToggleGroup tgState;
    public RadioButton rbTShirt;
    public RadioButton rbSneakers;
    public RadioButton rbShorts;
    public RadioButton rbSocks;
    public RadioButton rbCrampons;

    public TextArea taTotalSummary;

    public Button btnOrderEquipment;
    public Button btnResetEquipment;
    public CheckBox chkTwoEquipment;
    public CheckBox chkOneEquipment;
    public Label lbEquipmentCoupon;
    public RadioButton rbTennisball;
    public RadioButton rbPaintball;
    public RadioButton rbRacket;
    public RadioButton rbBasketball;
    public RadioButton rbFootball;
    public Label lbTypeClothing;
    public TextArea taSummaryEquipment;
    public TextField tfEquipmentQuantity;
    public Label lbEquipmentQuantity;
    public ToggleGroup tgStateEquipment;

    public void changeScreenButton(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbSize.setItems(sizeList);

    }

    public void handleOrderButtonAction(ActionEvent event) {
        ProductJava productJava = new ProductJava();
        Coupon coupon = new Coupon();
        double couponAmount = 0.0;
        DecimalFormat currency = new DecimalFormat("$,###.00");


        if ((chkOne.isSelected())) {
            coupon.setPrice(1);
            couponAmount += 1.0;
        }
        if (chkTwo.isSelected()) {
            coupon.setPrice(2);
            couponAmount += 2.0;
        }

        if (rbTShirt.isSelected()) {

            productJava.setSize(cbSize.getSelectionModel().getSelectedIndex());

            productJava.setPrice(6.99);

            productJava.setQuantity(Integer.parseInt(tfQuantity.getText()));

            taSummary.appendText(productJava.getQuantity() + " " + rbTShirt.getText() + " " + cbSize.getValue() + " " + currency.format(couponAmount) + " " + currency.format(productJava.getPrice()) + "\n");

            writeRecord(productJava, couponAmount);

        } else if (rbSneakers.isSelected()) {

            productJava.setSize(cbSize.getSelectionModel().getSelectedIndex());

            productJava.setPrice(7.99);

            productJava.setQuantity(Integer.parseInt(tfQuantity.getText()));

            taSummary.appendText(productJava.getQuantity() + " " + rbSneakers.getText() + " " + cbSize.getValue() + " " + currency.format(couponAmount) + " " + currency.format(productJava.getPrice()) + "\n");

            writeRecord(productJava, couponAmount);
        } else if (rbShorts.isSelected()) {

            productJava.setSize(cbSize.getSelectionModel().getSelectedIndex());

            productJava.setPrice(8.99);

            productJava.setQuantity(Integer.parseInt(tfQuantity.getText()));

            taSummary.appendText(productJava.getQuantity() + " " + rbShorts.getText() + " " + cbSize.getValue() + " " + currency.format(couponAmount) + " " + currency.format(productJava.getPrice()) + "\n");

            writeRecord(productJava, couponAmount);
        } else if (rbSocks.isSelected()) {

            productJava.setSize(cbSize.getSelectionModel().getSelectedIndex());

            productJava.setPrice(9.99);

            productJava.setQuantity(Integer.parseInt(tfQuantity.getText()));

            taSummary.appendText(productJava.getQuantity() + " " + rbSocks.getText() + " " + cbSize.getValue() + " " + currency.format(couponAmount) + " " + currency.format(productJava.getPrice()) + "\n");

            writeRecord(productJava, couponAmount);
        } else if (rbCrampons.isSelected()) {

            productJava.setSize(cbSize.getSelectionModel().getSelectedIndex());

            productJava.setPrice(10.99);

            productJava.setQuantity(Integer.parseInt(tfQuantity.getText()));

            taSummary.appendText(productJava.getQuantity() + " " + rbCrampons.getText() + " " + cbSize.getValue() + " " + currency.format(couponAmount) + " " + currency.format(productJava.getPrice()) +"\n");

            writeRecord(productJava, couponAmount);
        } else {
            taSummary.appendText("Please select a Product!\n");
        }

    }

    public void handleResetButtonAction(ActionEvent event) {
        rbTShirt.setSelected(false);
        rbCrampons.setSelected(false);
        rbShorts.setSelected(false);
        rbSneakers.setSelected(false);
        rbSocks.setSelected(false);
        tfQuantity.setText(null);
        cbSize.setValue(null);
        chkOne.setSelected(false);
        chkTwo.setSelected(false);
        taSummary.setText(null);
    }

    public void handleOrderEquipmentButtonAction(ActionEvent event) {
        ProductJava productJava = new ProductJava();
        Coupon coupon = new Coupon();
        double couponAmount = 0.0;
        DecimalFormat currency = new DecimalFormat("$,###.00");


        if ((chkOneEquipment.isSelected())) {
            coupon.setPrice(1);
            couponAmount += 1.0;
        }
        if (chkTwoEquipment.isSelected()) {
            coupon.setPrice(2);
            couponAmount += 2.0;
        }

        if (rbFootball.isSelected()) {

            productJava.setPrice(6.99);

            productJava.setQuantity(Integer.parseInt(tfEquipmentQuantity.getText()));

            taSummaryEquipment.appendText(productJava.getQuantity() + " " + rbFootball.getText() + " " + currency.format(couponAmount) + " " + currency.format(productJava.getPrice()) + "\n");

            writeRecord(productJava, couponAmount);

        } else if (rbBasketball.isSelected()) {

            productJava.setPrice(7.99);

            productJava.setQuantity(Integer.parseInt(tfEquipmentQuantity.getText()));

            taSummaryEquipment.appendText(productJava.getQuantity() + " " + rbBasketball.getText() + " " + currency.format(couponAmount) + " " + currency.format(productJava.getPrice()) + "\n");

            writeRecord(productJava, couponAmount);
        } else if (rbRacket.isSelected()) {

            productJava.setPrice(8.99);

            productJava.setQuantity(Integer.parseInt(tfEquipmentQuantity.getText()));

            taSummaryEquipment.appendText(productJava.getQuantity() + " " + rbRacket.getText() + " " + currency.format(couponAmount) + " " + currency.format(productJava.getPrice()) + "\n");

            writeRecord(productJava, couponAmount);
        } else if (rbPaintball.isSelected()) {

            productJava.setPrice(9.99);

            productJava.setQuantity(Integer.parseInt(tfEquipmentQuantity.getText()));

            taSummaryEquipment.appendText(productJava.getQuantity() + " " + rbPaintball.getText() + " " + currency.format(couponAmount) + " " + currency.format(productJava.getPrice()) + "\n");

            writeRecord(productJava, couponAmount);
        } else if (rbTennisball.isSelected()) {

            productJava.setPrice(10.99);

            productJava.setQuantity(Integer.parseInt(tfEquipmentQuantity.getText()));

            taSummaryEquipment.appendText(productJava.getQuantity() + " " + rbTennisball.getText() + " " + currency.format(couponAmount) + " " + currency.format(productJava.getPrice()) +"\n");

            writeRecord(productJava, couponAmount);
        } else {
            taSummaryEquipment.appendText("Please select a Product!\n");
        }
    }

    public void handleResetEquipmentButtonAction(ActionEvent event) {
    }

    public void handleCalculateSalesButtonAction(ActionEvent event) {
        readRecord();
    }

    private void readRecord() {
        File file = null;
        Scanner input = null;

        try {
            file = new File("src\\symbiose\\GestionProduits\\sales.txt");
            input = new Scanner(file);

            double subtotal = 0.0;
            double total = 0;
            double couponAmount = 0.0;
            double couponTotal = 0.0;
            DecimalFormat currency = new DecimalFormat("$ ,###.00");

            taSalesSummary.appendText("Quantity \t\t" + "Type \t\t" + "Price \t\t" + "Size \t\t\t" +" Subtotal \t\t\t" + "Coupon Amount \n");
            while (input.hasNext()) {
                int quantity = input.nextInt();
                String type = input.next();
                double price = input.nextDouble();
                String size = input.next();
                subtotal = price * quantity;
                couponAmount = input.nextDouble();
                taSalesSummary.appendText(quantity + "\t\t\t" + type + "\t\t" + currency.format(price) + "\t\t" + size + "\t\t" + currency.format(subtotal) + "\t\t\t" + currency.format(couponAmount) + "\n");
                total += subtotal;
                couponTotal += couponAmount;
            }
            taTotalSummary.appendText("Total Sales: " + currency.format(total) + "\n");
            taTotalSummary.appendText("Total Coupon Amount: " + currency.format(couponTotal) + "\n");
            taTotalSummary.appendText("Total : " + currency.format(total-couponTotal) + "\n");


            input.close();

        } catch (FileNotFoundException ex1) {
            ex1.toString();
        } catch (IOException ex2) {
            ex2.toString();
        } finally {
            if (input != null) {
                input.close();
            }
        }
    }

    public void writeRecord(ProductJava productJava, double couponAmount) {
        FileWriter fileWriter = null;
        PrintWriter printWriter = null;

        try {
            fileWriter = new FileWriter("src\\symbiose\\GestionProduits\\sales.txt", true);

            printWriter = new PrintWriter(fileWriter);
            if (rbTShirt.isSelected()) {
                printWriter.println(productJava.getQuantity() + " " + rbTShirt.getText() + " " + productJava.getPrice() + " " + cbSize.getValue() + " " + couponAmount);
            } else if (rbSneakers.isSelected()) {
                printWriter.println(productJava.getQuantity() + " " + rbSneakers.getText() + " " + productJava.getPrice() + " " + cbSize.getValue() + " " + couponAmount);
            } else if (rbSocks.isSelected()) {
                printWriter.println(productJava.getQuantity() + " " + rbSocks.getText() + " " + productJava.getPrice() + " " + cbSize.getValue() + " " + couponAmount);
            } else if (rbShorts.isSelected()) {
                printWriter.println(productJava.getQuantity() + " " + rbShorts.getText() + " " + productJava.getPrice() + " " + cbSize.getValue() + " " + couponAmount);
            } else if (rbCrampons.isSelected()) {
                printWriter.println(productJava.getQuantity() + " " + rbCrampons.getText() + " " + productJava.getPrice() + " " + cbSize.getValue() + " " + couponAmount);
            }

            printWriter.close();

        } catch (FileNotFoundException ex1) {
            ex1.toString();
        } catch (IOException ex2) {
            ex2.toString();
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }


}