public class Position {
    public static Position DEV;
    public static Position TEST;
    public static Position SCRUM_MASTER;
    public static Position PM;
    int id;
    PositionName name;
    enum PositionName {
        DEV, TEST, SCRUM_MASTER, PM
    }
    
    // Constructor
    public Position(int id, PositionName name) {
        this.id = id;
        this.name = name;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public PositionName getName() { return name; }
    public void setName(PositionName name) { this.name = name; }

    // toString method
    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}