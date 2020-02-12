package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class GuiEnterNicknameController implements Initializable {

    private static final int MAX_LENGTH = 20;

    @FXML
    private TextField tbxNickname;

    @FXML
    private Button btnGo;

    @FXML
    void buttonClicked() {
        GuiQuizController.name = tbxNickname.getText();
        Stage stage = (Stage) btnGo.getScene().getWindow();
        stage.close();
    }

    public static void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener((ov, oldValue, newValue) -> {
            if (tf.getText().length() > maxLength) {
                String s = tf.getText().substring(0, maxLength);
                tf.setText(s);
            }
        });
    }

    @FXML
    void txtfieldChangetxtfieldChange() {
        if(tbxNickname.getLength() > 2){
            btnGo.setDisable(false);
        }
        else {
            btnGo.setDisable(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addTextLimiter(tbxNickname, MAX_LENGTH);
        btnGo.setDisable(true);
    }
}

