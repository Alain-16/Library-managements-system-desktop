package students;

import Users.alevel;
import connections.connectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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

public class StudentController implements Initializable {


    @FXML
    private TextField search_field;

    @FXML
    private TableView<alevel> tabledata;

    @FXML
    private TableColumn<alevel, Integer> id_col;

    @FXML
    private TableColumn<alevel, String> name_col;

    @FXML
    private TableColumn<alevel, String> depart_col;

    @FXML
    private TableColumn<alevel, String> class_col;

    @FXML
    private TableColumn<alevel, String> level_col;

    @FXML
    private TableColumn<alevel, String> gender_col;

    ObservableList<alevel> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initcol();
        try {
            populateTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        FilteredList<alevel> filteredList = new FilteredList<>(list, e -> true);
        search_field.setOnKeyReleased(e -> {
            search_field.textProperty().addListener((observableValue, oldValue, newValue) -> filteredList.setPredicate((Predicate<? super alevel>) user -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lower = newValue.toLowerCase();
                if (user.getName().toLowerCase().contains(lower)) {
                    return true;
                } else if (user.getId().toString().contains(lower)) {
                    return true;
                } else if (user.getDepart().toLowerCase().contains(lower)) {
                    return true;
                } else return user.getStu_class().toLowerCase().contains(lower);
            } ));
            SortedList<alevel> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(tabledata.comparatorProperty());
            tabledata.setItems(sortedList);
        });



    }


    private void initcol() {
        id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        depart_col.setCellValueFactory(new PropertyValueFactory<>("depart"));
        class_col.setCellValueFactory(new PropertyValueFactory<>("stu_class"));
        level_col.setCellValueFactory(new PropertyValueFactory<>("level"));
        gender_col.setCellValueFactory(new PropertyValueFactory<>("gender"));

    }

    private void populateTable() throws SQLException {
        Connection dbhandler = connectionDB.Connectiondb2();
        String sql = "SELECT * FROM Library.students";
        PreparedStatement pst = dbhandler.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("Student_ID");
            String name = rs.getString("Student_name");
            String depart = rs.getString("Combination");
            String stu_class = rs.getString("Student_class");
            String level = rs.getString("level");
            String gender = rs.getString("Gender");
            list.add(new alevel(id, name, depart, stu_class, level, gender));
        }
        tabledata.setItems(list);
    }


}
