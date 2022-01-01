package Utils;

import Controllers.WelcomeController;
import Main.Main;
import Socket.SendThread;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.Socket;

public class ViewUtils {
    public static final String WElCOME_VIEW = "welcome.fxml";
    public static final String CHAT_VIEW = "chat.fxml";

    public static void loadView(String src) {
        FXMLLoader fxmlLoader = new FXMLLoader(WelcomeController.class.getResource("/fxml/" + src));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Main.window.setOnCloseRequest(event -> {
            event.consume();
            Platform.runLater(() -> {
                        if (AlertUtils.question("Are you sure want to exit")) {
                            try {
                                SendThread.send(Main.socket, StatusCode.EXIT_CODE);
                                Main.socket = new Socket(Main.host, Main.port);
                                ViewUtils.loadView(ViewUtils.WElCOME_VIEW);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            javafx.application.Platform.exit();
                        }
                    }
            );
        });

        Main.window.setTitle("Anonymous Chat");
        Main.window.setScene(scene);
        Main.window.show();
    }
}
