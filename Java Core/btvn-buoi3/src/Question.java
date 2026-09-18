import java.time.LocalDate;

public class Question {
    int id;
    String content;
    CategoryQuestion category;
    TypeQuestion type;
    Account creator;
    LocalDate createDate;

    // Constructor
    public Question(int id, String content, CategoryQuestion category, TypeQuestion type, Account creator, LocalDate createDate) {
        this.id = id;
        this.content = content;
        this.category = category;
        this.type = type;
        this.creator = creator;
        this.createDate = createDate;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public CategoryQuestion getCategory() { return category; }
    public void setCategory(CategoryQuestion category) { this.category = category; }

    public TypeQuestion getType() { return type; }
    public void setType(TypeQuestion type) { this.type = type; }

    public Account getCreator() { return creator; }
    public void setCreator(Account creator) { this.creator = creator; }

    public LocalDate getCreateDate() { return createDate; }
    public void setCreateDate(LocalDate createDate) { this.createDate = createDate; }

    // toString method
    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", category=" + category +
                ", type=" + type +
                ", creator=" + creator +
                ", createDate=" + createDate +
                '}';
    }
}
