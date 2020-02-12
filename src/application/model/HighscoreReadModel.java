package application.model;

import java.util.Objects;

public class HighscoreReadModel {
    private int id;
    private String nickname;
    private String points;
    private String time;

    //Default constructor
    public HighscoreReadModel(){}

    //Custom constructor
    public HighscoreReadModel(int id, String nickname, String points, String time) {
        this.id = id;
        this.nickname = nickname;
        this.points = points;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "HighscoreReadModel{" +
                "id=" + id +
                ", nickaname='" + nickname + '\'' +
                ", points='" + points + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HighscoreReadModel that = (HighscoreReadModel) o;
        return id == that.id &&
                Objects.equals(nickname, that.nickname) &&
                Objects.equals(points, that.points) &&
                Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickname, points, time);
    }
}
