package application.database;

import application.model.HighscoreInsertModel;
import java.util.ArrayList;
import java.util.List;

public class HighscoreData {
    private List<HighscoreInsertModel> list = new ArrayList();

    public HighscoreData(){createEntries();}

    private void createEntries(){
        list.add(new HighscoreInsertModel("Bronze", "50", "120"));
        list.add(new HighscoreInsertModel("Silber", "70", "90"));
        list.add(new HighscoreInsertModel("Gold", "90", "60"));
    }
    public List<HighscoreInsertModel> getList() {
        return list;
    }
}
