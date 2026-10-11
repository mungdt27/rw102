package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Position {

    private int id;
    private PositionName name;

    public enum PositionName {
        DEV,
        TEST,
        SCRUM_MASTER,
        PM
    }

    @Override
    public String toString() {
        return "Position{" + "id=" + id + ", name=" + name + '}';
    }
}