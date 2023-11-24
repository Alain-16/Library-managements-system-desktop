package students;


import Users.other_demo;
import connections.connectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class other_Controller implements Initializable {



    @FXML
    private TableView<other_demo> tableData;

    @FXML
    private TableColumn<other_demo, Integer> book_id_col;

    @FXML
    private TableColumn<other_demo, String> name_col;

    @FXML
    private TableColumn<other_demo, String> depart_col;

    @FXML
    private TableColumn<other_demo, String> type_col;

    @FXML
    private TableColumn<other_demo, Integer> period_col;

    @FXML
    private TableColumn<other_demo, Integer> date_col;

    @FXML
    private TableColumn<other_demo, String> status_col;

    @FXML
    private TableColumn<other_demo, Integer> id_col;

    @FXML
    private TableColumn<other_demo, String> stu_name_col;

    @FXML
    private TableColumn<other_demo, String> stu_class_col;

    @FXML
    private TableColumn<other_demo, String> special_col;

    @FXML
    private TextField id_field;

    @FXML
    private TextField name_field;

    @FXML
    private TextField stu_id_field;

    @FXML
    private TextField status_field;

    ObservableList<other_demo> list = FXCollections.observableArrayList();


    private void initcol() {
        book_id_col.setCellValueFactory(new PropertyValueFactory<>("bookid"));
        name_col.setCellValueFactory(new PropertyValueFactory<>("bookname"));
        depart_col.setCellValueFactory(new PropertyValueFactory<>("depart"));
        type_col.setCellValueFactory(new PropertyValueFactory<>("type"));
        period_col.setCellValueFactory(new PropertyValueFactory<>("period"));
        date_col.setCellValueFactory(new PropertyValueFactory<>("issuedate"));
        status_col.setCellValueFactory(new PropertyValueFactory<>("status"));
        id_col.setCellValueFactory(new PropertyValueFactory<>("studentid"));
        stu_name_col.setCellValueFactory(new PropertyValueFactory<>("studentname"));
        stu_class_col.setCellValueFactory(new PropertyValueFactory<>("studentclass"));
        special_col.setCellValueFactory(new PropertyValueFactory<>("special"));
    }

    private void populateTable() throws SQLException {
        Connection dbhandler = connectionDB.Connectiondb2();
        String sql = "SELECT * FROM library.issued_other_books";
        PreparedStatement pst = dbhandler.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            int bookID = rs.getInt("Book_ID");
            String bookname = rs.getString("Book_name");
            String departs = rs.getString("Department");
            String bkclass = rs.getString("type");
            int period = rs.getInt("period");
            Date mydate = rs.getDate("issued_date");
            String status = rs.getString("status");
            int id = rs.getInt("Student_ID");
            String name = rs.getString("Student_name");
            String stu_class = rs.getString("student_class");
            String special = rs.getString("special");
            list.add(new other_demo(bookID, bookname, departs, bkclass, period, mydate, status, id, name, stu_class, special));

        }
        tableData.setItems(list);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initcol();
        try {
            populateTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        FilteredList<other_demo> filteredList = new FilteredList<>(list, e -> true);
        id_field.setOnKeyReleased(e -> {
            id_field.textProperty().addListener((observableValue, oldValue, newValue) -> filteredList.setPredicate((Predicate<? super other_demo>) user -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lower = newValue.toLowerCase();
                return user.getBookid().toString().contains(lower);
            }));
            SortedList<other_demo> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(tableData.comparatorProperty());
            tableData.setItems(sortedList);
        });


        FilteredList<other_demo> filteredList2 = new FilteredList<>(list, e -> true);
        stu_id_field.setOnKeyReleased(e -> {
            stu_id_field.textProperty().addListener((observableValue, oldValue, newValue) -> filteredList2.setPredicate((Predicate<? super other_demo>) user -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lower = newValue.toLowerCase();
                return user.getStudentid().toString().contains(lower);
            }));
            SortedList<other_demo> sortedList = new SortedList<>(filteredList2);
            sortedList.comparatorProperty().bind(tableData.comparatorProperty());
            tableData.setItems(sortedList);
        });
        FilteredList<other_demo> filteredList3 = new FilteredList<>(list, e -> true);
        name_field.setOnKeyReleased(e -> {
            name_field.textProperty().addListener((observableValue, oldValue, newValue) -> filteredList3.setPredicate((Predicate<? super other_demo>) user -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lower = newValue.toLowerCase();
                return user.getBookname().toLowerCase().contains(lower);
            }));
            SortedList<other_demo> sortedList = new SortedList<>(filteredList3);
            sortedList.comparatorProperty().bind(tableData.comparatorProperty());
            tableData.setItems(sortedList);
        });
        FilteredList<other_demo> filteredList4 = new FilteredList<>(list, e -> true);
        status_field.setOnKeyReleased(e -> {
            status_field.textProperty().addListener((observableValue, oldValue, newValue) -> filteredList4.setPredicate((Predicate<? super other_demo>) user -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lower = newValue.toLowerCase();
                return user.getStatus().toLowerCase().contains(lower);
            }));
            SortedList<other_demo> sortedList = new SortedList<>(filteredList4);
            sortedList.comparatorProperty().bind(tableData.comparatorProperty());
            tableData.setItems(sortedList);
        });


    }

    public void report_function() {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Excel fil(*.xls)","*.xls");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showSaveDialog(stage);
        Workbook workbook = new HSSFWorkbook();
        Sheet spreadsheet = workbook.createSheet("sample");

        Row row = spreadsheet.createRow(0);

        for (int j = 0; j < tableData.getColumns().size(); j++) {
            row.createCell(j).setCellValue(tableData.getColumns().get(j).getText());
        }

        for (int i = 0; i < tableData.getItems().size(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < tableData.getColumns().size(); j++) {
                if(tableData.getColumns().get(j).getCellData(i) != null) {
                    row.createCell(j).setCellValue(tableData.getColumns().get(j).getCellData(i).toString());
                }
                else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        if (file != null) {

            try {
                FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath());
                workbook.write(fileOut);
                //fileOut.close();

                // Platform.exit();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
