package backend.repository;

import entity.Account;
import entity.Department;
import entity.Position;

import java.util.List;

public interface IAccountRepository {

    List<Account> findAll();

    List<Account> findByUsername(String username);

    List<Department> findAllDepartments();

    List<Position> findAllPositions();

    boolean existById(int id);

    boolean existByUsername(String username);

    boolean existByEmail(String email);

    boolean save(Account account);

    boolean deleteById(int id);

    boolean updateUsername(int id, String username);
}