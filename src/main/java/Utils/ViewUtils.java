package Utils;

import Controllers.WelcomeController;
import Main.Main;
import javafx.css.Style;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewUtils {
    public static final String WElCOME_VIEW="welcome.fxml";
    public static final String CHAT_VIEW="chat.fxml";
    public static final String MAIN_VIEW="main.fxml";
    public static final String MAIN_LOADING_VIEW="main-loading.fxml";
    public static final String HELLO="hello-view.fxml";
    public static final String WAITING_VIEW="waiting.fxml";
    public static void loadView( String src) {
        FXMLLoader fxmlLoader = new FXMLLoader(WelcomeController.class.getResource("/fxml/"+src));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Main.window.setTitle("Anonymous Chat");
        Main.window.setScene(scene);
        Main.window.show();
    }
}
