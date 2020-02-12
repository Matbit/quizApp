package application.repository;

import application.model.QuizInsertModel;
import application.model.QuizReadModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuizRepository {
    private static final String DB_NAME = "quizDB.sqlite";
    private static final String CON = "jdbc:sqlite:" + DB_NAME;

    private static final String TABLE_QUIZ = "quiz";
    private static final String COLUMN_QUIZ = "question";
    private static final String COLUMN_ANSWER = "answer";
    private static final String COLUMN_ARCHIVED = "archived";
    private static final String COLUMN_INFORMATION = "information";

    private static final String ADD_ROW = "INSERT INTO " + TABLE_QUIZ + "(" + COLUMN_QUIZ + ", " + COLUMN_ANSWER + ", " + COLUMN_ARCHIVED + ", "+COLUMN_INFORMATION+") VALUES (?,?,?,?);";

    private static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(CON);
        return con;
    }
    public boolean isConnectionOK() {
        try {
            Connection con = getConnection();
            con.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean createQuiz() {
        try {
            Connection con = getConnection();
            String sql = "CREATE Table IF NOT EXISTS " + TABLE_QUIZ + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_QUIZ + " TEXT," + COLUMN_ANSWER + " TEXT, "
                    + COLUMN_ARCHIVED + " TEXT, "+COLUMN_INFORMATION+" TEXT);";
            PreparedStatement cmd = con.prepareStatement(sql);
            cmd.execute();
            con.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertNewQuizEntry(QuizInsertModel model) {
        try {
            Connection con = getConnection();
            PreparedStatement cmd = con.prepareStatement(ADD_ROW);
            cmd.setString(1, model.getQuestion());
            cmd.setString(2, model.getAnswer());
            cmd.setString(3, model.getArchived());
            cmd.setString(4,model.getInformation());
            cmd.executeUpdate();
            con.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<QuizReadModel> findAllQuestions(){
        List<QuizReadModel> list = new ArrayList();
        try{
            Connection con = getConnection();
            PreparedStatement cmd = con.prepareStatement("SELECT * FROM "+ TABLE_QUIZ+";");
            ResultSet rs = cmd.executeQuery();

            while(rs.next()){
                QuizReadModel model = new QuizReadModel();
                model.setId(rs.getInt("id"));
                model.setQuestion(rs.getString(COLUMN_QUIZ));
                model.setAnswer(rs.getString(COLUMN_ANSWER));
                model.setArchived(rs.getString(COLUMN_ARCHIVED));
                model.setInformation(rs.getString(COLUMN_INFORMATION));
                list.add(model);
            }
            con.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean updateEntryByID(QuizReadModel model){
        try{
            Connection con = getConnection();
            String sql = "UPDATE " + TABLE_QUIZ + " SET " + COLUMN_QUIZ+ " = '" + model.getQuestion()+"', " + COLUMN_ANSWER+" = '"
                    + model.getAnswer()+"', " + COLUMN_ARCHIVED+" = '" + model.getArchived()+"', "+ COLUMN_INFORMATION+" = '"+ model.getInformation()
                    +"' WHERE id = '" + model.getId() +"' ";
            PreparedStatement cmd = con.prepareStatement(sql);
            cmd.executeUpdate();
            con.close();
            return true;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteEntry(QuizReadModel model){
        try{
            Connection con = getConnection();
            String sql = "DELETE FROM " + TABLE_QUIZ + " WHERE id = '" + model.getId()+"';";
            PreparedStatement cmd = con.prepareStatement(sql);
            cmd.execute();
            con.close();
            return true;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
