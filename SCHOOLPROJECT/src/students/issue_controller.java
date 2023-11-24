package students;

import connections.connectionDB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Optional;

public class issue_controller {
    @FXML
    private Label errorlabel;

    @FXML
    private TextField numberBK;

    @FXML
    private TextField nameBK;

    @FXML
    private TextField department_btn;

    @FXML
    private TextField type_btn;

    @FXML
    private TextField status_btn;

    @FXML
    private TextField student_ID;

    @FXML
    private TextField student_name;

    @FXML
    private TextField student_class;

    @FXML
    private CheckBox Borrow_check;

    @FXML
    private Button Borrrow_btn;

    @FXML
    private CheckBox period_btn;

    @FXML
    private DatePicker date_btn;

    @FXML
    private CheckBox class_check;

    @FXML
    void Borrow_function() {
        if (Mycheck().equals("Success")) {
            try {
                pull_up();
                pull_up_student();
                update_other();
                String id = numberBK.getText();
                String bookname = nameBK.getText();
                String depart = department_btn.getText();
                String classroom = type_btn.getText();
                String period = period_btn.getText();
                Date date = Date.valueOf(date_btn.getValue());
                String status = Borrow_check.getText();
                String student_id = student_ID.getText();
                String student = student_name.getText();
                String st_class = student_class.getText();
                Alert alt = new Alert(Alert.AlertType.CONFIRMATION);
                alt.setContentText("Book Borrowed successful");
                Optional<ButtonType> result = alt.showAndWait();
                ButtonType button = result.orElse(ButtonType.CANCEL);
                if (button == ButtonType.OK) {
                    connectionDB logcon = new connectionDB();
                    logcon.insertRecord_others(id, bookname, depart, classroom, period, date, status, student_id, student, st_class);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    PreparedStatement pst = null;
    ResultSet rst = null;

    public void pull_up() {
        String sql = "SELECT * FROM other_books WHERE Book_ID = ? ";
        String number = numberBK.getText();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "ALAIN", "0102Mugi");

            pst = conn.prepareStatement(sql);
            pst.setString(1, number);
            rst = pst.executeQuery();
            while (rst.next()) {
                String id = rst.getString("Book_ID");
                String name = rst.getString("Book_Name");
                String depart = rst.getString("Department");
                String bk_class = rst.getString("Type");
                String mystatus = rst.getString("Status");

                numberBK.setText(id);
                nameBK.setText(name);
                department_btn.setText(depart);
                type_btn.setText(bk_class);
                status_btn.setText(mystatus);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String Mycheck() {
        String status;
        String myStatus = status_btn.getText();
        String combo = Borrow_check.getText();
        if (combo.isEmpty()) {
            set_Error(Color.CRIMSON, "Empty fields please fill up.......");
            status = "Error";
        } else if (myStatus.equals("Borrow/Borrowed")) {
            set_Error(Color.TOMATO, "");
            status = "Error";
        } else {
            set_Error(Color.GREEN, "book is available...");
            status = "Success";
        }

        return status;
    }

    private void set_Error(Color color, String text) {
        errorlabel.setTextFill(color);
        errorlabel.setText(text);
    }

    public void update_other() {

        String Num = numberBK.getText();
        String Name = nameBK.getText();
        String myclass = type_btn.getText();
        String mydepart = department_btn.getText();
        String mystatus = Borrow_check.getText();
        connectionDB con = new connectionDB();
        con.Update_others(Num, Name, mydepart, myclass, mystatus);

    }


    public void pull_up_student() {
        String sql = "SELECT * FROM students WHERE Student_ID = ? ";
        String number = student_ID.getText();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "ALAIN", "0102Mugi");

            pst = conn.prepareStatement(sql);
            pst.setString(1, number);
            rst = pst.executeQuery();
            while (rst.next()) {
                String id = rst.getString("Student_ID");
                String name = rst.getString("Student_name");
                String depart = rst.getString("Combination");
                String bk_class = rst.getString("Student_class");

                student_ID.setText(id);
                student_name.setText(name);
                department_btn.setText(depart);
                student_class.setText(bk_class);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
