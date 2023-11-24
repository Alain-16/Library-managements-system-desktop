package LoginForm;


import connections.connection;
import connections.connectionDB;
import javafx.animation.TranslateTransition;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

import java.net.MalformedURLException;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class settingsController implements Initializable {
    @FXML
    private AnchorPane anchor;

    @FXML
    private Button SaveButton;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private VBox layer;

    @FXML
    private ImageView myImage;

    @FXML
    private Button EditButton;

    @FXML
    private AnchorPane layer2;

    @FXML
    private TextField nameField;

    @FXML
    private TextField userField;

    @FXML
    private TextField passField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField Phonefield;

    @FXML
    private Button profile_button;

    @FXML
    private Hyperlink link;

    @FXML
    private Hyperlink ProfileLink;

    @FXML
    private Hyperlink DocLink;


    @FXML
    public void Go_Documentation() {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("JavaFX WebView Example");

        WebView webView = new WebView();

        webView.getEngine().load("file:///home/alain/library%20management/SCHOOL%20PROJECT/src/java/index.html");
        //WebEngine wb = new WebEngine();
        //webView.setStyle("stylesheet.css");

        VBox vBox = new VBox(webView);
        Scene scene = new Scene(vBox, 960, 600);

        primaryStage.setScene(scene);
        primaryStage.show();

    }


    @FXML
    public void Go_Profile() throws SQLException {
        String name = nameField.getText();
        String user = userField.getText();
        String password = passField.getText();
        String email = emailField.getText();
        String phone = Phonefield.getText();
        Alert alt = new Alert(Alert.AlertType.CONFIRMATION);
        alt.setContentText("password successfully changed");
        Optional<ButtonType> result = alt.showAndWait();
        ButtonType button = result.orElse(ButtonType.CANCEL);
        if (button == ButtonType.OK) {
            connectionDB con = new connectionDB();
            con.insert_into_profile(name, user, password, email, phone);
            TranslateTransition slide = new TranslateTransition(Duration.seconds(2), layer2);
            slide.setToY(anchor.getLayoutY());
            slide.play();
        }

    }


    @FXML
    public void Go_change() {
        TranslateTransition slides = new TranslateTransition();
        slides.setDuration(Duration.seconds(2));
        slides.setNode(layer);
        slides.setToX(300);
        slides.play();
        //slides.setByZ(499);
        link.setVisible(true);


    }

    @FXML
    public void Go_Edit() throws MalformedURLException {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.bmp", "*.png", "*.jpg", "*.gif"));
        File file = chooser.showOpenDialog(new Stage());
        if (file != null) {
            String imagepath = file.toURI().toURL().toString();
            System.out.println("file:" + imagepath);
            Image image = new Image(imagepath);
            System.out.println("height:" + image.getHeight() + "\nWidth:" + image.getWidth());
            myImage.setImage(image);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Please Select an image");

            alert.showAndWait();
        }

    }


    public void Go_Saved() throws SQLException {

        String value1 = usernameField.getText();
        String value2 = passwordField.getText();
        Alert alt = new Alert(Alert.AlertType.CONFIRMATION);
        alt.setContentText("password successfully changed");
        Optional<ButtonType> result = alt.showAndWait();
        ButtonType button = result.orElse(ButtonType.CANCEL);
        if (button == ButtonType.OK) {

            Connection conn = connection.connectionDB();
            String sql = "UPDATE USERS SET password ='" + value2 + "' WHERE username = '" + value1 + "'";
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.executeUpdate();
            TranslateTransition slide = new TranslateTransition(Duration.seconds(2), layer);
            slide.setToX(anchor.getLayoutX());
            slide.play();
            Stage stage = new Stage();

            System.out.println("successfully updated");


        }

    }

    @FXML
    public void Go_to_Profile() {
        TranslateTransition slides = new TranslateTransition();
        slides.setDuration(Duration.seconds(2));
        slides.setNode(layer2);
        slides.setToY(550);
        slides.play();


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void Go_back() {
        TranslateTransition slide = new TranslateTransition(Duration.seconds(2), layer2);
        slide.setToY(anchor.getLayoutX());
        slide.play();
        System.out.println("return Done");
    }
}
