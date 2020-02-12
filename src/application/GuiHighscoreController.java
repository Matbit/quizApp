package application;

import application.model.HighscoreReadModel;
import application.service.HighscoreService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class GuiHighscoreController implements Initializable {

    @FXML
    private TableView<HighscoreReadModel> tblHighscore;

    @FXML
    private TableColumn<HighscoreReadModel, String> colName;

    @FXML
    private TableColumn<HighscoreReadModel, String> colPoints;

    @FXML
    private TableColumn<HighscoreReadModel, String> colTime;

    @FXML
    private Button btnClearHighscore;

    @FXML
    private ImageView imageBack;

    private List<HighscoreReadModel> list = new ArrayList();

    @FXML
    void imageClicked(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GuiStart.fxml"));
        Stage stage = (Stage) imageBack.getScene().getWindow();

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    private void loadData(){
        list = HighscoreService.findTopTenHighscore();
        if(list.size() < 1){
            btnClearHighscore.setDisable(true);
        }
        ObservableList obList = FXCollections.observableArrayList();
        for(HighscoreReadModel model : list){
            obList.add(model);
        }
        tblHighscore.setEditable(false);
        colName.setCellValueFactory(new PropertyValueFactory<HighscoreReadModel, String>("nickname"));
        colPoints.setCellValueFactory(new PropertyValueFactory<>("points"));
        colTime.setCellValueFactory(new PropertyValueFactory<HighscoreReadModel, String>("time"));
        tblHighscore.setItems(obList);
    }

    @FXML
    void buttonClicked(ActionEvent event) throws IOException {
        Button button;
        button = (Button) event.getSource();

        if(button.getId().equals(btnClearHighscore.getId())){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Bist du sicher?");
            alert.setHeaderText(null);
            alert.setContentText("Möchtest du wirklich alle Einträge endgültig löschen?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                HighscoreService.deleteHighscore();
                loadData();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
    }
}
