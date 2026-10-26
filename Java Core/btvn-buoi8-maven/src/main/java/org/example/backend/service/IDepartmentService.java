package org.example.backend.service;


import org.example.entity.Department;

import java.util.List;

public interface IDepartmentService {
    List<Department> findAll();

    String importCSV(String path);
}