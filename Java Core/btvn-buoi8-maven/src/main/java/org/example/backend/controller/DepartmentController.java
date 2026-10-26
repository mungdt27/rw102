package org.example.backend.controller;


import org.example.backend.repository.impl.DepartmentRepositoryImpl;
import org.example.backend.service.IDepartmentService;
import org.example.backend.service.impl.DepartmentServiceImpl;
import org.example.entity.Department;

import java.util.List;
import java.util.stream.Collectors;

public class DepartmentController {
    private IDepartmentService service;
    private DepartmentRepositoryImpl departmentRepository;

    public DepartmentController() {
        this.service = new DepartmentServiceImpl();
        this.departmentRepository = new DepartmentRepositoryImpl();
    }

    public List<Department> findAll() {
        return service.findAll();
    }

    public String importCSV(String path) {
        return service.importCSV(path);
    }

    public List<Department> findByName(String name) {
        return departmentRepository.findAll().stream()
                .filter(dep -> dep.getName() != null && dep.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public boolean insert(Department department) {
        return departmentRepository.create(department);
    }

    public boolean deleteById(int id) {
        return false;
    }

    public boolean updateName(int id, String name) {
        return false;
    }
}