package Controllers;

import Socket.ReceiveThread;
import Utils.AlertUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WaitingController implements Initializable {

    @FXML
    private StackPane StackPane;

    @FXML
    private VBox VBox;

    @FXML
    private ImageView imageView;

    @FXML
    private TextField inputName;

    @FXML
    void onEnterPress(KeyEvent event) {

    }

    @FXML
    void onStartClick(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        try {
////            String message = ReceiveThread.receive(WelcomeController.socket);
//            if (AlertUtils.question("You want to chat with " + message)) {
//                AlertUtils.alert("accept");
//            } else {
//                AlertUtils.alert("denied");
//            }
//        }catch (IOException e){
//            e.printStackTrace();
//        }
    }

    void findPartner() {
        try {
//            StackPane.setStyle("visibility: true");
//            VBox.setStyle("-fx-opacity: 0.2");
//            String message = ReceiveThread.receive(socket);
//            while (true) {
//                System.out.println("You want to chat with " + message);
//            if (AlertUtils.question("You want to chat with " + message)) {
//                AlertUtils.alert("accept");
//            } else {
//                AlertUtils.alert("denied");
//            }

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
