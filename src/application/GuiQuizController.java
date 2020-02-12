package application;

import application.model.HighscoreInsertModel;
import application.model.HighscoreReadModel;
import application.model.QuizReadModel;
import application.service.HighscoreService;
import application.service.QuizService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class GuiQuizController implements Initializable {

    @FXML
    private Button btnYES;

    @FXML
    private Button btnNO;

    @FXML
    private Label lInformation;

    @FXML
    private ImageView lCancel;

    @FXML
    private Label lQuestionField;

    @FXML
    private Label lCount;

    @FXML
    private Label lScore;

    private static int count = 0;
    private static int score = 0;
    private static List<QuizReadModel> questionList = new ArrayList();
    private static List<QuizReadModel> list = new ArrayList();
    private Instant start;
    private Instant stopp;
    private double time;
    public static String name = "";

    public GuiQuizController(){
    }


    private void sortQuestions(){
        Iterator<QuizReadModel> iterator = questionList.iterator();
        while(iterator.hasNext()){
            QuizReadModel model = iterator.next();
            if(model.getArchived().equals("true")){
                iterator.remove();
            }
        }
        int maxRandomValue = questionList.size();
        boolean hasListMaximumReached = false;
        while(!hasListMaximumReached){
            int random = (int) (Math.random() * maxRandomValue);
            if(random <= maxRandomValue && random > -1){
                QuizReadModel modelTip;
                modelTip = questionList.get(random);
                if(!list.contains(modelTip)){
                    list.add(modelTip);
                    if(list.size() == 10){
                        hasListMaximumReached = true;
                    }
                }
            }
        }
    }

    private void calcTime(){
        Duration diff = Duration.between(start, stopp);
        time = diff.getSeconds() + diff.getNano() / 1e9;
    }

    private boolean reachedTopTenResult(){
        List<HighscoreReadModel> highscoreList = HighscoreService.findTopTenHighscore();
        int lowestPoints = 100;

        for(HighscoreReadModel model : highscoreList){
            int points = Integer.parseInt(model.getPoints());
            if(points < lowestPoints){
                lowestPoints = points;
            }
        }
        if(score > lowestPoints){
            return true;
        }
        else if(score < lowestPoints) {
            return false;
        }
        for(HighscoreReadModel model : highscoreList){
            double timeAsDouble = Double.parseDouble(model.getTime());
            int pointsAsInteger = Integer.parseInt(model.getPoints());
            if(timeAsDouble > time && pointsAsInteger == lowestPoints){
                return true;
            }
        }
        return false;
    }

    private void finishedRound() throws IOException {
        stopp = Instant.now();
        btnNO.setDisable(true);
        btnYES.setDisable(true);
        //reached highscore?
        calcTime();
        //top ten result?
        if(reachedTopTenResult()){
            //aks user if he wants to save his highscore result
            Alert question = new Alert(Alert.AlertType.CONFIRMATION);
            question.setTitle("Herzlichen Glückwunsch");
            question.setHeaderText(null);
            question.setContentText("Du hast es in die TOP 10 der Highscore-Liste geschafft. Möchtest du dich dort eintragen?");

            Optional<ButtonType> result1 = question.showAndWait();
            if (result1.get() == ButtonType.OK) {

                Parent root = FXMLLoader.load(getClass().getResource("GuiEnterNickname.fxml"));
                Stage nicknameStage = new Stage();
                Scene scene = new Scene(root);
                scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
                nicknameStage.setScene(scene);
                nicknameStage.showAndWait();

                HighscoreInsertModel model = new HighscoreInsertModel();
                if(name.length() < 2){
                    model.setNickname("N.A.");
                }
                else {
                    model.setNickname(name);
                }
                model.setPoints(score+"");
                model.setTime(time+"");
                HighscoreService.insertNewHighscoreEntry(model);
                name = "";
            }
        }
        //ask if user wants to play again
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Nochmal?");
        alert.setHeaderText(null);
        alert.setContentText("Möchtest du eine neue Runde versuchen?");

        count = 0;
        score = 0;
        list = new ArrayList();
        questionList = new ArrayList();

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Parent root = FXMLLoader.load(getClass().getResource("GuiQuiz.fxml"));
            Stage stage = (Stage) btnYES.getScene().getWindow();

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("GuiStart.fxml"));
            Stage stage = (Stage) btnYES.getScene().getWindow();

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        }
    }

    private void showNextQuestion() throws IOException {
        QuizReadModel model;
        if(count <10) {
            model = list.get(count);
            lQuestionField.setText(model.getQuestion());
            lCount.setText("Frage " + (count + 1) + "/10");
        }
        lScore.setText("Score: "+score);
        if(count == 10){
            finishedRound();
        }
        if(count == 0){
            start = Instant.now();
        }
    }

    @FXML
    void buttonTapped(ActionEvent event) throws IOException {
        Button button;
        button = (Button) event.getSource();

        if(count <= 9){
            QuizReadModel model = list.get(count);
            if(button.getId().equals(this.btnYES.getId())){
                if(model.getAnswer().equals("true")){
                    score += 10;
                }
            }
            if(button.getId().equals(this.btnNO.getId())){
                if(model.getAnswer().equals("false")){
                    score += 10;
                }
            }
            lInformation.setText(list.get(count).getInformation());
            count++;
            showNextQuestion();
        }
    }

    @FXML
    void labelCancelClicked(MouseEvent event) throws IOException {
        count = 0;
        score = 0;
        list = new ArrayList();
        questionList = new ArrayList();

        Parent root = FXMLLoader.load(getClass().getResource("GuiStart.fxml"));
        Stage stage = (Stage) lCancel.getScene().getWindow();

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            startNewRound();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startNewRound() throws IOException {
        //load questions from database
        loadData();
        //set questions
        sortQuestions();
        showNextQuestion();
    }

    private void loadData(){
        try{
            questionList = new ArrayList();
            questionList = QuizService.findAllQuestions();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
