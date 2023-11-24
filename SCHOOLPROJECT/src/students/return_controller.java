package students;

import connections.connectionDB;

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
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class return_controller implements Initializable {
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
    private DatePicker date_btn;

    @FXML
    private TextField issue_date;

    @FXML
    void pull_up() {
        String sql = "SELECT * FROM issued_other_books WHERE Book_ID = ? ";
        String number = numberBK.getText();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "ALAIN", "0102Mugi");

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, number);
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                String id = rst.getString("Book_id");
                String name = rst.getString("Book_name");
                String depart = rst.getString("Department");
                String types = rst.getString("Type");
                String mydate = rst.getString("issue_date");
                String mystatus = rst.getString("status");
                String stuid = rst.getString("Student_ID");
                String stuname = rst.getString("Student_name");
                String stuclass = rst.getString("Student_class");

                numberBK.setText(id);
                nameBK.setText(name);
                department_btn.setText(depart);
                type_btn.setText(types);
                status_btn.setText(mystatus);
                issue_date.setText(mydate);
                student_ID.setText(stuid);
                student_name.setText(stuname);
                student_class.setText(stuclass);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void update_function() {
        String Num = numberBK.getText();
        String Name = nameBK.getText();
        String myclass = type_btn.getText();
        String mydepart = department_btn.getText();
        String mystatus = Borrow_check.getText();
        connectionDB con = new connectionDB();
        con.Update_others(Num, Name, mydepart, myclass, mystatus);

    }

    private String Mycheck() {
        String status;
        String myStatus = status_btn.getText();


        if (myStatus.equals("Borrow/Borrowed")) {
            set_Error(Color.TOMATO, "");
            status = "Error";
        } else {
            set_Error(Color.GREEN, "book is available...");
            status = "Success";
        }

        return status;
    }

    private void set_Error(Color mycolor, String text) {
        errorlabel.setTextFill(mycolor);
        errorlabel.setText(text);
    }


   /* public void return_function() {
        if (Mycheck().equals("Success")) {
            try {
                pull_up();
                update_function();
                String id = numberBK.getText();
                String bookname = nameBK.getText();
                String depart = department_btn.getText();
                String classroom = type_btn.getText();
                String date2 = issue_date.getText();
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
                    logcon.return_into_others(id, bookname, classroom, depart, date2, date, status, student_id, student, st_class);
                    Stage stage = (Stage) Borrrow_btn.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("return_others.fxml"));
                    stage.setScene(new Scene(root, 800, 600));
                    stage.show();
                    stage.setOnCloseRequest((event) -> {
                    });
                }

            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }*/


    public void function_return() {
        if (Mycheck().equals("Success")) {
            try {
                pull_up();
                update_function();
                String id = numberBK.getText();
                String bookname = nameBK.getText();
                String depart = department_btn.getText();
                String classroom = type_btn.getText();
                String date2 = issue_date.getText();
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
                    logcon.return_into_others(id, bookname, classroom, depart, date2, date, status, student_id, student, st_class);
                    Stage stage = (Stage) Borrrow_btn.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("return_others.fxml"));
                    stage.setScene(new Scene(root, 800, 600));
                    stage.show();
                    stage.setOnCloseRequest((event) -> {
                    });
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
