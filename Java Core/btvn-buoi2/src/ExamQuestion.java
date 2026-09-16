public class ExamQuestion {
    Exam exam;
    Question question;

    // Constructor
    public ExamQuestion(Exam exam, Question question) {
        this.exam = exam;
        this.question = question;
    }

    // Getters and Setters
    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) { this.exam = exam; }

    public Question getQuestion() { return question; }

    public void setQuestion(Question question) { this.question = question; }

    // toString method
    @Override
    public String toString() {
        return "ExamQuestion{" +
                "exam=" + exam +
                ", question=" + question +
                '}';
    }
}