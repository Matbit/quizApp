package application;

import application.model.QuizInsertModel;
import application.model.QuizReadModel;
import application.service.QuizService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import java.util.ResourceBundle;

public class GuiEditQuizController implements Initializable {

    public static List<QuizReadModel> listAll = new ArrayList();
    private ToggleGroup toggleGroup = new ToggleGroup();

    public GuiEditQuizController(){
    }
    private final int MAX_LENGTH_TEXTFIELDS = 90;

    @FXML
    private TableView<QuizReadModel> tblView = new TableView<QuizReadModel>();

    @FXML
    private TableColumn<QuizReadModel, String> colQuestion;

    @FXML
    private TableColumn<QuizReadModel, String> colInformation;

    @FXML
    private Button btnArchiveEntry;

    @FXML
    private TableView<QuizReadModel> tblArchive = new TableView<>();

    @FXML
    private TableColumn<QuizReadModel, String> colQuestionArchive;

    @FXML
    private TableColumn<QuizReadModel, String> colAnswerArchive;

    @FXML
    private Button btnActivate;

    @FXML
    private Button btnDelete;

    @FXML
    private TextField tbxQuestion;

    @FXML
    private RadioButton rbtnYES;

    @FXML
    private RadioButton rbtnNO;

    @FXML
    private TextField tbxInformation;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnAddData;

    @FXML
    private ImageView imageBack;

    @FXML
    void mouseClicked(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GuiStart.fxml"));
        Stage stage = (Stage) imageBack.getScene().getWindow();

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    private ObservableList<QuizReadModel> getList(){
        listAll = QuizService.findAllQuestions();
        ObservableList<QuizReadModel> observableList = FXCollections.observableArrayList();

        for(QuizReadModel model : listAll){
            observableList.add(model);
        }
        return observableList;
    }

    private void clearinputForm(){
        tbxQuestion.clear();
        tbxInformation.clear();
        rbtnYES.setSelected(false);
        rbtnNO.setSelected(false);
    }

    @FXML
    void buttonClicked(ActionEvent event) {
        Button button = new Button();
        button = (Button) event.getSource();

        //archive selected item
        if(button.getId().equals(this.btnArchiveEntry.getId())){
            QuizReadModel model = tblView.getSelectionModel().getSelectedItem();
                if(model != null){
                    model.setArchived("true");
                    QuizService.updateEntryByID(model);
                    tblView.getItems().removeAll(tblView.getSelectionModel().getSelectedItem());
                    loadData();
            }
        }
        //activate selected item
        if(button.getId().equals(this.btnActivate.getId())){
            QuizReadModel model = tblArchive.getSelectionModel().getSelectedItem();
            if(model != null){
                model.setArchived("false");
                QuizService.updateEntryByID(model);
                tblArchive.getItems().removeAll(tblArchive.getSelectionModel().getSelectedItem());
                loadData();
            }
        }
        //clear data from input form
        if(button.getId().equals(this.btnClear.getId())) {
            clearinputForm();
        }
        //add data
        if(button.getId().equals(this.btnAddData.getId())){
            if(rbtnNO.isSelected() || rbtnYES.isSelected()){
                QuizInsertModel model = new QuizInsertModel();
                if(model != null) {
                    if (tbxQuestion.getText().length() < 5) {
                        return;
                    }
                    model.setQuestion(tbxQuestion.getText());
                    if (rbtnYES.isSelected()) {
                        model.setAnswer("true");
                    } else if (rbtnNO.isSelected()) {
                        model.setAnswer("false");
                    }

                    if (tbxInformation.getText().length() < 3) {
                        return;
                    }
                    model.setInformation(tbxInformation.getText());
                    model.setArchived("false");
                    //add to database
                    try {
                        QuizService.insertNewQuizEntry(model);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information");
                        alert.setHeaderText("Erfolgreich hinzugefügt!");
                        alert.setContentText("Datensatz wurde zum Fragenpool hinzugefügt.");
                        alert.showAndWait();
                        clearinputForm();
                        loadData();
                    } catch (Exception e) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Ooops");
                        alert.setHeaderText("Leider hat das nicht geklappt.");
                        alert.showAndWait();
                    }
                }
            }
        }
        //delete data
        if(button.getId().equals(this.btnDelete.getId())){
            QuizReadModel model = tblArchive.getSelectionModel().getSelectedItem();
            try{
                QuizService.deleteEntry(model);
                loadData();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Erfolgreich gelöscht.");
                alert.setContentText("Datensatz wurde endgültig aus der Datenbank entfernt.");
                alert.showAndWait();
            }
            catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ooops");
                alert.setHeaderText("Leider hat das nicht geklappt.");
                alert.showAndWait();
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        loadData();
        rbtnYES.setToggleGroup(toggleGroup);
        rbtnNO.setToggleGroup(toggleGroup);

        addTextLimiter(tbxQuestion, MAX_LENGTH_TEXTFIELDS);
        addTextLimiter(tbxInformation, MAX_LENGTH_TEXTFIELDS);
    }

    public void loadData(){
        //settings for tblView
        tblView.setEditable(false);
        colQuestion.setCellValueFactory(new PropertyValueFactory<QuizReadModel,String>("question"));
        colInformation.setCellValueFactory(new PropertyValueFactory<QuizReadModel, String>("information"));

        tblView.setItems(getList());
        List<QuizReadModel> removeList = new ArrayList();
        for(QuizReadModel model : listAll){
            if(model.getArchived().equals("true")){
                removeList.add(model);
            }
        }
        tblView.getItems().removeAll(removeList);

        //tblArchive
        tblArchive.setEditable(false);
        colQuestionArchive.setCellValueFactory(new PropertyValueFactory<QuizReadModel, String>("question"));
        colAnswerArchive.setCellValueFactory(new PropertyValueFactory<QuizReadModel, String>("information"));

        tblArchive.setItems(getList());
        List<QuizReadModel> removeActivatedItemsList = new ArrayList();
        for(QuizReadModel model : listAll){
            if(model.getArchived().equals("false")){
                removeActivatedItemsList.add(model);
            }
        }
        tblArchive.getItems().removeAll(removeActivatedItemsList);
    }

    public static void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tf.getText().length() > maxLength) {
                    String s = tf.getText().substring(0, maxLength);
                    tf.setText(s);
                }
            }
        });
    }


}
