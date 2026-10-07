package backend.controller;

import backend.service.IAccountService;
import backend.service.impl.AccountServiceImpl;
import entity.Account;

import java.util.List;

public class AccountController {
    private IAccountService accountService;
    public AccountController() {
        accountService = new AccountServiceImpl();
    }

    public List<Account> findAll() {
        return accountService.findAll();
    }

    public List<Account> findByUsername(String username) {
        return accountService.findByUsername(username);
    }

    public boolean insert(Account account) {
        return accountService.insert(account);
    }

    public boolean deleteByUsername(String username) {
        return accountService.deleteByUsername(username);
    }

    public boolean updateFullName(String username, String fullName) {
        return accountService.updateFullName(username, fullName);
    }
}