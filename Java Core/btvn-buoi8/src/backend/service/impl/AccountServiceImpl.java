package backend.service.impl;

import backend.repository.IAccountRepository;
import backend.repository.impl.AccountRepositoryImpl;
import backend.service.IAccountService;
import entity.Account;

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
    public boolean insert(
            Account account
    ) {

        return repository.insert(account);
    }

    @Override
    public boolean deleteByUsername(
            String username
    ) {

        return repository.deleteByUsername(
                username
        );
    }

    @Override
    public boolean updateFullName(
            String username,
            String fullName
    ) {

        return repository.updateFullName(
                username,
                fullName
        );
    }
}