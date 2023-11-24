package Alevel.Subjects;


import Alevel.Book.book;

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

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;

/**
 * <b>The controllerAlevel class</b>
 * This class controls the Alevel books window which shows the available alevel books of all subjects and the search bar
 * user can search book by name for quick lookup
 * It contains of main functions like (populate()) which populates the data from the database to the table view and is attributes(id,bookname,bookclass,booknumber)
 */


public class ControllerAlevel implements Initializable {

    @FXML
    private TextField search_field;
    @FXML
    private TableView<book> tabledata;


    @FXML
    private TableColumn<book, Integer> id_col;

    @FXML
    private TableColumn<book, String> name_col;

    @FXML
    private TableColumn<book, String> depart_col;

    @FXML
    private TableColumn<book, String> class_col;

    @FXML
    private TableColumn<book, String> status_col;


    ObservableList<book> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initcol();
        try {
            populateTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        FilteredList<book> filteredList = new FilteredList<>(list, e -> true);
        search_field.setOnKeyReleased(e -> {
            search_field.textProperty().addListener((observableValue, oldValue, newValue) -> filteredList.setPredicate((Predicate<? super book>) user -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lower = newValue.toLowerCase();
                if (user.getBookname().toLowerCase().contains(lower)) {
                    return true;
                } else if (user.getStatus().toLowerCase().contains(lower)) {
                    return true;
                } else if (user.getBooknumber().toString().contains(lower)) {
                    return true;
                } else return user.getDepart().toLowerCase().contains(lower);
            }));
            SortedList<book> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(tabledata.comparatorProperty());
            tabledata.setItems(sortedList);
        });

    }


    private void initcol() {
        id_col.setCellValueFactory(new PropertyValueFactory<>("booknumber"));
        name_col.setCellValueFactory(new PropertyValueFactory<>("bookname"));
        depart_col.setCellValueFactory(new PropertyValueFactory<>("depart"));
        class_col.setCellValueFactory(new PropertyValueFactory<>("stream"));
        status_col.setCellValueFactory(new PropertyValueFactory<>("status"));

    }

    private void populateTable() throws SQLException {
        Connection dbhandler = connectionDB.Connectiondb2();
        String sql = "SELECT * FROM alevel_books";
        PreparedStatement pst = dbhandler.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            int number = rs.getInt("Book_ID");
            String bionumber = rs.getString("Book_name");
            String bioclass = rs.getString("Department");
            String classlevel = rs.getString("Book_class");
            String status = rs.getString("status");
            list.add(new book(number, bionumber, bioclass, classlevel, status));

        }
        tabledata.setItems(list);
    }

}





