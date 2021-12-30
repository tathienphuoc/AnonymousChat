package Utils;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AlertUtils {
    public static void alert(String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        DialogPane dialogPane=alert.getDialogPane();
        Button okButton = (Button) dialogPane.lookupButton(alert.getButtonTypes().get(0));
        dialogPane.setStyle("-fx-background-color: white");
        okButton.setStyle("-fx-background-color: #4294ff;-fx-cursor: hand;-fx-text-fill: white");
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
