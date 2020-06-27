package fxml;

import java.net.URL;

public class FXMLClassLoader {

    public static URL getResource(String name) {
        return FXMLClassLoader.class.getResource(name);
    }

}
