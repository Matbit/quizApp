package application;

import application.service.HighscoreService;
import application.service.QuizService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GuiStartController {

    public GuiStartController() {
        initDB();
    }

    @FXML
    private Button btnStartQuiz;

    @FXML
    private Button btnEditQuestions;

    @FXML
    private Button btnAchievement;

    @FXML
    private Button btnCredits;

    @FXML
    private Button btnExit;

    @FXML
    void buttonClicked(ActionEvent event) throws Exception {
        Button button = new Button();
        button = (Button) event.getSource();

        if(button.getId().equals(this.btnExit.getId())){
            System.exit(0);
        }
        else if(button.getId().equals(this.btnStartQuiz.getId())){
            Parent root = FXMLLoader.load(getClass().getResource("GuiQuiz.fxml"));
            Stage stage = (Stage) btnStartQuiz.getScene().getWindow();

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        }
        else if(button.getId().equals(this.btnEditQuestions.getId())){
            Parent root = FXMLLoader.load(getClass().getResource("GuiEditQuiz.fxml"));
            Stage stage = (Stage) btnEditQuestions.getScene().getWindow();

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        }
        else if(button.getId().equals(this.btnAchievement.getId())){
            Parent root = FXMLLoader.load(getClass().getResource("GuiHighscore.fxml"));
            Stage stage = (Stage) btnStartQuiz.getScene().getWindow();

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        }
        else if(button.getId().equals(this.btnCredits.getId())){
            Parent root = FXMLLoader.load(getClass().getResource("Credits.fxml"));
            Stage stage = (Stage) btnStartQuiz.getScene().getWindow();

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        }
    }

    //create table if not exists
    private void initDB(){
        QuizService.checkConnection();
        QuizService.createQuiz();
        QuizService.loadData();

        HighscoreService.createHighscore();
        HighscoreService.loadData();
    }

}
