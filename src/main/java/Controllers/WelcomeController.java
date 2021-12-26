package Controllers;

import Utils.AlertUtils;
import Utils.ViewUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeController {
    String path="/fxml/chat.fxml";

    @FXML
    private TextField inputName;

    @FXML
    void onStartClick(ActionEvent event) {
            ViewUtils.loadView(ViewUtils.MAIN_VIEW);
    }

    @FXML
    void onEnterPress(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)){
            AlertUtils.alert("nahn enter nene hien tennahn enter nene hien tennahn ente" + inputName.getText());
        }
    }

}
