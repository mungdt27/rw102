package backend.service;

import entity.Account;

import java.util.List;

public interface IAccountService {

    List<Account> findAll();

    List<Account> findByUsername(
            String username
    );

    boolean insert(Account account);

    boolean deleteByUsername(
            String username
    );

    boolean updateFullName(
            String username,
            String fullName
    );
}