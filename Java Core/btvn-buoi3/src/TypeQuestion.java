public class TypeQuestion {
    int id;
    TypeName name;
    enum TypeName {
        ESSAY, MULTIPLE_CHOICE
    }

    // Constructor
    public TypeQuestion(int id, TypeName name) {
        this.id = id;
        this.name = name;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public TypeName getName() { return name; }
    public void setName(TypeName name) { this.name = name; }

    // toString method
    @Override
    public String toString() {
        return "TypeQuestion{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
