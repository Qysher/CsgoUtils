package skins;

import com.sun.javafx.scene.control.skin.ButtonSkin;
import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class SidebarButton extends ButtonSkin {

    public SidebarButton(Button button) {
        super(button);

        ScaleTransition transitionIn = new ScaleTransition(Duration.millis(100), button);
        //transitionIn.setFromX(1.0d);
        transitionIn.setToX(1.075d);
        transitionIn.setAutoReverse(false);
        button.setOnMouseEntered(e -> transitionIn.play());

        ScaleTransition transitionOut = new ScaleTransition(Duration.millis(100), button);
        //transitionOut.setFromX(1.05d);
        transitionOut.setToX(1.0d);
        transitionOut.setAutoReverse(false);
        button.setOnMouseExited(e -> transitionOut.play());

        /*FadeTransition fadeIn = new FadeTransition(Duration.millis(100));
        fadeIn.setNode(button);
        fadeIn.setToValue(1);
        button.setOnMouseExited(e -> fadeIn.playFromStart());

        FadeTransition fadeOut = new FadeTransition(Duration.millis(100));
        fadeOut.setNode(button);
        fadeOut.setToValue(0.8);
        button.setOnMouseEntered(e -> fadeOut.playFromStart());*/
    }

}
