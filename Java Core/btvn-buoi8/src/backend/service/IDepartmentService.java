package backend.service;

import entity.Department;

import java.util.List;

public interface IDepartmentService {

    List<Department> findAll();

    List<Department> findByName(
            String name
    );

    boolean insert(
            Department department
    );

    boolean deleteById(
            int id
    );

    boolean updateName(
            int id,
            String name
    );
}