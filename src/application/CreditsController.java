package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;

public class CreditsController {
    @FXML
    private ImageView btnBack;

    @FXML
    void btnClicked(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GuiStart.fxml"));
        Stage stage = (Stage) btnBack.getScene().getWindow();

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}


