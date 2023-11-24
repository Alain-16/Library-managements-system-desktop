package View;

import connections.connectionDB;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class createController implements Initializable {
    @FXML
    private TextField book_id_field;

    @FXML
    private TextField book_name_field;

    @FXML
    private ComboBox<String> depart;


    @FXML
    private VBox layer;


    @FXML
    private ComboBox<String> book_class_field;

    @FXML
    private CheckBox available;


    @FXML
    private Label errorLabel;

    @FXML
    private Button createBook;

    @FXML
    private TextField id_field;

    @FXML
    private TextField name_field;

    @FXML
    private ComboBox<String> class_field;

    @FXML
    private CheckBox available_olevel;


    @FXML
    private Label errorLabel1;

    @FXML
    private Button createBook_olevel;

    ObservableList<String> list = FXCollections.observableArrayList("MCE", "MEG", "MPC", "PCM", "MCB");
    ObservableList<String> list2 = FXCollections.observableArrayList("S1", "S2", "S3");
    ObservableList<String> list3 = FXCollections.observableArrayList("S4", "S5", "S6");


    public void create_alevel_function() {
        if (create2().equals("Success")) {
            try {
                String id = book_id_field.getText();
                String name = book_name_field.getText();
                String department = depart.getValue();
                String bkclass = book_class_field.getValue();
                String status = available.getText();
                Alert alt = new Alert(Alert.AlertType.CONFIRMATION);
                alt.setContentText("Book inserted successful");
                Optional<ButtonType> result = alt.showAndWait();
                ButtonType button = result.orElse(ButtonType.CANCEL);
                if (button == ButtonType.OK) {
                    connectionDB createcon = new connectionDB();
                    createcon.create_into_alevel(id, name, department, bkclass, status);
                    Stage stage = (Stage) createBook.getScene().getWindow();
                    Parent parent = FXMLLoader.load(getClass().getResource("createbook.fxml"));
                    stage.setScene(new Scene(parent, 800, 600));
                    stage.show();
                    stage.setOnCloseRequest((event -> {

                    }));

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    @FXML
    void create_olevel_function() {
        if (create().equals("Success")) {
            try {
                String id = id_field.getText();
                String name = name_field.getText();
                String bkclass = class_field.getValue();
                String status = available_olevel.getText();
                Alert alt = new Alert(Alert.AlertType.CONFIRMATION);
                alt.setContentText("Book inserted successful");
                Optional<ButtonType> result = alt.showAndWait();
                ButtonType button = result.orElse(ButtonType.CANCEL);
                if (button == ButtonType.OK) {
                    connectionDB createcon = new connectionDB();
                    createcon.create_into_olevel(id, name, bkclass, status);
                    Stage stage = (Stage) createBook_olevel.getScene().getWindow();
                    Parent parent = FXMLLoader.load(getClass().getResource("createbook.fxml"));
                    stage.setScene(new Scene(parent, 800, 600));
                    stage.show();
                    stage.setOnCloseRequest((event -> {

                    }));

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    Connection conn = connectionDB.Connectiondb2();

    private String create2() {
        String status = "Success";
        String names = book_id_field.getText();

        if (names.isEmpty()) {
            setErrors(Color.TOMATO, "Empty fields please");
            status = "Erros";
        } else {
            String sql = "SELECT * FROM alevel_books WHERE Book_ID = ?";
            try {
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, names);

                ResultSet rst = pst.executeQuery();
                if (rst.next()) {
                    setErrors(Color.RED, "book already in the database");
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

    private String create() {
        String status = "Success";
        String names = id_field.getText();

        if (names.isEmpty()) {
            setErrors(Color.TOMATO, "Empty fields please");
            status = "Erros";
        } else {
            String sql = "SELECT * FROM olevel_books WHERE Book_ID = ?";
            try {
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, names);

                ResultSet rst = pst.executeQuery();
                if (rst.next()) {
                    setErrors(Color.RED, "book already in the database");
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

    private void setErrors(Color mycolor, String text) {
        errorLabel.setText(text);
        errorLabel.setTextFill(mycolor);
        errorLabel1.setTextFill(mycolor);
        errorLabel1.setText(text);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        depart.setItems(list);
        book_class_field.setItems(list3);
        class_field.setItems(list2);
    }

    public void Go_olevel() {
        TranslateTransition slides = new TranslateTransition();
        slides.setDuration(Duration.seconds(2));
        slides.setNode(layer);
        slides.setToX(408);
        slides.play();
    }
}
