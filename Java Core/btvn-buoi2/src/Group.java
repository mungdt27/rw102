import java.time.LocalDate;

public class Group {
    int id;
    String name;
    Account creator;
    LocalDate createDate;

    // Constructor
    public Group(int id, String name, Account creator, LocalDate createDate) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.createDate = createDate;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    void setName(String name) {
        this.name = name;
    }

    public Account getCreator() {
        return creator;
    }
    void setCreator(Account creator) { this.creator = creator; }

    public LocalDate getCreateDate() {
        return createDate;
    }
    void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    // toString method
    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creator=" + creator +
                ", createDate=" + createDate +
                '}';
    }
}