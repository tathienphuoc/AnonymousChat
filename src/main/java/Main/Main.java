package Main;

import Controllers.MainThread;
import Controllers.WelcomeController;
import Utils.ViewUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;

public class Main extends Application {
    public static Stage window;
    public static Socket socket;
    public static String partnerName;
    public static String host="localhost";
    public static int port=6000;

    static {
        try {
            socket = new Socket("localhost",6000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        ViewUtils.loadView(ViewUtils.WElCOME_VIEW);

    }
}