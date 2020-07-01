package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.Effect;
import javafx.scene.effect.MotionBlur;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    Pane titlePane = null;

    @FXML
    VBox sidebarVbox = null;

    @FXML
    WebView mainWebView = null;

    private boolean isWebViewValid() {
        return mainWebView != null;
    }

    public void openWebsite(String url) {
        if(isWebViewValid())
            mainWebView.getEngine().load(url);
    }

    @FXML
    public void onClickBackpack(ActionEvent event) {
        openWebsite("https://csgobackpack.net/?nick=76561198840652323");
    }

    @FXML
    public void onClickExchange(ActionEvent event) {
        openWebsite("http://csgo.exchange/id/76561198840652323");
    }

    @FXML
    public void onClickStash(ActionEvent event) {
        openWebsite("https://csgostash.com/");
    }

    @FXML
    public void onClickProfile(ActionEvent event) {
        openWebsite("https://steamcommunity.com/id/angoennerman/");
    }

    @FXML
    public void onClickInventory(ActionEvent event) {
        openWebsite("https://steamcommunity.com/id/angoennerman/inventory/#730");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(isWebViewValid()) {

            mainWebView.getEngine().setJavaScriptEnabled(true);
            mainWebView.getEngine().setUserDataDirectory(new File(Main.filePath + "browser.dat"));
        }

        if(titlePane != null) {
            titlePane.setOnMousePressed(mouseEvent -> {
                dragDelta.x = Main.stage.getX() - mouseEvent.getScreenX();
                dragDelta.y = Main.stage.getY() - mouseEvent.getScreenY();
            });

            titlePane.setOnMouseDragged(mouseEvent -> {
                Main.stage.setX(mouseEvent.getScreenX() + dragDelta.x);
                Main.stage.setY(mouseEvent.getScreenY() + dragDelta.y);
            });
        }

    }

    final Delta dragDelta = new Delta();

    // records relative x and y co-ordinates.
    class Delta { double x, y; }

}