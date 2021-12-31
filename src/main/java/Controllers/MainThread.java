package Controllers;

import Main.Main;
import Socket.ReceiveThread;
import Utils.AlertUtils;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MainThread extends Thread {
    @Override
    public void run() {
        try {
            String message = ReceiveThread.receive(Main.socket);
//            if (AlertUtils.question("You want to chat with " + message)) {
//                AlertUtils.alert("accept");
//            } else {
//                AlertUtils.alert("denied");
//            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
