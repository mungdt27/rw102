package org.example.context;

import org.example.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentContext {
    private Map<String, Department> mapByName;
}