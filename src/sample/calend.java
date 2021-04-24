package sample;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aboosidhu
 */

public class calend {



        @FXML
        private ComboBox<String> yearCombo;

        @FXML
        public TabPane eTabpane;
        //PASS YEARS TO BE DISPLAYED ON COMBOBOX
        private final ObservableList<String> yearArray = FXCollections.observableArrayList("2019", "2020", "2021");
        //PASS DATES(*DAY ONLY) TO BE DISPLAYED ON TABLECELL
        private final ObservableList<String> datesArray = FXCollections.observableArrayList("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30");
        //PASS CORRESPONDING MONTH
        private final ObservableList<String> monthArray = FXCollections.observableArrayList("01","02","03","04","05","06","07","08","09","10","11","12","01","02","03","04","05","06","07","08","09","10","11","12","01","02","03","04","05","06");
        //PASS ATTENDANCE STATUS FOR CORRESPONDING DATESe
        private final ObservableList<String> statusArray = FXCollections.observableArrayList("present", "absent","present", "absent","present", "absent","present", "absent","present", "absent","presnt",
                "present", "absent","present", "absent","present", "absent","present", "absent","present", "absent","presnt",
                "present", "absent","present", "absent","present", "absent","present", "absent","present", "absent","presnt");


        public final List<String> dayarray = new ArrayList<String>();

        @FXML
        TableView<AttendenceClass> attributeTable;
        TableColumn<AttendenceClass, String> attributeColumn = new TableColumn<>("MONTH");
        TableColumn<AttendenceClass, String> groupColumn;

