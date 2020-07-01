package sample;

import config.ConfigSystem;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;

public class Main extends Application {

    public static ConfigSystem configSystem;
    public static String filePath = System.getenv("APPDATA") + "/Qysher/TestProgramm/";

    public static SteamProfile userProfile = new SteamProfile();

    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = new FXMLLoader(getClass().getResource("main.fxml")).load();
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("CSGO Utils");
        primaryStage.setScene(new Scene(root, 1800, 900));
        primaryStage.show();

        stage = primaryStage;

        configSystem = new ConfigSystem(new File(filePath + "test.cfg")).load().registerShutdownHook();

    }

    public static void main(String[] args) {
        System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
        launch(args);
    }
}
