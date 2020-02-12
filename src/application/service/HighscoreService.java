package application.service;

import application.database.HighscoreData;
import application.model.HighscoreInsertModel;
import application.model.HighscoreReadModel;
import application.repository.HighscoreRepository;
import java.util.List;

public class HighscoreService {
    private static HighscoreRepository repository = new HighscoreRepository();

    public static boolean createHighscore(){
        return repository.createHighscore();
    }

    public static boolean insertNewHighscoreEntry(HighscoreInsertModel model){
        return repository.insertNewHighscoreEntry(model);
    }

    public static List<HighscoreReadModel> findHighscore(){
        return repository.findHighscore();
    }

    public static List<HighscoreReadModel> findTopTenHighscore(){
        return repository.findTopTenHighscore();
    }

    public static boolean deleteHighscore(){
        return repository.deleteHighscore();
    }

    public static boolean loadData(){
        List<HighscoreReadModel> highscoreEntries = repository.findTopTenHighscore();
        int size = highscoreEntries.size();
        if(size<1){
            HighscoreData data = new HighscoreData();
            List<HighscoreInsertModel> list = data.getList();
            for(HighscoreInsertModel model : list){
                repository.insertNewHighscoreEntry(model);
            }
            return true;
        }
        return false;
    }
}
