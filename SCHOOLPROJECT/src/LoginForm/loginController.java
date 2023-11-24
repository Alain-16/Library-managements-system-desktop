package LoginForm;


import connections.connectionDB;
import javafx.animation.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;

import LoginForm.splashController;

import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;
import java.io.IOException;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * <b>The logincontroller class</b>
 * This class controls the login and the signup window which is considered as a landing page of the a app
 * It contains of main functions like (login()) which iterats with database and graints access to the user if the credentails are valid
 */
public class loginController implements Initializable {
    @FXML
    private AnchorPane MyAnchor;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label ErroLabel;

    @FXML
    private Hyperlink forgotLink;

    @FXML
    private TextField usernameField;
    @FXML
    private Label errorlable;


    @FXML
    private PasswordField Password2Field;

    @FXML
    private Button SiginButton;

    @FXML
    private VBox layer;

    @FXML
    private Button Sigin_Button;

    @FXML
    private TextField emailField;


    @FXML
    private Button loginButton;

    PreparedStatement pst = null;
    ResultSet rst = null;

    @FXML
    public void Login_Go() {
        if (login().equals("Success")) {
            try {
                loginButton.getScene().getWindow();
                URL url = new File("src/Dashboard/UserDashboard.fxml").toURI().toURL();
                Parent root = FXMLLoader.load(url);
                Stage stage = new Stage();
                stage.setScene(new Scene(root, 1090, 690));
                stage.setTitle("Book Store Dashboard");
                stage.close();
                stage.setResizable(false);
                stage.show();
                stage.setOnCloseRequest((event) -> {
                });

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    Connection conn = connectionDB.Connectiondb2();


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    private String login() {
        String status = "Success";
        String names = nameField.getText();
        String password = passwordField.getText();
        if (names.isEmpty() || password.isEmpty()) {
            setLblError(Color.TOMATO, "Empty fields please");
            status = "Erros";
        } else {
            String sql = "SELECT * FROM users WHERE username = ? and password = ?";
            try {
                pst = conn.prepareStatement(sql);
                pst.setString(1, names);
                pst.setString(2, password);
                rst = pst.executeQuery();
                if (!rst.next()) {
                    setLblError(Color.TOMATO, "Enter correct username/password");
                    status = "Errors";
                } else {
                    setLblError(Color.GREEN, "Login Successfully.....Redirecting....");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return status;
    }

    @FXML
    public void Go_To_Sigin() throws IOException {
        if (usernameField.getText().isEmpty()) {
            set_Error(Color.TOMATO, "Enter username!!!");
        }
        if (Password2Field.getText().isEmpty()) {
            set_Error(Color.TOMATO, "Enter password!!!");
        } else {
            String name = usernameField.getText();
            String password = Password2Field.getText();
            String em = emailField.getText();
            Alert alt = new Alert(Alert.AlertType.CONFIRMATION);
            alt.setContentText("Successfully Signedin");
            Optional<ButtonType> result = alt.showAndWait();
            ButtonType button = result.orElse(ButtonType.CANCEL);
            if (button == ButtonType.OK) {
                connectionDB logcon = new connectionDB();
                logcon.Sigin(name, password,em);
                Stage stage = (Stage) SiginButton.getScene().getWindow();
                URL url = new File("src/Dashboard/UserDashboard.fxml").toURI().toURL();
                Parent root = FXMLLoader.load(url);
                stage.setScene(new Scene(root, 1090, 690));
                stage.setResizable(false);
                stage.show();
            }
        }


    }

    public void Go_On_Forgot() throws IOException {
        Stage stage = new Stage();
        URL url = new File("src/LoginForm/settings.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage.setTitle("Settings");
        stage.setScene(new Scene(root, 900, 600));
        stage.setFullScreen(false);
        stage.setMaximized(false);
        stage.show();
    }


    private void setLblError(Color color, String text) {
        ErroLabel.setTextFill(color);
        ErroLabel.setText(text);
        System.out.println(text);
    }

    private void set_Error(Color color, String text) {
        errorlable.setTextFill(color);
        errorlable.setText(text);
    }

    @FXML
    public void Sigin() {
        TranslateTransition slides = new TranslateTransition();
        slides.setDuration(Duration.seconds(2));
        slides.setNode(layer);
        slides.setToX(400);
        slides.play();
        Sigin_Button.setVisible(true);
        loginButton.setVisible(false);

    }

    @FXML
    public void Slide_back() {
        TranslateTransition slide = new TranslateTransition(Duration.seconds(2), layer);
        slide.setToX(MyAnchor.getLayoutX());
        slide.play();
        loginButton.setVisible(true);
        System.out.println("return Done");

    }
}
