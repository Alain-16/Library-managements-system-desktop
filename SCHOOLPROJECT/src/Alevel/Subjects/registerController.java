package Alevel.Subjects;

import connections.connectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class registerController implements Initializable {

    @FXML
    private TextField student_id_field;

    @FXML
    private TextField student_name_field;

    @FXML
    private ComboBox<String> depart;

    @FXML
    private ComboBox<String> student_class_field;

    @FXML
    private Label errorlabel;

    @FXML
    private Button registerAlevel;

    @FXML
    private ComboBox<String> level;

    @FXML
    private ComboBox<String> gender;

    ObservableList<String> list = FXCollections.observableArrayList("MCB", "PCM", "MPC", "MEG", "MEC");
    ObservableList<String> list2 = FXCollections.observableArrayList("Olevel", "Alevel");
    ObservableList<String> list3 = FXCollections.observableArrayList("male", "female");
    ObservableList<String> list4 = FXCollections.observableArrayList("S1", "S2", "S3", "S4", "S5", "S6");


    Connection conn = connectionDB.Connectiondb2();

    private String create2() {
        String status = "Success";
        String names = student_id_field.getText();

        if (names.isEmpty()) {
            setErrors(Color.TOMATO, "Empty fields please");
            status = "Erros";
        } else {
            String sql = "SELECT * FROM students WHERE Student_ID = ?";
            try {
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, names);

                ResultSet rst = pst.executeQuery();
                if (rst.next()) {
                    setErrors(Color.RED, "student already in the database");
                    status = "Errors";
                } else {
                    setErrors(Color.TOMATO, "registered successfully........");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return status;
    }

    private void setErrors(Color mycolor, String text) {
        errorlabel.setText(text);
        errorlabel.setTextFill(mycolor);
    }


    public void register_function() {
        if (create2().equals("Success")) {
            try {
                String id = student_id_field.getText();
                String name = student_name_field.getText();
                String department = depart.getValue();
                String bkclass = student_class_field.getValue();
                String stulevel = level.getValue();
                String stugender = gender.getValue();
                Alert alt = new Alert(Alert.AlertType.CONFIRMATION);
                alt.setContentText("registered successful");
                Optional<ButtonType> result = alt.showAndWait();
                ButtonType button = result.orElse(ButtonType.CANCEL);
                if (button == ButtonType.OK) {
                    connectionDB createcon = new connectionDB();
                    createcon.registerStudent(id, name, department, bkclass, stulevel, stugender);
                    Stage stage = (Stage) registerAlevel.getScene().getWindow();
                    Parent parent = FXMLLoader.load(getClass().getResource("src/Dashboard/UserDashboard.fxml"));
                    stage.setScene(new Scene(parent, 350, 550));
                    stage.show();
                    stage.setOnCloseRequest((event -> {

                    }));

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gender.setItems(list3);
        depart.setItems(list);
        student_class_field.setItems(list4);
        level.setItems(list2);
    }
}
