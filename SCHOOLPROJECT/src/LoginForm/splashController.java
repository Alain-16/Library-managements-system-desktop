package LoginForm;

import connections.connection;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class splashController implements Initializable {
    @FXML
    private StackPane stackpane;

    @FXML
    private AnchorPane anchor;

    @FXML
    private Label loadLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        FadeTransition fade = new FadeTransition(Duration.millis(3000), loadLabel);
        fade.setFromValue(1.0);
        fade.setToValue(0.3);
        fade.setCycleCount(FadeTransition.INDEFINITE);
        fade.setAutoReverse(true);
        fade.play();

        new connection().initdatabase();

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Thread.sleep(10000);
                return null;
            }

        };
        task.setOnSucceeded((e) -> openData());
        new Thread(task).start();
    }

    private void openData() {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/LoginForm/Login.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            PauseTransition delay = new PauseTransition(Duration.minutes(10));
            delay.setOnFinished( event -> stage.close() );
            delay.play();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
