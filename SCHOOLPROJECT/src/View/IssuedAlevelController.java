package View;

import connections.connectionDB;
import Users.bookDemo;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
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

/**
 * <b>The issuedAlevelcontroller class</b>
 * This class controls the issued Alevel books window which shows the available issued alevel books and the search bar
 * user can search book by name and student name for quick lookup
 * It contains of main functions like (populate()) which populates the data from the database to the table view and is attributes(id,bookname,bookclass,booknumber,period,studentname)
 */

public class IssuedAlevelController implements Initializable {


    @FXML
    private TableView<bookDemo> tableData;

    @FXML
    private TableColumn<bookDemo, Integer> book_id_col;

    @FXML
    private TableColumn<bookDemo, String> name_col;

    @FXML
    private TableColumn<bookDemo, String> depart_col;

    @FXML
    private TableColumn<bookDemo, String> class_col;

    @FXML
    private TableColumn<bookDemo, Integer> period_col;

    @FXML
    private TableColumn<bookDemo, Integer> date_col;

    @FXML
    private TableColumn<bookDemo, String> status_col;

    @FXML
    private TableColumn<bookDemo, Integer> id_col;

    @FXML
    private TableColumn<bookDemo, String> stu_name_col;

    @FXML
    private TableColumn<bookDemo, String> stu_class_col;

    @FXML
    private TableColumn<bookDemo, String> special_col;

    @FXML
    private TextField id_field;

    @FXML
    private TextField name_field;

    @FXML
    private TextField stu_id_field;

    @FXML
    private TextField status_field;
    ObservableList<bookDemo> list = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initcol();
        try {
            populateTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        FilteredList<bookDemo> filteredList = new FilteredList<>(list, e -> true);
        id_field.setOnKeyReleased(e -> {
            id_field.textProperty().addListener((observableValue, oldValue, newValue) -> filteredList.setPredicate((Predicate<? super bookDemo>) user -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lower = newValue.toLowerCase();
                return user.getBookid().toString().contains(lower);
            }));
            SortedList<bookDemo> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(tableData.comparatorProperty());
            tableData.setItems(sortedList);
        });


        FilteredList<bookDemo> filteredList2 = new FilteredList<>(list, e -> true);
        stu_id_field.setOnKeyReleased(e -> {
            stu_id_field.textProperty().addListener((observableValue, oldValue, newValue) -> filteredList2.setPredicate((Predicate<? super bookDemo>) user -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lower = newValue.toLowerCase();
                return user.getStudentid().toString().contains(lower);
            }));
            SortedList<bookDemo> sortedList = new SortedList<>(filteredList2);
            sortedList.comparatorProperty().bind(tableData.comparatorProperty());
            tableData.setItems(sortedList);
        });
        FilteredList<bookDemo> filteredList3 = new FilteredList<>(list, e -> true);
        name_field.setOnKeyReleased(e -> {
            name_field.textProperty().addListener((observableValue, oldValue, newValue) -> filteredList3.setPredicate((Predicate<? super bookDemo>) user -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lower = newValue.toLowerCase();
                return user.getBookname().toLowerCase().contains(lower);
            }));
            SortedList<bookDemo> sortedList = new SortedList<>(filteredList3);
            sortedList.comparatorProperty().bind(tableData.comparatorProperty());
            tableData.setItems(sortedList);
        });
        FilteredList<bookDemo> filteredList4 = new FilteredList<>(list, e -> true);
        status_field.setOnKeyReleased(e -> {
            status_field.textProperty().addListener((observableValue, oldValue, newValue) -> filteredList4.setPredicate((Predicate<? super bookDemo>) user -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lower = newValue.toLowerCase();
                return user.getStatus().toLowerCase().contains(lower);
            }));
            SortedList<bookDemo> sortedList = new SortedList<>(filteredList4);
            sortedList.comparatorProperty().bind(tableData.comparatorProperty());
            tableData.setItems(sortedList);
        });


    }


    private void initcol() {
        book_id_col.setCellValueFactory(new PropertyValueFactory<>("bookid"));
        name_col.setCellValueFactory(new PropertyValueFactory<>("bookname"));
        depart_col.setCellValueFactory(new PropertyValueFactory<>("depart"));
        class_col.setCellValueFactory(new PropertyValueFactory<>("bkclass"));
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
        String sql = "SELECT * FROM Library.issued_alevel_books";
        PreparedStatement pst = dbhandler.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            int bookID = rs.getInt("Book_ID");
            String bookname = rs.getString("Book_name");
            String departs = rs.getString("Department");
            String bkclass = rs.getString("Book_class");
            String period = rs.getString("period");
            Date mydate = rs.getDate("issued_date");
            String status = rs.getString("status");
            int id = rs.getInt("Student_ID");
            String name = rs.getString("Student_name");
            String stu_class = rs.getString("student_class");
            String special = rs.getString("special");
            list.add(new bookDemo(bookID, bookname, departs, bkclass, period, mydate, status, id, name, stu_class, special));

        }
        tableData.setItems(list);

    }

    public void report_function() throws IOException {
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

