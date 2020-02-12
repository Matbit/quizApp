package application.service;

import application.database.MockData;
import application.model.QuizInsertModel;
import application.model.QuizReadModel;
import application.repository.QuizRepository;

import java.util.List;

public class QuizService {
    private static QuizRepository repository = new QuizRepository();
    private static final int MARKER = 30;

    public static boolean checkConnection(){
        return repository.isConnectionOK();
    }

    public static boolean createQuiz(){
        return repository.createQuiz();
    }

    public static boolean insertNewQuizEntry(QuizInsertModel model){
        return repository.insertNewQuizEntry(model);
    }

    public static List<QuizReadModel> findAllQuestions(){
        return repository.findAllQuestions();
    }

    public static boolean updateEntryByID(QuizReadModel model){
        return repository.updateEntryByID(model);
    }

    public static boolean deleteEntry(QuizReadModel model) { return repository.deleteEntry(model); }

    /**
     * If the database has less entries than the marker, this method load new mock data into the database
     * @return true or false
     */
    public static boolean loadData(){
        List<QuizReadModel> list = repository.findAllQuestions();
        int size = list.size();
        if(size < MARKER){
            MockData mockData = new MockData();
            List<QuizInsertModel> quizList = mockData.getList();
            for(QuizInsertModel model : quizList){
                insertNewQuizEntry(model);
            }
            return true;
        }
        return false;
    }
}
