package application.model;

public class QuizReadModel {
    private int id;
    private String question;
    private String answer;
    private String archived;
    private String information;

    //Default constructor
    public QuizReadModel(){}

    //custom constructor
    public QuizReadModel(int id, String question, String answer, String archived, String information) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.archived = archived;
        this.information = information;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getArchived() {
        return archived;
    }

    public void setArchived(String archived) {
        this.archived = archived;
    }

    public String getInformation() { return information; }

    public void setInformation(String information) { this.information = information; }
}
