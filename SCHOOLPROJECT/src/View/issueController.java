package View;


import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import connections.connectionDB;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.IOException;
import java.net.URL;
import java.sql.*;


import java.util.Optional;
import java.util.ResourceBundle;


/**
 * <b>The issue controller class</b>
 * This controls the borrow or issue form window consists of bookid,bookname,booknumber,class,studentname fields that enters the data in the database
 * the <b>conn</b> is the object of connectionDB class which connects to the database it is used in the issue() function to check for
 * the entered credentails if are valid,
 * and the submit_inputs() function to submit the credentails.
 */


public class issueController implements Initializable {
    @FXML
    private Label errorlabel;


    @FXML
    private TextField numberBK;

    @FXML
    private TextField nameBK;

    @FXML
    private TextField department_btn;

    @FXML
    private TextField class_btn;

    @FXML
    private TextField status_btn;

    @FXML
    private TextField student_ID;

    @FXML
    private CheckBox period_btn;

    @FXML
    private TextField student_name;

    @FXML
    private DatePicker date_btn;

    @FXML
    private TextField student_class;

    @FXML
    private ComboBox<String> RoleCheck;


    @FXML
    private CheckBox Borrow_check;

    @FXML
    private Button Borrrow_btn;

    @FXML
    private AnchorPane myAnchor;
    @FXML
    private VBox layer;

    @FXML
    private Button Borrow_class;

    @FXML
    private TextField numberBK1;

    @FXML
    private TextField nameBK1;

    @FXML
    private TextField department_btn1;

    @FXML
    private TextField class_btn1;

    @FXML
    private TextField status_btn1;

    @FXML
    private TextField student_ID1;

    @FXML
    private TextField student_name1;

    @FXML
    private TextField student_class1;

    @FXML
    private DatePicker date_btn1;

    @FXML
    private CheckBox period_btn1;

    @FXML
    private CheckBox Borrow_check1;

    @FXML
    private Label errorlabel1;

    PreparedStatement pst = null;
    ResultSet rst = null;

    ObservableList<String> list = FXCollections.observableArrayList("class monitor", "Teache");

    public void pull_up() {
        String sql = "SELECT * FROM alevel_books WHERE Book_ID = ? ";
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
                String bk_class = rst.getString("Book_class");
                String mystatus = rst.getString("Status");

                numberBK.setText(id);
                nameBK.setText(name);
                department_btn.setText(depart);
                class_btn.setText(bk_class);
                status_btn.setText(mystatus);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

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
                String bk_class = rst.getString("Student_class");


                student_ID.setText(id);
                student_name.setText(name);
                student_class.setText(bk_class);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public void Borrow_function() {

        if (Mycheck().equals("Success")) {
            try {
                pull_up();
                pull_up_student();
                update_function();

                String id = numberBK.getText();
                String bookname = nameBK.getText();
                String depart = department_btn.getText();
                String classroom = class_btn.getText();
                String period = period_btn.getText();
                Date date = Date.valueOf(date_btn.getValue());
                String status = Borrow_check.getText();
                String student_id = student_ID.getText();
                String student = student_name.getText();
                String st_class = student_class.getText();
                String class_checks = RoleCheck.getValue();
                Alert alt = new Alert(Alert.AlertType.CONFIRMATION);
                alt.setContentText("Book Borrowed successful");
                Optional<ButtonType> result = alt.showAndWait();
                ButtonType button = result.orElse(ButtonType.CANCEL);
                if (button == ButtonType.OK) {
                    connectionDB logcon = new connectionDB();
                    logcon.insertRecord(id, bookname, depart, classroom, period, date, status, student_id, student, st_class, class_checks);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        }


    }

    private String Mycheck() {
        String status;
        String myStatus = status_btn.getText();
        String combo = Borrow_check.getText();
        if (combo.isEmpty()) {
            set_Error(Color.CRIMSON, "Empty fields please fill up.......");
            status = "Error";
        }
         else {
            set_Error(Color.GREEN, "book is available...");
            status = "Success";
        }

        return status;
    }

    private String MyClassCheck() {
        String status;
        String myStatus = status_btn1.getText();
        String combo = Borrow_check1.getText();
        if (combo.isEmpty()) {
            set_Error(Color.CRIMSON, "Empty fields please fill up.......");
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
        errorlabel1.setTextFill(color);
        errorlabel1.setText(text);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RoleCheck.setItems(list);

    }

    public void update_function() {

        String Num = numberBK.getText();
        String Name = nameBK.getText();
        String myclass = class_btn.getText();
        String mydepart = department_btn.getText();
        String mystatus = Borrow_check.getText();
        connectionDB con = new connectionDB();
        con.UpdateRecords(Num, Name, mydepart, myclass, mystatus);

    }

    public void update_class() {
        String num = numberBK1.getText();
        String name = nameBK1.getText();
        String depart = department_btn1.getText();
        String myclass = class_btn1.getText();
        String status = status_btn1.getText();
        connectionDB con = new connectionDB();
        con.Update_Records_class(num, name, depart, myclass, status);

    }


    public void Borrow_as_class_function() {
        if (MyClassCheck().equals("Success")) {
            try {
                pull_up_student();
                pull_up_class();
                update_class();

                String id = numberBK1.getText();
                String bookname = nameBK1.getText();
                String depart = department_btn1.getText();
                String classroom = class_btn1.getText();
                String period = period_btn1.getText();
                Date date = Date.valueOf(date_btn1.getValue());
                String status = Borrow_check1.getText();
                String student_id = student_ID1.getText();
                String student = student_name1.getText();
                String st_class = student_class1.getText();
                String class_checks = RoleCheck.getValue();
                Alert alt = new Alert(Alert.AlertType.CONFIRMATION);
                alt.setContentText("Book Borrowed successful");
                Optional<ButtonType> result = alt.showAndWait();
                ButtonType button = result.orElse(ButtonType.CANCEL);
                if (button == ButtonType.OK) {
                    connectionDB logcon = new connectionDB();
                    logcon.insertRecord(id, bookname, depart, classroom, period, date, status, student_id, student, st_class, class_checks);
                    Stage stage = (Stage) Borrow_class.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("issueAlevel.fxml"));
                    stage.setScene(new Scene(root, 1100, 650));
                    stage.show();
                    stage.setOnCloseRequest((event) -> {
                    });
                }

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }


        public void slider () {
            TranslateTransition slides = new TranslateTransition();
            slides.setDuration(Duration.seconds(2));
            slides.setNode(layer);
            slides.setToX(400);
            slides.play();
            Borrrow_btn.setVisible(false);

        }

        public void pull_up_class () {
            String sql = "SELECT * FROM alevel_books WHERE Book_ID = ? ";
            String number = numberBK1.getText();
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
                    String bk_class = rst.getString("Book_class");
                    String mystatus = rst.getString("Status");

                    numberBK1.setText(id);
                    nameBK1.setText(name);
                    department_btn1.setText(depart);
                    class_btn1.setText(bk_class);
                    status_btn1.setText(mystatus);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        public void pull_up_student_class () {
            String sql = "SELECT * FROM students WHERE Student_ID = ? ";
            String number = student_ID1.getText();
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


                    student_ID1.setText(id);
                    student_name1.setText(name);
                    student_class1.setText(bk_class);

                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        }



        public void slide_back () {
            TranslateTransition slide = new TranslateTransition(Duration.seconds(2), layer);
            slide.setToX(myAnchor.getLayoutX());
            slide.play();
            Borrrow_btn.setVisible(true);
            System.out.println("return Done");
        }

}



