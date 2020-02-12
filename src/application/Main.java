package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public Main() {
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("GuiStart.fxml"));
        primaryStage.setTitle("QuizApp v1.0.0");
        Scene startScene = new Scene(root);
        startScene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(startScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
