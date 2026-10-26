package org.example.entity;

public class Position {
    private Integer id;
    private PositionName name;

    public Position() {
    }

    public Position(Integer id, PositionName name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PositionName getName() {
        return name;
    }

    public void setName(PositionName name) {
        this.name = name;
    }
}