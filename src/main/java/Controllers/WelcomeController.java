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
                    if (message.contains("is used")) {
                        String finalMessage = message;
                        Platform.runLater(() -> {
                            StackPane.setStyle("visibility: false;");
                            VBox.setStyle("-fx-opacity: 1;");
                            AlertUtils.alert(finalMessage);
                        });
                        continue;
                    }

                    if (StatusCode.isChatCode(message)) {
                        Platform.runLater(() -> {
                            ViewUtils.loadView(ViewUtils.CHAT_VIEW);
                        });
                        return;
                    }

                    if(StatusCode.isRefuseCode(message)){
                        Platform.runLater(() -> {
                            AlertUtils.alert(Main.partnerName +" dont want to chat with you");
                        });
                        continue;
                    }

                    if (!message.contains("is connecting")) {
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
        try {
//            StackPane.setStyle("visibility: true");
//            VBox.setStyle("-fx-opacity: 0.2");
            StackPane.setStyle("visibility: true");
            VBox.setStyle("-fx-opacity: 0.2");
            String nickname = inputName.getText();
            SendThread.send(Main.socket, nickname);

//            if (ReceiveThread.receive(socket).contains("is used")) {
//                AlertUtils.alert(nickname + " is used");
//            } else {
//                StackPane.setStyle("visibility: true");
//                VBox.setStyle("-fx-opacity: 0.2");
////                showLoading();
//                System.out.println("truowc");
//                String message = ReceiveThread.receive(socket);
//                System.out.println("giua");
//
////            while (true) {
////                System.out.println("You want to chat with " + message);
//                if (AlertUtils.question("You want to chat with " + message)) {
//                    AlertUtils.alert("accept");
//                } else {
//                    AlertUtils.alert("denied");
//                }
//                System.out.println("truowc");

//            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }

    }

    void showLoading() {
        StackPane.setStyle("visibility: true");
        VBox.setStyle("-fx-opacity: 0.2");
    }

    @FXML
    void onEnterPress(KeyEvent event) {
//        try {
        if (event.getCode().equals(KeyCode.ENTER)) {
            onStartClick();
//                new MainThread().start();
//                Platform.runLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        StackPane.setStyle("visibility: false");//tắt loading
//                        VBox.setStyle("-fx-opacity: 1");//tắt loading
//                        if (ReceiveThread.receive(socket).contains("is used")) {// Tên đã tồn tại
//                            AlertUtils.alert(nickname + " is used");
//                        } else {
//                            String message = ReceiveThread.receive(socket);
//                            if (AlertUtils.question("You want to chat with " + message)) {
//                                AlertUtils.alert("accept");
//                            } else {
//                                AlertUtils.alert("denied");
//                            }
//                        }
//                    }
//                });
        }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    void findPartner() {
        try {
            StackPane.setStyle("visibility: true");
            VBox.setStyle("-fx-opacity: 0.2");
            String message = ReceiveThread.receive(Main.socket);
//            while (true) {
//                System.out.println("You want to chat with " + message);
            if (AlertUtils.question("You want to chat with " + message)) {
                AlertUtils.alert("accept");
            } else {
                AlertUtils.alert("denied");
            }

//                SendThread.send(socket, console.getInput("Your answer"));
//                message = ReceiveThread.receive(socket);
//                if (message.contains("Connecting")) {
//                    System.out.println(message);
//                    SendThread sendThread = new SendThread(socket);
//                    Thread send = new Thread(sendThread);
//                    send.start();
//
//                    ReceiveThread receiveThread = new ReceiveThread(socket);
//                    Thread receive = new Thread(receiveThread);
//                    receive.start();
//                    break;
//                }
//            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
