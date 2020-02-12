package application.repository;

import application.model.HighscoreInsertModel;
import application.model.HighscoreReadModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HighscoreRepository {
    private static final String DB_NAME = "quizDB.sqlite";
    private static final String CON = "jdbc:sqlite:" + DB_NAME;

    private static final String TABLE_HIGHSCORE = "highscore";
    private static final String COLUMN_NICKNAME = "nickname";
    private static final String COLUMN_POINTS = "points";
    private static final String COLUMN_TIME = "time";


    private static final String ADD_ROW = "INSERT INTO " + TABLE_HIGHSCORE + "(" + COLUMN_NICKNAME + ", " + COLUMN_POINTS + ", " + COLUMN_TIME + ") VALUES (?,?,?);";


    private static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(CON);
        return con;
    }

    public boolean createHighscore() {
        try {
            Connection con = getConnection();
            String sql = "CREATE Table IF NOT EXISTS " + TABLE_HIGHSCORE + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NICKNAME + " TEXT," + COLUMN_POINTS + " INTEGER, "
                    + COLUMN_TIME + " NUMERIC);";
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

    public boolean insertNewHighscoreEntry(HighscoreInsertModel model) {
        try {
            Connection con = getConnection();
            PreparedStatement cmd = con.prepareStatement(ADD_ROW);
            cmd.setString(1, model.getNickname());
            cmd.setString(2, model.getPoints());
            cmd.setString(3, model.getTime());
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

    public List<HighscoreReadModel> findHighscore() {
        List<HighscoreReadModel> list = new ArrayList();

        try {
            Connection con = getConnection();
            PreparedStatement cmd = con.prepareStatement("SELECT * FROM " + TABLE_HIGHSCORE + ";");
            ResultSet rs = cmd.executeQuery();

            while (rs.next()) {
                HighscoreReadModel model = new HighscoreReadModel();
                model.setId(rs.getInt("id"));
                model.setNickname(rs.getString(COLUMN_NICKNAME));
                model.setPoints(rs.getString(COLUMN_POINTS));
                model.setTime(rs.getString(COLUMN_TIME));
                list.add(model);
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<HighscoreReadModel> findTopTenHighscore(){
        List<HighscoreReadModel> list = new ArrayList();

        try {
            Connection con = getConnection();
            PreparedStatement cmd = con.prepareStatement("SELECT * FROM " + TABLE_HIGHSCORE + " order by " + COLUMN_POINTS + " desc, " + COLUMN_TIME + " asc limit 10;");
            ResultSet rs = cmd.executeQuery();

            while (rs.next()) {
                HighscoreReadModel model = new HighscoreReadModel();
                model.setId(rs.getInt("id"));
                model.setNickname(rs.getString(COLUMN_NICKNAME));
                model.setPoints(rs.getString(COLUMN_POINTS));
                model.setTime(rs.getString(COLUMN_TIME));
                list.add(model);
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public boolean deleteHighscore(){
        try{
            Connection con = getConnection();
            String sql = "DELETE FROM " + TABLE_HIGHSCORE  + " WHERE id > 0;";
            PreparedStatement cmd = con.prepareStatement(sql);
            cmd.execute();
            con.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
