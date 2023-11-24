package Dashboard;


import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;


import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;



import java.net.URL;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * <b>The userDashboard controller</b>
 * This class controls the every component seen on the user dashboard window like piecharts,leftnavigation bar,links,
 * the transactions of borrowing,returning the book,
 * functions like olevel_inputs():for issueing book (borrowing) for olevel students
 * -Alevel_inputs():for issueing book (borrowing) for Alevel students
 * -olevel_returns():for returning  book for olevel students
 * -Alevel_returns():for returning  book for Alevel students
 */

public class UserDashController implements Initializable {

    @FXML
    private BorderPane myborder;







    public void Go_to_Alevel() throws IOException {
        Stage stage = new Stage();
        URL url = new File("src/Alevel/Subjects/alevelbooks.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage.setScene(new Scene(root,902,634));
        stage.setResizable(false);
        stage.setTitle("Alevel Books");
        stage.show();



    }

    @Override

    public void initialize(URL url, ResourceBundle rb) {


    }


    public void Olevel_inputs() throws IOException {
        Stage stage = new Stage();
        URL url = new File("src/View/issueOlevelBook.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage.setTitle("issue a book for Olevel");
        stage.setScene(new Scene(root, 800, 600));
        stage.setResizable(false);
        stage.show();
    }

    public void Alevel_inputs() throws IOException {
        Stage stage = new Stage();
        URL url = new File("src/View/issueAlevel.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage.setTitle("issue a book for Alevel");
        stage.setScene(new Scene(root, 800, 600));
        stage.setResizable(false);
        stage.show();
    }

    public void Alevel_returns() throws IOException {
        Stage stage = new Stage();
        URL url = new File("src/View/returnAlevel.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage.setTitle("return a book for Alevel");
        stage.setScene(new Scene(root,  800,600));
        stage.setResizable(false);
        stage.show();
    }

    public void Olevel_returns() throws IOException {
        Stage stage = new Stage();
        URL url = new File("src/View/returnOlevelBook.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage.setTitle("return a book for Olevel");
        stage.setScene(new Scene(root, 800, 600));
        stage.setResizable(false);
        stage.show();
    }

    public void Alevel_issued_books(ActionEvent event) throws IOException {

        URL url = new File("src/View/issuedAlevel.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        myborder.setCenter(root);
    }

    public void Olevel_issued_book() throws IOException {
        URL url = new File("src/View/IssuedOlevel.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        myborder.setCenter(root);

    }

    public void Alevel_returned_book() throws IOException {
        URL url = new File("src/View/returnedAlevel.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        myborder.setCenter(root);

    }

    public void Olevel_returned_book() throws IOException {
        URL url = new File("src/View/ReturnedOlevel.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        myborder.setCenter(root);

    }

    public void Go_to_Olevel() throws IOException {
        Stage stage = new Stage();
        URL url = new File("src/Olevel/Subjects/OlevelBooks.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage.setScene(new Scene(root,902,635));
        stage.setResizable(false);
        stage.setTitle("Olevel Books");
        stage.show();



    }


    public void Go_to_Settings() throws IOException {

        URL url = new File("src/LoginForm/settings.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        myborder.setCenter(root);
    }


    public void createAlevel() throws IOException {
        Stage stage = new Stage();
        URL url = new File("src/View/createbook.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage.setScene(new Scene(root, 800, 600));
        stage.setTitle("create book");
        stage.show();
    }



    public void other_inputs() throws IOException {
        Stage stage = new Stage();
        URL url = new File("src/students/issue_others.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage.setTitle("BookStore");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    public void other_returns() throws IOException {
        Stage stage = new Stage();
        URL url = new File("src/students/return_others.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage.setTitle("BookStore");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    public void other_returned_book() throws IOException {
        URL url = new File("src/students/returned_other.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        myborder.setCenter(root);
    }

    public void other_function() throws IOException {
        Stage stage = new Stage();
        URL url = new File("src/students/createother.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage.setScene(new Scene(root, 800, 600));
        stage.setTitle("create book");
        stage.show();
    }


    public void Go_to_other() throws IOException {
        Stage stage = new Stage();
        URL url = new File("src/Olevel/Subjects/otherBooks.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage.setTitle("BookStore");
        stage.setScene(new Scene(root, 902, 624));
        stage.show();

    }

    public void Total_function() {
    }

    public void registered_function() throws IOException {
        URL url = new File("src/students/Students.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        myborder.setCenter(root);
    }

    public void register_student_function() throws IOException {
        URL url = new File("src/Alevel/Subjects/register.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        myborder.setCenter(root);
    }

    public void other_issued_books() throws IOException {
        URL url = new File("src/students/issued_other.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        myborder.setCenter(root);

    }

    public void onClick() throws IOException {
        URL url = new File("src/Dashboard/homePage.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        myborder.setCenter(root);
    }
}


