package Utils;

import Main.Main;
import javafx.scene.control.*;

import java.util.Optional;

public class AlertUtils {
    public static void alert(String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(Main.window);
        DialogPane dialogPane = alert.getDialogPane();
        Button okButton = (Button) dialogPane.lookupButton(alert.getButtonTypes().get(0));
        dialogPane.setStyle("-fx-background-color: white");
        okButton.setStyle("-fx-background-color: #4294ff;-fx-cursor: hand;-fx-text-fill: white");
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static boolean question(String content) {
        ButtonType accept = new ButtonType("Accept", ButtonBar.ButtonData.OK_DONE);
        ButtonType refuse = new ButtonType("Refuse", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, content, accept, refuse);
        alert.initOwner(Main.window);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-background-color: white");

        Button acceptButton = (Button) dialogPane.lookupButton(alert.getButtonTypes().get(0));
        acceptButton.setStyle("-fx-background-color: #4294ff;-fx-cursor: hand;-fx-text-fill: white");

        Button refuseButton = (Button) dialogPane.lookupButton(alert.getButtonTypes().get(1));
        refuseButton.setStyle("-fx-background-color: red;-fx-cursor: hand;-fx-text-fill: white");

        alert.setTitle("Question");
        alert.setHeaderText(null);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent()) {
            if (result.get().equals(accept)) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }
}
