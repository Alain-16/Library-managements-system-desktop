package students;


import connections.connectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class createControll implements Initializable {
    @FXML
    private TextField book_id;

    @FXML
    private TextField book_name;

    @FXML
    private ComboBox<String> depart;

    @FXML
    private ComboBox<String> type_field;

    @FXML
    private CheckBox status;

    @FXML
    private Label errolLabel;




    ObservableList<String> list = FXCollections.observableArrayList("story/poems","magazine","pastpapers","teachers guide","Dictionary");
    ObservableList<String> list2 = FXCollections.observableArrayList();


    public void create_function() {
        if (create2().equals("Success")){
            try {
                String num = book_id.getText();
                String name = book_name.getText();
                String mydepart = depart.getValue();
                String mytype = type_field.getValue();
                String mystatus = status.getText();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Book created Successfully");
                Optional<ButtonType> result = alert.showAndWait();
                ButtonType button = result.orElse(ButtonType.CANCEL);
                if (button == ButtonType.OK){
                    connectionDB createcon = new connectionDB();
                    createcon.create_into_other(num,name,mydepart,mytype,mystatus);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    Connection conn = connectionDB.Connectiondb2();

    private String create2() {
        String status = "Success";
        String names = book_id.getText();

        if (names.isEmpty()) {
            setErrors(Color.TOMATO, "Empty fields please");
            status = "Erros";
        } else {
            String sql = "SELECT * FROM other_books WHERE Book_id = ?";
            try {
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, names);

                ResultSet rst = pst.executeQuery();
                if (rst.next()) {
                    setErrors(Color.RED, "book already available");
                    status = "Errors";
                } else {
                    setErrors(Color.TOMATO, "created successfully........");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return status;
    }

    public void setErrors(Color color, String text){
        errolLabel.setText(text);
        errolLabel.setTextFill(color);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        type_field.setItems(list);
        depart.setItems(list2);
    }
}
