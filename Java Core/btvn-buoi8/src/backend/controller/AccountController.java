package backend.controller;

import backend.service.IAccountService;
import backend.service.impl.AccountServiceImpl;
import entity.Account;
import entity.Department;
import entity.Position;

import java.util.List;

public class AccountController {

    private IAccountService accountService;

    public AccountController() {

        accountService =
                new AccountServiceImpl();
    }

    public List<Account> findAll() {

        return accountService.findAll();
    }

    public List<Account> findByUsername(
            String username
    ) {

        return accountService.findByUsername(
                username
        );
    }

    public List<Department> findAllDepartments() {

        return accountService.findAllDepartments();
    }

    public List<Position> findAllPositions() {

        return accountService.findAllPositions();
    }

    public boolean existById(int id) {

        return accountService.existById(id);
    }

    public boolean existByUsername(
            String username
    ) {

        return accountService.existByUsername(
                username
        );
    }

    public boolean existByEmail(
            String email
    ) {

        return accountService.existByEmail(
                email
        );
    }

    public boolean save(Account account) {

        return accountService.save(account);
    }

    public boolean deleteById(int id) {

        return accountService.deleteById(id);
    }

    public boolean updateUsername(
            int id,
            String username
    ) {

        return accountService.updateUsername(
                id,
                username
        );
    }
}