        private static final List<String> groups = Arrays.asList("SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY");
        private static final List<String> montharray = Arrays.asList("JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER");
        private static final List<String> janarrayforsunday = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "", "", "", "", "", "", "", "", "", "", "");
        private static final List<String> janarrayformonday = Arrays.asList("", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "", "", "", "", "", "", "", "", "", "");
        private static final List<String> janarrayfortuesday = Arrays.asList("", "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "", "", "", "", "", "", "", "", "");
        private static final List<String> janarrayforwedenesday = Arrays.asList("", "", "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "", "", "", "", "", "", "", "");
        private static final List<String> janarrayforthursday = Arrays.asList("", "", "", "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "", "", "", "", "", "", "");
        private static final List<String> janarrayforfriday = Arrays.asList("", "", "", "", "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "", "", "", "", "", "");
        private static final List<String> janarrayforsaturday = Arrays.asList("", "", "", "", "", "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "", "", "", "", "");

        private static final List<String> febarrayforsunday = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
        private static final List<String> febarrayformonday = Arrays.asList("", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "", "", "", "", "", "", "", "", "", "", "", "", "");
        private static final List<String> febarrayfortuesday = Arrays.asList("", "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "", "", "", "", "", "", "", "", "", "", "", "");
        private static final List<String> febarrayforwedenesday = Arrays.asList("", "", "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "", "", "", "", "", "", "", "", "", "", "");
        private static final List<String> febarrayforthursday = Arrays.asList("", "", "", "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "", "", "", "", "", "", "", "", "", "");
        private static final List<String> febarrayforfriday = Arrays.asList("", "", "", "", "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "", "", "", "", "", "", "", "", "");
        private static final List<String> febarrayforsaturday = Arrays.asList("", "", "", "", "", "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "", "", "", "", "", "", "", "");

        private static final List<String> feblarrayforsunday = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "", "", "", "", "", "", "", "", "", "", "", "", "");
        private static final List<String> feblarrayformonday = Arrays.asList("", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "", "", "", "", "", "", "", "", "", "", "", "");
        private static final List<String> feblarrayfortuesday = Arrays.asList("", "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "", "", "", "", "", "", "", "", "", "", "");
        private static final List<String> feblarrayforwedenesday = Arrays.asList("", "", "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "", "", "", "", "", "", "", "", "", "");
        private static final List<String> feblarrayforthursday = Arrays.asList("", "", "", "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "", "", "", "", "", "", "", "", "");
        private static final List<String> feblarrayforfriday = Arrays.asList("", "", "", "", "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "", "", "", "", "", "", "", "");
        private static final List<String> feblarrayforsaturday = Arrays.asList("", "", "", "", "", "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "", "", "", "", "", "", "");

        private static final List<String> aprarrayforsunday = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "", "", "", "", "", "", "", "", "", "", "", "");
        private static final List<String> aprarrayformonday = Arrays.asList("", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "", "", "", "", "", "", "", "", "", "", "");
        private static final List<String> aprarrayfortuesday = Arrays.asList("", "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "", "", "", "", "", "", "", "", "", "");
        private static final List<String> aprarrayforwedenesday = Arrays.asList("", "", "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "", "", "", "", "", "", "", "", "");
        private static final List<String> aprarrayforthursday = Arrays.asList("", "", "", "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "", "", "", "", "", "", "", "");
        private static final List<String> aprarrayforfriday = Arrays.asList("", "", "", "", "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "", "", "", "", "", "", "");
        private static final List<String> aprarrayforsaturday = Arrays.asList("", "", "", "", "", "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "", "", "", "", "", "");


        public void initialize(URL location, ResourceBundle resources) {
            attributeTable.setFixedCellSize(30);
            //SET YEARS ON COMBOBOX
            yearCombo.setItems(yearArray);
            //ON YEAR CHANGE
            yearCombo.valueProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue ov, String t, String t1) {

                    try {
                        attributeTable.getItems().clear();
                        attributeTable.getColumns().clear();
                        buildYearTable(Integer.parseInt(t1));
                    } catch (SQLException ex) {
                        Logger.getLogger(calend.class.getName()).log(Level.SEVERE, null, ex);
                    }


                }
            });
        }
        //A TABLE ALONGWITH DATES AND ATTENDANCE STATUS WILL BE DISPLAYED
        private void buildYearTable(int year) throws SQLException {
            dayarray.clear();
            attributeTable.setEditable(true);

            attributeColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
            attributeColumn.setPrefWidth(100);
            attributeTable.getColumns().add(attributeColumn);
            for (int i = 0; i < 6; i++) {

                for (int j = 0; j < groups.size(); j++) {
                    dayarray.add(groups.get(j) + i);
                }
            }

            for (String dayar : dayarray) {

                groupColumn = new TableColumn<>(dayar);
                groupColumn.setCellValueFactory(cellData -> cellData.getValue().daysProperty(dayar));

                groupColumn.setCellFactory(column -> {
                    return new TableCell<AttendenceClass, String>() {
                        @Override
                        protected void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);

                            if (item == null || empty) {
                                setText(null);
                                setStyle("");
                            } else {
                                setText(item);
                                setAlignment(Pos.CENTER);
                                if (datesArray.contains(item)) {
                                    int indexx = datesArray.indexOf(item);
                                    //CHECK ATTENDANCE STATUS
                                    if (getIndex() == Integer.parseInt(monthArray.get(indexx)) - 1) {
                                        String status = statusArray.get(indexx);
                                        //#PRESENT
                                        if (status.equals("present")) {
                                            setTextFill(Color.BLACK);
                                            setStyle("-fx-background-color:green");
                                        }
                                        //#ABSENT
                                        else {
                                            setTextFill(Color.BLUE);
                                            setStyle("-fx-background-color:red");
                                        }
                                    } else {
                                        setTextFill(Color.GRAY);
                                        setText(item);
                                        setStyle("");
                                    }
                                } else {
                                    setTextFill(Color.GRAY);
                                    setText(item);
                                    setStyle("");
                                }
                            }

                        }

                    };

                });

                groupColumn.setPrefWidth(30);
                StringBuilder sb = new StringBuilder(dayar);

                sb.delete(1, sb.length());

                groupColumn.setText(sb.toString());

                attributeTable.getColumns().add(groupColumn);

            }
            //SETTING DATES ON TABLEVIEW FOR SELECTED YEAR
            for (int i = 0; i < montharray.size(); i++) {
                AttendenceClass row = new AttendenceClass(dayarray);
                row.setName(montharray.get(i));
                for (int j = 0; j < dayarray.size(); j++) {
                    //    int year=2024;
                    if (montharray.get(i) == "JANUARY" || montharray.get(i) == "MARCH" || montharray.get(i) == "MAY" || montharray.get(i) == "JULY" || montharray.get(i) == "AUGUST" || montharray.get(i) == "OCTOBER" || montharray.get(i) == "DECEMBER") {
                        Calendar day = new GregorianCalendar(year, i, 1);
                        int dayOfWeek = day.get(Calendar.DAY_OF_WEEK);
                        switch (dayOfWeek) {
                            case 1:
                                row.setDays(dayarray.get(j), janarrayforsunday.get(j));
                                break;

                            case 2:
                                row.setDays(dayarray.get(j), janarrayformonday.get(j));
                                break;

                            case 3:
                                row.setDays(dayarray.get(j), janarrayfortuesday.get(j));
                                break;
                            case 4:
                                row.setDays(dayarray.get(j), janarrayforwedenesday.get(j));
                                break;

                            case 5:
                                row.setDays(dayarray.get(j), janarrayforthursday.get(j));
                                break;

                            case 6:
                                row.setDays(dayarray.get(j), janarrayforfriday.get(j));
                                break;

                            case 7:
                                row.setDays(dayarray.get(j), janarrayforsaturday.get(j));
                                break;

                            default:
                                break;

                        }
                    } else if (montharray.get(i) == "FEBRUARY") {

                        Calendar day = new GregorianCalendar(year, i, 1);
                        int dayOfWeek = day.get(Calendar.DAY_OF_WEEK);
                        GregorianCalendar cal = new GregorianCalendar();
                        if (cal.isLeapYear(year)) {
                            switch (dayOfWeek) {
                                case 1:
                                    row.setDays(dayarray.get(j), feblarrayforsunday.get(j));
                                    break;

                                case 2:
                                    row.setDays(dayarray.get(j), feblarrayformonday.get(j));
                                    break;

                                case 3:
                                    row.setDays(dayarray.get(j), feblarrayfortuesday.get(j));
                                    break;
                                case 4:
                                    row.setDays(dayarray.get(j), feblarrayforwedenesday.get(j));
                                    break;

                                case 5:
                                    row.setDays(dayarray.get(j), feblarrayforthursday.get(j));
                                    break;

                                case 6:
                                    row.setDays(dayarray.get(j), feblarrayforfriday.get(j));
                                    break;

                                case 7:
                                    row.setDays(dayarray.get(j), feblarrayforsaturday.get(j));
                                    break;

                                default:
                                    break;

                            }

                        } else {
                            switch (dayOfWeek) {
                                case 1:
                                    row.setDays(dayarray.get(j), febarrayforsunday.get(j));
                                    break;

                                case 2:
                                    row.setDays(dayarray.get(j), febarrayformonday.get(j));
                                    break;

                                case 3:
                                    row.setDays(dayarray.get(j), febarrayfortuesday.get(j));
                                    break;
                                case 4:
                                    row.setDays(dayarray.get(j), febarrayforwedenesday.get(j));
                                    break;

                                case 5:
                                    row.setDays(dayarray.get(j), febarrayforthursday.get(j));
                                    break;

                                case 6:
                                    row.setDays(dayarray.get(j), febarrayforfriday.get(j));
                                    break;

                                case 7:
                                    row.setDays(dayarray.get(j), febarrayforsaturday.get(j));
                                    break;

                                default:
                                    break;

                            }

                        }

                    }

                    if (montharray.get(i) == "APRIL" || montharray.get(i) == "JUNE" || montharray.get(i) == "SEPTEMBER" || montharray.get(i) == "NOVEMBER") {
                        Calendar day = new GregorianCalendar(year, i, 1);
                        int dayOfWeek = day.get(Calendar.DAY_OF_WEEK);
                        switch (dayOfWeek) {
                            case 1:
                                row.setDays(dayarray.get(j), aprarrayforsunday.get(j));
                                break;

                            case 2:
                                row.setDays(dayarray.get(j), aprarrayformonday.get(j));
                                break;

                            case 3:
                                row.setDays(dayarray.get(j), aprarrayfortuesday.get(j));
                                break;
                            case 4:
                                row.setDays(dayarray.get(j), aprarrayforwedenesday.get(j));
                                break;

                            case 5:
                                row.setDays(dayarray.get(j), aprarrayforthursday.get(j));
                                break;

                            case 6:
                                row.setDays(dayarray.get(j), aprarrayforfriday.get(j));
                                break;

                            case 7:
                                row.setDays(dayarray.get(j), aprarrayforsaturday.get(j));
                                break;

                            default:
                                break;

                        }
                    }

                }

                attributeTable.getItems().add(row);
            }

        }
    }

