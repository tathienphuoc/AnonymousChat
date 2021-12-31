package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainLoadingController implements Initializable {

    @FXML
    private Button cancelButton;

    @FXML
    private Button findButton;

    @FXML
    private ImageView imageView;

    @FXML
    private Text nickname;

    @FXML
    private StackPane StackPane;

    @FXML
    private javafx.scene.layout.VBox VBox;

    @FXML
    void onFindClick(ActionEvent event) {
        StackPane.setStyle("visibility: true");
        VBox.setStyle("-fx-opacity: 0.2");
    }

    @FXML
    void onCancelClick(MouseEvent event) {
        StackPane.setStyle("visibility: false");
        VBox.setStyle("-fx-opacity: 1");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Setting the image view
        try {
            imageView.setImage(new Image(new FileInputStream("src/main/resources/images/load.gif")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
