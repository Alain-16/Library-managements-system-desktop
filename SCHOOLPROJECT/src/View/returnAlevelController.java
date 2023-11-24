package View;

import connections.connectionDB;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;

import javafx.scene.control.*;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.sql.*;
import java.util.Optional;

public class returnAlevelController {
    @FXML
    private AnchorPane myanchor;

    @FXML
    private TextField student_id_field1;

    @FXML
    private TextField student_name_field2;

    @FXML
    private DatePicker return_field;

    @FXML
    private TextField student_class_field2;

    @FXML
    private TextField bk_status1;

    @FXML
    private TextField class_bk1;

    @FXML
    private TextField Depart;

    @FXML
    private TextField name_BK1;

    @FXML
    private TextField number_BK1;

    @FXML
    private TextField date_field1;

    @FXML
    private CheckBox alevel_check;

    @FXML
    private Label errorlabel1;

    @FXML
    private Button Borrow_button;

    @FXML
    private TextField number_bk;

    @FXML
    private TextField name_BK;

    @FXML
    private TextField bk_status;

    @FXML
    private TextField class_bk;

    @FXML
    private TextField Depart1;

    @FXML
    private TextField student_id_field;

    @FXML
    private TextField student_name_field;

    @FXML
    private TextField student_class_field;
    @FXML
    private TextField period1;
    @FXML
    private TextField period2;

    @FXML
    private TextField issued_field;

    @FXML
    private CheckBox classCheck;

    @FXML
    private DatePicker date_field;



    @FXML
    private ComboBox<String> role;

    @FXML
    private VBox layer;



    private PreparedStatement pst;
    private ResultSet rst;

