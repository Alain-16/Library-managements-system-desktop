package LoginForm;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class logMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        URL url = new File("src/LoginForm/splash.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("Welcome");
        Scene scene = new Scene(root, 700, 450);
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
       PauseTransition delay = new PauseTransition(Duration.seconds(15));
        delay.setOnFinished( event -> primaryStage.close() );
        delay.play();
        primaryStage.show();


    }
}
