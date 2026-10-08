package backend.service.impl;

import backend.repository.IAccountRepository;
import backend.repository.impl.AccountRepositoryImpl;
import backend.service.IAccountService;
import entity.Account;
import entity.Department;
import entity.Position;

import java.util.List;

public class AccountServiceImpl
        implements IAccountService {

    private IAccountRepository repository;

    public AccountServiceImpl() {

        repository =
                new AccountRepositoryImpl();
    }

    @Override
    public List<Account> findAll() {

        return repository.findAll();
    }

    @Override
    public List<Account> findByUsername(
            String username
    ) {

        return repository.findByUsername(
                username
        );
    }

    @Override
    public List<Department> findAllDepartments() {

        return repository.findAllDepartments();
    }

    @Override
    public List<Position> findAllPositions() {

        return repository.findAllPositions();
    }

    @Override
    public boolean existById(int id) {

        return repository.existById(id);
    }

    @Override
    public boolean existByUsername(
            String username
    ) {

        return repository.existByUsername(
                username
        );
    }

    @Override
    public boolean existByEmail(
            String email
    ) {

        return repository.existByEmail(
                email
        );
    }

    @Override
    public boolean save(Account account) {

        return repository.save(account);
    }

    @Override
    public boolean deleteById(int id) {

        return repository.deleteById(id);
    }

    @Override
    public boolean updateUsername(
            int id,
            String username
    ) {

        return repository.updateUsername(
                id,
                username
        );
    }
}