    @FXML
    void Borrow_as_class_function() {
        if (Mycheck3().equals("Success")) {
            try {
                fetch_function();
                update_class();
                update_Alevel();
                String id = number_bk.getText();
                String bookname = name_BK.getText();
                String classroom = class_bk.getText();
                String mydate = issued_field.getText();
                Date date = Date.valueOf(date_field.getValue());
                String status = classCheck.getText();
                String student_id = student_id_field.getText();
                String student = student_name_field.getText();
                String st_class = student_class_field.getText();
                String class_checks = role.getValue();
                Alert alt = new Alert(Alert.AlertType.CONFIRMATION);
                alt.setContentText("Book returned successful");
                Optional<ButtonType> result = alt.showAndWait();
                ButtonType button = result.orElse(ButtonType.CANCEL);
                if (button == ButtonType.OK) {
                    connectionDB logcon = new connectionDB();
                    logcon.return_into_Olevel(id, bookname, classroom, mydate, date, status, student_id, student, st_class, class_checks);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

    private String Mycheck3() {
        String status;
        String myStatus = bk_status.getText();

        if (myStatus.equals("Borrow/Borrowed")) {
            set_Error(Color.TOMATO, "");
            status = "Success";
        } else {
            set_Error(Color.GREEN, "book is available...");
            status = "Success";
        }

        return status;
    }


    @FXML
    void fetch_function() {
        String sql = "SELECT * FROM issued_alevel_books WHERE Book_ID = ? ";
        String number = number_bk.getText();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "ALAIN", "0102Mugi");

            pst = conn.prepareStatement(sql);
            pst.setString(1, number);
            rst = pst.executeQuery();
            while (rst.next()) {
                String id = rst.getString("Book_ID");
                String name = rst.getString("Book_name");
                String mydepart = rst.getString("Department");
                String bk_class = rst.getString("Book_class");
                String myperiod = rst.getString("period");
                String mydate = rst.getString("issued_date");
                String mystatus = rst.getString("status");
                String stuid = rst.getString("Student_ID");
                String stuname = rst.getString("Student_name");
                String stuclass = rst.getString("student_class");

                number_bk.setText(id);
                name_BK.setText(name);
                Depart1.setText(mydepart);
                class_bk.setText(bk_class);
                period2.setText(myperiod);
                bk_status.setText(mystatus);
                issued_field.setText(mydate);
                student_id_field.setText(stuid);
                student_name_field.setText(stuname);
                student_class_field.setText(stuclass);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void update_class(){
        String Num = number_bk.getText();
        String Name = name_BK.getText();
        String depart = Depart1.getText();
        String myclass = class_bk.getText();
        String mystatus = classCheck.getText();
        connectionDB con = new connectionDB();
        con.Update_Records_class(Num, Name,depart, myclass, mystatus);
    }

    @FXML
    void pull_function() {
        String sql = "SELECT * FROM issued_alevel_books WHERE Book_ID = ? ";
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
                String depart = rst.getString("Department");
                String myperiod = rst.getString("period");
                String bk_class = rst.getString("Book_class");
                String mydate = rst.getString("issued_date");
                String mystatus = rst.getString("status");
                String stuid = rst.getString("Student_ID");
                String stuname = rst.getString("Student_name");
                String stuclass = rst.getString("student_class");

                number_BK1.setText(id);
                name_BK1.setText(name);
                Depart.setText(depart);
                period1.setText(myperiod);
                class_bk1.setText(bk_class);
                bk_status1.setText(mystatus);
                date_field1.setText(mydate);
                student_id_field1.setText(stuid);
                student_name_field2.setText(stuname);
                student_class_field2.setText(stuclass);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @FXML
    void slide_over() {
        TranslateTransition slides = new TranslateTransition();
        slides.setDuration(Duration.seconds(2));
        slides.setNode(layer);
        slides.setToX(400);
        slides.play();
        Borrow_button.setVisible(false);

    }

    @FXML
    void submite_inputs() {
        if (Mycheck().equals("Success")) {
            try {
                pull_function();
                update_function();
                update_Alevel();
                String id = number_BK1.getText();
                String bookname = name_BK1.getText();
                String depart = Depart.getText();
                String classroom = class_bk1.getText();
                String date2 =date_field1 .getText();
                Date date = Date.valueOf(return_field.getValue());
                String status = alevel_check.getText();
                String student_id = student_id_field1.getText();
                String student = student_name_field2.getText();
                String st_class = student_class_field2.getText();
                String class_checks =  role.getValue();
                Alert alt = new Alert(Alert.AlertType.CONFIRMATION);
                alt.setContentText("Book returned successful");
                Optional<ButtonType> result = alt.showAndWait();
                ButtonType button = result.orElse(ButtonType.CANCEL);
                if (button == ButtonType.OK) {
                    connectionDB logcon = new connectionDB();
                    logcon.return_into_Alevel(id, bookname, classroom, depart, date2, date, status, student_id, student, st_class, class_checks);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

        private String Mycheck () {
            String status;
            String myStatus = bk_status1.getText();
           if (myStatus.equals("Borrow/Borrowed")) {
                set_Error(Color.TOMATO, "");
                status = "Success";
            } else {
                set_Error(Color.GREEN, "book is available...");
                status = "Success";
            }

            return status;
        }


        private void set_Error(Color color, String text) {
            errorlabel1.setTextFill(color);
            errorlabel1.setText(text);
        }
    public void update_Alevel(){
        String num = number_BK1.getText();
        String name = name_BK1.getText();
        String depart = Depart.getText();
        String myclass = class_bk1.getText();
        Date mydate = Date.valueOf(return_field.getValue());
        String mystatus = alevel_check.getText();
        String stu_id = student_id_field1.getText();
        String stu_name = student_name_field2.getText();
        String stu_class = student_class_field2.getText();
        String myrole = role.getValue();
        connectionDB con = new connectionDB();
        con.update_alevel(num,name,myclass,depart,mydate,mystatus,stu_id,stu_name,stu_class,myrole);
    }



    private void update_function(){
        String Num = number_BK1.getText();
        String Name = name_BK1.getText();
        String myclass = class_bk1.getText();
        String mydepart = Depart.getText();
        String mystatus = alevel_check.getText();
        connectionDB con = new connectionDB();
        con.UpdateRecords(Num, Name, mydepart, myclass, mystatus);

    }

    public void slide_back() {
        TranslateTransition slide = new TranslateTransition(Duration.seconds(2), layer);
        slide.setToX(myanchor.getLayoutX());
        slide.play();
        Borrow_button.setVisible(true);
        System.out.println("return Done");
    }
}
