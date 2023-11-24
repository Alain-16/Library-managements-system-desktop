package Dashboard;


import connections.connectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class userController implements Initializable {
    @FXML
    private Button btnback;
    @FXML
    private TableView<booksOL> Tabledata;
    @FXML
    private TableColumn<booksOL, String> nameCol;

    @FXML
    private TableColumn<booksOL, Integer> passwordCol;


    private PreparedStatement pst;
    ObservableList<booksOL> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initcol();
        try {
            populateTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    private void initcol() {

        nameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));


    }

    private void populateTable() throws SQLException {
        Connection dbhandler = connectionDB.Connectiondb2();
        String sql = "SELECT * FROM Library.USERS";
        pst = dbhandler.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {

            String name = rs.getString("username");
            int passwords = rs.getInt("password");

            list.add(new booksOL(name, passwords));


        }
        Tabledata.setItems(list);

    }

    @FXML
    public void Go_back() throws IOException {
        Stage stage = (Stage) btnback.getScene().getWindow();
        URL url = new File("src/Dashboard/UserDashboard.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage.setScene(new Scene(root, 800, 700));
        stage.setFullScreen(true);
        stage.setMaximized(true);
        stage.show();
        stage.close();
        stage.setOnCloseRequest((event) -> {
        });

    }

}
