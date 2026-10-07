package backend.service.impl;

import backend.repository.IDepartmentRepository;
import backend.repository.impl.DepartmentRepositoryImpl;
import backend.service.IDepartmentService;
import entity.Department;

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