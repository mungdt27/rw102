package entity;

public class Position {

    private int id;
    private PositionName name;

    public enum PositionName {
        DEV,
        TEST,
        SCRUM_MASTER,
        PM
    }

    // Constructor
    public Position(int id, PositionName name) {
        this.id = id;
        this.name = name;
    }

    // Getter & Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PositionName getName() {
        return name;
    }

    public void setName(PositionName name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Position{" + "id=" + id + ", name=" + name + '}';
    }
}