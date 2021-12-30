package Controllers;

import Utils.ViewUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class MainController {

    @FXML
    private Button findButton;

    @FXML
    private Text nickname;

    @FXML
    void onFindClick(ActionEvent event) {
        ViewUtils.loadView(ViewUtils.MAIN_LOADING_VIEW);
    }

}
