package backend.controller;

import backend.service.IDepartmentService;
import backend.service.impl.DepartmentServiceImpl;
import entity.Department;

import java.util.List;

public class DepartmentController {
    private IDepartmentService departmentService;
    public DepartmentController() {
        departmentService = new DepartmentServiceImpl();
    }

    public List<Department> findAll() {
        return departmentService.findAll();
    }

    public List<Department> findByName(String name) {
        return departmentService.findByName(name);
    }

    public boolean insert(Department department) {
        return departmentService.insert(department);
    }

    public boolean deleteById(int id) {
        return departmentService.deleteById(id);
    }

    public boolean updateName(int id, String name) {
        return departmentService.updateName(id, name);
    }
}