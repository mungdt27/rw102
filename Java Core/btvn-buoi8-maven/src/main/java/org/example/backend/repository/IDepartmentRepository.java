package org.example.backend.repository;

import org.example.entity.Department;

import java.util.List;

public interface IDepartmentRepository {
    List<Department> findAll();
    Department findById(int id);
    boolean create(Department department);

    void createAll(List<Department> departments);

    boolean existByName(String name);
}