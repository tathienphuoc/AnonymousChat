package Main;

import Controllers.WelcomeController;
import Utils.ViewUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static Stage window;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        window = stage;
        ViewUtils.loadView(ViewUtils.MAIN_LOADING_VIEW);
    }
}