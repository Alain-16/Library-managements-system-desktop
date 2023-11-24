package Olevel.Subjects;

import connections.connectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class otherController implements Initializable {

    @FXML
    private TableView<otherDemo> tabledata;

    @FXML
    private TableColumn<otherDemo, Integer> book_id_col1;

    @FXML
    private TableColumn<otherDemo, String> name_col1;

    @FXML
    private TableColumn<otherDemo, String> depart_col1;

    @FXML
    private TableColumn<otherDemo, String> type_col1;

    @FXML
    private TableColumn<otherDemo, String> status_col1;

    @FXML
    private TextField id_field;

    ObservableList<otherDemo> list = FXCollections.observableArrayList();




    private void othercol(){
        book_id_col1.setCellValueFactory(new PropertyValueFactory<>("id"));
        name_col1.setCellValueFactory(new PropertyValueFactory<>("bookname"));
        depart_col1.setCellValueFactory(new PropertyValueFactory<>("depart"));
        type_col1.setCellValueFactory(new PropertyValueFactory<>("booktype"));
        status_col1.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void populateOther() throws SQLException {
        Connection conn = connectionDB.Connectiondb2();
        String sql = "SELECT * FROM other_books";
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rst = pst.executeQuery();
        while(rst.next()){
            int bookid = rst.getInt("Book_id");
            String bookname = rst.getString("Book_name");
            String depart = rst.getString("Department");
            String mytype = rst.getString("type");
            String mystatus = rst.getString("status");

            list.add(new otherDemo(bookid,bookname,depart,mytype,mystatus));
        }
        tabledata.setItems(list);

    }


    public void report_function()
    {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Excel fil(*.xls)","*.xls");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showSaveDialog(stage);
        Workbook workbook = new HSSFWorkbook();
        Sheet spreadsheet = workbook.createSheet("sample");

        Row row = spreadsheet.createRow(0);

        for (int j = 0; j < tabledata.getColumns().size(); j++) {
            row.createCell(j).setCellValue(tabledata.getColumns().get(j).getText());
        }

        for (int i = 0; i < tabledata.getItems().size(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < tabledata.getColumns().size(); j++) {
                if(tabledata.getColumns().get(j).getCellData(i) != null) {
                    row.createCell(j).setCellValue(tabledata.getColumns().get(j).getCellData(i).toString());
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

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        othercol();
        try {
            populateOther();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        FilteredList<otherDemo> filteredList = new FilteredList<>(list, e -> true);
        id_field.setOnKeyReleased(e -> {
            id_field.textProperty().addListener((observableValue, oldValue, newValue) -> filteredList.setPredicate((Predicate<? super otherDemo>) user -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lower = newValue.toLowerCase();
                if (user.getBookname().toLowerCase().contains(lower)) {
                    return true;
                } else if (user.getId().toString().contains(lower)) {
                    return true;
                } else return user.getStatus().toLowerCase().contains(lower);
            }));
            SortedList<otherDemo> sortedList = new SortedList<otherDemo>(filteredList);
            sortedList.comparatorProperty().bind(tabledata.comparatorProperty());
            tabledata.setItems(sortedList);
        });

    }

    }

