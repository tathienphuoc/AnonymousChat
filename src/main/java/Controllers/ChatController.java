package Controllers;

import Main.Main;
import Socket.ReceiveThread;
import Socket.SendThread;
import Utils.AlertUtils;
import Utils.StatusCode;
import Utils.ViewUtils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ChatController implements Initializable {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private ImageView exitButton;

    @FXML
    private TextField input;

    @FXML
    private TextField partnerName;

    @FXML
    private VBox vbox;


    @FXML
    void onExitClick(MouseEvent event) {
        backToWelcomeScene();
    }

    private void backToWelcomeScene() {
        Platform.runLater(() -> {
            if (AlertUtils.question("Are you sure want to exit")) {
                try {
                    SendThread.send(Main.socket, StatusCode.EXIT_CODE);
                    Main.socket = new Socket(Main.host, Main.port);
                    ViewUtils.loadView(ViewUtils.WElCOME_VIEW);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @FXML
    void onSendClick() {
        if (!input.getText().isBlank()) {
            try {
                if (StatusCode.isExitCode(input.getText())) {
                    backToWelcomeScene();
                } else {
                    SendThread.send(Main.socket, input.getText());
                    displayMessage(input.getText(), true);
                }
                input.setText("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void onEnterPress(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            onSendClick();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.partnerName.setText("Chatting with " + Main.partnerName);
        try {
            exitButton.setImage(new Image(new FileInputStream("src/main/resources/images/x-icon.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Thread listen = new Thread(() -> {
            try {
                while (true) {
                    String message = ReceiveThread.receive(Main.socket);
                    if (StatusCode.isExitCode(message)) {
                        Platform.runLater(() -> {
                            System.out.println(message);
                            AlertUtils.alert(Main.partnerName + " is out the chat");
                            try {
                                Main.socket = new Socket(Main.host, Main.port);
                                ViewUtils.loadView(ViewUtils.WElCOME_VIEW);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        });
                    } else {
                        Platform.runLater(() -> {
                            displayMessage(message, false);
                        });
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        listen.start();
    }

    public void displayMessage(String message, boolean isSender) {
        HBox hBox = new HBox();
        hBox.setAlignment(isSender ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);
        hBox.getStyleClass().add("chat-container");

        TextFlow textFlow = new TextFlow();
        textFlow.getStyleClass().add(isSender ? "send" : "receive");

        Text text = new Text(message);
        text.setFill(Color.WHITE);

        textFlow.getChildren().add(text);
        hBox.getChildren().add(textFlow);
        vbox.getChildren().add(hBox);
        scrollPane.setVvalue(1.0);
    }
}
