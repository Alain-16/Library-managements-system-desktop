package Olevel.Subjects;


import connections.connectionDB;

import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Predicate;

public class ControllerOlevel implements Initializable {

    @FXML
    private TextField search_field;
    @FXML
    private TableView<bookOL> tableData;


    @FXML
    private TableColumn<bookOL, Integer> id_col;

    @FXML
    private TableColumn<bookOL, String> name_col;

    @FXML
    private TableColumn<bookOL, String> class_col;

    @FXML
    private TableColumn<bookOL, String> status_col;




    ObservableList<bookOL> list = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initcol();
        try {
            populateTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        FilteredList<bookOL> filteredList = new FilteredList<>(list, e -> true);
        search_field.setOnKeyReleased(e -> {
            search_field.textProperty().addListener((observableValue, oldValue, newValue) -> filteredList.setPredicate((Predicate<? super bookOL>) user -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lower = newValue.toLowerCase();
                if (user.getBookname().toLowerCase().contains(lower)) {
                    return true;
                } else if (user.getBooknumber().toString().contains(lower)) {
                    return true;
                } else return user.getStatus().toLowerCase().contains(lower);
            }));
            SortedList<bookOL> sortedList = new SortedList<bookOL>(filteredList);
            sortedList.comparatorProperty().bind(tableData.comparatorProperty());
            tableData.setItems(sortedList);
        });

    }

    private void initcol() {
        id_col.setCellValueFactory(new PropertyValueFactory<>("booknumber"));
        name_col.setCellValueFactory(new PropertyValueFactory<>("bookname"));
        class_col.setCellValueFactory(new PropertyValueFactory<>("stream"));
        status_col.setCellValueFactory(new PropertyValueFactory<>("status"));

    }



    private void populateTable() throws SQLException {
        Connection dbhandler = connectionDB.Connectiondb2();
        String sql = "SELECT * FROM olevel_books";
        PreparedStatement pst = dbhandler.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            int bioname = rs.getInt("Book_ID");
            String bionumber = rs.getString("Book_name");
            String bioclass = rs.getString("Book_class");
            String classlvl = rs.getString("status");
            list.add(new bookOL(bioname, bionumber, bioclass, classlvl));

        }
        tableData.setItems(list);
    }


    public void report_function(ActionEvent event) {
    }
}

