package Controllers;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class ChatController {

    @FXML
    private ImageView backButton;

    @FXML
    private ImageView sendButton;

    @FXML
    void onBackClick(MouseEvent event) {
        System.out.println("onBackClick");
    }

    @FXML
    void onSendClick(MouseEvent event) {
        System.out.println("onSendClick");
    }

    @FXML
    void onEnterPress(KeyEvent event) {
        System.out.println("onSendPress");
    }

}
