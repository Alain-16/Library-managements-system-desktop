package View;

import connections.connectionDB;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import javafx.util.Duration;

import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class issueOlevelController implements Initializable {
    @FXML
    private Label errorlabel;

    @FXML
    private Button Borrow_button;

    @FXML
    private TextField number_BK;

    @FXML
    private TextField name_BK;

    @FXML
    private TextField class_bk;

    @FXML
    private TextField bk_status;

    @FXML
    private TextField student_class_field;

    @FXML
    private TextField student_name_field;

    @FXML
    private TextField student_id_field;

    @FXML
    private CheckBox Borrow_check2;

    @FXML
    private CheckBox period_field;

    @FXML
    private DatePicker date_field;


    @FXML
    private TextField number_BK1;

    @FXML
    private TextField name_BK1;


    @FXML
    private AnchorPane myAnchor;
    @FXML
    private VBox layer;

    @FXML
    private TextField class_bk1;

    @FXML
    private TextField bk_status1;

    @FXML
    private TextField student_id_field1;

    @FXML
    private TextField student_name_field1;

    @FXML
    private TextField student_class_field1;

    @FXML
    private CheckBox period_field1;

    @FXML
    private DatePicker date_field1;

    @FXML
    private CheckBox Borrow_check21;

    @FXML
    private Label errorlabel1;



    @FXML
    private ComboBox<String> roleCheck;

    private PreparedStatement pst;
    private ResultSet rst;

    ObservableList<String> list = FXCollections.observableArrayList("class monitor","teacher");



    public void fetch_function() {
        String sql = "SELECT * FROM students WHERE Student_ID = ? ";
        String number = student_id_field.getText();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "ALAIN", "0102Mugi");

            pst = conn.prepareStatement(sql);
            pst.setString(1, number);
            rst = pst.executeQuery();
            while (rst.next()) {
                String id = rst.getString("Student_ID");
                String name = rst.getString("Student_name");
                String bk_class = rst.getString("Student_class");


                student_id_field.setText(id);
                student_name_field.setText(name);
                student_class_field.setText(bk_class);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void pull_function() {
        String sql = "SELECT * FROM olevel_books WHERE Book_ID = ? ";
        String number = number_BK.getText();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "ALAIN", "0102Mugi");

            pst = conn.prepareStatement(sql);
            pst.setString(1, number);
            rst = pst.executeQuery();
            while (rst.next()) {
                String id = rst.getString("Book_ID");
                String name = rst.getString("Book_name");
                String bk_class = rst.getString("Book_class");
                String mystatus = rst.getString("Status");

                number_BK.setText(id);
                name_BK.setText(name);
                class_bk.setText(bk_class);

                bk_status.setText(mystatus);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void submite_inputs() {
        if (issueOlevel().equals("Success")) {
            try {
                pull_function();
                fetch_function();
                update_olevel_function();
                String id = number_BK.getText();
                String name = name_BK.getText();
                String classroom = class_bk.getText();
                String status = Borrow_check2.getText();
                String period = period_field.getText();
                Date date = Date.valueOf(date_field.getValue());
                String st_id = student_id_field.getText();
                String st_name = student_name_field.getText();
                String st_class = student_class_field.getText();
                String special_case = roleCheck.getValue();
                Alert alt = new Alert(Alert.AlertType.CONFIRMATION);
                alt.setContentText("Book Borrowed successful");
                Optional<ButtonType> result = alt.showAndWait();
                ButtonType button = result.orElse(ButtonType.CANCEL);
                if (button == ButtonType.OK) {
                    connectionDB logcon = new connectionDB();
                    logcon.insert_into_Olevel(id, name, classroom, period, date, status, st_id, st_name, st_class, special_case);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }

    Connection conn = connectionDB.Connectiondb2();
    private String issueOlevel() {
        String status = "Success";
        String myStatus = bk_status.getText();
        String number = number_BK.getText();


        if (myStatus.equals("Borrow/Borrowed")) {
            set_Error(Color.TOMATO, "");
            status = "Error";
        } else {
            String sql = "SELECT * FROM issued_olevel_books WHERE status = ?";
            try {
                pst = conn.prepareStatement(sql);
                pst.setString(1, number);

                rst = pst.executeQuery();
                if (rst.next()) {
                    set_Error(Color.TOMATO, "book already issued");
                    status = "Errors";
                } else {
                    set_Error(Color.GREEN, "Borrowed successfully........");
                    status = "Success";
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return status;
    }

    private String issueOlevelClass() {
        String status = "Success";
        String myStatus = bk_status1.getText();
        String number = number_BK1.getText();


        if (myStatus.equals("Borrow/Borrowed")) {
            set_Error(Color.TOMATO, "");
            status = "Error";
        } else {
            String sql = "SELECT * FROM issued_olevel_books WHERE Book_ID = ?";
            try {
                pst = conn.prepareStatement(sql);
                pst.setString(1, number);

                rst = pst.executeQuery();
                if (rst.next()) {
                    set_Error(Color.TOMATO, "book already issued");
                    status = "Errors";
                } else {
                    set_Error(Color.GREEN, "Borrowed successfully........");
                    status = "Success";
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return status;
    }


    private void set_Error(Color color, String text) {
        errorlabel.setTextFill(color);
        errorlabel.setText(text);
        errorlabel1.setText(text);
        errorlabel1.setTextFill(color);
    }



    public void slider() {
            TranslateTransition slides = new TranslateTransition();
            slides.setDuration(Duration.seconds(2));
            slides.setNode(layer);
            slides.setToX(400);
            slides.play();
            Borrow_button.setVisible(false);

    }

    public void update_olevel_function() {

        String Num = number_BK.getText();
        String Name = name_BK.getText();
        String myclass = class_bk.getText();
        String mystatus = Borrow_check2.getText();
        connectionDB con = new connectionDB();
        con.Update_Records_Olevel(Num, Name, myclass, mystatus);

    }



    public void Borrow_olevel_class() {
        if (issueOlevelClass().equals("Success")) {
            try {
                pull_function_class();
                fetch_function_class();
                update_class();
                String id = number_BK1.getText();
                String name = name_BK1.getText();
                String classroom = class_bk1.getText();
                String status = Borrow_check21.getText();
                String period = period_field1.getText();
                Date date = Date.valueOf(date_field1.getValue());
                String st_id = student_id_field1.getText();
                String st_name = student_name_field1.getText();
                String st_class = student_class_field1.getText();
                String special_case = roleCheck.getValue();
                Alert alt = new Alert(Alert.AlertType.CONFIRMATION);
                alt.setContentText("Book Borrowed successful");
                Optional<ButtonType> result = alt.showAndWait();
                ButtonType button = result.orElse(ButtonType.CANCEL);
                if (button == ButtonType.OK) {
                    connectionDB logcon = new connectionDB();
                    logcon.insert_into_Olevel(id, name, classroom, period, date, status, st_id, st_name, st_class, special_case);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void update_class(){
        String num = number_BK1.getText();
        String name = name_BK1.getText();
        String myclass = class_bk1.getText();
        String mystatus = Borrow_check21.getText();
        connectionDB con = new connectionDB();
        con.Update_class_Olevel(num,name,myclass,mystatus);
    }

    public void pull_function_class() {
        String sql = "SELECT * FROM olevel_books WHERE Book_ID = ? ";
        String number = number_BK1.getText();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "ALAIN", "0102Mugi");

            pst = conn.prepareStatement(sql);
            pst.setString(1, number);
            rst = pst.executeQuery();
            while (rst.next()) {
                String id = rst.getString("Book_ID");
                String name = rst.getString("Book_name");
                String bk_class = rst.getString("Book_class");
                String mystatus = rst.getString("Status");

                number_BK1.setText(id);
                name_BK1.setText(name);
                class_bk1.setText(bk_class);

                bk_status1.setText(mystatus);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fetch_function_class() {
        String sql = "SELECT * FROM students WHERE Student_ID = ? ";
        String number = student_id_field1.getText();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "ALAIN", "0102Mugi");

            pst = conn.prepareStatement(sql);
            pst.setString(1, number);
            rst = pst.executeQuery();
            while (rst.next()) {
                String id = rst.getString("Student_ID");
                String name = rst.getString("Student_name");
                String bk_class = rst.getString("Student_class");


                student_id_field1.setText(id);
                student_name_field1.setText(name);
                student_class_field1.setText(bk_class);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roleCheck.setItems(list);
    }

    public void slide_back() {
        TranslateTransition slide = new TranslateTransition(Duration.seconds(2), layer);
        slide.setToX(myAnchor.getLayoutX());
        slide.play();
        Borrow_button.setVisible(true);
        System.out.println("return Done");

    }
}
