package sample;

import config.ConfigSystem;
import fxml.FXMLClassLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    public static ConfigSystem configSystem;
    public static String filePath = System.getenv("APPDATA") + "/Qysher/TestProgramm/";

    public static SteamProfile userProfile = new SteamProfile();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(FXMLClassLoader.getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 660, 420));
        primaryStage.show();

        configSystem = new ConfigSystem(new File(filePath + "test.cfg")).load().registerShutdownHook();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
