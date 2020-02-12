package application.model;

public class HighscoreInsertModel {
    private String nickname;
    private String points;
    private String time;

    //Default constructor
    public HighscoreInsertModel() {
    }
    //Custom constructor
    public HighscoreInsertModel(String nickaname, String points, String time) {
        this.nickname = nickaname;
        this.points = points;
        this.time = time;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
