package org.example.backend.controller;


import org.example.backend.service.IAccountService;
import org.example.backend.service.impl.AccountServiceImpl;
import org.example.entity.Account;

import java.util.List;

public class AccountController {
    private IAccountService service;

    public AccountController() {
        this.service = new AccountServiceImpl();
    }

    public List<Account> findAll() {
        return service.findAll();
    }

    public boolean create(Account account) {
        return service.create(account);
    }

    public boolean update(int id, String username) {
        return service.update(id, username);
    }

    public boolean delete(int id) {
        return service.delete(id);
    }

    public boolean checkUsernameExists(String username, Integer id) {
        return service.checkUsernameExists(username, id);
    }

    public boolean checkEmailExists(String email, Integer id) {
        return service.checkEmailExists(email, id);
    }

    public boolean checkIdExists(int id) {
        return service.checkIdExists(id);
    }

    public List<Account> findByUsername(String username) {
        return service.findByUsername(username);
    }

    public String importCSV(String path) {
        return service.importCSV(path);
    }
}