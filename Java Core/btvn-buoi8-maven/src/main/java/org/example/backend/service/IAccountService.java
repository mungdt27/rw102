package org.example.backend.service;


import org.example.entity.Account;

import java.util.List;

public interface IAccountService {
    List<Account> findAll();
    boolean create(Account account);
    boolean update(int id, String username);
    boolean delete(int id);
    boolean checkUsernameExists(String username, Integer id);
    boolean checkEmailExists(String email, Integer id);
    boolean checkIdExists(int id);

    List<Account> findByUsername(String username);

    String importCSV(String path);
}