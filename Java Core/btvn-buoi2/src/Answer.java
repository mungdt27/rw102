public class Answer {
    int id;
    String content;
    Question question;
    boolean isCorrect;

    // Constructor
    public Answer(int id, String content, Question question, boolean isCorrect) {
        this.id = id;
        this.content = content;
        this.question = question;
        this.isCorrect = isCorrect;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    // toString method
    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", question=" + question +
                ", isCorrect=" + isCorrect +
                '}';
    }
}