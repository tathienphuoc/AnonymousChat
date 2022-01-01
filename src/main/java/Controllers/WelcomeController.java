package Controllers;

import Main.Main;
import Utils.AlertUtils;
import Utils.StatusCode;
import Utils.ViewUtils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import Socket.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

public class WelcomeController implements Initializable {
    String path = "/fxml/chat.fxml";
    @FXML
    private TextField inputName;
    @FXML
    private ImageView imageView;

    @FXML
    private StackPane StackPane;

    @FXML
    private VBox VBox;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        StackPane.setStyle("visibility: true");
//        VBox.setStyle("-fx-opacity: 0.2");

        Thread listen = new Thread(() -> {
            while (true) {
                try {
                    String message = ReceiveThread.receive(Main.socket);
                    System.out.println(message);
                    if (StatusCode.isNameIsUsedCode(message)) {
                        Platform.runLater(() -> {
//                            StackPane.setStyle("visibility: false;");
//                            VBox.setStyle("-fx-opacity: 1;");
                            disableLoading();
                            AlertUtils.alert(inputName.getText() + " is used");
                        });
                        continue;
                    }

                    if (StatusCode.isChatCode(message)) {
                        Platform.runLater(() -> {
                            ViewUtils.loadView(ViewUtils.CHAT_VIEW);
                        });
                        return;
                    }

                    if (StatusCode.isRefuseCode(message)) {
                        Platform.runLater(() -> {
                            AlertUtils.alert(Main.partnerName + " dont want to chat with you");
                        });
                        continue;
                    }

                    if (!StatusCode.isConnectCode(message)) {
                        String finalMessage1 = message;
                        Main.partnerName = message;
                        AtomicBoolean isAccept = new AtomicBoolean(false);
                        Platform.runLater(() -> {
                            isAccept.set(AlertUtils.question("You want to chat with " + finalMessage1));

                            System.out.println("Is accept: " + isAccept.get());

                            try {
                                SendThread.send(Main.socket, isAccept.get() ? "y" : "n");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    }
                } catch (IOException e) {

                }
            }
        });
        listen.start();
        try {
            imageView.setImage(new Image(new FileInputStream("src/main/resources/images/load.gif")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onStartClick() {
        String nickname = inputName.getText();
        if (nickname.isBlank()) {
            Platform.runLater(() -> {
                AlertUtils.alert("Please enter your nickname");
            });
        } else {
            try {
//                StackPane.setStyle("visibility: true");
//                VBox.setStyle("-fx-opacity: 0.2");
                enableLoading();
                SendThread.send(Main.socket, nickname);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void enableLoading() {
        StackPane.setStyle("visibility: true");
        VBox.setStyle("-fx-opacity: 0.2");
    }

    void disableLoading() {
        StackPane.setStyle("visibility: false");
        VBox.setStyle("-fx-opacity: 1");
    }

    @FXML
    void onEnterPress(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            onStartClick();
        }
    }

}
