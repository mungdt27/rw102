package org.example.backend.service.impl;

import org.example.backend.repository.IDepartmentRepository;
import org.example.backend.repository.impl.DepartmentRepositoryImpl;
import org.example.backend.service.IDepartmentService;
import org.example.entity.Department;

import java.util.List;

public class DepartmentServiceImpl
        implements IDepartmentService {

    private IDepartmentRepository repository;

    public DepartmentServiceImpl() {

        repository =
                new DepartmentRepositoryImpl();
    }

    @Override
    public List<Department> findAll() {

        return repository.findAll();
    }

    @Override
    public List<Department> findByName(
            String name
    ) {

        return repository.findByName(name);
    }

    @Override
    public boolean insert(
            Department department
    ) {

        return repository.insert(
                department
        );
    }

    @Override
    public boolean deleteById(
            int id
    ) {

        return repository.deleteById(id);
    }

    @Override
    public boolean updateName(
            int id,
            String name
    ) {

        return repository.updateName(
                id,
                name
        );
    }
}