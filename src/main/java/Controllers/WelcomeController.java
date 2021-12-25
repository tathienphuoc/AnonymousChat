package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class WelcomeController {

    @FXML
    private TextField inputName;

    @FXML
    private Button startButton;

    @FXML
    void onStartClick(ActionEvent event) {
        System.out.println("Your nickname is: "+ inputName.getText());
    }

    @FXML
    void onEnterClick(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)){
            System.out.println("nahn enter nene hien ten" + inputName.getText());
        }
    }

}
